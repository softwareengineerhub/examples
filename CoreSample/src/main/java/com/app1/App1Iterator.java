/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

import java.util.Iterator;

/**
 *
 * @author Denis
 */
public class App1Iterator {
    
    public static void main(String[] args){
        SimpleList<String> simpleList = new SimpleArrayList();
        simpleList.add("A");
        simpleList.add("b");
        simpleList.add("c");
        for(String s: simpleList){
            System.out.println(s);
        }
        System.out.println("-----------------");
        Iterator<String> itr = simpleList.iterator();
        while(itr.hasNext()){
            String str = itr.next();
            System.out.println(str);
            simpleList.remove(0);
        }
    }
    
}
