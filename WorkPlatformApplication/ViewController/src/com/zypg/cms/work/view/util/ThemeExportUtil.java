package com.zypg.cms.work.view.util;


import com.zypg.cms.work.view.model.ThemeResourceModel;
import com.zypg.cms.work.view.model.ThemeTreeModel;
import com.zypg.cms.work.view.model.UcmFileModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class ThemeExportUtil {
    public ThemeExportUtil() {
        super();
    }

    public static Document generateTreeXml(List<ThemeTreeModel> list, Element parentNode) {
        Document document = null;
        if (parentNode == null) {
            DocumentFactory factory = new DocumentFactory();
            document = factory.createDocument();
            if (list.size() == 1) {
                ThemeTreeModel model = list.get(0);
                Element root = document.addElement("Theme");
                root.addAttribute("name", model.getName());
                root.addAttribute("id", model.getId());
                root.addAttribute("remarks", model.getRemarks());
                root.addAttribute("compCode", model.getCompCode());
                root.addAttribute("themeDocId", model.getPid());
                root.addAttribute("table", "CMS_THEME_T");
                try {
                    generateResNode(root, DaoUtil.getThemeResourceList(new Number(model.getId())));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                for (ThemeTreeModel model : list) {
                    if ("ROOT".equalsIgnoreCase(model.getType())) {
                        Element root = document.addElement("Theme");
                        root.addAttribute("name", model.getName());
                        root.addAttribute("id", model.getId());
                        root.addAttribute("remarks", model.getRemarks());
                        root.addAttribute("compCode", model.getCompCode());
                        root.addAttribute("themeDocId", model.getPid());
                        root.addAttribute("table", "CMS_THEME_T");
                        generateTreeXml(list, root);
                        break;
                    }
                }
            }
        } else {
            for (ThemeTreeModel model : list) {
                if (model.getPid().equals(parentNode.attributeValue("id"))) {
                    Element child = parentNode.addElement("category");
                    child.addAttribute("name", model.getName());
                    child.addAttribute("id", model.getId());
                    child.addAttribute("pid", model.getPid());
                    child.addAttribute("remarks", model.getRemarks());
                    child.addAttribute("table", "CMS_THEME_TREE_T");
                    try {
                        generateResNode(child, DaoUtil.getThemeResourceList(new Number(model.getId())));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    generateTreeXml(list, child);
                }
            }
        }
        return document;
    }

    public static void generateResNode(Element node, List<ThemeResourceModel> list) {
        for (ThemeResourceModel model : list) {
            if (model.getTreeNodeId().equals(node.attributeValue("id"))) {
                Element child = node.addElement("resource");
                child.addAttribute("resourceId", model.getResourceId());
                child.addAttribute("resourceName", model.getResourceName());
                child.addAttribute("libCode", model.getLibCode());
                child.addAttribute("docId", model.getRefDocId());
                child.addAttribute("source", model.getLibName() + ".xml");
                child.addAttribute("table", "CMS_THEME_RESOURCE_T");
            }
        }
    }

    public static Document generateRefResourceXml(Connection conn, List<ThemeResourceModel> resourcelist,
                                                  String libCode, String libName) {
        String tableName = "CMS_" + libCode.toUpperCase() + "_T";
        DocumentFactory factory = new DocumentFactory();
        Document document = factory.createDocument();
        Element root = document.addElement(libCode + "S");
        for (ThemeResourceModel model : resourcelist) {
            Element node = root.addElement(libCode);
            try {
                fillNode(node, "CMS_DOC_T", model.getRefDocId());
                fillNode(node, tableName, model.getRefDocId());
                if ("BOOK".equals(libCode) || "ELEC_PROD".equals(libCode)) {
                    fillNode(node, "CMS_JB_INFO_T", model.getRefDocId());
                    fillNode(node, "CMS_XC_INFO_T", model.getRefDocId());
                    fillNode(node, "CMS_CHAPTER_T", model.getRefDocId());
                }
                List<UcmFileModel> ucmFileList = DaoUtil.getUCMFilesByDocId(conn, new Number(model.getRefDocId()));
                if (ucmFileList != null && ucmFileList.size() > 0) {
                    Element fileNode = node.addElement("FILES");
                    for (UcmFileModel file : ucmFileList) {
                        String fileName = file.getFileName();
                        Element fileChildNode = fileNode.addElement("FILE");
                        Number docId = file.getDocId();
                        fileChildNode.addAttribute("docId", toString(docId));
                        fileChildNode.addAttribute("ucmDid", toString(file.getUcmDid()));
                        fileChildNode.addAttribute("ucmDocname", toString(file.getUcmDocName()));
                        fileChildNode.addAttribute("fileName", toString(fileName));
                        String filePath = null;
                        String ftpPath = file.getFilePath();
                        if (ftpPath == null || ftpPath.length() == 0) {
                            filePath = "files/" + libName + "/" + docId + "/" + fileName;
                        } else {
                            filePath = "files/" + libName + "/" + docId + "/" + ftpPath + "/" + fileName;
                        }
                        fileChildNode.addAttribute("filePath", filePath);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return document;
    }

    public static Document generateRefResourceXml(List<ThemeResourceModel> resourcelist, String libCode,
                                                  String libName) {
        String tableName = "CMS_" + libCode.toUpperCase() + "_T";
        DocumentFactory factory = new DocumentFactory();
        Document document = factory.createDocument();
        Element root = document.addElement(libCode + "S");
        for (ThemeResourceModel model : resourcelist) {
            Element node = root.addElement(libCode);
            try {
                fillNode(node, "CMS_DOC_T", model.getRefDocId());
                fillNode(node, tableName, model.getRefDocId());
                if ("BOOK".equals(libCode) || "ELEC_PROD".equals(libCode)) {
                    fillNode(node, "CMS_JB_INFO_T", model.getRefDocId());
                    fillNode(node, "CMS_XC_INFO_T", model.getRefDocId());
                    fillChapterNode(node, model.getRefDocId());
                }
                List<Row> ucmFileList = CommonUtil.getWorkAM().getUCMFileByDocId(new Number(model.getRefDocId()));
                if (ucmFileList != null && ucmFileList.size() > 0) {
                    Element fileNode = node.addElement("FILES");
                    for (Row file : ucmFileList) {
                        String fileName = (String)file.getAttribute("FileName");
                        Element fileChildNode = fileNode.addElement("FILE");
                        Number docId = (Number)file.getAttribute("DocId");
                        Number refDocId = (Number)file.getAttribute("RefDocId");
                        String docType = (String)file.getAttribute("DocType");
                        fileChildNode.addAttribute("docId", toString(docId));
                        fileChildNode.addAttribute("ucmDid", toString(file.getAttribute("UcmDid")));
                        fileChildNode.addAttribute("ucmDocname", toString(file.getAttribute("UcmDocname")));
                        fileChildNode.addAttribute("fileName", toString(fileName));
                        if (docType != null && docType.length() > 0) {
                            fileChildNode.addAttribute("docType", toString(docType));
                        }
                        String filePath = null;
                        String ftpPath = (String)file.getAttribute("FtpPath");
                        if (ftpPath == null || ftpPath.length() == 0) {
                            filePath = "files/" + libName + "/" + docId + "/" + fileName;
                        } else {
                            filePath = "files/" + libName + "/" + docId + "/" + ftpPath + "/" + fileName;
                        }
                        fileChildNode.addAttribute("filePath", filePath);
                        if ("AUDIO".equals(libCode) || "ILLUSTRATION".equals(libCode) ||
                            "MATERIAL_FIGURE".equals(libCode) || "PHOTOGRAPHY_FIGURE".equals(libCode) ||
                            "AUDIO".equals(libCode) || "VIDEO".equals(libCode)) {
                            String thumbnailUrl = CommonUtil.getWorkAM().getThumbnailUrl(docId);
                            if (thumbnailUrl != null && thumbnailUrl.length() > 0) {
                                String thumbnailName = thumbnailUrl.substring(thumbnailUrl.lastIndexOf("/") + 1);
                                fileChildNode.addAttribute("thumbnailUrl",
                                                           "/files/" + libName + "/" + docId + "/" + "缩略图" + "/" +
                                                           thumbnailName);
                            }
                            String previewUrl = CommonUtil.getWorkAM().getPreviewUrl(docId);
                            if (previewUrl != null && previewUrl.length() > 0) {
                                String previewName = previewUrl.substring(previewUrl.lastIndexOf("/") + 1);
                                fileChildNode.addAttribute("previewUrl",
                                                           "/files/" + libName + "/" + docId + "/" + "预览文件" + "/" +
                                                           previewName);
                            }
                        } else {
                            if ("COVER".equals(docType)) {
                                String coverUrl = CommonUtil.getWorkAM().getThumbnailUrl(docId);
                                if (coverUrl != null && coverUrl.length() > 0) {
                                    String coverName = coverUrl.substring(coverUrl.lastIndexOf("/") + 1);
                                    fileChildNode.addAttribute("thumbnailUrl",
                                                               "/files/" + libName + "/" + docId + "/" + "封面缩略图" +
                                                               "/" + coverName);
                                }
                            }
                            String thumbnailUrl = CommonUtil.getWorkAM().getThumbnailUrl(refDocId);
                            if (thumbnailUrl != null && thumbnailUrl.length() > 0) {
                                String thumbnailName = thumbnailUrl.substring(thumbnailUrl.lastIndexOf("/") + 1);
                                fileChildNode.addAttribute("thumbnailUrl",
                                                           "files/" + libName + "/" + docId + "/" + (ftpPath == null ?
                                                                                                     "" : ftpPath) +
                                                           "/" + thumbnailName);
                            }
                            String previewUrl = CommonUtil.getWorkAM().getPreviewUrl(refDocId);
                            if (previewUrl != null && previewUrl.length() > 0) {
                                String previewName = previewUrl.substring(previewUrl.lastIndexOf("/") + 1);
                                fileChildNode.addAttribute("previewUrl",
                                                           "files/" + libName + "/" + docId + "/" + (ftpPath == null ?
                                                                                                     "" : ftpPath) +
                                                           "/" + previewName);
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return document;
    }

    public static String toString(Object o) {
        return o == null ? "" : o.toString();
    }

    public static void fillNode(Element node, String tableName, String docId) throws SQLException {
        Map<String, Object> map = DaoUtil.getRowDataByTableName(tableName, new Number(docId));
        if (map != null && map.size() > 0) {
            Element element = node.addElement(tableName);
            for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                Object value = map.get(key);
                Element child = element.addElement(key);
                child.addText(value == null ? "" : value.toString());
            }
        }
    }

    public static void fillChapterNode(Element node, String docId) throws SQLException {
        List<Map<String, Object>> chaptersList = DaoUtil.getChapters(new Number(docId));
        if (chaptersList != null && chaptersList.size() > 0) {
            Element element = node.addElement("CMS_CHAPTER_T");
            for (Map<String, Object> map : chaptersList) {
                Element chapter = element.addElement("CHAPTER");
                if (map != null && map.size() > 0) {
                    for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
                        String key = (String)it.next();
                        Object value = map.get(key);
                        Element child = chapter.addElement(key);
                        child.addText(value == null ? "" : value.toString());
                    }
                }
            }
        }
    }

    public static void writeDocument(Document document, String filePath) {
        try {
            //读取文件
            FileOutputStream fos = new FileOutputStream(filePath);
            //设置文件编码
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setIndent(true);
            xmlFormat.setNewlines(true);
            xmlFormat.setEncoding("UTF-8");
            //创建写文件方法
            XMLWriter xmlWriter = new XMLWriter(fos, xmlFormat);
            //写入文件
            xmlWriter.write(document);
            //关闭
            xmlWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<ThemeTreeModel> list = new ArrayList<ThemeTreeModel>();
        ThemeTreeModel m1 = new ThemeTreeModel();
        m1.setId("1");
        m1.setPid("0");
        m1.setType("ROOT");
        m1.setName("湖南");
        m1.setRemarks("湖南");

        ThemeTreeModel m2 = new ThemeTreeModel();
        m2.setId("2");
        m2.setPid("1");
        m2.setType("CHILD");
        m2.setName("长沙");
        m2.setRemarks("长沙");


        ThemeTreeModel m3 = new ThemeTreeModel();
        m3.setId("3");
        m3.setPid("2");
        m3.setType("CHILD");
        m3.setName("中南");
        m3.setRemarks("中南");
        list.add(m1);
        list.add(m2);
        list.add(m3);

        Document doc = ThemeExportUtil.generateTreeXml(list, null);
        System.out.println(doc.asXML());
        try {
            //读取文件
            FileOutputStream fos = new FileOutputStream("D:\\test1.xml");
            //            FileWriter fileWriter = new FileWriter("D:\\test1.xml");
            //设置文件编码
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setIndent(true);
            xmlFormat.setNewlines(true);
            xmlFormat.setEncoding("UTF-8");
            //创建写文件方法
            XMLWriter xmlWriter = new XMLWriter(fos, xmlFormat);
            //写入文件
            xmlWriter.write(doc);
            //关闭
            xmlWriter.close();
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
    }
}
