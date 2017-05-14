/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Denis
 */
public class App1 {
    
    public static void main(String[] args){
        SimpleList simpleList = new SimpleArrayList();
        simpleList.add(1);
        simpleList.add("dasdsada");
        //Integer a = simpleList.get(0);
        //String IS-A Object
        Object [] a = new String[10];
        Animal[] animals = new Dog[10];
        Dog[] dogs = new Dog[10];
        m(dogs);
        m(animals);
        
        List<Animal> list = new ArrayList<Animal>();
        list.add(new Animal());
        list.add(new Dog());
        m1(list);
        List<Dog> dogList = new ArrayList<Dog>();
        //m1(dogList);
        
        m2(list);
        m2(dogList);
        
        
        List<String> stringList = new ArrayList();
        List<String> stringList1 = new ArrayList<>();
        
        //WildCart
        m3(list);
        m3(dogList);
        
       List<? extends Animal> myList = new ArrayList();
       // List myList = new ArrayList<? extends Animal>();
       //myList.add(new Animal());
       //myList.add(new Dog());
        Animal animal1 = myList.get(0);
        
        List<? super Animal> myList1 = new ArrayList();
        myList1.add(new Animal());
        myList1.add(new Dog());
        //Animal animal1 = myList.get(0);
        
        //CONSUMER - extends (read data via get)
        //PRODUCER - super (write data via put)
        
        
        
        ////
        SimpleList simpleList1 = new SimpleArrayList();
        SimpleList<String> simpleList2 = new SimpleArrayList();
        SimpleList<Integer> simpleList3 = new SimpleArrayList();
        
        simpleList1.add("A");
        simpleList1.add(1);
        
        simpleList2.add("A");
        //simpleList2.add(1);
        
        //simpleList3.add("A");
        simpleList3.add(1);
        
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList();
        simpleArrayList.add("aaaaa");
        
    }
    
    private static void m(Animal[] animals){
        
    }  
    
    private static void m1(List<Animal> list){
        
    }
    
    private static void m2(List list){
        //String s=(String) list.get(0);
    }
    
    private static void m3(List<? extends Animal> list){
        Animal a=list.get(0);
        //list.add(a)
    }
    
}
