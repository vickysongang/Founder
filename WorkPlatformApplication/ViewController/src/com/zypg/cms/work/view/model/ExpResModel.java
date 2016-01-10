package com.zypg.cms.work.view.model;

public class ExpResModel {
    private String resCode;
    private String resName;
    private String libCode;
    private String columnName;
    private String tableName;
    private String exportFlag;

    public ExpResModel() {
        super();
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResName() {
        return resName;
    }

    public void setLibCode(String libCode) {
        this.libCode = libCode;
    }

    public String getLibCode() {
        return libCode;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setExportFlag(String exportFlag) {
        this.exportFlag = exportFlag;
    }

    public String getExportFlag() {
        return exportFlag;
    }
}
