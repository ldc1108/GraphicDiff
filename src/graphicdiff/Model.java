package graphicdiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Model knows NOTHING about view / context. Could be command line, GUI, web..
 * @author Luke
 */
public class Model {
    
    private ArrayList<CSVLine> clones = new ArrayList();
    int currentLine = 0;
    
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
        
    }
    
    public void addLine(String fullLine, int lineNumber) {
        CSVLine clone = new CSVLine(fullLine, lineNumber);
        clones.add(clone);
    }
    
    public CSVLine next() {
        if (currentLine+1 < clones.size()) {
            currentLine++;
            return clones.get(currentLine);
        } else {
            return null;
        }
    }
    
    public CSVLine prev() {
       if (currentLine-1 > clones.size()) {
            currentLine--;
            return clones.get(currentLine);
        } else {
            return null;
        }
    }
}
