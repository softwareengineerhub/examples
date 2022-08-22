/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.app.MonitoringProcessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Администратор
 */
public class Admin extends HttpServlet {

    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String operation = req.getParameter("operation");
            InitialContext ctx = new InitialContext();
            MonitoringProcessor mp=(MonitoringProcessor) ctx.lookup("centerMonitoring#com.app.MonitoringProcessor");
            mp.process(operation);
        } catch (NamingException ex) {
           throw new RuntimeException(ex);
        }
    }

   
}
