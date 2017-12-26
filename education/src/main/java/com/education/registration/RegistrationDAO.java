/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

/**
 *
 * @author prokopiukd
 */
public class RegistrationDAO {

    public void createUser(String login, String password, DataSource ds) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO user_role(login, pass, role) VALUES(?,MD5(?),?)");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, "user");
            ps.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
