package graphicdiff;

import jsyntaxpane.DefaultSyntaxKit;
import model.Model;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * View knows only about Model
 * @author Luke
 */
public class View extends JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browse;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel editorPanePanels;
    private javax.swing.JPanel seperator1;
    private javax.swing.JPanel seperator2;
    private javax.swing.JPanel seperator3;
    private javax.swing.JPanel jumpFormPanel;
    private javax.swing.JPanel seperator4;
    private javax.swing.JTextField levenDistance;
    
    private javax.swing.JScrollPane scrollPane1;
    //private RTextScrollPane sp1;
    private javax.swing.JScrollPane scrollPane2;
    //private RTextScrollPane sp2;
    
    private javax.swing.JTextField jumpField;
    private javax.swing.JButton left;
    
    private javax.swing.JTextArea fileInfo;
    private javax.swing.JEditorPane clonePane1;
    //private RSyntaxTextArea rsta1;
    private javax.swing.JEditorPane clonePane2;
    //private RSyntaxTextArea rsta2;
    
    private javax.swing.JButton right;
    private javax.swing.JButton skip;
    // End of variables declaration//GEN-END:variables

    Model model;
    /**
     * Creates new form GUI
     */
    public View() {
        initComponents();
    }
    
    public View(Model model) {
        initComponents();
        this.model = model;
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        
         try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        leftPanel = new javax.swing.JPanel();
        browse = new javax.swing.JButton();
        seperator1 = new javax.swing.JPanel();
        right = new javax.swing.JButton();
        seperator2 = new javax.swing.JPanel();
        left = new javax.swing.JButton();
        seperator3 = new javax.swing.JPanel();
        jumpFormPanel = new javax.swing.JPanel();
        jumpField = new javax.swing.JTextField();
        skip = new javax.swing.JButton();
        seperator4 = new javax.swing.JPanel();
        levenDistance = new javax.swing.JTextField();
        levenDistance.setEditable(false);
        levenDistance.setText("Levenshtein Distance: ");
        
        editorPanePanels = new javax.swing.JPanel();
        
        jsyntaxpane.DefaultSyntaxKit.initKit();
        fileInfo = new JTextArea();
        clonePane1 = new javax.swing.JEditorPane();   
        scrollPane1 = new javax.swing.JScrollPane(clonePane1);
        clonePane1.setContentType("text/c");
        
        clonePane2 = new javax.swing.JEditorPane();
        scrollPane2 = new javax.swing.JScrollPane(clonePane2);
        clonePane2.setContentType("text/c");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Set layout for west pane (Buttons and stuff)
        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("CloneDiff - Luke Coy"));
        leftPanel.setLayout(new java.awt.GridLayout(10, 1));

        browse.setText("Browse (CSV)");
        
        leftPanel.add(browse);
        
        leftPanel.add(seperator1);
        
        right.setText("->");
        leftPanel.add(right);

        leftPanel.add(seperator2);
        
        left.setText("<-");
        leftPanel.add(left);

        leftPanel.add(seperator3);
        
        jumpFormPanel.setLayout(new java.awt.GridLayout(1, 2));
        jumpFormPanel.add(jumpField);
        skip.setText("Jump to it!");
        jumpFormPanel.add(skip);
        leftPanel.add(jumpFormPanel);
        
        leftPanel.add(seperator4);
        leftPanel.add(levenDistance);
        
        getContentPane().add(leftPanel, java.awt.BorderLayout.LINE_START);
        editorPanePanels.setLayout(new java.awt.GridLayout(1, 2));

        editorPanePanels.add(scrollPane1);
        
        editorPanePanels.add(scrollPane2);
        
        getContentPane().add(editorPanePanels, java.awt.BorderLayout.CENTER);
        getContentPane().add(fileInfo, java.awt.BorderLayout.SOUTH);

        pack();
    }
    
    /**
     * @param args the command line arguments
     */
    public void appear() {
       this.setVisible(true);
    }

    public void setPanel1(String s) {
        clonePane1.setText(s);
    }
    
    public void setPanel2(String s) {
        clonePane2.setText(s);
    }
    
    public String getSkipField() {
        return jumpField.getText();
    }
    
    void addBrowseListener(ActionListener list) {
        browse.addActionListener(list);
    }
    
    void addRightListener(ActionListener list) {
        right.addActionListener(list);
    }
    
    void addLeftListener(ActionListener list) {
        left.addActionListener(list);
    }
    
    void addSkipListener(ActionListener list) {
        skip.addActionListener(list);
    }
    
    void setLevenDistance(int l) {
        levenDistance.setText("Levenshtein Distance: "+l);
    }
    
    void setFileInfo(String s) {
        fileInfo.setText(s);
    }
}
