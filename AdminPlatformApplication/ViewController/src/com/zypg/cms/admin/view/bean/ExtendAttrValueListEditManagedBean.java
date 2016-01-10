package com.zypg.cms.admin.view.bean;


import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;


public class ExtendAttrValueListEditManagedBean extends CustomManagedBean {
    private RichInputText valueCodeInput;
    private RichInputText valueMeaningInput;

    public ExtendAttrValueListEditManagedBean() {
    }

    public void initExtendAttrValueListEdit() {
        String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
        Number listId = (Number)this.resolveExpression("#{pageFlowScope.listId}");
        Number extendAttrId = (Number)this.resolveExpression("#{pageFlowScope.extendAttrId}");
        this.getAdminAM().preEditExtendAttrValueList(operType, extendAttrId, listId);
    }

    public boolean validator() {
        String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
        Number extendAttrId = (Number)this.resolveExpression("#{pageFlowScope.extendAttrId}");
        if (this.getValueCodeInput().getValue() == null) {
            this.addFormattedMessage(this.getValueCodeInput().getClientId(), "值Code不能为空", FacesMessage.SEVERITY_WARN);
            return false;
        } else if ("CREATE".equals(operType) &&
                   this.getAdminAM().isAttrValueCodeExsit((String)this.getValueCodeInput().getValue(), extendAttrId)) {
            this.addFormattedMessage(this.getValueCodeInput().getClientId(), "该字段已存在", FacesMessage.SEVERITY_WARN);
            return false;
        } else if (this.getValueMeaningInput().getValue() == null) {
            this.addFormattedMessage(this.getValueMeaningInput().getClientId(), "值含义不能为空", FacesMessage.SEVERITY_WARN);
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

    public void setValueCodeInput(RichInputText valueCodeInput) {
        this.valueCodeInput = valueCodeInput;
    }

    public RichInputText getValueCodeInput() {
        return valueCodeInput;
    }

    public void setValueMeaningInput(RichInputText valueMeaningInput) {
        this.valueMeaningInput = valueMeaningInput;
    }

    public RichInputText getValueMeaningInput() {
        return valueMeaningInput;
    }
}
