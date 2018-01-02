/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app04.server.with.servletcontext;

import com.mycompany.my.jetty.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author prokopiukd
 */
public class ServerWithServletContext {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);        
        context.setContextPath("/hello");
        server.setHandler(context);
        
        context.addServlet(new ServletHolder(new HelloServlet()), "/*");
        context.addServlet(new ServletHolder(new HelloServlet("Denis")), "/denis/*");
        context.addServlet(new ServletHolder(new HelloServlet("Piter")), "/piter/*");
        context.addServlet(new ServletHolder(new HelloServlet("Boris")), "/boris/*");        
        
        server.start();
        server.join();
    }
}
