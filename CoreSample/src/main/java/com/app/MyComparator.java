/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import java.util.Comparator;

/**
 *
 * @author Denis
 */
public class MyComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        System.out.println("Comparator. o1="+o1+"; o2="+o2);
        if (o1.getClass() == String.class && o2.getClass() == Integer.class) {
            return 1;
        } else if (o1.getClass() == Integer.class && o2.getClass() == String.class) {
            return -1;
        } else if (o1.getClass() == Integer.class && o2.getClass() == Integer.class) {
            Integer a = (Integer) o1;
            Integer b = (Integer) o2;
            return a.compareTo(b);
        }
        return 1;
    }

}
