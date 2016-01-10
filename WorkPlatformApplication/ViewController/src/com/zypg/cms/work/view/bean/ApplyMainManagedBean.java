package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.honythink.applicationframework.hadf.util.FileUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.ucm.xml.XmlModel;
import com.zypg.cms.work.model.WorkAMImpl;
import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyHVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyHVORowImpl;
import com.zypg.cms.work.model.viewobject.theme.query.CmsThemeExportLibVOImpl;
import com.zypg.cms.work.view.model.ExportBookModel;
import com.zypg.cms.work.view.model.ExportCategoryModel;
import com.zypg.cms.work.view.model.ThemeResourceModel;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DaoUtil;
import com.zypg.cms.work.view.util.ExportDaoUtil;
import com.zypg.cms.work.view.util.ExportHtmlUtil;
import com.zypg.cms.work.view.util.ResDownloadMetadataUtil;

import com.zypg.cms.work.view.util.ThemeExportUtil;

import com.zypg.cms.work.view.util.XmlUtil;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import org.dom4j.Document;


public class ApplyMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsResExpApplyHVORowImpl> tableModel =
        new CustomTableModel<CmsResExpApplyHVORowImpl>("WorkAMDataControl", "ResExpAM", "CmsResExpApplyHVO", 20,
                                                       "findResExpApplyHVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private ResExpAMImpl resExpAm;
    private WorkAMImpl workAm;
    private RichPopup ucmDownloadPopup;
    private RichCommandButton downloadButton;

    public ApplyMainManagedBean() {
        this.resExpAm = CommonUtil.getResExpAM();
        this.workAm = CommonUtil.getWorkAM();
    }

    public void initApplyMain() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvStatus", CommonUtil.getCurrStatusCode());
        paramMap.put("bvCompCode", CommonUtil.getCompCode());
        paramMap.put("bvUserId", CommonUtil.getUserId());
        System.out.println("initApplyMain paramMap:" + paramMap);
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
    }

    public void tableDblClick(ClientEvent clientEvent) {
        commonRenderer.tableDblSelectAction(clientEvent);
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row;
            try {
                row = (Row)list.get(index);
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
            System.out.println("docId:" + docId);
            this.setExpressionValue("#{pageFlowScope.docId}", docId);
            this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        }
    }

    public void downloadActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null && currSelectedList.size() > 0) {
            String applyIdStr = currSelectedList.get(0);
            applyIdStr = applyIdStr.substring(applyIdStr.lastIndexOf("_") + 1);
            CmsResExpApplyHVOImpl applyHeader = this.resExpAm.getCmsResExpApplyHVO();
            try {
                CmsResExpApplyHVORowImpl applyRow =
                    (CmsResExpApplyHVORowImpl)applyHeader.getRow(new Key(new Object[] { new Number(applyIdStr) }));
                String downloadType = applyRow.getDownloadType();
                HttpServletRequest request =
                    (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String url =
                    request.getContextPath() + "/faces/work/resexp/inline/applyDownload.jsf?applyId=" + applyIdStr +
                    "&downloadType=" + downloadType + "&compCode=" + CommonUtil.getCompCode();
                this.openWindow(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //            CmsResExpApplyHVOImpl applyHeader = this.resExpAm.getCmsResExpApplyHVO();
            //            try {
            //                CmsResExpApplyHVORowImpl applyRow =
            //                    (CmsResExpApplyHVORowImpl)applyHeader.getRow(new Key(new Object[] { new Number(applyIdStr) }));
            //                String downloadType = applyRow.getDownloadType();
            //                String applyName = applyRow.getApplyName();
            //                if ("TEMPLATE".equals(downloadType)) {
            //                    //开始下载工作
            //                    ResDownloadMetadataUtil downloadUtil =
            //                        new ResDownloadMetadataUtil(this.resExpAm, new Number(applyIdStr), applyName);
            //                    XmlModel root = downloadUtil.generateHeaderAndSpace();
            //                    XmlModel connector = downloadUtil.generatorConnector();
            //                    XmlModel template = downloadUtil.generatorTemplate();
            //                    root.addChildren(connector);
            //                    root.addChildren(template);
            //                    String downloadXml = XmlModel.xmlHeader + "\n" +
            //                        root.toXmlString();
            //                    System.out.println(downloadXml);
            //                    String replaceValue =
            //                        downloadXml.replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">",
            //                                                                                                  "&gt;").replaceAll("\n",
            //                                                                                                                     "  ");
            //                    //                    this.appendScript("showDownloadFrame('" + replaceValue + "');");
            //                } else {
            //                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            //                    this.setExpressionValue("#{pageFlowScope.downloadFileName}",
            //                                            applyName + sdf.format(new Date()) + ".zip");
            //                    this.executeCompomentAction(this.getDownloadButton().getClientId());
            //                }
            //            } catch (Exception e) {
            //                e.printStackTrace();
            //            }
        }
    }

    public void exportBookHtmlListener(FacesContext facesContext, OutputStream outputStream) {
        try {
            String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
            File zipFile = null;
            String templatePath = Constants.TEMPLATE_PATH;
            zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList != null && currSelectedList.size() > 0) {
                try {
                    String applyIdStr = currSelectedList.get(0);
                    Number applyId = new Number(applyIdStr.substring(applyIdStr.lastIndexOf("_") + 1));
                    //下载模版
                    Map<String, String> fileMap = new HashMap<String, String>();
                    CommonUtil.listDirectory(templatePath, fileMap);
                    List<ExportBookModel> bookModelList = new ArrayList<ExportBookModel>();
                    List<String> docIdList = ExportDaoUtil.getDocIdList(applyId);
                    //生成图书详细页面
                    for (String docId : docIdList) {
                        ExportBookModel model = ExportDaoUtil.getExportBookModel(docId);
                        bookModelList.add(model);
                        String bookDetailContent = ExportHtmlUtil.generateBookDetailHtml(model);
                        String bookDetailPath = zipAttPath + docId + ".html";
                        writeStringToFile(bookDetailContent, bookDetailPath);
                        putFileIntoZipEntry(out, bookDetailPath,
                                            "/main/Category_" + model.getCatetoryId() + "/" + model.getDocId() + "/" +
                                            model.getDocId() + ".html");
                        //下载缩略图
                        String thumbnailUrl = ExportDaoUtil.getThumbnailUrl(docId);
                        if (thumbnailUrl != null) {
                            String thumbnailPath = zipAttPath + docId + ".jpg";
                            CommonUtil.download(thumbnailUrl, thumbnailPath);
                            putFileIntoZipEntry(out, thumbnailPath,
                                                "/main/Category_" + model.getCatetoryId() + "/" + model.getDocId() +
                                                "/" + model.getDocId() + ".jpg");
                            putFileIntoZipEntry(out, thumbnailPath,
                                                "/main/Category_" + model.getCatetoryId() + "/" + "small_images" +
                                                "/" + model.getDocId() + ".jpg");
                        }
                        //下载样张
                        Map<String, String> map = ExportDaoUtil.getSampleUCMDID(docId);
                        String sampleUcmDid = map.get("ucmDid");
                        String attribute15 = map.get("attribute15"); //用来处理数据库与文件系统关系断裂的文件，在UCM上存在，但是在数据库不存在
                        InputStream is = null;
                        if (attribute15 != null) {
                            is = CommonUtil.getInputStreamByUrl(attribute15);
                        } else {
                            is = DocService.getInstance().getFile(sampleUcmDid);
                        }
                        ZipEntry entry =
                            new ZipEntry("/main/Category_" + model.getCatetoryId() + "/" + model.getDocId() + "/" +
                                         model.getDocId() + "_sample.pdf");
                        out.putNextEntry(entry);
                        byte[] b = new byte[1024 * 512];
                        int len = 0;
                        while ((len = is.read(b)) != -1) {
                            out.write(b, 0, len);
                        }
                        out.closeEntry();
                        is.close();
                    }
                    //生成目录页面
                    List<ExportCategoryModel> categoryList = ExportDaoUtil.getCategoryList(applyId);
                    for (ExportCategoryModel category : categoryList) {
                        List<ExportBookModel> categoryBookList = new ArrayList<ExportBookModel>();
                        List<String> docList = ExportDaoUtil.getCategoryRes(category.getCategoryId(), applyId);
                        for (String docId : docList) {
                            categoryBookList.add(ExportDaoUtil.getExportBookModel(docId));
                        }
                        String categoryContent =
                            ExportHtmlUtil.generateCategoryHtml(category.getCategoryName(), categoryBookList);
                        String categoryPath = zipAttPath + "Category_" + category.getCategoryId() + ".html";
                        writeStringToFile(categoryContent, categoryPath);
                        putFileIntoZipEntry(out, categoryPath, "/main/Category_" + category.getCategoryId() + ".html");
                    }

                    for (Iterator it = fileMap.keySet().iterator(); it.hasNext(); ) {
                        String fileName = (String)it.next();
                        String filePath = fileMap.get(fileName);
                        //更新left.html和main.html的内容
                        if (fileName.equals("left.html") || fileName.equals("main.html")) {
                            String htmlContent = null;
                            if (fileName.equals("left.html")) {
                                htmlContent = ExportHtmlUtil.generateLeftHtml(categoryList);
                            } else {
                                htmlContent = ExportHtmlUtil.generateMainHtml(bookModelList);
                            }
                            writeStringToFile(htmlContent, filePath);
                        }
                        putFileIntoZipEntry(out, filePath, filePath.replace(templatePath, ""));
                    }
                    //替换或更改模版中的文件
                    //生成data.xml
                    String dataXmlPath = zipAttPath + "data.xml";
                    Document document = ExportHtmlUtil.generateDataXml(bookModelList);
                    ThemeExportUtil.writeDocument(document, dataXmlPath);
                    putFileIntoZipEntry(out, dataXmlPath, "data.xml");
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

    public String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char)str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) { //汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        this.refreshUIComponent(commonRenderer.getTable());
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setTableModel(CustomTableModel<CmsResExpApplyHVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsResExpApplyHVORowImpl> getTableModel() {
        return tableModel;
    }

    public void downloadPopupCloseListener(PopupCanceledEvent popupCanceledEvent) {
        RichPopup rp = (RichPopup)popupCanceledEvent.getComponent();
        rp.hide();
    }

    public void setUcmDownloadPopup(RichPopup ucmDownloadPopup) {
        this.ucmDownloadPopup = ucmDownloadPopup;
    }

    public RichPopup getUcmDownloadPopup() {
        return ucmDownloadPopup;
    }

    public void setDownloadButton(RichCommandButton downloadButton) {
        this.downloadButton = downloadButton;
    }

    public RichCommandButton getDownloadButton() {
        return downloadButton;
    }
}
