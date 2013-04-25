package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Model knows NOTHING about view / context. Could be command line, GUI, web..
 * @author Luke
 */
public class Model {
    
    private ArrayList<CSVLine> clones = new ArrayList();
    private ArrayList<String[]> cloneMethods = new ArrayList();
    private HashMap<String, String> notesMap = new HashMap();
    
    int currentLine = 0;
    
    // Takes in the directory of the CSV file
    public void processFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String line = reader.readLine();
            int lineNumber = 2;
            while (line != null) {
                System.out.println(line + "| Line Number:"+lineNumber);
                this.addLine(line, lineNumber);
                line = reader.readLine();
                lineNumber++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        // The debugger
        //retreiveMethod(clones.get(194));
        
    }
    
    // Save all the notes the user wrote
    public void saveNotes() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("notes.txt"));
            for (String key : notesMap.keySet()) {
                String value = notesMap.get(key);
                writer.write(String.valueOf(key)+"\n"+value+"\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Add a note to the map
    public void addNotes(String s) {
        notesMap.put(clones.get(currentLine).getFullLine(), s);
    }
    
    // Given a CSVline, returns method content
    // MISTAKE: Should be string array of method contents
    public String[] retreiveMethod(CSVLine csv) {
        // array of size 2 for method contents
        String methods[] = new String[2];
        // Serves no purpose other then getting to curr directory
        File file = new File("");
        // Directory of C File (used for both clones)
        String dir = "";
        // Directory of corresponding ctag file (used for both clones)
        String ctagDir = "";
        // string array of 2 for the ctag method names (the code clone methods)
        String ctagFullMethodName[] = new String[2];
        BufferedReader reader;
        try {
            // For both clones in the CSV
            for (int i=0; i<2; i++) {
                // Get the nth directory code clone
                dir = file.getAbsolutePath() + "\\" + csv.directoryFileNames[i];
                System.out.println("Source Dir:"+dir);
                // navigate to ctag file (source folder hard coded in - sorry)
                ctagDir = dir.replace("httpd-2.2.14", "ctag_output") + ".txt";
                System.out.println("CTag Dir:"+ctagDir);
                
                reader = new BufferedReader(new FileReader(ctagDir));
                
                String line = reader.readLine();
                
                
                
                // Find the method in the ctag file
                while (line != null) {
                    
                    if (line.split("\\s++")[0].toLowerCase().equals(csv.methods[i].toLowerCase()) && line.contains("/^") && line.contains("$/")) {
                        // Adjust index because it starts on the start of the pattern
                        System.out.println("check");
                        int sub1 = line.indexOf("/^")+2;
                        int sub2 = line.indexOf("$/");
                        ctagFullMethodName[i] = line.substring(sub1, sub2);
                        System.out.println("Full Method Name: "+ctagFullMethodName[i]);
                        break;
                    }
                    line = reader.readLine();
                }
                
                reader.close();
                
                
                
                // Now we go into the actual C file and find the ctag method we just got 
                reader = new BufferedReader(new FileReader(dir));
                line = reader.readLine();
                while (line != null) {
                    if (line.contains(ctagFullMethodName[i])) {
                        // use a stack, pop when we see a } and push for {
                        Stack<String> s = new Stack();   
                        // Get the first { to start the stack
                        while (!line.contains("{")) {
                            methods[i] += line + "\n";
                            System.out.println("Current Method Line:"+line);
                            line = reader.readLine();
                        }
                        s.push("{");
                        // In the rare case that there is a "}" on the same line
                        if (line.contains("}")) {
                            s.pop();
                            methods[i] = line;
                        }
                        System.out.println(s.size());
                        System.out.println("Current Method Line:"+line);
                        line = reader.readLine();
                        
                        // Keep going through the method content until stack empty
                        while (s.empty()==false) {
                            if (!reader.ready() && line == null) {
                                System.err.println("EOF BUT STACK !EMPTY");
                                methods[i] += "EOF?";
                                break;
                            }
                            if (line.contains("{")) {
                                for (int a=0; a<countSymbols(line, '{'); a++) {
                                    s.push("{");
                                }
                                System.out.println(s.size());
                            } if (line.contains("}")) {
                                for (int a=0; a<countSymbols(line, '}'); a++) {
                                    s.pop();
                                }
                                System.out.println(s.size());
                            }
                            methods[i] += line + "\n";
                            System.out.println("Current Method Line:"+line);
                            line = reader.readLine();
                        } 
                    // send output to jeditorpane or something in form of string?
                    }
                    line = reader.readLine();
                }
                reader.close();  
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
        }
        return methods;
    }
    
    // Return current leven distance of 
    public int getCurrlevenDistance() {
         return clones.get(currentLine).getLevenDistance();
    }
    
    public int getCurentLine() {
        return currentLine;
    }
    
    public String getCurrFileInfo() {
        return clones.get(currentLine).getFullLine();
    }
    
    // See how many of a given symbol are on a line
    public int countSymbols(String line, char sym) {
        int count = 0;
        for (int i=0; i<line.length(); i++) {
            if (line.charAt(i)==sym) {
                count++;
            }
        }
        return count;
    }
    
    // Add each line from the CSV file here (used by process file)
    public void addLine(String fullLine, int lineNumber) {
        CSVLine clone = new CSVLine(fullLine, lineNumber);
        //System.out.println(lineNumber);
        
        clones.add(clone);
        cloneMethods.add(retreiveMethod(clone));
    }
    
    // Sifts to next element in list of CSVlines (sifts cursor forward)
    public String[] next() {
        if (currentLine+1 < clones.size()) {
            currentLine++;
            return cloneMethods.get(currentLine);
        } else {
            return null;
        }
    }
    
    // Sifts to previous element (sifts cursor back)
    public String[] prev() {
       if (currentLine-1 >= 0) {
            currentLine--;
            return cloneMethods.get(currentLine);
        } else {
            return null;
        }
    }
    
    public String[] skip(int SkipNumber) {
        if (SkipNumber < cloneMethods.size() && SkipNumber >= 0) {
            currentLine = SkipNumber;
            return cloneMethods.get(SkipNumber);
        } else {
            return null;
        }
    }
}
