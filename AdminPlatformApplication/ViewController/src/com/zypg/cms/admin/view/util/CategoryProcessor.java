package com.zypg.cms.admin.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class CategoryProcessor implements Runnable {
    private String compCode;
    private final static String LIB_CODE = "BOOK";
    private final static String PARENT_NODE_TYPE = "CATEGORY_GROUP";
    private final static String SECOND_NODE_TYPE = "CATEGORY";
    private final static String XML_PATH = "/home/oracle/cms/Category.xml";

    public CategoryProcessor(String compCode) {
        this.compCode = compCode;
    }

    public String getTopParentId(Connection conn, String compCode) {
        String topParentId = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM cms_lib_category_g_rel_t r WHERE r.lib_code = ? AND r.comp_code = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, LIB_CODE);
            ps.setObject(2, compCode);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                topParentId = rs.getObject("rel_id").toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return topParentId;
    }

    /**
     * 删除分类
     * @param parentCategoryId
     * @return
     */
    public String deleteCategory(Connection conn, String parentCategoryId) {
        String result = null;
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{ ? = call CMS_DATA_FIX_PKG.delete_category(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, parentCategoryId);
            cs.execute();
            result = cs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            result = "E";
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("CUX_HGM_CACHEMGT_PKG.delete_category result = " + result);
        return result;
    }

    /**
     * 新增分类
     * @param categoryName
     * @param parentNodeType
     * @param parentNodeId
     * @param catType
     * @param catId
     * @param seq
     * @return
     */
    public String insertCategory(Connection conn, String categoryName, String parentNodeType, String parentNodeId,
                                 String catType, String catId, Integer seq, Integer level) {
        String result = null;
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{ ? = call CMS_DATA_FIX_PKG.insert_category(?,?,?,?,?,?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, categoryName);
            cs.setObject(3, parentNodeType);
            cs.setObject(4, parentNodeId);
            cs.setObject(5, catType);
            cs.setObject(6, catId);
            cs.setObject(7, seq);
            cs.setObject(8, level);
            cs.execute();
            result = cs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            result = "E";
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("CUX_HGM_CACHEMGT_PKG.insert_category result = " + result);
        return result;
    }

    public void processCategory(Connection conn, String xmlPath, String parentCategoryId) {
        FileInputStream fis = null;
        Document document = null;
        try {
            fis = new FileInputStream(xmlPath);
            Reader read = new InputStreamReader(fis, "UTF-8");
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        int i = 0;
        Integer fisrtSeq = 10;
        for (Iterator it = root.elementIterator("CATEGORY"); it.hasNext(); ) {
            Element child = (Element)it.next();
            if (!"未分类".equals(child.elementText("catName"))) {
                i++;
                System.out.println("第一层" + i + ":" + child.elementText("catName"));
                String parentNodeId = null;
                try {
                    parentNodeId =
                            insertCategory(conn, child.elementText("catName"), PARENT_NODE_TYPE, parentCategoryId,
                                           child.elementText("catType"), child.elementText("catID"), fisrtSeq, 1);
                } catch (Exception e) {
                    parentNodeId = null;
                    System.err.println(child.elementText("catName") + "已经存在:" + e.getMessage());
                }
                if (parentNodeId != null) {
                    Integer secondSeq = 10;
                    for (Iterator it1 = child.elementIterator("CATEGORY"); it1.hasNext(); ) {
                        Element child1 = (Element)it1.next();
                        System.out.println("    第二层:" + child1.elementText("catName"));
                        insertCategory(conn, child1.elementText("catName"), SECOND_NODE_TYPE, parentNodeId,
                                       child1.elementText("catType"), child1.elementText("catID"), secondSeq, 2);
                        secondSeq += 10;
                    }
                    fisrtSeq += 10;
                }
            }
        }
        System.out.println(i++);
    }

    public void printCategory(String xmlPath) {
        FileInputStream fis = null;
        Document document = null;
        try {
            fis = new FileInputStream(xmlPath);
            Reader read = new InputStreamReader(fis, "UTF-8");
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        int i = 0;
        for (Iterator it = root.elementIterator("CATEGORY"); it.hasNext(); ) {
            Element child = (Element)it.next();
            i++;
            System.out.println("第一层" + i + ":" + child.elementText("catName"));
            for (Iterator it1 = child.elementIterator("CATEGORY"); it1.hasNext(); ) {
                Element child1 = (Element)it1.next();
                System.out.println("    第二层:" + child1.elementText("catName"));
            }
        }
        System.out.println(i++);
    }

    public void process() {
        try {
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            //删除所有的图书分类
            String parentCategoryId = getTopParentId(conn, compCode);
            deleteCategory(conn, parentCategoryId);
            //开始插入图书分类
            processCategory(conn, XML_PATH, parentCategoryId);
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void run() {
        process();
    }
}
