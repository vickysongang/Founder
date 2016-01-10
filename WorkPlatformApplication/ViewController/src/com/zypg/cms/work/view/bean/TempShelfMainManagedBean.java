package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsLib4TempShelfVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResTempShelfVORowImpl;
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

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;


public class TempShelfMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsResTempShelfVORowImpl> tableModel =
        new CustomTableModel<CmsResTempShelfVORowImpl>("WorkAMDataControl", "ResExpAM", "CmsResTempShelfVO", 20);
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private ResExpAMImpl resExpAm;
    private RichPopup resTemplatePopup;
    private RichPanelGroupLayout panelGroup;
    private RichPanelSplitter panelSplitter;

    public TempShelfMainManagedBean() {
        this.resExpAm = CommonUtil.getResExpAM();
    }

    public void initTempShelf() {
        String categoryCode = null;
        try {
            categoryCode = resExpAm.findSecondLevelLib(CommonUtil.getCompCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setExpressionValue("#{pageFlowScope.categoryCode}", categoryCode);
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCompCode", CommonUtil.getCompCode());
        if ("-3".equals(categoryCode)) {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "DELETED_TEMP_SHELF");
            paramMap.put("bvCategoryCode", null);
            paramMap.put("bvDeleteFlag", "Y");
            resExpAm.findResTempShelf(null, "Y", CommonUtil.getCompCode());
        } else {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "TO_USE_TEMP_SHELF");
            paramMap.put("bvCategoryCode", categoryCode);
            paramMap.put("bvDeleteFlag", "N");
            resExpAm.findResTempShelf(categoryCode, "N", CommonUtil.getCompCode());
        }
        paramMap.put("bvUserId", CommonUtil.getUserId());
        System.out.println("initTempShelf-->paramMap:" + paramMap);
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        commonRenderer.resetStatusSession();
    }

    public void libActionListener(ActionEvent actionEvent) {
        String categoryCode = (String)actionEvent.getComponent().getAttributes().get("categoryCode");
        this.setExpressionValue("#{pageFlowScope.categoryCode}", categoryCode);
        if ("-3".equals(categoryCode)) {
            resExpAm.findResTempShelf(null, "Y", CommonUtil.getCompCode());
            this.setExpressionValue("#{pageFlowScope.deleteFlag}", "Y");
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "DELETED_TEMP_SHELF");
        } else {
            resExpAm.findResTempShelf(categoryCode, "N", CommonUtil.getCompCode());
            this.setExpressionValue("#{pageFlowScope.deleteFlag}", "N");
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "TO_USE_TEMP_SHELF");
        }
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        String categoryCode = (String)this.resolveExpression("#{pageFlowScope.categoryCode}");
        CmsLib4TempShelfVOImpl libVO = resExpAm.getCmsLib4TempShelfVO();
        libVO.setbvCompCode(CommonUtil.getCompCode());
        libVO.setbvUserId(resExpAm.getCustomDBTransaction().getUserId());
        libVO.executeQuery();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if ("-3".equals(categoryCode)) {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "DELETED_TEMP_SHELF");
            paramMap.put("bvCategoryCode", null);
            paramMap.put("bvDeleteFlag", "Y");
            resExpAm.findResTempShelf(null, "Y", CommonUtil.getCompCode());
        } else {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "TO_USE_TEMP_SHELF");
            paramMap.put("bvCategoryCode", categoryCode);
            paramMap.put("bvDeleteFlag", "N");
            resExpAm.findResTempShelf(categoryCode, "N", CommonUtil.getCompCode());
        }
        paramMap.put("bvUserId", CommonUtil.getUserId());
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        this.refreshUIComponent(commonRenderer.getTable());
        this.refreshUIComponent(this.getPanelSplitter());
    }

    public void submitActionListener(ActionEvent actionEvent) {
        List<Number> list = new ArrayList<Number>();
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList != null) {
                for (String key : currSelectedList) {
                    Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                    list.add(new Number(docId));
                }
            }
            this.setExpressionValue("#{pageFlowScope.shelfDocIdList}", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTableModel(CustomTableModel<CmsResTempShelfVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsResTempShelfVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setResTemplatePopup(RichPopup resTemplatePopup) {
        this.resTemplatePopup = resTemplatePopup;
    }

    public RichPopup getResTemplatePopup() {
        return resTemplatePopup;
    }

    public void setPanelGroup(RichPanelGroupLayout panelGroup) {
        this.panelGroup = panelGroup;
    }

    public RichPanelGroupLayout getPanelGroup() {
        return panelGroup;
    }

    private void clearSelectedDocIds() {
        //清除被选中状态
        String selectAllKey = CommonUtil.getCurrStatusCode() + "_" + "selectAllFlag";
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            CommonUtil.putStringToSession(selectAllKey, "N");
            currSelectedList.clear();
            CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
        }
    }

    public void applyReturnListener(ReturnEvent returnEvent) {
        clearSelectedDocIds();
        this.findIterator("CmsLib4TempShelfVOIterator").executeQuery();
        commonRenderer.refreshSelectAllCheckBox();
        this.refreshUIComponent(this.getPanelSplitter());
    }

    public void exportBookReturnListener(ReturnEvent returnEvent) {
        clearSelectedDocIds();
        this.findIterator("CmsLib4TempShelfVOIterator").executeQuery();
        this.refreshUIComponent(this.getPanelSplitter());
    }


    private void showResult(String result) {
        if (result.startsWith("S")) {
            this.showMessage("操作成功!");
            clearSelectedDocIds();
        } else {
            this.addFormattedMessage(null, result.substring(result.indexOf(",") + 1), FacesMessage.SEVERITY_ERROR);
        }
        this.findIterator("CmsLib4TempShelfVOIterator").executeQuery();
    }

    private String getSelectedDocIds() {
        StringBuffer docIds = new StringBuffer();
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            docIds.append(docId + ",");
        }
        if (docIds.length() > 0) {
            return docIds.substring(0, docIds.length() - 1);
        }
        return null;
    }

    public void deleteActionListener(ActionEvent actionEvent) {
        String docIds = getSelectedDocIds();
        if (docIds != null) {
            showResult(resExpAm.deleteTempShelf(docIds));
        }
        commonRenderer.refreshSelectAllCheckBox();
    }

    public void deleteResDialogActionListener(DialogEvent dialogEvent) {
        String docIds = getSelectedDocIds();
        if (docIds != null) {
            showResult(resExpAm.deleteTempShelf(docIds));
        }
        tableModel.getModel();
        commonRenderer.refreshSelectAllCheckBox();
    }

    public void recoverActionListener(ActionEvent actionEvent) {
        String docIds = getSelectedDocIds();
        if (docIds != null) {
            showResult(resExpAm.recoverTempShelf(docIds));
        }
        tableModel.getModel();
        commonRenderer.refreshSelectAllCheckBox();
        this.refreshUIComponent(this.getPanelSplitter());
    }

    public void destroyResActionListener(DialogEvent dialogEvent) {
        String docIds = getSelectedDocIds();
        if (docIds != null) {
            showResult(resExpAm.destroyTempShelf(docIds));
        }
        commonRenderer.refreshSelectAllCheckBox();
        tableModel.getModel();
        this.refreshUIComponent(this.getPanelSplitter());
    }

    public void gotoExportBookHtml(ActionEvent actionEvent) {
        List<Number> list = new ArrayList<Number>();
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList != null) {
                for (String key : currSelectedList) {
                    Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                    list.add(new Number(docId));
                }
            }
            this.setExpressionValue("#{pageFlowScope.shelfIdList}", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addResourceActionListener(ActionEvent actionEvent) {
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/theme/inline/resourceOrigin.jsf?compCode=" + CommonUtil.getCompCode() +
            "&nodeId=''&goPage=addResource&groupUserFlag=" + this.resolveExpression("#{pageFlowScope.groupUserFlag}");
        this.openWindow(url);
    }

    public void setPanelSplitter(RichPanelSplitter panelSplitter) {
        this.panelSplitter = panelSplitter;
    }

    public RichPanelSplitter getPanelSplitter() {
        return panelSplitter;
    }
}
