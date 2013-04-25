package graphicdiff;

import model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Controller processes user requests - Inner classes are observer patterns
 * @author Luke
 */
public class Controller {

    Model model;
    View view;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        
        view.addBrowseListener(new browseListener());
        view.addLeftListener(new leftListener());
        view.addRightListener(new rightListener());
        view.addSkipListener(new skipListener());
    }
    
    // Inner Classes
        // All need callbacks on actionPerformed - finish later
    class browseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Browse");
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(new JFrame());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                chooser.getSelectedFile());
            }
            model.processFile(chooser.getSelectedFile());
            
        }
    }
    
    class rightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            System.out.println("Right");
            String[] clones = model.next();
            if (clones!=null) {
                view.setPanel1(clones[0]);
                view.setPanel2(clones[1]);
            }
        }
    }
    
    class leftListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            System.out.println("Left");
            String[] clones = model.prev();
            if (clones!=null) {
                view.setPanel1(clones[0]);
                view.setPanel2(clones[1]);
            }
        }
    }
    
    class skipListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Skip");
            String skipNumber = view.getSkipField();
            try {
                int skipInt = Integer.parseInt(skipNumber);
                String[] clones = model.skip(skipInt);
                if (clones!=null) {
                    view.setPanel1(clones[0]);
                    view.setPanel2(clones[1]);
                }
            } catch (NumberFormatException numForEx) {
             
            } 
        }
    }
    
    // End Inner Classes 
}