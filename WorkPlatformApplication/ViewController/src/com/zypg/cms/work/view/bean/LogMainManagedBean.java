package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.log.query.CmsRollbackLogVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import oracle.jbo.domain.Number;


public class LogMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsRollbackLogVORowImpl> tableModel =
        new CustomTableModel<CmsRollbackLogVORowImpl>("WorkAMDataControl", "LogAM", "CmsRollbackLogVO", 20,
                                                      "findRollbackLogByDocId");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);

    public void preRollbackLog() {
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getLogAM().preRollbackLog(docId);
    }

    public void preProcessLog() {
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getLogAM().preProcessLog(docId);
    }

    public LogMainManagedBean() {
    }

    public void setTableModel(CustomTableModel<CmsRollbackLogVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsRollbackLogVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }
}
