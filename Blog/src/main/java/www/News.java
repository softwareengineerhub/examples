/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

import java.util.Date;

/**
 *
 * @author RSDENIS
 */
public class News {
    private int ID;
    private String title;
    private String description;
    private String text;
    private String author;
    private int view = 0;
    private long date;
    private int decisions = 0;

    public News(){

    }
    
    public News(int ID,String title,String description,String text,String author,int view,long date,int decisions){
        this.ID = ID;
        this.author=author;
        this.date=date;
        this.decisions=decisions;
        this.description=description;
        this.text=text;
        this.title=title;
        this.view=view;
    }

    public String getAuthor() {
        return author;
    }

    public long getDate() {
        return date;
    }

    public int getDecisions() {
        return decisions;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public int getView() {
        return view;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setDecisions(int decisions) {
        this.decisions = decisions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
 
}
