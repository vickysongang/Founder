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


public class PeriodicalEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText issnInput;
    private RichSelectOneChoice yearSelectOneChoice;
    private RichSelectOneChoice periodNumSelectOneChoice;

    public PeriodicalEditManagedBean() {

    }

    public void preEditPeriodical() {
        if (CommonUtil.getPeriodicalAM().getDBTransaction().isDirty()) {
            CommonUtil.getPeriodicalAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            Number returnDocId = CommonUtil.getPeriodicalAM().preEditPeriodical(mode, docId, CommonUtil.getCompCode());
            this.setExpressionValue("#{pageFlowScope.docId}", returnDocId);
        } else {
            CommonUtil.getPeriodicalAM().initPeriodicalEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public boolean itemValidator() {
        if (this.getYearSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getYearSelectOneChoice().getClientId(), "必须选择年份",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPeriodNumSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getPeriodNumSelectOneChoice().getClientId(), "必须选择期数",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getPeriodicalAM().batchEditPeriodical((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getPeriodicalAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            if (!itemValidator()) {
                return null;
            }
            CommonUtil.getPeriodicalAM().getDBTransaction().commit();
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getPeriodicalAM().getDBTransaction().rollback();
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
        CommonUtil.getPeriodicalAM().initYear(categoryId);
    }

    public void setIssnInput(RichInputText issnInput) {
        this.issnInput = issnInput;
    }

    public RichInputText getIssnInput() {
        return issnInput;
    }

    public void setYearSelectOneChoice(RichSelectOneChoice yearSelectOneChoice) {
        this.yearSelectOneChoice = yearSelectOneChoice;
    }

    public RichSelectOneChoice getYearSelectOneChoice() {
        return yearSelectOneChoice;
    }

    public void setPeriodNumSelectOneChoice(RichSelectOneChoice periodNumSelectOneChoice) {
        this.periodNumSelectOneChoice = periodNumSelectOneChoice;
    }

    public RichSelectOneChoice getPeriodNumSelectOneChoice() {
        return periodNumSelectOneChoice;
    }

    public void periodicalCategoryIdValueChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            Number categoryId = (Number)valueChangeEvent.getNewValue();
            CommonUtil.getPeriodicalAM().updateDocCategroupRel(categoryId);
        }
    }
}
