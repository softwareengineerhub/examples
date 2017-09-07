/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author dprokopiuk
 */
@WebListener(value="asda")
public class MyListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
       
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        
    }
}
