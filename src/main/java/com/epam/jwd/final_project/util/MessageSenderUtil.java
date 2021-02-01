package com.epam.jwd.final_project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class MessageSenderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderUtil.class);

    private MessageSenderUtil() {
    }

    public static void sendRegistrationEmail(String userEmail) {
        Session emailSession = createSession();

        try (Transport transport = emailSession.getTransport()) {
            MimeMessage message = new MimeMessage(emailSession);
            message.setRecipients(Message.RecipientType.TO, userEmail);
            message.setSubject("Thank you for registration on 'Let's rate it!'");
            message.setText("Hope you will like it!");
            transport.connect("s.zenko1772453@gmail.com", "password");
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            LOGGER.error("Sending message error", e);
        }
    }

    private static Session createSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        return Session.getDefaultInstance(properties);
    }

}
