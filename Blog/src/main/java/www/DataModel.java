/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www;

//import java.sql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author RSDENIS
 */
public class DataModel {
    private static DataModel inst=null;

    private DataModel() {

    }

    public static DataModel instance() throws SQLException{
        if(inst==null){
            return new DataModel();
        }
        return inst;
    }
    
    public Collection<News> getNews(String order,boolean isDesk) throws SQLException {
        return initData(10);
    }

    private List<News> initData(int n){
        List<News> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            list.add(new News(i, "Title" + i, "Description" + i, "Text" + i, "Author" + i, i, System.currentTimeMillis(), i));
        }
        return list;
    }

    public Collection<News> getNewsByAuthor(String author) throws SQLException {
        return initData(10);
    }

    public News getNews(String title, String author) throws SQLException {
        return initData(10).get(0);
    }

    /**
     * TODO: Возвращать ньюс по ИД
     * @param ID
     * @return
     */
    public News getNews(int ID) throws SQLException{
        return initData(10).get(0);
    }

    public void addNews(News n) throws SQLException{

    }

    public void registrationUser(String login,String password) throws SQLException{

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
            return resList;
                  
    }

    public void updateView(News n) throws SQLException{

    }

    public void updateDesicions(News n) throws SQLException{

    }
    
}
