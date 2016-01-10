package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.viewobject.common.query.CmsDocUcmRelVORowImpl;
import com.zypg.cms.work.model.viewobject.docbook.query.CmsDocBookJobVORowImpl;
import com.zypg.cms.work.model.viewobject.offline.query.CmsOfflineEntryProcessTaskVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class OfflineEntryProcessManagedBean extends CustomManagedBean {
    CustomTableModel<CmsOfflineEntryProcessTaskVORowImpl> tableModel =
        new CustomTableModel<CmsOfflineEntryProcessTaskVORowImpl>("WorkAMDataControl", "OfflineAM",
                                                                  "CmsOfflineEntryProcessTaskVO", 20,
                                                                  "findOfflineEntryProcessTaskVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichCommandButton batchDownloadButton;
    private RichPopup deletePopup;
    private RichPopup forceDeletePopup;

    public OfflineEntryProcessManagedBean() {
    }

    public void initOfflineEntryProcess() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
    }

    public void setTableModel(CustomTableModel<CmsOfflineEntryProcessTaskVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsOfflineEntryProcessTaskVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
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
            String url = request.getContextPath() + "/faces/work/offline/inline/taskView.jsf?docId=" + docId;
            this.openWindow(url);
        }
    }

    public void refreshTaskActionListener(ActionEvent actionEvent) {
        initOfflineEntryProcess();
    }

    public void deleteTaskDialogListener(DialogEvent dialogEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            try {
                List<Row> ucmFileList = CommonUtil.getWorkAM().getUCMFileByDocId(new Number(docId));
                for (Row row : ucmFileList) {
                    CmsDocUcmRelVORowImpl ucmFileRow = (CmsDocUcmRelVORowImpl)row;
                    try {
                        DocService.getInstance().deleteDocByDID(ucmFileRow.getUcmDid().toString());
                    } catch (NamingException e) {
                        e.printStackTrace();
                    } catch (IdcClientException e) {
                        e.printStackTrace();
                    }
                }
                CommonUtil.getOfflineAM().deleteTask(new Number(docId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        CommonUtil.getOfflineAM().getDBTransaction().commit();
    }

    public void forceDeleteTaskDialogListener(DialogEvent dialogEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            try {
                CommonUtil.getOfflineAM().deleteTask(new Number(docId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        CommonUtil.getOfflineAM().getDBTransaction().commit();
    }

    public void viewTaskActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        Integer docId = null;
        if (null != currSelectedList && currSelectedList.size() > 0) {
            String key = currSelectedList.get(0);
            docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
        }
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/offline/inline/taskView.jsf?docId=" + docId;
        this.openWindow(url);
    }

    public void downloadTaskInfoActionListener(ActionEvent actionEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        this.setExpressionValue("#{pageFlowScope.downloadFileName}", sdf.format(new Date()) + ".zip");
        System.out.println("download file name---->" + this.resolveExpression("#{pageFlowScope.downloadFileName}"));
        this.executeCompomentAction(this.getBatchDownloadButton().getClientId());
    }

    public void batchDownloadActionListener(FacesContext facesContext, OutputStream outputStream) {
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
            File zipFile = null;
            zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            for (String key : currSelectedList) {
                try {
                    Number docId = new Number(key.substring(key.lastIndexOf("_") + 1));
                    System.out.println(docId);
                    List<Row> ucmFileList = CommonUtil.getWorkAM().getUCMFileByDocId(docId);
                    for (Row row : ucmFileList) {
                        Number ucmDID = (Number)row.getAttribute("UcmDid");
                        String fileName = (String)row.getAttribute("FileName");
                        String atrribute15 = (String)row.getAttribute("Attribute15");
                        InputStream is = null;
                        if (atrribute15 != null) {
                            is = CommonUtil.getInputStreamByUrl(atrribute15);
                        } else {
                            is = DocService.getInstance().getFile(ucmDID.toString());
                        }
                        ZipEntry entry = new ZipEntry(fileName);
                        out.putNextEntry(entry);
                        byte[] b = new byte[1024 * 512];
                        int len = 0;
                        while ((len = is.read(b)) != -1) {
                            out.write(b, 0, len);
                        }
                        out.closeEntry();
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.close();
            FileInputStream fisRar = new FileInputStream(zipAttPath);
            int len = 0;
            byte[] bytes = new byte[100000];
            while ((len = fisRar.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            fisRar.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBatchDownloadButton(RichCommandButton batchDownloadButton) {
        this.batchDownloadButton = batchDownloadButton;
    }

    public RichCommandButton getBatchDownloadButton() {
        return batchDownloadButton;
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void setForceDeletePopup(RichPopup forceDeletePopup) {
        this.forceDeletePopup = forceDeletePopup;
    }

    public RichPopup getForceDeletePopup() {
        return forceDeletePopup;
    }
}
