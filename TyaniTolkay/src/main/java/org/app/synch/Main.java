/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.app.synch;

/**
 *
 * @author prokopiukd
 */
public class Main {
   
    public static Producer p;
    public static Consumer c;
    
    public static void main(String[] args) {
        p = new Producer();
        c = new Consumer();
        p.start();
        c.start();
    }
    
}
