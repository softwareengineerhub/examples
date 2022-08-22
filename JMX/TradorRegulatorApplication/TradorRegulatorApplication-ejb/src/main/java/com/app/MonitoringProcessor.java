/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import javax.ejb.Remote;

/**
 *
 * @author DProkopiuk
 */
@Remote
public interface MonitoringProcessor {

    public void process(String operation);

}
