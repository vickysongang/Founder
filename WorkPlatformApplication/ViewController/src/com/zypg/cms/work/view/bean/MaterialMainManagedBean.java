package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureQVORowImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;


public class MaterialMainManagedBean extends CustomManagedBean {
    private List<List<Map<String, Object>>> materialFigureList = new ArrayList<List<Map<String, Object>>>();
    CustomTableModel<CmsMaterialFigureVORowImpl> tableModel =
        new CustomTableModel<CmsMaterialFigureVORowImpl>("WorkAMDataControl", "MaterialFigureAM",
                                                         "CmsMaterialFigureVO", 20, "findMaterialFigureVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichPopup deleteFigureGroupPopup;
    private RichCommandImageLink returnLink;
    private RichPanelStretchLayout coverPanelStretchLayout;

    public MaterialMainManagedBean() {
    }

    public void initMaterial() {
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
        paramMap.put("bvGroupDetailFlag", "N");
        paramMap.put("bvGroupId", null);
        tableModel.setParamMap(paramMap);
        this.setExpressionValue("#{pageFlowScope.groupFlag}", "N");
        commonRenderer.resetStatusSession();
        this.generateMaterialList();
    }


    public void generateMaterialList() {
        int i = 5;
        List<CmsMaterialFigureVORowImpl> model = tableModel.getModel();
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        for (Row row : model) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("figureId", row.getAttribute("FigureId"));
            String figureName = (String)row.getAttribute("FigureName");
            if (figureName != null) {
                map.put("figureName", figureName.length() > 10 ? figureName.substring(0, 10) + "..." : figureName);
            } else {
                figureName = "";
                map.put("figureName", figureName);
            }
            map.put("figureDimension", row.getAttribute("FigureDimension"));
            map.put("docId", row.getAttribute("DocId"));
            map.put("checked", row.getAttribute("Checked"));
            map.put("thumbnailUrl", row.getAttribute("ThumbnailUrl"));
            map.put("groupFlag", row.getAttribute("GroupFlag"));
            map.put("groupId", row.getAttribute("GroupId"));
            map.put("figureCount", row.getAttribute("FigureCount"));
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

        this.setMaterialFigureList(list);
    }

    public void materialImageDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Number docId = (Number)map.get("docId");
        Number groupId = (Number)map.get("groupId");
        Number figureCount = (Number)map.get("figureCount");
        if (groupId != null && figureCount != null && figureCount.intValue() > 1) {
            commonRenderer = new CommonRenderer(tableModel);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
            paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
            paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
            paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
            paramMap.put("bvGroupDetailFlag", "Y");
            paramMap.put("bvGroupId", groupId);
            tableModel.setParamMap(paramMap);
            this.setExpressionValue("#{pageFlowScope.groupFlag}", "Y");
            this.refreshUIComponent(this.getCoverPanelStretchLayout());
            this.refreshUIComponent(this.getReturnLink());
        } else if (groupId != null && (figureCount == null || figureCount.intValue() == 0)) {
            this.setExpressionValue("#{pageFlowScope.groupFlag}", "Y");
            this.setExpressionValue("#{pageFlowScope.docId}", docId);
            this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url =
                request.getContextPath() + "/faces/work/material/inline/relatedMaterialView.jsf?docId=" + docId;
            this.openWindow(url);
        } else {
            this.setExpressionValue("#{pageFlowScope.groupFlag}", "N");
            this.setExpressionValue("#{pageFlowScope.docId}", docId);
            this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url =
                request.getContextPath() + "/faces/work/material/inline/relatedMaterialView.jsf?docId=" + docId;
            this.openWindow(url);
        }
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
        this.generateMaterialList();
        this.refreshUIComponent(this.getCoverPanelStretchLayout());
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

        this.generateMaterialList();
        this.refreshUIComponent(this.getCoverPanelStretchLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void setMaterialFigureList(List<List<Map<String, Object>>> materialFigureList) {
        this.materialFigureList = materialFigureList;
    }

    public List<List<Map<String, Object>>> getMaterialFigureList() {
        if (materialFigureList.size() == 0) {
            this.generateMaterialList();
        }
        return materialFigureList;
    }

    public void setTableModel(CustomTableModel<CmsMaterialFigureVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsMaterialFigureVORowImpl> getTableModel() {
        return tableModel;
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

    public void refreshActionListener(ActionEvent actionEvent) {
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", new ArrayList<String>());
        this.generateMaterialList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    /**
     * 到指定页
     **/
    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        commonRenderer.goPageIndex(valueChangeEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateMaterialList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public String refreshCoverAction() {
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateMaterialList();
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
            CmsMaterialFigureQVORowImpl queryRow =
                (CmsMaterialFigureQVORowImpl)CommonUtil.getMaterialFigureAM().getCmsMaterialFigureQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getMaterialFigureAM().getCmsMaterialFigureQVO().getAttributeDefs()) {
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
            this.generateMaterialList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void deleteDocDialogActionListener(DialogEvent dialogEvent) {
        commonRenderer.deleteDocDialogActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateMaterialList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void destroyDocActionListener(DialogEvent dialogEvent) {
        commonRenderer.destroyDocActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateMaterialList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void deleteFigureGroupActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (currSelectedList.size() > 0) {
                String key = currSelectedList.get(0);
                Integer docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
                this.setExpressionValue("#{pageFlowScope.docId}", new Number(docId));
            } else {
                this.setExpressionValue("#{pageFlowScope.docId}", new Number(-99));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.showPopup(this.getDeleteFigureGroupPopup().getClientId());
    }

    public void setDeleteFigureGroupPopup(RichPopup deleteFigureGroupPopup) {
        this.deleteFigureGroupPopup = deleteFigureGroupPopup;
    }

    public RichPopup getDeleteFigureGroupPopup() {
        return deleteFigureGroupPopup;
    }

    public void deleteFigureGroupDialogListener(DialogEvent dialogEvent) {
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
        String groupFlag = (String)this.resolveExpression("#{pageFlowScope.groupFlag}");
        String result = CommonUtil.getFigureGroupAM().deleteFigureGroup(docId, groupFlag);
        if ("Y".equals(result)) {
            CommonUtil.getFigureGroupAM().getDBTransaction().commit();
            this.showMessage("拆分成功");
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
    }

    public void returnActionListener(ActionEvent actionEvent) {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        paramMap.put("bvGroupDetailFlag", "N");
        paramMap.put("bvGroupId", null);
        tableModel.setParamMap(paramMap);
        this.setExpressionValue("#{pageFlowScope.groupFlag}", "N");
        this.refreshUIComponent(this.getCoverPanelStretchLayout());
    }

    public void setReturnLink(RichCommandImageLink returnLink) {
        this.returnLink = returnLink;
    }

    public RichCommandImageLink getReturnLink() {
        return returnLink;
    }

    public void setCoverPanelStretchLayout(RichPanelStretchLayout coverPanelStretchLayout) {
        this.coverPanelStretchLayout = coverPanelStretchLayout;
    }

    public RichPanelStretchLayout getCoverPanelStretchLayout() {
        return coverPanelStretchLayout;
    }

    public void switchStatusActionListener(ActionEvent actionEvent) {
        commonRenderer.switchStatusActionListener(actionEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateMaterialList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
    }
}
