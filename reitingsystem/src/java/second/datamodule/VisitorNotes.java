/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package second.datamodule;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Денис
 */
public class VisitorNotes {
    private String name;
    private String note;
    private Date d;
    private Time t;

    public VisitorNotes(String name, String note, Date d, Time t) {
        this.name = name;
        this.note = note;
        this.d = d;
        this.t = t;
    }

    public Date getD() {
        return d;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public Time getT() {
        return t;
    }

    
}
