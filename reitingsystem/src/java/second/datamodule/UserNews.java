/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package second.datamodule;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Денис
 */
public class UserNews {

    private Date d;
    private Time t;
    private String news;
    private String userName;

    public UserNews(Date d, Time t, String news, String userName) {
        this.d = d;
        this.t = t;
        this.news = news;
        this.userName = userName;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Time getT() {
        return t;
    }

    public void setT(Time t) {
        this.t = t;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
