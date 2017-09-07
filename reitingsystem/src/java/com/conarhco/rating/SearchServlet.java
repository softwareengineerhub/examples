package com.conarhco.rating;

import com.conarhco.rating.Catalog.CatalogType;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO: + rol. В поиске сделать пост-запрос и поддержку русского языка
 * TODO: + rol: БАГ: Поиск по пустой строке не работает
 */
public class SearchServlet extends HttpServlet {

    //TODO: + rol: Сервлет осуществляет поиск и помещает в сессию СерчРезалтс
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
            String type = req.getParameter("type");
            CatalogType cType = type == null || type.length() == 0 ? CatalogType.CATALOG_ALL : CatalogType.valueOf(Integer.parseInt(type));
            String searchText = req.getParameter("searchText");
            String descSearch = req.getParameter("descSearch");
            String contentSearch = req.getParameter("contentSearch");
            SearchResultsBean searchResult = (SearchResultsBean) req.getSession().getAttribute("searchResultBean");
            if (searchResult == null) {
                searchResult = new SearchResultsBean();
                req.getSession().setAttribute("searchResultBean", searchResult);
            }
            searchResult.setSearchText(searchText);
            searchResult.setDescSearch("on".equals(descSearch) ? true : false);
            searchResult.setContentSearch("on".equals(contentSearch) ? true : false);
            searchResult.setCatalogType(cType);
            String redirect = "catalog.jsp?type=" + cType;
            resp.sendRedirect(redirect);
        } catch (Exception ex) {
            getServletContext().log("ERROR", ex);
        }
    }
}
