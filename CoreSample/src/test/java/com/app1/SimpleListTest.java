/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Denis
 */
public class SimpleListTest {
    
    @Test
    public void addTest(){
        SimpleList list = new SimpleArrayList();
        assertTrue(list.isEmpty());
        list.add(1);
        list.add(1);
        list.add(1);
        assertTrue(list.size()==3);
        assertFalse(list.isEmpty());
        SimpleList list1 = new SimpleArrayList();
        list1.add(1);
        list1.add(1);
        list1.add(1);
        assertEquals(list, list1);
        assertEquals(list.hashCode(), list1.hashCode());
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getTest(){
        SimpleList list = new SimpleArrayList();
        assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        list.add("B");
        assertEquals(list.get(0).toString(), "1");
        assertEquals(list.get(1).toString(), "2");
        assertEquals(list.get(2), "B");
        list.get(-1);
    }
    
    @Test
    public void setTest(){
        SimpleList list = new SimpleArrayList();
        assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        list.add("B");
        Object oldValue=list.set(2, "A");
        assertEquals(oldValue, "B");
        assertEquals(list.get(2), "A");
    }
    
    @Test
    public void clearTest(){
        SimpleList list = new SimpleArrayList();
        assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        list.add("B");
        list.clear();        
        assertEquals(list.size()+"", "0");
    }
    
    @Test
    public void removeTest(){
        SimpleList list = initList();              
        list.remove(0);        
        assertTrue(list.size()==10);
        for(int i=0;i<list.size();i++){
            assertTrue(Integer.parseInt(list.get(i).toString())==i+1);
        }
        
        list = initList();
        list.remove(5);        
        assertTrue(list.size()==10);
        for(int i=0;i<5;i++){
            assertTrue(Integer.parseInt(list.get(i).toString())==i);
        }
        for(int i=5;i<list.size();i++){
            assertTrue(Integer.parseInt(list.get(i).toString())==i+1);
        }
               
        list = initList();  
        list.remove(10);
        assertTrue(list.size()==10);                
        for(int i=0;i<list.size();i++){
            assertTrue(Integer.parseInt(list.get(i).toString())==i);
        }        
    }
    
    private  SimpleList  initList(){
        SimpleList list = new SimpleArrayList();                
        for(int i=0;i<11;i++){
            list.add(i);
        }
        return list;
    }
}