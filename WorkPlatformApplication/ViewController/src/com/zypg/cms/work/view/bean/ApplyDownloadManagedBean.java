package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.excel.utils.ExportExcelUtil;
import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyLVVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyLVVORowImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpTemplateVOImpl;
import com.zypg.cms.work.view.model.ExportBookModel;
import com.zypg.cms.work.view.model.ExportCategoryModel;
import com.zypg.cms.work.view.model.TempShelfDownloadExcelModel;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DaoUtil;
import com.zypg.cms.work.view.util.ExportDaoUtil;
import com.zypg.cms.work.view.util.ExportHtmlUtil;
import com.zypg.cms.work.view.util.ResDownloadUtil;
import com.zypg.cms.work.view.util.ThemeExportUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class ApplyDownloadManagedBean extends CustomManagedBean {
    private RichCommandButton downloadButton;
    private RichRegion logRegion;
    private RichPoll poll;

    public ApplyDownloadManagedBean() {
    }

    public void init4ApplyDownload() {
        this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        String applyId = (String)this.resolveExpression("#{pageFlowScope.applyId}");
        String downloadType = (String)this.resolveExpression("#{pageFlowScope.downloadType}");
        if (applyId == null) {
            return;
        }
        CommonUtil.getWorkAM().createExportLog(applyId, downloadType);
    }

    public void downloadApplyActionListener(ActionEvent actionEvent) {
        ResExpAMImpl am = CommonUtil.getResExpAM();
        String downloadType = (String)this.resolveExpression("#{pageFlowScope.downloadType}");
        String applyId = (String)this.resolveExpression("#{pageFlowScope.applyId}");
        String applyName = null;
        try {
            applyName = am.getApplyName(new Number(applyId));
        } catch (SQLException e) {
            applyName = "";
        }
        String downloadFileName = CommonUtil.getCompName() + "_" + applyName + ".zip";
        System.out.println("downloadFileName:" + downloadFileName);
        this.setExpressionValue("#{pageFlowScope.downloadFileName}", downloadFileName);
        this.startPoll(poll);
        try {
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            DaoUtil.updateThemeExportLogStatus(conn, "1", downloadType, CommonUtil.getUserId());
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.executeCompomentAction(this.getDownloadButton().getClientId());
        this.refreshUIComponent(this.getLogRegion());
        this.getLogRegion().refresh(FacesContext.getCurrentInstance());
    }

    public void applyDownloadListener(FacesContext facesContext, OutputStream outputStream) throws Exception {
        try {
            ResExpAMImpl am = CommonUtil.getResExpAM();
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            String downloadType = (String)this.resolveExpression("#{pageFlowScope.downloadType}");
            String applyId = (String)this.resolveExpression("#{pageFlowScope.applyId}");
            if (applyId == null) {
                return;
            }
            if ("TEMPLATE".equals(downloadType)) {
                String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
                File zipFile = new File(zipAttPath);
                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(zipFile);
                CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
                ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
                out.setEncoding("GBK");
                //生成index.xml

                //1、查询行表
                CmsResExpApplyLVVOImpl applyLineVo = am.getCmsResExpApplyLVVO();
                applyLineVo.setbvApplyId(new Number(applyId));
                applyLineVo.executeQuery();
                applyLineVo.setRangeSize(-1);
                if (applyLineVo.getEstimatedRowCount() > 0) {
                    List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
                    Document indexDoc = DocumentHelper.createDocument();
                    Element indexRoot = indexDoc.addElement("Founder");
                    Element docElement = indexRoot.addElement("document");
                    String libCode = null;
                    for (Row row : applyLineVo.getAllRowsInRange()) {
                        CmsResExpApplyLVVORowImpl lineRow = (CmsResExpApplyLVVORowImpl)row;
                        libCode = lineRow.getLibCode();
                        //生成index.xml中的资源
                        Element indexResource = docElement.addElement("resource");
                        indexResource.addAttribute("id", lineRow.getLineId().toString());
                        indexResource.addAttribute("topic", lineRow.getResName());
                        indexResource.addAttribute("path",
                                                   "docs/" + lineRow.getResName() + "_" + lineRow.getLineId() + ".xml");
                        Map<String, String> linedata = new HashMap<String, String>();

                        //生成资源xml文件
                        Document resDoc = DocumentHelper.createDocument();
                        Element resRoot = resDoc.addElement("Founder");
                        Element docResRoot = resRoot.addElement("document");
                        ResDownloadUtil.generateDocAttribute(am, docResRoot, applyId, lineRow.getDocId(), linedata,
                                                             libCode);
                        ResDownloadUtil.generateJinbianInfo(am, docResRoot, applyId, lineRow.getDocId(), linedata);
                        ResDownloadUtil.generateXCInfo(am, docResRoot, applyId, lineRow.getDocId(), linedata);
                        ResDownloadUtil.generateAttachInfo(am, docResRoot, applyId, lineRow.getDocId(),
                                                           lineRow.getResName(), lineRow.getLineId(), out);
                        String resXmlPath = zipAttPath + lineRow.getResName() + "_" + lineRow.getDocId() + ".xml";
                        ThemeExportUtil.writeDocument(resDoc, resXmlPath);
                        putFileIntoZipEntry(out, resXmlPath,
                                            "docs/" + lineRow.getResName() + "_" + lineRow.getLineId() + ".xml");
                        datas.add(linedata);
                    }
                    indexRoot.addAttribute("DocLibCode", libCode);
                    indexRoot.addAttribute("BaseInfoType", "XML");
                    //生成index.xml
                    String indexXmlPath = zipAttPath + applyId + "_" + "index.xml";
                    ThemeExportUtil.writeDocument(indexDoc, indexXmlPath);
                    putFileIntoZipEntry(out, indexXmlPath, "index.xml");
                    List<String> headers = new ArrayList<String>();
                    CmsResExpTemplateVOImpl templateVO = am.getCmsResExpTemplateVO();
                    templateVO.setbvApplyId(new Number(applyId));
                    templateVO.executeQuery();
                    templateVO.setRangeSize(-1);
                    if (templateVO.getEstimatedRowCount() > 0) {
                        //生成index.xls
                        for (Row hrw : templateVO.getAllRowsInRange()) {
                            String hname = (String)hrw.getAttribute("ResName");
                            headers.add(hname);
                        }
                        TempShelfDownloadExcelModel shelfExcelData = new TempShelfDownloadExcelModel(datas, headers);
                        Map<Integer, Integer> wm = new HashMap<Integer, Integer>();
                        ExportExcelUtil excelUtil = new ExportExcelUtil("index", wm, true);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        excelUtil.exportExcel(shelfExcelData, os);
                        byte[] array = os.toByteArray();
                        ByteArrayInputStream xlsIn = new ByteArrayInputStream(array);
                        ZipEntry entry = new ZipEntry("index.xls");
                        out.putNextEntry(entry);
                        byte[] xlsBytes = new byte[1024 * 512];
                        int xlsLen = 0;
                        while ((xlsLen = xlsIn.read(xlsBytes)) != -1) {
                            out.write(xlsBytes, 0, xlsLen);
                        }
                        out.closeEntry();
                        xlsIn.close();
                    }
                    DaoUtil.updateThemeExportLogStatus(conn, "2", downloadType, CommonUtil.getUserId());

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
                    if (conn != null) {
                        conn.close();
                    }
                }
            } else if ("HTML".equals(downloadType)) {
                String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
                File zipFile = null;
                String templatePath = Constants.TEMPLATE_PATH;
                zipFile = new File(zipAttPath);
                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(zipFile);
                CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
                ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
                out.setEncoding("GBK");
                try {
                    //下载模版
                    Map<String, String> fileMap = new HashMap<String, String>();
                    CommonUtil.listDirectory(templatePath, fileMap);
                    List<ExportBookModel> bookModelList = new ArrayList<ExportBookModel>();
                    List<String> docIdList = ExportDaoUtil.getDocIdList(new Number(applyId));
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
                    List<ExportCategoryModel> categoryList = ExportDaoUtil.getCategoryList(new Number(applyId));
                    for (ExportCategoryModel category : categoryList) {
                        List<ExportBookModel> categoryBookList = new ArrayList<ExportBookModel>();
                        List<String> docList =
                            ExportDaoUtil.getCategoryRes(category.getCategoryId(), new Number(applyId));
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
                DaoUtil.updateThemeExportLogStatus(conn, "2", downloadType, CommonUtil.getUserId());
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
            }
            this.stopPoll(poll);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void refreshLogRegion() {
        this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        this.refreshUIComponent(this.getLogRegion());
        this.getLogRegion().refresh(FacesContext.getCurrentInstance());
    }

    public void pollListener(PollEvent pollEvent) {
        this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        this.refreshUIComponent(this.getLogRegion());
        this.getLogRegion().refresh(FacesContext.getCurrentInstance());
    }

    public void startPoll(RichPoll poll) {
        poll.setInterval(5000);
        AdfFacesContext.getCurrentInstance().addPartialTarget(poll);
    }

    public void stopPoll(RichPoll poll) {
        poll.setInterval(-1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(poll);
    }

    public void setDownloadButton(RichCommandButton downloadButton) {
        this.downloadButton = downloadButton;
    }

    public RichCommandButton getDownloadButton() {
        return downloadButton;
    }

    public void setLogRegion(RichRegion logRegion) {
        this.logRegion = logRegion;
    }

    public RichRegion getLogRegion() {
        return logRegion;
    }

    public void setPoll(RichPoll poll) {
        this.poll = poll;
    }

    public RichPoll getPoll() {
        return poll;
    }
}
