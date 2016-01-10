package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.model.viewobject.query.CmsResDownloadVORowImpl;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.component.UIXIterator;
import org.apache.myfaces.trinidad.component.html.HtmlRowLayout;
import org.apache.myfaces.trinidad.component.html.HtmlTableLayout;

public class ResDownloadAuthBean extends CustomManagedBean {
    private UIXIterator downloadIterator;
    private HtmlTableLayout table;
    private RichPanelGroupLayout panelGroupBinding;

    public ResDownloadAuthBean() {
    }

    public void allValueChangelistener(ValueChangeEvent valueChangeEvent) {
        String type = (String)valueChangeEvent.getComponent().getAttributes().get("downloadType");
        System.out.println("allValueChangelistener type---->"+type);
        Boolean value = (Boolean)valueChangeEvent.getNewValue();
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("currentType", type);
        if(value){
                OperationBinding ob =
                    BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("batchSetResDownloadAuth");
                ob.getParamsMap().put("type", type);
                ob.getParamsMap().put("value", value);
                ob.execute();
                
                //设置其他radio组件的值
                if(type.equals("no")){
                       this.setExpressionValue("#{pageFlowScope.isAuthDownload}", false);
                       this.setExpressionValue("#{pageFlowScope.isFreeDownload}", false);
                    }
                
                if(type.equals("auth")){
                       this.setExpressionValue("#{pageFlowScope.isNoDownload}", false);
                       this.setExpressionValue("#{pageFlowScope.isFreeDownload}", false);
                    }
                
                if(type.equals("free")){
                       this.setExpressionValue("#{pageFlowScope.isNoDownload}", false);
                       this.setExpressionValue("#{pageFlowScope.isAuthDownload}", false);
                    }
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.getPanelGroupBinding());
            }
    }

    public void setDownloadIterator(UIXIterator downloadIterator) {
        this.downloadIterator = downloadIterator;
    }

    public UIXIterator getDownloadIterator() {
        return downloadIterator;
    }

    public void saveActionListener(ActionEvent actionEvent) {
        OperationBinding ob =
            BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("saveResDownloadAuth");
        ob.getParamsMap().put("libCode", AdfFacesContext.getCurrentInstance().getPageFlowScope().get("libCode"));
        ob.getParamsMap().put("roleId", AdfFacesContext.getCurrentInstance().getPageFlowScope().get("roleId"));
        String ret = (String)ob.execute();
        if ("S".equals(ret)) {
            this.showMessage("保存成功！");
            
        } else {
            this.showMessage("保存失败！");
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getTable());
    }

    public void setTable(HtmlTableLayout table) {
        this.table = table;
    }

    public HtmlTableLayout getTable() {
        return table;
    }

    public void setPanelGroupBinding(RichPanelGroupLayout panelGroupBinding) {
        this.panelGroupBinding = panelGroupBinding;
    }

    public RichPanelGroupLayout getPanelGroupBinding() {
        return panelGroupBinding;
    }
}
