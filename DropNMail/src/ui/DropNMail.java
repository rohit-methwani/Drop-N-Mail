
package ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Raju Methwani
 */
public class DropNMail extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = null;
        try {
            root = FXMLLoader.load(new URL("file:///D:\\DropNMail\\src\\fxml\\PageOne.fxml"));
//        root.getChildren().add(btn);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception ex) {
            Logger.getLogger(DropNMail.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
