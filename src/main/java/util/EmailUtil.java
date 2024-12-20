/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 11/30/2024 
 * File Name: EmailUtil.java 
 * Description: Email Util for sending out Email.
 */

package util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Email Utility for sending out email.
 */
public class EmailUtil {
  /**
   * Send out a HTML email.
   * 
   * @param session session for sender authorization
   * @param to target email address
   * @param from sender's email adddress
   * @param body email body
   */
  public static void sendEmail(Session session, String to, String from, String body) {
    try {
      MimeMessage msg = new MimeMessage(session);
      // set message headers
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");

      // set message properties
      ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
      msg.setFrom(new InternetAddress(from));
      msg.setReplyTo(InternetAddress.parse(from, false));
      msg.setSubject("News from MyNotifier on " + now.toLocalDate(), "UTF-8");
      msg.setText(body, "UTF-8");
      msg.setSentDate(Date.from(now.toInstant()));
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
      // msg.setContent(body, "text/html; charset=utf-8");

      Transport.send(msg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
