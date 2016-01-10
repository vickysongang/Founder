package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CustomManagedBean;

public class CommonManagedBean extends CustomManagedBean {
    public CommonManagedBean() {
        super();
    }

    public String getCurrTime() {
        return System.currentTimeMillis() + "";
    }
}
