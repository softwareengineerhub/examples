package com.mycompany.my.jetty.app06.server.with.api.defaltservlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.my.jetty.HelloServlet;
import java.net.URL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author prokopiukd
 */
public class ServletWithAPIDefaultServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        WebAppContext context = new WebAppContext();
        URL url = ServletWithAPIDefaultServer.class.getResource("/webapp");
        String resourceBaseDir = url.toExternalForm();
        context.setResourceBase(resourceBaseDir);
        context.setContextPath("/");
        server.setHandler(context);
        ServletHolder defaultServlet = new ServletHolder(DefaultServlet.class);
        defaultServlet.setName("Default");
        defaultServlet.setInitParameter("useFileMappedBuffer", "false");

        context.addServlet(defaultServlet, "/*");
        context.addServlet(new ServletHolder(new HelloServlet("Denis")), "/denis/*");
        context.addServlet(new ServletHolder(new HelloServlet("Piter")), "/piter/*");
        context.addServlet(new ServletHolder(new HelloServlet("Boris")), "/boris/*");

        server.start();
        server.join();
    }

}
