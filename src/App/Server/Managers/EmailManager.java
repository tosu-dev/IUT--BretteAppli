package App.Server.Managers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailManager {

    private static String SMTP_HOST;
    private static int    SMTP_PORT;
    private static String SMTP_USERNAME;
    private static String SMTP_PASSWORD;

    public static void setHost(String smtpHost) {
        SMTP_HOST = smtpHost;
    }

    public static void setPort(int smtpPort) {
        SMTP_PORT = smtpPort;
    }

    public static void setUsername(String USERNAME) {
        EmailManager.SMTP_USERNAME = USERNAME;
    }

    public static void setPassword(String PASSWORD) {
        EmailManager.SMTP_PASSWORD = PASSWORD;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "mail.mehdi-ali.me");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        return properties;
    }

    private static Authenticator getAuthenticator() {
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        };
    }

    private static MimeMessage createMessage(Session session, String to, String subject, String message) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(SMTP_USERNAME, "Médiathèque Wakan Tanka"));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);

        return mimeMessage;
    }

    public static void send(String to, String subject, String message) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getInstance(EmailManager.getProperties(), EmailManager.getAuthenticator());

        Transport.send(EmailManager.createMessage(session, to, subject, message));
    }

}
