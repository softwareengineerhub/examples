/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prokopiukd
 */
public class Main {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread a = new Thread() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        countDownLatch.countDown();
                        System.out.println("CountDown.# "+countDownLatch.getCount());
                        Thread.sleep(5000);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        a.start();

        Thread b = new Thread() {

            @Override
            public void run() {
                try {
                    while (countDownLatch.getCount() != 0) {
                        countDownLatch.await();
                    }
                    System.out.println("\tExecution finished!!!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        };
        b.start();
    }

}
