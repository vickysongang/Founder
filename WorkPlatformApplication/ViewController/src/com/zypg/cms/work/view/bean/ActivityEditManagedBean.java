package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import oracle.jbo.domain.Number;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;


public class ActivityEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private RichInputText activityThemeInputText;

    public ActivityEditManagedBean() {
    }


    public void preEditActivity() {
        if (CommonUtil.getActivityAM().getDBTransaction().isDirty()) {
            CommonUtil.getActivityAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            Number newDocId = CommonUtil.getActivityAM().preEditActivity(mode, docId, CommonUtil.getCompCode());
            this.setExpressionValue("#{pageFlowScope.docId}", newDocId);
        } else {
            CommonUtil.getActivityAM().initActivityEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
    }


    public boolean itemValidator() {
        if (this.getActivityThemeInputText().getValue() == null) {
            this.addFormattedMessage(this.getActivityThemeInputText().getClientId(), "活动主题必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getActivityAM().batchEditActivity((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getActivityAM().getTransaction().commit();
            return "toReturn";
        } else {
            if (!itemValidator()) {
                return null;
            }
            CommonUtil.getActivityAM().getTransaction().commit();
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getActivityAM().getTransaction().rollback();
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void setActivityThemeInputText(RichInputText activityThemeInputText) {
        this.activityThemeInputText = activityThemeInputText;
    }

    public RichInputText getActivityThemeInputText() {
        return activityThemeInputText;
    }
}
