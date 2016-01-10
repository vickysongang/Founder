package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;

import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

public class EncyclopediasEntryViewManagedBean extends CustomManagedBean {
    public EncyclopediasEntryViewManagedBean() {
    }

    public void initEncyclopediasEntry() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        System.out.println(docIdStr);
        try {
            oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
            CommonUtil.getEntryAM().initEncyclopediasEntryViewInfo(docId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
