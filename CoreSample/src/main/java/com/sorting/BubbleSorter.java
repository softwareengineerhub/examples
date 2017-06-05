/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorting;

/**
 *
 * @author Denis
 * 
 * 
 * 
 */
public class BubbleSorter implements Sorter {

    //n2
    @Override
    public void sort(int[] data) {
        for(int i=0;i<data.length-1;i++){
            for(int j=i;j<data.length-1;j++){                
                if(data[i]<data[j+1]){
                    /*int tmp = data[i];
                    data[i] = data[j+1];
                    data[j+1]=tmp;*/
                    data[i] = data[j+1]+data[i];
                    data[j+1] = data[i]-data[j+1];
                    data[i]=data[i]-data[j+1];    
                }
            }
        }
    }
    
}
