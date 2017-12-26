/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.registration;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author prokopiukd
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/web/login/register"})
public class RegistrationServlet extends HttpServlet {

    @Resource(name = "education")
    private DataSource ds;
    private RegistrationDAO registrationDAO;

    @Override
    public void init() {
        registrationDAO = new RegistrationDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("NAME");
        String password = req.getParameter("PASSWORD");
        registrationDAO.createUser(name, password, ds);
        resp.sendRedirect("../../web/user/user-page.xhtml");
    }

}
