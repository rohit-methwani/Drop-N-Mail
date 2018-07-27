package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
    FXML Controller class
    @author Rohit Methwani
 */
public class PageOneController implements  Initializable {

    /**
        FXML Data Members.
    */
    @FXML
    private TextField emailtf;
    @FXML
    private Pane rootpane;
    @FXML
    private Pane login;
    @FXML
    private Button loginbtn;
    @FXML
    private TextField passfield;

    /**
        Data Members.
    */
    private static String username;
    private static String password;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    


    /**
        The below method is triggered once the login button is clicked.This method loads the fxml file of another frame and sets it to the rootpane.
    */
    @FXML
    private void changeframe(MouseEvent event) {
       changeFrame();
    }
    
    /*----------GETTERS-----------*/
    public static String getUserName(){
        return username;
    }
    public static String getPassword(){
        return password;
    }

    /**
     * The below method validates if the email is in the proper format or not.
     * @param:Email string which has to be validated.
     * @return:Boolean value indicating success if true else false.
     */
    public boolean validateEmail(String email){
         String emailPatternString = "^[_A-Za-z0-9\\+]+(\\.[_A-Za-z0-9-\\+]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern emailPattern = Pattern.compile(emailPatternString);
            Matcher emailMatcher = emailPattern.matcher(email);
            if(emailMatcher.matches()){
                return true;
            }
            else{
                return false;
            }
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        System.out.println("KeyPressed");
        if(event.getCode().toString().equals("13")){
            changeFrame();
        }
    }
    public void changeFrame(){
         try{
            VBox root =  FXMLLoader.load(new URL ("file:///D:\\DropNMail\\src\\fxml\\MainPage.fxml"));
            username = emailtf.getText();
            password = passfield.getText();
            if(validateEmail(username)){
                rootpane.getChildren().setAll(root);
            }
            else{
                emailtf.setStyle("-fx-text-fill:rgb(255,0,0)");
            }
            
        } 
        catch(IOException ex) {
            Logger.getLogger(PageOneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void keyReleased(KeyEvent event) {
         System.out.println("KeyPressed");
        if(event.getCode().toString().equals("13")){
            changeFrame();
        }
    }

    @FXML
    private void keyPress(KeyEvent event) {
         System.out.println("KeyPressed");
        if(event.getCode().toString().equals("13")){
                System.out.println("Enter key pressed");
                changeFrame();
        }
    }
}
