/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app07.server.with.api.filter;

import com.mycompany.my.jetty.HelloServlet;
import com.mycompany.my.jetty.app06.server.with.api.defaltservlet.ServletWithAPIDefaultServer;
import java.net.URL;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author prokopiukd
 */
public class ServletWithAPIDefaultServerAndFilter {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        WebAppContext context = new WebAppContext();
        server.setHandler(context);
        setUpServlets(context);
        setUpFilters(context);
        server.start();
        server.join();
    }
    
    private static void setUpServlets(WebAppContext context){        
        URL url = ServletWithAPIDefaultServer.class.getResource("/webapp");
        String resourceBaseDir = url.toExternalForm();
        context.setResourceBase(resourceBaseDir);
        context.setContextPath("/");
        ServletHolder defaultServlet = new ServletHolder(DefaultServlet.class);
        defaultServlet.setName("Default");
        defaultServlet.setInitParameter("useFileMappedBuffer", "false");

        context.addServlet(defaultServlet, "/*");
        context.addServlet(new ServletHolder(new HelloServlet("Denis")), "/denis/*");
        context.addServlet(new ServletHolder(new HelloServlet("Piter")), "/piter/*");
        context.addServlet(new ServletHolder(new HelloServlet("Boris")), "/boris/*");
    }
    
    private static void setUpFilters(WebAppContext context){
        FilterHolder filter = new FilterHolder(MyServletFilter.class);
        filter.setName("ServletFilter");
        filter.setInitParameter("Application", "com.mycompany.my.jetty.app07.server.with.api.filter.App");
        context.addFilter(filter, "/*", EnumSet.allOf(DispatcherType.class));
        
    }

}
