package com.zypg.cms.admin.view.util;

import com.honythink.mw.ucm.bean.UcmServerConfigBean;
import com.honythink.mw.ucm.factory.UcmServerConfigFactory;

import java.sql.SQLException;

import javax.naming.NamingException;


public class Constants {
    private static UcmServerConfigBean uscb = null;

    static {
        try {
            uscb = UcmServerConfigFactory.getInstance();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final String SERVER_ADDRESS = uscb.getUcmHost();
    public static final int UCM_HTTP_PORT = uscb.getUcmHttpPort();
    public static final int UCM_PORT = uscb.getUcmPort();
    public static final String UCM_PROTOCOL = uscb.getProtocol();
    public static final String UCM_USERNAME = uscb.getAdminUsername();
    public static final String UCM_PASSWORD = uscb.getAdminPassword();
    public static final int SOCKET_TIMEOUT = uscb.getSocketTimeout();
    public static final int CONNECTION_SIZE = uscb.getConnectionSize();

    public static final String FTP_SERVER = SERVER_ADDRESS;

    public static final int FTP_PORT = 21;
    public static final String FTP_USERNAME = "ftpadmin";
    public static final String FTP_PASSWORD = "oracle";
    
    
    public static final String PUB_PLATFORM_PORT = "9080";

    public static final boolean IS_ONLINE_MODE = true; //true是线上环境，false是本地环境

}

