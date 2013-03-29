/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicdiff;

/**
 *
 * @author Luke
 */
public class GraphicDiff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // You can clearly see the privacy here
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        
        view.appear();
    }
}
