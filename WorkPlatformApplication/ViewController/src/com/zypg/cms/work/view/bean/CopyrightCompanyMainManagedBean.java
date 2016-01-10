package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.copyright.query.CmsCompanyCopyrightVOImpl;
import com.zypg.cms.work.model.viewobject.copyright.query.CmsCompanyCopyrightVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class CopyrightCompanyMainManagedBean extends CustomManagedBean {

    CustomTableModel<CmsCompanyCopyrightVORowImpl> tableModel =
        new CustomTableModel<CmsCompanyCopyrightVORowImpl>("WorkAMDataControl", "CopyrightAM", "CmsCompanyCopyrightVO",
                                                           20, "findCompanyVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    private RichPopup popup;

    public CopyrightCompanyMainManagedBean() {
    }

    public void initCompany() {
        CmsCompanyCopyrightVOImpl companyVO = CommonUtil.getCopyrightAM().getCmsCompanyCopyrightVO4View();
        companyVO.setbvCompCode(CommonUtil.getCompCode());
        companyVO.executeQuery();
    }

    public void companyTableDblClick(ClientEvent clientEvent) {
        commonRenderer.tableDblSelectAction(clientEvent);
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

    public void setTableModel(CustomTableModel<CmsCompanyCopyrightVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsCompanyCopyrightVORowImpl> getTableModel() {
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

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setCompanyId(SelectionEvent selectionEvent) {
        RichTable rt = (RichTable)selectionEvent.getSource();
        CollectionModel tableModel = (CollectionModel)rt.getValue();
        JUCtrlHierBinding adfTableBinding = (JUCtrlHierBinding)tableModel.getWrappedData();
        DCIteratorBinding tableIteratorBinding = adfTableBinding.getDCIteratorBinding();
        Object selectedRowData = rt.getSelectedRowData();
        JUCtrlHierNodeBinding nodeBinding = (JUCtrlHierNodeBinding)selectedRowData;
        Key _rwKey = (nodeBinding == null) ? null : nodeBinding.getRowKey();
        tableIteratorBinding.setCurrentRowWithKey((_rwKey == null) ? null : _rwKey.toStringFormat(true));
        oracle.jbo.domain.Number companyId =
            (oracle.jbo.domain.Number)nodeBinding.getCurrentRow().getAttribute("CompanyId");
        this.setExpressionValue("#{pageFlowScope.companyId}", companyId);
    }

    public void refreshReturnListener(ReturnEvent returnEvent) {
        initCompany();
    }

    public void deleteActionListener(DialogEvent dialogEvent) {
        oracle.jbo.domain.Number companyId =
            (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.companyId}");
        CommonUtil.getCopyrightAM().deleteCompanyById(companyId);
        this.setExpressionValue("#{pageFlowScope.companyId}", null);
        initCompany();
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        this.initCompany();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }
}
