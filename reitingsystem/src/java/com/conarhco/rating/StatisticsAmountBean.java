/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

/**
 *
 * @author Денис
 */
public class StatisticsAmountBean {

    private int total;
    private int today;
    private int yesterday;
    private int beforYesterday;

    public StatisticsAmountBean() {
    }

    public StatisticsAmountBean(int total, int today, int yesterday, int beforYesterday) {
        this.total = total;
        this.today = today;
        this.yesterday = yesterday;
        this.beforYesterday = beforYesterday;
    }

    public int getBeforYesterday() {
        return beforYesterday;
    }

    public void setBeforYesterday(int beforYesterday) {
        this.beforYesterday =  Math.max(0,beforYesterday-yesterday-today);
    }

    public int getToday() {
        return today;
    }

    public void setToday(int today) {
        this.today = today;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = Math.max(0,total);
    }

    public int getYesterday() {
        return yesterday;
    }

    public void setYesterday(int yesterday) {
        this.yesterday = Math.max(0,yesterday-today);
    }

    public void add(int index, int amount) {
        switch (index) {
            case 0:
                setToday(amount);
                break;
            case 1:
                setYesterday(amount);
                break;
            case 2:
                setBeforYesterday(amount);
                break;
            case 3:
                setTotal(amount);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
