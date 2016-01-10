package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.view.model.ExpResModel;
import com.zypg.cms.work.view.model.UcmFileModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import oracle.jbo.domain.ClobDomain;
import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;

import org.dom4j.Document;
import org.dom4j.Element;


public class ResourcePushThread implements Runnable {
    private Number docId;

    public ResourcePushThread() {
        super();
    }

    public ResourcePushThread(Number docId) {
        super();
        this.docId = docId;
    }

    @Override
    public void run() {
        try {
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            Map<String, String> result = DaoUtil.getResInfoByDocId(conn, docId);
            String resName = result.get("resName");
            String compName = result.get("compName");
            String libCode = result.get("libCode");
            System.out.println("Push Resource:" + resName + "  " + compName);
            if ("BOOK".equals(libCode)) {
                Document indexDoc = org.dom4j.DocumentHelper.createDocument();
                Element indexRoot = indexDoc.addElement("Founder");
                Element docElement = indexRoot.addElement("document");
                generateDocAttribute(conn, docElement, docId, libCode);
                generateJinbianInfo(conn, docElement, docId, libCode);
                generateXCInfo(conn, docElement, docId, libCode);
                generateAttachInfo(conn, docElement, docId, compName, resName, libCode);
                indexRoot.addAttribute("DocLibCode", libCode);
                indexRoot.addAttribute("BaseInfoType", "XML");
                String indexXmlPath =
                    Constants.RES_EXP_STORE_PATH + File.separator + compName + File.separator + resName;
                File file = new File(indexXmlPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                ThemeExportUtil.writeDocument(indexDoc, indexXmlPath + File.separator + "index.xml");
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateDocAttribute(Connection conn, Element resRoot, Number docId,
                                            String libCode) throws SQLException {
        Element resEle = resRoot.addElement("resource");
        Element metadataTypeEle = resEle.addElement("metadata");
        metadataTypeEle.addAttribute("name", "type");
        metadataTypeEle.setText("基本信息");

        Element contentTypeEle = resEle.addElement("metadata");
        contentTypeEle.addAttribute("name", "contentType");
        contentTypeEle.setText(libCode);

        List<ExpResModel> metadatas = DaoUtil.getExpResModels(conn, "METADATA", libCode);

        if (metadatas.size() > 0) {
            String dynamicSql = "select";
            String tableName = "";
            List<String[]> attrs = new ArrayList<String[]>();
            for (ExpResModel metadata : metadatas) {
                if ("select".equals(dynamicSql)) {
                    dynamicSql = dynamicSql + " " + metadata.getColumnName();
                    tableName = metadata.getTableName();
                    String[] attArray = new String[2];
                    attArray[0] = metadata.getColumnName();
                    attArray[1] = metadata.getResName();
                    attrs.add(attArray);
                } else {
                    dynamicSql = dynamicSql + " ," + metadata.getColumnName();
                    String[] attArray = new String[2];
                    attArray[0] = metadata.getColumnName();
                    attArray[1] = metadata.getResName();
                    attrs.add(attArray);
                }
            }
            if (!"select".equals(dynamicSql)) {
                dynamicSql = dynamicSql + " from " + tableName + " where doc_id=" + docId.toString();
            }
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(dynamicSql);
            while (rs.next()) {
                for (String[] attArray : attrs) {
                    Element meta = resEle.addElement("metadata");
                    meta.addAttribute("name", attArray[0]);
                    meta.addAttribute("trans", attArray[1]);
                    String value = rs.getObject(attArray[0]) == null ? "" : rs.getObject(attArray[0]).toString();
                    meta.setText(value);
                }
            }
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
        }
    }

    public static void generateJinbianInfo(Connection conn, Element resRoot, Number docId,
                                           String libCode) throws SQLException {
        List<ExpResModel> jbInfos = DaoUtil.getExpResModels(conn, "JB_INFO", libCode);
        if (jbInfos.size() > 0) {
            String dynamicSql = "select";
            String tableName = "";
            List<String[]> attrs = new ArrayList<String[]>();
            for (ExpResModel jbInfo : jbInfos) {
                if ("select".equals(dynamicSql)) {
                    dynamicSql = dynamicSql + " " + jbInfo.getColumnName();
                    tableName = jbInfo.getTableName();
                    String[] attArray = new String[2];
                    attArray[0] = jbInfo.getColumnName();
                    attArray[1] = jbInfo.getResName();
                    attrs.add(attArray);
                } else {
                    dynamicSql = dynamicSql + " ," + jbInfo.getColumnName();
                    String[] attArray = new String[2];
                    attArray[0] = jbInfo.getColumnName();
                    attArray[1] = jbInfo.getResName();
                    attrs.add(attArray);
                }
            }

            if (!"select".equals(dynamicSql)) {
                dynamicSql = dynamicSql + " from " + tableName + " where doc_id=" + docId.toString();
            }
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(dynamicSql);
            while (rs.next()) {
                int i = 1;
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

                    String columnTypeName = rs.getMetaData().getColumnTypeName(i);
                    String value = "";
                    if ("CLOB".equals(columnTypeName)) {
                        value =
                                rs.getObject(attArray[0]) == null ? "" : CommonUtil.clobToString(rs.getObject(attArray[0]));
                    } else {
                        value = rs.getObject(attArray[0]) == null ? "" : rs.getObject(attArray[0]).toString();
                    }

                    Element content = resourcesModel.addElement("metadata");
                    content.addAttribute("name", "content");
                    content.setText("<![CDATA[" + value + "]]>");
                    i++;
                }
            }
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
        }
    }

    public static void generateXCInfo(Connection conn, Element resRoot, Number docId,
                                      String libCode) throws SQLException {
        List<ExpResModel> xcInfos = DaoUtil.getExpResModels(conn, "XC_INFO", libCode);
        if (xcInfos.size() > 0) {
            String tableName = "CMS_XC_INFO_T";
            List<String[]> attrs = new ArrayList<String[]>();
            for (ExpResModel xcInfo : xcInfos) {
                String[] attArray = new String[2];
                attArray[0] = xcInfo.getColumnName();
                attArray[1] = xcInfo.getResName();
                attrs.add(attArray);
            }
            for (String[] cts : attrs) {
                String xcValue = "";
                String dynamicSql =
                    "select TITLE, AUTHOR, CONTENT, TYPE from " + tableName + " where TYPE='" + cts[0] +
                    "' and DOC_ID=" + docId;
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(dynamicSql);
                while (rs.next()) {
                    Element resourcesModel = resRoot.addElement("resource");

                    Element type = resourcesModel.addElement("metadata");
                    type.addAttribute("name", "type");
                    type.setText("宣传信息");

                    Element contentType = resourcesModel.addElement("metadata");
                    contentType.addAttribute("name", "contentType");
                    contentType.setText(cts[1]);

                    Element topic = resourcesModel.addElement("metadata");
                    topic.addAttribute("name", "topic");
                    topic.setText((String)rs.getObject("TITLE"));

                    Element author = resourcesModel.addElement("metadata");
                    author.addAttribute("name", "author");
                    author.setText("<![CDATA[" + (String)rs.getObject("AUTHOR") + "]]>");

                    Element content = resourcesModel.addElement("metadata");
                    content.addAttribute("name", "content");
                    ClobDomain value = (ClobDomain)rs.getObject("CONTENT");
                    content.setText("<![CDATA[" + value.toString() + "]]>");
                    if ("".equalsIgnoreCase(xcValue)) {
                        xcValue = value.toString();
                    } else {
                        xcValue = xcValue + "\n" +
                                value.toString();
                    }
                }
                if (rs != null) {
                    rs.close();
                }
                if (stat != null) {
                    stat.close();
                }
            }
        }
    }

    public static void generateAttachInfo(Connection conn, Element resRoot, Number docId, String compName,
                                          String resName, String libCode) throws SQLException, NamingException,
                                                                                 IdcClientException, IOException {
        List<ExpResModel> attachs = DaoUtil.getExpResModels(conn, "ATTACH_TYPE", libCode);
        if (attachs.size() > 0) {
            for (ExpResModel attach : attachs) {
                List<UcmFileModel> fileModels = DaoUtil.getUCMFilesByDocIdAndType(conn, docId, attach.getColumnName());
                for (UcmFileModel file : fileModels) {
                    Element attachResource = resRoot.addElement("resource");
                    Element type = attachResource.addElement("metadata");
                    type.addAttribute("name", "type");
                    type.setText("附件信息");

                    Element contentType = attachResource.addElement("metadata");
                    contentType.addAttribute("name", "contentType");
                    contentType.setText(attach.getResCode());

                    Element topic = attachResource.addElement("metadata");
                    topic.addAttribute("name", "topic");
                    topic.setText(file.getFileName());

                    Element path = attachResource.addElement("metadata");
                    path.addAttribute("name", "path");
                    String attachPath =
                        "attach" + File.separator + file.getFilePath() + File.separator + file.getFileName();
                    path.setText(attachPath);

                    InputStream ucmIs = DocService.getInstance().getFile(file.getUcmDid().toString());
                    //                    InputStream ucmIs = new FileInputStream("C:\\Users\\vicky\\Desktop\\DocType.xml");
                    String attachAbsolutePath =
                        Constants.RES_EXP_STORE_PATH + File.separator + compName + File.separator + resName +
                        File.separator + "attach" + File.separator + file.getFilePath();
                    File attachDir = new File(attachAbsolutePath);
                    if (!attachDir.exists()) {
                        attachDir.mkdirs();
                    }
                    FileOutputStream fos =
                        new FileOutputStream(attachAbsolutePath + File.separator + file.getFileName());
                    byte[] b = new byte[1024];
                    int len = ucmIs.read(b);
                    while (len != -1) {
                        fos.write(b, 0, len);
                        len = ucmIs.read(b);
                    }
                    fos.flush();
                    fos.close();
                    if (ucmIs != null) {
                        ucmIs.close();
                    }
                }
            }
        }
    }
}
