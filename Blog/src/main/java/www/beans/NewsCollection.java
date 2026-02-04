/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www.beans;

import java.sql.SQLException;
import java.util.Collection;
import www.DataModel;
import www.News;

/**
 * TODO Переделать так, чтобы параметр сортировки ставился отдельно, а тип (восходящая, нисходящая) отдельно (через булеан)
 * @author Конарх
 */
public class NewsCollection {
    private String sort;

    public NewsCollection(){
    }

    public NewsCollection(String sort){
        setSort(sort);
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Collection<News> getNews() throws SQLException{
        return DataModel.instance().getNews(sort,false);
    }

}
