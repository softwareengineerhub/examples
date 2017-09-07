/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jango.basic;

import javax.mail.Message;
import javax.mail.Session;

/**
 *
 * @author Администратор
 */
public class JangoMailSender {

    public void send(JangoMail jangoMail, String host, int port, String content, String subject, String from, String to, String user, String password) throws Exception {
        Session session = jangoMail.initSession(host, port, user, password);
        Message msg = jangoMail.initMessage(session, content, subject, from, to);
        jangoMail.send(msg);
    }
}
