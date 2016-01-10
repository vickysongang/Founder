package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.util.DateUtil;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;


public class JoinFigureGroupManagedBean extends CustomManagedBean {
    private RichInputText groupIdInputText;
    private RichInputText groupNameOrDescInputText;
    private RichInputDate startTimeInputDate;
    private RichInputDate endTimeInputDate;

    public JoinFigureGroupManagedBean() {
    }

    public void initFigureGroup() {
        CommonUtil.getFigureGroupAM().init4JionFigureGroup();
        this.setExpressionValue("#{pageFlowScope.groupId}", null);
    }

    public void saveActionListener(ActionEvent actionEvent) {
        String docIds = (String)this.resolveExpression("#{pageFlowScope.docId}");
        oracle.jbo.domain.Number newGroupId =
            (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.groupId}");
        String result = CommonUtil.getFigureGroupAM().joinNewFigureGroup(docIds, newGroupId);
        if ("Y".equals(result)) {
            CommonUtil.getFigureGroupAM().getDBTransaction().commit();
            this.navToOutCome("toReturn");
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getFigureGroupAM().getDBTransaction().rollback();
    }

    public void searchActionListener(ActionEvent actionEvent) {
        BigDecimal bigGroupId = (BigDecimal)this.getGroupIdInputText().getValue();
        oracle.jbo.domain.Number groupId = null;
        if (bigGroupId != null) {
            groupId = new oracle.jbo.domain.Number(bigGroupId.intValue());
        }
        String groupNameOrDesc = (String)this.getGroupNameOrDescInputText().getValue();
        Date startTime = DateUtil.getJboDateFromUtilDate((java.util.Date)this.getStartTimeInputDate().getValue());
        Date endTime = DateUtil.getJboDateFromUtilDate((java.util.Date)this.getEndTimeInputDate().getValue());
        String libCode = CommonUtil.getLibCode();
        Row[] rows =
            CommonUtil.getFigureGroupAM().queryFigureGroup(groupId, groupNameOrDesc, startTime, endTime, libCode,CommonUtil.getCompCode());
        if (rows.length > 0) {
            oracle.jbo.domain.Number firstGroupId = (oracle.jbo.domain.Number)rows[0].getAttribute("GroupId");
            CommonUtil.getFigureGroupAM().editFigureGroup(firstGroupId);
            this.setExpressionValue("#{pageFlowScope.groupId}", firstGroupId);
        }
    }

    public void setGroupIdInputText(RichInputText groupIdInputText) {
        this.groupIdInputText = groupIdInputText;
    }

    public RichInputText getGroupIdInputText() {
        return groupIdInputText;
    }

    public void setGroupNameOrDescInputText(RichInputText groupNameOrDescInputText) {
        this.groupNameOrDescInputText = groupNameOrDescInputText;
    }

    public RichInputText getGroupNameOrDescInputText() {
        return groupNameOrDescInputText;
    }

    public void setStartTimeInputDate(RichInputDate startTimeInputDate) {
        this.startTimeInputDate = startTimeInputDate;
    }

    public RichInputDate getStartTimeInputDate() {
        return startTimeInputDate;
    }

    public void setEndTimeInputDate(RichInputDate endTimeInputDate) {
        this.endTimeInputDate = endTimeInputDate;
    }

    public RichInputDate getEndTimeInputDate() {
        return endTimeInputDate;
    }

    public void tableSelectionListener(SelectionEvent selectionEvent) {
        this.invokeMethodExpression("#{bindings.CmsFigureGroupVO.collectionModel.makeCurrent}", null,
                                    new Class[] { SelectionEvent.class }, new Object[] { selectionEvent });
        RichTable rt = (RichTable)selectionEvent.getSource();
        CollectionModel cm = (CollectionModel)rt.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)cm.getWrappedData();
        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        JUCtrlHierNodeBinding selectedRowData = (JUCtrlHierNodeBinding)rt.getSelectedRowData();
        Key rowKey = selectedRowData.getRowKey();
        iter.setCurrentRowWithKey(rowKey.toStringFormat(true));
        Row row = selectedRowData.getRow();
        oracle.jbo.domain.Number groupId = (oracle.jbo.domain.Number)row.getAttribute("GroupId");
        CommonUtil.getFigureGroupAM().editFigureGroup(groupId);
        this.setExpressionValue("#{pageFlowScope.groupId}", groupId);
    }
}
