/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dprokopiuk
 */
@WebServlet(name="SecurityServlet",urlPatterns={"/security"})
public class SecurityServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        PrintWriter writer=resp.getWriter();
        writer.print(getClass().getName());
        writer.flush();
        writer.close();        
    }
    
}
