/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app05.server.with.web.context;

import com.mycompany.my.jetty.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author prokopiukd
 */
public class ServerWithWebContext {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        WebAppContext context = new WebAppContext();
        context.setResourceBase("/webapp");
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
