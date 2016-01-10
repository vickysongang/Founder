package com.zypg.cms.work.view.bean;


import com.zypg.cms.excel.utils.CustomExcelUtil;
import com.zypg.cms.foldermanager.model.FolderConstants;
import com.zypg.cms.work.model.am.CopyrightAMImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class CopyrightItemImportManagedBean extends CustomManagedBean {
    public CopyrightItemImportManagedBean() {
    }

    public void itemTempleteDownLoadListener(FacesContext facesContext, OutputStream outputStream) {
        String path = "D:\\workspace\\FounderUCM\\02 系统实现\\workspace\\WorkPlatformApplication\\ViewController\\public_html\\WEB-INF";
        //        String os = System.getProperty("os.name");
        //        if (os != null && os.startsWith("Windows")) {
        //            FacesContext context = FacesContext.getCurrentInstance();
        //            path = context.getExternalContext().getRealPath("/") + "WEB-INF";
        //        } else {
        //            path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        //            if (path.indexOf("WEB-INF") > 0) {
        //                path = path.substring(0, path.indexOf("WEB-INF") + 7);
        //            }
        //        }
        FolderConstants.xmlPath = path + "/config/author_copyright_template.xls";
        FileInputStream file;
        try {
            file = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int len = file.read(buffer);
            while (len != -1) {
                System.out.println("len--->"+len);
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
        System.out.println("@@@@");
        // 1、获取文件
        String importFlag = "S";
        String inputFileId = (String)clientEvent.getParameters().get("inputFileId");
        String enctype = (String)clientEvent.getParameters().get("enctype");
        String formId = (String)clientEvent.getParameters().get("formId");
        oracle.jbo.domain.Number itemId  = new  oracle.jbo.domain.Number();
        try {
            RichInputFile inputFile = (RichInputFile)clientEvent.getComponent().findComponent(inputFileId);
            UploadedFile uploadFile = (UploadedFile)inputFile.getValue();
            InputStream in = uploadFile.getInputStream();
            List<Map<String, Object>> excelDatas = CustomExcelUtil.getExcelData(in, 0);
            List<String> errMsgList = new ArrayList<String>();
            if (errMsgList.size() > 0) {
                importFlag = "E";
                for (String msg : errMsgList) {
                    this.addFormattedMessage(null, msg, FacesMessage.SEVERITY_ERROR);
                }
                inputFile.resetValue();
            } else {
                System.out.println("开始导入了~~~");
                CopyrightAMImpl copyrightAM = CommonUtil.getCopyrightAM();
                String result = copyrightAM.importItemData(excelDatas);
                System.out.println("result="+result);
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
    }
}
