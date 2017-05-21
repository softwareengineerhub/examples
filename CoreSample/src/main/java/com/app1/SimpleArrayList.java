/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 *
 * @author Denis
 */
public class SimpleArrayList <T> implements SimpleList<T> {
    private int capacity = 10;
    private Object[] data;    
    private int n;
    private long version;
    
    public SimpleArrayList(){
        data = new Object[capacity];
    }
    

    @Override
    public void add(T item) {
        if(n+1>data.length){
            Object [] tmp = new Object[data.length+capacity];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data=tmp;
        }
        data[n]=item;
        n++;
        version++;
    }

    @Override
    public T get(int index) {
        return (T)data[index];
    }

    @Override
    public T set(int index, Object value) {
        version++;
        Object oldValue = data[index];
        data[index]=value;
        return (T)oldValue;
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
        version++;
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

    @Override
    public Iterator<T> iterator() {
        
        return new Iterator<T>(){
            
            private int n;
            private long iteratorVersion=version;
            private T currentValue;
            
            @Override
            public boolean hasNext() {
                return n<size();
            }

            @Override
            public T next() {            
                if(iteratorVersion!=version){
                    throw new ConcurrentModificationException();
                }
                T val=get(n);
                n++;
                currentValue=val;
                return val;
            }

            @Override
            public void remove() {
                if(iteratorVersion!=version){
                    throw new ConcurrentModificationException();
                }
                //SimpleArrayList.this.remove(currentValue);
            }
            
        };
    }
    
}
