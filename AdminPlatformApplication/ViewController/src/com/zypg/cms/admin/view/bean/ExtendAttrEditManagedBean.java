package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.jbo.domain.Number;

import oracle.wsm.common.util.CommonUtil;

public class ExtendAttrEditManagedBean extends CustomManagedBean {
    private RichInputText attrCodeInput;
    private RichInputText attrMeaningInput;
    private RichSelectOneChoice attrTypeChoice;

    public ExtendAttrEditManagedBean() {
    }

    public void initEditExtendAttr() {
        String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
        Number extendAttrId = (Number)this.resolveExpression("#{pageFlowScope.extendAttrId}");
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String libCode = (String)this.resolveExpression("#{pageFlowScope.libCode}");
        this.getAdminAM().preEditExtendAttr(operType, extendAttrId, compCode, libCode);
    }

    public boolean validator() {
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String libCode = (String)this.resolveExpression("#{pageFlowScope.libCode}");
        String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
        if (this.getAttrCodeInput().getValue() == null) {
            this.addFormattedMessage(this.getAttrCodeInput().getClientId(), "字段唯一标识不能为空", FacesMessage.SEVERITY_WARN);
            return false;
        } else if ("CREATE".equals(operType) &&
                   this.getAdminAM().isAttrCodeExsit((String)this.getAttrCodeInput().getValue(), compCode, libCode)) {
            this.addFormattedMessage(this.getAttrCodeInput().getClientId(), "该字段已存在", FacesMessage.SEVERITY_WARN);
            return false;
        } else if (this.getAttrMeaningInput().getValue() == null) {
            this.addFormattedMessage(this.getAttrMeaningInput().getClientId(), "字段含义不能为空", FacesMessage.SEVERITY_WARN);
            return false;
        } else if (this.getAttrTypeChoice().getValue() == null) {
            this.addFormattedMessage(this.getAttrTypeChoice().getClientId(), "字段类型不能为空", FacesMessage.SEVERITY_WARN);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (validator()) {
            this.getAdminAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            return null;
        }
    }

    public String cancelAction() {
        this.getAdminAM().getDBTransaction().rollback();
        return "toReturn";
    }

    public void setAttrCodeInput(RichInputText attrCodeInput) {
        this.attrCodeInput = attrCodeInput;
    }

    public RichInputText getAttrCodeInput() {
        return attrCodeInput;
    }

    public void setAttrMeaningInput(RichInputText attrMeaningInput) {
        this.attrMeaningInput = attrMeaningInput;
    }

    public RichInputText getAttrMeaningInput() {
        return attrMeaningInput;
    }

    public void setAttrTypeChoice(RichSelectOneChoice attrTypeChoice) {
        this.attrTypeChoice = attrTypeChoice;
    }

    public RichSelectOneChoice getAttrTypeChoice() {
        return attrTypeChoice;
    }
}
