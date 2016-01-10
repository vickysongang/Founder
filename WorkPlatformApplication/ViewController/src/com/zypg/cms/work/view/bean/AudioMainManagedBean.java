package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.foldermanager.model.Folder;
import com.zypg.cms.foldermanager.model.FolderConstants;
import com.zypg.cms.foldermanager.model.FolderFactory;
import com.zypg.cms.foldermanager.model.FtpFile;
import com.zypg.cms.foldermanager.model.LoadBatchUtil;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioQVORowImpl;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioVORowImpl;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;

import com.zypg.cms.work.view.util.UCMUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class AudioMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsAudioVORowImpl> tableModel =
        new CustomTableModel<CmsAudioVORowImpl>("WorkAMDataControl", "AudioAM", "CmsAudioVO", 20, "findAudioVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public AudioMainManagedBean() {
    }

    public void initAudioMain() {
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

    public void editAudioReturnListener(ReturnEvent returnEvent) {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
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
            String url = request.getContextPath() + "/faces/work/audio/inline/relatedAudioView.jsf?docId=" + docId;
            this.openWindow(url);
        }
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        initAudioMain();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void complexRearchReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            CmsAudioQVORowImpl queryRow = (CmsAudioQVORowImpl)CommonUtil.getAudioAM().getCmsAudioQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getAudioAM().getCmsAudioQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
        }
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setTableModel(CustomTableModel<CmsAudioVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsAudioVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }
}
