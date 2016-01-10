package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.viewobject.common.query.CmsDocUcmRelVORowImpl;
import com.zypg.cms.work.model.viewobject.theme.query.CmsThemeExportLibVOImpl;
import com.zypg.cms.work.model.viewobject.theme.query.CmsThemeVORowImpl;
import com.zypg.cms.work.view.model.ThemeResourceModel;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DaoUtil;
import com.zypg.cms.work.view.util.ThemeExportThread;
import com.zypg.cms.work.view.util.ThemeExportUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
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
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichGoLink;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import org.dom4j.Document;


public class ThemeMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsThemeVORowImpl> tableModel =
        new CustomTableModel<CmsThemeVORowImpl>("WorkAMDataControl", "ThemeAM", "CmsThemeVO", 20, "findThemeVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPopup deletePopup;
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichCommandButton exportButton;

    public ThemeMainManagedBean() {
    }

    public void initTheme() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
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
            //String url = request.getContextPath() + "/faces/work/offline/inline/taskView.jsf?docId=" + docId;
            // this.openWindow(url);
        }
    }


    public void deleteDocDialogActionListener(DialogEvent dialogEvent) {
        List<Number> list = new ArrayList<Number>();
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            for (String key : currSelectedList) {
                Integer id = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                Number docId = new Number(id);
                //删除UCM上的文件
                List<Row> ucmFileRows = CommonUtil.getWorkAM().getUCMFileByDocId(docId);
                for (Row row : ucmFileRows) {
                    CmsDocUcmRelVORowImpl ucmFileRow = (CmsDocUcmRelVORowImpl)row;
                    try {
                        DocService.getInstance().deleteDocByDID(ucmFileRow.getUcmDid().toString());
                    } catch (NamingException e) {
                        e.printStackTrace();
                    } catch (IdcClientException e) {
                        e.printStackTrace();
                    }
                }
                list.add(docId);
            }
            CommonUtil.getWorkAM().destroyDoc(list);
            this.reFindCategory();
            commonRenderer.statusValueChange(CommonUtil.getCurrStatusCode());
        } catch (SQLException e) {
            this.showMessage("删除失败！");
        }
        this.showMessage("删除成功！");
    }


    public void rollbackReturnListener(ReturnEvent returnEvent) {
        commonRenderer.rollbackReturnListener(returnEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void refreshTaskActionListener(ActionEvent actionEvent) {
        initTheme();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void setTableModel(CustomTableModel<CmsThemeVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsThemeVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setExportButton(RichCommandButton exportButton) {
        this.exportButton = exportButton;
    }

    public RichCommandButton getExportButton() {
        return exportButton;
    }

    public void exportThemeActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (String key : currSelectedList) {
            try {
                Number themeDocId = new Number(key.substring(key.lastIndexOf("_") + 1));
                sb.append(themeDocId + ",");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String docIds = sb.substring(0, sb.length() - 1);
        String url = request.getContextPath() + "/faces/work/theme/inline/themeExport.jsf?docIds=" + docIds+"&compCode="+CommonUtil.getCompCode();
        this.openWindow(url);

        //this.executeCompomentAction(this.getExportButton().getClientId());
    }

    public void themeExportListener(FacesContext facesContext, OutputStream outputStream) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        ThemeExportThread themeExport = new ThemeExportThread(currSelectedList, outputStream);
        Thread thread = new Thread(themeExport);
        thread.start();


        //
        //        try {
        //            List<String> currSelectedList =
        //                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        //            if (currSelectedList == null) {
        //                return;
        //            }
        //            //            boolean isOnline = Constants.IS_ONLINE_MODE; //true是线上环境，false是本地环境
        //            boolean isOnline = false;
        //            String zipAttPath = null; //线上的临时目录
        //            File zipFile = null;
        //            if (isOnline) {
        //                zipAttPath = "/home/oracle/cms/entry/export.zip";
        //            } else {
        //                zipAttPath = "F:dowload/export.zip";
        //            }
        //            zipFile = new File(zipAttPath);
        //            FileOutputStream fileOutputStream;
        //            fileOutputStream = new FileOutputStream(zipFile);
        //            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
        //            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
        //            out.setEncoding("GBK");
        //            for (String key : currSelectedList) {
        //                try {
        //                    Number themeDocId = new Number(key.substring(key.lastIndexOf("_") + 1));
        //                    //生成 主题.xml
        //                    String themeName =
        //                        (String)CommonUtil.getThemeAM().getThemeRow(themeDocId).getAttribute("ThemeName");
        //                    String xmlFilePath = zipAttPath + "THEME_" + themeDocId + ".xml";
        //                    Document document =
        //                        ThemeExportUtil.generateTreeXml(DaoUtil.getThemeTreeNodes(themeDocId.toString()), null);
        //                    ThemeExportUtil.writeDocument(document, xmlFilePath);
        //                    FileInputStream fis = new FileInputStream(xmlFilePath);
        //                    ZipEntry themeEntry = new ZipEntry(themeName + "/主题.xml");
        //                    out.putNextEntry(themeEntry);
        //                    byte[] b = new byte[1024 * 512];
        //                    int lenHtml = 0;
        //                    while ((lenHtml = fis.read(b)) != -1) {
        //                        out.write(b, 0, lenHtml);
        //                    }
        //                    out.closeEntry();
        //                    fis.close();
        //                    CmsThemeExportLibVOImpl exportVO = CommonUtil.getThemeAM().getCmsThemeExportLibVO();
        //                    exportVO.setbvDocId(themeDocId);
        //                    exportVO.executeQuery();
        //                    exportVO.setRangeSize(-1);
        //                    for (Row row : exportVO.getAllRowsInRange()) {
        //                        String libCode = (String)row.getAttribute("LibCode");
        //                        System.out.println("libCode:" + libCode);
        //                        String libName = (String)row.getAttribute("LibName");
        //                        List<ThemeResourceModel> resourceList = DaoUtil.getThemeResourceList(themeDocId, libCode);
        //                        Document doc = ThemeExportUtil.generateRefResourceXml(resourceList, libCode, libName);
        //                        String path = zipAttPath + libCode + themeDocId + ".xml";
        //                        ThemeExportUtil.writeDocument(doc, path);
        //                        FileInputStream is = new FileInputStream(path);
        //                        ZipEntry libEntry = new ZipEntry(themeName + "/" + libName + ".xml");
        //
        //                        out.putNextEntry(libEntry);
        //                        byte[] b1 = new byte[1024 * 512];
        //                        int lenHtml1 = 0;
        //                        while ((lenHtml1 = is.read(b1)) != -1) {
        //                            out.write(b1, 0, lenHtml1);
        //                        }
        //                        out.closeEntry();
        //                        is.close();
        //                        for (ThemeResourceModel resource : resourceList) {
        //                            List<Row> ucmFileList =
        //                                CommonUtil.getWorkAM().getUCMFileByDocId(new Number(resource.getRefDocId()));
        //                            System.out.println("【" + resource.getResourceName() + "】的文件数:" + ucmFileList.size());
        //                            for (Row file : ucmFileList) {
        //                                Number ucmDID = (Number)file.getAttribute("UcmDid");
        //                                Number docId = (Number)file.getAttribute("DocId");
        //                                Object fileName = file.getAttribute("FileName");
        //                                InputStream ucmIs = DocService.getInstance().getFile(ucmDID.toString());
        //                                String ftpPath = (String)file.getAttribute("FtpPath");
        //                                String filePath = null;
        //                                if (ftpPath == null || ftpPath.length() == 0) {
        //                                    filePath = themeName + "/files/" + libName + "/" + docId + "/" + fileName;
        //                                } else {
        //                                    filePath =
        //                                            themeName + "/files/" + libName + "/" + docId + "/" + ftpPath + "/" + fileName;
        //                                }
        //                                ZipEntry fileEntry = new ZipEntry(filePath);
        //                                out.putNextEntry(fileEntry);
        //                                byte[] b2 = new byte[1024 * 512];
        //                                int len = 0;
        //                                while ((len = ucmIs.read(b2)) != -1) {
        //                                    out.write(b2, 0, len);
        //                                }
        //                                out.closeEntry();
        //                                ucmIs.close();
        //                            }
        //                        }
        //                    }
        //                } catch (Exception e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //            out.close();
        //            FileInputStream fisRar = new FileInputStream(zipAttPath);
        //            int len = 0;
        //            byte[] bytes = new byte[100000];
        //            while ((len = fisRar.read(bytes)) != -1) {
        //                outputStream.write(bytes, 0, len);
        //            }
        //            outputStream.flush();
        //            outputStream.close();
        //            fisRar.close();
        //        } catch (FileNotFoundException e) {
        //            e.printStackTrace();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
    }

    public void themeMakeActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (null != currSelectedList && currSelectedList.size() > 0) {
                String key = currSelectedList.get(0);
                Number docId = new Number(key.substring(key.lastIndexOf("_") + 1));
                String nodeId = CommonUtil.getThemeAM().preMakeTheme(CommonUtil.getCompCode(), docId);
                this.setExpressionValue("#{sessionScope.docId}", docId);
                String compCode = CommonUtil.getCompCode();
                this.setExpressionValue("#{sessionScope.compCode}", compCode);
                HttpServletRequest request =
                    (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String url =
                    request.getContextPath() + "/faces/work/theme/inline/themeMaker.jsf?nodeId=" + nodeId + "&compCode=" +
                    compCode+"&groupUserFlag="+this.resolveExpression("#{pageFlowScope.groupUserFlag}");
                this.openWindow(url, 1000, 600);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}
