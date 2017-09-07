package com.action;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DProkopiuk
 */
import jango.basic.JangoMailSender;
import jango.impl.SimpleJangoMail;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import javax.naming.InitialContext;
import myjavamail.common.DefaultMailSender;
import myjavamail.common.MailSender;

import weblogic.j2ee.descriptor.wl.JMSBean;
import weblogic.j2ee.descriptor.wl.JMSConnectionFactoryBean;
import weblogic.j2ee.descriptor.wl.QueueBean;
import weblogic.jms.extensions.JMSModuleHelper;
import weblogic.management.configuration.JMSSystemResourceMBean;

public class JMSMonitoring {

    private static boolean adminWasNotified;

    private static MBeanServerConnection connection;
    private static JMXConnector connector;
    private static ObjectName service;

    static {
        try {
            System.out.println("…");
            service = new ObjectName("com.bea:Name=RuntimeService," + "Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
        } catch (MalformedObjectNameException e) {
            throw new AssertionError(e.getMessage());
        }
    }

    public static void initConnection(String hostname, String portString, String username, String password) throws Exception {
        String protocol = "t3";
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.runtime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);
        Hashtable h = new Hashtable();
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
        connector = JMXConnectorFactory.connect(serviceURL, h);
        connection = connector.getMBeanServerConnection();
    }

    public static ObjectName[] getJMSServers() throws Exception {
        ObjectName serverRuntime = (ObjectName) connection.getAttribute(service, "ServerRuntime");
        ObjectName jmsRuntime = (ObjectName) connection.getAttribute(serverRuntime, "JMSRuntime");
        ObjectName[] jmsServers = (ObjectName[]) connection.getAttribute(jmsRuntime, "JMSServers");
        for (ObjectName item : jmsServers) {
            System.out.println(item);
        }
        return jmsServers;
    }

    public static void testMethod() {
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            env.put(Context.PROVIDER_URL, "t3://localhost:7001");
            env.put(Context.SECURITY_PRINCIPAL, "root");
            env.put(Context.SECURITY_CREDENTIALS, "11111111");
            Context ctx = new InitialContext(env);
            ObjectName[] serverRT = getJMSServers();
            int length = (int) serverRT.length;
            for (int i = 0; i < length; i++) {
                ObjectName[] queues = (ObjectName[]) connection.getAttribute(serverRT[i], "Destinations");
                String jmsServerName = (String) connection.getAttribute(serverRT[i], "Name");
                System.out.println("JMS Server name:" + jmsServerName);
                int queueCount = (int) queues.length;
                for (int k = 0; k < queueCount; k++) {
                    String queueNameWithModule = (String) connection.getAttribute(queues[k], "Name");
                    Long messagesCurrentCount = (Long) connection.getAttribute(queues[k], "MessagesCurrentCount");
                    System.out.println("!!!!!!!!!!!!messagesCurrentCount=" + messagesCurrentCount);
                    System.out.println("!!!!!!!!!!!!queueNameWithModulet=" + queueNameWithModule);
                    if (messagesCurrentCount > 10) {
                        makeMailNotification();
                    } else {
                        adminWasNotified = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    String hostname = "localhost";
                    String portString = "7001";
                    String username = "root";
                    String password = "11111111";
                    System.out.println("initializing conenction….");
                    initConnection(hostname, portString, username, password);
                    JMSMonitoring.testMethod();
                    connector.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 1, 5000);

    }

    public static void makeMailNotification() throws Exception {
        if (!adminWasNotified) {
            JangoMailSender jangoMailSender = new JangoMailSender();
            String user = "denys.prokopiuk";
            String password = "266634UZ";
            String from = "denisprokopiuk@gmail.com";
            String to = "denis4321@ukr.net";
            String host = "relay.jangosmtp.net";
            jangoMailSender.send(new SimpleJangoMail(), host, 25, "http://localhost:7001/TradorRegulatorApplication-web/", "Please start ExceptionCenter", from, to, user, password);
            adminWasNotified = true;
        }
    }

}
