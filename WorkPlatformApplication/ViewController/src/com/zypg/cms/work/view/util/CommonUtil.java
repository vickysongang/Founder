package com.zypg.cms.work.view.util;


import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;

import com.honythink.applicationframework.hadf.util.ADFUtils;
import com.honythink.applicationframework.hadf.util.JSFUtils;

import com.zypg.cms.foldermanager.model.FolderConstants;
import com.zypg.cms.work.model.WorkAMImpl;
import com.zypg.cms.work.model.am.ActivityAMImpl;
import com.zypg.cms.work.model.am.AttachAMImpl;
import com.zypg.cms.work.model.am.AudioAMImpl;
import com.zypg.cms.work.model.am.BookAMImpl;
import com.zypg.cms.work.model.am.CommonAMImpl;
import com.zypg.cms.work.model.am.CopyrightAMImpl;
import com.zypg.cms.work.model.am.CoursewareAMImpl;
import com.zypg.cms.work.model.am.DocBookAMImpl;
import com.zypg.cms.work.model.am.ElecProdAMImpl;
import com.zypg.cms.work.model.am.EntryAMImpl;
import com.zypg.cms.work.model.am.ExtendAttrAMImpl;
import com.zypg.cms.work.model.am.FigureGroupAMImpl;
import com.zypg.cms.work.model.am.IllustrationAMImpl;
import com.zypg.cms.work.model.am.LogAMImpl;
import com.zypg.cms.work.model.am.MaterialFigureAMImpl;
import com.zypg.cms.work.model.am.NewspaperAMImpl;
import com.zypg.cms.work.model.am.OfflineAMImpl;
import com.zypg.cms.work.model.am.PeriodicalAMImpl;
import com.zypg.cms.work.model.am.PhotographyFigureAMImpl;
import com.zypg.cms.work.model.am.RelationAMImpl;
import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.am.TeachingPlanAMImpl;
import com.zypg.cms.work.model.am.ThemeAMImpl;
import com.zypg.cms.work.model.am.VideoAMImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Method;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jftp.config.Settings;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import oracle.sql.CLOB;

import org.apache.commons.lang.ArrayUtils;


public class CommonUtil {
    private static String SPLIT = "@quot@";

    public CommonUtil() {
        super();
    }

    public static List<Row> getValueFromTable(String clomnNames, String table, String whereClause, WorkAMImpl workAm) {
        List<Row> rets = new ArrayList<Row>();
        String dynimicSql = "select " + clomnNames + " from " + table + " where " + whereClause;
        System.out.println("dynimicSql--->" + dynimicSql);
        Object existVo = workAm.findViewObject("dynamocVO4Common");
        if (existVo != null) {
            workAm.deleteChild(existVo);
        }
        ViewObjectImpl dynamicVo =
            (ViewObjectImpl)workAm.createViewObjectFromQueryStmt("dynamocVO4Common", dynimicSql);
        dynamicVo.executeQuery();
        dynamicVo.setRangeSize(-1);
        if (dynamicVo.getEstimatedRowCount() > 0) {
            for (Row fr : dynamicVo.getAllRowsInRange()) {
                rets.add(fr);
            }
        }
        return rets;
    }

    public static String findLovTypeCodeByMeaning(String lookupTypeCode, String meaning) {
        return CommonUtil.getWorkAM().findLovTypeCodeByMeaning(lookupTypeCode, meaning);
    }

    public static String findLovMeaingByLookupValueCode(String lookupTypeCode, String lookupValueCode) {
        return CommonUtil.getWorkAM().findLovMeaingByLookupValueCode(lookupTypeCode, lookupValueCode);
    }

    public static WorkAMImpl getWorkAM() {
        return (WorkAMImpl)ADFUtils.getAM("WorkAMDataControl", null);
    }

    public static NewspaperAMImpl getNewspaperAM() {
        return (NewspaperAMImpl)ADFUtils.getAM("WorkAMDataControl", "NewspaperAM");
    }

    public static BookAMImpl getBookAM() {
        return (BookAMImpl)ADFUtils.getAM("WorkAMDataControl", "BookAM");
    }

    public static ElecProdAMImpl getElecProdAM() {
        return (ElecProdAMImpl)ADFUtils.getAM("WorkAMDataControl", "ElecProdAM");
    }

    public static CopyrightAMImpl getCopyrightAM() {
        return (CopyrightAMImpl)ADFUtils.getAM("WorkAMDataControl", "CopyrightAM");
    }

    public static AudioAMImpl getAudioAM() {
        return (AudioAMImpl)ADFUtils.getAM("WorkAMDataControl", "AudioAM");
    }

    public static AttachAMImpl getAttachAM() {
        return (AttachAMImpl)ADFUtils.getAM("WorkAMDataControl", "AttachAM");
    }

    public static VideoAMImpl getVideoAM() {
        return (VideoAMImpl)ADFUtils.getAM("WorkAMDataControl", "VideoAM");
    }

    public static LogAMImpl getLogAM() {
        return (LogAMImpl)ADFUtils.getAM("WorkAMDataControl", "LogAM");
    }

    public static RelationAMImpl getRelationAM() {
        return (RelationAMImpl)ADFUtils.getAM("WorkAMDataControl", "RelationAM");
    }

    public static PeriodicalAMImpl getPeriodicalAM() {
        return (PeriodicalAMImpl)ADFUtils.getAM("WorkAMDataControl", "PeriodicalAM");
    }

    public static MaterialFigureAMImpl getMaterialFigureAM() {
        return (MaterialFigureAMImpl)ADFUtils.getAM("WorkAMDataControl", "MaterialFigureAM");
    }

    public static PhotographyFigureAMImpl getPhotographyFigureAM() {
        return (PhotographyFigureAMImpl)ADFUtils.getAM("WorkAMDataControl", "PhotographyFigureAM");
    }

    public static ActivityAMImpl getActivityAM() {
        return (ActivityAMImpl)ADFUtils.getAM("WorkAMDataControl", "ActivityAM");
    }

    public static CoursewareAMImpl getCoursewareAM() {
        return (CoursewareAMImpl)ADFUtils.getAM("WorkAMDataControl", "CoursewareAM");
    }

    public static IllustrationAMImpl getIllustrationAM() {
        return (IllustrationAMImpl)ADFUtils.getAM("WorkAMDataControl", "IllustrationAM");
    }

    public static TeachingPlanAMImpl getTeachingPlanAM() {
        return (TeachingPlanAMImpl)ADFUtils.getAM("WorkAMDataControl", "TeachingPlanAM");
    }

    public static FigureGroupAMImpl getFigureGroupAM() {
        return (FigureGroupAMImpl)ADFUtils.getAM("WorkAMDataControl", "FigureGroupAM");
    }

    public static DocBookAMImpl getDocBookAM() {
        return (DocBookAMImpl)ADFUtils.getAM("WorkAMDataControl", "DocBookAM");
    }

    public static ResExpAMImpl getResExpAM() {
        return (ResExpAMImpl)ADFUtils.getAM("WorkAMDataControl", "ResExpAM");
    }

    public static CommonAMImpl getCommonAM() {
        return (CommonAMImpl)ADFUtils.getAM("WorkAMDataControl", "CommonAM");
    }

    public static EntryAMImpl getEntryAM() {
        return (EntryAMImpl)ADFUtils.getAM("WorkAMDataControl", "EntryAM");
    }

    public static OfflineAMImpl getOfflineAM() {
        return (OfflineAMImpl)ADFUtils.getAM("WorkAMDataControl", "OfflineAM");
    }

    public static ThemeAMImpl getThemeAM() {
        return (ThemeAMImpl)ADFUtils.getAM("WorkAMDataControl", "ThemeAM");
    }

    public static ExtendAttrAMImpl getExtendAttrAM() {
        return (ExtendAttrAMImpl)ADFUtils.getAM("WorkAMDataControl", "ExtendAttrAM");
    }

    public static void putStringToSession(String key, String value) {
        getWorkAM().putStringToSession(key, value);
    }

    public static void putObjectToSession(String key, Object value) {
        getWorkAM().putValueToSession(key, value);
    }

    public static Object getValueFromSession(String key) {
        return getWorkAM().getValueFromSession(key);
    }

    public static String getCurrStatusCode() {
        String currStatusCode = null;
        String deleteFlag = CommonUtil.getDeleteFlag();
        if ("Y".equals(deleteFlag)) {
            currStatusCode = "DELETED_" + CommonUtil.getLibCode();
        } else {
            currStatusCode = (String)JSFUtils.resolveExpression("#{pageFlowScope.currStatusCode}");
        }
        return currStatusCode;
    }

    public static String getLibCode() {
        return (String)JSFUtils.resolveExpression("#{pageFlowScope.libCode}");
    }

    public static String getLibName() {
        return getWorkAM().getLibNameByCode(getLibCode());
    }

    public static String getDeleteFlag() {
        return (String)JSFUtils.resolveExpression("#{pageFlowScope.deleteFlag}");
    }

    public static String getCompCode() {
        return (String)JSFUtils.resolveExpression("#{pageFlowScope.compCode}");
    }

    public static String getLibTypeCode() {
        return (String)JSFUtils.resolveExpression("#{pageFlowScope.libTypeCode}");
    }

    public static String getCompName() {
        return getWorkAM().getCompNameByCode(getCompCode());
    }

    public static String getUsername() {
        return getWorkAM().getCustomDBTransaction().getUserName();
    }

    public static Number getUserId() {
        return getWorkAM().getCustomDBTransaction().getUserId();
    }

    public static String getDocNameByDocId(Number docId) {
        return getWorkAM().getDocNameByDocId(docId);
    }

    public static String formartSize(long fileSize) {
        if (fileSize >> 30 >= 1 && fileSize >> 30 < 1024) {
            return (String.format("%10.2f", fileSize / 1024.0 / 1024.0 / 1024.0) + "GB").trim();
        } else if (fileSize >> 20 >= 1 && fileSize >> 20 < 1024) {
            return (String.format("%10.2f", fileSize / 1024.0 / 1024.0) + "MB").trim();
        } else if (fileSize >> 10 >= 1 && fileSize >> 10 < 1024) {
            return (String.format("%10.2f", fileSize / 1024.0) + "KB").trim();
        } else {
            return fileSize + "B";
        }
    }

    public static boolean validateIsrc(String isrc) {
        //原版就木有
        return true;
    }

    public static boolean validateIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("((978-)?7-\\d{2,5}-\\d{1,6}-[\\d|X])|((978)?7\\d{2,5}\\d{1,6}[\\d|X])");
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }

    public static boolean validateIssn(String issn) {
        //原版就木有
        return true;
    }

    public static Map<String, String> readExifMetadata(File file) throws Exception {
        Map<String, String> exifMap = new HashMap<String, String>();
        Metadata metadata = JpegMetadataReader.readMetadata(file);
        Directory directory = metadata.getDirectory(ExifDirectory.class);
        Iterator<?> tags = directory.getTagIterator();
        while (tags.hasNext()) {
            Tag tag = (Tag)tags.next();
            exifMap.put(tag.getTagName(), tag.getDescription());
        }
        return exifMap;
    }

    public static Map<String, String> readExifMetadata(InputStream is) throws Exception {
        Map<String, String> exifMap = new HashMap<String, String>();
        Metadata metadata = JpegMetadataReader.readMetadata(is);
        Directory directory = metadata.getDirectory(ExifDirectory.class);
        Iterator<?> tags = directory.getTagIterator();
        while (tags.hasNext()) {
            Tag tag = (Tag)tags.next();
            exifMap.put(tag.getTagName(), tag.getDescription());
        }
        return exifMap;
    }

    public static String getIPAddress(String name) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("获取失败");
        }
        return address.getHostAddress().toString();
    }

    public static String getUuidString() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getNetworkCardIp() {
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr != null && addr instanceof Inet4Address) {
                        System.out.println("IP:" + addr.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            String str = (String)o;
            str = str.trim();
            if (str.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void initFtpConn() {
        Settings.setProperty("jftp.disableLog", true);
        FolderConstants.ftpServer = Constants.FTP_SERVER;
        FolderConstants.ftpPort = Constants.FTP_PORT;
        FolderConstants.userName = Constants.FTP_USERNAME;
        FolderConstants.userPass = Constants.FTP_PASSWORD;
    }

    public static boolean isValidFormatWhenBatchSync(String libCode, String fileFormat) {
        String[] fileFormats = null;
        if ("AUDIO".equals(libCode)) {
            fileFormats = Constants.GATHER_VALID_AUDIO_FORMAT.split(";");
        } else if ("VIDEO".equals(libCode)) {
            fileFormats = Constants.GATHER_VALID_VIDEO_FORMAT.split(";");
        } else if ("MATERIAL_FIGURE".equals(libCode)) {
            fileFormats = Constants.GATHER_VALID_PIC_FORMAT.split(";");
        } else if ("PHOTOGRAPHY_FIGURE".equals(libCode)) {
            fileFormats = Constants.GATHER_VALID_PIC_FORMAT.split(";");
        } else {
            return true;
        }
        if (!ArrayUtils.contains(fileFormats, fileFormat.toLowerCase())) {
            return false;
        }
        return true;
    }

    public static boolean isValidFormatWhenSync(String docType, String fileFormat) {
        String[] fileFormats = null;
        if ("AUDIO".equalsIgnoreCase(docType)) {
            fileFormats = Constants.GATHER_VALID_AUDIO_FORMAT.split(";");
        } else if ("VIDEO".equalsIgnoreCase(docType)) {
            fileFormats = Constants.GATHER_VALID_VIDEO_FORMAT.split(";");
        } else if ("COVER".equalsIgnoreCase(docType)) {
            fileFormats = Constants.GATHER_VALID_PIC_FORMAT.split(";");
        } else if ("ILLUSTRATION".equalsIgnoreCase(docType)) {
            fileFormats = Constants.GATHER_VALID_PIC_FORMAT.split(";");
        } else {
            return true;
        }
        if (!ArrayUtils.contains(fileFormats, fileFormat.toLowerCase())) {
            return false;
        }
        return true;
    }

    public static boolean removeFtpDirs(String ftpPath) {
        boolean result = false;
        Process pid;
        System.out.println("removeFtpDirs---->ftpPath:" + ftpPath);
        StringBuffer cmdout = new StringBuffer();
        try {
            ftpPath = ftpPath + "###";
            StringBuffer shell = new StringBuffer();
            shell.append("/home/oracle/cms/bin/removeFtpDirs.sh");
            shell.append(" " + ftpPath.replaceAll(" ", "@#^"));
            System.out.println("removeFtpDir shell:" + shell.toString());
            pid = Runtime.getRuntime().exec(shell.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(pid.getErrorStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                cmdout.append(line).append(System.getProperty("line.separator"));
            }
            result = true;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public static String getUnicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            byte[] bytes = s.getBytes("unicode");
            for (int i = 0; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                out.append(str1);
                out.append(str);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> listDirectory(String path, Map<String, String> fileMap) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                listDirectory(files[i].getAbsolutePath(), fileMap);
            } else {
                //                System.out.println(files[i].getAbsolutePath());
                fileMap.put(files[i].getName(), files[i].getAbsolutePath());
            }
        }
        return fileMap;
    }

    /**
     * 下载文件到本地
     * @param urlString
     *          被下载的文件地址
     * @param filename
     *          本地文件名
     * @throws Exception
     *           各种异常
     */
    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    public static InputStream getInputStreamByUrl(String urlString) {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(urlString);
            URLConnection con = url.openConnection();
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public static String encrypt(String mingwen) {
        String ret = null;
        if (mingwen == null) {
            return null;
        }
        byte[] chs;
        try {
            chs = mingwen.getBytes("UTF-8");
            int i = 0;
            for (byte ch : chs) {
                if (ret == null) {
                    ret = (byte)(ch + 5) + "";
                } else {
                    ret = ret + SPLIT + (byte)(ch + 5) + "";
                }
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 把CLOB转换成String
     * @return
     */
    public static String clobToString(Object obj) {
        String result = "";
        try {
            if (obj != null) {
                CLOB cObj = null;
                if (obj.getClass().equals("oracle/sql/CLOB"))
                    cObj = (CLOB)obj;
                else if ("weblogic.jdbc.wrapper.Clob_oracle_sql_CLOB".equals(obj.getClass().getName())) {
                    Method method = obj.getClass().getMethod("getVendorObj", new Class[0]);
                    cObj = (CLOB)method.invoke(obj, new Object[0]);
                } else if (obj.getClass().equals("java/lang/String"))
                    result = (new StringBuilder()).append(obj).toString();
                if (cObj != null)
                    result = cObj.getSubString(1L, (int)cObj.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取图片高度和宽度
     * @param filePath
     * @return
     */
    public static Map<String, Integer> getPicWidthAndHeight(String filePath) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String command = "identify " + filePath + " | awk '{print $3}'";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String currentLine = "";
            while ((currentLine = in.readLine()) != null) {
                if (currentLine.trim().length() > 0) {
                    String[] arr = currentLine.split("x");
                    map.put("width", Integer.valueOf(arr[0]));
                    map.put("height", Integer.valueOf(arr[1]));
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("获取图片高度和宽度出错");
            map.put("width", 0);
            map.put("height", 0);
        }
        return map;
    }

    public static void main(String[] args) {
        //        System.out.println(CommonUtil.getUnicode("主题.xml"));
        //        String a = "http://127.0.0.1:7101/faces/work";
        //        System.out.println(a.substring(0, a.indexOf("faces")));
        //((978-)?7-\d{2,5}-\d{1,6}-[\d|X])|((978)?7\d{2,5}\d{1,6}[\d|X])
        //        System.out.println(CommonUtil.validateIsbn("978-7-55590-011-5"));
        //        System.out.println(CommonUtil.validateIsbn("978-7-555-90011-5"));//978-7-452-15845-1
        //        System.out.println(CommonUtil.validateIsbn("978-7-452-15845-1"));
        System.out.println(CommonUtil.encrypt("JIANGMINGLEI" + CommonUtil.SPLIT + "E10ADC3949BA59ABBE56E057F20F883E"));
        //        try {
        //            CommonUtil.download("http://ucm.hold.founder.com:16200/cs/groups/public/documents/document/exbn/mda2/~edisp/zypg006770.jpg",
        //                                "D:/a.jpg");
        //        } catch (Exception e) {
        //        }
    }
}
