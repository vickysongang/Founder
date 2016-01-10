package com.zypg.cms.work.view.model;

import java.io.Serializable;

public class ExtendAttrModel {
    private String attrType;
    private String attrValue;

    public ExtendAttrModel() {
        super();
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrValue() {
        return attrValue;
    }
}
