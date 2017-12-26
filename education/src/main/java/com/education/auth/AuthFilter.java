/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.auth;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author prokopiukd
 */
public class AuthFilter implements Filter {

    @Resource(name = "education")
    private DataSource ds;
    private FilterConfig filterConfig;
    private LoginDAO loginDAO;
    private static final String USER_ATTRIBUTE = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        loginDAO = new LoginDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (alreadyPassedAuth(req, resp)) {
            chain.doFilter(request, response);
           
        } else if (login(req, resp)) {
            chain.doFilter(request, response);
        } else if (req.getParameter("NAME")!=null) {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/web/login/error_login.html");
        } else {
            HttpSession session = req.getSession();
            if (session == null || session.getAttribute(USER_ATTRIBUTE) == null) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/web/login/login.html");
            }
        }
    }

    private boolean alreadyPassedAuth(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (session != null) {
            if (session.getAttribute(USER_ATTRIBUTE) != null) {
                return true;
            }
        }
        return false;
    }

    private boolean login(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("NAME");
        String password = req.getParameter("PASSWORD");
        boolean succes = loginDAO.check(name, password, ds);
        if (succes) {
            HttpSession session = req.getSession(true);
            session.setAttribute(USER_ATTRIBUTE, name);
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }

}
