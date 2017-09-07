/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Конарх
 */
public class VisitorNote {
    private String note;
    private String name;
    private Date date;
    private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public VisitorNote(String name, String note, Date date) {
        this.note = note;
        this.name = name;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString(){
        return format.format(date);
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

}
