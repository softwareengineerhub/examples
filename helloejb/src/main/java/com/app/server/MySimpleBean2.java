package com.app.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import java.io.Serializable;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MySimpleBean2 {

    @Lock(LockType.READ)
    public void doOperation(){
        System.out.println("doOperation().start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doOperation().finish");
    }
}
