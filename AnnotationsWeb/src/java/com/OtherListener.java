/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author dprokopiuk
 */
public class OtherListener implements HttpSessionBindingListener, HttpSessionActivationListener{

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
