package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class NewFigureGroupManagedBean extends CustomManagedBean {
    private Map<String, Object> figureGroupMap = new HashMap<String, Object>();
    private List<List<Map<String, Object>>> singleFigureList = new ArrayList<List<Map<String, Object>>>();
    List<Map<String, Object>> groupFigureList = new ArrayList<Map<String, Object>>();

    public NewFigureGroupManagedBean() {
    }

    public void initFigureGroup() {
        String docIds = (String)this.resolveExpression("#{pageFlowScope.docId}");
        figureGroupMap =
                CommonUtil.getFigureGroupAM().initFigureGroup(docIds, CommonUtil.getLibCode(), CommonUtil.getCompCode());
        Number defaultIndexFigureDocId = (Number)figureGroupMap.get("defaultIndexFigureDocId");
        List<Number> inGroupList = (List<Number>)figureGroupMap.get("inGroupList");
        List<Number> notInGroupList = (List<Number>)figureGroupMap.get("notInGroupList");
        this.setExpressionValue("#{pageFlowScope.defaultIndexFigureDocId}", defaultIndexFigureDocId);
        this.setExpressionValue("#{pageFlowScope.inGroupList}", inGroupList);
        this.setExpressionValue("#{pageFlowScope.notInGroupList}", notInGroupList);
    }

    public void generateGroupFigureList() {
        List<Map<String, Object>> groupList = new ArrayList<Map<String, Object>>();
        List<Number> inGroupList = (List<Number>)figureGroupMap.get("inGroupList");
        if (inGroupList == null) {
            inGroupList = (List<Number>)this.resolveExpression("#{pageFlowScope.inGroupList}");
        }
        CmsDocTVOImpl docVO = CommonUtil.getFigureGroupAM().getCmsDocTVO4Query();
        for (Number groupId : inGroupList) {
            int i = 3;
            List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
            List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
            Row[] docGroupRelRows = CommonUtil.getFigureGroupAM().queryDocGroupRel(groupId);
            for (Row r : docGroupRelRows) {
                Number docId = (Number)r.getAttribute("DocId");
                Row[] docRows = docVO.getFilteredRows("DocId", docId);
                if (docRows.length > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("docId", docId);
                    map.put("thumbnailUrl", docRows[0].getAttribute("ThumbnailUrl"));
                    l.add(map);
                    if (i-- == 1) {
                        i = 3;
                        list.add(l);
                        l = new ArrayList<Map<String, Object>>();
                    }
                }
            }
            //补空
            for (int j = 1; j <= i % 3; j++) {
                l.add(new HashMap<String, Object>());
            }
            list.add(l);
            Map<String, Object> groupMap = new HashMap<String, Object>();
            groupMap.put("groupName", docGroupRelRows.length > 0 ? docGroupRelRows[0].getAttribute("GroupName") : "");
            groupMap.put("figureList", list);
            groupList.add(groupMap);
        }
        this.setGroupFigureList(groupList);

    }

    public void generateSingleFigureList() {
        int i = 4;
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> notInGroupList = (List<Number>)figureGroupMap.get("notInGroupList");
        if (notInGroupList == null) {
            notInGroupList = (List<Number>)this.resolveExpression("#{pageFlowScope.notInGroupList}");
        }
        CmsDocTVOImpl docVO = CommonUtil.getFigureGroupAM().getCmsDocTVO4Query();
        for (Number docId : notInGroupList) {
            Row[] docRows = docVO.getFilteredRows("DocId", docId);
            if (docRows.length > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("thumbnailUrl", docRows[0].getAttribute("ThumbnailUrl"));
                l.add(map);
                if (i-- == 1) {
                    i = 4;
                    list.add(l);
                    l = new ArrayList<Map<String, Object>>();
                }
            }
        }
        //补空
        for (int j = 1; j <= i % 4; j++) {
            l.add(new HashMap<String, Object>());
        }
        list.add(l);
        this.setSingleFigureList(list);
    }

    public void setGroupRecordValueChangeListener(ValueChangeEvent valueChangeEvent) {
        String groupName = (String)valueChangeEvent.getComponent().getAttributes().get("groupName");
        if ((Boolean)valueChangeEvent.getNewValue()) {
            DCIteratorBinding dcib = this.findIterator("CmsFigureGroupTVOIterator");
            Row row = dcib.getCurrentRow();
            if (row != null) {
                row.setAttribute("GroupName", groupName);
            }
        }
    }

    public void setIndexFigureValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Number docId = (Number)valueChangeEvent.getComponent().getAttributes().get("docId");
        if ((Boolean)valueChangeEvent.getNewValue()) {
            CommonUtil.getFigureGroupAM().setFigureToIndex(docId);
        }
    }

    public void saveActionListener(ActionEvent actionEvent) {
        CommonUtil.getFigureGroupAM().getDBTransaction().commit();
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getFigureGroupAM().getDBTransaction().rollback();
    }

    public void setFigureGroupMap(Map<String, Object> figureGroupMap) {
        this.figureGroupMap = figureGroupMap;
    }

    public Map<String, Object> getFigureGroupMap() {
        return figureGroupMap;
    }

    public void setSingleFigureList(List<List<Map<String, Object>>> singleFigureList) {
        this.singleFigureList = singleFigureList;
    }

    public List<List<Map<String, Object>>> getSingleFigureList() {
        if (singleFigureList.size() == 0) {
            generateSingleFigureList();
        }
        return singleFigureList;
    }

    public void setGroupFigureList(List<Map<String, Object>> groupFigureList) {
        this.groupFigureList = groupFigureList;
    }

    public List<Map<String, Object>> getGroupFigureList() {
        if (groupFigureList.size() == 0) {
            this.generateGroupFigureList();
        }
        return groupFigureList;
    }
}
