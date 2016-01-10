package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;

public class NewsSetupEditManagedBean extends CustomManagedBean {
    private RichInputText newsNumInputText;
    private RichInputDate publishedDateInputDate;

    public NewsSetupEditManagedBean() {

    }

    public void init4NewsSetupEdit() {
        if (this.getAdminAM().getDBTransaction().isDirty()) {
            this.getAdminAM().getDBTransaction().rollback();
        }
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
        Number setupId = (Number)this.resolveExpression("#{pageFlowScope.setupId}");
        Number year = (Number)this.resolveExpression("#{pageFlowScope.year}");
        this.getAdminAM().preEditNews(mode, categoryId, setupId, year);
    }

    public boolean requiredItemValidator() {
        if (this.isEmpty(this.getNewsNumInputText().getValue())) {
            this.addFormattedMessage(this.getNewsNumInputText().getClientId(), "刊期必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (isEmpty(this.getPublishedDateInputDate().getValue())) {
            this.addFormattedMessage(this.getPublishedDateInputDate().getClientId(), "日期必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (requiredItemValidator()) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number setupId = (Number)this.resolveExpression("#{pageFlowScope.setupId}");
            String result =
                this.getAdminAM().saveNewsSetup(mode, setupId, (String)this.resolveExpression("#{pageFlowScope.compCode}"));
            if ("S".equals(result)) {
                this.showMessage("保存成功");
                return "toReturn";
            } else {
                this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
            }
        }
        return null;
    }

    public String cancelAction() {
        this.getAdminAM().getDBTransaction().rollback();
        return "toReturn";
    }

    public void setPublishedDateInputDate(RichInputDate publishedDateInputDate) {
        this.publishedDateInputDate = publishedDateInputDate;
    }

    public RichInputDate getPublishedDateInputDate() {
        return publishedDateInputDate;
    }

    public void setNewsNumInputText(RichInputText newsNumInputText) {
        this.newsNumInputText = newsNumInputText;
    }

    public RichInputText getNewsNumInputText() {
        return newsNumInputText;
    }
}
