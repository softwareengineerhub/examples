/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorting;

/**
 *
 * @author Denis
 */
public class SelectionSorter implements Sorter {

    @Override
    public void sort(int[] data) {
        for(int i=0;i<data.length-1;i++){
            int max = data[i];
            for(int j=i+1;j<data.length;j++){
                if(data[j]>max){
                    max = data[j];                    
                    data[j]=data[i];
                    data[i]= max;
                }
            }
        }
    }
    
}
