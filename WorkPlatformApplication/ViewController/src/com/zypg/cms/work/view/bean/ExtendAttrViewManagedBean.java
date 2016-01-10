package com.zypg.cms.work.view.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.zypg.cms.work.model.am.ExtendAttrAMImpl;
import com.zypg.cms.work.model.viewobject.extendattr.CmsExtendAttrVOImpl;
import com.zypg.cms.work.model.viewobject.extendattr.CmsExtendAttrVORowImpl;
import com.zypg.cms.work.model.viewobject.extendattr.CmsExtendValueListRowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class ExtendAttrViewManagedBean extends CustomManagedBean {
    ExtendAttrAMImpl am = CommonUtil.getExtendAttrAM();
    private List<Map<String, String>> extendAttrList = new ArrayList<Map<String, String>>();
    private Map<String, Object> defaultExtendAttrValueMap = new HashMap<String, Object>();

    public ExtendAttrViewManagedBean() {
    }

    public void initExtendAttrView() {
        Number docId = getDocId();
        Map<String, String> docMap = am.getDocInfo(docId);
        String compCode = docMap.get("compCode");
        String libCode = docMap.get("libCode");
        CommonUtil.getExtendAttrAM().initExtendAttr(compCode, libCode);
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

    public Integer getExtendAttrListSize() {
        return extendAttrList.size();
    }

    public void setDefaultExtendAttrValueMap(Map<String, Object> defaultExtendAttrValueMap) {
        this.defaultExtendAttrValueMap = defaultExtendAttrValueMap;
    }

    public Map<String, Object> getDefaultExtendAttrValueMap() {
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
                                                      (o.get("attrValue") == null || o.get("attrValue") == "") ? null :
                                                      new Number(o.get("attrValue")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    defaultExtendAttrValueMap.put(key, o.get("attrValue"));
                }
            }
        }
        return defaultExtendAttrValueMap;
    }

    public void setExtendAttrList(List<Map<String, String>> extendAttrList) {
        this.extendAttrList = extendAttrList;
    }
}
