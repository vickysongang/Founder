package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.util.JSFUtils;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;


import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;

import oracle.jbo.RowIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

public class ExportBootHtmlManageBean extends CustomManagedBean {


    private RichCommandButton deleteRow;
    private RichTable selectTable;
    private RichSelectBooleanCheckbox isSetDefault;
    private RichInputText exportThemeUI;

    public ExportBootHtmlManageBean() {
    }

    public void exportSelectFieldTableDblClickEvent(ClientEvent clientEvent) {
        DCIteratorBinding dc = this.findIterator("CmsAllExportFieldVO1Iterator");
        DCIteratorBinding dc2 = this.findIterator("CmsSelectedExportFieldTVO1Iterator");
        Row row = dc.getCurrentRow();
        ViewObject vo = dc2.getViewObject();
        if (row != null) {
            ViewCriteria vc = vo.createViewCriteria();
            ViewCriteriaRow vr = vc.createViewCriteriaRow();
            vr.setAttribute("FieldName", row.getAttribute("FieldName"));
            vr.setAttribute("FieldCode", row.getAttribute("FieldCode"));
            vc.add(vr);
            RowIterator rt = vo.findByViewCriteria(vc, -1, ViewObject.QUERY_MODE_SCAN_ENTITY_ROWS);
            if (rt.getRowCount() > 0) {
                this.showWarnMessage("同一字段不能添加2次");
            } else {
                Row ro = vo.createRow();
                ro.setAttribute("FieldName", row.getAttribute("FieldName"));
                ro.setAttribute("FieldCode", row.getAttribute("FieldCode"));
                ro.setAttribute("DefaultFlag", "Y");
                vo.insertRow(ro);
                this.refreshUIComponent(this.selectTable);
            }

        }

    }

    public void exportDeleteFieldTableDblClickEvent(ClientEvent clientEvent) {
        ActionEvent ae = new ActionEvent(this.deleteRow);
        ae.queue();
    }


    public void setDeleteRow(RichCommandButton deleteRow) {
        this.deleteRow = deleteRow;
    }

    public RichCommandButton getDeleteRow() {
        return deleteRow;
    }

    public void setSelectTable(RichTable selectTable) {
        this.selectTable = selectTable;
    }

    public RichTable getSelectTable() {
        return selectTable;
    }

    public void init() {
        DCIteratorBinding dc = this.findIterator("CmsBookExportHtmlTVO1Iterator");
        DCIteratorBinding dc2 = this.findIterator("CmsSelectedExportFieldTVO1Iterator");
        this.findOperation("Rollback").execute();
        ViewObject hemlvo = dc.getViewObject();
        ViewObject selectvo = dc2.getViewObject();
        List<Number> docid = (List<Number>)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("docIds");
        Object userid = CommonUtil.getResExpAM().getUserID();
        if (docid != null) {
            if (docid.size() > 0) {
                Row newRow = hemlvo.createRow();
                hemlvo.insertRow(newRow);
                newRow.setAttribute("DocId", docid.get(0));
            }

        }
        selectvo.setWhereClause("DEFAULT_FLAG  = 'Y' and CREATED_BY='" + userid + "'");
        selectvo.executeQuery();

    }

    public void setIsSetDefault(RichSelectBooleanCheckbox isSetDefault) {
        this.isSetDefault = isSetDefault;
    }

    public RichSelectBooleanCheckbox getIsSetDefault() {
        return isSetDefault;
    }

    public String exportBookSave() {
        boolean isDefault = (Boolean)this.getIsSetDefault().getValue();
        List<Number> docid = (List<Number>)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("docIds");
        Object theme = this.resolveExpression("#{bindings.ExportTheme.inputValue}");
        Object level = this.resolveExpression("#{bindings.CategoryLevel1.inputValue}");
        Object reason = this.resolveExpression("#{bindings.ExportReason1.inputValue}");
        Object remark = this.resolveExpression("#{bindings.Remarks.inputValue}");

        Object exportId = this.resolveExpression(" #{bindings.ExportId.inputValue}");
        DCIteratorBinding dc = this.findIterator("CmsSelectedExportFieldTVO1Iterator");
        Row[] rowSet = dc.getViewObject().getFilteredRows("DefaultFlag", "Y");
        if (!isDefault) {
            this.findOperation("Rollback").execute();
        }
        CommonUtil.getResExpAM().createCmsSelectedExportField(rowSet, docid);
        CommonUtil.getResExpAM().submitExportBooKHtml(docid, theme + "", remark + "", level + "",CommonUtil.getCompCode(), reason + "",
                                                      exportId + "");
        this.findOperation("Commit").execute();
        return "end";
    }

    public void setExportThemeUI(RichInputText exportThemeUI) {
        this.exportThemeUI = exportThemeUI;
    }

    public RichInputText getExportThemeUI() {
        return exportThemeUI;
    }
}
