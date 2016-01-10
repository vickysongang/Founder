package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class NewspaperSetupManagedBean extends CustomManagedBean {
    private RichPopup deletePopup;

    public NewspaperSetupManagedBean() {
    }

    public void initNewspaperSetup() {
        this.getAdminAM().findComp4Common((String)this.resolveExpression("#{pageFlowScope.compStr}"));
        String compCode = this.getAdminAM().getFirstCompCode();
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.getAdminAM().initNewspaperSetup(compCode);
    }

    public void switchCompActionListener(ActionEvent actionEvent) {
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.refreshUIComponent(actionEvent.getComponent().getParent());
        this.getAdminAM().initNewspaperSetup(compCode);
    }

    public void deleteActionListener(ActionEvent actionEvent) {
        Number categoryId = (Number)actionEvent.getComponent().getAttributes().get("categoryId");
        this.setExpressionValue("#{pageFlowScope.categoryId}", categoryId);
        this.showPopup(this.getDeletePopup().getClientId());
    }

    public void deleteDialogListener(DialogEvent dialogEvent) {
        Number categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
        String result = this.getAdminAM().deleteNewspaperSetup(categoryId);
        if ("S".equals(result)) {
            this.showMessage("删除成功");
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
        this.findIterator("CmsNewspaperSetupVOIterator").executeQuery();
    }

    public void newOrEditReturnListener(ReturnEvent returnEvent) {
        this.findIterator("CmsNewspaperSetupVOIterator").executeQuery();
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }
}
