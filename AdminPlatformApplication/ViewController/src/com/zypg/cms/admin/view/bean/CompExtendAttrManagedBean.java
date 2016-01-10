package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class CompExtendAttrManagedBean extends CustomManagedBean {
    private RichPopup deletePopup;
    private RichPopup deleteValueListPopup;

    public CompExtendAttrManagedBean() {
    }

    public void initCompExtendAttr() {
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String libCode = "BOOK";
        this.setExpressionValue("#{pageFlowScope.libCode}", "BOOK");
        this.getAdminAM().queryExtendAttr(compCode, libCode);
    }

    public void switchLibActionListener(ActionEvent actionEvent) {
        String libCode = (String)actionEvent.getComponent().getAttributes().get("libCode");
        this.setExpressionValue("#{pageFlowScope.libCode}", libCode);
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        this.getAdminAM().queryExtendAttr(compCode, libCode);
    }

    public void deleteActionListener(ActionEvent actionEvent) {
        Number extendAttrId = (Number)actionEvent.getComponent().getAttributes().get("extendAttrId");
        this.setExpressionValue("#{pageFlowScope.extendAttrId}", extendAttrId);
        this.showPopup(this.getDeletePopup().getClientId());
    }

    public void deleteDialogListener(DialogEvent dialogEvent) {
        Number extendAttrId = (Number)this.resolveExpression("#{pageFlowScope.extendAttrId}");
        String result = this.getAdminAM().deleteExtendAttr(extendAttrId);
        if ("S".equals(result)) {
            this.showMessage("删除成功");
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
        this.findIterator("CmsExtendAttrVOIterator").executeQuery();
    }

    public void newOrEditReturnListener(ReturnEvent returnEvent) {
        String saveFlag = (String)returnEvent.getReturnParameters().get("saveFlag");
        if ("Y".equals(saveFlag)) {
            String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
            if ("CREATE".equals(operType)) {
                this.showMessage("新增成功");
            } else {
                this.showMessage("编辑成功");
            }
            this.findIterator("CmsExtendAttrVOIterator").executeQuery();
        }
    }

    public String editExtendAttrValueListActionListener() {
        Number extendAttrId = (Number)this.resolveExpression("#{pageFlowScope.extendAttrId}");
        this.getAdminAM().queryExtendAttrValueList(extendAttrId);
        return "toExtendAttrValueList";
    }

    public void newOrEditValueListReturnListener(ReturnEvent returnEvent) {
        String saveFlag = (String)returnEvent.getReturnParameters().get("saveFlag");
        if ("Y".equals(saveFlag)) {
            String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
            if ("CREATE".equals(operType)) {
                this.showMessage("新增成功");
            } else {
                this.showMessage("编辑成功");
            }
            this.findIterator("CmsExtendValueListVOIterator").executeQuery();
        }
    }

    public void deleteValueListActionListener(ActionEvent actionEvent) {
        Number listId = (Number)actionEvent.getComponent().getAttributes().get("listId");
        this.setExpressionValue("#{pageFlowScope.listId}", listId);
        this.showPopup(this.getDeleteValueListPopup().getClientId());
    }

    public void deleteValueListDialogListener(DialogEvent dialogEvent) {
        Number listId = (Number)this.resolveExpression("#{pageFlowScope.listId}");
        String result = this.getAdminAM().deleteExtendAttrValueList(listId);
        if ("S".equals(result)) {
            this.showMessage("删除成功");
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
        this.findIterator("CmsExtendValueListVOIterator").executeQuery();
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void setDeleteValueListPopup(RichPopup deleteValueListPopup) {
        this.deleteValueListPopup = deleteValueListPopup;
    }

    public RichPopup getDeleteValueListPopup() {
        return deleteValueListPopup;
    }
}
