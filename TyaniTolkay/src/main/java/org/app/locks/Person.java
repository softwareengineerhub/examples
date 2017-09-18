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
public class Person extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Main1.LOCK.lock();

                while (!Main1.sh.isProduced()) {
                    Main1.CONDITION.await();
                }
                System.out.println("\tConsumed: " + Main1.sh.consume());
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                Main1.LOCK.unlock();
            }
        }
    }

}
