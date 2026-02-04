/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

//import java.sql.jdbc.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RSDENIS
 */
public class DataModelOld {
    private static DataModelOld inst=null;

    private DataModelOld() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(DataModelOld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() throws SQLException{
       return DriverManager.getConnection("jdbc:mysql://127.0.0.1/blog", "root", "111111");
    }
    
    public static DataModelOld instance() throws SQLException{
        if(inst==null){
            return new DataModelOld();
        }
        return inst;
    }
    
    public Collection<News> getNews(String order,boolean isDesk) throws SQLException {
        Connection con=this.getConnection();
        try{
            ArrayList<News> arList = new ArrayList<News>();
            if(isDesk) order+=" DESK";
            PreparedStatement st=con.prepareStatement("SELECT * FROM news ORDER BY " +order);
            //st.setString(1, order);
            //System.out.println(st);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
               News n = translateResultSetIntoNews(rs);
                arList.add(n);
            }
            return arList;
        }finally{
            con.close();
        }
    }

    public Collection<News> getNewsByAuthor(String author) throws SQLException {
        Connection con=this.getConnection();
        try{
            ArrayList<News> arList = new ArrayList<News>();
            PreparedStatement st=con.prepareStatement("SELECT * FROM news WHERE author=?");
            st.setString(1, author);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                News n = translateResultSetIntoNews(rs);
                arList.add(n);
            }
            return arList;
        }finally{
            con.close();
        }
    }

    public News getNews(String title, String author) throws SQLException {
        Connection con=this.getConnection();
        try{
            PreparedStatement st=con.prepareStatement("SELECT * FROM news WHERE title=? AND author=?");
            st.setString(1, title);
            st.setString(2, author);
            ResultSet rs=st.executeQuery();
            rs.next();
             News n = translateResultSetIntoNews(rs);
            return n;
        }finally{
            con.close();
        }
    }

    /**
     * TODO: Возвращать ньюс по ИД
     * @param ID
     * @return
     */
    public News getNews(int ID) throws SQLException{
        Connection con=this.getConnection();
        try{
            PreparedStatement st=con.prepareStatement("SELECT * FROM news WHERE id=?");
            st.setInt(1,ID);
          //  st.setString(2, author);
            ResultSet rs=st.executeQuery();
            rs.next();
            News n = translateResultSetIntoNews(rs);
            return n;
        }finally{
            con.close();
        }
    }

    public void addNews(News n) throws SQLException{
        Connection con=getConnection();
        try{
            PreparedStatement ps=con.prepareStatement("INSERT INTO news(title,description,text,author,view,date,decisions) Values(?,?,?,?,?,?,?)");
            ps.setString(1, n.getTitle());
            ps.setString(2, n.getDescription());
            ps.setString(3, n.getText());
            ps.setString(4, n.getAuthor());
            ps.setInt(5, n.getView());
            Date date = new Date(System.currentTimeMillis());
            ps.setDate(6, date);
            ps.setInt(7, n.getDecisions());
            int i=ps.executeUpdate();
            if(i!=1)throw new SQLException("!!!!!!!!!!!!!!!!!!!");
        }finally{
            con.close();
        }
    }

    public void registrationUser(String login,String password) throws SQLException{
        if(login==null||password==null)return;
        Connection con=getConnection();
        try{
            PreparedStatement ps=con.prepareStatement("INSERT INTO user_roles(user_name,role_name) Values(?,?)");
            ps.setString(1, login);
            ps.setString(2, "admin");
            int i=ps.executeUpdate();
            if(i!=1)throw new SQLException("!!!!!!!!!!!!!!!!!!! DATE MODEL-------------");
            ps=con.prepareStatement("INSERT INTO user(user_name,user_pass) Values(?,?)");
            ps.setString(1, login);
            ps.setString(2, password);
            i=ps.executeUpdate();
            if(i!=1)throw new SQLException("!!!!!!!!!!!!!!!!!!! DATE MODEL-------------");
        } catch(Exception ex){

        }
    }


    private boolean toIncludeNews(News n,String par){
         if(n.getAuthor().toLowerCase().indexOf(par)!=-1||
                        //n.getDate().toString().toLowerCase().indexOf(par)!=-1||
                        n.getDescription().toLowerCase().indexOf(par)!=-1||
                        n.getText().toLowerCase().indexOf(par)!=-1||
                        (n.getView()+"").toLowerCase().indexOf(par)!=-1||
                        n.getTitle().toLowerCase().indexOf(par)!=-1){
                    return true;
                }
        return false;
    }

    private News translateResultSetIntoNews(ResultSet rs) throws SQLException{
        int id=rs.getInt("id");
        String title=rs.getString("title");
        String description =rs.getString("description");
        String text=rs.getString("text");
        String author=rs.getString("author");
        int view=rs.getInt("view");
       // Date date=rs.getDate("date");
        int decisions=rs.getInt("decisions");
        return new News(id,title,description,text,author,view,System.currentTimeMillis(),decisions);
    }
   

    public ArrayList<News> getSearchValues(String param) throws SQLException{
            ArrayList<News> resList = new ArrayList<News>();
            Connection con=this.getConnection();
            try{
                PreparedStatement st=con.prepareStatement("SELECT * FROM news");
                ResultSet rs=st.executeQuery();
            while(rs.next()){
                News n=translateResultSetIntoNews(rs);
                if(toIncludeNews(n, param.toLowerCase()))resList.add(n);
            }
            return resList;  
            }
            finally{
                con.close();
            }
                  
    }

    public void updateView(News n) throws SQLException{
        Connection con=getConnection();
        try {
             int update=n.getView()+1;
             PreparedStatement ps=con.prepareStatement("UPDATE news SET view='"+update+"' WHERE id='"+n.getID()+"'");
             int t=ps.executeUpdate();
             if(t!=1)throw new SQLException("!!!!!!!!!!!!!!!!!!! DATE MODEL-------------");
        } finally {
            con.close();
        }
    }

    public void updateDesicions(News n) throws SQLException{
        Connection con=getConnection();
        try {
             int update=n.getDecisions()+1;
             PreparedStatement ps=con.prepareStatement("UPDATE news SET decisions='"+update+"' WHERE id='"+n.getID()+"'");
             int t=ps.executeUpdate();
             if(t!=1)throw new SQLException("!!!!!!!!!!!!!!!!!!! DATE MODEL-------------");
        } finally {
            con.close();
        }
    }
    
}
