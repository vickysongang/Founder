package com.zypg.cms.work.view.util;


import com.zypg.cms.foldermanager.model.FtpFile;
import com.zypg.cms.foldermanager.xmlutils.XmlUtil;
import com.zypg.cms.ws.client.CustomWSClient;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.commons.fileupload.FileItem;

import org.w3c.dom.Document;

import oracle.jbo.domain.Number;


public class UCMUtil {
    protected static String WS_RETURN_VALUE = null;

    public UCMUtil() {
        super();
    }

    public static Map<String, String> checkoutFromUCM(String dID) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("IdcService", "CHECKOUT");
        map.put("dID", dID);
        return map;
    }

    public static Map<String, String> deleteFromUCM(String dID, String dDocName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("IdcService", "DELETE_DOC");
        map.put("dID", dID);
        map.put("dDocName", dDocName);
        return map;
    }

    public static Map<String, String> checkinFileToUCM(FtpFile file, Number docId, String dDocName, Number refDocId,
                                                       String docType, String path, String compCode, String userName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("IdcService", "CHECKIN_UNIVERSAL");
        if (dDocName != null) {
            map.put("dDocName", dDocName);
        }
        map.put("dDocTitle", file.getFileName());
        map.put("doFileCopy", "1");
        map.put("dDocAuthor", userName);
        map.put("dSecurityGroup", "Public");
        map.put("dDocType", "Document");
        map.put("xDocId", docId == null ? "-99" : docId + "");
        map.put("xRefDocId", refDocId == null ? "" : refDocId + "");
        String filePath = file.getFilePath().replace(path, "");
        map.put("xFtpPath", filePath.substring(1, filePath.length() - 1));
        map.put("xFtpUploadDate", file.getLastUploadDate());
        map.put("xCompCode", compCode);
        map.put("xDocType", docType == null ? "" : docType);
        map.put("primaryFile", Constants.FTP_ADDRESS_PREFIX + file.getFilePath() + file.getFileName());
        return map;
    }

    public static Map<String, String> batchCheckinFileToUCM(FtpFile file, Number docId, String dDocName,
                                                            Number refDocId, String docType, String compCode,
                                                            String userName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("IdcService", "CHECKIN_UNIVERSAL");
        if (dDocName != null) {
            map.put("dDocName", dDocName);
        }
        map.put("dDocTitle", file.getFileName());
        map.put("doFileCopy", "1");
        map.put("dDocAuthor", userName);
        map.put("dSecurityGroup", "Public");
        map.put("dDocType", "Document");
        map.put("xDocId", docId == null ? "-99" : docId + "");
        map.put("xRefDocId", refDocId == null ? "" : refDocId + "");
        map.put("xFtpPath", file.getFilePath());
        map.put("xFtpUploadDate", file.getLastUploadDate());
        map.put("xCompCode", compCode);
        map.put("xDocType", docType == null ? "" : docType);
        map.put("primaryFile", Constants.FTP_ADDRESS_PREFIX + file.getFilePath() + file.getFileName());
        return map;
    }

    public static Map<String, String> checkinMaterialFileToUCM(FileItem fileItem, Number docId, String docType,
                                                               String compCode, String primaryFile, String userName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("IdcService", "CHECKIN_UNIVERSAL");
        map.put("dDocTitle", fileItem.getName());
        map.put("doFileCopy", "1");
        map.put("dDocAuthor", userName);
        map.put("dSecurityGroup", "Public");
        map.put("dDocType", "Document");
        map.put("xDocId", docId == null ? "-99" : docId + "");
        map.put("xDocType", docType == null ? "" : docType);
        map.put("xRefDocId", docId == null ? "" : docId + "");
        map.put("xCompCode", compCode);
        map.put("primaryFile", primaryFile);
        return map;
    }

    public static void invokeWebService(Object[] values, String methodName, String wsdl, String namespace) {
        CustomWSClient.asynInvokeWS(wsdl, namespace, values, methodName, null, new AxisCallback() {
                @Override
                public void onMessage(MessageContext mc) {
                    Document doc = XmlUtil.parseXml(mc.getEnvelope().toString());
                    String wsreturn = XmlUtil.getText(doc, "wsreturn");
                    if (wsreturn.startsWith("S")) {
                        System.out.println("wsreturn:" + wsreturn);
                        System.out.println("同步结束！！！");
                        UCMUtil.WS_RETURN_VALUE = wsreturn;
                    }
                }

                @Override
                public void onFault(MessageContext context) {
                    System.out.println("同步出现Fault..." + context.getFailureReason().getMessage());
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    System.out.println("同步出现Error," + e.getMessage());
                }

                @Override
                public void onComplete() {
                    System.out.println("同步完成...");
                }
            });
        System.out.println("等待结果！！！");
    }

    public static void main(String[] args) {
        String a = "a";
        System.out.println(a.split(",")[0]);
        //        String a = "/大象出版社/图书库/苏南冲突研究/正文/正文高精度PDF/";
        //        String b = "/大象出版社/图书库/苏南冲突研究";
        //        System.out.println(a.replace(b, "").substring(1));
        //        JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条文字串", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("S,9787500775232_9787500775232[1/1]同步成功.".substring(2));
    }
}
