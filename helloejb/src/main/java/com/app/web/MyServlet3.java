package com.app.web;

import com.app.server.MySimpleBean2;
import com.app.server.MySimpleBean3;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Future;

@WebServlet(name = "myServlet3", urlPatterns = {"/myServlet3"})
public class MyServlet3 extends HttpServlet {

    @Inject
    private MySimpleBean3 mySimpleBean3;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("Servlet-START");
            Future future = mySimpleBean3.doMethod2();
            Object res = future.get();
            resp.getWriter().println(res);
            System.out.println("Servlet-FINISH");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
