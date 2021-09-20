package com.devm8.stockalarms.sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final String mail = "testdevm8@gmail.com";
    private static final String password = "parolameadetestdevm8";

    public static void sendEmail(String toMail,String subject,String message) {
        Properties properties = getProperties();
        Session session = getSession(properties);
        try {
            MimeMessage mineMessage = getMimeMessage(subject, message, toMail, session);
            Transport.send(mineMessage);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }

    }

    private static MimeMessage getMimeMessage(String subject, String message, String to, Session session) throws MessagingException {
        MimeMessage mineMessage = new MimeMessage(session);
        mineMessage.setFrom(new InternetAddress(EmailSender.mail));
        mineMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mineMessage.setSubject(subject);
        mineMessage.setText(message);
        return mineMessage;
    }

    private static Session getSession(Properties properties) {
        Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mail, password);

            }

        });
        session.setDebug(true);
        return session;
    }

    private static Properties getProperties() {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    public static void main(String[] args) {
        sendEmail("muntearadumihai@gmail.com","Test Mail","Test message");
    }
}

