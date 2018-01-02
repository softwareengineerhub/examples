/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app03.server.with.context.handler;

import com.mycompany.my.jetty.HelloHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

/**
 *
 * @author prokopiukd
 */
public class ServerWithContextHandler {
    
     public static void main(String[] args) throws Exception {
        Server server = new Server(8089);  
        ContextHandler context = new ContextHandler();
        context.setContextPath("/hello");
        context.setResourceBase(".");
        server.setHandler(context);        
        context.setHandler(new HelloHandler());
        server.start();
        server.join();
    }
    
}
