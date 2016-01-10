package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;


public class ThemeEditManagedBean extends CustomManagedBean {
    private RichInputText themeNameInputText;

    public ThemeEditManagedBean() {
    }

    public void preEditTheme() {
        if (CommonUtil.getThemeAM().getDBTransaction().isDirty()) {
            CommonUtil.getThemeAM().getDBTransaction().rollback();
        }
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number docId = null;
        if (!"CREATE".equals(mode)) {
            docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        }
        docId = CommonUtil.getThemeAM().preEditTheme(mode, docId, CommonUtil.getCompCode());
        this.setExpressionValue("#{pageFlowScope.docId}", docId);
    }

    public boolean itemValidator() {
        if (this.getThemeNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getThemeNameInputText().getClientId(), "主题名必填", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (!itemValidator()) {
            return null;
        }
        CommonUtil.getThemeAM().getDBTransaction().commit();
        this.appendScript("saveExtendAttrs()");
        return null;
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getThemeAM().getDBTransaction().rollback();
    }

    public void setThemeNameInputText(RichInputText themeNameInputText) {
        this.themeNameInputText = themeNameInputText;
    }

    public RichInputText getThemeNameInputText() {
        return themeNameInputText;
    }
}
