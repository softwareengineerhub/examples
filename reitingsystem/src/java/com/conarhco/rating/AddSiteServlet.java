/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Конарх
 */
public class AddSiteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int sid = -1;
        if (req.getParameter("sid")!=null){
            sid = Integer.parseInt(req.getParameter("sid"));
        }
        String regtype = req.getParameter("regtype");
        String[] counters = req.getParameterValues(regtype+"counter");
        if (counters==null) new IllegalArgumentException("Выберите счетчик");
        int[] countersInt = new int[counters.length];
        for (int i=0; i<counters.length; i++){
            countersInt[i] = Integer.parseInt(counters[i]);
        }
        int category = Integer.parseInt(req.getParameter("category"));
        int subcategory = Integer.parseInt(req.getParameter("subcategory"));
        String siteName = req.getParameter("siteName");
        if (siteName.isEmpty()) throw new IllegalArgumentException("Укажите название ресурса");
        String siteLink = req.getParameter("siteLink");
        if (siteLink.isEmpty()) throw new IllegalArgumentException("Укажите адрес ресурса");
        String siteDesc = req.getParameter("siteDesc");
        if (siteDesc.isEmpty()) throw new IllegalArgumentException("Укажите описание ресурса");
        String user = null;
        try {
            if (sid < 0) {
                user = (String) req.getSession().getAttribute("user");
                //Добавление сайта
                DataModule.getInstance().addSite(user, siteName, siteLink, siteDesc, regtype, countersInt, category, subcategory);
            } else {
                //Обновление данных

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            System.out.println("user="+user+" sid="+sid+" regtype="+regtype+" counters="+Arrays.toString(counters)+" cat="+category+
                           " subCat="+subcategory+" name="+siteName+" link="+siteLink+" desc="+siteDesc);
        }
        

        resp.sendRedirect("addsite.jsp");
    }

}
