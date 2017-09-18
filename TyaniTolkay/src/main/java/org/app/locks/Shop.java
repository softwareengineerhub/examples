/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.app.locks;

/**
 *
 * @author prokopiukd
 */
public class Shop extends Thread {

    private int value;
    private boolean produced;

    @Override
    public void run() {
        while (true) {
            try {
                Main1.LOCK.lock();
                while (produced) {
                    Main1.CONDITION.await();
                }
                System.out.println("Produced: " + (++value));
                produced = true;
                Main1.CONDITION.signal();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                Main1.LOCK.unlock();
            }
        }
    }

    public int consume() {
        produced = false;
        return value;
    }

    public boolean isProduced() {
        return produced;
    }

}
