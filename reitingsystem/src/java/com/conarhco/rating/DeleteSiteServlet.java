/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rolan
 */
public class DeleteSiteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String siteId = req.getParameter("sid");
        String siteName = req.getParameter("siteName");
        try {
            if (siteId != null && !siteId.isEmpty()) {
                DataModule.getInstance().deleteSite(Integer.parseInt(siteId));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        resp.sendRedirect("mysites.jsp?deletedName=" + (siteName == null ? " " : siteName));
    }
}
