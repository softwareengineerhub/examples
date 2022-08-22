/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.management.AttributeValueExp;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.QueryExp;
import javax.management.StringValueExp;

/**
 *
 * @author DProkopiuk
 */
@Startup
@Singleton(mappedName = "centerMonitoring")
@RunAs(value = "trader")
@DeclareRoles({"trader"})
public class ExceptionCenterMonitoring implements MonitoringProcessor {

    @Resource(lookup = "java:comp/env/jmx/domainRuntime")
    private MBeanServer server;

    @PostConstruct
    public void init() {
        process("suspend");
        System.out.println("ExceptionCenterMonitoring");
    }

    @Schedule(persistent = false, dayOfWeek = "1", minute = "0", hour = "8")
    private void startExceptionCenter() {
        System.out.println("Exception center is started");
        process("resume");
    }

    @Schedule(persistent = false, dayOfWeek = "1", minute = "0", hour = "18")
    private void stopExceptionCenter() {
        System.out.println("Exception center is stopped");        
        process("suspend");
    }

    @Override
    public void process(String operation) {        
        if("suspend".equalsIgnoreCase(operation)){
            availabilityManager.setNeedToConsume(false);
        }
        if("resume".equalsIgnoreCase(operation)){
            availabilityManager.setNeedToConsume(true);
        }
        
        /*try {
            QueryExp exp = Query.anySubString(new AttributeValueExp("Name"), new StringValueExp("ExceptionCenter"));
            Set<ObjectName> set = server.queryNames(null, exp);
            for (ObjectName item : set) {
                MBeanInfo beanInfo = server.getMBeanInfo(item);
                for (MBeanOperationInfo element : beanInfo.getOperations()) {
                    if (operation != null && operation.equalsIgnoreCase(element.getName())) {
                        Object res = server.invoke(item, operation, null, null);
                        break;
                    }
                }
            }
            System.out.println(String.format("Monitoring.operation=%s", operation));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }*/
    }

}
