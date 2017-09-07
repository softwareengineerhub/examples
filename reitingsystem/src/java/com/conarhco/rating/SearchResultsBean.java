/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conarhco.rating;

import com.conarhco.rating.Catalog.CatalogType;
import java.sql.SQLException;
import java.util.List;

/**
 * TODO: + rol:  Бин который содержит текст поиска, параметры галочек, и коллекцию поиска
 * Нужен для вывода результатов поиска
 * @author Конарх
 */
public class SearchResultsBean {

    //TODO: + rol: Перенести в СерчРезалтсБин
    private String searchText;
    private boolean descSearch;
    private boolean contentSearch;
    private CatalogType catalogType;
    //-----------------------------------

    public List<SiteManager> getSites() throws SQLException {
        return DataModule.getInstance().getSites(searchText, descSearch, contentSearch, catalogType);
    }

    public boolean getContentSearch() {
        return contentSearch;
    }

    public boolean getDescSearch() {
        return descSearch;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setCatalogType(CatalogType catalogType) {
        this.catalogType = catalogType;
    }

    public void setContentSearch(boolean contentSearch) {
        this.contentSearch = contentSearch;
    }

    public void setDescSearch(boolean descSearch) {
        this.descSearch = descSearch;
    }

    public boolean isSearchMode() {        
        return searchText != null;
    }
}
