/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

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
 * @author Конарх
 */
public class VoteServlet extends HttpServlet {

    //@TODO: denis: БАГ:  если сделать ссылку /vote?cid=1&sid2 и счётчик 1 не привязан к сайту 2 счётчик 1 всеравно увеличится
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cid = Integer.parseInt(req.getParameter("cid"));
        try {
            DataModule.getInstance().incCounter(cid, new java.sql.Date(System.currentTimeMillis()));
            //System.out.println("counter "+cid+" increased");
            resp.sendRedirect("sites.jsp?cid=" + cid + "&sid=" + req.getParameter("sid"));
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

}
