/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

import java.sql.SQLException;

/**
 *
 * @author Конарх
 */
public class UserManager {
    private UserProfile profile = null;

    public void setLogin(String name) throws Exception{
        profile=DataModule.getInstance().getUserProfileByLogin(name);
    }

    public void setSiteId(int id) throws Exception{
        profile = DataModule.getInstance().getUserProfileBySite(id);
    }

    public UserProfile getUserProfile(){
        return profile;
    }



}
