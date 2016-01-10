package com.zypg.cms.admin.view.model;

public class ResCategoryModel {
    private String categoryId;
    private String categoryName;
    private String parentNodeType;
    private String parentNodeId;
    private String compCode;
    private String libCode;
    private String libTypeCode;
    private String primaryFlag;
    private String categoryGroupCode;
    private int seq;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String isLeafNode;
    private String lastUpdateDate;

    public ResCategoryModel() {
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

    public void setParentNodeType(String parentNodeType) {
        this.parentNodeType = parentNodeType;
    }

    public String getParentNodeType() {
        return parentNodeType;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setLibCode(String libCode) {
        this.libCode = libCode;
    }

    public String getLibCode() {
        return libCode;
    }

    public void setLibTypeCode(String libTypeCode) {
        this.libTypeCode = libTypeCode;
    }

    public String getLibTypeCode() {
        return libTypeCode;
    }

    public void setPrimaryFlag(String primaryFlag) {
        this.primaryFlag = primaryFlag;
    }

    public String getPrimaryFlag() {
        return primaryFlag;
    }

    public void setCategoryGroupCode(String categoryGroupCode) {
        this.categoryGroupCode = categoryGroupCode;
    }

    public String getCategoryGroupCode() {
        return categoryGroupCode;
    }

    public void setIsLeafNode(String isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public String getIsLeafNode() {
        return isLeafNode;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
}
