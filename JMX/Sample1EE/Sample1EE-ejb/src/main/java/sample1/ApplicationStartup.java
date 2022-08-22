/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample1;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 *
 * @author Администратор
 */
@Singleton
@Startup
public class ApplicationStartup {
    @Resource(lookup = "java:/comp/env/jmx/domainRuntime")
    private MBeanServer server;
    
    @PostConstruct
    public void init(){
        try {
            Trader trader = new Trader();
            ObjectName key = new ObjectName("test:key=value");
            if(server.isRegistered(key)){
                server.unregisterMBean(key);
            }
            server.registerMBean(trader, key);
            System.out.println("Init finished");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
