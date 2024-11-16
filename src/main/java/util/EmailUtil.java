package util;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtil {

  private static final Logger LOGGER = Logger.getLogger(EmailUtil.class);
  // Setting logger
  static {
    try {
      // Create Layout out object
      PatternLayout layout = new PatternLayout();
      layout.setConversionPattern("[%-5l] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}:%L - %m%n");
      // Create Appender object having layout object
      ConsoleAppender appender = new ConsoleAppender(layout);
      appender.setName("STDOUT");
      // Add Appender object to Logger object
      LOGGER.addAppender(appender);
      // Logger level to retrieve log message
      LOGGER.setLevel(Level.DEBUG);
      // Logger info
      LOGGER.info("Email::Log4j Setup ready");

    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.fatal("Email::Problem while setting up Log4j");
    }
  }

  public static void sendEmail(String emailFrom, int OTP) {
    LOGGER.info("Email::SendEmail start setting");

    final PropertyHelper propertyHelper = ObjectContainer.getPropertyHelper();
    Session session =
        Session.getInstance(propertyHelper.getProperties(), new jakarta.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(propertyHelper.getMailUsername(),
                propertyHelper.getMailPassword());
          }
        });

    try {
      String otpString = String.valueOf(OTP);

      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(propertyHelper.getMailUsername()));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailFrom));
      message.setSubject("Mã xác minh cho email khôi phục: " + otpString);

      String htmlContent = "<html>" + "<body>" + "<h1>Xác minh email khôi phục của bạn</h1>"
          + "<p>Sử dụng mã này để hoàn tất việc thiết lập email khôi phục của bạn:</p>"
          + "<h2 style='color: blue;'>" + otpString + "</h2>" + "</body>" + "</html>";

      Multipart multipart = new MimeMultipart();

      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setText("Xác minh email khôi phục của bạn. Sử dụng mã này: " + otpString, "UTF-8");

      MimeBodyPart htmlPart = new MimeBodyPart();
      htmlPart.setContent(htmlContent, "text/html; charset=UTF-8");

      multipart.addBodyPart(textPart);
      multipart.addBodyPart(htmlPart);

      message.setContent(multipart);

      LOGGER.debug("Email::SendEmail prepared message. Sending...");
      Transport.send(message);
      LOGGER.info("Email::SendEmail successfully sent OTP to " + emailFrom);

    } catch (MessagingException e) {
      LOGGER.error("Email::SendEmail failed to send OTP to " + emailFrom, e);
      throw new RuntimeException("Failed to send email", e);
    }
  }

}
