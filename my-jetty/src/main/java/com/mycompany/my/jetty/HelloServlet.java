/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author prokopiukd
 */
public class HelloServlet extends HttpServlet {

    private String greeting = "Hello World";

    public HelloServlet() {
    }

    public HelloServlet(String greeting) {
        this.greeting = greeting;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>" + greeting + "</h1>");
        writer.println("session=" + req.getSession(true).getId());
        writer.println("<br/>");
        writer.println("hashCode=" + hashCode());
    }

}
