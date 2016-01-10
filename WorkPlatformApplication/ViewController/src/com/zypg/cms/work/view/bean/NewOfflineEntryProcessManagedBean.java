package com.zypg.cms.work.view.bean;


import com.zypg.cms.foldermanager.xmlutils.XmlUtil;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.ws.client.CustomWSClient;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.jbo.domain.Number;

import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.commons.fileupload.FileItem;

import org.w3c.dom.Document;


public class NewOfflineEntryProcessManagedBean extends CustomManagedBean {
    public NewOfflineEntryProcessManagedBean() {
    }

    public void init4NewOfflineEntryProcess() {
        this.getSession().setAttribute("fileItemList", null);
        this.setExpressionValue("#{sessionScope.entryCompName}", CommonUtil.getCompName());
    }

    public String saveAction() {
        List<Map<String, Object>> fileItemList =
            (List<Map<String, Object>>)this.getSession().getAttribute("fileItemList");
        if (fileItemList == null || fileItemList.size() == 0) {
            this.addFormattedMessage(null, "请先上传文件", FacesMessage.SEVERITY_INFO);
            return null;
        } else {
            this.getSession().setAttribute("fileItemList", null);
        }
        for (int i = 0; i < fileItemList.size(); i++) {
            Map<String, Object> fileInfoMap = fileItemList.get(i);
            FileItem item = (FileItem)fileInfoMap.get("fileItem");
            String uuidFileName = (String)fileInfoMap.get("uuidFileName");
            Number taskId =
                CommonUtil.getOfflineAM().createTaskMonitor(item.getName().substring(0, item.getName().lastIndexOf(".")),
                                                            CommonUtil.getCompCode());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fptServerName = CommonUtil.getIPAddress(Constants.FTP_SERVER);
            if ("127.0.0.1".equals(fptServerName)) {
                fptServerName = Constants.FTP_SERVER;
            }
            String paramStr =
                "/" + CommonUtil.getCompName() + "/条目库/" + sdf.format(new Date()) + "/" + uuidFileName + "," + taskId +
                "," + fptServerName + "," + Constants.FTP_PORT + "," + Constants.FTP_USERNAME + "," +
                Constants.FTP_PASSWORD + "," + CommonUtil.getCompCode() + "," + CommonUtil.getUserId();
            System.out.println("paramStr------>" + paramStr);
            CommonUtil.getOfflineAM().processTaskMonitorLog(taskId.toString(), "条目WORD入库任务开始...", null, "INSERT",
                                                            CommonUtil.getUserId().toString(), 10);
            CommonUtil.getOfflineAM().getDBTransaction().commit();
            CustomWSClient.asynInvokeWS(Constants.OFFLINE_ENTRY_PROCESS_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE,
                                        new Object[] { paramStr }, "processOfflineEntry", null, new AxisCallback() {
                    @Override
                    public void onMessage(MessageContext mc) {
                        Document doc = XmlUtil.parseXml(mc.getEnvelope().toString());
                        String wsresult = XmlUtil.getText(doc, "wsresult");
                        System.out.println("离线条目入库处理结果:" + wsresult);
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
        }
        return "toReturn";
    }

    public void cancelActionListener(ActionEvent actionEvent) {

    }
}
