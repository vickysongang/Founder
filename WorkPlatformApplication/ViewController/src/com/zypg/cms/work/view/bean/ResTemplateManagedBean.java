package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.viewobject.common.lov.CmsLookupValueVOImpl;
import com.zypg.cms.work.model.viewobject.common.lov.CmsLookupValueVORowImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatusVOImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatusVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class ResTemplateManagedBean extends CustomManagedBean {
    private ResExpAMImpl resExpAm;

    public ResTemplateManagedBean() {
        this.resExpAm = CommonUtil.getResExpAM();
    }

    public void initResTemplate() {
        this.setExpressionValue("#{pageFlowScope.applyName}", null);
        this.setExpressionValue("#{pageFlowScope.remarks}", null);
        this.setExpressionValue("#{pageFlowScope.exportReason}", "XUANTI");
        List<Number> shelfDocIdList = (List<Number>)this.resolveExpression("#{pageFlowScope.shelfDocIdList}");
        if (shelfDocIdList == null || shelfDocIdList.size() == 0) {
            this.setExpressionValue("#{pageFlowScope.shelfDocIdListSize}", 0);
        } else {
            this.setExpressionValue("#{pageFlowScope.shelfDocIdListSize}", shelfDocIdList.size());
        }
        resExpAm.initResTemplate((String)this.resolveExpression("#{pageFlowScope.categoryCode}"),
                                 CommonUtil.getCompCode());
    }

    public List<SelectItem> getExportReasonList() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        CmsLookupValueVOImpl lookupVO = CommonUtil.getWorkAM().getCmsExportReasonLov();
        lookupVO.executeQuery();
        lookupVO.setRangeSize(-1);
        for (Row row : lookupVO.getAllRowsInRange()) {
            CmsLookupValueVORowImpl ri = (CmsLookupValueVORowImpl)row;
            SelectItem item = new SelectItem(ri.getLookupValueCode(), ri.getMeaning());
            items.add(item);
        }
        return items;
    }

    public String submitActionListener() {
        List<Number> shelfDocIdList = (List<Number>)this.resolveExpression("#{pageFlowScope.shelfDocIdList}"); //保存的时候用
        if (shelfDocIdList.size() == 0) {
            String categoryCode = (String)this.resolveExpression("#{pageFlowScope.categoryCode}");
            shelfDocIdList = CommonUtil.getResExpAM().getAllTempShelfRow("N", categoryCode);
            System.out.println("shelfDocIdList:" + shelfDocIdList.size());
        }
        String applyName = (String)this.resolveExpression("#{pageFlowScope.applyName}");
        String remarks = (String)this.resolveExpression("#{pageFlowScope.remarks}");
        String exportReason = (String)this.resolveExpression("#{pageFlowScope.exportReason}");
        String msg =
            resExpAm.submitApplyDoc(shelfDocIdList, applyName, remarks, CommonUtil.getCompCode(), exportReason);
        if ("S".equals(msg)) {
            this.navToOutCome("toReturn");
            this.showMessage("提交成功！！！");
        } else {
            this.showErrorMessage("提交失败：" + msg);
        }
        return null;
    }

    public String cancelActionListener() {
        resExpAm.getDBTransaction().rollback();
        return "toReturn";
    }

    public void editActionListener(ActionEvent actionEvent) {
        String mode = (String)actionEvent.getComponent().getAttributes().get("mode");
        Number templateId = (Number)actionEvent.getComponent().getAttributes().get("templateId");
        String categoryCode = (String)this.resolveExpression("#{pageFlowScope.categoryCode}");
        Object[] ret = resExpAm.preResTemplateEdit(mode, templateId, categoryCode, CommonUtil.getCompCode());
        this.setExpressionValue("#{pageFlowScope.currentTemplateId}", ret[0]);
        this.setExpressionValue("#{pageFlowScope.templateName}", ret[1]);
    }

    public void saveActionListener(ActionEvent actionEvent) {
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        String resTemplateName = (String)adfFacesContext.getPageFlowScope().get("templateName");
        Number templateId = (Number)adfFacesContext.getPageFlowScope().get("currentTemplateId");
        resExpAm.saveResTemplate(templateId, resTemplateName);
    }

    public void returnBackActionListener(ActionEvent actionEvent) {
        resExpAm.getDBTransaction().rollback();
    }

    public void deleteActionListener(ActionEvent actionEvent) {
        String result = resExpAm.deleteTemplate((Number)actionEvent.getComponent().getAttributes().get("templateId"));
        if ("0".equals(result)) {
            this.showMessage("模板删除成功!");
            resExpAm.getDBTransaction().rollback();
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
    }

    public void exportReasonValueChangeListener(ValueChangeEvent valueChangeEvent) {
        String exportReason = (String)valueChangeEvent.getNewValue();
        this.setExpressionValue("#{pageFlowScope.exportReason}", exportReason);
    }
}
