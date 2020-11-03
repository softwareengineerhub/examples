package com.app.web;

import com.app.interceptor.MyStatlessBean;
import com.app.server.MySimpleBean3;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Future;

@WebServlet(name = "myServletInterceptor", urlPatterns = {"/myServletInterceptor"})
public class MyServletInterceptor extends HttpServlet {

    @Inject
    private MyStatlessBean myStatlessBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int res = myStatlessBean.add(1,2);
        resp.getWriter().println(res);
    }
}
