/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author dprokopiuk
 */
@WebFilter(
        filterName = "MyFilter",
urlPatterns = {"/annotation","/index.jsp"},
asyncSupported = true,
servletNames={"Q"},
dispatcherTypes={DispatcherType.INCLUDE},
initParams = {
    @WebInitParam(name = "filterParam1", value = "val1"),
    @WebInitParam(name = "filterParam2", value = "val2")}
)

public class MyFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Enumeration<String> en = filterConfig.getInitParameterNames();
        while (en.hasMoreElements()) {
            String s = en.nextElement();
            System.out.println(s + "=" + filterConfig.getInitParameter(s));
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}