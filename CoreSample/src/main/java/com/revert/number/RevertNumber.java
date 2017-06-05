/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revert.number;

/**
 *
 * @author Denis
 */
public class RevertNumber {
    
    public static void main(String[] args){        
        int a = 123;
        int res=revert(a);
        System.out.println(res);
        System.out.println(sumOfNumbers(a));
    }
    
    
    //123 = 1*100 + 2*10+3*1;
    /*a) res=0;
         123%10=3-->!=0
         res = 0*10 + 3;
         res = 3;
         a=12;
    b)  res=3;
        12%10=2-->!=0
        res = 3*10+2;
    
    */
    //5/2->2
    private static int revert(int a){        
        int res=0;
        while(a%10!=0){
            res=res*10+a%10;
            a=a/10;
        }
        return res;
    }
    
    private static int sumOfNumbers(int a){        
        int sum=0;
        while(a%10!=0){
            sum+=a%10;
            a=a/10;
        }
        return sum;
    }
}
