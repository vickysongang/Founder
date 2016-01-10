package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.foldermanager.model.Folder;
import com.zypg.cms.foldermanager.model.FolderFactory;
import com.zypg.cms.work.view.model.UcmFileModel;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import net.sf.jftp.net.FtpConnection;

import oracle.jbo.domain.Number;


public class FileManageThread implements Runnable {
    private List<String> fileManageList = new ArrayList<String>();
    private String compName;
    private String libName;
    private String currStatus;


    public FileManageThread() {
        super();
    }

    public FileManageThread(List<String> fileManageList, String compName, String libName, String currStatus) {
        this.fileManageList = fileManageList;
        this.compName = compName;
        this.libName = libName;
        this.currStatus = currStatus;
    }

    @Override
    public void run() {
        try {
            CommonUtil.initFtpConn();
            FolderManager fm = new FolderManager();
            FtpConnection ftpConn = fm.getConn();
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            for (String key : fileManageList) {
                Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
                //参数 用户登录名 出版社名(中文) LibCode “具体库名（中文）”
                List<String> folders = new ArrayList<String>();
                //根据lookup表获取图书库的所有文件结构
                String directorys = DaoUtil.getFileManageDirectory(conn, docId);
                if (directorys == null || directorys.length() == 0) {
                    continue;
                }
                for (String directory : directorys.split(",")) {
                    if (directory != null && directory.length() > 0) {
                        folders.add(directory);
                    }
                }
                String docName = DaoUtil.getDocNameByDocId(conn, docId);
                Folder rootFolder = FolderFactory.createFolderByList(compName, libName, docName, folders);
                fm.mkAllDir(rootFolder);
                System.out.println("currStatus:" + currStatus);
                if (currStatus.contains(Constants.GATHER_STATUS_PREFIX)) {
                    System.out.println("从采集状态点击文件管理");
                    continue;
                }
                //生成占位符
                List<UcmFileModel> ucmFileList = DaoUtil.getUCMFilesByDocId(conn, docId);
                int size = ucmFileList.size();
                DaoUtil.insertFileManageMonitorRecord(conn, docId, 0, size); //先归零
                if (ucmFileList != null && size > 0) {
                    int i = 0;
                    for (UcmFileModel file : ucmFileList) {
                        i++;
                        String ftpRelativePath = file.getFilePath();
                        String ftpPath = "/" + compName + "/" + libName + "/" + docName + "/" + ftpRelativePath;
                        String fileName = file.getFileName();
                        Number dId = file.getUcmDid();
                        String ucmDocName = file.getUcmDocName();
                        String placeholderFlag = "FEILIUZHIXIASANQIANCHIPLACEHOLDER";
                        String placeholderStr =
                            placeholderFlag + "/docId---->" + docId + "#@#" + "fileName" + "---->" + fileName + "#@#" +
                            "filePath" + "---->" + ftpPath + "#@#" + "ucmDocId" + "---->" + dId.toString() + "#@#" +
                            "ucmDocName" + "---->" + ucmDocName;
                        ByteArrayInputStream in = null;
                        try {
                            in = new ByteArrayInputStream(placeholderStr.getBytes("UTF-8"));
                            ftpConn.sendFtpCommand(FtpConnection.CWD + " " + ftpPath);
                            ftpConn.updatePWD();
                            ftpConn.upload(fileName, fileName, in);
                            DaoUtil.insertFileManageMonitorRecord(conn, docId, i, size); //先归零
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } finally {
                            if (in != null) {
                                in.close();
                            }
                        }
                    }
                    ftpConn.sendFtpCommand(FtpConnection.CWD + " /");
                    ftpConn.updatePWD();
                }
                System.out.println("改变状态。。。");
                DaoUtil.fileManage(conn, docId, null);
            }
            if (ftpConn != null) {
                ftpConn.disconnect();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFileManageList(List<String> fileManageList) {
        this.fileManageList = fileManageList;
    }

    public List<String> getFileManageList() {
        return fileManageList;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompName() {
        return compName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getLibName() {
        return libName;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus;
    }

    public String getCurrStatus() {
        return currStatus;
    }
}
