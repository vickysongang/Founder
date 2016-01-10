package com.zypg.cms.admin.view.util;

import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import com.zypg.cms.admin.view.model.ChoiceCategoryModel;
import com.zypg.cms.admin.view.model.ResCategoryModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

public class DaoUtil {
    public DaoUtil() {
        super();
    }

    /**
     * 获取分类树上所有节点的数据
     * @param compCode
     * @return
     * @throws SQLException
     */
    public static List<ResCategoryModel> getResCategoryNodes(String compCode) throws SQLException {
        List<ResCategoryModel> categoryNodes = new ArrayList<ResCategoryModel>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            ps =
 conn.prepareStatement("SELECT CATEGORY_ID,CATEGORY_NAME,PARENT_NODE_TYPE,PARENT_NODE_ID,COMP_CODE,LIB_CODE," +
                       "LIB_TYPE_CODE,PRIMARY_FLAG,CATEGORY_GROUP_CODE,CMS_ADMIN_PLATFORM_PKG.is_leaf_node(CATEGORY_ID)," +
                       "seq,attribute1,attribute2,attribute3,last_update_date FROM CMS_RES_CATEGORY_V v WHERE v.comp_code IS NULL OR v.comp_code = ?");
            ps.setObject(1, compCode);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                ResCategoryModel node = new ResCategoryModel();
                node.setCategoryId(rs.getObject(1) + "");
                node.setCategoryName(rs.getString(2));
                node.setParentNodeType(rs.getString(3));
                node.setParentNodeId(rs.getObject(4) + "");
                node.setCompCode(rs.getString(5));
                node.setLibCode(rs.getString(6));
                node.setLibTypeCode(rs.getString(7));
                node.setPrimaryFlag(rs.getString(8));
                node.setCategoryGroupCode(rs.getString(9));
                node.setIsLeafNode(rs.getString(10));
                node.setSeq(rs.getInt(11));
                node.setAttribute1(rs.getString(12));
                node.setAttribute2(rs.getString(13));
                node.setAttribute3(rs.getString(14));
                node.setLastUpdateDate(rs.getString(15));
                categoryNodes.add(node);
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
        return categoryNodes;
    }

    /**
     * 删除分类树节点
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public static String deleteResCategory(String categoryId) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_admin_platform_pkg.delete_res_category(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, Integer.parseInt(categoryId));
            cs.execute();
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
        System.out.println("cms_admin_platform_pkg.delete_res_category result = " + result);
        return result;
    }

    /**
     * 新增分类树节点
     * @param parentNodeId
     * @param categoryName
     * @param attribute1
     * @param attribute2
     * @param attribute3
     * @return
     * @throws SQLException
     */
    public static String addResCategory(String parentNodeId, String categoryName, String attribute1, String attribute2,
                                        String attribute3) throws SQLException {
        System.out.println("parentNodeId:" + parentNodeId);
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_admin_platform_pkg.add_res_category(?,?,?,?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, parentNodeId);
            cs.setObject(3, categoryName);
            cs.setObject(4, attribute1);
            cs.setObject(5, attribute2);
            cs.setObject(6, attribute3);
            cs.execute();
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
        System.out.println("cms_admin_platform_pkg.add_res_category result = " + result);
        return result;
    }

    /**
     * 修改分类树节点
     * @param categoryId
     * @param categoryName
     * @param attribute1
     * @param attribute2
     * @param attribute3
     * @return
     * @throws SQLException
     */
    public static String modifyResCategory(String categoryId, String categoryName, String attribute1,
                                           String attribute2, String attribute3) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_admin_platform_pkg.modify_res_category(?,?,?,?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, categoryId);
            cs.setObject(3, categoryName);
            cs.setObject(4, attribute1);
            cs.setObject(5, attribute2);
            cs.setObject(6, attribute3);
            cs.execute();
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
        System.out.println("cms_admin_platform_pkg.modify_res_category result = " + result);
        return result;
    }

    public static List<ChoiceCategoryModel> getChoiceCategoryNodes() throws SQLException {
        List<ChoiceCategoryModel> categoryNodes = new ArrayList<ChoiceCategoryModel>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            ps =
 conn.prepareStatement("SELECT v.category_id, v.category_name, v.parent_node_id,v.is_leaf_node,v.last_update_date," +
                       "v.category_description,v.category_code,v.category_type  FROM cms_choice_category_v v");
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                ChoiceCategoryModel node = new ChoiceCategoryModel();
                node.setCategoryId(rs.getObject(1) + "");
                node.setCategoryName(rs.getString(2));
                node.setParentNodeId(rs.getObject(3) + "");
                node.setIsLeafNode(rs.getString(4));
                node.setLastUpdateDate(rs.getString(5));
                node.setCategoryDescription(rs.getString(6) == null ? "" : rs.getString(6));
                node.setCategoryCode(rs.getString(7));
                node.setCategoryType(rs.getString(8));
                categoryNodes.add(node);
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
        return categoryNodes;
    }

    /**
     * 删除下拉列表分类树节点
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public static String deleteChoiceCategory(String categoryId) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_admin_platform_pkg.delete_choice_category(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, categoryId);
            cs.execute();
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
        System.out.println("cms_admin_platform_pkg.delete_choice_category result = " + result);
        return result;
    }

    /**
     * 修改下拉分类树中的节点
     * @param categoryId
     * @param categoryName
     * @param categoryDesc
     * @return
     * @throws SQLException
     */
    public static String modifyChoiceCategory(String categoryId, String categoryName,
                                              String categoryDesc) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_admin_platform_pkg.modify_choice_category(?,?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, categoryId);
            cs.setObject(3, categoryName);
            cs.setObject(4, categoryDesc);
            cs.execute();
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
        System.out.println("cms_admin_platform_pkg.modify_choice_category result = " + result);
        return result;
    }

    /**
     * 增加下拉列表分类树节点
     * @param parentNodeId
     * @param categoryCode
     * @param categoryName
     * @param categoryType
     * @param categoryDesc
     * @return
     * @throws SQLException
     */
    public static String addChoiceCategory(String parentNodeId, String categoryCode, String categoryName,
                                           String categoryType, String categoryDesc) throws SQLException {
        String result = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getJndiDatasource().getConnection();
            cs = conn.prepareCall("{ ? = call cms_admin_platform_pkg.add_choice_category(?,?,?,?,?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setObject(2, parentNodeId);
            cs.setObject(3, categoryCode);
            cs.setObject(4, categoryName);
            cs.setObject(5, categoryType);
            cs.setObject(6, categoryDesc);
            cs.execute();
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
        System.out.println("cms_admin_platform_pkg.add_choice_category result = " + result);
        return result;
    }

    public static String getUserPassword(String userName) {
        String userPassword = null;
        if (userName != null && !"".equals(userName)) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DataSourceUtil.getJndiDatasource().getConnection();
                ps = conn.prepareStatement("SELECT t.password FROM cms_user_t t WHERE t.user_name = ?");
                ps.setString(1, userName.toUpperCase());
                rs = ps.executeQuery();
                while (rs.next()) {
                    userPassword = rs.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (ps != null)
                        ps.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return userPassword;
    }
}
