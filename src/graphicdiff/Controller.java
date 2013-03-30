package graphicdiff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        }
    }
    
    class rightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            System.out.println("Right");
        }
    }
    
    class leftListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            System.out.println("Left");
        }
    }
    
    class skipListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Skip");
        }
    }
    
    // End Inner Classes 
}