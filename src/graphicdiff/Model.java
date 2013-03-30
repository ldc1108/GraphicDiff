package graphicdiff;

import java.util.ArrayList;


/**
 * Model knows NOTHING about view / context. Could be command line, GUI, web..
 * @author Luke
 */
public class Model {
    
    private ArrayList<CSVLine> clones = new ArrayList();
    int currentLine = 0;
    
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
