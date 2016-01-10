package com.zypg.cms.work.view.bean;


import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.am.EntryAMImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocUcmRelVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.List;

import javax.faces.application.FacesMessage;

import javax.naming.NamingException;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.stellent.ridc.IdcClientException;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;


public class EntryDupViewManagedBean extends CustomManagedBean {
    private RichPanelGroupLayout panelGroupLayout;
    private RichTable entryTable;

    public EntryDupViewManagedBean() {
    }

    public void initEntryDup4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        System.out.println("docIdStr:" + docIdStr);
        if (docIdStr != null) {
            try {
                EntryAMImpl am = CommonUtil.getEntryAM();
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                am.initMasterEntry4Dup(docId);
                oracle.jbo.domain.Number thatDocId = am.querySimilarEntry(docId);
                am.initSlaveEntry4Dup(thatDocId);
            } catch (SQLException e) {
                System.out.println("docIdStr:" + docIdStr);
            }
        }
    }

    public void setPanelGroupLayout(RichPanelGroupLayout panelGroupLayout) {
        this.panelGroupLayout = panelGroupLayout;
    }

    public RichPanelGroupLayout getPanelGroupLayout() {
        return panelGroupLayout;
    }


    public void onTableSelect(SelectionEvent selectionEvent) {
        RichTable rt = (RichTable)selectionEvent.getSource();
        CollectionModel cm = (CollectionModel)rt.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)cm.getWrappedData();
        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();

        JUCtrlHierNodeBinding selectedRowData = (JUCtrlHierNodeBinding)rt.getSelectedRowData();
        Key rowKey = selectedRowData.getRowKey();
        iter.setCurrentRowWithKey(rowKey.toStringFormat(true));
        Row row = selectedRowData.getRow();
        oracle.jbo.domain.Number thatDocId = (oracle.jbo.domain.Number)row.getAttribute("ThatDocId");
        System.out.println("thatDocId:" + thatDocId);
        CommonUtil.getEntryAM().initSlaveEntry4Dup(thatDocId);
        this.refreshUIComponent(this.getPanelGroupLayout());
    }

    public void setEntryTable(RichTable entryTable) {
        this.entryTable = entryTable;
    }

    public RichTable getEntryTable() {
        return entryTable;
    }

    public String deleteMasterEntryAction() {
        oracle.jbo.domain.Number masterDocId =
            (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.masterDocId}");
        String result = CommonUtil.getEntryAM().deleteEntry(masterDocId);
        if ("S".equals(result)) {
            List<Row> ucmFileRows = CommonUtil.getWorkAM().getUCMFileByDocId(masterDocId);
            for (Row row : ucmFileRows) {
                CmsDocUcmRelVORowImpl ucmFileRow = (CmsDocUcmRelVORowImpl)row;
                try {
                    DocService.getInstance().deleteDocByDID(ucmFileRow.getUcmDid().toString());
                } catch (NamingException e) {
                    e.printStackTrace();
                } catch (IdcClientException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            this.closeWindow();
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
        return null;
    }

    public String deleteSlaveEntryAction() {
        oracle.jbo.domain.Number slaveDocId =
            (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.slaveDocId}");
        String result = CommonUtil.getEntryAM().deleteEntry(slaveDocId);
        if ("S".equals(result)) {
            List<Row> ucmFileRows = CommonUtil.getWorkAM().getUCMFileByDocId(slaveDocId);
            for (Row row : ucmFileRows) {
                CmsDocUcmRelVORowImpl ucmFileRow = (CmsDocUcmRelVORowImpl)row;
                try {
                    DocService.getInstance().deleteDocByDID(ucmFileRow.getUcmDid().toString());
                } catch (NamingException e) {
                    e.printStackTrace();
                } catch (IdcClientException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            this.closeWindow();
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
        }
        return null;
    }
}
