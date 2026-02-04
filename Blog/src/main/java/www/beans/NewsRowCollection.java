/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www.beans;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import www.News;

/**
 *
 * @author Конарх
 */
public class NewsRowCollection {

    public NewsRowCollection() {
    }

    public Collection<NewsRow> getNews() throws SQLException{
        Iterator<News> newsR = new NewsCollection("view DESC").getNews().iterator();
        Iterator<News> newsP = new NewsCollection("decisions DESC").getNews().iterator();
        Iterator<News> newsD = new NewsCollection("date DESC").getNews().iterator();
        Collection<NewsRow> rows = new LinkedHashSet<NewsRow>();
        while (newsR.hasNext()){
            NewsRow r = new NewsRow(newsR.next(), newsP.next(), newsD.next());
            rows.add(r);
        }
        return rows;
    }

}
