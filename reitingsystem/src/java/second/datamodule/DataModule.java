/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package second.datamodule;

import com.conarhco.rating.UserProfile;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 *
 * @author Денис
 */
public class DataModule {

    private DataSource db;
    private static DataModule instance;

    public static class LifeListener implements ServletContextListener {

        @Resource(name = "jdbc/rating")
        private DataSource db;

        public void contextInitialized(ServletContextEvent sce) {
            try {
                DataModule.getInstance().db = db;
                sce.getServletContext().setAttribute("db", DataModule.getInstance());
            } catch (Exception ex) {
                sce.getServletContext().log("NEH", ex);
            }
        }

        public void contextDestroyed(ServletContextEvent sce) {
        }
    }

    private DataModule() {
    }

    public List<Site> getUserSites(String user) throws SQLException {
        Connection c = null;
        List<Site> list = new ArrayList<Site>();
        try {
            c = getConnection();
            String sql = "SELECT site.name as siteName,descr,screen,link,added,free,street,house,homeTel,workTel,mobiTel,site.email as siteMail,"
                    + " cities.city,"
                    + " countries.country,"
                    + " subcategory.name as subcategoryName,"
                    + " category.name as categoryName,"
                    + " registrationType.type"
                    + " FROM site"
                    + " JOIN cities ON (cities.id=site.city)"
                    + " JOIN countries ON (cities.country=countries.id)"
                    + " JOIN subcategory ON (subcategory.id=site.subcategory)"
                    + " JOIN category ON (subcategory.category=category.id)"
                    + " JOIN registrationType ON (registrationType.id=site.registration)"
                    + " JOIN users ON (site.usr=(SELECT id FROM users WHERE users.name=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("siteName");
                String descr = rs.getString("descr");
                String screen = rs.getString("screen");
                String link = rs.getString("link");
                Date added = rs.getDate("added");
                Boolean free = rs.getBoolean("free");
                String registration = rs.getString("type");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String subcategory = rs.getString("subcategoryName");
                String category = rs.getString("categoryName");
                String street = rs.getString("street");
                String house = rs.getString("house");
                String homeTel = rs.getString("homeTel");
                String workTel = rs.getString("workTel");
                String mobiTel = rs.getString("mobiTel");
                String email = rs.getString("siteMail");
                list.add(new Site(user, name, descr, screen, link, added, free, registration, city, country, subcategory, category, street, house, homeTel, workTel, mobiTel, email));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public Site getSiteByName(String siteName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String sql = "SELECT descr,screen,link,added,free,street,house,homeTel,workTel,mobiTel,site.email as siteMail,"
                    + " cities.city,"
                    + " countries.country,"
                    + " subcategory.name as subcategoryName,"
                    + " category.name as categoryName,"
                    + " registrationType.type,"
                    + " users.name as user"
                    + " FROM site"
                    + " JOIN cities ON (cities.id=site.city)"
                    + " JOIN countries ON (cities.country=countries.id)"
                    + " JOIN subcategory ON (subcategory.id=site.subcategory)"
                    + " JOIN category ON (subcategory.category=category.id)"
                    + " JOIN registrationType ON (registrationType.id=site.registration)"
                    + " JOIN users ON (site.usr=site.id)"
                    + " WHERE site.name=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String user = rs.getString("user");
            String descr = rs.getString("descr");
            String screen = rs.getString("screen");
            String link = rs.getString("link");
            Date added = rs.getDate("added");
            Boolean free = rs.getBoolean("free");
            String registration = rs.getString("type");
            String city = rs.getString("city");
            String country = rs.getString("country");
            String subcategory = rs.getString("subcategoryName");
            String category = rs.getString("categoryName");
            String street = rs.getString("street");
            String house = rs.getString("house");
            String homeTel = rs.getString("homeTel");
            String workTel = rs.getString("workTel");
            String mobiTel = rs.getString("mobiTel");
            String email = rs.getString("siteMail");
            return new Site(user, siteName, descr, screen, link, added, free, registration, city, country, subcategory, category, street, house, homeTel, workTel, mobiTel, email);
        } finally {
            closeConnection(c);
        }
    }

    public List<String> getSitesNamesByRegistrationType(String registrationType) throws SQLException {
        Connection c = null;
        try {
            List<String> list = new ArrayList<String>();
            c = getConnection();
            String sql = "SELECT name FROM site WHERE (registration=(SELECT id FROM registrationType WHERE type=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, registrationType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<String> getSitesNamesByRegistrationType(int registrationId) throws SQLException {
        Connection c = null;
        try {
            List<String> list = new ArrayList<String>();
            c = getConnection();
            String sql = "SELECT name FROM site WHERE (registration=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, registrationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<String> getSitesNamesByKeyWords(int keyWordId) throws SQLException {
        Connection c = null;
        try {
            List<String> list = new ArrayList<String>();
            c = getConnection();
            String sql = "SELECT name FROM site JOIN keywords ON (keywords.site=keywords.id AND keywords.id=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, keyWordId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<String> getSitesNamesForCities(String city) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName FROM site JOIN cities ON (cities.id=site.city AND cities.id=(SELECT id FROM cities WHERE city=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForCities(int cityId) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName FROM site JOIN cities ON (cities.id=site.city AND cities.id=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, cityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForSubcategory(String subCategory) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName FROM site JOIN subcategory ON (subcategory.id=site.subcategory AND subcategory.id=(SELECT id FROM subcategory WHERE name=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, subCategory);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForSubcategory(int cityId) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName FROM site JOIN subcategory ON (subcategory.id=site.city AND subcategory.id=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, cityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForCategory(int categoryId) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName"
                    + " FROM site"
                    + " JOIN subcategory ON (subcategory.id=site.subcategory)"
                    + " JOIN category ON (category.id=subcategory.category AND category.id=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForCategory(String categoryName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName"
                    + " FROM site"
                    + " JOIN subcategory ON (subcategory.id=site.subcategory)"
                    + " JOIN category ON (category.id=subcategory.category AND category.id=(SELECT id FROM category WHERE name=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForCountry(int countryId) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName"
                    + " FROM site"
                    + " JOIN cities ON (cities.id=site.city)"
                    + " JOIN countries ON (countries.id=cities.country AND countries.id=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesForCountry(String countryName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            List<String> list = new ArrayList<String>();
            String sql = "SELECT site.name as siteName"
                    + " FROM site"
                    + " JOIN cities ON (cities.id=site.city)"
                    + " JOIN countries ON (countries.id=cities.country AND countries.id=(SELECT id FROM countries WHERE country=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, countryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("siteName"));
            }
            return list;
        } finally {
            c.close();
        }
    }

    public List<String> getSitesNamesByKeyWords(String keyWord) throws SQLException {
        Connection c = null;
        try {
            List<String> list = new ArrayList<String>();
            c = getConnection();
            String sql = "SELECT name FROM site JOIN keywords ON (keywords.site=keywords.id AND keywords.id=(SELECT id FROM keywords WHERE keyword=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, keyWord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public Map<String, List<String>> getCategoryStructure() throws SQLException {
        Connection c = null;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        try {
            c = getConnection();
            String sql = "SELECT subcategory.name as subcat,category.name as cat FROM subcategory JOIN category ON (category.id=subcategory.category) ORDER BY cat";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("cat");
                List<String> list = map.get(category) == null ? new ArrayList<String>() : map.get(category);
                list.add(rs.getString("subcat"));
                map.put(category, list);
            }
            return map;
        } finally {
            closeConnection(c);
        }
    }

    public Map<String, List<String>> getCountriesStructure(String login) throws SQLException {
        Connection c = null;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        try {
            c = getConnection();
            String sql = "SELECT city,countries.country FROM cities JOIN countries ON (countries.id=cities.country) ORDER BY country";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String country = rs.getString("country");
                List<String> list = map.get(country) == null ? new ArrayList<String>() : map.get(country);
                list.add(rs.getString("city"));
                map.put(country, list);
            }
            return map;
        } finally {
            closeConnection(c);
        }
    }

    public List<VisitorNotes> getVisitorNotesForSite(int sid) throws SQLException {
        Connection c = null;
        List<VisitorNotes> list = new ArrayList<VisitorNotes>();
        try {
            c = getConnection();
            String sql = "SELECT date,time,note,name FROM visitornotes WHERE site=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new VisitorNotes(rs.getString("name"), rs.getString("note"), rs.getDate("date"), rs.getTime("time")));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<VisitorNotes> getVisitorNotesForSite(String siteName) throws SQLException {
        Connection c = null;
        List<VisitorNotes> list = new ArrayList<VisitorNotes>();
        try {
            c = getConnection();
            String sql = "SELECT date,time,note,visitornotes.name as visitorName FROM visitornotes WHERE (site=(SELECT id FROM site WHERE name=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, siteName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new VisitorNotes(rs.getString("name"), rs.getString("note"), rs.getDate("date"), rs.getTime("time")));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<String> getKeyWordsForSite(int sid) throws SQLException {
        Connection c = null;
        List<String> list = new ArrayList<String>();
        try {
            c = getConnection();
            String sql = "SELECT keyword FROM keywords WHERE site=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("keyword"));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<String> getKeyWordsForSite(String siteName) throws SQLException {
        Connection c = null;
        List<String> list = new ArrayList<String>();
        try {
            c = getConnection();
            String sql = "SELECT keyword FROM keywords WHERE (site=(SELECT id FROM site WHERE name=?))";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, siteName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("keyword"));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<UserNews> getUserNewsForSite(int sid) throws SQLException {
        Connection c = null;
        List<UserNews> list = new ArrayList<UserNews>();
        try {
            c = getConnection();
            String sql = "SELECT date,time,news,users.name FROM usernews JOIN users ON (users.id=usernews.usr)  WHERE site=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserNews(rs.getDate("date"), rs.getTime("time"), rs.getString("news"), rs.getString("name")));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public List<UserNews> getUserNewsForSite(String siteName) throws SQLException {
        Connection c = null;
        List<UserNews> list = new ArrayList<UserNews>();
        try {
            c = getConnection();
            String sql = "SELECT date,time,news,users.name as user FROM usernews JOIN users ON (users.id=usernews.usr)  WHERE site=(SELECT id FROM site WHERE name=?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, siteName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserNews(rs.getDate("date"), rs.getTime("time"), rs.getString("news"), rs.getString("user")));
            }
            return list;
        } finally {
            closeConnection(c);
        }
    }

    public Integer getUserId(String login) throws SQLException {
        Connection c = null;
        Integer id = null;
        try {
            c = getConnection();
            String query = "SELECT id FROM users WHERE name=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } finally {
            closeConnection(c);
        }
        return id;
    }

    public Map<String, String> getFAQContent() throws SQLException {
        Connection c = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            c = getConnection();
            String sql = "SELECT question,answer FROM faq";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("question"), rs.getString("answer"));
            }
            return map;
        } finally {
            closeConnection(c);
        }
    }

    public Map<String, String> getTextsContent() throws SQLException {
        Connection c = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            c = getConnection();
            String sql = "SELECT name,content FROM texts";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("name"), rs.getString("content"));
            }
            return map;
        } finally {
            closeConnection(c);
        }
    }

    public boolean authorize(String login, String pass) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT ID FROM users WHERE name=? AND password=MD5(?)");
            st.setString(1, login);
            st.setString(2, pass);
            ResultSet res = st.executeQuery();
            return res.next();
        } finally {
            closeConnection(conn);
        }
    }

    public void createUser(String login, String pass, String email) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO users(name,password,email,ban) VALUES(?,MD5(?),?,?)");
            st.setString(1, login);
            st.setString(2, pass);
            st.setString(3, email);
            st.setBoolean(4, false);
            st.executeUpdate();
        } finally {
            closeConnection(conn);
        }
    }

    public void setBanForUser(String user, boolean ban) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE users SET ban=? WHERE name=?");
            st.setBoolean(1, ban);
            st.setString(2, user);
            st.executeUpdate();
        } finally {
            closeConnection(conn);
        }
    }

    public static DataModule getInstance() {
        if (instance == null) {
            instance = new DataModule();
        }
        return instance;
    }

    private void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    private Connection getConnection() throws SQLException {
        return db.getConnection();
    }
}
