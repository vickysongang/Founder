package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import oracle.jbo.domain.Number;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;


public class IllustrationEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText illustrationNameInputText;
    private RichInputText illustrationSourceInputText;

    public IllustrationEditManagedBean() {
    }

    public void preEditIllustration() {
        if (CommonUtil.getIllustrationAM().getDBTransaction().isDirty()) {
            CommonUtil.getIllustrationAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            Number docId = null;
            docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            CommonUtil.getIllustrationAM().preEditIllustration(docId);
        } else {
            CommonUtil.getIllustrationAM().initIllustrationEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), "BOOK", "END_PROD_LIB");
    }

    public boolean itemValidator() {
        if (this.getIllustrationNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getIllustrationNameInputText().getClientId(), "图片名称必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getIllustrationSourceInputText().getValue() == null) {
            this.addFormattedMessage(this.getIllustrationSourceInputText().getClientId(), "图片来源必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getIllustrationAM().batchEditIllustration((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getIllustrationAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            if (!itemValidator()) {
                return null;
            }
            CommonUtil.getIllustrationAM().getDBTransaction().commit();
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getIllustrationAM().getDBTransaction().rollback();
    }


    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setIllustrationNameInputText(RichInputText illustrationNameInputText) {
        this.illustrationNameInputText = illustrationNameInputText;
    }

    public RichInputText getIllustrationNameInputText() {
        return illustrationNameInputText;
    }

    public void setIllustrationSourceInputText(RichInputText illustrationSourceInputText) {
        this.illustrationSourceInputText = illustrationSourceInputText;
    }

    public RichInputText getIllustrationSourceInputText() {
        return illustrationSourceInputText;
    }
}
