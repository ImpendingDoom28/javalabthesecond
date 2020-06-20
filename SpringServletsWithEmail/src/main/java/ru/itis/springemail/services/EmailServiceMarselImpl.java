package ru.itis.springemail.services;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailServiceMarselImpl implements EmailService {

        private String username = "debtcounter88@gmail.com";
        private String password = "nhelysqgfhjkm10";
        private Properties props;
        private Session session;

        public EmailServiceMarselImpl() {
            props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            this.session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }

        @Override
        public void sendEmail(String to, String subject, String freemarkerText) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setContent(freemarkerText, "text/html");
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
}
