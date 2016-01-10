package com.zypg.cms.work.view.bean;


import com.zypg.cms.excel.utils.CustomExcelUtil;
import com.zypg.cms.foldermanager.model.FolderConstants;
import com.zypg.cms.work.model.am.CopyrightAMImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.validator.BookImportValidator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class CopyrightAuthorImportManagedBean extends CustomManagedBean {
    public CopyrightAuthorImportManagedBean() {
    }

    public void authorTempleteDownLoadListener(FacesContext facesContext, OutputStream outputStream) {
        //        String path = "D:";
        String path = "";
        String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Windows")) {
            FacesContext context = FacesContext.getCurrentInstance();
            path = context.getExternalContext().getRealPath("/") + "WEB-INF";
        } else {
            path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            if (path.indexOf("WEB-INF") > 0) {
                path = path.substring(0, path.indexOf("WEB-INF") + 7);
            }
        }
        String abslutePath = path + "/config/author_copyright_template.xls";
        FileInputStream file;
        try {
            file = new FileInputStream(abslutePath);
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

    public void uploadFileServerListener(ClientEvent clientEvent) {
        // 1、获取文件
        String importFlag = "S";
        String inputFileId = (String)clientEvent.getParameters().get("inputFileId");
        String enctype = (String)clientEvent.getParameters().get("enctype");
        String formId = (String)clientEvent.getParameters().get("formId");
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String currentPublisherCode =
            (String)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("currentPublisherCode");
        oracle.jbo.domain.Number currentCategoryId =
            (oracle.jbo.domain.Number)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("currentCategoryId");
        try {
            RichInputFile inputFile = (RichInputFile)clientEvent.getComponent().findComponent(inputFileId);
            RichSelectOneRadio radio = (RichSelectOneRadio)clientEvent.getComponent().findComponent("sor1");
            String coverType = (String)radio.getValue();
            //2、处理导入及校验
            UploadedFile uploadFile = (UploadedFile)inputFile.getValue();
            InputStream in = uploadFile.getInputStream();
            List<Map<String, Object>> excelDatas = CustomExcelUtil.getExcelData(in, 0);
            //3、校验
            BookImportValidator validator = new BookImportValidator(excelDatas, coverType, CommonUtil.getCompCode());
            List<String> errMsgList = validator.validateCopyright();
            if (errMsgList.size() > 0) {
                importFlag = "E";
                for (String msg : errMsgList) {
                    this.addFormattedMessage(null, msg, FacesMessage.SEVERITY_ERROR);
                }
                inputFile.resetValue();
            } else {
                //4、入库
                List<Map<String, Object>> validBooks = validator.coverHandle(); //去重处理（根据覆盖或者忽略处理）
                //数据库处理
                System.out.println("开始导入了~~~");
                String result =
                    CommonUtil.getCopyrightAM().importData(excelDatas, coverType, compCode, currentCategoryId);
                System.out.println("result=" + result);
                if (!"S".equalsIgnoreCase(result)) {
                    this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
                    importFlag = "E";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            importFlag = "E";
        } finally {
            //3、还原form的提交格式
            this.appendScript("recoverFormDataFormat('" + formId + "','" + enctype + "')");
        }
        this.setExpressionValue("#{pageFlowScope.importFlag}", importFlag);
        if ("S".equals(importFlag)) {
            //关闭窗口 并提示导入成功！
            this.navToOutCome("toReturn");
        }
    }
}
