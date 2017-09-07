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
public class ChangeProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        try {
            System.out.println("-----------------------------------");
             String login =(String)req.getSession().getAttribute("user");
            String country = req.getParameter("country");
            String city = req.getParameter("cityToStoreInDB");
            String street = req.getParameter("street");
            String house = req.getParameter("house");
            String phone = req.getParameter("phone");
            String work_phone = req.getParameter("work_phone");
            String mobile_phone = req.getParameter("mobile_phone");
            String email = req.getParameter("email");
            System.out.println(city);
            DataModule.getInstance().changeProfile(login, country, city, street, house, phone, work_phone, mobile_phone, email);
            resp.sendRedirect("profile.jsp");
        } catch (Exception ex) {
            getServletContext().log("ERROR",ex);
        }


    }

}