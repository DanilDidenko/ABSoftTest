package main.java;


import main.java.Models.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailHandler {

    private String email;
    private String password;
    private boolean isCredentionalsValid = false;

    public boolean setCredantionals(String email, String password) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        try {
            Session session = Session.getInstance(properties, null);
            Transport transport = session.getTransport("smtp");
            transport.connect(email, password);
            transport.close();
            this.email = email;
            this.password = password;
            this.isCredentionalsValid = true;
            return true;
        } catch (AuthenticationFailedException e) {
            Logger.error("AuthenticationFailedException - for authentication failures");
            return false;
        } catch (MessagingException e) {
            Logger.error("for other failures");
            return false;
        }
    }


    public Email sendEmail(String to, String subject, String content) {
        if (!isCredentionalsValid) {
            Logger.error("You have to set your credentionals first");
            return null;
        }
        Properties properties = System.getProperties();

        Logger.info(new StringBuilder().append("Signin in to gmail account ").append(email).toString());

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            System.out.println("Sending email");
            Transport.send(message);

            System.out.println("Sent message successfully");
        } catch (MessagingException e) {
            Logger.error(e.getMessage());
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

        return new Email(this.email, to, subject, content);
    }

}
