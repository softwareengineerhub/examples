/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.my.jetty.app07.server.with.api.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author prokopiukd
 */
public class MyServletFilter implements Filter {

    private FilterConfig filterConfig;
    // private Application application;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.filterConfig = fc;
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        System.out.println("@Before.Filter!!!");
        Enumeration<String> names = filterConfig.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println(name);
        }
        fc.doFilter(sr, sr1);
        System.out.println("@After.Filter!!!");
    }

    @Override
    public void destroy() {

    }

}
