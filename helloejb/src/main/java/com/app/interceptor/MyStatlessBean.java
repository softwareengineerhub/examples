package com.app.interceptor;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@Interceptors(value = {MyInterceptor.class})
public class MyStatlessBean {

    @PostConstruct
    public void init(){
        System.out.println("MyStatlessBean.init()");
    }

    @WebMethod
    public int add(int a, int b){
        return a+b;
    }

    @Schedule(second = "*/20", minute = "*", hour = "*")
    public void regularTask(){
        System.out.println("MyStatlessBean.regularTask()");
    }
}
