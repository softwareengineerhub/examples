/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Denis
 */
public class App1 {

    public static void main(String[] args) {
        // Collection collection = null;
        // List, Set
        //Collection c = List;
        //Collection c = Set;

        //List - order, index access, same item
        //Set - no order, no index access, unique
        List list1 = new ArrayList();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        System.out.println(list1);// order
        System.out.println(list1.get(1));//b
        list1.add("a");
        list1.add("b");
        list1.add("c");
        System.out.println(list1.size());

        System.out.println("---------------LinkedList");
        //LinkedList
        List list2 = new LinkedList();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        System.out.println(list2);// order
        System.out.println(list2.get(1));//b
        list2.add("a");
        list2.add("b");
        list2.add("c");
        System.out.println(list2.size());
        LinkedList list3 = new LinkedList();

        //-------------SET--------------------------
        System.out.println("-----HashSet--------");
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        System.out.println(set1);
        Iterator itr = set1.iterator();
        while (itr.hasNext()) {
            Object val = itr.next();
            System.out.println(val);
        }
        System.out.println("-------------------");
        for (Object item : set1) {
            System.out.println(item);
        }
        System.out.println("--------Persons-----------");
        Set set2 = new HashSet();

        Person p = new Person();
        p.setAge(1);
        p.setName("A");

        Person p1 = new Person();
        p1.setAge(2);
        p1.setName("B");

        Person p2 = new Person();
        p2.setAge(3);
        p2.setName("C");
        int hashCode2 = System.identityHashCode(p2);

        set2.add(p);
        set2.add(p1);
        set2.add(p2);
        System.out.println(set2);

        Person p3 = new Person();
        p3.setAge(3);
        p3.setName("C");
        set2.add(p3);
        System.out.println(set2);
        //System.out.println(p3);
        int hashCode3 = System.identityHashCode(p3);
        System.out.println("hashCode2=" + hashCode2);
        System.out.printf("hashCode3=%s\n", hashCode3);
        for (Object obj : set2) {
            System.out.println(System.identityHashCode(obj));
        }

        System.out.println("---------------------------");
        Set linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("A");
        linkedHashSet.add("B");
        linkedHashSet.add("C");
        System.out.println(linkedHashSet);
        
        System.out.println("------------TreeSet---------------");
        Set treeSet = new TreeSet();        
        treeSet.add(3);
        treeSet.add(7);
        treeSet.add(10);
        treeSet.add(0);
        treeSet.add(-1);
        //treeSet.add("A");
        System.out.println(treeSet);
        
        System.out.println("------------TreeSet-Comparator--------------");
        TreeSet treeSet1 = new TreeSet(new MyComparator());               
        treeSet1.add(3);
        treeSet1.add(7);
        treeSet1.add(10);
        treeSet1.add(0);
        treeSet1.add(-1);
        //treeSet.add("A");
        System.out.println(treeSet1);
        
        
        System.out.println("------------TreeSet-Comparator--------------");
        //TreeSet treeSet2 = new TreeSet(new MyPersonComparator());               
        //TreeSet treeSet2 = new TreeSet();    
        TreeSet treeSet2 = new TreeSet(new MyPersonComparator());     
        treeSet2.add(p);
        treeSet2.add(p1);
        treeSet2.add(p2);
        treeSet2.add(p3);        
        System.out.println(treeSet2);
                
        //MAP (key, value) - key is a set
        System.out.println("------------MAP----------------");
        Map map = new HashMap();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        for(Object key: map.keySet()){
            Object res=map.get(key);
            System.out.println(key+"="+res);   
        }
               
        
        System.out.println("------LinkedHashMap---------");
        Map map1 = new LinkedHashMap();
        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");
        for(Object key: map1.keySet()){
            Object res=map1.get(key);
            System.out.println(key+"="+res);   
        }
        
        System.out.println("------TreeMap---------");
        Map map2 = new TreeMap();
        map2.put(1, "A");
        map2.put(2, "B");
        map2.put(3, "C");
        for(Object key: map2.keySet()){
            Object res=map2.get(key);
            System.out.println(key+"="+res);   
        }
        System.out.println("---------------");
        Object oldValue=map2.put(2, "Test");
        System.out.println("oldValue="+oldValue);
        for(Object key: map2.keySet()){
            Object res=map2.get(key);
            System.out.println(key+"="+res);   
        }
        
        
    }

}
