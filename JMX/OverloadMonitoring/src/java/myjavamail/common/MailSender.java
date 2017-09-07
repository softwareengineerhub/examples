/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjavamail.common;

import javax.mail.Message;
import javax.mail.Session;

/**
 *
 * @author Администратор
 */
public interface MailSender {

    public Session initDefaultSession(String host, int port, boolean authEnabled, boolean starttlsEnable);

    public Session initAuthSession(String host, int port, boolean authEnabled, boolean starttlsEnable, final String userName, final String password);

    public Message initMessage(Session session, String from, String to, String subject, String text) throws Exception;

    public void sendMessage(Message msg) throws Exception;
}
