/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

import java.util.Arrays;

/**
 *
 * @author Denis
 */
public class SimpleArrayList implements SimpleList {
    private int capacity = 10;
    private Object[] data;    
    private int n;
    
    public SimpleArrayList(){
        data = new Object[capacity];
    }
    

    @Override
    public void add(Object item) {
        if(n+1>data.length){
            Object [] tmp = new Object[data.length+capacity];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data=tmp;
        }
        data[n]=item;
        n++;
    }

    @Override
    public Object get(int index) {
        return data[index];
    }

    @Override
    public Object set(int index, Object value) {
        Object oldValue = data[index];
        data[index]=value;
        return oldValue;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void remove(int index) {
        /*if(index==data.length-1){
            data[n]=null;
            n--;
            return;
        }*/
        for(int i=index;i<n-1;i++){
            data[i]=data[i+1];
        }
        data[n]=null;
        n--;        
    }

    @Override
    public void clear() {
        data = new Object[capacity];
        n=0;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }
    
    @Override
    public int hashCode(){
        return Arrays.hashCode(data);
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        if(obj.getClass()==getClass()){
            SimpleArrayList sal = (SimpleArrayList) obj;
            return Arrays.equals(data, sal.data);            
        }
        return false;
    }
    
}
