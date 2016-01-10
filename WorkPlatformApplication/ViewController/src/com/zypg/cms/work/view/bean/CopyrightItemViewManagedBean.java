package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;


public class CopyrightItemViewManagedBean extends CustomManagedBean {
    public CopyrightItemViewManagedBean() {
    }

    public void initInfo4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.itemId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number itemId = new oracle.jbo.domain.Number(docIdStr);
                CommonUtil.getCopyrightAM().initItem4View(itemId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
