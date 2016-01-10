package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.excel.utils.ExportExcelUtil;
import com.zypg.cms.work.view.model.TempShelfDownloadExcelModel;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DaoUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.naming.NamingException;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class SearchExportManagedBean extends CustomManagedBean {
    private RichCommandButton exportButton;
    private RichSelectBooleanCheckbox coverCheckbox;
    private RichSelectBooleanCheckbox lowpdfCheckbox;
    private RichSelectBooleanCheckbox epubCheckbox;
    private RichSelectBooleanCheckbox mobiCheckbox;

    public SearchExportManagedBean() {
    }

    public void initSearchExport() {
        CommonUtil.getResExpAM().query4SearchExport("reset", CommonUtil.getCompCode());
    }

    public void searchActionListener(ActionEvent actionEvent) {
        CommonUtil.getResExpAM().query4SearchExport("search", CommonUtil.getCompCode());
    }

    public void resetActionListener(ActionEvent actionEvent) {
        CommonUtil.getResExpAM().query4SearchExport("reset", CommonUtil.getCompCode());
    }

    public void exportAttacthActionListener(ActionEvent actionEvent) {
        Boolean coverFlag = (Boolean)this.getCoverCheckbox().getValue();
        Boolean lowpdfFlag = (Boolean)this.getLowpdfCheckbox().getValue();
        Boolean epubFlag = (Boolean)this.getEpubCheckbox().getValue();
        Boolean mobiFlag = (Boolean)this.getMobiCheckbox().getValue();
        if (!coverFlag && !lowpdfFlag && !epubFlag && !mobiFlag) {
            this.addFormattedMessage(null, "请选择导出类型", FacesMessage.SEVERITY_INFO);
            return;
        }
        DCIteratorBinding dcib = this.findIterator("CmsSearchExportVOIterator");
        dcib.setRangeSize(-1);
        if (dcib.getEstimatedRowCount() > 0) {
            this.executeCompomentAction(this.getExportButton().getClientId());
        } else {
            this.addFormattedMessage(null, "没有可导出的记录", FacesMessage.SEVERITY_WARN);
        }
    }

    public void exportUcmAttach(String ucmDid, String compName, String bookName, String fileName,
                                ZipOutputStream out) throws NamingException, SQLException, IdcClientException,
                                                            IOException {
        InputStream ucmIs = DocService.getInstance().getFile(ucmDid);
        if (ucmIs != null) {
            ZipEntry fileEntry = new ZipEntry(compName + File.separator + bookName + File.separator + fileName);
            out.putNextEntry(fileEntry);
            byte[] b2 = new byte[1024 * 512];
            int len = 0;
            while ((len = ucmIs.read(b2)) != -1) {
                out.write(b2, 0, len);
            }
            out.closeEntry();
            ucmIs.close();
        }
    }

    public void exportLowpdf(Connection conn, Row row, ZipOutputStream out) throws SQLException, NamingException,
                                                                                   IdcClientException, IOException {
        Number docId = (Number)row.getAttribute("DocId");
        String bookName = (String)row.getAttribute("BookName");
        String compName = (String)row.getAttribute("CompName");
        System.out.println("导出低精度PDF:" + docId + "  " + compName + "  " + bookName);
        List<Map<String, Object>> lowPdfList = DaoUtil.getDocResByDocId(conn, docId, "LOW_PDF");
        for (Map<String, Object> lowPdfMap : lowPdfList) {
            exportUcmAttach(lowPdfMap.get("ucmDid").toString(), compName, bookName, (String)lowPdfMap.get("fileName"),
                            out);
        }
    }

    public void exportCover(Connection conn, Row row, ZipOutputStream out) throws SQLException, NamingException,
                                                                                  IdcClientException, IOException {
        Number docId = (Number)row.getAttribute("DocId");
        String bookName = (String)row.getAttribute("BookName");
        String compName = (String)row.getAttribute("CompName");
        System.out.println("导出封面:" + docId + "  " + compName + "  " + bookName);
        Map<String, Object> coverMap = DaoUtil.getCoverByDocId(conn, docId);
        if (coverMap != null && coverMap.size() > 0) {
            exportUcmAttach(coverMap.get("ucmDid").toString(), compName, bookName, (String)coverMap.get("fileName"),
                            out);
        }
    }

    public void exportEpub(Connection conn, Row row, ZipOutputStream out) throws SQLException, NamingException,
                                                                                 IdcClientException, IOException {
        Number docId = (Number)row.getAttribute("DocId");
        String bookName = (String)row.getAttribute("BookName");
        String compName = (String)row.getAttribute("CompName");
        System.out.println("导出EPUB:" + docId + "  " + compName + "  " + bookName);
        List<Map<String, Object>> epubList = DaoUtil.getDocAttachByDocId(conn, docId, "epub");
        for (Map<String, Object> epubMap : epubList) {
            exportUcmAttach(epubMap.get("ucmDid").toString(), compName, bookName, (String)epubMap.get("fileName"),
                            out);
        }
    }

    public void exportMobi(Connection conn, Row row, ZipOutputStream out) throws SQLException, NamingException,
                                                                                 IdcClientException, IOException {
        Number docId = (Number)row.getAttribute("DocId");
        String bookName = (String)row.getAttribute("BookName");
        String compName = (String)row.getAttribute("CompName");
        System.out.println("导出MOBI:" + docId + "  " + compName + "  " + bookName);
        List<Map<String, Object>> mobiList = DaoUtil.getDocAttachByDocId(conn, docId, "mobi");
        for (Map<String, Object> mobiMap : mobiList) {
            exportUcmAttach(mobiMap.get("ucmDid").toString(), compName, bookName, (String)mobiMap.get("fileName"),
                            out);
        }
    }

    public void exportAttachListener(FacesContext facesContext, OutputStream outputStream) {
        Boolean coverFlag = (Boolean)this.getCoverCheckbox().getValue();
        Boolean lowpdfFlag = (Boolean)this.getLowpdfCheckbox().getValue();
        Boolean epubFlag = (Boolean)this.getEpubCheckbox().getValue();
        Boolean mobiFlag = (Boolean)this.getMobiCheckbox().getValue();
        DCIteratorBinding dcib = this.findIterator("CmsSearchExportVOIterator");
        dcib.setRangeSize(-1);
        try {
            String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
            File zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            boolean isAllExport = true;

            for (Row row : dcib.getAllRowsInRange()) {
                Object flag = row.getAttribute("Checked");
                if (flag != null && flag.toString().length() > 0) {
                    if ("true".equals(flag.toString())) {
                        isAllExport = false;
                        if (coverFlag) {
                            this.exportCover(conn, row, out);
                        }
                        if (lowpdfFlag) {
                            this.exportLowpdf(conn, row, out);
                        }
                        if (epubFlag) {
                            this.exportEpub(conn, row, out);
                        }
                        if (mobiFlag) {
                            this.exportMobi(conn, row, out);
                        }
                    }
                }
            }

            if (isAllExport) {
                for (Row row : dcib.getAllRowsInRange()) {
                    if (coverFlag) {
                        this.exportCover(conn, row, out);
                    }
                    if (lowpdfFlag) {
                        this.exportLowpdf(conn, row, out);
                    }
                    if (epubFlag) {
                        this.exportEpub(conn, row, out);
                    }
                    if (mobiFlag) {
                        this.exportMobi(conn, row, out);
                    }
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
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 全选
     * */
    public void selectAllAction(ValueChangeEvent valueChangeEvent) {
        Object flag = valueChangeEvent.getNewValue();
        CommonUtil.getResExpAM().selectResExpAllAction(flag + "");
    }

    public void setExportButton(RichCommandButton exportButton) {
        this.exportButton = exportButton;
    }

    public RichCommandButton getExportButton() {
        return exportButton;
    }

    public void setCoverCheckbox(RichSelectBooleanCheckbox coverCheckbox) {
        this.coverCheckbox = coverCheckbox;
    }

    public RichSelectBooleanCheckbox getCoverCheckbox() {
        return coverCheckbox;
    }

    public void setLowpdfCheckbox(RichSelectBooleanCheckbox lowpdfCheckbox) {
        this.lowpdfCheckbox = lowpdfCheckbox;
    }

    public RichSelectBooleanCheckbox getLowpdfCheckbox() {
        return lowpdfCheckbox;
    }

    public void setEpubCheckbox(RichSelectBooleanCheckbox epubCheckbox) {
        this.epubCheckbox = epubCheckbox;
    }

    public RichSelectBooleanCheckbox getEpubCheckbox() {
        return epubCheckbox;
    }

    public void setMobiCheckbox(RichSelectBooleanCheckbox mobiCheckbox) {
        this.mobiCheckbox = mobiCheckbox;
    }

    public RichSelectBooleanCheckbox getMobiCheckbox() {
        return mobiCheckbox;
    }
}
