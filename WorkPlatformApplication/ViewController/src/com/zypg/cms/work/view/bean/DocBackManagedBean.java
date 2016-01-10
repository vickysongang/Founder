package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.am.LogAMImpl;
import com.zypg.cms.work.model.viewobject.log.CmsRollbackLogTVOImpl;
import com.zypg.cms.work.model.viewobject.log.CmsRollbackLogTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class DocBackManagedBean extends CustomManagedBean {
    private RichInputText reasonInputText;

    public DocBackManagedBean() {
    }

    public void preDocBack() {
        String docIds = (String)this.resolveExpression("#{pageFlowScope.docId}");
        String[] docIdArray = docIds.split(",");
        if (docIdArray != null && docIdArray.length > 0) {
            try {
                Number docId = new Number(docIdArray[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveActionListener(ActionEvent actionEvent) {
        String docIds = (String)this.resolveExpression("#{pageFlowScope.docId}");
        String[] docIdArray = docIds.split(",");
        String rollbackReason = (String)this.getReasonInputText().getValue();
        for (String str : docIdArray) {
            try {
                Number docId = new Number(str);
                CommonUtil.getLogAM().insertRollbackLog(docId, rollbackReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        CommonUtil.getLogAM().getDBTransaction().commit();
        this.setExpressionValue("#{pageFlowScope.operatorResult}", "SAVE");
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.operatorResult}", "CANCEL");
    }

    public void setReasonInputText(RichInputText reasonInputText) {
        this.reasonInputText = reasonInputText;
    }

    public RichInputText getReasonInputText() {
        return reasonInputText;
    }
}
