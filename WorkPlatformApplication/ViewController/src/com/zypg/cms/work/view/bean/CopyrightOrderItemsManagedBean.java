package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.honythink.applicationframework.hadf.util.DateUtil;

import com.zypg.cms.work.model.viewobject.copyright.query.Cms3rdAuthorizationItemVORowImpl;
import com.zypg.cms.work.model.viewobject.copyright.query.Cms3rdAuthorizationOrderVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputDate;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class CopyrightOrderItemsManagedBean extends CustomManagedBean {
    private Integer pageCount = 5;
    private CustomTableModel<Cms3rdAuthorizationItemVORowImpl> tableModel =
        new CustomTableModel<Cms3rdAuthorizationItemVORowImpl>("WorkAMDataControl", "CopyrightAM",
                                                               "Cms3rdAuthorizationItemVO", pageCount,
                                                               "findOrderItemsVC");
    private CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichInputDate endTimeInputDate;

    public CopyrightOrderItemsManagedBean() {
    }

    public void initOrderItems() {
        if (CommonUtil.getCopyrightAM().getDBTransaction().isDirty()) {
            CommonUtil.getCopyrightAM().getDBTransaction().rollback();
        }
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Number orderDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        //如果已经加入了 根据authorDocId过滤掉
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
        //查询author 过滤条件：授权类型，授权地区，授权语种
        //COPYRIGHT_FORM,AUTHORIZED_AREA,AUTHORIZED_LANG
        Cms3rdAuthorizationOrderVORowImpl row =
            CommonUtil.getCopyrightAM().init4CopyrightAuthor(orderDocId, DateUtil.getCurrentJboDate());
        String authorizedTypeMeaning =
            CommonUtil.findLovMeaingByLookupValueCode("AUTHORIZED_TYPE", row.getAuthorizedType());
        this.setExpressionValue("#{pageFlowScope.authorizedType}", authorizedTypeMeaning);
        this.setExpressionValue("#{pageFlowScope.authorizedArea}", row.getAuthorizedArea());
        this.setExpressionValue("#{pageFlowScope.authorizedLang}", row.getAuthorizedLang());
        this.setExpressionValue("#{pageFlowScope.defaultEndTime}", DateUtil.getCurrentJboDate());
    }

    public void initOrderItemsDetail() {
        if (CommonUtil.getCopyrightAM().getDBTransaction().isDirty()) {
            CommonUtil.getCopyrightAM().getDBTransaction().rollback();
        }
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Number orderDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        //        paramMap.put("bvAuthorDocId", this.resolveExpression("#{pageFlowScope.authorDocId}"));
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        System.out.println("initOrderItems paramMap:" + paramMap);
        tableModel.setParamMap(paramMap);
        //查询author 过滤条件：授权类型，授权地区，授权语种
        //COPYRIGHT_FORM,AUTHORIZED_AREA,AUTHORIZED_LANG
        Cms3rdAuthorizationOrderVORowImpl row =
            CommonUtil.getCopyrightAM().init4CopyrightAuthor(orderDocId, DateUtil.getCurrentJboDate());
        this.setExpressionValue("#{pageFlowScope.authorizedType}", row.getAuthorizedType());
        this.setExpressionValue("#{pageFlowScope.authorizedArea}", row.getAuthorizedArea());
        this.setExpressionValue("#{pageFlowScope.authorizedLang}", row.getAuthorizedLang());

    }

    public void deleteOrderItemsActionListener(ActionEvent actionEvent) {
        Map map = actionEvent.getComponent().getAttributes();
        Number itemId = (Number)map.get("itemId");
        CommonUtil.getCopyrightAM().deleteOrderItems(itemId);
        CommonUtil.getCopyrightAM().getDBTransaction().commit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
        Number orderDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Date utilDate = (Date)getEndTimeInputDate().getValue();
        oracle.jbo.domain.Date jboDate = DateUtil.getJboDateFromUtilDate(utilDate);
        CommonUtil.getCopyrightAM().init4CopyrightAuthor(orderDocId,
                                                         jboDate == null ? DateUtil.getCurrentJboDate() : jboDate);
    }

    public void exclusiveSetActionListener(ActionEvent actionEvent) {
        Map map = actionEvent.getComponent().getAttributes();
        Number itemId = (Number)map.get("itemId");
        CommonUtil.getCopyrightAM().exclusiveSet(itemId);
        CommonUtil.getCopyrightAM().getDBTransaction().commit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
    }

    public void exclusiveCancelActionListener(ActionEvent actionEvent) {
        Map map = actionEvent.getComponent().getAttributes();
        Number itemId = (Number)map.get("itemId");
        CommonUtil.getCopyrightAM().exclusiveCancel(itemId);
        CommonUtil.getCopyrightAM().getDBTransaction().commit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
    }

    public void setOrderItemsActionListener(ActionEvent actionEvent) {
        Number orderDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Number authorDocId = (Number)actionEvent.getComponent().getAttributes().get("authorDocId");
        CommonUtil.getCopyrightAM().setOrderItems(orderDocId, authorDocId, CommonUtil.getCompCode());
        CommonUtil.getCopyrightAM().getDBTransaction().commit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);

        Date utilDate = (Date)getEndTimeInputDate().getValue();
        oracle.jbo.domain.Date jboDate = DateUtil.getJboDateFromUtilDate(utilDate);
        CommonUtil.getCopyrightAM().init4CopyrightAuthor(orderDocId,
                                                         jboDate == null ? DateUtil.getCurrentJboDate() : jboDate);
    }

    public void addAllOrderItemsActionListener(ActionEvent actionEvent) {
        DCIteratorBinding dcib = this.findIterator("CmsAuthorCopyrightVO4QueryIterator");
        if (dcib.getEstimatedRowCount() == 0) {
            this.addFormattedMessage(null, "没有符合条件的图书版权", FacesMessage.SEVERITY_INFO);
            return;
        }
        Number orderDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        for (Row row : dcib.getAllRowsInRange()) {
            Number authorDocId = (Number)row.getAttribute("DocId");
            CommonUtil.getCopyrightAM().setOrderItems(orderDocId, authorDocId, CommonUtil.getCompCode());
        }
        CommonUtil.getCopyrightAM().getDBTransaction().commit();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvOrderDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);

        Date utilDate = (Date)getEndTimeInputDate().getValue();
        oracle.jbo.domain.Date jboDate = DateUtil.getJboDateFromUtilDate(utilDate);
        CommonUtil.getCopyrightAM().init4CopyrightAuthor(orderDocId,
                                                         jboDate == null ? DateUtil.getCurrentJboDate() : jboDate);
    }

    public void searchActionListener(ActionEvent actionEvent) {
        Number orderDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Date utilDate = (Date)getEndTimeInputDate().getValue();
        oracle.jbo.domain.Date jboDate = DateUtil.getJboDateFromUtilDate(utilDate);
        CommonUtil.getCopyrightAM().init4CopyrightAuthor(orderDocId,
                                                         jboDate == null ? DateUtil.getCurrentJboDate() : jboDate);
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setTableModel(CustomTableModel<Cms3rdAuthorizationItemVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<Cms3rdAuthorizationItemVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setEndTimeInputDate(RichInputDate endTimeInputDate) {
        this.endTimeInputDate = endTimeInputDate;
    }

    public RichInputDate getEndTimeInputDate() {
        return endTimeInputDate;
    }
}
