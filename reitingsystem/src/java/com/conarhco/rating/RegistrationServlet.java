/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dprokopiuk
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login.isEmpty()) throw new IllegalArgumentException("no login");
        String password = req.getParameter("password");
        if (password.isEmpty()) throw new IllegalArgumentException("no password");
        String email = req.getParameter("email");
        if (email.isEmpty()) throw new IllegalArgumentException("no email");
        try {
            boolean b = DataModule.getInstance().addUser(login, password, email);
            if (b) {
                req.getSession().setAttribute("user", login);
                resp.sendRedirect("index.jsp");
            } else {
                resp.sendRedirect("registration.jsp?errorRegister=1");
            }
        } catch (Exception ex) {
            getServletContext().log("ERROR", ex);
        }
    }
}
