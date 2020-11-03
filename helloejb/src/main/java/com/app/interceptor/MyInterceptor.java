package com.app.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

public class MyInterceptor {

    @AroundInvoke
    public Object proceed(InvocationContext ctx) throws Exception {
        System.out.println("@@@@@@@MyInterceptor-BEGIN");
        Object res = ctx.proceed();
        System.out.println("@@@@@@@MyInterceptor-FINISH");
        return res;
    }


    @AroundTimeout
    public Object proceedTimer(InvocationContext ctx) throws Exception {
        System.out.println("@@@@@@@MyInterceptorTimeout-BEGIN");
        Object res = ctx.proceed();
        System.out.println("@@@@@@@MyInterceptorTimeout-FINISH");
        return res;
    }


}
