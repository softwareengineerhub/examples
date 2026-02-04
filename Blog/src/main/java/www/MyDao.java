package www;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

public class MyDao {


    public String getLogin(){
        try(Connection c = getConnection()){
            ResultSet rs = c.prepareStatement("SELECT * FROM user").executeQuery();
            while(rs.next()){
                return rs.getString("user_name");
            }
            return null;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }


    private Connection getConnection(){
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/myDS2");
            return ds.getConnection();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
