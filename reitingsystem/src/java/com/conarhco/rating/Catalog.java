/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Catalog data provider
 * @author Admin
 */
public class Catalog {

    private Integer categoryId;
    private Integer subCategoryId;
    private Integer userId;
    private CatalogType catalogType;
    private List<Map<Integer, String>> categories = new ArrayList<Map<Integer, String>>();
    private Map<Integer, String> subCategories = new LinkedHashMap<Integer, String>();

    /**
     * Catalog type enumeration
     */
    public enum CatalogType {

        /**
         * Describes all catalog types
         */
        CATALOG_ALL(99),
        /**
         * Describes sites catalog type
         */
        CATALOG_SITES(1),
        /**
         * Describes stores catalog type
         */
        CATALOG_STORES(2);

        /**
         * Constructs catalog type
         * @param type catalog type value
         */
        CatalogType(Integer type) {
            this.type = type;
        }

        /**
         * Returns catalog type value
         * @return
         */
        public Integer getType() {
            return type;
        }

        /**
         * Returns value for speceified catalog type, or null
         * @param type catalog type
         * @return value for speceified catalog type, or null
         */
        public static CatalogType valueOf(Integer type) {
            if (type == null) {
                return CATALOG_ALL;
            }
            CatalogType catalogType = CATALOG_ALL;
            for (CatalogType value : CatalogType.values()) {
                if (value.getType().equals(type)) {
                    catalogType = value;
                    continue;
                }
            }
            return catalogType;
        }

        @Override
        public String toString() {
            return Integer.toString(type);
        }
        private Integer type = null;
    }

    /**
     * Sets user id by user name
     * @param userName user name
     */
    public void setUserId(String userName) throws SQLException {
        userId = DataModule.getInstance().getUserId(userName);
    }

    /**
     * Sets category identifier
     * @param categoryId category id
     * @throws SQLException if db errors occurs
     */
    public void setCategoryId(int categoryId) throws SQLException {
        this.categoryId = categoryId;
        if (categories.isEmpty()) {
            categories = DataModule.getInstance().getCategories();
            if ((this.categoryId == null || this.categoryId == 0) && !categories.isEmpty()) {
                Map<Integer, String> part = categories.get(0);
                if (!part.isEmpty()) {
                    this.categoryId = part.keySet().iterator().next();
                }
            }
        }
    }

    /**
     * Sets subcategory identifier
     * @param subCategoryId sub category id
     * @throws SQLException if db errors occurs
     */
    public void setSubCategoryId(int subCategoryId) throws SQLException {
        this.subCategoryId = subCategoryId;
        if (subCategories.isEmpty()) {
            subCategories = DataModule.getInstance().getSubCategories(categoryId);
            if ((this.subCategoryId == null || this.subCategoryId == 0) && !subCategories.isEmpty()) {
                this.subCategoryId = subCategories.keySet().iterator().next();
            }
        }
    }

    /**
     * Sets catalog type
     * @param catalogType catalog type value
     */
    public void setCatalogType(int catalogType) {
        this.catalogType = CatalogType.valueOf(catalogType);
    }

    /**
     * Returns category identifier
     * @return category identifier
     */
    public int getCategoryId() {
        return categoryId == null ? 0 : categoryId;
    }

    /**
     * Returns subcategory identifier
     * @return subcategory identifier
     */
    public int getSubCategoryId() {
        return subCategoryId == null ? 0 : subCategoryId;
    }

    /**
     * Returns catalog type
     * @return catalog type
     */
    public int getCatalogType() {
        return catalogType == null ? CatalogType.CATALOG_ALL.getType() : catalogType.getType();
    }

    /**
     * Returns set of sites depens on category, subcategory and catalog type
     * @return set of sites depens on category, subcategory and catalog type
     * @throws SQLException if db errors occurs
     */
    public List<SiteManager> getSites() throws SQLException {
        return DataModule.getInstance().getSites(subCategoryId, catalogType);
    }

    /**
     * Returns list of sites related to user
     * @return list of sites related to user
     * @throws SQLException if db errors occurs
     */
    public List<SiteManager> getUserSites() throws SQLException {
        return DataModule.getInstance().getUserSites(userId);
    }

    /**
     * Returns map of categories splitted on parts
     * @return map of categories splitted on parts
     */
    public List<Map<Integer, String>> getCategories() {
        return categories;
    }

    /**
     * Return map of subcategories depends on category
     * @return map of subcategories depends on category
     */
    public Map<Integer, String> getSubCategories() {
        return subCategories;
    }

    /**
     * Retuens categories parts count
     * @return categories parts count
     */
    public int getCategoriesPartsCount() {
        return categories.size();
    }

    /**
     * Returns subcategories map size
     * @return subcategories map size
     */
    public int getSubCategoriesSize() {
        return subCategories.size();
    }
}
