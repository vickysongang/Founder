package com.zypg.cms.admin.view.bean;


import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

public class RoleUserBean extends CustomManagedBean {
    private RichPopup deleteRolePopup;

    public RoleUserBean() {
    }

    public void deleteUserPrivilegeActionListener(ActionEvent actionEvent) {
        Number roleId = (Number)this.resolveExpression("#{pageFlowScope.roleId}");
        Number userId = (Number)actionEvent.getComponent().getAttributes().get("userId");

        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("userId", userId);
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("userNmae",
                                                                    actionEvent.getComponent().getAttributes().get("userNmae"));
        RichPopup.PopupHints rph = new RichPopup.PopupHints();
        this.getDeleteRolePopup().show(rph);
    }

    public void initRoleUser() {
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("roleUserID", this.getAdminAM().getUserID());
    }

    public void setDeleteRolePopup(RichPopup deleteRolePopup) {
        this.deleteRolePopup = deleteRolePopup;
    }

    public RichPopup getDeleteRolePopup() {
        return deleteRolePopup;
    }

    public void deleteRoleDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            Number roleId = (Number)this.resolveExpression("#{pageFlowScope.roleId}");
            Number userId = (Number)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userId");
            OperationBinding op = this.findOperation("revokeRole");
            op.getParamsMap().put("roleId", roleId);
            op.getParamsMap().put("userId", userId);
            op.execute();
            this.findOperation("Commit").execute();
            DCIteratorBinding iter = this.findIterator("CmsRoleUserVOIterator");
            iter.executeQuery();
            this.getAdminAM().createAdminOperLog("管理员:\"" + this.resolveExpression("#{pageFlowScope.userNmae}") +
                                                 "\" 回收成功",
                                                 (String)this.resolveExpression("#{pageFlowScope.compCode}"));

        }

    }
}
