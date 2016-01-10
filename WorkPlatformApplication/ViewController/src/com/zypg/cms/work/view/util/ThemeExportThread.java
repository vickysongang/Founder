package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.view.model.ThemeResourceModel;
import com.zypg.cms.work.view.model.UcmFileModel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import oracle.jbo.domain.Number;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import org.dom4j.Document;


public class ThemeExportThread implements Runnable {
    private List<String> exportList = new ArrayList<String>();
    private OutputStream outputStream;

    public ThemeExportThread() {

    }

    public ThemeExportThread(List<String> exportList, OutputStream outputStream) {
        this.exportList = exportList;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try {
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
            File zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            for (String key : exportList) {
                try {
                    Number themeDocId = new Number(key.substring(key.lastIndexOf("_") + 1));
                    //生成 主题.xml
                    String themeName = getThemeName(conn, themeDocId);
                    String xmlFilePath = zipAttPath + "主题_" + themeDocId + ".xml";
                    Document document =
                        ThemeExportUtil.generateTreeXml(DaoUtil.getThemeTreeNodes(themeDocId.toString()), null);
                    ThemeExportUtil.writeDocument(document, xmlFilePath);
                    FileInputStream fis = new FileInputStream(xmlFilePath);
                    ZipEntry themeEntry = new ZipEntry(themeName + "/主题.xml");
                    System.out.println("导出主题:" + themeName + ",生成:" + themeName + "/主题.xml 文件");
                    out.putNextEntry(themeEntry);
                    byte[] b = new byte[1024 * 512];
                    int lenHtml = 0;
                    while ((lenHtml = fis.read(b)) != -1) {
                        out.write(b, 0, lenHtml);
                    }
                    out.closeEntry();
                    fis.close();

                    List<Map<String, String>> exportLibList = this.getThemeExportLibs(conn, themeDocId);
                    for (int i = 0; i < exportLibList.size(); i++) {
                        Map<String, String> libMap = exportLibList.get(i);
                        String libCode = libMap.get("libCode");
                        System.out.println("libCode:" + libCode);
                        String libName = libMap.get("libName");
                        List<ThemeResourceModel> resourceList = DaoUtil.getThemeResourceList(themeDocId, libCode);
                        Document doc = ThemeExportUtil.generateRefResourceXml(conn, resourceList, libCode, libName);
                        String path = zipAttPath + libCode + themeDocId + ".xml";
                        ThemeExportUtil.writeDocument(doc, path);
                        FileInputStream is = new FileInputStream(path);
                        System.out.println("path:" + path);
                        ZipEntry libEntry = new ZipEntry(themeName + "/" + libName + ".xml");
                        out.putNextEntry(libEntry);
                        System.out.println("导出库:" + libName + ",生成:" + themeName + "/" + libName + ".xml");
                        byte[] b1 = new byte[1024 * 512];
                        int lenHtml1 = 0;
                        while ((lenHtml1 = is.read(b1)) != -1) {
                            out.write(b1, 0, lenHtml1);
                        }
                        out.closeEntry();
                        is.close();
                        for (ThemeResourceModel resource : resourceList) {
                            List<UcmFileModel> ucmFileList =
                                DaoUtil.getUCMFilesByDocId(conn, new Number(resource.getRefDocId()));
                            System.out.println("ucmFileList:" + ucmFileList.size());
                            for (UcmFileModel file : ucmFileList) {
                                Number ucmDID = file.getUcmDid();
                                Number docId = file.getDocId();
                                String fileName = file.getFileName();
                                InputStream ucmIs = DocService.getInstance().getFile(ucmDID.toString());
                                String ftpPath = file.getFilePath();
                                String filePath = null;
                                if (ftpPath == null || ftpPath.length() == 0) {
                                    filePath = themeName + "/files/" + libName + "/" + docId + "/" + fileName;
                                } else {
                                    filePath =
                                            themeName + "/files/" + libName + "/" + docId + "/" + ftpPath + "/" + fileName;
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
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //DaoUtil.createThemeExportCompleteLog();
            out.close();
            FileInputStream fisRar = new FileInputStream(zipAttPath);
            //            FileOutputStream fisOut = new FileOutputStream("C://a.zip");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getThemeName(Connection conn, Number themeDocId) throws SQLException {
        String themeName = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT t.theme_name FROM cms_theme_t t WHERE t.doc_id = ?");
            ps.setObject(1, themeDocId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                themeName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return themeName;
    }

    public List<Map<String, String>> getThemeExportLibs(Connection conn, Number themeDocId) throws SQLException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT DISTINCT t.lib_code,lib.lib_name\n" +
                        "  FROM cms_theme_resource_t t, cms_lib_v lib\n" +
                        " WHERE t.tree_node_id IN\n" +
                        "       (SELECT tree.node_id\n" +
                        "          FROM cms_theme_tree_t tree\n" +
                        "        CONNECT BY PRIOR tree.node_id = tree.parent_node_id\n" +
                        "         START WITH tree.parent_node_id = ?)\n" +
                        "   AND t.lib_code = lib.lib_code");
            ps.setObject(1, themeDocId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("libCode", rs.getString(1));
                map.put("libName", rs.getString(2));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return list;
    }
}
