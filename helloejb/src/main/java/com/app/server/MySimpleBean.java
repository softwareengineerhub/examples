package com.app.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import java.io.Serializable;

@Singleton
@Startup
public class MySimpleBean {

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void init(){
        System.out.println("MySimpleBean.init()");

        ScheduleExpression se = new ScheduleExpression();
        se.second("*/20");
        se.minute("*");
        se.hour("*");

        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(false);
        timerConfig.setInfo("THis is metadata");
        Timer timer = timerService.createCalendarTimer(se, timerConfig);
    }

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void doTask(){
        //System.out.println("MySimpleBean.doTask()");
    }

    public void doAction(){
        System.out.println("doAction()");
    }

    public String doAction2(int a){
        System.out.println("doAction2();a="+a);
        return "This is doAction2";
    }


    @Timeout
    public void doTask2(Timer t){
        Serializable info = t.getInfo();
        //System.out.println("info="+info);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("MySimpleBean.destroy()");
    }

}
