/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjavamail.common;

import com.sun.mail.smtp.SMTPMessage;
import java.net.InetAddress;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Администратор
 */
public class DefaultMailSender implements MailSender {

    @Override
    public Session initDefaultSession(String host, int port, boolean authEnabled, boolean starttlsEnable) {
        Properties props = initSettings(host, port, authEnabled, starttlsEnable);
        return Session.getDefaultInstance(props);
    }

    @Override
    public Session initAuthSession(String host, int port, boolean authEnabled, boolean starttlsEnable, final String userName, final String password) {
        Properties props = initSettings(host, port, authEnabled, starttlsEnable);
        return Session.getDefaultInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }

        });
    }

    private Properties initSettings(String host, int port, boolean authEnabled, boolean starttlsEnable) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.auth", String.valueOf(authEnabled));
        props.put("mail.smtp.starttls.enable", String.valueOf(starttlsEnable));
        return props;
    }

    @Override
    public Message initMessage(Session session, String from, String to, String subject, String text) throws Exception {
        Message msg = new SMTPMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(text);
        return msg;
    }

    @Override
    public void sendMessage(Message msg) throws Exception {
        Transport.send(msg);
    }
}
