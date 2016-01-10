package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.model.viewobject.query.CmsFirstLevelLibVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsRoleLibVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsSecondLevelLibVORowImpl;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;

import org.apache.myfaces.trinidad.component.UIXCollection;
import org.apache.myfaces.trinidad.component.UIXEditableValue;
import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class RoleAuth4pbBean extends CustomManagedBean{
    private RichRegion resDownloadSetRegionBinding;

    public RoleAuth4pbBean() {
    }

    public void displayAuthDiscloseListener(DisclosureEvent disclosureEvent) {
        // Add event code here...
        this.findOperation("Rollback").execute();
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

    public void resDownloadDiscloseListener(DisclosureEvent disclosureEvent) {
        this.findOperation("Rollback").execute();
        this.findOperation("queryRoleLib4WorkPlatform").execute();
    }
    
        private void resetStamp(UIComponent comp){
            if(comp instanceof UIXEditableValue){
                    UIXEditableValue editable = (UIXEditableValue)comp;
                    editable.resetValue();
                }
            if(comp instanceof UIXCollection){
                    UIXCollection collection = (UIXCollection)comp;
                    collection.resetStampState();
                }
            
             Iterator<UIComponent> comps =  comp.getFacetsAndChildren();
             if(comps!=null)
             while(comps.hasNext()){
                    UIComponent ccomp = comps.next();
                    resetStamp(ccomp);
                 }
        }

    public void selectRoleLibServerListener(ClientEvent clientEvent) {
        FacesCtrlHierNodeBinding node =
            (FacesCtrlHierNodeBinding)clientEvent.getComponent().getAttributes().get("row");
        CmsRoleLibVORowImpl libRow = (CmsRoleLibVORowImpl)node.getRow();
        this.setExpressionValue("#{pageFlowScope.currentLibCode}", libRow.getLibCode());
        //先回滚
        this.findOperation("Rollback").execute();
//        OperationBinding ob =
//            BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("initResDownloadVO");
//        ob.getParamsMap().put("libCode", libRow.getLibCode());
//        ob.getParamsMap().put("roleId", this.resolveExpression("#{pageFlowScope.roleId}"));
//        ob.execute();
       
//        resetStamp(this.getResDownloadSetRegionBinding());
       
        this.setExpressionValue("#{pageFlowScope.currentLibCode}", libRow.getLibCode());
        AdfFacesContext.getCurrentInstance().addPartialTarget(clientEvent.getComponent().getParent());
        this.getResDownloadSetRegionBinding().refresh(FacesContext.getCurrentInstance());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getResDownloadSetRegionBinding());
    }

    public void setResDownloadSetRegionBinding(RichRegion resDownloadSetRegionBinding) {
        this.resDownloadSetRegionBinding = resDownloadSetRegionBinding;
    }

    public RichRegion getResDownloadSetRegionBinding() {
        return resDownloadSetRegionBinding;
    }
}
