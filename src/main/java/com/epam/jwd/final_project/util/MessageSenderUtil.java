package com.epam.jwd.final_project.util;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class MessageSenderUtil {

    private MessageSenderUtil() {

    }

    public static void sendRegistrationEmail(String newUserEmail) {
        Session emailSession = createSession();

        try (Transport transport = emailSession.getTransport()) {
            MimeMessage message = new MimeMessage(emailSession);

            message.setRecipients(Message.RecipientType.TO, newUserEmail);
            message.setSubject("Thank you for registration on Let's rate it!");
            message.setText("Hope you will like it!");

            transport.connect("s.zenko1772453@gmail.com", "pmyeczlujmvehntt");
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("Your message has been successfully sent.");
        } catch (MessagingException e) {
            System.out.println("Your message has not been sent. Please contact support service.");
            // log + error
        }
    }

    public static void sendMessageAboutNewProduct() {

    }

    private static Session createSession() {
        Session emailSession;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        emailSession = Session.getDefaultInstance(properties);

        return emailSession;
    }



//    public Email createAdminEmail(List<User> users, Book book) {
//        Email adminEmail;
//        String addressFrom;
//        String addressesTo;
//        String password;
//        String messageSubject;
//        String messageBody;
//
//        addressFrom = baseLogic.getAdminEmail(users);
//        addressesTo = baseLogic.getAddressesOfGeneralUsers(users);
//        password = input.enterString("\nEnter your e-mail password to send " +
//                "information about new book to users ->");
//
//        messageBody = bookLogic.getFullInfoAboutBook(book);
//
//        messageSubject = "Information about new book in your home library";
//
//        adminEmail = new Email(addressFrom, addressesTo, password, messageSubject, messageBody);
//
//        return adminEmail;
//    }
//
//    public Email createGeneralUserEmail(List<User> users) {
//        Book book;
//        Email generalUserEmail;
//        String addressFrom;
//        String addressesTo;
//        String password;
//        String messageSubject;
//        String messageBody;
//
//        addressFrom = "homeLibrary.generalUser@gmail.com";
//        password = "password_GU";
//        messageSubject = "New book for home library";
//
//        book = bookLogic.createBookUsingConsoleInput();
//        addressesTo = baseLogic.getAdminEmail(users);
//        messageBody = bookLogic.getFullInfoAboutBook(book);
//
//        generalUserEmail = new Email(addressFrom, addressesTo, password, messageSubject, messageBody);
//
//        return generalUserEmail;
//    }
//
//    public void sendEmail(Email email) {
//        Properties properties = new Properties();
//        Session emailSession;
//
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.port", "587");
//
//        emailSession = Session.getDefaultInstance(properties);
//
//        try (Transport transport = emailSession.getTransport()) {
//            MimeMessage message = new MimeMessage(emailSession);
//
//            message.setRecipients(Message.RecipientType.TO, email.getAddressesTo());
//            message.setSubject(email.getMessageSubject());
//            message.setText(email.getMessageBody());
//
//            transport.connect(email.getAddressFrom(), email.getPassword());
//            transport.sendMessage(message, message.getAllRecipients());
//
//            System.out.println("Your message has been successfully sent.");
//        } catch (MessagingException e) {
//            System.out.println("Your message has not been sent. Please contact support service.");
//        }
//    }


}
