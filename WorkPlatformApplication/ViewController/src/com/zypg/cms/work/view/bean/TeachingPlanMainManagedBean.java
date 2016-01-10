package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.model.viewobject.teachingplan.query.CmsTeachingPlanQVORowImpl;
import com.zypg.cms.work.model.viewobject.teachingplan.query.CmsTeachingPlanVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

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


public class TeachingPlanMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsTeachingPlanVORowImpl> tableModel =
        new CustomTableModel<CmsTeachingPlanVORowImpl>("WorkAMDataControl", "TeachingPlanAM", "CmsTeachingPlanVO", 20,
                                                       "findTeachingPlanVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public TeachingPlanMainManagedBean() {
    }

    public void initTeachingPlan() {
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

    public void teachingPlanTableDblClick(ClientEvent clientEvent) {
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
                request.getContextPath() + "/faces/work/teachingplan/inline/relatedTeachingPlanView.jsf?docId=" +
                docId;
            this.openWindow(url);
        }
    }

    public void reFileManageDialogListener(DialogEvent dialogEvent) {
        commonRenderer.reFileManageAction();
    }

    public void setTableModel(CustomTableModel<CmsTeachingPlanVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsTeachingPlanVORowImpl> getTableModel() {
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
        initTeachingPlan();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public String refreshWhenSycnAction() {
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
        return null;
    }

    public void complexRearchReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            CmsTeachingPlanQVORowImpl queryRow =
                (CmsTeachingPlanQVORowImpl)CommonUtil.getTeachingPlanAM().getCmsTeachingPlanQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getTeachingPlanAM().getCmsTeachingPlanQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
        }
    }
}
