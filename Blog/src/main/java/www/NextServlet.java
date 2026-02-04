/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RSDENIS
 */
public class NextServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //DataModel.instance().incremIndex();
            resp.getWriter().print("iuui");
            resp.sendRedirect("all.jsp");
        } catch (Exception ex) {
            throw new ServletException("wrong!!!");
        }
    }

}