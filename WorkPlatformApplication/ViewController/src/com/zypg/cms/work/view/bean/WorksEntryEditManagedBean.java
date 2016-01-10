package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;


public class WorksEntryEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText fileNameInputText;

    public WorksEntryEditManagedBean() {
    }

    public void preEditWorksEntry() {
        if (CommonUtil.getEntryAM().getDBTransaction().isDirty()) {
            CommonUtil.getEntryAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            oracle.jbo.domain.Number docId = null;
            docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
            Number newDocId = CommonUtil.getEntryAM().preEditWorksEntry(docId);
            this.setExpressionValue("#{pageFlowScope.docId}", newDocId);
        } else {
            CommonUtil.getEntryAM().initWorksEntryEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
    }

    public boolean itemValidator() {
        if (this.getFileNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getFileNameInputText().getClientId(), "标题必填", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getEntryAM().batchEditWorksEntry((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getEntryAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            try {
                if (!itemValidator()) {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            CommonUtil.getEntryAM().getDBTransaction().commit();
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void cancelWorksEntryActionListener(ActionEvent actionEvent) {
        CommonUtil.getEntryAM().rollBack();
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void setFileNameInputText(RichInputText fileNameInputText) {
        this.fileNameInputText = fileNameInputText;
    }

    public RichInputText getFileNameInputText() {
        return fileNameInputText;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }
}
