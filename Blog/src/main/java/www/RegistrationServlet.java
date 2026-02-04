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
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = req.getParameter("user_login");
            String pass = req.getParameter("user_password");
            DataModel.instance().registrationUser(login, pass);
            resp.sendRedirect("addNews.jsp");
        } catch (SQLException ex) {
            throw new ServletException();
        }
    }

}