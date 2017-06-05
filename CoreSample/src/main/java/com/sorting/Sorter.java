/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorting;

/**
 * Bubble, Selection, Insertion, Merge --> O(n)
 * Sn=(a1+an)*2/n
 * d = an-an-1;
 * 
 * Linear Search, Binary Search
 * 
 * @author Denis
 */
public interface Sorter {
    
    public void sort(int[] data);
    
}
