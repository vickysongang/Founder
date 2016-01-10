package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.model.viewobject.courseware.query.CmsCoursewareQVORowImpl;
import com.zypg.cms.work.model.viewobject.courseware.query.CmsCoursewareVORowImpl;
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


public class CoursewareMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsCoursewareVORowImpl> tableModel =
        new CustomTableModel<CmsCoursewareVORowImpl>("WorkAMDataControl", "CoursewareAM", "CmsCoursewareVO", 20,
                                                     "findCoursewareVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public CoursewareMainManagedBean() {
    }

    public void initCourseware() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        if (!"Y".equals(this.resolveExpression("#{pageFlowScope.deleteFlag}"))) {
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        commonRenderer.resetStatusSession();
    }

    public void coursewareTableDblClick(ClientEvent clientEvent) {
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
                request.getContextPath() + "/faces/work/courseware/inline/relatedCoursewareView.jsf?docId=" + docId;
            this.openWindow(url);
        }
    }

    public void reFileManageDialogListener(DialogEvent dialogEvent) {
        commonRenderer.reFileManageAction();
    }

    public void setTableModel(CustomTableModel<CmsCoursewareVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsCoursewareVORowImpl> getTableModel() {
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
        initCourseware();
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
            CmsCoursewareQVORowImpl queryRow =
                (CmsCoursewareQVORowImpl)CommonUtil.getCoursewareAM().getCmsCoursewareQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getCoursewareAM().getCmsCoursewareQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
        }
    }
}
