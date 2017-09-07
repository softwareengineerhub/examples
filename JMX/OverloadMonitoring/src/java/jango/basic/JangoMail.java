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
public interface JangoMail {

    public Session initSession(String host, int port, String user, String password) throws Exception;

    public Message initMessage(Session session, String content, String subject, String from, String to) throws Exception;

    public void send(Message msg) throws Exception;

}
