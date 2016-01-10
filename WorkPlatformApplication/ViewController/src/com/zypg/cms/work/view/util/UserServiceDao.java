package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserServiceDao {
    public UserServiceDao() {
        super();
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
