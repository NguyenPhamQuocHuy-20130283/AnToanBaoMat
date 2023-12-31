package org.example.demo.Services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Properties;

public class SendMailService implements Serializable {
    private static final String username = "chung41901@gmail.com";
    private static final String password = "cilqommsedbazdxx";

    public SendMailService() {
    }

    public static boolean sendMail(String toEmail,String subject, String msg) {
        boolean result = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        //test send mail
        String toEmail = "20130283@st.hcmuaf.edu.vn";
        String subject = "Test send mail";
        String msg = "Hello, this is a test mail";

        if (sendMail(toEmail, subject, msg)) {
            System.out.println("Send mail success");
        } else {
            System.out.println("Send mail fail");
        }
    }
}