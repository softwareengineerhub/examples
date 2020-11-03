package com.app.web;

import com.app.server.MySimpleBean;
import com.app.server.MySimpleBean2;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="myServlet2", urlPatterns = {"/myServlet2"})
public class MyServlet2 extends HttpServlet {

    @Inject
    private MySimpleBean2 mySimpleBean2;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mySimpleBean2.doOperation();
    }
}
