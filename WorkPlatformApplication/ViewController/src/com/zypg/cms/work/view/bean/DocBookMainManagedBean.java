package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.WorkAMImpl;
import com.zypg.cms.work.model.am.DocBookAMImpl;
import com.zypg.cms.work.model.viewobject.docbook.query.CmsDocBookJobVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DocBookImpThread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class DocBookMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsDocBookJobVORowImpl> tableModel =
        new CustomTableModel<CmsDocBookJobVORowImpl>("WorkAMDataControl", "DocBookAM", "CmsDocBookJobVO", 20,
                                                     "findDocBookJobVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPopup monitorPopup;
    private DocBookAMImpl docBookAm;
    private WorkAMImpl workAm;

    public DocBookMainManagedBean() {
        this.docBookAm = CommonUtil.getDocBookAM();
        this.workAm = CommonUtil.getWorkAM();
    }

    public void initDocBookMain() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
    }


    public void tableDblClick(ClientEvent clientEvent) {
        commonRenderer.tableDblSelectAction(clientEvent);
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (!"TO_IMPORT_DOCBOOK".equals(CommonUtil.getCurrStatusCode()) && indexStr != null && indexStr.length() > 0 &&
            (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row;
            try {
                row = (Row)list.get(index);
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
            this.setExpressionValue("#{pageFlowScope.docId}", docId);
            this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
            this.findOperation("findMonitorByJobId").execute();
            this.showPopup(this.getMonitorPopup().getClientId());
        }
    }

    public void impActionListener(ActionEvent actionEvent) {
        StringBuffer jobIds = new StringBuffer();
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            jobIds.append(docId + "/");
        }
        currSelectedList.clear();
        Map<String, String> jobMsg = docBookAm.getJobMsgByIds(jobIds.toString());
        if (jobMsg.get("jobNames") != null) {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", "IMPORTING_DOCBOOK");
            commonRenderer.statusValueChange(CommonUtil.getCurrStatusCode());
            new Thread(new DocBookImpThread(jobMsg.get("jobNames"), jobIds.substring(0, jobIds.length() - 1),
                                            jobMsg.get("compName"), jobMsg.get("compCode"))).start();
        }
    }

    public void deleteActionListener(ActionEvent actionEvent) {
        StringBuffer jobIds = new StringBuffer();
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            jobIds.append(docId + ",");
        }
        if (jobIds.length() > 0) {
            docBookAm.deleteJob(jobIds.toString());
            currSelectedList.clear();
            CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
        }
    }

    public void refreshMonitorActionListener(ActionEvent actionEvent) {
        this.findOperation("findMonitorByJobId").execute();
    }

    public void refreshJobActionListener(ActionEvent actionEvent) {
        this.refreshUIComponent(commonRenderer.getTable());
    }

    public void setTableModel(CustomTableModel<CmsDocBookJobVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsDocBookJobVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setMonitorPopup(RichPopup monitorPopup) {
        this.monitorPopup = monitorPopup;
    }

    public RichPopup getMonitorPopup() {
        return monitorPopup;
    }
}
