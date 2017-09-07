/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

/**
 * TODO: denis: Добавить роль в профиль пользователя.
 * @author Конарх
 */
public class UserProfile {
    private String name;
    private String country;
    private String city;
    private String street;
    private String house;
    private String homeTel;
    private String workTel;
    private String mobTel;
    private String email;
    private String password;//TODO: denis: БАГ: не хранить в пользователе пароль. он хранится только в базе и сравнивается при запросе по мд5

    

    public UserProfile(String name, String country, String city, String street, String house, String homeTel, String workTel, String mobTel, String email, String password) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.homeTel = homeTel;
        this.workTel = workTel;
        this.mobTel = mobTel;
        this.email = email;
        this.password=password;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public String getHouse() {
        return house;
    }

    public String getMobTel() {
        return mobTel;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getWorkTel() {
        return workTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
