/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app02.server.with.handler;

import com.mycompany.my.jetty.HelloHandler;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author prokopiukd
 */
public class ServerWithHandler {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        server.setHandler(new HelloHandler());
        server.start();
        server.join();
    }
}
