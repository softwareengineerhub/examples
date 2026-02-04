/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Конарх
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        System.out.println("new sesssion = "+sess.isNew());
        sess.invalidate();//Фактически логаут
        resp.sendRedirect("index.jsp");
        /*for (Enumeration<String> en = getServletContext().getAttributeNames(); en.hasMoreElements();){
            System.out.println(en.nextElement());
        }*/
    }
     
}
