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
public interface SimpleList {

    public void add(Object item);

    public Object get(int index);

    public Object set(int index, Object value);

    public int size();

    public void remove(int index);

    public void clear();

    public boolean isEmpty();

}
