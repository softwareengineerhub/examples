/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import sample2.Trader;

/**
 *
 * @author DProkopiuk
 */
@Singleton
@Startup
public class ApplicationStartup {

    @Resource(lookup = "java:comp/env/jmx/domainRuntime")
    private MBeanServer server;

    @PostConstruct
    public void init() {
        try {
            ObjectName key = new ObjectName("sample:key=value");
            Trader trader = new Trader();
            if(server.isRegistered(key)){
                server.unregisterMBean(key);
            }
            server.registerMBean(trader, key);            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
