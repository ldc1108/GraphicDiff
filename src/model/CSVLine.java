package model;

/**
 *
 * @author Luke
 */
public class CSVLine {
    
    /* All one line:
     * 
     * httpd-2.2.14_copy.modules.aaa.mod_authn_alias..authn_alias_get_realm_hash
     * .txt-httpd-2.2.14_copy.modules.aaa.mod_authn_alias
     * ..authn_alias_check_password.txt,6
     * 
     * Meaning:
     * 
     * httpd-2.2.14_copy is the apache root (rename your root if you need to)
     * .  -> go into this directory OR open this C file
     * .. -> the next portion is the method call (portion before is file)
     * -  -> the next portion is the other directory/c file/method
     * ,6 -> the leven distance (irrelevant)
     */
    
    private String fullLine = "";
    int lineNumber = 0;
    private int levenDistance = 0;
    // Break the full line into substrings
    String[] directoryFileNames = new String[2];
    String[] methods = new String[2];
    
    
    
    public CSVLine(String fullLine, int lineNumber) {
        this.fullLine = fullLine;
        this.lineNumber = lineNumber;
        for (int i=0; i<2; i++) {
            // Get the 1st or 2nd "clone" in code clone
            String half = fullLine.split(",")[0].split("httpd-2.2.14_copy")[i+1];
            levenDistance = Integer.parseInt(fullLine.split(",")[1]);
            //System.out.println("Half"+i+":"+half);
            String method = half.substring(half.indexOf("..")).replace("..", "").replace(".txt-", "");
            
            String directoryFileName = half.substring(0, half.indexOf(".."));
            directoryFileName = directoryFileName.replace(".", "\\");
            directoryFileName += ".C";
            
            directoryFileNames[i] = ("httpd-2.2.14" + directoryFileName);
            methods[i] = method.replace("-","").replace(".txt", "");
            //System.out.println(directoryFileNames[i]);
            System.out.println("CSV Methods:"+methods[i]);
        }
    }
    
    public String[] getDirectoryFileName() {
        return directoryFileNames;
    }
    
    public String[] getMethods() {
        return methods;
    }

    /**
     * @return the levenDistance
     */
    public int getLevenDistance() {
        return levenDistance;
    }

    /**
     * @param levenDistance the levenDistance to set
     */
    public void setLevenDistance(int levenDistance) {
        this.levenDistance = levenDistance;
    }

    /**
     * @return the fullLine
     */
    public String getFullLine() {
        return fullLine;
    }

    /**
     * @param fullLine the fullLine to set
     */
    public void setFullLine(String fullLine) {
        this.fullLine = fullLine;
    }
    
}
