/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Denis
 */
public class IteratorApp {
   
    public static void main(String[] args){
        ArrayList al = new ArrayList();
        for(Object obj:al ){
            
        }
        for(int i=0;i<al.size();i++){
            
        }
        Set set = new HashSet();
        for(Object obj:set){
            
        }
        
        Iterator itr = al.iterator();
        while(itr.hasNext()){
            Object obj=itr.next();            
            //remove last next object
            itr.remove();
        }
        
        LinkedList linkedList = new LinkedList();
        Iterator descItr=linkedList.descendingIterator();
        for(int i=linkedList.size()-1;i>=0;i--){
            
        }
        System.out.println("----------------------");
        List<String> list = new ArrayList();
        list.add("A");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println("index access:");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println("\nFor each:");        
        for(String str: list){
            System.out.println(str);
        }
        
        System.out.println("\nIterator:");        
        Iterator<String> strIterator = list.iterator();
        while(strIterator.hasNext()){
            String item = strIterator.next();
            System.out.println(item);
        }
        
        try {
            System.out.println("\nConcurrent modification exception:");
            for (String str : list) {
                System.out.println(str);
                if ("c".equalsIgnoreCase(str)) {
                    list.remove(str);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try{
        System.out.println("\nConcurrent modification exception:");
        Iterator<String> strItr = list.iterator();
        while(strItr.hasNext()){
            String item = strItr.next();
            System.out.println(item);
            if("c".equalsIgnoreCase(item)){                
                strIterator.remove();                                
            }
            if("e".equalsIgnoreCase(item)){                
                list.add("G");                                
            }
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        System.out.println("\nList iterator:");
        ListIterator<String> listIterator = list.listIterator();
        while(listIterator.hasNext()){
            String item=listIterator.next();
            listIterator.add("Q");
        }
        System.out.println(list);
        ListIterator<String> descListIterator = list.listIterator(list.size());
        while(descListIterator.hasPrevious()){
            String item=descListIterator.previous();
            System.out.println(item);
        }

        LinkedList ll = new LinkedList();
        ll.descendingIterator();
    }
    
}
