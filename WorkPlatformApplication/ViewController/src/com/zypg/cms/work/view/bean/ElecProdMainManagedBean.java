package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.model.viewobject.elecprod.query.CmsElecProdQVORowImpl;
import com.zypg.cms.work.model.viewobject.elecprod.query.CmsElecProdVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class ElecProdMainManagedBean extends CustomManagedBean {
    private List<List<Map<String, Object>>> elecProdCoverList = new ArrayList<List<Map<String, Object>>>();
    CustomTableModel<CmsElecProdVORowImpl> tableModel =
        new CustomTableModel<CmsElecProdVORowImpl>("WorkAMDataControl", "ElecProdAM", "CmsElecProdVO", 20,
                                                   "findElecProdVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public ElecProdMainManagedBean() {

    }

    public void initElecProd() {
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
        commonRenderer.resetStatusSession();
        generateElecProdCoverList();
    }

    public void generateElecProdCoverList() {
        int i = 5;
        List<CmsElecProdVORowImpl> model = tableModel.getModel();
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        for (Row row : model) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("elecProdId", row.getAttribute("ElecProdId"));
            String elecProdName = (String)row.getAttribute("ElecProdName");
            if (elecProdName != null) {
                map.put("elecProdName",
                        elecProdName.length() > 10 ? elecProdName.substring(0, 10) + "..." : elecProdName);
            } else {
                elecProdName = "";
                map.put("elecProdName", elecProdName);
            }
            map.put("ISBN", row.getAttribute("Isbn"));
            map.put("docId", row.getAttribute("DocId"));
            map.put("checked", row.getAttribute("Checked"));
            map.put("thumbnailUrl", row.getAttribute("ThumbnailUrl"));
            map.put("syncFlag", row.getAttribute("SyncFlag"));
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
        this.setElecProdCoverList(list);
    }

    public void elecProdImageDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)map.get("docId");

        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/elecprod/inline/relatedElecProdView.jsf?docId=" + docId;
        this.openWindow(url);
    }

    public void elecProdTableDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row = (Row)list.get(index);
            Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url =
                request.getContextPath() + "/faces/work/elecprod/inline/relatedElecProdView.jsf?docId=" + docId;
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
        this.generateElecProdCoverList();
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

        this.generateElecProdCoverList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void setElecProdCoverList(List<List<Map<String, Object>>> elecProdList) {
        this.elecProdCoverList = elecProdList;
    }

    public List<List<Map<String, Object>>> getElecProdCoverList() {
        if (elecProdCoverList.size() == 0) {
            generateElecProdCoverList();
        }
        return elecProdCoverList;
    }

    public void reFileManageDialogListener(DialogEvent dialogEvent) {
        commonRenderer.reFileManageAction();
        this.refreshCoverAction();
    }

    public void setTableModel(CustomTableModel<CmsElecProdVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsElecProdVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        this.generateElecProdCoverList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            currSelectedList.clear();
            this.refreshUIComponent(this.getButtonPanelGroupLayout());
        }
    }

    /**
     * 到指定页
     **/
    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        commonRenderer.goPageIndex(valueChangeEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateElecProdCoverList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public String refreshCoverWhenSycnAction() {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            for (String key : currSelectedList) {
                String docId = key.substring(key.lastIndexOf("_") + 1);
                try {
                    String docName = CommonUtil.getDocNameByDocId(new Number(docId));
                    String path = "/" + CommonUtil.getCompName() + "/" + CommonUtil.getLibName() + "/" + docName;
                    CommonUtil.initFtpConn();
                    FolderManager fm = new FolderManager();
                    if (fm.exists(path)) {
                        CommonUtil.getCommonAM().startSync(docId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return refreshCoverAction();
    }

    public String refreshCoverAction() {
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateElecProdCoverList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        return null;
    }

    public void searchActionListenser(ActionEvent actionEvent) {
        commonRenderer.searchActionListenser(actionEvent);
        this.refreshCoverAction();
    }

    public void queryWhenPressEnterListener(ClientEvent clientEvent) {
        commonRenderer.queryWhenPressEnterListener(clientEvent);
        this.refreshCoverAction();
    }

    public void rollbackReturnListener(ReturnEvent returnEvent) {
        commonRenderer.rollbackReturnListener(returnEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateElecProdCoverList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void deleteDocDialogActionListener(DialogEvent dialogEvent) {
        commonRenderer.deleteDocDialogActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateElecProdCoverList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void destroyDocActionListener(DialogEvent dialogEvent) {
        commonRenderer.destroyDocActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateElecProdCoverList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void switchStatusActionListener(ActionEvent actionEvent) {
        commonRenderer.switchStatusActionListener(actionEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateElecProdCoverList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
    }

    public void complexRearchReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            CmsElecProdQVORowImpl queryRow =
                (CmsElecProdQVORowImpl)CommonUtil.getElecProdAM().getCmsElecProdQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getElecProdAM().getCmsElecProdQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
            this.refreshCoverAction();
        }
    }
}
