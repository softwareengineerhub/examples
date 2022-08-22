/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author DProkopiuk
 */
@MessageDriven(mappedName = "myQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ExceptionCenter implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("--------------------------------");
            System.out.println("Exception Message is processing:");
            TextMessage msg = (TextMessage) message;
            System.out.println(msg.getText());
            System.out.println("--------------------------------");
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }

}
