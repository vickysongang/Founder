package com.zypg.cms.work.view.bean;


import com.zypg.cms.foldermanager.model.LoadBatchUtil;
import com.zypg.cms.work.model.am.MaterialFigureAMImpl;
import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.UCMUtil;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import javax.imageio.ImageIO;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;

import org.apache.commons.fileupload.FileItem;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;


public class MaterialEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText figureNameInputText;

    public MaterialEditManagedBean() {
    }

    public void preEditMaterial() {
        if (CommonUtil.getMaterialFigureAM().getDBTransaction().isDirty()) {
            CommonUtil.getMaterialFigureAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number docId = null;
            if ("CREATE".equals(mode)) {
                docId = CommonUtil.getMaterialFigureAM().preEditMaterial(mode, docId, CommonUtil.getCompCode());
                this.setExpressionValue("#{pageFlowScope.docId}", docId);
            } else {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
                CommonUtil.getMaterialFigureAM().preEditMaterial(mode, docId, CommonUtil.getCompCode());
            }
            System.out.println("init docId:" + docId);
        } else {
            CommonUtil.getMaterialFigureAM().initMaterialFigureEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
        this.getSession().setAttribute("fileItem", null);
        this.getSession().setAttribute("fileFormat", Constants.MATERIAL_FIGURE_VALID_FILE_FORMAT);
    }

    public void uploadFileActionListener(ActionEvent actionEvent) {
        this.appendScript("invokeUploadAction('" + Constants.MATERIAL_FIGURE_VALID_FILE_FORMAT + "')");
    }

    public boolean itemValidator() {
        if (this.getFigureNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getFigureNameInputText().getClientId(), "图片名称必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        MaterialFigureAMImpl am = CommonUtil.getMaterialFigureAM();
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (!"Y".equals(batchFlag)) {
            Number docId = null;
            try {
                docId = new Number(this.resolveExpression("#{pageFlowScope.docId}"));
            } catch (SQLException e) {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            if ("CREATE".equals(mode)) {
                if (!itemValidator()) {
                    return null;
                }
                try {
                    FileItem fileItem = (FileItem)this.getSession().getAttribute("fileItem");
                    if (fileItem == null) {
                        this.addFormattedMessage(null, "请先上传文件", FacesMessage.SEVERITY_INFO);
                        return null;
                    } else {
                        this.getSession().setAttribute("fileItem", null);
                    }
                    File file = new File(Constants.MATERIAL_UPLOAD_PATH + fileItem.getName());
                    InputStream is = new FileInputStream(file);
                    try {
                        ImageInfo imageInfo = Sanselan.getImageInfo(is, fileItem.getName());
                        String fileSize = CommonUtil.formartSize(fileItem.getSize());
                        am.updateMaterialFigureInfo(imageInfo, fileSize);
                    } catch (ImageReadException ire) {
                        System.out.println("ImageReadException:" + ire.getMessage());
                        try {
                            System.out.println(fileItem.getName().substring(fileItem.getName().lastIndexOf(".") + 1));
                            BufferedImage buff = ImageIO.read(is);
                            am.updateMaterialFigure(fileItem.getName().substring(fileItem.getName().lastIndexOf(".") +
                                                                                 1),
                                                    CommonUtil.formartSize(is.available()), buff.getWidth(),
                                                    buff.getHeight());
                        } catch (Exception ioe) {
                            Map<String, Integer> map =
                                CommonUtil.getPicWidthAndHeight(Constants.MATERIAL_UPLOAD_PATH + fileItem.getName());
                            am.updateMaterialFigure(fileItem.getName().substring(fileItem.getName().lastIndexOf(".") +
                                                                                 1),
                                                    CommonUtil.formartSize(is.available()), map.get("width"),
                                                    map.get("height"));
                        }
                    } catch (IOException ioe) {
                        System.out.println("IOException:" + ioe.getMessage());
                    }
                    try {
                        Map<String, String> exifMap = CommonUtil.readExifMetadata(file);
                        am.processExifInfo4MaterialFigure(docId, exifMap, mode);
                    } catch (Exception e) {
                        System.out.println("读取图片EXIF信息时出错：" + e.getMessage());
                        e.printStackTrace();
                    }
                    am.getDBTransaction().commit();
                    LoadBatchUtil util = new LoadBatchUtil();
                    Map<String, String> map =
                        UCMUtil.checkinMaterialFileToUCM(fileItem, docId, CommonUtil.getLibCode(),
                                                         CommonUtil.getCompCode(),
                                                         Constants.MATERIAL_UPLOAD_PATH + fileItem.getName(),
                                                         CommonUtil.getUsername());
                    util.addData(map);
                    String transData = util.getSplitTransData(Constants.SYNC_PER_BATCH_COUNT);
                    UCMUtil.invokeWebService(new Object[] { transData + "###" + docId + "###" +
                                                            CommonUtil.getUserId() }, "doBatchLoadWithParam",
                                             Constants.UCM_SYNC_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //单个修改
                FileItem fileItem = (FileItem)this.getSession().getAttribute("fileItem");
                if (fileItem != null) {
                    this.getSession().setAttribute("fileItem", null);
                    File file = new File(Constants.MATERIAL_UPLOAD_PATH + fileItem.getName());
                    try {
                        InputStream is = new FileInputStream(file);
                        try {
                            ImageInfo imageInfo = Sanselan.getImageInfo(is, fileItem.getName());
                            String fileSize = CommonUtil.formartSize(fileItem.getSize());
                            am.updateMaterialFigureInfo(imageInfo, fileSize);
                        } catch (ImageReadException ire) {
                            System.out.println("ImageReadException:" + ire.getMessage());
                        } catch (IOException ioe) {
                            System.out.println("IOException:" + ioe.getMessage());
                        }
                        try {
                            Map<String, String> exifMap = CommonUtil.readExifMetadata(file);
                            am.processExifInfo4MaterialFigure(docId, exifMap, mode);
                        } catch (Exception e) {
                            System.out.println("读取图片EXIF信息时出错：" + e.getMessage());
                            e.printStackTrace();
                        }
                        am.getDBTransaction().commit();
                        //删除原来的图片
                        //                        List<Row> ucmFileList = CommonUtil.getWorkAM().getUCMFileByDocId(docId);
                        //                        for (Row row : ucmFileList) {
                        //                            DocService.getInstance().deleteDocByDID(row.getAttribute("UcmDid").toString());
                        //                        }
                        //上传新图片
                        LoadBatchUtil util = new LoadBatchUtil();
                        Map<String, String> map =
                            UCMUtil.checkinMaterialFileToUCM(fileItem, docId, CommonUtil.getLibCode(),
                                                             CommonUtil.getCompCode(),
                                                             Constants.MATERIAL_UPLOAD_PATH + fileItem.getName(),
                                                             CommonUtil.getUsername());
                        util.addData(map);
                        String transData = util.getSplitTransData(Constants.SYNC_PER_BATCH_COUNT);
                        UCMUtil.invokeWebService(new Object[] { transData + "###" + docId + "###" +
                                                                CommonUtil.getUserId() }, "doBatchLoadWithParam",
                                                 Constants.UCM_SYNC_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    am.getDBTransaction().commit();
                }
            }
            this.appendScript("saveExtendAttrs()");
            return null;
        } else {
            am.batchEditMaterialFigure((String)this.resolveExpression("#{pageFlowScope.docId}"));
            am.getDBTransaction().commit();
            return "toReturn";
        }
    }


    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getMaterialFigureAM().getDBTransaction().rollback();
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setFigureNameInputText(RichInputText figureNameInputText) {
        this.figureNameInputText = figureNameInputText;
    }

    public RichInputText getFigureNameInputText() {
        return figureNameInputText;
    }
}
