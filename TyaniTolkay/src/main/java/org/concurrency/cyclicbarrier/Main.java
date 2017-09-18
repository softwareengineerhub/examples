/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.concurrency.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author prokopiukd
 */
public class Main {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread a = new Thread() {

            @Override
            public void run() {
                try {
                    System.out.println("A. start");
                    System.out.println("A. @Before 1");
                    Thread.sleep(5000);
                    barrier.await();
                    Thread.sleep(10000);
                    System.out.println("A. @After 1");
                    System.out.println("A. @Before 2");
                    barrier.await();
                    System.out.println("A. @After 2");
                    System.out.println("A. finish");
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
                    System.out.println("B. start");
                    System.out.println("B. @Before 1");
                    barrier.await();
                    System.out.println("B. @After 1");
                    System.out.println("B. @Before 2");
                    Thread.sleep(5000);
                    barrier.await();
                    Thread.sleep(10000);
                    System.out.println("B. @After 2");
                    System.out.println("B. finish");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        };
        b.start();
    }

}
