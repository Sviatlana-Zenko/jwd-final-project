package com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Final class that provides static methods for sending
 * email messages to app users.
 */
public final class MessageSenderUtil {

    /**
     * A constant that is the Logger class object to log information
     * about exceptions that may arise during email sending process.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderUtil.class);
    /**
     * A constant that is the ApplicationProperties object that
     * contains info about properties required for email sending.
     */
    private static final ApplicationProperties properties = PropertyReaderUtil.getApplicationProperties();
    /**
     * String that contains the subject of the message for a newly registered user
     */
    private static final String SUBJECT = "Thank you for registration on 'Let's rate it!'";
    /**
     * String that contains the text of the message for a newly registered user
     */
    private static final String TEXT = "Hope you will like it!";

    private MessageSenderUtil() {
    }

    /**
     * Sends a message to the newly registered user confirming his
     * registration in the application.
     *
     * @param userEmail email address to send message
     */
    public static void sendRegistrationEmail(String userEmail) {
        Session emailSession = createSession();

        try (Transport transport = emailSession.getTransport()) {
            MimeMessage message = new MimeMessage(emailSession);
            message.setRecipients(Message.RecipientType.TO, userEmail);
            message.setSubject(SUBJECT);
            message.setText(TEXT);
            transport.connect(properties.getAppEmail(), properties.getEmailPassword());
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            LOGGER.error("Message sending error", e);
        }
    }

    /**
     * Creating a Session object that'll be used in constructing message for sending.
     *
     * @return a Session object with needed configurations
     */
    private static Session createSession() {
        Properties sessionProperties = new Properties();
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.host", "smtp.gmail.com");
        sessionProperties.put("mail.smtp.starttls.enable", "true");
        sessionProperties.put("mail.smtp.port", "587");

        return Session.getDefaultInstance(sessionProperties);
    }

}
