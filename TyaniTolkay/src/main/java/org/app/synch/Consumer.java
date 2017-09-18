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
public class Consumer extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    while (!Main.p.isProduced()) {
                        wait();
                    }
                }
                synchronized (Main.p) {
                    System.out.println("\tConsumed: " + (Main.p.consume()));
                    Main.p.notify();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
