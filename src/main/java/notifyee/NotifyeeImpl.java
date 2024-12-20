/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/03/2024 
 * File Name: NotifyeeImpl.java 
 * Description: A class for notifyee, observer in the observer pattern
 */

package notifyee;

import content.Content;
import util.EmailUtil;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * A notifyee that receive update from Notifier.
 */
public class NotifyeeImpl implements Notifyee {
  private Content content;
  private String fromEmail;
  private String toEmail;
  private String password;

  /**
   * Notifyee with target email address. Sending from chhchen@bu.edu as default.
   * 
   * @param toEmail target email address
   * @param content content of the notification
   */
  public NotifyeeImpl(String toEmail, Content content) {
    this(Dotenv.load().get("EMAIL"), Dotenv.load().get("PASSWORD"), toEmail, content);
  }

  /**
   * Notifyee with target email address from an email account.
   * 
   * @param fromEmail the gmail account that sending email from
   * @param password the password for authorize sending email
   * @param toEmail target email adddress
   * @param content content of the notification
   */
  public NotifyeeImpl(String fromEmail, String password, String toEmail, Content content) {
    this.fromEmail = fromEmail;
    this.password = password;
    this.toEmail = toEmail;
    this.content = content;
  }

  @Override
  public void update() {
    sendEmail();
  }
  
  /**
   * Sned email to a target user with the reuiered content.
   */
  public void sendEmail() {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(fromEmail, password);
      }
    };
    Session session = Session.getInstance(props, auth);

    EmailUtil.sendEmail(session, toEmail, fromEmail, content.getContent());
  }

}
