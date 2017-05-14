/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app1;

/**
 *
 * @author Denis
 */
public interface SimpleList<T> {

    public void add(T item);

    public T get(int index);

    public T set(int index, T value);

    public int size();

    public void remove(int index);

    public void clear();

    public boolean isEmpty();

}
