package com.zypg.cms.admin.view.model;

public class ChoiceCategoryModel {
    private String categoryId;
    private String categoryCode;
    private String categoryName;
    private String parentNodeId;
    private String isLeafNode;
    private String lastUpdateDate;
    private String categoryDescription;
    private String categoryType;

    public ChoiceCategoryModel() {
        super();
    }

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

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setIsLeafNode(String isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public String getIsLeafNode() {
        return isLeafNode;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryType() {
        return categoryType;
    }
}
