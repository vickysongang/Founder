package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.viewobject.theme.query.CmsThemeExportLibVOImpl;
import com.zypg.cms.work.view.model.ThemeResourceModel;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DaoUtil;
import com.zypg.cms.work.view.util.ThemeExportUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
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


public class ThemeExportManagedBean extends CustomManagedBean {
    private RichCommandButton exportButton;
    private RichPoll poll;
    private RichRegion logRegion;

    public ThemeExportManagedBean() {

    }

    public void initThemeExport() {
        this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        String docIds = (String)this.resolveExpression("#{pageFlowScope.docIds}");
        System.out.println("initThemeExport--->docIds:::" + docIds);
        if (docIds == null) {
            return;
        }
        for (String key : docIds.split(",")) {
            String docId = key.substring(key.lastIndexOf("_") + 1);
            CommonUtil.getWorkAM().createExportLog(docId, "THEME");
        }
    }

    public void exportThemeActionListener(ActionEvent actionEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String downloadFileName = "中原大地_" + CommonUtil.getCompName() + "_" + sdf.format(new Date()) + ".zip";
        this.setExpressionValue("#{pageFlowScope.downloadFileName}", downloadFileName);
        this.startPoll(poll);
        try {
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            DaoUtil.updateThemeExportLogStatus(conn, "1", "THEME", CommonUtil.getUserId());
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.executeCompomentAction(this.getExportButton().getClientId());
        this.refreshUIComponent(this.getLogRegion());
        this.getLogRegion().refresh(FacesContext.getCurrentInstance());
    }

    public void themeExportListener(FacesContext facesContext, OutputStream outputStream) {
        try {
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            String docIds = (String)this.resolveExpression("#{pageFlowScope.docIds}");
            if (docIds == null) {
                return;
            }
            String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
            File zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            for (String key : docIds.split(",")) {
                try {
                    Number themeDocId = new Number(key);
                    //生成 主题.xml
                    String themeName =
                        (String)CommonUtil.getThemeAM().getThemeRow(themeDocId).getAttribute("ThemeName");
                    String xmlFilePath = zipAttPath + "THEME_" + themeDocId + ".xml";
                    Document document =
                        ThemeExportUtil.generateTreeXml(DaoUtil.getThemeTreeNodes(themeDocId.toString()), null);
                    ThemeExportUtil.writeDocument(document, xmlFilePath);
                    putFileIntoZipEntry(out, xmlFilePath, themeName + "/主题.xml");
                    DaoUtil.updateThemeExpoxtToFinishedStatus(conn, "2", "THEME", themeName + "/主题.xml",
                                                              CommonUtil.getUserId());
                    CmsThemeExportLibVOImpl exportVO = CommonUtil.getThemeAM().getCmsThemeExportLibVO();
                    exportVO.setbvDocId(themeDocId);
                    exportVO.executeQuery();
                    exportVO.setRangeSize(-1);
                    for (Row row : exportVO.getAllRowsInRange()) {
                        String libCode = (String)row.getAttribute("LibCode");
                        System.out.println("libCode:" + libCode);
                        String libName = (String)row.getAttribute("LibName");
                        List<ThemeResourceModel> resourceList = DaoUtil.getThemeResourceList(themeDocId, libCode);
                        Document doc = ThemeExportUtil.generateRefResourceXml(resourceList, libCode, libName);
                        String path = zipAttPath + libCode + themeDocId + ".xml";
                        ThemeExportUtil.writeDocument(doc, path);
                        putFileIntoZipEntry(out, path, themeName + "/" + libName + ".xml");
                        for (ThemeResourceModel resource : resourceList) {
                            List<Row> ucmFileList =
                                CommonUtil.getWorkAM().getUCMFileByDocId(new Number(resource.getRefDocId()));
                            System.out.println("【" + resource.getResourceName() + "】的文件数:" + ucmFileList.size());
                            for (Row file : ucmFileList) {
                                Number ucmDID = (Number)file.getAttribute("UcmDid");
                                Number docId = (Number)file.getAttribute("DocId");
                                Object fileName = file.getAttribute("FileName");
                                String ftpPath = (String)file.getAttribute("FtpPath");
                                String attribute3 = (String)file.getAttribute("Attribute3");
                                Number refDocId = (Number)file.getAttribute("RefDocId");
                                //下载原始文件
                                String filePath = null;
                                if (ftpPath == null || ftpPath.length() == 0) {
                                    filePath = themeName + "/files/" + libName + "/" + docId + "/" + fileName;
                                } else {
                                    String attachCode = DaoUtil.getAttachCodeByPath(conn, ftpPath);
                                    if ("MAIN_TYPESET_FILE".equals(attachCode) ||
                                        "COVER_TYPESET_FILE".equals(attachCode)) {
                                        System.out.println("排版文件...");
                                        continue;
                                    }
                                    filePath =
                                            themeName + "/files/" + libName + "/" + docId + "/" + ftpPath + "/" + fileName;
                                }

                                String attribute15 = (String)file.getAttribute("Attribute15");
                                InputStream ucmIs = null;
                                if (attribute15 != null) {
                                    ucmIs = CommonUtil.getInputStreamByUrl(attribute15);
                                } else {
                                    ucmIs = DocService.getInstance().getFile(ucmDID.toString());
                                }
                                ZipEntry fileEntry = new ZipEntry(filePath);
                                out.putNextEntry(fileEntry);
                                byte[] b2 = new byte[1024 * 512];
                                int len = 0;
                                while ((len = ucmIs.read(b2)) != -1) {
                                    out.write(b2, 0, len);
                                }
                                out.closeEntry();
                                ucmIs.close();
                                DaoUtil.updateThemeExpoxtToFinishedStatus(conn, "2", "THEME", filePath,
                                                                          CommonUtil.getUserId());
                                if ("AUDIO".equals(libCode) || "ILLUSTRATION".equals(libCode) ||
                                    "MATERIAL_FIGURE".equals(libCode) || "PHOTOGRAPHY_FIGURE".equals(libCode) ||
                                    "AUDIO".equals(libCode) || "VIDEO".equals(libCode)) {
                                    //下载缩略图
                                    String thumbnailUrl = CommonUtil.getWorkAM().getThumbnailUrl(docId);
                                    if (thumbnailUrl != null && thumbnailUrl.length() > 0) {
                                        String thumbnailName =
                                            thumbnailUrl.substring(thumbnailUrl.lastIndexOf("/") + 1);
                                        String thumbnailPath = zipAttPath + libCode + thumbnailName;
                                        CommonUtil.download(thumbnailUrl, thumbnailPath);
                                        putFileIntoZipEntry(out, thumbnailPath,
                                                            themeName + "/files/" + libName + "/" + docId + "/" +
                                                            "缩略图" + "/" + thumbnailName);
                                    }
                                    //下载预览文件
                                    String previewUrl = CommonUtil.getWorkAM().getPreviewUrl(docId);
                                    if (previewUrl != null && previewUrl.length() > 0) {
                                        String previewName = previewUrl.substring(previewUrl.lastIndexOf("/") + 1);
                                        String previewPath = zipAttPath + libCode + previewName;
                                        CommonUtil.download(previewUrl, previewPath);
                                        putFileIntoZipEntry(out, previewPath,
                                                            themeName + "/files/" + libName + "/" + docId + "/" +
                                                            "预览文件" + "/" + previewName);
                                    }
                                } else {
                                    String coverUrl = CommonUtil.getWorkAM().getThumbnailUrl(docId);
                                    if (coverUrl != null && coverUrl.length() > 0) {
                                        String coverName = coverUrl.substring(coverUrl.lastIndexOf("/") + 1);
                                        String thumbnailPath = zipAttPath + libCode + coverName;
                                        CommonUtil.download(coverUrl, thumbnailPath);
                                        putFileIntoZipEntry(out, thumbnailPath,
                                                            themeName + "/files/" + libName + "/" + docId + "/" +
                                                            "封面缩略图" + "/" + coverName);
                                    }
                                    String thumbnailUrl = CommonUtil.getWorkAM().getThumbnailUrl(refDocId);
                                    if (thumbnailUrl != null && thumbnailUrl.length() > 0) {
                                        String thumbnailName =
                                            thumbnailUrl.substring(thumbnailUrl.lastIndexOf("/") + 1);
                                        String thumbnailPath = zipAttPath + libCode + thumbnailName;
                                        CommonUtil.download(thumbnailUrl, thumbnailPath);
                                        putFileIntoZipEntry(out, thumbnailPath,
                                                            themeName + "/files/" + libName + "/" + docId + "/" +
                                                            (ftpPath == null ? "" : ftpPath) + "/" + thumbnailName);
                                    }
                                    String previewUrl = CommonUtil.getWorkAM().getPreviewUrl(refDocId);
                                    if (previewUrl != null && previewUrl.length() > 0) {
                                        String previewName = previewUrl.substring(previewUrl.lastIndexOf("/") + 1);
                                        String previewPath = zipAttPath + libCode + previewName;
                                        CommonUtil.download(previewUrl, previewPath);
                                        putFileIntoZipEntry(out, previewPath,
                                                            themeName + "/files/" + libName + "/" + docId + "/" +
                                                            (ftpPath == null ? "" : ftpPath) + "/" + previewName);
                                    }
                                }

                                //下载条目库处理过程中生成的zip包
                                if (attribute3 != null && attribute3.length() > 0) {
                                    System.out.println("attribute3:" + attribute3);
                                    String resultFileName = attribute3.substring(attribute3.lastIndexOf("/") + 1);
                                    String resultFilePath =
                                        themeName + "/files/" + libName + "/" + docId + "/" + resultFileName;
                                    putFileIntoZipEntry(out, attribute3, resultFilePath);
                                }
                            }
                        }
                        DaoUtil.updateThemeExpoxtToFinishedStatus(conn, "2", "THEME",
                                                                  themeName + "/" + libName + ".xml",
                                                                  CommonUtil.getUserId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            DaoUtil.updateThemeExportLogStatus(conn, "2", "THEME", CommonUtil.getUserId());
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
            this.stopPoll(poll);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

    public void setPoll(RichPoll poll) {
        this.poll = poll;
    }

    public RichPoll getPoll() {
        return poll;
    }

    public void setLogRegion(RichRegion logRegion) {
        this.logRegion = logRegion;
    }

    public RichRegion getLogRegion() {
        return logRegion;
    }

    public void setExportButton(RichCommandButton exportButton) {
        this.exportButton = exportButton;
    }

    public RichCommandButton getExportButton() {
        return exportButton;
    }
}
