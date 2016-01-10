package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.util.DateUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.am.AudioAMImpl;
import com.zypg.cms.work.model.viewobject.audio.CmsAudioTVORowImpl;
import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.UCMUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.ClobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.myfaces.trinidad.component.TableUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class AudioEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText isrcIsbnInput;
    private RichInputText fileNameInputText;

    public AudioEditManagedBean() {
    }

    public void preEditAudio() {
        if (CommonUtil.getAudioAM().getDBTransaction().isDirty()) {
            CommonUtil.getAudioAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            oracle.jbo.domain.Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            Number newDocId = CommonUtil.getAudioAM().preEditAudio(mode, docId, CommonUtil.getCompCode());
            this.setExpressionValue("#{pageFlowScope.docId}", newDocId);
        } else {
            CommonUtil.getAudioAM().initAudioEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
        this.getSession().setAttribute("fileItem", null);
        this.getSession().setAttribute("fileFormat", Constants.AUDIO_VALID_FILE_FORMAT);
    }

    public void prScriptAudio() {
        oracle.jbo.domain.Number docId = null;
        docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getAudioAM().preScriptAudio(docId);
    }


    public boolean validate() {
        String isrcIsbn = (String)this.getIsrcIsbnInput().getValue();
        //正则验证isbn
        if (CommonUtil.validateIsbn(isrcIsbn) || CommonUtil.validateIsrc(isrcIsbn)) {
            return true;
        } else {
            this.addFormattedMessage(this.getIsrcIsbnInput().getClientId(), "请输入合法的ISRC/ISBN",
                                     FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    public void cancelAudioActionListener(ActionEvent actionEvent) {
        CommonUtil.getAudioAM().rollBack();
    }

    public void uploadFileActionListener(ActionEvent actionEvent) {
        this.appendScript("invokeUploadAction('" + Constants.AUDIO_VALID_FILE_FORMAT + "')");
    }

    public boolean itemValidator() {
        if (this.getFileNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getFileNameInputText().getClientId(), "文件名称必填", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        AudioAMImpl am = CommonUtil.getAudioAM();
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            am.batchEditAudio((String)this.resolveExpression("#{pageFlowScope.docId}"));
            am.getDBTransaction().commit();
            return "toReturn";
        } else {
            try {
                if (!itemValidator()) {
                    return null;
                }
                String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
                if ("CREATE".equals(mode)) {
                    FileItem fileItem = (FileItem)this.getSession().getAttribute("fileItem");
                    if (fileItem == null) {
                        this.addFormattedMessage(null, "请先上传文件", FacesMessage.SEVERITY_INFO);
                        return null;
                    } else {
                        this.getSession().setAttribute("fileItem", null);
                    }
                    Map<String, Object> metadataMap = new HashMap<String, Object>();
                    metadataMap.put("dDocTitle", fileItem.getName());
                    metadataMap.put("dDocAuthor", CommonUtil.getUsername());
                    metadataMap.put("dSecurityGroup", "Public");
                    metadataMap.put("dDocType", "Document");

                    CmsAudioTVORowImpl newRow = (CmsAudioTVORowImpl)am.getCmsAudioTVO().getCurrentRow();
                    metadataMap.put("xDocId", newRow.getDocId());
                    DataObject data =
                        DocService.getInstance().checkinUniversal(metadataMap, fileItem.getName(), fileItem.getInputStream());
                    newRow.setFileFormat(data.get("dExtension"));
                    newRow.setFileSize(CommonUtil.formartSize(fileItem.getSize()));
                    newRow.setFileStoreTime(DateUtil.getCurrentJboDate());
                    am.getDBTransaction().commit();
                    //调用ws Object[] values, String methodName, String wsdl, String namespace
                    StringBuffer sb = new StringBuffer();
                    sb.append(data.get("dID") + "");
                    sb.append(",");
                    sb.append(newRow.getDocId() + "");
                    sb.append(",");
                    sb.append("AUDIO");
                    UCMUtil.invokeWebService(new Object[] { sb.toString() }, "processMedia",
                                             Constants.VIDEO_DEAL_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE);
                } else {
                    FileItem fileItem = (FileItem)this.getSession().getAttribute("fileItem");
                    if (fileItem != null) {
                        this.getSession().setAttribute("fileItem", null);
                        Map<String, Object> metadataMap = new HashMap<String, Object>();
                        metadataMap.put("dDocTitle", fileItem.getName());
                        metadataMap.put("dDocAuthor", CommonUtil.getUsername());
                        metadataMap.put("dSecurityGroup", "Public");
                        metadataMap.put("dDocType", "Document");
                        CmsAudioTVORowImpl currRow = (CmsAudioTVORowImpl)am.getCmsAudioTVO().getCurrentRow();
                        metadataMap.put("xDocId", currRow.getDocId());
                        //                        List<Row> ucmFileList = CommonUtil.getWorkAM().getUCMFileByDocId(currRow.getDocId());
                        //                        for (Row row : ucmFileList) {
                        //                            DocService.getInstance().deleteDocByDID(row.getAttribute("UcmDid").toString());
                        //                        }
                        DataObject data =
                            DocService.getInstance().checkinUniversal(metadataMap, fileItem.getName(), fileItem.getInputStream());
                        currRow.setFileFormat(data.get("dExtension"));
                        currRow.setFileSize(CommonUtil.formartSize(fileItem.getSize()));
                        currRow.setFileStoreTime(DateUtil.getCurrentJboDate());
                        am.getDBTransaction().commit();
                        StringBuffer sb = new StringBuffer();
                        sb.append(data.get("dID") + "");
                        sb.append(",");
                        sb.append(currRow.getDocId() + "");
                        sb.append(",");
                        sb.append("AUDIO");
                        UCMUtil.invokeWebService(new Object[] { sb.toString() }, "processMedia",
                                                 Constants.VIDEO_DEAL_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE);
                    } else {
                        am.getDBTransaction().commit();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void saveAudioScriptActionListener(ClientEvent clientEvent) {
        String inputFileId = (String)clientEvent.getParameters().get("inputFileId");
        RichInputFile inputFile = (RichInputFile)clientEvent.getComponent().findComponent(inputFileId);
        UploadedFile uploadedFile = (UploadedFile)inputFile.getValue();
        if (uploadedFile != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()));
                String line = "";
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                CmsAudioTVORowImpl newRow =
                    (CmsAudioTVORowImpl)CommonUtil.getAudioAM().getCmsAudioTVO().getCurrentRow();
                newRow.setScript(new ClobDomain(sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        CommonUtil.getAudioAM().getDBTransaction().commit();
        this.navToOutCome("toReturn");
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setIsrcIsbnInput(RichInputText isrcIsbnInput) {
        this.isrcIsbnInput = isrcIsbnInput;
    }

    public RichInputText getIsrcIsbnInput() {
        return isrcIsbnInput;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void setFileNameInputText(RichInputText fileNameInputText) {
        this.fileNameInputText = fileNameInputText;
    }

    public RichInputText getFileNameInputText() {
        return fileNameInputText;
    }
}
