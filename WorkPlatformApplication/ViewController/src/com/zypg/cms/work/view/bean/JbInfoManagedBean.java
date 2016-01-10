package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;

import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.event.ActionEvent;

public class JbInfoManagedBean extends CustomManagedBean {
    public JbInfoManagedBean() {
    }

    public void initJbInfo() {
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getBookAM().initJbInfo(docId);
    }

    public void saveActionListener(ActionEvent actionEvent) {
        CommonUtil.getBookAM().getDBTransaction().commit();
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getBookAM().getDBTransaction().rollback();
    }
}
