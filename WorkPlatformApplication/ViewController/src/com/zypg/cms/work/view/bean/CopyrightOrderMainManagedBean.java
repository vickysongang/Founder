package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.copyright.query.Cms3rdAuthorizationOrderVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class CopyrightOrderMainManagedBean extends CustomManagedBean {
    CustomTableModel<Cms3rdAuthorizationOrderVORowImpl> tableModel =
        new CustomTableModel<Cms3rdAuthorizationOrderVORowImpl>("WorkAMDataControl", "CopyrightAM", "Cms3rdAuthorizationOrderVO", 20,
                                               "findOrderVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGridLayout panelGridLayout;
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichInputText searchInput;

    public CopyrightOrderMainManagedBean() {
    }

    public void initOrder() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        if (!this.resolveExpression("#{pageFlowScope.deleteFlag}").equals("Y")) {
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
    }
    
    public void tableDblClick(ClientEvent clientEvent) {
        commonRenderer.tableDblSelectAction(clientEvent);
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row = (Row)list.get(index);
            Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
            System.out.println("order docId="+docId);
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url =
                request.getContextPath() + "/faces/work/copyright/inline/copyrightOrderView.jsf?docId=" +
                docId;
            this.openWindow(url);
        }
    }

    public void setTableModel(CustomTableModel<Cms3rdAuthorizationOrderVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<Cms3rdAuthorizationOrderVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setPanelGridLayout(RichPanelGridLayout panelGridLayout) {
        this.panelGridLayout = panelGridLayout;
    }

    public RichPanelGridLayout getPanelGridLayout() {
        return panelGridLayout;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setSearchInput(RichInputText searchInput) {
        this.searchInput = searchInput;
    }

    public RichInputText getSearchInput() {
        return searchInput;
    }

    public void refreshActionListener(ActionEvent actionEvent) {
       this.initOrder();
       this.refreshUIComponent(this.buttonPanelGroupLayout);
    }
}
