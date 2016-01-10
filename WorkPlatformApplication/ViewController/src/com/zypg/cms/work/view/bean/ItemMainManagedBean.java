package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.copyright.query.Cms3rdAuthorizationItemVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;


public class ItemMainManagedBean extends CustomManagedBean {

    private CustomTableModel<Cms3rdAuthorizationItemVORowImpl> tableModel =
        new CustomTableModel<Cms3rdAuthorizationItemVORowImpl>("WorkAMDataControl", "CopyrightAM",
                                                               "Cms3rdAuthorizationItemVO", 20, "findOrderItemsVC");
    private CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGridLayout panelGridLayout;
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichInputText searchInput;
    private RichTree categoryTree;

    public ItemMainManagedBean() {
    }

    public void initItem() {
        CommonUtil.getCopyrightAM().findCategory4Detail((String)this.resolveExpression("#{pageFlowScope.compCode}"),
                                                        CommonUtil.getLibCode());
        commonRenderer = new CommonRenderer(tableModel);
        filterTableModel(null);
    }

    public void filterTableModel(String timeRange) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        if (timeRange != null) {
            String[] items = timeRange.split(",");
            try {
                Number startNum = new Number(items[0]);
                Number endNum = new Number(items[1]);
                paramMap.put("bvStartNum", startNum);
                paramMap.put("bvEndNum", endNum);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        System.out.println("initAuthor=" + paramMap);
    }

    public void categoryTreeSelectionListener(SelectionEvent selectionEvent) {
        RowKeySet rks = categoryTree.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)categoryTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            String timeRange = (String)nodeBinding.getAttribute("TimeRange");
            this.setExpressionValue("#{pageFlowScope.timeRange}", timeRange);
            filterTableModel(timeRange);
        }
    }

    public void tableDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        CollectionModel tableModel = (CollectionModel)map.get("value");
        JUCtrlHierBinding adfTableBinding = (JUCtrlHierBinding)tableModel.getWrappedData();
        DCIteratorBinding it = adfTableBinding.getDCIteratorBinding();
        Row row = it.getCurrentRow();
        oracle.jbo.domain.Number itemId = (oracle.jbo.domain.Number)row.getAttribute("ItemId");
        System.out.println("itemId=" + itemId);
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/copyright/inline/copyrightItemView.jsf?itemId=" + itemId;
        this.openWindow(url);

    }

    public void setTableModel(CustomTableModel<Cms3rdAuthorizationItemVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<Cms3rdAuthorizationItemVORowImpl> getTableModel() {
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
        initItem();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void importReturnListener(ReturnEvent returnEvent) {
        String operation = (String)returnEvent.getReturnParameters().get("operation");
        if ("IMPORT".equals(operation)) {
            this.findIterator("Cms3rdAuthorizationItemVOIterator").executeQuery();
            this.addFormattedMessage(null, "导入成功", FacesMessage.SEVERITY_INFO);
        }
    }

    public void setCategoryTree(RichTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    public RichTree getCategoryTree() {
        return categoryTree;
    }
}
