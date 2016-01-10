package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.jbo.domain.Number;


public class NewspaperEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText issnInput;
    private RichInputText cnInputText;
    private RichSelectOneChoice yearSelectOneChoice;
    private RichSelectOneChoice newsIdSelectOneChoice;

    public NewspaperEditManagedBean() {

    }

    public void preEditNewspaper() {
        if (CommonUtil.getNewspaperAM().getDBTransaction().isDirty()) {
            CommonUtil.getNewspaperAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            Number returnDocId = CommonUtil.getNewspaperAM().preEditNewspaper(mode, docId, CommonUtil.getCompCode());
            this.setExpressionValue("#{pageFlowScope.docId}", returnDocId);
        } else {
            CommonUtil.getNewspaperAM().initNewspaperEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public boolean validate() {
        String issn = (String)this.getIssnInput().getValue();
        //正则验证isbn
        if (CommonUtil.validateIssn(issn)) {
            return true;
        } else {
            this.addFormattedMessage(issn, "请输入合法的ISSN", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    public boolean itemValidator() {
        if (this.getIssnInput().getValue() == null) {
            this.addFormattedMessage(this.getIssnInput().getClientId(), "ISSN必填", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        //        else if (CommonUtil.validateIssn((String)this.getIssnInput().getValue())) {
        //            //            this.addFormattedMessage(this.getIssnInput().getClientId(), "请输入合法的ISSN", FacesMessage.SEVERITY_ERROR);
        //            //            return false;
        //        }
        else if (this.getCnInputText().getValue() == null) {
            this.addFormattedMessage(this.getCnInputText().getClientId(), "CN必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getYearSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getYearSelectOneChoice().getClientId(), "年份必选", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getNewsIdSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getNewsIdSelectOneChoice().getClientId(), "期数必选",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getNewspaperAM().batchEditNewspaper((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getNewspaperAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            if (!itemValidator()) {
                return null;
            }
            CommonUtil.getNewspaperAM().getDBTransaction().commit();
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getNewspaperAM().getDBTransaction().rollback();
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void categoryChangeListener(ValueChangeEvent valueChangeEvent) {
        Number categoryId = (Number)valueChangeEvent.getNewValue();
        this.setExpressionValue("#{pageFlowScope.categoryId}", categoryId);
        CommonUtil.getNewspaperAM().initYear(categoryId);
    }

    public void setIssnInput(RichInputText issnInput) {
        this.issnInput = issnInput;
    }

    public RichInputText getIssnInput() {
        return issnInput;
    }

    public void setCnInputText(RichInputText cnInputText) {
        this.cnInputText = cnInputText;
    }

    public RichInputText getCnInputText() {
        return cnInputText;
    }

    public void setYearSelectOneChoice(RichSelectOneChoice yearSelectOneChoice) {
        this.yearSelectOneChoice = yearSelectOneChoice;
    }

    public RichSelectOneChoice getYearSelectOneChoice() {
        return yearSelectOneChoice;
    }

    public void setNewsIdSelectOneChoice(RichSelectOneChoice newsIdSelectOneChoice) {
        this.newsIdSelectOneChoice = newsIdSelectOneChoice;
    }

    public RichSelectOneChoice getNewsIdSelectOneChoice() {
        return newsIdSelectOneChoice;
    }

    public void newspaperCategoryIdValueChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            Number categoryId = (Number)valueChangeEvent.getNewValue();
            CommonUtil.getNewspaperAM().updateDocCategroupRel(categoryId);
        }
    }
}
