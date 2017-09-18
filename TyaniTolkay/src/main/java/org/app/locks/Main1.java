/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.app.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author prokopiukd
 */
public class Main1 {
    
    public static final Lock LOCK = new ReentrantLock(true);
    public static final Condition CONDITION = LOCK.newCondition();
    public static Person p;
    public static Shop sh;

    public static void main(String[] args) {
        p = new Person();
        sh = new Shop();
        sh.start();
        p.start();
    }

}
