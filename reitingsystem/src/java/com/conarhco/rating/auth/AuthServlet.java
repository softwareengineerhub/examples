/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating.auth;

import com.conarhco.rating.DataModule;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 777
 */
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        try {
            boolean auth = DataModule.getInstance().authorize(login, pass);
            if (auth) {
                req.getSession(true).setAttribute("user", login);
                ((HttpServletResponse)resp).sendRedirect("index.jsp");
            }else{
                resp.sendRedirect("errorAutherization.jsp");
            }
        } catch (Exception ex) {
           getServletContext().log("ERROR",ex);
           resp.sendRedirect("errorAutherization.jsp");
           ex.printStackTrace();
        }

    }
   
}