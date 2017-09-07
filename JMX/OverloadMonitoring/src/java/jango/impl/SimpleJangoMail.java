/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jango.impl;

import com.sun.mail.smtp.SMTPMessage;
import jango.basic.JangoMail;
import java.net.InetAddress;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Администратор
 */
public class SimpleJangoMail implements JangoMail {

    private String host;
    private int port;
    private String user;
    private String password;

    @Override
    public Session initSession(String host, int port, String user, String password) throws Exception {
        this.port = port;
        this.host = host;
        this.user = user;
        this.password = password;
        Properties props = initProps();
        return Session.getDefaultInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SimpleJangoMail.this.user, SimpleJangoMail.this.password);
            }

        });
    }

    @Override
    public Message initMessage(Session session, String content, String subject, String from, String to) throws Exception {
        Message msg = new SMTPMessage(session);
        msg.setText(content);
        msg.setSubject(subject);
        InternetAddress addr = new InternetAddress(from);
        msg.setFrom(addr);
        msg.setRecipient(Message.RecipientType.TO, InternetAddress.parse(to)[0]);
        return msg;
    }

    @Override
    public void send(Message msg) throws MessagingException {
        Transport.send(msg);
    }

    protected Properties initProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.host", host); 
        //props.put("mail.smtp.port", "25");
        return props;
    }

}
