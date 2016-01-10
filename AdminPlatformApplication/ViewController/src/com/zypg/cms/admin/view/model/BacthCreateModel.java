package com.zypg.cms.admin.view.model;

public class BacthCreateModel {
    public BacthCreateModel() {
        super();
    } 
    private Number year;
    private String name;
    private String date;
    private Number periodNum;
    private boolean flag;
    private String isExist;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setPeriodNum(Number periodNum) {
        this.periodNum = periodNum;
    }

    public Number getPeriodNum() {
        return periodNum;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }

    public String getIsExist() {
        return isExist;
    }
 

    public void setYear(Number year) {
        this.year = year;
    }

    public Number getYear() {
        return year;
    }
}
