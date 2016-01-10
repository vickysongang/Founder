package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.ReturnEvent;

import oracle.jbo.domain.Number;

public class SyncCancelManagedBean extends CustomManagedBean {
    private RichPopup syncCancelPopup;
    private RichSelectBooleanCheckbox selectAllCheckBox;
    private RichTable syncCancelTable;
    private RichPopup batchSyncCancelPopup;
    private RichInputText uniqueResNameInputText;
    private RichInputText statusInputText;
    private RichInputText libNameInputText;
    private RichInputText createrNameInputText;
    private RichInputText creationDateInputText;

    public SyncCancelManagedBean() {
    }

    public void initSyncCancel() {
        this.getAdminAM().findComp4Common((String)this.resolveExpression("#{pageFlowScope.compStr}"));
        String compCode = this.getAdminAM().getFirstCompCode();
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.getAdminAM().queryDoc4SyncCancel(compCode);
    }

    public void switchCompActionListener(ActionEvent actionEvent) {
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.refreshUIComponent(actionEvent.getComponent().getParent());
        this.getAdminAM().queryDoc4SyncCancel(compCode);
    }

    public void syncCancelActionListener(ActionEvent actionEvent) {
        Number docId = (Number)actionEvent.getComponent().getAttributes().get("docId");
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        this.setExpressionValue("#{pageFlowScope.docId}", docId);
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        if (docId != null) {
            this.showPopup(this.getSyncCancelPopup().getClientId());
        }
    }

    public void syncCancelDialogListener(DialogEvent dialogEvent) {
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        if (docId != null) {
            String result = this.getAdminAM().cancelSync(docId);
            if ("S".equals(result)) {
                String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
                this.showMessage("取消成功");
                this.getAdminAM().queryDoc4SyncCancel(compCode);
            } else {
                this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void selectAllAction(ClientEvent clientEvent) {
        Boolean isSelectAllChecked = (Boolean)clientEvent.getParameters().get("params");
        System.out.println("isSelectAllCheckedisSelectAllChecked:" + isSelectAllChecked);
        DCIteratorBinding dcIterator = this.findIterator("CmsSyncCancelVOIterator");
        dcIterator.setRangeSize(-1);
        Row[] rows = dcIterator.getAllRowsInRange();
        for (Row row : rows) {
            System.out.println("sss");
            row.setAttribute("Checked", isSelectAllChecked);
        }
        this.refreshUIComponent(this.getSyncCancelTable());
    }

    public void selectOneAction(ClientEvent clientEvent) {
        Boolean isSelectChecked = (Boolean)clientEvent.getParameters().get("params");
        if (!isSelectChecked) {
            this.getSelectAllCheckBox().setSelected(isSelectChecked);
            this.refreshUIComponent(this.getSelectAllCheckBox());
        }
    }

    public void batchSyncCancelActionListener(ActionEvent actionEvent) {
        this.showPopup(this.getBatchSyncCancelPopup().getClientId());
    }

    public void batchSyncCancelDialogListener(DialogEvent dialogEvent) {
        DCIteratorBinding dcIterator = this.findIterator("CmsSyncCancelVOIterator");
        dcIterator.setRangeSize(-1);
        Row[] rows = dcIterator.getAllRowsInRange();
        int count = 0;
        for (Row row : rows) {
            if (row.getAttribute("Checked") != null && (Boolean)row.getAttribute("Checked")) {
                String result = this.getAdminAM().cancelSync((Number)row.getAttribute("DocId"));
                if (!"S".equals(result)) {
                    count++;
                }
            }
        }
        if (count == 0) {
            this.showMessage("取消成功");
        }
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        this.getAdminAM().queryDoc4SyncCancel(compCode);
    }
    
    public String querySyncCanelAction() {
        Object uniqueResName = this.getUniqueResNameInputText().getValue();
        Object libName = this.getLibNameInputText().getValue();
        Object status = this.getStatusInputText().getValue();
        Object creater = this.getCreaterNameInputText().getValue();
        Object creationDate = this.getCreationDateInputText().getValue();
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        
        
        BindingContainer bindings = getBindings();
        OperationBinding saveBusinessLog = bindings.getOperationBinding("querySyncCanel");
        Map map = saveBusinessLog.getParamsMap();
        map.put("uniqueResName", uniqueResName);
        map.put("libName", libName);
        map.put("status", status);
        map.put("creater", creater);
        map.put("creationDate", creationDate);
        map.put("compCode", compCode);

        String result = (String)saveBusinessLog.execute();
        // Add event code here...
        return null;
    }
    public String resetQueryAction() {
        this.getCreaterNameInputText().setValue(null);
        this.getCreationDateInputText().setValue(null);
        this.getUniqueResNameInputText().setValue(null);
        this.getLibNameInputText().setValue(null);
        this.getStatusInputText().setValue(null);
        
        // Add event code here...
        return null;
    }
    
    public BindingContainer getBindings() {
            return BindingContext.getCurrent().getCurrentBindingsEntry();
        }

    public void setSyncCancelTable(RichTable syncCancelTable) {
        this.syncCancelTable = syncCancelTable;
    }

    public RichTable getSyncCancelTable() {
        return syncCancelTable;
    }

    public void setSyncCancelPopup(RichPopup syncCancelPopup) {
        this.syncCancelPopup = syncCancelPopup;
    }

    public RichPopup getSyncCancelPopup() {
        return syncCancelPopup;
    }

    public void setSelectAllCheckBox(RichSelectBooleanCheckbox selectAllCheckBox) {
        this.selectAllCheckBox = selectAllCheckBox;
    }

    public RichSelectBooleanCheckbox getSelectAllCheckBox() {
        return selectAllCheckBox;
    }

    public void setBatchSyncCancelPopup(RichPopup batchSyncCancelPopup) {
        this.batchSyncCancelPopup = batchSyncCancelPopup;
    }

    public RichPopup getBatchSyncCancelPopup() {
        return batchSyncCancelPopup;
    }

    public void setUniqueResNameInputText(RichInputText uniqueResNameInputText) {
        this.uniqueResNameInputText = uniqueResNameInputText;
    }

    public RichInputText getUniqueResNameInputText() {
        return uniqueResNameInputText;
    }

    public void setStatusInputText(RichInputText statusInputText) {
        this.statusInputText = statusInputText;
    }

    public RichInputText getStatusInputText() {
        return statusInputText;
    }

    public void setLibNameInputText(RichInputText libNameInputText) {
        this.libNameInputText = libNameInputText;
    }

    public RichInputText getLibNameInputText() {
        return libNameInputText;
    }

    public void setCreaterNameInputText(RichInputText createrNameInputText) {
        this.createrNameInputText = createrNameInputText;
    }

    public RichInputText getCreaterNameInputText() {
        return createrNameInputText;
    }

    public void setCreationDateInputText(RichInputText creationDateInputText) {
        this.creationDateInputText = creationDateInputText;
    }

    public RichInputText getCreationDateInputText() {
        return creationDateInputText;
    }



}
