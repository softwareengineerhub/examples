/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Это не баг, это фича. не трогать. если в базе остутствует запись с количеством счётчика (counterdata), то счётчик не отображается
 * @author Конарх
 */
public class SiteManager {

    private SiteProfile currentSite;
    private List<Set<CounterBean>> counters = new ArrayList<Set<CounterBean>>();
    private int countersAmount = 0;

    public void setSite(String name) throws SQLException {
        currentSite = DataModule.getInstance().getSiteProfile(name);
        //System.out.println("site profile: "+currentSite);
    }

    public void setSiteId(int id) throws SQLException {
        currentSite = DataModule.getInstance().getSiteProfileByID(id);
        //System.out.println("site profile: "+currentSite);
    }

    public SiteProfile getSiteProfile() {
        return currentSite;
    }

    public Map<String, Map<Date, Integer>> getCounters() throws SQLException {
        Collection<String> countNames = DataModule.getInstance().getCounterNamesForSite(currentSite);
        Map<String, Map<Date, Integer>> map = new HashMap<String, Map<Date, Integer>>();
        for (String s : countNames) {
            map.put(s, DataModule.getInstance().getCounter(currentSite, s));
        }
        return map;
    }

    public Set<CounterBean> getSiteCounters() throws SQLException {
        return DataModule.getInstance().getSiteCounters(currentSite.getId());
    }

    public List<Set<CounterBean>> getSplittedSiteCounters() throws SQLException {
        return DataModule.getInstance().getSplittedSiteCounters(currentSite.getId());
    }

    public Map<String, Integer> getCountersTotal() throws SQLException {
        Collection<String> counterNames = DataModule.getInstance().getCounterNamesForSite(currentSite);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String name : counterNames) {
            map.put(name, DataModule.getInstance().getAmountForCounter(currentSite, name));
        }
        return map;
    }

    public Map<String, Integer> getCounterCodes() throws SQLException {
        return DataModule.getInstance().getCountersTotal(currentSite);
    }

    public int getTotalAmountOfCounters() throws SQLException {
        countersAmount = DataModule.getInstance().getTotalAmountOfCounters(currentSite);
        return countersAmount;
    }

    public int getCountersAmount() {
        if (countersAmount == 0) {
            try {
                getTotalAmountOfCounters();
            } catch (SQLException ex) {
                countersAmount =-1;
                ex.printStackTrace();
            }
        }
        return countersAmount;
    }
}
