package com.zypg.cms.work.view.util;

import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.ucm.xml.XmlModel;
import com.zypg.cms.work.model.am.ResExpAMImpl;

import com.zypg.cms.work.model.viewobject.resexp.query.CmsResAttachExpVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResAttachExpVORowImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyLVVOImpl;

import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyLVVORowImpl;

import com.zypg.cms.work.model.viewobject.resexp.query.CmsResMetadataExpVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResMetadataExpVORowImpl;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import oracle.jbo.Row;

import oracle.jbo.domain.ClobDomain;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import oracle.stellent.ridc.IdcClientException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ResDownloadUtil {
    public ResDownloadUtil() {
        super();
    }

    public static void generateDocAttribute(ResExpAMImpl am, Element resRoot, String applyId, Number docId,
                                            Map<String, String> datas,String libCode) throws SQLException {
        Element resEle = resRoot.addElement("resource");
        Element metadataTypeEle = resEle.addElement("metadata");
        metadataTypeEle.addAttribute("name", "type");
        metadataTypeEle.setText("基本信息");
        
        Element contentTypeEle = resEle.addElement("metadata");
        contentTypeEle.addAttribute("name", "contentType");
        contentTypeEle.setText(libCode);
        
        CmsResMetadataExpVOImpl metaDataVO = am.getCmsResMetadataExpVO();
        metaDataVO.setbvApplyId(new Number(applyId));
        metaDataVO.setbvMetaType("METADATA");
        metaDataVO.setbvDocId(docId);
        metaDataVO.executeQuery();
        metaDataVO.setRangeSize(-1);
        if (metaDataVO.getEstimatedRowCount() > 0) {
            String dynamicSql = "select";
            String tableName = "";
            List<String[]> attrs = new ArrayList<String[]>();
            for (Row rw : metaDataVO.getAllRowsInRange()) {
                CmsResMetadataExpVORowImpl metaRow = (CmsResMetadataExpVORowImpl)rw;
                if ("select".equals(dynamicSql)) {
                    dynamicSql = dynamicSql + " " + metaRow.getColumnName();
                    tableName = metaRow.getTableName();
                    String[] attArray = new String[2];
                    attArray[0] = metaRow.getColumnName();
                    attArray[1] = metaRow.getResName();
                    attrs.add(attArray);
                } else {
                    dynamicSql = dynamicSql + " ," + metaRow.getColumnName();
                    String[] attArray = new String[2];
                    attArray[0] = metaRow.getColumnName();
                    attArray[1] = metaRow.getResName();
                    attrs.add(attArray);
                }
            }
            if (!"select".equals(dynamicSql)) {
                dynamicSql = dynamicSql + " from " + tableName + " where doc_id=" + docId.toString();
            }
            System.out.println("dynamicSql:" + dynamicSql);
            Object existVo = am.findViewObject("dynamocVO4DocAttribute");
            if (existVo != null) {
                am.deleteChild(existVo);
            }
            ViewObjectImpl dynamocVo =
                (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocAttribute", dynamicSql);
            dynamocVo.executeQuery();
            dynamocVo.setRangeSize(-1);
            if (dynamocVo.getEstimatedRowCount() > 0) {
                Row drw = dynamocVo.getRowAtRangeIndex(0);
                for (String[] attArray : attrs) {
                    Element meta = resEle.addElement("metadata");
                    meta.addAttribute("name", attArray[0]);
                    meta.addAttribute("trans", attArray[1]);
                    String value =
                        drw.getAttribute(attArray[0]) == null ? "" : drw.getAttribute(attArray[0]).toString();
                    meta.setText(value);
                    datas.put(attArray[1], value);
                }
            }
        }
    }

    public static void generateJinbianInfo(ResExpAMImpl am, Element resRoot, String applyId, Number docId,
                                           Map<String, String> datas) throws SQLException {
        CmsResMetadataExpVOImpl metaDataVO = am.getCmsResMetadataExpVO();
        metaDataVO.setbvApplyId(new Number(applyId));
        metaDataVO.setbvMetaType("JB_INFO");
        metaDataVO.setbvDocId(docId);
        metaDataVO.executeQuery();
        metaDataVO.setRangeSize(-1);
        if (metaDataVO.getEstimatedRowCount() > 0) {
            String dynamicSql = "select";
            String tableName = "";
            List<String[]> attrs = new ArrayList<String[]>();
            for (Row rw : metaDataVO.getAllRowsInRange()) {
                CmsResMetadataExpVORowImpl metaRow = (CmsResMetadataExpVORowImpl)rw;
                if ("select".equals(dynamicSql)) {
                    dynamicSql = dynamicSql + " " + metaRow.getColumnName();
                    tableName = metaRow.getTableName();
                    String[] attArray = new String[2];
                    attArray[0] = metaRow.getColumnName();
                    attArray[1] = metaRow.getResName();
                    attrs.add(attArray);
                } else {
                    dynamicSql = dynamicSql + " ," + metaRow.getColumnName();
                    String[] attArray = new String[2];
                    attArray[0] = metaRow.getColumnName();
                    attArray[1] = metaRow.getResName();
                    attrs.add(attArray);
                }
            }

            if (!"select".equals(dynamicSql)) {
                dynamicSql = dynamicSql + " from " + tableName + " where doc_id=" + docId.toString();
            }

            Object existVo = am.findViewObject("dynamocVO4DocAttribute");
            if (existVo != null) {
                am.deleteChild(existVo);
            }

            ViewObjectImpl dynamocVo =
                (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocAttribute", dynamicSql);
            dynamocVo.executeQuery();
            dynamocVo.setRangeSize(-1);
            if (dynamocVo.getEstimatedRowCount() > 0) {
                Row drw = dynamocVo.getRowAtRangeIndex(0);
                for (String[] attArray : attrs) {
                    Element resourcesModel = resRoot.addElement("resource");
                    
                    Element type = resourcesModel.addElement("metadata");
                    type.addAttribute("name", "type");
                    type.setText("精编信息");

                    Element contentType = resourcesModel.addElement("metadata");
                    contentType.addAttribute("name", "contentType");
                    contentType.setText(attArray[1]);

                    Element topic = resourcesModel.addElement("metadata");
                    topic.addAttribute("name", "topic");
                    topic.setText(attArray[1]);

                    Element author = resourcesModel.addElement("metadata");
                    author.addAttribute("name", "author");
                    author.setText("<![CDATA[ ]]>");

                    String value =
                        drw.getAttribute(attArray[0]) == null ? "" : drw.getAttribute(attArray[0]).toString();
                    Element content = resourcesModel.addElement("metadata");
                    content.addAttribute("name", "content");
                    content.setText("<![CDATA[" + value + "]]>");
                    datas.put(attArray[1], value);
                }
            }
        }
    }

    public static void generateXCInfo(ResExpAMImpl am, Element resRoot, String applyId, Number docId,
                                      Map<String, String> datas) throws SQLException {
        CmsResMetadataExpVOImpl metaDataVO = am.getCmsResMetadataExpVO();
        metaDataVO.setbvApplyId(new Number(applyId));
        metaDataVO.setbvMetaType("XC_INFO");
        metaDataVO.setbvDocId(docId);
        metaDataVO.executeQuery();
        metaDataVO.setRangeSize(-1);
        if (metaDataVO.getEstimatedRowCount() > 0) {
            String tableName = "CMS_XC_INFO_T";
            List<String[]> attrs = new ArrayList<String[]>();
            for (Row rw : metaDataVO.getAllRowsInRange()) {
                CmsResMetadataExpVORowImpl metaRow = (CmsResMetadataExpVORowImpl)rw;
                String[] attArray = new String[2];
                attArray[0] = metaRow.getColumnName();
                attArray[1] = metaRow.getResName();
                attrs.add(attArray);
            }

            for (String[] cts : attrs) {
                String xcKey = cts[1];
                String xcValue = "";
                String sql =
                    "select TITLE, AUTHOR, CONTENT, TYPE from " + tableName + " where TYPE='" + cts[0] + "' and DOC_ID=" +
                    docId;
                Object existVo = am.findViewObject("dynamocVO4DocXCInfo");
                if (existVo != null) {
                    am.deleteChild(existVo);
                }
                ViewObjectImpl dynamocVo =
                    (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocXCInfo", sql);
                dynamocVo.executeQuery();
                dynamocVo.setRangeSize(-1);
                if (dynamocVo.getEstimatedRowCount() > 0) {
                    for (Row xcrow : dynamocVo.getAllRowsInRange()) {
                        Element resourcesModel = resRoot.addElement("resource");
                        Element type = resourcesModel.addElement("metadata");
                        type.addAttribute("name", "type");
                        type.setText("宣传信息");

                        Element contentType = resourcesModel.addElement("metadata");
                        contentType.addAttribute("name", "contentType");
                        contentType.setText(cts[1]);

                        Element topic = resourcesModel.addElement("metadata");
                        topic.addAttribute("name", "topic");
                        topic.setText((String)xcrow.getAttribute("TITLE"));

                        Element author = resourcesModel.addElement("metadata");
                        author.addAttribute("name", "author");
                        author.setText("<![CDATA[" + (String)xcrow.getAttribute("AUTHOR") + "]]>");

                        Element content = resourcesModel.addElement("metadata");
                        content.addAttribute("name", "content");
                        ClobDomain value = (ClobDomain)xcrow.getAttribute("CONTENT");
                        content.setText("<![CDATA[" + value.toString() + "]]>");
                        if ("".equalsIgnoreCase(xcValue)) {
                            xcValue = value.toString();
                        } else {
                            xcValue = xcValue + "\n" +
                                    value.toString();
                        }
                    }
                    datas.put(xcKey, xcValue);
                }
            }
        }
    }

    public static void generateAttachInfo(ResExpAMImpl am, Element resRoot, String applyId, Number docId,
                                          String resName, Number lineId, ZipOutputStream out) throws SQLException,
                                                                                                     NamingException,
                                                                                                     IdcClientException,
                                                                                                     IOException {
        CmsResAttachExpVOImpl attchVo = am.getCmsResAttachExpVO();
        attchVo.setbvApplyId(new Number(applyId));
        attchVo.setbvDocId(docId);
        attchVo.executeQuery();
        attchVo.setRangeSize(-1);
        if (attchVo.getEstimatedRowCount() > 0) {
            for (Row arw : attchVo.getAllRowsInRange()) {
                CmsResAttachExpVORowImpl attachRow = (CmsResAttachExpVORowImpl)arw;
                String fileName = attachRow.getFileName();
                Number ucmDID = attachRow.getUcmDid();
                String attribute15 = attachRow.getAttribute15();
                Element attachResource = resRoot.addElement("resource");
                Element type = attachResource.addElement("metadata");
                type.addAttribute("name", "type");
                type.setText("附件信息");

                Element contentType = attachResource.addElement("metadata");
                contentType.addAttribute("name", "contentType");
                contentType.setText(attachRow.getResCode());

                Element topic = attachResource.addElement("metadata");
                topic.addAttribute("name", "topic");
                topic.setText(fileName);

                Element path = attachResource.addElement("metadata");
                path.addAttribute("name", "path");
                path.setText("docs/" + resName + "_" + lineId + "/" + attachRow.getFilePath() + "/" + fileName);

                InputStream ucmIs = null;
                if (attribute15 != null) {
                    ucmIs = CommonUtil.getInputStreamByUrl(attribute15);
                } else {
                    ucmIs = DocService.getInstance().getFile(ucmDID.toString());
                }
                ZipEntry fileEntry =
                    new ZipEntry("docs/" + resName + "_" + lineId + "/" + attachRow.getFilePath() + "/" + fileName);
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
    }
}
