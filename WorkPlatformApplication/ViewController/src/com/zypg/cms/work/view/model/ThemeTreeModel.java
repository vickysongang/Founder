package com.zypg.cms.work.view.model;

public class ThemeTreeModel {
    private String id;
    private String pid;
    private String name;
    private String type;
    private String resCount;
    private String compCode;
    private String remarks;

    public ThemeTreeModel() {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setResCount(String resCount) {
        this.resCount = resCount;
    }

    public String getResCount() {
        return resCount;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }
}
