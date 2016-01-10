package com.zypg.cms.work.view.util;

import com.zypg.cms.ucm.xml.XmlModel;
import com.zypg.cms.work.model.am.ResExpAMImpl;

import com.zypg.cms.work.model.viewobject.resexp.query.CmsResMetadataExpVOImpl;

import com.zypg.cms.work.model.viewobject.resexp.query.CmsResMetadataExpVORowImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.ClobDomain;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

public class TempResMetadataUtil {
    private ResExpAMImpl am;
    private Number applyId;
    private Number docId;
    private Map<String, String> datas;

    public TempResMetadataUtil(ResExpAMImpl am, Number applyId, Number docId, Map<String, String> datas) {
        this.am = am;
        this.applyId = applyId;
        this.docId = docId;
        this.datas = datas;
    }

    public XmlModel generateRootAndSpace() {
        XmlModel rootModel = new XmlModel("Founder");
        return rootModel;
    }

    /**
     * 生成基本元数据信息
     * @return
     */
    public XmlModel generateDocAttribute() {
        XmlModel attributeModel = new XmlModel("resource");
        XmlModel type = new XmlModel("metadata");
        type.addAttribute("name", "type");
        type.setText("基本信息");
        attributeModel.addChildren(type);

        CmsResMetadataExpVOImpl metaDataVO = am.getCmsResMetadataExpVO();
        metaDataVO.setbvApplyId(applyId);
        metaDataVO.setbvMetaType("METADATA");
        metaDataVO.setbvDocId(docId);
        metaDataVO.executeQuery();
        metaDataVO.setRangeSize(-1);
        String dynamicSql = "select";
        String tableName = "";
        List<String[]> attrs = new ArrayList<String[]>();
        for (Row rw : metaDataVO.getAllRowsInRange()) {
            CmsResMetadataExpVORowImpl metaRow = (CmsResMetadataExpVORowImpl)rw;
            if ("select".equals(dynamicSql)) {
                dynamicSql = dynamicSql + " " + metaRow.getColumnName();
                tableName = metaRow.getTableName();
                String[] attArray = new String[2];
                attArray[0] = metaRow.getColumnName();
                attArray[1] = metaRow.getResName();
                attrs.add(attArray);
            } else {
                dynamicSql = dynamicSql + " ," + metaRow.getColumnName();
                String[] attArray = new String[2];
                attArray[0] = metaRow.getColumnName();
                attArray[1] = metaRow.getResName();
                attrs.add(attArray);
            }
        }
        if (!"select".equals(dynamicSql)) {
            dynamicSql = dynamicSql + " from " + tableName + " where doc_id=" + docId.toString();
        }
        Object existVo = am.findViewObject("dynamocVO4DocAttribute");
        if (existVo != null) {
            am.deleteChild(existVo);
        }
        ViewObjectImpl dynamocVo =
            (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocAttribute", dynamicSql);
        dynamocVo.executeQuery();
        dynamocVo.setRangeSize(-1);
        if (dynamocVo.getEstimatedRowCount() > 0) {
            Row drw = dynamocVo.getRowAtRangeIndex(0);
            for (String[] attArray : attrs) {
                XmlModel meta = new XmlModel("metadata");
                meta.addAttribute("name", attArray[0]);
                meta.addAttribute("trans", attArray[1]);
                String value = drw.getAttribute(attArray[0]) == null ? "" : drw.getAttribute(attArray[0]).toString();
                meta.setText(value);
                attributeModel.addChildren(meta);
                datas.put(attArray[1], value);
            }
        }
        return attributeModel;
    }

    /**
     * 生成精编信息
     * @return
     */
    public List<XmlModel> generateJinbianInfo() {
        List<XmlModel> jbinfos = new ArrayList<XmlModel>();
        CmsResMetadataExpVOImpl metaDataVO = am.getCmsResMetadataExpVO();
        metaDataVO.setbvApplyId(applyId);
        metaDataVO.setbvMetaType("JB_INFO");
        metaDataVO.setbvDocId(docId);
        metaDataVO.executeQuery();
        metaDataVO.setRangeSize(-1);
        String dynamicSql = "select";
        String tableName = "";
        List<String[]> attrs = new ArrayList<String[]>();
        for (Row rw : metaDataVO.getAllRowsInRange()) {
            CmsResMetadataExpVORowImpl metaRow = (CmsResMetadataExpVORowImpl)rw;
            if ("select".equals(dynamicSql)) {
                dynamicSql = dynamicSql + " " + metaRow.getColumnName();
                tableName = metaRow.getTableName();
                String[] attArray = new String[2];
                attArray[0] = metaRow.getColumnName();
                attArray[1] = metaRow.getResName();
                attrs.add(attArray);
            } else {
                dynamicSql = dynamicSql + " ," + metaRow.getColumnName();
                String[] attArray = new String[2];
                attArray[0] = metaRow.getColumnName();
                attArray[1] = metaRow.getResName();
                attrs.add(attArray);
            }
        }

        if (!"select".equals(dynamicSql)) {
            dynamicSql = dynamicSql + " from " + tableName + " where doc_id=" + docId.toString();
        } else {
            return jbinfos;
        }

        Object existVo = am.findViewObject("dynamocVO4DocAttribute");
        if (existVo != null) {
            am.deleteChild(existVo);
        }

        ViewObjectImpl dynamocVo =
            (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocAttribute", dynamicSql);
        dynamocVo.executeQuery();
        dynamocVo.setRangeSize(-1);
        if (dynamocVo.getEstimatedRowCount() > 0) {
            Row drw = dynamocVo.getRowAtRangeIndex(0);
            for (String[] attArray : attrs) {
                XmlModel resourcesModel = new XmlModel("resource");
                XmlModel typeMeta = new XmlModel("type");
                typeMeta.setText("精编/宣传信息");

                XmlModel contentTypeMeta = new XmlModel("contentType");
                contentTypeMeta.setText(attArray[1]);

                XmlModel topicMeta = new XmlModel("topic");
                topicMeta.setText(attArray[1]);

                XmlModel authorMeta = new XmlModel("author");
                authorMeta.setText("<![CDATA[ ]]>");

                String value = drw.getAttribute(attArray[0]) == null ? "" : drw.getAttribute(attArray[0]).toString();
                XmlModel contentMeta = new XmlModel("content");
                contentMeta.setText("<![CDATA[" + value + "]]>");
                resourcesModel.addChildren(typeMeta);
                resourcesModel.addChildren(contentTypeMeta);
                resourcesModel.addChildren(topicMeta);
                resourcesModel.addChildren(authorMeta);
                resourcesModel.addChildren(contentMeta);
                jbinfos.add(resourcesModel);
                datas.put(attArray[1], value);
            }
        }
        return jbinfos;
    }


    public List<XmlModel> generateXCInfo() {
        List<XmlModel> xcInfos = new ArrayList<XmlModel>();
        CmsResMetadataExpVOImpl metaDataVO = am.getCmsResMetadataExpVO();
        metaDataVO.setbvApplyId(applyId);
        metaDataVO.setbvMetaType("XC_INFO");
        metaDataVO.setbvDocId(docId);
        metaDataVO.executeQuery();
        metaDataVO.setRangeSize(-1);
        String tableName = "CMS_XC_INFO_T";
        List<String[]> attrs = new ArrayList<String[]>();
        for (Row rw : metaDataVO.getAllRowsInRange()) {
            CmsResMetadataExpVORowImpl metaRow = (CmsResMetadataExpVORowImpl)rw;
            String[] attArray = new String[2];
            attArray[0] = metaRow.getColumnName();
            attArray[1] = metaRow.getResName();
            attrs.add(attArray);
        }

        for (String[] cts : attrs) {
            String xcKey = cts[1];
            String xcValue = "";
            String sql =
                "select TITLE, AUTHOR, CONTENT, TYPE from " + tableName + " where TYPE='" + cts[0] + "' and DOC_ID=" +
                this.docId;
            Object existVo = am.findViewObject("dynamocVO4DocXCInfo");
            if (existVo != null) {
                am.deleteChild(existVo);
            }
            ViewObjectImpl dynamocVo = (ViewObjectImpl)am.createViewObjectFromQueryStmt("dynamocVO4DocXCInfo", sql);
            dynamocVo.executeQuery();
            dynamocVo.setRangeSize(-1);
            if (dynamocVo.getEstimatedRowCount() > 0) {
                for (Row xcrow : dynamocVo.getAllRowsInRange()) {
                    XmlModel resourcesModel = new XmlModel("resource");
                    XmlModel typeMeta = new XmlModel("type");
                    typeMeta.setText("精编/宣传信息");

                    XmlModel contentTypeMeta = new XmlModel("contentType");
                    contentTypeMeta.setText(cts[1]);

                    XmlModel topicMeta = new XmlModel("topic");
                    topicMeta.setText((String)xcrow.getAttribute("TITLE"));

                    XmlModel authorMeta = new XmlModel("author");
                    authorMeta.setText("<![CDATA[" + (String)xcrow.getAttribute("AUTHOR") + "]]>");

                    XmlModel contentMeta = new XmlModel("content");
                    ClobDomain value = (ClobDomain)xcrow.getAttribute("CONTENT");
                    contentMeta.setText("<![CDATA[" + value.toString() + "]]>");
                    resourcesModel.addChildren(typeMeta);
                    resourcesModel.addChildren(contentTypeMeta);
                    resourcesModel.addChildren(topicMeta);
                    resourcesModel.addChildren(authorMeta);
                    resourcesModel.addChildren(contentMeta);
                    xcInfos.add(resourcesModel);

                    if ("".equalsIgnoreCase(xcValue)) {
                        xcValue = value.toString();
                    } else {
                        xcValue = xcValue + "\n" +
                                value.toString();
                    }
                }
                this.datas.put(xcKey, xcValue);
            }
        }
        return xcInfos;
    }


    public XmlModel getRootModel() {
        XmlModel header = this.generateRootAndSpace();
        XmlModel attributs = this.generateDocAttribute();
        header.addChildren(attributs);
        List<XmlModel> jingbians = this.generateJinbianInfo();
        for (XmlModel ml : jingbians) {
            header.addChildren(ml);
        }
        List<XmlModel> xcInfos = this.generateXCInfo();
        for (XmlModel ml1 : xcInfos) {
            header.addChildren(ml1);
        }
        return header;
    }

    public void setDatas(Map<String, String> datas) {
        this.datas = datas;
    }

    public Map<String, String> getDatas() {
        return datas;
    }


    public static void main(String[] args) {
        String amDef = "com.zypg.cms.work.model.WorkAM";
        String config = "WorkAMLocal";
        ApplicationModule am = Configuration.createRootApplicationModule(amDef, config);
        ResExpAMImpl resAm = (ResExpAMImpl)am.findApplicationModule("ResExpAM");
        Map<String, String> datas = new HashMap<String, String>();
        TempResMetadataUtil util = new TempResMetadataUtil(resAm, new Number(161), new Number(1540), datas);
        System.out.println(util.getRootModel());
        //        util.generateDocAttribute();
        Iterator<String> keyi = datas.keySet().iterator();
        while (keyi.hasNext()) {
            String key = keyi.next();
            String value = datas.get(key);
            System.out.println(key + "-----" + value);
        }
        //        System.out.println(array.size());
    }
}
