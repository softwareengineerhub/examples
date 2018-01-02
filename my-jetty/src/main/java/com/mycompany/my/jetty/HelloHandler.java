/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author prokopiukd
 */
public class HelloHandler extends AbstractHandler {

    @Override
    public void handle(String string, Request rqst, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        hsr1.setContentType("text/html;charset=utf-8");
        hsr1.setStatus(HttpServletResponse.SC_OK);
        rqst.setHandled(true);
        hsr1.getWriter().println("<h1>Hello World</h1>");
    }
    
}
