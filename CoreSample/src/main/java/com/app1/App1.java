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
public class App1 {
    
    public static void main(String[] args){
        String[] a = new String[3];
        System.out.println(a.hashCode());
        System.out.println("Arrays:"+Arrays.hashCode(a));
        a[0]="A";
        a[1]="B";
        a[2]="C";
        System.out.println(a.hashCode());
        System.out.println("Arrays:"+Arrays.hashCode(a));
    }
    
}
