package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.theme.query.CmsThemeResourceVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import com.zypg.cms.work.view.util.HttpUtil;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class ThemeResourceManagedBean extends CustomManagedBean {
    CustomTableModel<CmsThemeResourceVORowImpl> tableModel =
        new CustomTableModel<CmsThemeResourceVORowImpl>("WorkAMDataControl", "ThemeAM", "CmsThemeResourceVO", 20,
                                                        "findResourceVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichSelectBooleanCheckbox selectAllCheckBox;
    private RichTable resTable;
    private RichPopup deletePopup;
    private RichCommandButton refreshButton;

    public ThemeResourceManagedBean() {
    }

    public void initThemeResource() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String nodeIdStr = (String)this.resolveExpression("#{pageFlowScope.nodeId}");
        System.out.println("nodeIdStr:" + nodeIdStr);
        try {
            oracle.jbo.domain.Number nodeId = new oracle.jbo.domain.Number(nodeIdStr);
            paramMap.put("bvNodeId", nodeId);
            tableModel.setParamMap(paramMap);
            tableModel.getModel();
            String statiticsResult = CommonUtil.getThemeAM().getThemeResourceStatistics(nodeId);
            this.setExpressionValue("#{pageFlowScope.statiticsResult}", statiticsResult);
            System.out.println("tableModel.getParamMap:" + tableModel.getParamMap());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CommonUtil.putStringToSession("Theme_selectAllFlag", "N");
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession("Theme_currSelected");
        if (currSelectedList != null) {
            currSelectedList.clear();
        }
    }


    public void refreshActionListener(ActionEvent actionEvent) {
        initThemeResource();
        this.appendScript("refreshZTree()");
    }

    public void setTableModel(CustomTableModel<CmsThemeResourceVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsThemeResourceVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setSelectAllCheckBox(RichSelectBooleanCheckbox selectAllCheckBox) {
        this.selectAllCheckBox = selectAllCheckBox;
    }

    public RichSelectBooleanCheckbox getSelectAllCheckBox() {
        return selectAllCheckBox;
    }

    public void selectAllAction(ClientEvent clientEvent) {
        List list = (List)resTable.getValue();
        Boolean isSelectAllChecked = (Boolean)clientEvent.getParameters().get("params");
        String prefix = "Theme";
        String selectAllKey = prefix + "_" + "selectAllFlag";
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(prefix + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (isSelectAllChecked) {
            CommonUtil.putStringToSession(selectAllKey, "Y");
            for (int i = 0; i < list.size(); i++) {
                Row row = (Row)list.get(i);
                Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("ResourceId")).intValue();
                String key = prefix + "_" + docId;
                if (!currSelectedList.contains(key)) {
                    currSelectedList.add(key);
                }
            }
        } else {
            CommonUtil.putStringToSession(selectAllKey, "N");
            currSelectedList.clear();
        }
        CommonUtil.putObjectToSession(prefix + "_currSelected", currSelectedList);
    }

    public void selectOneAction(ClientEvent clientEvent) {
        Boolean isSelectChecked = (Boolean)clientEvent.getParameters().get("params");
        Integer docId = ((Double)clientEvent.getParameters().get("docId")).intValue();
        String prefix = "Theme";
        String key = prefix + "_" + docId;
        String selectAllKey = prefix + "_" + "selectAllFlag";
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(prefix + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (!isSelectChecked) {
            this.getSelectAllCheckBox().setSelected(isSelectChecked);
            if (currSelectedList.contains(key)) {
                currSelectedList.remove(key);
            }
        } else {
            this.getSelectAllCheckBox().setSelected(!isSelectChecked);
            if (!currSelectedList.contains(key)) {
                currSelectedList.add(key);
            }
        }
        CommonUtil.putObjectToSession(prefix + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(selectAllKey, "O");
        this.refreshUIComponent(this.getSelectAllCheckBox());
    }

    public void deleteThemeResourceActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession("Theme_currSelected");
        if (currSelectedList == null || currSelectedList.size() == 0) {
            this.addFormattedMessage(null, "请选择要删除的记录！", FacesMessage.SEVERITY_WARN);
        } else {
            this.showPopup(this.getDeletePopup().getClientId());
        }
    }

    public void deleteThemeResourceDialogListener(DialogEvent dialogEvent) {
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession("Theme_currSelected");
        for (String key : currSelectedList) {
            try {
                oracle.jbo.domain.Number resourceId =
                    new oracle.jbo.domain.Number(key.substring(key.lastIndexOf("_") + 1));
                CommonUtil.getThemeAM().deleteThemeResource(resourceId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        CommonUtil.getThemeAM().getDBTransaction().commit();
        try {
            String nodeIdStr = (String)this.resolveExpression("#{pageFlowScope.nodeId}");
            oracle.jbo.domain.Number nodeId = new oracle.jbo.domain.Number(nodeIdStr);
            String statiticsResult = CommonUtil.getThemeAM().getThemeResourceStatistics(nodeId);
            this.setExpressionValue("#{pageFlowScope.statiticsResult}", statiticsResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.refreshUIComponent(this.getResTable());
        this.appendScript("refreshZTree()");
    }

    public void addResourceActionListener(ActionEvent actionEvent) {
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String nodeId = (String)this.resolveExpression("#{pageFlowScope.nodeId}");
        String url =
            request.getContextPath() + "/faces/work/theme/inline/resourceOrigin.jsf?compCode=" + CommonUtil.getCompCode() +
            "&nodeId=" + nodeId + "&goPage=addTheme&groupUserFlag=" +
            this.resolveExpression("#{pageFlowScope.groupUserFlag}");
        this.openWindow(url);
    }

    public void setResTable(RichTable resTable) {
        this.resTable = resTable;
    }

    public RichTable getResTable() {
        return resTable;
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void setRefreshButton(RichCommandButton refreshButton) {
        this.refreshButton = refreshButton;
    }

    public RichCommandButton getRefreshButton() {
        return refreshButton;
    }
}
