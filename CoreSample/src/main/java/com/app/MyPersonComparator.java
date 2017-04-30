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
public class MyPersonComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
       System.out.println("comp");
       
       /*Comparable c1 = (Comparable) o1;
       Comparable c2 = (Comparable) o2;
       return c1.compareTo(c2);*/
       
       Person p1 = (Person) o1;
       Person p2 = (Person) o2;
       return (p1.getAge()-p2.getAge());               
    }
    
}
