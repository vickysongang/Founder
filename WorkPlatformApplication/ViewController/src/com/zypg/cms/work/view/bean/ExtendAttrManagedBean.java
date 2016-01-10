package com.zypg.cms.work.view.bean;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.honythink.applicationframework.hadf.util.DateUtil;

import com.zypg.cms.work.model.am.ExtendAttrAMImpl;
import com.zypg.cms.work.model.viewobject.extendattr.CmsExtendAttrVOImpl;
import com.zypg.cms.work.model.viewobject.extendattr.CmsExtendAttrVORowImpl;
import com.zypg.cms.work.model.viewobject.extendattr.CmsExtendValueListRowImpl;
import com.zypg.cms.work.view.model.ExtendAttrModel;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.domain.Number;
import oracle.jbo.Row;


public class ExtendAttrManagedBean extends CustomManagedBean {
    ExtendAttrAMImpl am = CommonUtil.getExtendAttrAM();
    private RichPanelFormLayout extendAttrFormLayout;
    private List<Map<String, String>> extendAttrList = new ArrayList<Map<String, String>>();
    private Map<String, Object> defaultExtendAttrValueMap = new HashMap<String, Object>();

    public ExtendAttrManagedBean() {

    }

    public void initExtendAttr() {
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String libCode = (String)this.resolveExpression("#{pageFlowScope.libCode}");
        System.out.println("compCode:" + compCode + " libCode:" + libCode);
        CommonUtil.getExtendAttrAM().initExtendAttr(compCode, libCode);
    }


    public Map<String, List<SelectItem>> getChoiceValueList() {
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String libCode = (String)this.resolveExpression("#{pageFlowScope.libCode}");
        Map<String, List<SelectItem>> map = new HashMap<String, List<SelectItem>>();
        Row[] choiceRows = am.getExtendAttr4Choice(compCode, libCode);
        for (Row row : choiceRows) {
            CmsExtendAttrVORowImpl extendAttrRow = (CmsExtendAttrVORowImpl)row;
            String key = extendAttrRow.getAttrCode();
            if (!map.containsKey(key)) {
                List<SelectItem> list = new ArrayList<SelectItem>();
                Row[] valueRows = am.getExtendValueList(extendAttrRow.getExtendAttrId());
                for (Row r : valueRows) {
                    CmsExtendValueListRowImpl valueRow = (CmsExtendValueListRowImpl)r;
                    SelectItem item = new SelectItem(valueRow.getValueCode(), valueRow.getValueMeaning());
                    list.add(item);
                }
                map.put(key, list);
            }
        }
        return map;
    }

    public String getJsonStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<UIComponent> children = this.getExtendAttrFormLayout().getChildren();
        if (children.size() > 0) {
            Map<String, ExtendAttrModel> map = new HashMap<String, ExtendAttrModel>();
            for (UIComponent comp : children) {
                boolean b = comp.isRendered();
                if (b) {
                    if ("oracle.adf.rich.Text".equals(comp.getRendererType())) {
                        RichInputText text = (RichInputText)comp;
                        String changedDesc = text.getChangedDesc();
                        System.out.println(text.getValue());
                        ExtendAttrModel model = new ExtendAttrModel();
                        model.setAttrType(changedDesc.split(",")[1]);
                        model.setAttrValue((text.getValue() == "" || text.getValue() == null) ? null :
                                           text.getValue().toString());
                        map.put(changedDesc.split(",")[0], model);
                    } else if ("oracle.adf.rich.Date".equals(comp.getRendererType())) {
                        RichInputDate date = (RichInputDate)comp;
                        String changedDesc = date.getChangedDesc();
                        ExtendAttrModel model = new ExtendAttrModel();
                        model.setAttrType(changedDesc.split(",")[1]);
                        if ((date.getValue() == "" || date.getValue() == null)) {
                            model.setAttrValue(null);
                        } else {
                            Object o = date.getValue();
                            if (o instanceof Date) {
                                Date d = (Date)date.getValue();
                                model.setAttrValue(sdf.format(d));
                            } else {
                                model.setAttrValue(o.toString());
                            }
                        }
                        map.put(changedDesc.split(",")[0], model);
                    } else if ("oracle.adf.rich.Choice".equals(comp.getRendererType())) {
                        RichSelectOneChoice choice = (RichSelectOneChoice)comp;
                        String changedDesc = choice.getChangedDesc();
                        ExtendAttrModel model = new ExtendAttrModel();
                        model.setAttrType(changedDesc.split(",")[1]);
                        model.setAttrValue((choice.getValue() == "" || choice.getValue() == null) ? null :
                                           choice.getValue().toString());
                        map.put(changedDesc.split(",")[0], model);
                    }
                }
            }
            String jsonStr = JSON.toJSONString(map);
            System.out.println(jsonStr);
            return jsonStr;
        }
        return null;
    }

    public void saveActionListener(ActionEvent actionEvent) {
        Number docId = getDocId();
        String jsonStr = getJsonStr();
        System.out.println("保存扩展字段jsonStr：" + jsonStr);
        if (jsonStr != null) {
            String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
            System.out.println("operType：" + operType);
            am.saveExtendAttrs(operType, docId, jsonStr);
        }
        this.appendScript("closePopup()");
    }

    public void saveServerListener(ClientEvent clientEvent) {
        Number docId = getDocId();
        String jsonStr = getJsonStr();
        if (jsonStr != null) {
            String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
            am.saveExtendAttrs(operType, docId, jsonStr);
        }
        //        this.appendScript("closePopup()");
    }

    public List<Map<String, String>> getExtendAttrList() {
        if (extendAttrList.size() == 0) {
            CmsExtendAttrVOImpl vo = am.getCmsExtendAttrVO();
            for (Row row : vo.getAllRowsInRange()) {
                Map<String, String> map = new HashMap<String, String>();
                CmsExtendAttrVORowImpl attrRow = (CmsExtendAttrVORowImpl)row;
                map.put("AttrCode", attrRow.getAttrCode());
                map.put("AttrMeaning", attrRow.getAttrMeaning());
                map.put("AttrType", attrRow.getAttrType());
                extendAttrList.add(map);
            }
        }
        return extendAttrList;
    }

    public Number getDocId() {
        Number docId = null;
        Object o = this.resolveExpression("#{pageFlowScope.docId}");
        if (o instanceof Number) {
            docId = (Number)o;
        } else {
            try {
                docId = new Number(o);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return docId;
    }

    public Integer getExtendAttrListSize() {
        return extendAttrList.size();
    }

    public void setDefaultExtendAttrValueMap(Map<String, Object> defaultExtendAttrValueMap) {
        this.defaultExtendAttrValueMap = defaultExtendAttrValueMap;
    }

    public Map<String, Object> getDefaultExtendAttrValueMap() {
        String operType = (String)this.resolveExpression("#{pageFlowScope.operType}");
        if ("EDIT".equals(operType) && defaultExtendAttrValueMap.size() == 0) {
            Number docId = getDocId();
            String jsonStr = am.getExtendAttrs(docId);
            if (jsonStr != null && jsonStr.length() > 0) {
                Map map = JSON.parseObject(jsonStr, Map.class);
                for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
                    String key = (String)it.next();
                    JSONObject o = (JSONObject)map.get(key);
                    if ("NUMBER".equals(o.get("attrType"))) {
                        try {
                            defaultExtendAttrValueMap.put(key,
                                                          (o.get("attrValue") == null || o.get("attrValue") == "") ?
                                                          null : new Number(o.get("attrValue")));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        defaultExtendAttrValueMap.put(key, o.get("attrValue"));
                    }
                }
            }
        }
        return defaultExtendAttrValueMap;
    }

    public void setExtendAttrFormLayout(RichPanelFormLayout extendAttrFormLayout) {
        this.extendAttrFormLayout = extendAttrFormLayout;
    }

    public RichPanelFormLayout getExtendAttrFormLayout() {
        return extendAttrFormLayout;
    }

    public void setExtendAttrList(List<Map<String, String>> extendAttrList) {
        this.extendAttrList = extendAttrList;
    }

}
