package com.zypg.cms.work.view.model;

import oracle.jbo.domain.Number;

public class UcmFileModel {
    private Number docId;
    private String ucmDocName;
    private String fileName;
    private String filePath;
    private Number ucmDid;
    private String ftpUploadDate;
    private Number refDocId;

    public UcmFileModel() {
        super();
    }

    public void setDocId(Number docId) {
        this.docId = docId;
    }

    public Number getDocId() {
        return docId;
    }

    public void setUcmDocName(String ucmDocName) {
        this.ucmDocName = ucmDocName;
    }

    public String getUcmDocName() {
        return ucmDocName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFtpUploadDate(String ftpUploadDate) {
        this.ftpUploadDate = ftpUploadDate;
    }

    public String getFtpUploadDate() {
        return ftpUploadDate;
    }

    public void setUcmDid(Number ucmDid) {
        this.ucmDid = ucmDid;
    }

    public Number getUcmDid() {
        return ucmDid;
    }

    public void setRefDocId(Number refDocId) {
        this.refDocId = refDocId;
    }

    public Number getRefDocId() {
        return refDocId;
    }
}
