/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package www.beans;

import www.News;

/**
 *
 * @author Конарх
 */
public class NewsRow {
    private News newsByRating;
    private News newsByPopular;
    private News newsByDate;

    public NewsRow(News newsByRating, News newsByPopular, News newsByDate) {
        this.newsByRating = newsByRating;
        this.newsByPopular = newsByPopular;
        this.newsByDate = newsByDate;
    }

    public News getNewsByDate() {
        return newsByDate;
    }

    public News getNewsByPopular() {
        return newsByPopular;
    }

    public News getNewsByRating() {
        return newsByRating;
    }

}
