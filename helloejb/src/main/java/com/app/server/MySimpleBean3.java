package com.app.server;

import javax.ejb.*;
import java.util.concurrent.Future;

@Singleton
@Asynchronous
public class MySimpleBean3 {

    public void doMethod(){
        System.out.println("doMethod.start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doMethod().finish");
    }


    public Future doMethod2(){
        System.out.println("doMethod2.start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doMethod2().finish");
        return new AsyncResult("We are EJBs");
    }


}
