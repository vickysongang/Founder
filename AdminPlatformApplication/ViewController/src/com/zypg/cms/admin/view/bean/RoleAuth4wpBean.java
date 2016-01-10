package com.zypg.cms.admin.view.bean;


import com.zypg.cms.admin.model.viewobject.query.CmsFirstLevelLibVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsRoleLibVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsSecondLevelLibVORowImpl;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;
import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;

import org.apache.myfaces.trinidad.event.DisclosureEvent;


public class RoleAuth4wpBean extends CustomManagedBean {
    private RichRegion roleResSetRegionBinding;

    public RoleAuth4wpBean() {
    }

    public void displayAuthSelectChangeListener(ValueChangeEvent valueChangeEvent) {
        FacesCtrlHierNodeBinding node =
            (FacesCtrlHierNodeBinding)valueChangeEvent.getComponent().getAttributes().get("row");
        CmsFirstLevelLibVORowImpl firstRw = (CmsFirstLevelLibVORowImpl)node.getRow();
        RowIterator ri = firstRw.getCmsSecondLevelLibVO();
        for (Row rw1 : ri.getAllRowsInRange()) {
            CmsSecondLevelLibVORowImpl secondRw = (CmsSecondLevelLibVORowImpl)rw1;
            secondRw.setIsSelected((Boolean)valueChangeEvent.getNewValue());
        }
    }

    public void saveDisplayAuthActionListener(ActionEvent actionEvent) {
        this.findOperation("saveDisplayAuth").execute();
        this.showMessage("保存成功！");
    }

    public void displayAuthDiscloseListener(DisclosureEvent disclosureEvent) {
        this.findOperation("Rollback").execute();
    }

    public void operationAuthDiscloseListener(DisclosureEvent disclosureEvent) {
        // 查询lib列表
        this.findOperation("Rollback").execute();
        this.findOperation("queryRoleLib4WorkPlatform").execute();
    }

    public void selectRoleLibServerListener(ClientEvent clientEvent) {
        FacesCtrlHierNodeBinding node =
            (FacesCtrlHierNodeBinding)clientEvent.getComponent().getAttributes().get("row");
        CmsRoleLibVORowImpl libRow = (CmsRoleLibVORowImpl)node.getRow();
        this.setExpressionValue("#{pageFlowScope.currentLibCode}", libRow.getLibCode());
        //先回滚
        this.findOperation("Rollback").execute();
        OperationBinding ob = this.findOperation("queryRes4WP");
        ob.getParamsMap().put("libCode", libRow.getLibCode());
        ob.execute();
        
        OperationBinding isAllSelected = this.findOperation("queryRes4WPIsAllSelected");
        Boolean isAllSelectedStr = (Boolean) isAllSelected.execute();
        System.out.println("isAllSelectedStr:"+isAllSelectedStr);
        
        if(isAllSelectedStr != null && !"".equals(isAllSelectedStr)){
                this.setExpressionValue("#{pageFlowScope.isAllSelected}", isAllSelectedStr);
        }else{
                this.setExpressionValue("#{pageFlowScope.isAllSelected}", false);
            }

        this.setExpressionValue("#{pageFlowScope.currentLibCode}", libRow.getLibCode());

        AdfFacesContext.getCurrentInstance().addPartialTarget(clientEvent.getComponent().getParent());
        this.getRoleResSetRegionBinding().refresh(FacesContext.getCurrentInstance());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getRoleResSetRegionBinding());
    }

    public void setRoleResSetRegionBinding(RichRegion roleResSetRegionBinding) {
        this.roleResSetRegionBinding = roleResSetRegionBinding;
    }

    public RichRegion getRoleResSetRegionBinding() {
        return roleResSetRegionBinding;
    }
}
