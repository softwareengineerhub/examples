/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Конарх
 */
public class VisitorNotesManager {
    private int siteId = -1;
    private int max = Integer.MAX_VALUE;

    public void setMax(int max) {
        this.max = max;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public Collection<VisitorNote> getNotes() throws Exception{
        return DataModule.getInstance().getVisitorNotes(siteId, max);
    }


}
