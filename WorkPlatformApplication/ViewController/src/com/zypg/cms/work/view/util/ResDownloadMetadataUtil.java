package com.zypg.cms.work.view.util;


import com.zypg.cms.excel.utils.ExportExcelUtil;
import com.zypg.cms.ucm.xml.XmlModel;
import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResAttachExpVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResAttachExpVORowImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyLVVOImpl;
import com.zypg.cms.work.model.viewobject.resexp.query.CmsResExpApplyLVVORowImpl;
import com.zypg.cms.work.view.model.TempShelfDownloadExcelModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;


public class ResDownloadMetadataUtil {
    private ResExpAMImpl am;
    private Number applyId;
    private String applyName;

    public ResDownloadMetadataUtil(ResExpAMImpl am, Number applyId, String applyName) {
        this.am = am;
        this.applyId = applyId;
        this.applyName = applyName;
    }

    public XmlModel generateHeaderAndSpace() {
        XmlModel root = new XmlModel("download");
        return root;
    }

    public XmlModel generatorConnector() {
        XmlModel connecter = new XmlModel("connecter");
        connecter.addAttribute("host", Constants.SERVER_ADDRESS);
        connecter.addAttribute("port", Constants.UCM_PORT + "");
        connecter.addAttribute("protocal", Constants.UCM_PROTOCOL);
        connecter.addAttribute("username", Constants.UCM_USERNAME);
        connecter.addAttribute("password", Constants.UCM_PASSWORD);
        connecter.addAttribute("socket_timeout", Constants.SOCKET_TIMEOUT + "");
        connecter.addAttribute("connection_size", Constants.CONNECTION_SIZE + "");
        return connecter;
    }

    public XmlModel generatorTemplate() throws Exception {
        XmlModel template = new XmlModel("template");
        template.addAttribute("name", applyName);

        XmlModel docs = new XmlModel("docs");
        template.addChildren(docs);
        //1、查询行表
        CmsResExpApplyLVVOImpl applyLineVo = am.getCmsResExpApplyLVVO();
        applyLineVo.setbvApplyId(applyId);
        applyLineVo.executeQuery();
        applyLineVo.setRangeSize(-1);
        if (applyLineVo.getEstimatedRowCount() > 0) {
            List<String> headers = new ArrayList<String>();
            //头信息
            String headerSql = "SELECT DISTINCT reah.apply_id,\n" +
                "       reah.apply_name,\n" +
                "       rt.template_id,\n" +
                "       rt.template_name,\n" +
                "       er.res_code,\n" +
                "       er.res_name,\n" +
                "       er.res_type_code,\n" +
                "       er.res_type_name,\n" +
                "       substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1) res_type_simple_code,\n" +
                "       er.attribute1 column_name,\n" +
                "       er.attribute2 table_name,\n" +
                "       decode(substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1),'METADATA',1,'JB_INFO',2,3) order_num\n" +
                "  FROM cms_res_exp_apply_h_t  reah,\n" +
                "       cms_res_template_t     rt,\n" +
                "       cms_res_template_rel_t rtr,\n" +
                "       cms_exp_res_v          er\n" +
                " WHERE  reah.template_id = rt.template_id\n" +
                "   AND rt.template_id = rtr.template_id\n" +
                "   AND rtr.res_code = er.res_code\n" +
                "   AND rtr.res_type_code = er.res_type_code\n" +
                "   AND substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1) <>\n" +
                "       'ATTACH_TYPE'\n" +
                "   and substr(er.res_type_code, 1, instr(er.res_type_code, '#') - 1) <>\n" +
                "       'REL_RES'\n" +
                "   and reah.apply_id = " + this.applyId.toString() + "   order by order_num";
            Object existVo = am.findViewObject("dynamocVO4DocHeaders");
            if (existVo != null) {
                am.deleteChild(existVo);
            }
            ViewObjectImpl dynamicVo =
                (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocHeaders", headerSql);
            dynamicVo.executeQuery();
            dynamicVo.setRangeSize(-1);

            if (dynamicVo.getEstimatedRowCount() <= 0) {
                throw new Exception("生成excel头信息失败！");
            }

            for (Row hrw : dynamicVo.getAllRowsInRange()) {
                String hname = (String)hrw.getAttribute("RES_NAME");
                headers.add(hname);
            }

            List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
            //生成index.xml
            XmlModel founderModel = new XmlModel("Founder");
            String libCode = "";
            for (Row rw : applyLineVo.getAllRowsInRange()) {
                Map<String, String> linedata = new HashMap<String, String>();
                //生成index.xml中的资源
                XmlModel resourceModel = new XmlModel("resource");
                CmsResExpApplyLVVORowImpl lineRw = (CmsResExpApplyLVVORowImpl)rw;
                libCode = lineRw.getLibCode();
                resourceModel.addAttribute("id", lineRw.getLineId().toString());
                resourceModel.addAttribute("topic", lineRw.getResName());
                resourceModel.addAttribute("path", "docs/" + lineRw.getResName() + ".xml");
                founderModel.addChildren(resourceModel);

                //生成"docs/" + lineRw.getResName() + ".xml"文件
                //1、元数据信息
                TempResMetadataUtil metaUtil = new TempResMetadataUtil(am, applyId, lineRw.getDocId(), linedata);
                XmlModel rootModel = metaUtil.getRootModel();
                //2、附件信息
                CmsResAttachExpVOImpl attchVo = am.getCmsResAttachExpVO();
                attchVo.setbvApplyId(applyId);
                attchVo.setbvDocId(lineRw.getDocId());
                attchVo.executeQuery();
                attchVo.setRangeSize(-1);
                XmlModel docModel = new XmlModel("doc");
                docModel.addAttribute("name", lineRw.getResName());
                docs.addChildren(docModel);

                if (attchVo.getEstimatedRowCount() > 0) {
                    for (Row arw : attchVo.getAllRowsInRange()) {
                        CmsResAttachExpVORowImpl attchRow = (CmsResAttachExpVORowImpl)arw;
                        XmlModel attachResource = new XmlModel("resource");
                        XmlModel type = new XmlModel("metadata");
                        type.addAttribute("name", "type");
                        type.setText("附件信息");
                        XmlModel contentType = new XmlModel("metadata");
                        contentType.addAttribute("name", "contentType");
                        contentType.setText(attchRow.getResName());
                        XmlModel topic = new XmlModel("metadata");
                        topic.addAttribute("name", "topic");
                        topic.setText(attchRow.getFileName());
                        XmlModel path = new XmlModel("metadata");
                        path.addAttribute("name", "path");
                        path.setText(attchRow.getFilePath());
                        attachResource.addChildren(type);
                        attachResource.addChildren(contentType);
                        attachResource.addChildren(topic);
                        attachResource.addChildren(path);
                        rootModel.addChildren(attachResource);

                        //组合download 模型
                        XmlModel fileModel = new XmlModel("file");
                        fileModel.addAttribute("name", attchRow.getFileName());
                        fileModel.addAttribute("path", attchRow.getFilePath());
                        fileModel.addAttribute("did", attchRow.getUcmDid().toString());
                        docModel.addChildren(fileModel);
                    }
                }

                String docMetaXmlString = XmlModel.xmlHeader + "\n" +
                    rootModel.toXmlString();
                //上传至UCM
                ByteArrayInputStream inputStream = new ByteArrayInputStream(docMetaXmlString.getBytes("UTF-8"));
                String did = TempResMetadataUCMUtil.checkinToUcm(lineRw.getResName() + ".xml", inputStream);
                XmlModel docMetaFile = new XmlModel("meta");
                docMetaFile.addAttribute("name", lineRw.getResName() + ".xml");
                docMetaFile.addAttribute("path", this.applyName + "/docs");
                docMetaFile.addAttribute("did", did);
                docs.addChildren(docMetaFile);

                datas.add(linedata);
            }

            //生成index.xls
            TempShelfDownloadExcelModel shelfExcelData = new TempShelfDownloadExcelModel(datas, headers);
            Map<Integer, Integer> wm = new HashMap<Integer, Integer>();
            ExportExcelUtil excelUtil = new ExportExcelUtil("index", wm, true);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            excelUtil.exportExcel(shelfExcelData, os);
            byte[] array = os.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(array);

            String xlsDid = TempResMetadataUCMUtil.checkinToUcm("index.xls", in);
            XmlModel templateMetaXlsFile = new XmlModel("meta");
            templateMetaXlsFile.addAttribute("name", "index.xls");
            templateMetaXlsFile.addAttribute("path", this.applyName);
            templateMetaXlsFile.addAttribute("did", xlsDid);
            template.addChildren(templateMetaXlsFile);
            founderModel.addAttribute("DocLibCode", libCode);
            founderModel.addAttribute("BaseInfoType", "XML");
            //index.xml 上传至UCM
            String indexXmlStr = XmlModel.xmlHeader + "\n" +
                founderModel.toXmlString();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(indexXmlStr.getBytes("UTF-8"));
            String xmlDid = TempResMetadataUCMUtil.checkinToUcm("index.xml", inputStream);
            XmlModel templateMetaFile = new XmlModel("meta");
            templateMetaFile.addAttribute("name", "index.xml");
            templateMetaFile.addAttribute("path", this.applyName);
            templateMetaFile.addAttribute("did", xmlDid);
            template.addChildren(templateMetaFile);
        } else {
            return null;
        }
        return template;
    }

    public static void main(String[] args) {
        String amDef = "com.zypg.cms.work.model.WorkAM";
        String config = "WorkAMLocal";
        ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
        ResExpAMImpl resAm = (ResExpAMImpl)am.findApplicationModule("ResExpAM");
        ResDownloadMetadataUtil downloadUtil = new ResDownloadMetadataUtil(resAm, new Number(210), "模板1");
        XmlModel root = downloadUtil.generateHeaderAndSpace();
        XmlModel connector = downloadUtil.generatorConnector();
        XmlModel template;
        try {
            template = downloadUtil.generatorTemplate();
            root.addChildren(connector);
            root.addChildren(template);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
