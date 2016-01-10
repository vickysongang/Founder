package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.honythink.applicationframework.hadf.util.DateUtil;

import com.zypg.cms.work.model.viewobject.activity.query.CmsActivityVORowImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatusVOImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatusVORowImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsEntryDupLibVOImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsEntryDupLibVORowImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsEntryDupResultVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;

public class EntryDupManagedBean extends CustomManagedBean {
    CustomTableModel<CmsEntryDupResultVORowImpl> tableModel =
        new CustomTableModel<CmsEntryDupResultVORowImpl>("WorkAMDataControl", "EntryAM", "CmsEntryDupResultVO", 20,
                                                         "findEntryDupResultVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichInputDate startDateInputDate;
    private RichInputDate endDateInputDate;
    private RichInputText rateInputText;

    public EntryDupManagedBean() {
    }

    public void initEntryDup() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(2, -1);
        this.setExpressionValue("#{pageFlowScope.defaultStartDate}", gc.getTime());
        this.setExpressionValue("#{pageFlowScope.defaultEndDate}", date);
        CmsEntryDupLibVOImpl libVO = CommonUtil.getEntryAM().getCmsEntryDupLibVO();
        libVO.executeQuery();
        CmsEntryDupLibVORowImpl row = (CmsEntryDupLibVORowImpl)libVO.first();
        if (row != null) {
            this.setExpressionValue("#{pageFlowScope.defaultLibCode}", row.getLibCode());
        }
    }

    public List<SelectItem> getEntryLibList() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        CmsEntryDupLibVOImpl libVO = CommonUtil.getEntryAM().getCmsEntryDupLibVO();
        libVO.executeQuery();
        libVO.setRangeSize(-1);
        for (Row row : libVO.getAllRowsInRange()) {
            CmsEntryDupLibVORowImpl ri = (CmsEntryDupLibVORowImpl)row;
            SelectItem item = new SelectItem(ri.getLibCode(), ri.getLibName());
            items.add(item);
        }
        return items;
    }

    public void searchActionListener(ActionEvent actionEvent) {
        Object rate = this.getRateInputText().getValue();
        if (rate == null) {
            this.addFormattedMessage(this.getRateInputText().getClientId(), "请输入0~100范围内的正整数！",
                                     FacesMessage.SEVERITY_WARN);
            return;
        }
        String entryLibCode = (String)this.resolveExpression("#{pageFlowScope.entryLibCode}");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCompCode", CommonUtil.getCompCode());
        paramMap.put("bvLibCode",
                     entryLibCode == null ? (String)this.resolveExpression("#{pageFlowScope.defaultLibCode}") :
                     entryLibCode);
        paramMap.put("bvRate", rate);
        Date startDate = (Date)this.getStartDateInputDate().getValue();
        Date endDate = (Date)this.getEndDateInputDate().getValue();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(endDate);
        gc.add(5, 1);
        paramMap.put("bvStartDate", startDate == null ? null : DateUtil.getJboDateFromUtilDate(startDate));
        paramMap.put("bvEndDate", endDate == null ? null : DateUtil.getJboDateFromUtilDate(gc.getTime()));
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        System.out.println("tableModel参数：" + tableModel.getParamMap());
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        tableModel.getModel();
    }

    public void tableDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row = (Row)list.get(index);
            oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)row.getAttribute("DocId");
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = request.getContextPath() + "/faces/work/entry/inline/relatedEntryDupView.jsf?docId=" + docId;
            this.openWindow(url, 1300, 650);
        }
    }

    public void entryLibValueChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent != null) {
            String libCode = (String)valueChangeEvent.getNewValue();
            this.setExpressionValue("#{pageFlowScope.entryLibCode}", libCode);
        }
    }

    public void setStartDateInputDate(RichInputDate startDateInputDate) {
        this.startDateInputDate = startDateInputDate;
    }

    public RichInputDate getStartDateInputDate() {
        return startDateInputDate;
    }

    public void setEndDateInputDate(RichInputDate endDateInputDate) {
        this.endDateInputDate = endDateInputDate;
    }

    public RichInputDate getEndDateInputDate() {
        return endDateInputDate;
    }

    public void setRateInputText(RichInputText rateInputText) {
        this.rateInputText = rateInputText;
    }

    public RichInputText getRateInputText() {
        return rateInputText;
    }

    public void setTableModel(CustomTableModel<CmsEntryDupResultVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsEntryDupResultVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }
}
