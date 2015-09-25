package com.azovstal.ziptest.core.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SampleSMTPClient2 {

    public static void main(String args[]) throws Exception {

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "vmail1" );
        properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
        
		Session session = Session.getDefaultInstance(properties, new SMTPAuthenticator() );
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("jboss@azovstal.com.ua"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("budukh-rn@azovstal.com.ua"));
        message.setSubject("Wishes..!");
        message.setText("Have a great day!");

        System.out.println("Sending Message .....");
        Transport.send(message);
        System.out.println("Message Sent.....");
    }
}