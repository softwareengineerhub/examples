/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

import com.conarhco.rating.Catalog.CatalogType;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
////import java.util.TreeSet;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * TODO: denis: все пароли в пользователя в МД5. Проверить все запросы.
 * TODO: +conarh: обновить базу: пользователи (бан, права-через комбо (admin, user))
 * TODO: +conarh: обновить базу: факю
 * TODO: +conarh: обновить базу: правила и условия
 * TODO: +conarh: обновить базу: переделать связи профиля пользователя, профиля сайта, системы новостей, добавить платный-бесплатный сайт. учесть срок истечения времени отображения сайтов (триал бесплатный + платный 365) при поиске и каталоге.
 * TODO: denis: полностью переписать все запросы в БД на основании изменившейся структуры. изменить бины и фабрики соответсвенно. Действуй постепенно, поочередно в соответсвии с функциональностью. Изменив бин и фактори, пиши тудуху и коммить. я буду расписывать их на себя и рола и будем переделывать страницы.
 *
 * @author Конарх
 */
public class DataModule {

    // TODO: +conarh: в таблице keywords убрать уникальность имени, либо сделать пару имя <=> id сайта
    private DataSource db;
    private static DataModule instance;
    private List<String> cashList;
    private Comparator<SiteManager> sitesComparator = new SitesComparator();

    public class SitesComparator implements Comparator<SiteManager> {

        public int compare(SiteManager manager1, SiteManager manager2) {
            int amount1 = manager1.getCountersAmount();
            int amount2 = manager2.getCountersAmount();
            if (amount1 == amount2) {
                return manager2.getSiteProfile().getId() - manager1.getSiteProfile().getId();
            }
            return amount2 - amount1;
        }
    };

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

    /*private DataModule(){
    instance=this;
    }*/
    private DataModule() {
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        cashList = new ArrayList<String>();
    }

    public UserProfile getUserProfileByLogin(String login) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT users.street,users.house,users.homeTel,users.workTel,users.mobiTel,users.email,users.img,users.password,countries.country,cities.city FROM users JOIN countries ON (users.country=countries.id) JOIN cities ON (users.cities=cities.id) WHERE name=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            UserProfile item = null;
            if (rs.next()) {
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                String house = rs.getString("house");
                String homeTel = rs.getString("homeTel");
                String workTel = rs.getString("workTel");
                String mobTel = rs.getString("mobiTel");
                String email = rs.getString("email");
                String password = rs.getString("password");
                item = new UserProfile(login, country, city, street, house, homeTel, workTel, mobTel, email, password);
            }
            return item;
        } finally {
            if (c != null) {
                c.close();
            }
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
            if (c != null) {
                c.close();
            }
        }
        return id;
    }

    public UserProfile getUserProfileBySite(int siteID) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT users.name, users.street, users.password, users.house,users.homeTel,users.workTel,users.mobiTel,users.email,users.img,countries.country,cities.city FROM users JOIN countries ON (users.country=countries.id) JOIN cities ON (users.cities=cities.id) WHERE users.id=(SELECT user FROM usersites WHERE site=?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, siteID);
            ResultSet rs = ps.executeQuery();
            UserProfile item = null;
            if (rs.next()) {
                String login = rs.getString("name");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                String house = rs.getString("house");
                String homeTel = rs.getString("homeTel");
                String workTel = rs.getString("workTel");
                String mobTel = rs.getString("mobiTel");
                String email = rs.getString("email");
                String password = rs.getString("password");
                item = new UserProfile(login, country, city, street, house, homeTel, workTel, mobTel, email, password);
            }
            return item;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public Collection<SiteProfile> getSites(String login) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT site.id,site.screen,site.name,site.descr,site.link FROM site JOIN userSites ON (userSites.site=site.ID and userSites.user=(SELECT ID FROM users WHERE name=?))";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            List<SiteProfile> list = new ArrayList<SiteProfile>();
            while (rs.next()) {
                SiteProfile item = getSiteProfile(rs);
                list.add(item);
            }
            return list;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public SiteProfile getSiteProfile(String siteName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();

            String query = "SELECT site.id,site.screen,site.name,site.descr,site.link FROM site WHERE site.name=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, siteName);
            ResultSet rs = ps.executeQuery();
            SiteProfile item = null;
            if (rs.next()) {
                item = getSiteProfile(rs);
            }
            return item;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    //
    public SiteProfile getSiteProfileByID(int id) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT site.id, site.name,site.screen,site.name,site.descr, "
                    + "site.link,registrationType.type,subcategory.name as subcategoryName,category.name as categoryName FROM site "
                    + "JOIN subcategory ON (subcategory.id=site.subcategory) "
                    + "JOIN category ON (subcategory.category=category.id) "
                    + "JOIN registrationType ON (site.registration=registrationType.id) "
                    + "WHERE site.id=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            SiteProfile item = null;
            if (rs.next()) {
                item = getSiteProfile(rs);
            }
            query = "SELECT counterType FROM counter WHERE site=?";
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Integer> list = new ArrayList<Integer>();
            while (rs.next()) {
                list.add(rs.getInt("counterType"));
            }
            int[] counts = new int[list.size()];
            item.setCounters(counts);
            return item;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private SiteProfile getSiteProfile(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String siteName = rs.getString("name");
        String screenshot = rs.getString("screen");
        String desc = rs.getString("descr");
        String link = rs.getString("link");
        String category = rs.getString("categoryName");
        String subcategory = rs.getString("subcategoryName");
        String registration = rs.getString("type");
        SiteProfile item = new SiteProfile(id, screenshot, siteName, desc, link, category, subcategory, registration, null);
        return item;
    }

    /**
     * Returns list of sites for parent category
     * @param parent parent category id
     * @param catalogType type of catalog
     * @return list of sites for parent category
     * @throws SQLException if db errors occurs
     */
    public List<SiteManager> getSites(Integer parent, CatalogType catalogType) throws SQLException {
        Connection conn = null;
        List<SiteManager> sites = new ArrayList<SiteManager>();
        try {
            conn = getConnection();
            // select first category id
            if (parent == null) {
                PreparedStatement st1 = conn.prepareStatement("select id, name from subcategory order by name");
                ResultSet res = st1.executeQuery();
                if (res.next()) {
                    parent = res.getInt(1);
                }
            }

            PreparedStatement st = conn.prepareStatement("SELECT id FROM site where subcategory = ? " + (catalogType != null && catalogType != CatalogType.CATALOG_ALL ? " and registration = " + catalogType.getType() : ""));
            // if categories table is not empty
            if (parent != null) {
                st.setInt(1, parent);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    SiteManager manager = new SiteManager();
                    manager.setSiteId(rs.getInt("id"));
                    sites.add(manager);
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        Collections.sort(sites, sitesComparator);
        return sites;
    }

    /**
     * Returns list of sites related to user
     * @param userId user id
     * @return list of sites related to user
     * @throws SQLException if db errors occurs
     */
    public List<SiteManager> getUserSites(Integer userId) throws SQLException {
        Connection conn = null;
        List<SiteManager> sites = new ArrayList<SiteManager>();
        try {
            conn = getConnection();

            PreparedStatement st = conn.prepareStatement("SELECT site.* FROM site join usersites on site.id = usersites.site where user = ?");
            // if categories table is not empty
            if (userId != null) {
                st.setInt(1, userId);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    SiteManager manager = new SiteManager();
                    manager.setSiteId(rs.getInt("id"));
                    sites.add(manager);
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        Collections.sort(sites, sitesComparator);
        return sites;
    }

    /**
     * Returns set of sites depends on search text
     * @param searchText text to search
     * @param descSearch search in keywords
     * @param contentSearch search in site name and description
     * @return set of sites depends on search text
     * @throws SQLException if db errors occurs
     */
    public List<SiteManager> getSites(String searchText, boolean descSearch, boolean contentSearch, CatalogType type) throws SQLException {
        Connection conn = null;
        List<SiteManager> sites = new ArrayList<SiteManager>();
        try {
            conn = getConnection();
            boolean searchAll = !contentSearch && !descSearch;
            String[] words = searchText.split(" ");
            Set<String> wordsSet = new HashSet<String>(new ArrayList<String>(Arrays.asList(words)));
            wordsSet.remove("");
            String textQueryPart = "";
            String joinQueryPart = "";
            for (Iterator<String> it = wordsSet.iterator(); it.hasNext();) {
                String word = it.next();
                if (contentSearch || searchAll) {
                    textQueryPart += "lower(site.name) like lower('%" + word + "%') or lower(site.descr) like lower('%" + word + "%')" + (it.hasNext() ? " or " : "");
                }
                if (descSearch || searchAll) {
                    joinQueryPart += "lower(keywords.keyword) like lower('%" + word + "%')" + (it.hasNext() ? " or " : "");
                }
            }

            boolean useType = type != null && type != CatalogType.CATALOG_ALL;
            String query = "SELECT site.id FROM site";
            // construct final search query
            if (descSearch || searchAll) {
                if (wordsSet.isEmpty()) {
                    query+= useType ? " where registration=" + type.toString() : "";
                } else {
                    query += " join keywords on site.id = keywords.site where (" + joinQueryPart;
                    query += (textQueryPart.length() > 0 ? " or (" + textQueryPart + "))" : ")") + (useType ? " and registration=" + type.toString() : "");
                }
            } else {
                query += (textQueryPart.length() > 0 || useType ? " where " : "") + (textQueryPart.length() > 0 ? "(" + textQueryPart + ")" : "") + (textQueryPart.length() > 0 && useType ? " and " : "") + (useType ? "registration=" + type.toString() : "");
            }
            System.out.println("Search query: " + query);
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet sitesRes = st.executeQuery();
            while (sitesRes.next()) {
                SiteManager manager = new SiteManager();
                manager.setSiteId(sitesRes.getInt("id"));
                sites.add(manager);
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        Collections.sort(sites, sitesComparator);
        return sites;
    }

    public TreeMap<Date, Integer> getCounter(SiteProfile profile, String counterName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            TreeMap<Date, Integer> map = new TreeMap<Date, Integer>();
            int id = profile.getId();
            String query = "SELECT date, counts FROM counterData JOIN counter ON (counter.site=? AND counter.id=counterData.counter AND counter.counterType=(SELECT id FROM counterType WHERE name=?)) ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, counterName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("date");
                Integer counts = rs.getInt("counts");
                map.put(date, map.containsKey(date) ? map.get(date) + counts : counts);
            }
            return map;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public TreeMap<Date, Integer> getCounter(int sid, String counterName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            TreeMap<Date, Integer> map = new TreeMap<Date, Integer>();
            String query = "SELECT date, counts FROM counterData JOIN counter ON (counter.site=? AND counter.id=counterData.counter AND counter.counterType=(SELECT id FROM counterType WHERE name=?)) ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, sid);
            ps.setString(2, counterName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("date");
                Integer counts = rs.getInt("counts");
                map.put(date, map.containsKey(date) ? map.get(date) + counts : counts);
            }
            return map;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /*public int getAmountForCounter(SiteProfile profile, String counterName) throws SQLException {
    Map<Date, Integer> map = getCounter(profile, counterName);
    int count = 0;
    for (Integer i : map.values()){
    count+=i;
    }
    return count;
    }*/
    public int getAmountForCounter(SiteProfile profile, String counterName) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT sum(counts) FROM counterData JOIN counter ON (counter.site=? AND counter.id=counterData.counter AND counter.counterType=(SELECT id FROM counterType WHERE name=?)) ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, profile.getId());
            ps.setString(2, counterName);
            ResultSet rs = ps.executeQuery();
            int sum = 0;
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            rs.close();
            ps.close();
            return sum;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    //SELECT date, counts, counter.id as cid, counter.countertype as ctype, sid.id as sid FROM counterdata, counter, (select site.ID from usersites join site on usersites.site=site.id where site.name="Site1") as sid where counterdata.counter=counter.id and counter.site=sid.id order by (ctype)
    public int getTotalAmountOfCounters(SiteProfile profile) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT sum(counts) FROM counterData JOIN counter ON (counter.id=counterData.counter AND counter.site=?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, profile.getId());
            ResultSet rs = ps.executeQuery();
            int sum = 0;
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }


    public int getTotalAmountOfCounters(int sid) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT sum(counts) FROM counterData JOIN counter ON (counter.id=counterData.counter AND counter.site=?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            int sum = 0;
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public Collection<SiteProfile> getTopSites() throws SQLException {
        List<SiteProfile> allSites = new ArrayList<SiteProfile>();
        Connection con = null;
        try {
            con = getConnection();
            String query = "SELECT * FROM site";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String screenshot = rs.getString("screen");
                String name = rs.getString("name");
                String directLink = rs.getString("link");
                SiteProfile site = new SiteProfile(id, screenshot, name, "", directLink, "", "", "", null);
                site.setTotalAmountOfCounters(getTotalAmountOfCounters(id));
                allSites.add(site);
            }
            Collections.sort(allSites);
            if(allSites.size()>=20){
                return allSites.subList(0, 19);
            }
            if(allSites.size()%2!=0){
               SiteProfile first=allSites.get(0);
               allSites.add(first);
            }
            return allSites;
        } finally {
            if(con!=null){
            con.close();
            }
        }
    }

    public Collection<String> getCounterNamesForSite(SiteProfile profile) throws SQLException {
        if (cashList.size() != 0) {
            return cashList;
        }
        Connection c = null;
        try {
            c = getConnection();

            Collection counterTypes = new ArrayList<String>();
            String query = "SELECT name FROM counterType";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                counterTypes.add(rs.getString("name"));
            }
            cashList.addAll(counterTypes);
            //System.out.println(cashList);
            return counterTypes;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /*
     * Returns total amount for each counter type for the profile
     * @param profile current SiteProfile
     * @return map total amount for every counter type for profile
     */
    public Map<String, Integer> getCountersTotal(SiteProfile profile) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            Map<String, Integer> map = new HashMap<String, Integer>();
            String query = "SELECT id,name FROM counterType join counter on (counterType.id=counter.counterType)  WHERE counter.site=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, profile.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("name"), rs.getInt("id"));
            }
            return map;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public Set<CounterBean> getSiteCounters(Integer siteId) throws SQLException {
        Set<CounterBean> counters = new LinkedHashSet<CounterBean>();
        Connection c = null;
        try {
            c = getConnection();
            PreparedStatement sites = c.prepareStatement("select cid, name, sum(counterdata.counts) as counts from (select counter.id as cid, countertype.name as name from counter join countertype on counter.countertype=countertype.id where site = ?) as ct join counterdata on ct.cid=counterdata.counter group by cid order by name");
            sites.setInt(1, siteId);
            ResultSet sitesRes = sites.executeQuery();
            while (sitesRes.next()) {
                counters.add(new CounterBean(sitesRes.getInt("cid"), sitesRes.getInt("counts"), sitesRes.getString("name")));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return counters;
    }

    public Map<String, Integer> getCounterTypesByRegType(String regType) throws SQLException {
        Connection c = null;
        Map<String, Integer> types = new LinkedHashMap<String, Integer>();
        try {
            c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT countertype.ID, countertype.name FROM countertype where registration=(select registrationtype.id from registrationtype where registrationtype.type=?) ORDER BY countertype.name");
            ps.setString(1, regType);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                types.put(res.getString("name"), res.getInt("ID"));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return types;
    }

    public List<Set<CounterBean>> getSplittedSiteCounters(Integer siteId) throws SQLException {
        List<Set<CounterBean>> counters = new ArrayList<Set<CounterBean>>();
        Connection c = null;
        try {
            c = getConnection();
            PreparedStatement sites = c.prepareStatement("select cid, name, sum(counterdata.counts) as counts from (select counter.id as cid, countertype.name as name from counter join countertype on counter.countertype=countertype.id where site = ?) as ct join counterdata on ct.cid=counterdata.counter group by cid order by name");
            sites.setInt(1, siteId);
            ResultSet sitesRes = sites.executeQuery();
            int part = 1;
            Set<CounterBean> tmp = new LinkedHashSet<CounterBean>();
            for (int index = 0; sitesRes.next(); index++) {
                if (index / 4 + 1 > part) {
                    counters.add(tmp);
                    tmp = new LinkedHashSet<CounterBean>();
                    part++;
                }
                tmp.add(new CounterBean(sitesRes.getInt("cid"), sitesRes.getInt("counts"), sitesRes.getString("name")));
            }
            counters.add(tmp);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return counters;
    }

    public void incCounter(int cid, java.sql.Date date) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            System.out.println("after retreaving connection");
            String query = "UPDATE counterData SET counts=counts+1 WHERE counter=? and date=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, cid);
            ps.setDate(2, date);
            int i = ps.executeUpdate();
            if (i == 0) {
                query = "INSERT counterData(counts, counter, date) VALUES (1, ?, ?)";
                ps = c.prepareStatement(query);
                ps.setInt(1, cid);
                ps.setDate(2, date);
                i = ps.executeUpdate();
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /*
     * Adds new user to db
     * @param login new user
     * @param password password
     * @param email email
     * @return true if new user was added
     */
    public boolean addUser(String login, String password, String mail) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT name FROM users WHERE name=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
            query = "INSERT users(name,password,email) VALUES(?,?,?)";
            ps = c.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, mail);
            ps.executeUpdate();
            return true;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public void addVisitorNote(int sid, String name, String note) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "INSERT INTO visitornotes(date, time, name, note, site) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setTime(2, new java.sql.Time(System.currentTimeMillis()));
            ps.setString(3, name);
            ps.setString(4, note);
            ps.setInt(5, sid);
            ps.executeUpdate();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public Collection<VisitorNote> getVisitorNotes(int sid, int max) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            String query = "SELECT date, name, note FROM visitornotes WHERE site=? ORDER BY date DESC, time DESC LIMIT ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, sid);
            ps.setInt(2, max);
            ResultSet res = ps.executeQuery();
            Collection<VisitorNote> reply = new LinkedHashSet<VisitorNote>();
            while (res.next()) {
                Date d = res.getDate("date");
                String name = res.getString("name");
                String content = res.getString("note");
                VisitorNote note = new VisitorNote(name, content, d);
                reply.add(note);
            }
            return reply;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /*
     * @param login currentUser
     * @param pass currentPassword
     * @return true if there is a user in db with expected login and password
     */
    public boolean authorize(String login, String pass) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT ID FROM users WHERE name=? AND password=?");
            st.setString(1, login);
            st.setString(2, pass);
            ResultSet res = st.executeQuery();
            return res.next();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*
     * Updates password for currentUser
     * @param login currentUser
     * @param password current password
     */
    public void changePassword(String login, String password) throws SQLException {
        Connection conn = getConnection();
        try {
            String query = "UPDATE users SET password=? WHERE name=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, password);
            st.setString(2, login);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*
     * Updates profile information
     * @param login currentUser
     * @param country current country
     * @param city current city
     * @param street current street
     * @param house current house
     * @param phone current phone
     * @param work_phone current work_phone
     * @param current mobile_phone
     * @param email current email
     */
    public void changeProfile(String login, String country, String city, String street, String house, String phone, String work_phone, String mobile_phone, String email) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT id FROM cities WHERE city=?");
            st.setString(1, city);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                st = conn.prepareStatement("INSERT INTO cities (city) VALUES(?)");
                st.setString(1, city);
                st.executeUpdate();
            }
            String query = "UPDATE users SET email=?,"
                    + " country=(SELECT id FROM countries WHERE country=?),"
                    + " cities=(SELECT id FROM cities WHERE city=?),"
                    + " street=?, house=?, homeTel=?, workTel=?, mobiTel=? WHERE name=?";
            st = conn.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, country);
            st.setString(3, city);
            st.setString(4, street);
            st.setString(5, house);
            st.setString(6, phone);
            st.setString(7, work_phone);
            st.setString(8, mobile_phone);
            st.setString(9, login);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * Returns map of categories splitted on parts
     * @return map of categories splitted on parts
     * @throws SQLException if db errors occurs
     */
    public List<Map<Integer, String>> getCategories() throws SQLException {
        List<Map<Integer, String>> categories = new ArrayList<Map<Integer, String>>();
        Map<Integer, String> tmp = getAllCategories();
        // split categories on three parts
        //try {
            int partsCnt = Math.min(tmp.size(), 3);
            int partSize = tmp.size() / partsCnt;
            int mod = tmp.size() % partsCnt;
            List<Integer> data = new ArrayList<Integer>(tmp.keySet());
            for (int index = 0; index < partsCnt; index++, mod--) {
                Map<Integer, String> partMap = new LinkedHashMap<Integer, String>();
                int size = partSize + (mod > 0 ? 1 : 0);
                List<Integer> partList = data.subList(0, size);
                while (partList.size() > 0) {
                    Integer key = partList.remove(0);
                    partMap.put(key, tmp.get(key));
                }
                categories.add(partMap);
            }
        /*} catch (Exception ex) {
            categories.add(tmp);
            ex.printStackTrace();
        }*/
        return categories;
    }

    public Map<Integer, String> getAllCategories() throws SQLException {
        Connection conn = getConnection();
        Map<Integer, String> tmp = new LinkedHashMap<Integer, String>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT id, name FROM category order by name");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tmp.put(rs.getInt("id"), rs.getString("name"));
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return tmp;
    }

    public Map<String, Map<String, Integer>> getAllSubcategories() throws SQLException {
        Connection conn = getConnection();
        Map<String, Map<String, Integer>> subs = new LinkedHashMap<String, Map<String, Integer>>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT category.name as cname, subcategory.name as sname, subcategory.id FROM category join subcategory on category.id=subcategory.category ORDER BY cname, sname");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String cname = rs.getString("cname");
                if (!subs.containsKey(cname)) {
                    subs.put(cname, new LinkedHashMap<String, Integer>());
                }
                Map<String, Integer> sub = subs.get(cname);
                sub.put(rs.getString("sname"), rs.getInt("id"));
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return subs;
    }

    /**
     * Returns map of subcategories for specified category
     * @param parent category id
     * @return map of subcategories for specified category
     * @throws SQLException if db errors occurs
     */
    public Map<Integer, String> getSubCategories(Integer parent) throws SQLException {
        Connection conn = getConnection();
        Map<Integer, String> subCategories = new LinkedHashMap<Integer, String>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT id, name FROM subcategory where category = ? order by name");
            // select first category id
            if (parent == null) {
                PreparedStatement st1 = conn.prepareStatement("select id, name from category order by name");
                ResultSet res = st1.executeQuery();
                if (res.next()) {
                    parent = res.getInt("id");
                }
            }

            // if categories table is not empty
            if (parent != null) {
                st.setInt(1, parent);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    subCategories.put(rs.getInt("id"), rs.getString("name"));
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return subCategories;
    }

    /*
     * Method to check existing of user with password
     * @param login currentUser
     * @param password currentUser's password
     * @return true if there is a user in db with corresponded login and password
     */
    public boolean isCorrectPassword(String login, String password) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            String query = "SELECT id FROM users WHERE name=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

//Получает цифры для статистики---------------------------------------
    public int getCountForDate(int sid, String counterName) throws SQLException {
        Map<Date, Integer> map = getCounter(sid, counterName);
        Set<Date> keys = map.keySet();
        int sum = 0;////Calendar c = Calendar.getInstance();
        Date current = new Date();
        for (Date d : keys) {
            if (d.getDay() == current.getDay()) {
                if (d.getMonth() == current.getMonth()) {
                    if (d.getYear() == current.getYear()) {
                        sum += map.get(d);
                    }
                }
            }
        }
        return sum;
    }

    public Collection<String> getCounterNamesForSite(int sid) throws SQLException {
        Connection c = null;
        try {
            c = getConnection();
            Collection counterTypes = new ArrayList<String>();
            String query = "SELECT name FROM counterType join counter on (counterType.id=counter.counterType)  WHERE counter.site=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                counterTypes.add(rs.getString("name"));
            }
            return counterTypes;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /*
     * Defines all data for statistics page
     * @param sid id of site
     * @param statisticsType period for stastics data
     */
    public StatisticsAmountBean getCountByDay(int sid, StatisticsPeriod statisticsType, String counterName) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            int[] interval = null;
            switch (statisticsType) {
                case DAY:
                    interval = new int[]{0, 1, 2, 30};
                    break;
                case WEEK:
                    interval = new int[]{0, 1, 2, 4};
                    break;
                case MONTH:
                    interval = new int[]{0, 1, 2, 12};
                    break;
            }
            StatisticsAmountBean bean = new StatisticsAmountBean();
            for (int i = 0; i < interval.length; i++) {
                String query = "SELECT "
                        + "sum(counts) as counts FROM counterData JOIN counter ON"
                        + " (counter.site=? AND counter.id=counterData.counter "
                        + "AND counter.counterType=(SELECT id FROM counterType WHERE name=?))"
                        + " WHERE DATE_SUB(CURDATE(),INTERVAL  ? " + statisticsType.name() + ")<=date";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, sid);
                ps.setString(2, counterName);
                ps.setInt(3, interval[i]);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    bean.add(i, rs.getInt("counts"));
                }
            }
            return bean;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /*
     * Defines all data for drawing sefvlet
     * @param sid id of site
     * @param statisticsType period for stastics data
     */
    public Map<Date, Integer> getCountDataToDraw(int sid, StatisticsPeriod statisticsType, String counterName) throws SQLException {
        Connection con = null;
        Map<Date, Integer> map = new TreeMap<Date, Integer>();
        Calendar c = Calendar.getInstance();
        int period = 0;
        try {
            con = getConnection();
            int[] interval = null;
            switch (statisticsType) {
                case DAY:
                    interval = new int[]{0, 1, 2, 30};
                    period = 30;
                    break;
                case WEEK:
                    interval = new int[]{0, 1, 2, 4};
                    period = 4;
                    break;
                case MONTH:
                    interval = new int[]{0, 1, 2, 12};
                    period = 12;
                    break;
            }
            for (int i = 0; i < interval.length; i++) {
                String query = "SELECT "
                        + "counts,date,WEEKOFYEAR(date) as week, MONTH(date) as month FROM counterData JOIN counter ON"
                        + " (counter.site=? AND counter.id=counterData.counter "
                        + "AND counter.counterType=(SELECT id FROM counterType WHERE name=?))"
                        + " WHERE DATE_SUB(CURDATE(),INTERVAL  ? " + statisticsType.name() + ")<=date";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, sid);
                ps.setString(2, counterName);
                ps.setInt(3, interval[i]);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    c.setTime(rs.getDate("date"));
                    map.put(c.getTime(), rs.getInt("counts"));
                }
            }
            for (int i = 0; i < period; i++) {

                switch (statisticsType) {
                    case DAY:
                        c.add(Calendar.DAY_OF_MONTH, -i);
                        break;
                    case WEEK:
                        c.add(Calendar.WEEK_OF_YEAR, -i);
                        break;
                    case MONTH:
                        c.add(Calendar.MONTH, -i);
                        break;
                }
                for (Date d : map.keySet()) {
                    if (!map.containsKey(c.getTime())) {
                        map.put(c.getTime(), 0);
                        break;
                    }
                }
            }
            return map;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //</Получает цифры для статистики--------------------------------------->
    /**
     * Delete site with specified id from db
     * @param siteId site id
     * @throws SQLException if db errors occurs
     */
    public void deleteSite(Integer siteId) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement st = con.prepareStatement("delete from site where id = ?");
            st.setInt(1, siteId);
            st.execute();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    //TODO: denis: БАГ: Картинка хранится в абсолютном пути, что неправильно. сделать для пользовательских картинок подпапку в имагес, брать путь к ней из контекста, а в базе хранить только имя,  к которому и добавлять
    public void addSite(String user, String siteName, String url, String desc, String regtype, int[] counters, int category, int subcategory) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO site(name, descr, screen, link, registration, subcategory) VALUES(?, ?, \"images/1.jpg\", ?, (SELECT registrationtype.id FROM registrationtype WHERE registrationtype.type=?), ?)");
            st.setString(1, siteName);
            st.setString(2, desc);
            st.setString(3, url);
            st.setString(4, regtype);
            st.setInt(5, subcategory);
            st.executeUpdate();
            ResultSet res = st.getGeneratedKeys();
            res.next();
            int sid = res.getInt(1);
            //System.out.println(sid);
            //Добавление пользователя
            st = conn.prepareStatement("INSERT INTO usersites(user, site) VALUES ((SELECT users.ID FROM users WHERE users.name=?), ?)");
            st.setString(1, user);
            st.setInt(2, sid);
            st.executeUpdate();
            //Добавление счетчиков
            st = conn.prepareStatement("INSERT INTO counter(site, counterType) VALUES (?, ?)");
            int[] counterIds = new int[counters.length];
            for (int i = 0; i < counters.length; i++) {
                st.setInt(1, sid);
                st.setInt(2, counters[i]);
                st.executeUpdate();
                res = st.getGeneratedKeys();
                res.next();
                counterIds[i] = res.getInt(1);
            }
            st = conn.prepareStatement("INSERT INTO counterdata(date, counter) VALUES (?, ?)");
            st.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            for (int cnt : counterIds) {
                st.setInt(2, cnt);
                st.executeUpdate();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Map<Integer,Map<Integer,String>> getCategoryAndSubcategory() throws SQLException{
        Connection con = null;
        Map<Integer,Map<Integer,String>> map = new HashMap<Integer,Map<Integer,String>>();
        try{
            con=getConnection();
            String query="SELECT * FROM category";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                map.put(rs.getInt("id"),new HashMap<Integer,String>());
            }
            for(int category:map.keySet()){
                query="SELECT id,name FROM subcategory WHERE (category=?)";
                ps=con.prepareStatement(query);
                ps.setInt(1, category);
                rs=ps.executeQuery();
                while(rs.next()){
                    map.get(category).put(rs.getInt("id"),rs.getString("name"));
                }
            }
            return map;
        }finally{
            if(con!=null){
                con.close();
            }
        }
    }

    public static DataModule getInstance() {
        if (instance == null) {
            instance = new DataModule();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        //return DriverManager.getConnection("jdbc:mysql://localhost/rating_site_db", "rating_user", "111111");
        //System.out.println(db);
        return db.getConnection();
    }
}
