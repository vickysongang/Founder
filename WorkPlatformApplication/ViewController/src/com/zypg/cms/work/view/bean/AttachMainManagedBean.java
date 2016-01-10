package com.zypg.cms.work.view.bean;


import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.viewobject.attach.CmsAttachEditTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataObject;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class AttachMainManagedBean extends CustomManagedBean {
    private UploadedFile uploadedFile;
    private RichPopup deletePopup;

    public AttachMainManagedBean() {
    }

    public void preMgmtAttach() {
        CommonUtil.getCommonAM().preMgmtAttachTable((Number)this.resolveExpression("#{pageFlowScope.docId}"),
                                                    CommonUtil.getLibCode());
        CommonUtil.getAttachAM().preMgmtAttach((Number)this.resolveExpression("#{pageFlowScope.docId}"),
                                               CommonUtil.getLibCode());
    }


    public void uploadFileValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object newFile = valueChangeEvent.getNewValue();
        if (newFile != null) {
            this.setUploadedFile((UploadedFile)newFile);
            this.setExpressionValue("#{pageFlowScope.newFile}", newFile);
            CmsAttachEditTVORowImpl attachRow =
                (CmsAttachEditTVORowImpl)CommonUtil.getAttachAM().getCmsAttachEditTVO().getCurrentRow();
            attachRow.setFileSize(new Number(this.getUploadedFile().getLength()));
        }
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void saveActionListener(ActionEvent actionEvent) {
        try {
            if (uploadedFile != null && null != uploadedFile.getInputStream()) {
                Map<String, Object> metadataMap = new HashMap<String, Object>();
                metadataMap.put("dDocTitle", uploadedFile.getFilename());
                metadataMap.put("dDocAuthor", CommonUtil.getUsername());
                metadataMap.put("dSecurityGroup", "Public");
                metadataMap.put("dDocType", "Document");
                CmsAttachEditTVORowImpl attachRow =
                    (CmsAttachEditTVORowImpl)CommonUtil.getAttachAM().getCmsAttachEditTVO().getCurrentRow();
                metadataMap.put("xDocId", this.resolveExpression("#{pageFlowScope.docId}").toString());
                metadataMap.put("xFtpPath",
                                ((CmsAttachEditTVORowImpl)CommonUtil.getAttachAM().getCmsAttachEditTVO().getCurrentRow()).getAttachTypeMeaning());
                DataObject data =
                    DocService.getInstance().checkinUniversal(metadataMap, uploadedFile.getFilename(), uploadedFile.getInputStream());
                System.out.println("data=" + data);
                attachRow.setUcmDid(new Number(data.get("dID")));
                attachRow.setUcmDocname(data.get("dDocName"));
                attachRow.setFormat(data.get("dExtension"));
                //插入doc_ucm_rel docid 存的是sourcDocId
                CommonUtil.getAttachAM().addAttach();
                CommonUtil.getAttachAM().getDBTransaction().commit();
                this.findIterator("CmsAttachTVO4ViewIterator").executeQuery();
                CommonUtil.getAttachAM().preMgmtAttach((Number)this.resolveExpression("#{pageFlowScope.docId}"),
                                                       CommonUtil.getLibCode());

            } else {
                this.addFormattedMessage(null, "请选择文件或者更新文件", FacesMessage.SEVERITY_WARN);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IdcClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getAttachAM().getDBTransaction().rollback();
    }

    public void downloadListener(FacesContext facesContext, OutputStream outputStream) {
        Number ucmDid = (Number)this.resolveExpression("#{pageFlowScope.ucmDid}");
        InputStream file;
        try {
            file = DocService.getInstance().getFile(ucmDid + "");
            byte[] buffer = new byte[1024];
            int len = file.read(buffer);
            while (len != -1) {
                outputStream.write(buffer, 0, len);
                len = file.read(buffer);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void deleteDialogActionListener(DialogEvent dialogEvent) {
        Boolean flag = true;
        String ucmDocname = (String)dialogEvent.getComponent().getAttributes().get("ucmDocname");
        Number attachId = (Number)dialogEvent.getComponent().getAttributes().get("attachId");
        try {
            CommonUtil.getCommonAM().deleteAttachById(attachId);
            DocService.getInstance().deleteDocByDDocName(ucmDocname);
            this.findIterator("CmsAttachTVO4ViewIterator").executeQuery();
        } catch (NamingException e) {
            flag = false;
            e.printStackTrace();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } catch (IdcClientException e) {
            flag = false;
            e.printStackTrace();
        }
        if (!flag) {
            this.showMessage("删除失败！");
        }
        this.showMessage("删除成功");
    }
}
