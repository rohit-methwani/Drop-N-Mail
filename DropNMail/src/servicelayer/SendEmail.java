package servicelayer;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
  This class does the job of sending the email using javax.mail api.
  @author:Rohit Methwani
*/

public class SendEmail {

  /**
    Data Members.
  */
  private String to,from,password,path,subject;
  private String host ="smtp.gmail.com";
  private MimeMessage message;
  private MimeBodyPart messageBodyPart;
  private Session session;
  private Multipart multipart;
  private Properties props;
  private DataSource source;
  private boolean status;

  
  /**
    This method sends the FileList to the appropriate email address.
    @param:FileList which has to be sent.
    @return:Nothing.
  */
  public void send(List<File> fileList) {

      props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "465");

      session = Session.getInstance(props,new javax.mail.Authenticator() {
        @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
        }
      });
      try {
      message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
      messageBodyPart = new MimeBodyPart();
      multipart = new MimeMultipart();
      message.setSubject(subject);

      for(File f:fileList){
        messageBodyPart = new MimeBodyPart();
        source = new FileDataSource(f.getPath());
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(f.getName());
        multipart.addBodyPart(messageBodyPart);
      }

      message.setContent(multipart);

      Transport.send(message);
      setStatus(true);

    }
    catch (Exception e) {
  }
  }

  /*---------------SETTERS----------------*/
  public void setFromAddress(String address){
    this.from = address;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public void setSubject(String subject){
    this.subject = subject;    
  }

  public void setToAddress(String address){
    this.to=address;
  }

  public void setAttachmentPath(String path){
    this.path = path;
  }

  public void setStatus(boolean stat){
    this.status=stat;
  }


}


