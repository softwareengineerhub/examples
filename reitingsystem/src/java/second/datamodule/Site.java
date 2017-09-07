/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package second.datamodule;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Денис
 */
public class Site {

    private String user;
    private String name;
    private String descr;
    private String screen;
    private String link;
    private Date added;
    private boolean free;

    private String registration;

    private String city;
    private String country;
    private String subcategory;
    private String category;
    private String street;
    private String house;
    private String homeTel;
    private String workTel;
    private String mobiTel;
    private String email;
    private List<String> keyWords;
    private List<VisitorNotes> visitorNotes;
    private List<UserNews> userNews;

    public Site(String user, String name, String descr, String screen, String link, Date added, boolean free, String registration, String city, String country, String subcategory, String category, String street, String house, String homeTel, String workTel, String mobiTel, String email) {
        this.user = user;
        this.name = name;
        this.descr = descr;
        this.screen = screen;
        this.link = link;
        this.added = added;
        this.free = free;
        this.registration = registration;
        this.city = city;
        this.country = country;
        this.subcategory = subcategory;
        this.category = category;
        this.street = street;
        this.house = house;
        this.homeTel = homeTel;
        this.workTel = workTel;
        this.mobiTel = mobiTel;
        this.email = email;
    }


    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMobiTel() {
        return mobiTel;
    }

    public void setMobiTel(String mobiTel) {
        this.mobiTel = mobiTel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getStrret() {
        return street;
    }

    public void setStrret(String strret) {
        this.street = strret;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<UserNews> getUserNews() {
        return userNews;
    }

    public void setUserNews(List<UserNews> userNews) {
        this.userNews = userNews;
    }

    public List<VisitorNotes> getVisitorNotes() {
        return visitorNotes;
    }

    public void setVisitorNotes(List<VisitorNotes> visitorNotes) {
        this.visitorNotes = visitorNotes;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }
}
