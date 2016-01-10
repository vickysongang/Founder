package com.zypg.cms.work.view.util;


import com.honythink.mw.ucm.bean.UcmServerConfigBean;
import com.honythink.mw.ucm.factory.UcmServerConfigFactory;

import java.sql.SQLException;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.naming.NamingException;


public class Constants {
    private static UcmServerConfigBean uscb = null;
    public static boolean IS_ONLINE_MODE = true; //true是线上环境，false是本地环境
    static {
        try {
            uscb = UcmServerConfigFactory.getInstance();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            IS_ONLINE_MODE = true;
        } else if (Pattern.matches("Windows.*", osName)) {
            IS_ONLINE_MODE = false;
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

    public static final String UCM_SYNC_WS_WSDL =
        "http://" + SERVER_ADDRESS + ":" + UCM_HTTP_PORT + "/CmsUcmSyncWS/BatchLoaderPort?WSDL";
    public static final String UCM_SYNC_WS_NAMESPACE = "http://ws.ucmsync.cms.zypg.com/";
    public static final String VIDEO_DEAL_WS_WSDL =
        "http://" + SERVER_ADDRESS + ":" + UCM_HTTP_PORT + "/CmsUcmSyncWS/UcmOperatorPort?WSDL";
    public static final String FTP_ADDRESS_PREFIX = "/home/ftpsite";

    public static final String MATERIAL_UPLOAD_PATH =
        IS_ONLINE_MODE ? "/home/oracle/cms/temp/material/" : "d:\\upload\\";
    public static final String MATERIAL_UPLOAD_TEMP_PATH =
        IS_ONLINE_MODE ? "/home/oracle/cms/temp/material/temp/" : "d:\\upload\\temp\\";

    public static final String OFFLINE_ENTRY_PROCESS_WS_WSDL =
        "http://" + SERVER_ADDRESS + ":" + UCM_HTTP_PORT + "/CmsUcmSyncWS/OfflineEntryProcessPort?WSDL";

    public static final String DOCBOOK_REST_URL =
        "http://" + SERVER_ADDRESS + ":" + UCM_HTTP_PORT + "/CmsUcmSyncWS/resources/docbook/processDocBook";

    public static final String[] ftpExcludeFiles = { "THUMBS.DB", "DESKTOP.INI" };

    public static final String GATHER_VALID_PIC_FORMAT = "jpg;jpeg;tif;tiff;gif;png;bmp;eps;dcs;psd";
    public static final String MATERIAL_FIGURE_VALID_FILE_FORMAT = "jpg;jpeg;tif;tiff;gif;png;bmp;eps;dcs;psd;";
    public static final String PHOTOGRAPHY_FIGURE_VALID_FILE_FORMAT = "jpg;jpeg;tif;tiff;gif;png;bmp;eps;dcs;psd;";
    public static final String AUDIO_VALID_FILE_FORMAT = "mp3;wma;wav;";
    public static final String GATHER_VALID_AUDIO_FORMAT = "mp3;wma;wav";
    public static final String VIDEO_VALID_FILE_FORMAT = "avi;vob;dat;mpg;wmv;flv;mp4;";
    public static final String GATHER_VALID_VIDEO_FORMAT = "avi;vob;dat;mpg;wmv;flv;mp4";

    public static final String GATHER_STATUS_PREFIX = "TO_GATHER";

    public static final String ZIP_ATTPATH =
        IS_ONLINE_MODE ? "/home/oracle/cms/entry/export.zip" : "D://templatePath/export.zip";
    public static final String TEMPLATE_PATH =
        IS_ONLINE_MODE ? "/home/oracle/cms/template/book_template/" : "C:\\Users\\vicky\\Desktop\\template\\book_template\\";

    public static int SYNC_PER_BATCH_COUNT = 500;

    public static String WORD_CLIENT_PATH = "/home/oracle/cms/entry/WordClient.zip";

    public static String RES_EXP_STORE_PATH = "/home/oracle/cms/exp";
    //    public static String RES_EXP_STORE_PATH = "D://exp";
}
