package ui;

import servicelayer.SendEmail;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;


/**
    FXML Controller class
    @author Rohit Methwani
 
*/
public class MainPageController implements Initializable {

    /**
        FXML Data Members.
    */
    @FXML
    private Pane header;
    @FXML
    private Button exit;
    @FXML
    private Pane mainbackground;
    @FXML
    private Label notification;
    @FXML
    private TextField sendAddress;
    
    /**
        Data Members.
    */
    private SendEmail email=new SendEmail();
    private List<File> fileList;
    private String toEmail="";
    private SystemTray tray = SystemTray.getSystemTray();
    private TrayIcon icon = new TrayIcon( Toolkit.getDefaultToolkit().createImage("C:\\Users\\Raju Methwani\\Desktop\\clickdimensions-icons_1x.png"),"Your Message has been sent!");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       notification.setText("  "); 
    }    

    /**
        The below method is triggered on mouse click of the Exit button.
    */
    @FXML
    private void exit(MouseEvent event) {
        mainbackground.setDisable(true);
        System.exit(0);
    }

    /**
        The below method is triggered once you started dropping the files over the panel.
    */
    @FXML
    private void dOver(DragEvent event) {
            if(event.getGestureSource()!=mainbackground && event.getDragboard().hasFiles()){
               mainbackground.setStyle("-fx-background-color:rgba(52,198,211,0.1)");
               event.acceptTransferModes(TransferMode.COPY);
            }
    }

    /**
        The below method is triggered once you have dropped the files on the panel.
    */
    @FXML
    private void dragdrop(DragEvent event) {
     mainbackground.setStyle("-fx-background-color:rgb(255,255,255)");
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            String filePath = null;
            fileList = db.getFiles();
            for(File f:fileList){
                notification.setText("File attached:"+f.getName());
            }
        }
        event.setDropCompleted(success);
    
    }

    /**
        The below method is triggered once you clicked the send button.
    */
    @FXML
    private void mouseClick(MouseEvent event) {
        if(sendAddress.getText().equals("")){
            System.out.println("Inside this!");
            notification.setStyle("-fx-text-fill:#Ed2939");
            notification.setText("Please Enter the email!");
        }
        else{
                email.setFromAddress(PageOneController.getUserName());
                email.setPassword(PageOneController.getPassword());
                if(validateEmail(sendAddress.getText())){
                    notification.setStyle("-fx-text-fill:#34c6d3");
                    notification.setText("Your Message is on its way!");
                    sendAddress.setStyle("-fx-text-fill:#3cb371");
                    email.setToAddress(sendAddress.getText());
                    email.send(fileList);
                    notification.setText("Your message has been sent sucessfully!");
                }
                else{
                    sendAddress.setStyle("-fx-text-fill:#Ed2939");
                    notification.setText("Please enter correct email!");
                }
        }
    }
    
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
    private void onMouseClick(MouseEvent event) {
        sendAddress.requestFocus();
    }
}
