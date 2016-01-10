package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

public class WorksEntryViewManagedBean extends CustomManagedBean {
    public WorksEntryViewManagedBean() {
    }

    public void initWorksEntry() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        System.out.println(docIdStr);
        try {
            oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
            CommonUtil.getEntryAM().initWorksEntryViewInfo(docId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
