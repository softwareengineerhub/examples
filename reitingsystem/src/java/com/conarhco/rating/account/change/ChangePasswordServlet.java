/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating.account.change;

import com.conarhco.rating.DataModule;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Денис
 */
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String password = req.getParameter("password");
            String login =(String)req.getSession().getAttribute("user");
            String oldPassword = req.getParameter("oldPassword");
            if(DataModule.getInstance().isCorrectPassword(login,oldPassword)){
            DataModule.getInstance().changePassword(login, password);
            resp.sendRedirect("user/profile.jsp");
            }else{
                 resp.sendRedirect("user/profile.jsp?errorChangePassword=1");
            }
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}