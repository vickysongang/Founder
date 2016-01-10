package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class NewsSetupManagedBean extends CustomManagedBean {
    private RichPopup deletePopup;

    public NewsSetupManagedBean() {
    }

    public void initNewsSetup() {
        this.getAdminAM().findComp4Common((String)this.resolveExpression("#{pageFlowScope.compStr}"));
        String compCode = this.getAdminAM().getFirstCompCode();
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.getAdminAM().initNewsSetupQueryVO(compCode);
        this.getAdminAM().queryNewsSetup(null, null);
    }

    public void switchCompActionListener(ActionEvent actionEvent) {
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.refreshUIComponent(actionEvent.getComponent().getParent());
        this.getAdminAM().initNewsSetupQueryVO(compCode);
        this.getAdminAM().queryNewsSetup(null, null);
    }

    public void categoryValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Number categoryId = (Number)valueChangeEvent.getNewValue();
        this.setExpressionValue("#{pageFlowScope.categoryId}", categoryId);
        this.getAdminAM().queryNewsSetup(categoryId, null);
    }

    public void yearValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Number year = (Number)valueChangeEvent.getNewValue();
        this.setExpressionValue("#{pageFlowScope.year}", year);
        this.getAdminAM().queryNewsSetup(null, year);
    }

    public void deleteActionListener(ActionEvent actionEvent) {
        Number setupId = (Number)actionEvent.getComponent().getAttributes().get("setupId");
        this.setExpressionValue("#{pageFlowScope.setupId}", setupId);
        this.showPopup(this.getDeletePopup().getClientId());
    }

    public void deleteDialogListener(DialogEvent dialogEvent) {
        Number setupId = (Number)this.resolveExpression("#{pageFlowScope.setupId}");
        String result = this.getAdminAM().deleteNewsSetup(setupId);
        if ("S".equals(result)) {
            this.showMessage("删除成功");
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
        this.findIterator("CmsNewsSetupVOIterator").executeQuery();
    }

    public void newOrEditReturnListener(ReturnEvent returnEvent) {
        this.getAdminAM().queryNewsSetup(null, null);
        this.findIterator("CmsNewsSetupVOIterator").executeQuery();
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void batchCreateNewsPaperReturnListener(ReturnEvent returnEvent) {
        this.getAdminAM().queryNewsSetup(null, null);
        this.findIterator("CmsNewsSetupVOIterator").executeQuery();
    }
}
