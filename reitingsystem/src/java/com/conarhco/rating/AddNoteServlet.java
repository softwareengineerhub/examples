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
public class AddNoteServlet extends HttpServlet {
    private int max;

    @Override
    public void init(){
        max = Integer.parseInt(getInitParameter("max"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int sid = Integer.parseInt(req.getParameter("sid"));
        String name = req.getParameter("name");
        if (name == null || name.isEmpty()) {
            name = (String) req.getSession().getAttribute("user");
        }
        String note = req.getParameter("note");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Введите имя");
        if (note.isEmpty()) throw new IllegalArgumentException("Введите заметку");
        if (note.length()>max) throw new IllegalArgumentException("Заметка слишком длинная");
        try {
            DataModule.getInstance().addVisitorNote(sid, name, note);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        resp.sendRedirect("sites.jsp?sid="+sid);
    }

}
