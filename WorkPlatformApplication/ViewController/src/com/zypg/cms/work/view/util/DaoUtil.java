package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import com.zypg.cms.foldermanager.model.FtpFile;
import com.zypg.cms.work.view.model.ExpResModel;
import com.zypg.cms.work.view.model.ThemeResourceModel;
import com.zypg.cms.work.view.model.ThemeTreeModel;

import com.zypg.cms.work.view.model.UcmFileModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.jbo.domain.Number;


public class DaoUtil {
    public DaoUtil() {
        super();
    }

    /**
     * 根据根节点获取主题树上的节点List
     * @param rootNodeId
     * @return
     * @throws SQLException
     */
    public static List<ThemeTreeModel> getThemeTreeNodes(String rootNodeId) throws SQLException {
        List<ThemeTreeModel> treeNodes = new ArrayList<ThemeTreeModel>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            ps =
 conn.prepareStatement("SELECT t.node_id, t.parent_node_id, t.node_name,t.node_type,cms_work_platform_pkg.get_theme_resource_count(t.node_id)" +
                       ",t.comp_code,t.remarks FROM cms_theme_tree_t t where cms_work_platform_pkg.get_theme_tree_parent_node(t.node_id) = ? ORDER BY t.creation_date");
            ps.setObject(1, rootNodeId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                ThemeTreeModel node = new ThemeTreeModel();
                node.setId(rs.getObject(1) + "");
                node.setPid(rs.getObject(2) + "");
                node.setName(rs.getString(3));
                node.setType(rs.getString(4));
                node.setResCount(rs.getObject(5) == null ? "0" : rs.getObject(5).toString());
                node.setCompCode(rs.getString(6));
                node.setRemarks(rs.getString(7));
                treeNodes.add(node);
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
            if (conn != null) {
                conn.close();
            }
        }
        return treeNodes;
    }

    /**
     * 插入节点
     * @param nodeName
     * @param nodeType
     * @param parentNodeId
     * @param userId
     * @param compCode
     * @return
     * @throws SQLException
     */
    public static String insertThemeNode(String nodeName, String nodeType, String parentNodeId, String userId,
                                         String compCode) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_work_platform_pkg.insert_theme_tree_node(?,?,?,?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, nodeName);
            cs.setObject(3, nodeType);
            cs.setObject(4, parentNodeId);
            cs.setObject(5, userId);
            cs.setObject(6, compCode);
            cs.execute();
            conn.commit();
            result = cs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            result = "E";
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        System.out.println("cms_work_platform_pkg.insert_theme_tree_node result = " + result);
        return result;
    }

    /**
     * 更新节点
     * @param nodeId
     * @param nodeName
     * @throws SQLException
     */
    public static void updateThemeNodeName(String nodeId, String nodeName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            ps =
 conn.prepareStatement("UPDATE cms_theme_tree_t t SET t.node_name = ? WHERE t.node_id = ? and t.node_name <> ?");
            ps.setObject(1, nodeName);
            ps.setObject(2, Integer.parseInt(nodeId));
            ps.setObject(3, nodeName);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 更新节点
     * @param nodeId
     * @return
     * @throws SQLException
     */
    public static String deleteThemeNode(String nodeId) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_work_platform_pkg.delete_theme_tree_node(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, Integer.parseInt(nodeId));
            cs.execute();
            conn.commit();
            result = cs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            result = "E";
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        System.out.println("cms_work_platform_pkg.delete_theme_tree_node result = " + result);
        return result;
    }

    /**
     * 根据docId获取所有UCM文件
     * @param conn
     * @param docId
     * @return
     * @throws SQLException
     */
    public static List<UcmFileModel> getUCMFilesByDocId(Connection conn, Number docId) throws SQLException {
        List<UcmFileModel> ucmFileList = new ArrayList<UcmFileModel>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps =
 conn.prepareStatement("SELECT dur.ucm_docname, dur.file_name, dur.ftp_path, dur.ucm_did, dur.ftp_upload_date,dur.ref_doc_id FROM  cms_doc_ucm_rel_t dur WHERE dur.doc_id = ?");
            ps.setObject(1, docId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                UcmFileModel ucmFile = new UcmFileModel();
                ucmFile.setDocId(docId);
                ucmFile.setUcmDocName(rs.getString(1));
                ucmFile.setFileName(rs.getString(2));
                ucmFile.setFilePath(rs.getString(3));
                ucmFile.setUcmDid(rs.getObject(4) == null ? null : new Number(rs.getObject(4)));
                ucmFile.setFtpUploadDate(rs.getString(5));
                ucmFile.setRefDocId(rs.getObject(6) == null ? null : new Number(rs.getObject(6)));
                ucmFileList.add(ucmFile);
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
        return ucmFileList;
    }

    /**
     * 根据docId获取所有UCM文件
     * @param conn
     * @param docId
     * @return
     * @throws SQLException
     */
    public static List<UcmFileModel> getUCMFilesByDocIdAndType(Connection conn, Number docId,
                                                               String resTypeName) throws SQLException {
        List<UcmFileModel> ucmFileList = new ArrayList<UcmFileModel>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps =
 conn.prepareStatement("SELECT dur.ucm_docname, dur.file_name, dur.ftp_path, dur.ucm_did, dur.ftp_upload_date,dur.ref_doc_id FROM  cms_doc_ucm_rel_t dur WHERE dur.doc_id = ? and instr(dur.ftp_path,?) >0");
            ps.setObject(1, docId);
            ps.setObject(2, resTypeName);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                UcmFileModel ucmFile = new UcmFileModel();
                ucmFile.setDocId(docId);
                ucmFile.setUcmDocName(rs.getString(1));
                ucmFile.setFileName(rs.getString(2));
                ucmFile.setFilePath(rs.getString(3));
                ucmFile.setUcmDid(rs.getObject(4) == null ? null : new Number(rs.getObject(4)));
                ucmFile.setFtpUploadDate(rs.getString(5));
                ucmFile.setRefDocId(rs.getObject(6) == null ? null : new Number(rs.getObject(6)));
                ucmFileList.add(ucmFile);
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
        return ucmFileList;
    }

    public static List<ThemeResourceModel> getThemeResourceList(Number nodeId) throws SQLException {
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        List<ThemeResourceModel> resourceList = new ArrayList<ThemeResourceModel>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps =
 conn.prepareStatement("SELECT t.resource_id,  t.resource_name, t.lib_code,t.tree_node_id, t.comp_code, t.attribute1,lib.lib_name\n" +
                        "FROM cms_theme_resource_t t , cms_lib_v lib WHERE t.lib_code = lib.lib_code and t.tree_node_id = ?");
            ps.setObject(1, nodeId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                ThemeResourceModel res = new ThemeResourceModel();
                res.setResourceId(rs.getObject(1).toString());
                res.setResourceName(rs.getString(2));
                res.setLibCode(rs.getString(3));
                res.setTreeNodeId(rs.getObject(4) == null ? null : rs.getObject(4).toString());
                res.setCompCode(rs.getString(5));
                res.setRefDocId(rs.getString(6));
                res.setLibName(rs.getString(7));
                resourceList.add(res);
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
            if (conn != null) {
                conn.close();
            }
        }
        return resourceList;
    }

    public static void updateThemeExportLogStatus(Connection conn, String statusCode, String exportType,
                                                  Number userId) throws SQLException {
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{ call cms_work_platform_pkg.update_export_log_status(?,?,?)}");
            cs.setObject(1, statusCode);
            cs.setObject(2, exportType);
            cs.setObject(3, userId);
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    public static void updateThemeExpoxtToFinishedStatus(Connection conn, String statusCode, String exportType,
                                                         String resourceName, Number userId) throws SQLException {
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{ call cms_work_platform_pkg.update_export_log_to_finish(?,?,?,?)}");
            cs.setObject(1, statusCode);
            cs.setObject(2, exportType);
            cs.setObject(3, resourceName);
            cs.setObject(4, userId);
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    public static List<ThemeResourceModel> getThemeResourceList(Number docId, String libCode) throws SQLException {
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        List<ThemeResourceModel> resourceList = new ArrayList<ThemeResourceModel>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.resource_id,\n" +
                "       t.resource_name,\n" +
                "       t.lib_code,\n" +
                "       t.tree_node_id,\n" +
                "       t.comp_code,\n" +
                "       t.attribute1,\n" +
                "       lib.lib_name\n" +
                "  FROM cms_theme_resource_t t, cms_lib_v lib\n" +
                " WHERE t.lib_code = lib.lib_code\n" +
                "   AND t.tree_node_id IN\n" +
                "       (SELECT tree.node_id\n" +
                "          FROM cms_theme_tree_t tree\n" +
                "        CONNECT BY PRIOR tree.node_id = tree.parent_node_id\n" +
                "         START WITH tree.parent_node_id = ?)\n" +
                "   AND t.lib_code = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, docId);
            ps.setObject(2, libCode);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                ThemeResourceModel res = new ThemeResourceModel();
                res.setResourceId(rs.getObject(1).toString());
                res.setResourceName(rs.getString(2));
                res.setLibCode(rs.getString(3));
                res.setTreeNodeId(rs.getObject(4) == null ? null : rs.getObject(4).toString());
                res.setCompCode(rs.getString(5));
                res.setRefDocId(rs.getString(6));
                res.setLibName(rs.getString(7));
                resourceList.add(res);
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
            if (conn != null) {
                conn.close();
            }
        }
        return resourceList;
    }


    public static Map<String, Object> getRowDataByTableName(String tableName, Number docId) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "select * from " + tableName + " where doc_id = " + docId;
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    if ("CLOB".equals(data.getColumnTypeName(i))) {
                        map.put(columnName, CommonUtil.clobToString(rs.getClob(i)));
                    } else {
                        map.put(columnName, rs.getObject(i));
                    }
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

    public static List<Map<String, Object>> getChapters(Number docId) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from CMS_CHAPTER_T where doc_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, docId);
            // 执行查询
            rs = ps.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    if ("CLOB".equals(data.getColumnTypeName(i))) {
                        map.put(columnName, CommonUtil.clobToString(rs.getClob(i)));
                    } else {
                        map.put(columnName, rs.getObject(i));
                    }
                }
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
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static String getDocNameByDocId(Connection conn, Number docId) {
        CallableStatement st = null;
        String result = null;
        try {
            st = conn.prepareCall("begin ? := cms_work_platform_pkg.get_docname_by_doc_id(?); end;");
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, docId);
            st.executeUpdate();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String getSyncBatchNum(Connection conn, Number docId) {
        CallableStatement st = null;
        String result = null;
        try {
            st = conn.prepareCall("begin ? := cms_common_pkg.get_sync_batch_num(?); end;");
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, docId);
            st.executeUpdate();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String deleteGatherMonitorRecord(Connection conn, Number docId) {
        String result = null;
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin ? := cms_common_pkg.delete_gather_monitor_record(?); end;");
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, docId);
            st.execute();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            result = "N";
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void endSync(Connection conn, String docId) {
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin cms_common_pkg.end_sync(?); end;");
            st.setObject(1, docId);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 处理批量同步的日志
     * @param docId
     * @param fileName
     * @param filePath
     * @return
     */
    public static String processSyncLog(Connection conn, Number userId, Number docId, String fileName, String filePath,
                                        String userName, String compCode, String libCode, String operation,
                                        String gatherResult, String thumbnailResult, String remarks, String batchNum) {
        System.out.println("fileName:" + fileName + "  filePath:" + filePath);
        String result = null;
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin cms_common_pkg.sync_log_processor(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
            st.setObject(1, userId);
            st.setObject(2, userName == null ? null : userName.toUpperCase());
            st.setObject(3, compCode);
            st.setObject(4, libCode);
            st.setObject(5, docId);
            st.setObject(6, fileName);
            st.setObject(7, filePath);
            st.setObject(8, operation);
            st.setObject(8, operation);
            st.setObject(9, gatherResult);
            st.setObject(10, thumbnailResult);
            st.setObject(11, remarks);
            st.setObject(12, batchNum);
            st.registerOutParameter(13, Types.VARCHAR);
            st.registerOutParameter(14, Types.VARCHAR);
            st.executeUpdate();
            result = (String)st.getObject(13);
            if (!"S".equals(result)) {
                result = (String)st.getObject(14);
            }
            System.out.println("cms_common_pkg.sync_log_processor  result:" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String getDocType(Connection conn, String libCode, FtpFile file, String path) {
        System.out.println("getDocType:libCode--->" + libCode + "   filePath---->" + file.getFilePath() +
                           "    path------>" + path);
        String docType = null;
        //查询docType类型
        String filePath = file.getFilePath();
        if (filePath != null && filePath.startsWith(path)) {
            try {
                filePath = filePath.substring(path.length() + 1, filePath.length() - 1);
            } catch (Exception e) {
                filePath = file.getFilePath();
                e.printStackTrace();
            }
        }
        docType = getDocType(conn, filePath, libCode);
        System.out.println("docType--->" + docType);
        return docType;
    }

    public static String getDocType(Connection conn, String filePath, String libCode) {
        String result = null;
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin ? := cms_common_pkg.get_doc_type(?,?); end;");
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, filePath);
            st.setObject(3, libCode);
            st.execute();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void fileManage(Connection conn, Number docId, String syncFlag) {
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin cms_common_pkg.file_manage(?,?); end;");
            st.setObject(1, docId);
            st.setObject(2, syncFlag);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertFileManageMonitorRecord(Connection conn, Number docId, Integer currFileCount,
                                                     Integer totalFileCount) {
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin cms_work_platform_pkg.insert_fm_monitor_record(?,?,?); end;");
            st.setObject(1, docId);
            st.setObject(2, currFileCount);
            st.setObject(3, totalFileCount);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map<String, String> getFileManageMonitorRecord(Number docId) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT civ.unique_res_name, t.curr_file_count, t.total_file_count\n" +
                "  FROM cms_file_manage_monitor_t t, cms_res_common_info_v civ\n" +
                " WHERE t.doc_id = civ.doc_id AND t.doc_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, docId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                map.put("resName", rs.getString(1));
                map.put("currFileCount", rs.getString(2));
                map.put("totalFileCount", rs.getString(3));
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
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }

    public static String getFileManageDirectory(Connection conn, Number docId) {
        String result = null;
        CallableStatement st = null;
        try {
            st = conn.prepareCall("begin ? := cms_common_pkg.get_file_manage_directory(?); end;");
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, docId);
            st.execute();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static List<String> getBookExportExcelHeader() throws SQLException {
        List<String> list = new ArrayList<String>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT v.res_code,v.res_name FROM cms_exp_res_v v WHERE v.res_type_code = 'METADATA#BOOK'";
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(2));
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
        list.add("状态");
        return list;
    }

    public static List<Map<String, String>> getBookExportExcelDatas(String compCode,
                                                                    String statusStr) throws SQLException {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            List<String> headers = getBookExportExcelHeader();
            String sql = null;
            if (statusStr == null || "".equals(statusStr)) {
                sql = "SELECT * FROM cms_book_export_info_v v WHERE v.comp_code = '" + compCode + "'";
            } else {
                sql =
"SELECT * FROM cms_book_export_info_v v WHERE v.comp_code = '" + compCode + "' and v.status in (" + statusStr + ")";
            }
            stat = conn.createStatement();
            // 执行查询
            rs = stat.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                Map<String, String> lineDatas = new HashMap<String, String>();
                for (int i = 1; i <= data.getColumnCount() - 2; i++) {
                    String key = headers.get(i - 1);
                    String value = rs.getObject(i) == null ? "" : rs.getObject(i).toString();
                    //                    System.out.println("key:" + key + "   value:" + value);
                    lineDatas.put(key, value);
                }
                datas.add(lineDatas);
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
        return datas;
    }

    /**
     * 获取文档的UCM资源
     * @param conn
     * @param docId    文档ID
     * @param resCode  资源类型
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> getDocResByDocId(Connection conn, Number docId,
                                                             String resCode) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT r.ucm_did,r.file_name\n" +
                "  FROM cms_exp_res_v t,cms_doc_ucm_rel_t r\n" +
                " WHERE t.lib_code = 'BOOK'\n" +
                "   AND t.res_code = ?" + "  AND t.attribute1 = r.ftp_path\n" +
                "   AND r.doc_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, resCode);
            ps.setObject(2, docId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> lineDatas = new HashMap<String, Object>();
                lineDatas.put("ucmDid", rs.getObject(1));
                lineDatas.put("fileName", rs.getObject(2));
                datas.add(lineDatas);
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
        return datas;
    }

    /**
     * 获取正文其他附件
     * @param conn
     * @param docId
     * @param fileExt  附件类型：epub或mobi
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> getDocAttachByDocId(Connection conn, Number docId,
                                                                String fileExt) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT r.ucm_did, r.file_name\n" +
                "  FROM cms_exp_res_v t, cms_doc_ucm_rel_t r\n" +
                " WHERE t.lib_code = 'BOOK'\n" +
                "   AND t.res_code = 'MAIN_OTHER_ATTACHS'\n" +
                "   AND t.attribute1 = r.ftp_path\n" +
                "   AND instr(lower(r.file_name), ?) > 0\n" +
                "   AND r.doc_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, fileExt);
            ps.setObject(2, docId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> lineDatas = new HashMap<String, Object>();
                lineDatas.put("ucmDid", rs.getObject(1));
                lineDatas.put("fileName", rs.getObject(2));
                datas.add(lineDatas);
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
        return datas;
    }

    /**
     * 获取封面文件信息
     * @param conn
     * @param docId
     * @return
     * @throws SQLException
     */
    public static Map<String, Object> getCoverByDocId(Connection conn, Number docId) throws SQLException {
        Map<String, Object> datas = new HashMap<String, Object>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT r.ucm_did,r.file_name\n" +
                "  FROM cms_exp_res_v t,cms_doc_ucm_rel_t r\n" +
                " WHERE t.lib_code = 'BOOK'\n" +
                "   AND t.res_code = 'COVER_HIGH_JPG'\n" +
                "  AND t.attribute1 = r.ftp_path\n" +
                " AND r.doc_type = 'COVER'\n" +
                "   AND r.doc_id = ? and rownum =1";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, docId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                datas.put("ucmDid", rs.getObject(1));
                datas.put("fileName", rs.getObject(2));
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
        return datas;
    }

    /**
     * 根据附件的路径获取附件所有的类型Code
     * @param conn
     * @param ftpPath
     * @return
     * @throws SQLException
     */
    public static String getAttachCodeByPath(Connection conn, String ftpPath) throws SQLException {
        String result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql =
                "SELECT t.lookup_value_code FROM cms_lookup_value_t t WHERE t.lookup_type_code = 'ATTACH_TYPE#BOOK' AND t.attribute1 = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, ftpPath);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
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
        return result;
    }

    public static List<ExpResModel> getExpResModels(Connection conn, String resTypeCode,
                                                    String libCode) throws SQLException {
        List<ExpResModel> list = new ArrayList<ExpResModel>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql =
                "SELECT v.res_code,v.res_name,v.lib_code,v.attribute1,v.attribute2 FROM cms_exp_res_v v WHERE v.res_type_simple_code = ?" +
                " and v.lib_code = ? and v.export_flag = 'Y'";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, resTypeCode);
            ps.setObject(2, libCode);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                ExpResModel model = new ExpResModel();
                model.setResCode(rs.getString(1));
                model.setResName(rs.getString(2));
                model.setLibCode(rs.getString(3));
                model.setColumnName(rs.getString(4));
                model.setTableName(rs.getString(5));
                list.add(model);
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

    public static Map<String, String> getResInfoByDocId(Connection conn, Number docId) throws SQLException {
        Map<String, String> result = new HashMap<String, String>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.unique_res_name res_name, c.comp_name,d.lib_code\n" +
                "  FROM cms_res_common_info_v t, cms_doc_t d, cms_comp_v c\n" +
                " WHERE t.doc_id = d.doc_id\n" +
                "   AND d.comp_code = c.comp_code\n" +
                "   AND t.doc_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, docId);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put("resName", rs.getString(1));
                result.put("compName", rs.getString(2));
                result.put("libCode", rs.getString(3));
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
        return result;
    }
}


