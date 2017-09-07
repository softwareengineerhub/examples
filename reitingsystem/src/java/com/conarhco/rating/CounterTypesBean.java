/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

import java.sql.SQLException;
import java.util.Map;

/**
 * Бин для вывода типов счетчиков
 * @author Конарх
 */
public class CounterTypesBean {
    private String type = null;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
 
    public Map<String, Integer> getCounters() throws SQLException{
        return DataModule.getInstance().getCounterTypesByRegType(type);
    }
}
