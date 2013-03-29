
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
    
    // Inner Classes
        // All need callbacks on actionPerformed - finish later
    class browseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
        }
    }
    
    class rightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
        }
    }
    
    class leftListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
        }
    }
    
    class skipListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        
    }
    
    // End Inner Classes
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        
        view.addBrowseListener(new browseListener());
        view.addLeftListener(new leftListener());
        // Finish this later (right and skip)
    }
}
