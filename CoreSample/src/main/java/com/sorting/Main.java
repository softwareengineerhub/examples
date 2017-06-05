/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorting;

import java.util.Arrays;

/**
 *
 * @author Denis
 */
public class Main {

    public static void main(String[] args) {        
        Sorter sorter = getSorter(1);
        int[] data = {3, 1, 7, 2, 0, 4, 8};
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    private static Sorter getSorter(int type) {
        switch (type) {
            case 0:
                return new BubbleSorter();
            case 1:
                return new SelectionSorter();    
            default:
                throw new IllegalArgumentException("No sorter for type=" + type);
        }
    }

}
