package com.zypg.cms.work.view.util;

import com.honythink.applicationframework.hadf.util.DataSourceUtil;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.foldermanager.model.FtpFile;

import com.zypg.cms.foldermanager.model.LoadBatchUtil;

import com.zypg.cms.work.view.model.UcmFileModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.commons.lang.ArrayUtils;

public class SyncThread implements Runnable {
    private List<String> syncList = new ArrayList<String>();
    private String compCode;
    private String compName;
    private String libCode;
    private String libName;
    private Number userId;
    private String userName;

    public SyncThread(List<String> syncList, String compCode, String compName, String libCode, String libName,
                      Number userId, String userName) {
        this.syncList = syncList;
        this.compCode = compCode;
        this.compName = compName;
        this.libCode = libCode;
        this.libName = libName;
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            System.out.println("同步开始...");
            CommonUtil.initFtpConn(); //初始化FTP的连接信息
            FolderManager fm = new FolderManager();
            LoadBatchUtil util = new LoadBatchUtil();
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
            List<Number> list = new ArrayList<Number>();
            StringBuffer sb = new StringBuffer();
            for (String key : syncList) {
                Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
                String batchNum = DaoUtil.getSyncBatchNum(conn, docId);
                String docName = DaoUtil.getDocNameByDocId(conn, docId);
                String path = "/" + compName + "/" + libName + "/" + docName;
                list.add(docId);
                sb.append(docId + ",");
                List<FtpFile> files = fm.getFtpFiles(path);
                Collections.sort(files);
                //获取已经存在的文件
                List<UcmFileModel> ucmFileList = DaoUtil.getUCMFilesByDocId(conn, docId);
                //删除上次的同步监控日志
                DaoUtil.deleteGatherMonitorRecord(conn, docId);
                int syncFileCount = 0; //用来计算同步的书籍里是否有可以同步的文件
                //当UCM上没有该资源的文件时，代表所有的文件都是新上传
                if (ucmFileList.size() == 0) {
                    for (FtpFile file : files) {
                        System.out.println("文件信息：" + file.getFileName() + "   " + file.getFileFormat());
                        if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
                            String docType = DaoUtil.getDocType(conn, libCode, file, path);
                            if (CommonUtil.isValidFormatWhenSync(docType, file.getFileFormat())) {
                                Map<String, String> map =
                                    UCMUtil.checkinFileToUCM(file, docId, null, null, docType, path, compCode,
                                                             userName);
                                util.addData(map);
                                DaoUtil.processSyncLog(conn, userId, docId, file.getFileName(),
                                                       getFilePath(file, path), null, compCode, libCode, "新增", null,
                                                       null, null, batchNum);
                                syncFileCount++;
                            } else {
                                DaoUtil.processSyncLog(conn, userId, new Number(-99), file.getFileName(),
                                                       file.getFilePath(), userName, compCode, libCode, "新增", "FAIL",
                                                       null, "不支持该格式的文件", batchNum);
                                continue;
                            }
                        }
                    }
                } else {
                    List<UcmFileModel> deleteList = new ArrayList<UcmFileModel>();
                    for (UcmFileModel ucmFile : ucmFileList) {
                        boolean existFlag = false;
                        String ucmPath = ucmFile.getFilePath() + "/" + ucmFile.getFileName();
                        for (FtpFile file : files) {
                            if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
                                if (ucmPath.equals(getFilePath(file, path) + "/" + file.getFileName())) {
                                    existFlag = true;
                                    break;
                                }
                            }
                        }
                        if (!existFlag) {
                            Number ucmDid = ucmFile.getUcmDid();
                            Map<String, String> map =
                                UCMUtil.deleteFromUCM(ucmDid.toString(), ucmFile.getUcmDocName());
                            util.addData(map);
                            DaoUtil.processSyncLog(conn, userId, docId, ucmFile.getFileName(), ucmFile.getFilePath(),
                                                   null, compCode, libCode, "删除", null, null, null, batchNum);
                            syncFileCount++;
                            deleteList.add(ucmFile);
                        }
                    }
                    for (UcmFileModel deleteFile : deleteList) {
                        ucmFileList.remove(deleteFile);
                    }
                    for (FtpFile file : files) {
                        if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
                            String pathName = getFilePath(file, path) + file.getFileName();
                            boolean existFlag = false;
                            String uploadDate = null;
                            Number ucmDid = null;
                            String ucmDocname = null;
                            Number refDocId = null;
                            boolean b = PlaceHolderUtil.isPlaceHolder(fm, file);
                            if (b) {
                                continue;
                            }
                            for (UcmFileModel ucmFile : ucmFileList) {
                                String pathName1 = ucmFile.getFilePath() + ucmFile.getFileName();
                                if (pathName.equals(pathName1)) {
                                    existFlag = true;
                                    uploadDate = ucmFile.getFtpUploadDate();
                                    ucmDid = ucmFile.getUcmDid();
                                    ucmDocname = ucmFile.getUcmDocName();
                                    refDocId = ucmFile.getRefDocId();
                                    break;
                                }
                            }
                            if (existFlag) {
                                if (!file.getLastUploadDate().equals(uploadDate)) {
                                    Map<String, String> map1 = UCMUtil.checkoutFromUCM(ucmDid.toString());
                                    util.addData(map1);
                                    String docType = DaoUtil.getDocType(conn, libCode, file, path);
                                    Map<String, String> map2 =
                                        UCMUtil.checkinFileToUCM(file, docId, ucmDocname, refDocId, docType, path,
                                                                 compCode, userName);
                                    util.addData(map2);
                                    DaoUtil.processSyncLog(conn, userId, docId, file.getFileName(),
                                                           getFilePath(file, path), null, compCode, libCode, "更新",
                                                           null, null, null, batchNum);
                                    syncFileCount++;
                                }
                            } else {
                                String docType = DaoUtil.getDocType(conn, libCode, file, path);
                                if (CommonUtil.isValidFormatWhenSync(docType, file.getFileFormat())) {
                                    Map<String, String> map =
                                        UCMUtil.checkinFileToUCM(file, docId, null, refDocId, docType, path, compCode,
                                                                 userName);
                                    util.addData(map);
                                    DaoUtil.processSyncLog(conn, userId, docId, file.getFileName(),
                                                           getFilePath(file, path), null, compCode, libCode, "新增",
                                                           null, null, null, batchNum);
                                    syncFileCount++;
                                } else {
                                    DaoUtil.processSyncLog(conn, userId, new Number(-99), file.getFileName(),
                                                           file.getFilePath(), userName, compCode, libCode, "新增",
                                                           "FAIL", null, "不支持该格式的文件", batchNum);
                                    continue;
                                }
                            }
                        }
                    }
                }
                System.out.println("syncFileCount:" + syncFileCount);
                if (syncFileCount == 0) {
                    DaoUtil.endSync(conn, docId.toString());
                }
            }
            String transData = util.getSplitTransData(Constants.SYNC_PER_BATCH_COUNT);
            UCMUtil.invokeWebService(new Object[] { transData + "###" + sb.toString() + "###" + userId },
                                     "doBatchLoadWithParam", Constants.UCM_SYNC_WS_WSDL,
                                     Constants.UCM_SYNC_WS_NAMESPACE);
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath(FtpFile file, String path) {
        String filePath = file.getFilePath().replace(path, "");
        return filePath.substring(1, filePath.length() - 1);
    }

    public void setSyncList(List<String> syncList) {
        this.syncList = syncList;
    }

    public List<String> getSyncList() {
        return syncList;
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

    public void setLibCode(String libCode) {
        this.libCode = libCode;
    }

    public String getLibCode() {
        return libCode;
    }

    public void setUserId(Number userId) {
        this.userId = userId;
    }

    public Number getUserId() {
        return userId;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
