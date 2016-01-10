package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.entry.query.CmsWorksEntryVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class WorksEntryMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsWorksEntryVORowImpl> tableModel =
        new CustomTableModel<CmsWorksEntryVORowImpl>("WorkAMDataControl", "EntryAM", "CmsWorksEntryVO", 20,
                                                     "findWorksEntryVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichCommandButton batchDownloadButton;
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public WorksEntryMainManagedBean() {

    }

    public void initWorksEntry() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!"Y".equals(this.resolveExpression("#{pageFlowScope.deleteFlag}"))) {
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        commonRenderer.resetStatusSession();
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        initWorksEntry();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void tableDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row = (Row)list.get(index);
            oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)row.getAttribute("DocId");
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url =
                request.getContextPath() + "/faces/work/entry/inline/relatedWorksEntryView.jsf?docId=" + docId;
            this.openWindow(url);
        }
    }

    public void downloadEntryActionListener(ActionEvent actionEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        this.setExpressionValue("#{pageFlowScope.downloadFileName}", sdf.format(new Date()) + "＿论著条目.zip");
        this.executeCompomentAction(this.getBatchDownloadButton().getClientId());
    }

    public void setTableModel(CustomTableModel<CmsWorksEntryVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsWorksEntryVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setBatchDownloadButton(RichCommandButton batchDownloadButton) {
        this.batchDownloadButton = batchDownloadButton;
    }

    public RichCommandButton getBatchDownloadButton() {
        return batchDownloadButton;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }
}
