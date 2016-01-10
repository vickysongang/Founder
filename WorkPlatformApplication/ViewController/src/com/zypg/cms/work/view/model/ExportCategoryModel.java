package com.zypg.cms.work.view.model;

import java.util.ArrayList;
import java.util.List;

public class ExportCategoryModel {
    private String categoryId;
    private String categoryName;
    private String url;
    private List<ExportCategoryModel> childrens = new ArrayList<ExportCategoryModel> ();

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setChildrens(List<ExportCategoryModel> childrens) {
        this.childrens = childrens;
    }

    public List<ExportCategoryModel> getChildrens() {
        return childrens;
    }
}
