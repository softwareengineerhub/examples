/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app01.server.simple;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author prokopiukd
 */
public class SimplestServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        server.start();
        server.join();
    }

}
