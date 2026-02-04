/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

import java.io.IOException;
//import java.io.PrintWriter;
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
public class AddNewsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
resp.setContentType("text/plain; charset=utf-8");
        String title=req.getParameter("title");
        String description=req.getParameter("description");
        String text=req.getParameter("text");
        //String user=req.getRemoteUser();
        String user="denis";
        try {
            //DataModel.instance().addNews(title, description, text, user);
            resp.sendRedirect("all.jsp");
        } catch (Exception ex) {
             throw new ServletException(ex);
        }
    }


}