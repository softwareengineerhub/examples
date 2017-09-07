/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dprokopiuk
 */
@WebServlet(
        description = "Description",
        name = "AnnotatinServlet",
        urlPatterns = {"/annotation"},
        initParams = {
            @WebInitParam(name = "param1", value = "val1")
            ,
    @WebInitParam(name = "param2", value = "val2")},
        loadOnStartup = 1,
        asyncSupported = true)
public class AnnotatinServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        getServletContext().getNamedDispatcher("Q").forward(req, resp);

        /*Enumeration en = getServletContext().getInitParameterNames();
        ServletOutputStream out = resp.getOutputStream();
        while (en.hasMoreElements()) {
            out.println(en.nextElement().toString() + "<br>");
        }
        Enumeration<String> en1 = getInitParameterNames();
        out.println("----------------------------------" + "<br>");
        while (en1.hasMoreElements()) {
            String s = en1.nextElement();
            out.println(s + "=" + getInitParameter(s) + "<br>");
        }
        out.flush();
        out.close();*/
    }
}
