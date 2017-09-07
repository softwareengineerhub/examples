/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

/**
 *
 * @author rol
 */
public class CounterBean {
    
    private int id;
    private int count;
    private String name;

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CounterBean(int id, int count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }
    
    
}
