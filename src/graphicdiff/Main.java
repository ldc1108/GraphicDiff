package graphicdiff;

import java.io.File;
import model.Model;

/**
 *
 * @author Luke
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // You can clearly see the privacy here
        File file = new File(".");
        System.out.println(file.getAbsolutePath());
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        System.out.println("Hello");
        view.appear();
    }
}
