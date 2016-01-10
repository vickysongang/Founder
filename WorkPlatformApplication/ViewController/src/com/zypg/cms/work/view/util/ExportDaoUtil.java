package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import com.zypg.cms.work.view.model.ExportBookModel;
import com.zypg.cms.work.view.model.ExportCategoryModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jbo.domain.Number;


public class ExportDaoUtil {

    public static List<ExportCategoryModel> getCategoryList(Number applyId) throws SQLException {
        List<ExportCategoryModel> categoryList = new ArrayList<ExportCategoryModel>();
        int level = getCategoryLevel(applyId);
        int size = 0;
        List<Map<String, String>> mapList = getCategoryMapList(applyId);
        if (level == 1) {
            for (int i = 0; i < mapList.size(); i++) {
                Map<String, String> map = mapList.get(i);
                String parentNodeType = map.get("parentNodeType");
                if (parentNodeType == null || parentNodeType.equals("CATEGORY_GROUP")) {
                    ExportCategoryModel category = new ExportCategoryModel();
                    category.setCategoryId(map.get("categoryId"));
                    category.setCategoryName(map.get("categoryName"));
                    size = getCategoryRes(category.getCategoryId(), applyId).size();
                    category.setUrl("Category_" + category.getCategoryId() + ".html?size=" + size);
                    categoryList.add(category);
                }
            }
        } else if (level == 2) {
            for (int i = 0; i < mapList.size(); i++) {
                Map<String, String> map = mapList.get(i);
                String parentNodeType = map.get("parentNodeType");
                if (parentNodeType == null || parentNodeType.equals("CATEGORY_GROUP")) {
                    ExportCategoryModel category = new ExportCategoryModel();
                    category.setCategoryId(map.get("categoryId"));
                    category.setCategoryName(map.get("categoryName"));
                    size = getCategoryRes(category.getCategoryId(), applyId).size();
                    category.setUrl("Category_" + category.getCategoryId() + ".html?size=" + size);
                    List<ExportCategoryModel> childrens = new ArrayList<ExportCategoryModel>();
                    for (int j = 0; j < mapList.size(); j++) {
                        Map<String, String> map1 = mapList.get(i);
                        String parentNodeId = map1.get("parenteNodeId");
                        if (parentNodeId != null && parentNodeId.equals(category.getCategoryId())) {
                            ExportCategoryModel category1 = new ExportCategoryModel();
                            category1.setCategoryId(map1.get("categoryId"));
                            category1.setCategoryName(map1.get("categoryName"));
                            size = getCategoryRes(category.getCategoryId(), applyId).size();
                            category1.setUrl("Category_" + category1.getCategoryId() + ".html?size=" + size);
                            childrens.add(category1);
                        }
                    }
                    category.setChildrens(childrens);
                    categoryList.add(category);
                }
            }
        }
        return categoryList;
    }

    public static List<Map<String, String>> getCategoryMapList(Number applyId) throws SQLException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT crt.category_id,\n" +
                "                c.parent_node_id,\n" +
                "                nvl(c.category_name, '未分类') category_name,\n" +
                "                c.parent_node_type\n" +
                "  FROM cms_res_temp_shelf_v v, cms_doc_category_rel_t crt, cms_category_t c\n" +
                " WHERE v.shelf_id IN\n" +
                "       (SELECT shelf_id FROM cms_res_exp_apply_l_t l WHERE l.head_id = " + applyId + ")\n" +
                "   AND v.doc_id = crt.doc_id(+)\n" +
                "   AND crt.category_id = c.category_id(+)";
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("categoryId", rs.getObject(1) == null ? null : rs.getObject(1).toString());
                map.put("parenteNodeId", rs.getObject(2) == null ? null : rs.getObject(2).toString());
                map.put("categoryName", rs.getObject(3) == null ? null : rs.getObject(3).toString());
                map.put("parentNodeType", rs.getObject(4) == null ? null : rs.getObject(4).toString());
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static int getCategoryLevel(Number applyId) {
        int level = 1;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_work_platform_pkg.get_category_level(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setObject(2, applyId);
            cs.execute();
            level = cs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return level;
    }

    public static List<String> getCategoryRes(String categoryId, Number applyId) {
        List<String> list = new ArrayList<String>();
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_work_platform_pkg.get_category_res(?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, categoryId);
            cs.setObject(3, applyId);
            cs.execute();
            String docIds = cs.getString(1);
            System.out.println("docIds:" + docIds);
            if (docIds != null && docIds.length() > 0) {
                list = Arrays.asList(docIds.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return list;
    }

    public static Map<String, Object> getFieldsMap(String docId, String tableName) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.field_code, t.field_name\n" +
                "  FROM cms_selected_export_field_t t, cms_all_export_field_t f\n" +
                " WHERE t.doc_id = " + docId + "\n" +
                "   AND t.field_code = f.field_code\n" +
                "   AND f.attribute1 = '" + tableName + "'";
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                map.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }

    public static Map<String, Object> getBaseInfoDataMap(String docId) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM cms_book_t t WHERE t.doc_id = " + docId;
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    map.put(columnName, columnValue == null ? "" : columnValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }

    public static Map<String, Object> getJbInfoDataMap(String docId) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM cms_jb_info_t t WHERE t.doc_id = " + docId;
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    map.put(columnName, columnValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }

    public static List<Object> getxcInfoDataList(String docId) throws SQLException {
        List<Object> list = new ArrayList<Object>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.content FROM cms_xc_info_t t WHERE t.doc_id = " + docId;
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getObject(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static Map<String, String> getBookCategoryInfo(String docId) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT nvl(c.category_id, -1) category_id,\n" +
                "       nvl(c.category_name, '未分类') category_name\n" +
                "  FROM cms_book_t b, cms_doc_category_rel_t crt, cms_category_t c\n" +
                " WHERE b.doc_id = crt.doc_id(+)\n" +
                "   AND crt.category_id = c.category_id(+)\n" +
                "   AND b.doc_id = " + docId;
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                map.put("categoryId", rs.getObject(1).toString());
                map.put("categoryName", rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }

    public static ExportBookModel getExportBookModel(String docId) throws SQLException {
        ExportBookModel model = new ExportBookModel();
        Map<String, Object> fieldsMap = new HashMap<String, Object>();
        Map<String, Object> baseInfoFiledsMap = getFieldsMap(docId, "CMS_BOOK_T");
        Map<String, Object> jbInfoFiledsMap = getFieldsMap(docId, "CMS_JB_INFO_T");
        Map<String, Object> xcInfoFiledsMap = getFieldsMap(docId, "CMS_XC_INFO_T");
        fieldsMap.put("baseInfoField", baseInfoFiledsMap);
        fieldsMap.put("jbInfoField", jbInfoFiledsMap);
        fieldsMap.put("xcInfoField", xcInfoFiledsMap);
        model.setFieldsMap(fieldsMap);
        Map<String, Object> baseInfoMap = getBaseInfoDataMap(docId);
        Map<String, Object> jbInfoMap = getJbInfoDataMap(docId);
        List<Object> xcList = getxcInfoDataList(docId);
        Map<String, Object> datasMap = new HashMap<String, Object>();
        datasMap.put("baseInfoData", baseInfoMap);
        datasMap.put("jbInfoData", jbInfoMap);
        datasMap.put("xcInfoData", xcList);
        model.setDatasMap(datasMap);
        model.setAuthor((String)baseInfoMap.get("AUTHOR"));
        model.setBookName((String)baseInfoMap.get("BOOK_NAME"));
        Map<String, String> categoryMap = getBookCategoryInfo(docId);
        model.setCategoryName(categoryMap.get("categoryName"));
        model.setCatetoryId(categoryMap.get("categoryId"));
        model.setDetailHtmlUrl("Category_" + categoryMap.get("categoryId") + "\\" + docId + "\\" + docId + ".html");
        model.setDocId(docId);
        model.setEdition(baseInfoMap.get("EDITION") == null ? null : baseInfoMap.get("EDITION").toString());
        model.setImpression(baseInfoMap.get("IMPRESSION") == null ? null : baseInfoMap.get("IMPRESSION").toString());
        model.setIsbn((String)baseInfoMap.get("ISBN"));
        String sampleUcmDid = getThumbnailUrl(docId);
        if (sampleUcmDid != null) {
            model.setPdfUrl("Category_" + categoryMap.get("categoryId") + "\\" + docId + "\\" + docId + "_sample.pdf");
        } else {
            model.setPdfUrl("#");
        }
        model.setPubTime(baseInfoMap.get("PUB_TIME") == null ? null : baseInfoMap.get("PUB_TIME").toString());
        model.setThumbnailName(docId + ".jpg");
        model.setPdfName(docId + "_sample.pdf");
        model.setThumbnailUrl("Category_" + categoryMap.get("categoryId") + "\\" + "small_images\\" + docId + ".jpg");
        return model;
    }

    public static List<String> getDocIdList(Number applyId) throws SQLException {
        List<String> list = new ArrayList<String>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT to_char(v.doc_id)\n" +
                "  FROM cms_res_temp_shelf_v v\n" +
                " WHERE v.shelf_id IN\n" +
                "       (SELECT shelf_id FROM cms_res_exp_apply_l_t l WHERE l.head_id = " + applyId + ")";
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String docId = rs.getString(1);
                list.add(docId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static String getThumbnailUrl(String docId) throws SQLException {
        String url = null;
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.thumbnail_url FROM cms_doc_t t WHERE t.doc_id = " + docId;
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                url = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return url;
    }

    public static Map<String, String> getSampleUCMDID(String docId) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "\n" +
                "SELECT r.ucm_did,r.attribute15\n" +
                "  FROM cms_doc_ucm_rel_t r\n" +
                " WHERE upper(r.file_name) LIKE '%PDF'\n" +
                "   AND r.doc_id = " + docId + "\n" +
                "   AND ROWNUM = 1";
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                map.put("ucmDid", rs.getString(1));
                map.put("attribute15", rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }
}
