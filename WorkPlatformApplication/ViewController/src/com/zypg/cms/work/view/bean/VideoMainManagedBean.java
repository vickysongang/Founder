package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.video.query.CmsVideoQVORowImpl;
import com.zypg.cms.work.model.viewobject.video.query.CmsVideoVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class VideoMainManagedBean extends CustomManagedBean {
    private List<List<Map<String, Object>>> videoList = new ArrayList<List<Map<String, Object>>>();
    CustomTableModel<CmsVideoVORowImpl> tableModel =
        new CustomTableModel<CmsVideoVORowImpl>("WorkAMDataControl", "VideoAM", "CmsVideoVO", 20, "findVideoVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public VideoMainManagedBean() {
    
    }

    public void initVideo() {
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
        this.generateList();
        commonRenderer.resetStatusSession();
    }

    public void generateList() {
        int i = 5;
        List<CmsVideoVORowImpl> model = tableModel.getModel();
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        for (Row row : model) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("videoId", row.getAttribute("VideoId"));
            String fileName = (String)row.getAttribute("FileName");
            if (fileName != null) {
                map.put("fileName", fileName.length() > 15 ? fileName.substring(0, 15) + "..." : fileName);
            } else {
                fileName = "";
                map.put("fileName", fileName);
            }
            map.put("fileTime", row.getAttribute("FileTime"));
            map.put("docId", row.getAttribute("DocId"));
            map.put("checked", row.getAttribute("Checked"));
            map.put("thumbnailUrl", row.getAttribute("ThumbnailUrl"));
            l.add(map);
            if (i-- == 1) {
                i = 5;
                list.add(l);
                l = new ArrayList<Map<String, Object>>();
            }
        }
        //补空
        for (int j = 1; j <= i % 5; j++) {
            l.add(new HashMap<String, Object>());
        }
        list.add(l);
        this.setVideoList(list);
    }

    public void videoImageDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Number docId = (Number)map.get("docId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/video/inline/relatedVideoView.jsf?docId=" + docId;
        this.openWindow(url);
    }

    public void imageSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Boolean isSelectChecked = (Boolean)map.get("checked");
        Integer docId = ((oracle.jbo.domain.Number)map.get("docId")).intValue();
        String key = CommonUtil.getCurrStatusCode() + "_" + docId;
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (!isSelectChecked) {
            if (currSelectedList.size() > 1) {
                currSelectedList.clear();
                currSelectedList.add(key);
            } else {
                currSelectedList.clear();
            }
        } else {
            currSelectedList.clear();
            currSelectedList.add(key);
        }
        //代表当前选中
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "O");
        this.generateList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void imageDblSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Boolean isSelectChecked = (Boolean)map.get("checked");

        Integer docId = ((oracle.jbo.domain.Number)map.get("docId")).intValue();
        String key = CommonUtil.getCurrStatusCode() + "_" + docId;
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (!isSelectChecked) {
            if (currSelectedList.contains(key)) {
                currSelectedList.remove(key);
            }
        } else {
            if (!currSelectedList.contains(key)) {
                currSelectedList.add(key);
            }
        }
        //代表当前选中
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "O");

        this.generateList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void videoTableDblClick(ClientEvent clientEvent) {
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
            String url = request.getContextPath() + "/faces/work/video/inline/relatedVideoView.jsf?docId=" + docId;
            this.openWindow(url);
        }
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setTableModel(CustomTableModel<CmsVideoVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsVideoVORowImpl> getTableModel() {
        return tableModel;
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", new ArrayList<String>());
        this.generateList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }


    /**
     * 到指定页
     **/
    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        commonRenderer.goPageIndex(valueChangeEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public String refreshCoverAction() {
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        return null;
    }

    public void searchActionListenser(ActionEvent actionEvent) {
        commonRenderer.searchActionListenser(actionEvent);
        this.refreshCoverAction();
    }

    public void complexRearchReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            CmsVideoQVORowImpl queryRow = (CmsVideoQVORowImpl)CommonUtil.getVideoAM().getCmsVideoQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getVideoAM().getCmsVideoQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
            this.refreshCoverAction();
        }
    }

    public void queryWhenPressEnterListener(ClientEvent clientEvent) {
        commonRenderer.queryWhenPressEnterListener(clientEvent);
        this.refreshCoverAction();
    }

    public void rollbackReturnListener(ReturnEvent returnEvent) {
        commonRenderer.rollbackReturnListener(returnEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void deleteDocDialogActionListener(DialogEvent dialogEvent) {
        commonRenderer.deleteDocDialogActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void destroyDocActionListener(DialogEvent dialogEvent) {
        commonRenderer.destroyDocActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public List<List<Map<String, Object>>> getVideoList() {
        if (videoList.size() == 0) {
            this.generateList();
        }
        return videoList;
    }

    public void setVideoList(List<List<Map<String, Object>>> videoList) {
        this.videoList = videoList;
    }

    public void switchStatusActionListener(ActionEvent actionEvent) {
        commonRenderer.switchStatusActionListener(actionEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
    }
}
