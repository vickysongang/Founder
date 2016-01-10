package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;

public class ElecProdEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText isrcIsbnInput;
    private RichInputText elecProdNameInputText;

    public ElecProdEditManagedBean() {
    }

    public void preEditElecProd() {
        if (CommonUtil.getElecProdAM().getDBTransaction().isDirty()) {
            CommonUtil.getElecProdAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            Number newDocId =
                CommonUtil.getElecProdAM().preEditElecProd(mode, docId, CommonUtil.getCompCode(), CommonUtil.getCompName());
            this.setExpressionValue("#{pageFlowScope.docId}", newDocId);
        } else {
            CommonUtil.getElecProdAM().initElecProdEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
    }

    public boolean itemValidator() {
        if (this.getElecProdNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getElecProdNameInputText().getClientId(), "电子音像产品名称必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getIsrcIsbnInput().getValue() == null) {
            this.addFormattedMessage(this.getIsrcIsbnInput().getClientId(), "ISRC/ISBN必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (!CommonUtil.validateIsbn((String)this.getIsrcIsbnInput().getValue()) &&
                   !CommonUtil.validateIsrc((String)this.getIsrcIsbnInput().getValue())) {
            this.addFormattedMessage(this.getIsrcIsbnInput().getClientId(), "请输入合法的ISRC/ISBN",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getElecProdAM().batchEditElecProd((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getElecProdAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            if (!itemValidator()) {
                return null;
            }
            CommonUtil.getElecProdAM().getDBTransaction().commit();
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getElecProdAM().getDBTransaction().rollback();
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setIsrcIsbnInput(RichInputText isrcIsbnInput) {
        this.isrcIsbnInput = isrcIsbnInput;
    }

    public RichInputText getIsrcIsbnInput() {
        return isrcIsbnInput;
    }


    public void setElecProdNameInputText(RichInputText elecProdNameInputText) {
        this.elecProdNameInputText = elecProdNameInputText;
    }

    public RichInputText getElecProdNameInputText() {
        return elecProdNameInputText;
    }
}
