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
public class Producer extends Thread {

    private int value;
    private boolean produced;

    @Override
    public void run() {
        while (true) {
            try {

                synchronized (this) {
                    while (produced) {
                        wait();
                    }
                    System.out.println("Produced: " + (++value));
                    produced = true;
                }
                synchronized (Main.c) {
                    Main.c.notify();
                }

                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean isProduced() {
        return produced;
    }

    public int consume() {
        produced = false;
        return value;
    }

}
