package com.app.web;

import com.app.server.MySimpleBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="myServlet", urlPatterns = {"/myServlet"})
public class MyServlet extends HttpServlet {
    @Inject
    private MySimpleBean mySimpleBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mySimpleBean.doAction();
        String res = mySimpleBean.doAction2(1);
        resp.getWriter().println(res);
    }
}
