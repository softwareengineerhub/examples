/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

/**
 *
 * @author Конарх
 */
public class SiteProfile implements Comparable<SiteProfile>{
    private int id;
    private String screenshot;
    private String name;
    private String desc;
    private String directLink;
    private String category;
    private String subcategory;
    private String registrationType;
    private int totalAmountOfCounters;
    private int[] counters;

    public SiteProfile(int id, String screenshot, String name, String desc, String directLink, String category, String subcategory, String regType, int[] counters) {
        this.id = id;
        this.screenshot = screenshot;
        this.name = name;
        this.desc = desc;
        this.directLink = directLink;
        this.category = category;
        this.subcategory = subcategory;
        this.registrationType = regType;
        this.counters=counters;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDirectLink() {
        return directLink;
    }

    public void setDirectLink(String directLink) {
        this.directLink = directLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String toString(){
        return "SiteProfile: name="+name+" desc="+desc+" img="+screenshot+" link="+directLink;
    }

    public int getTotalAmountOfCounters() {
        return totalAmountOfCounters;
    }

    public void setTotalAmountOfCounters(int totalAmountOfCounters) {
        this.totalAmountOfCounters = totalAmountOfCounters;
    }

    public int compareTo(SiteProfile o) {
           int delta=getTotalAmountOfCounters()-o.getTotalAmountOfCounters();
           //if(delta==0)return 1;
           //return (delta)/Math.max(1, Math.abs(delta));
           return delta;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int[] getCounters() {
        return counters;
    }

    public void setCounters(int[] counters) {
        this.counters = counters;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    

}
