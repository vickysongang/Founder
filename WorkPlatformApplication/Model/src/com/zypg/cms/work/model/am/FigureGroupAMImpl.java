package com.zypg.cms.work.model.am;


import com.honythink.applicationframework.hadf.util.DateUtil;

import com.zypg.cms.work.model.am.common.FigureGroupAM;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.CmsDocGroupRelTVOImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.CmsDocGroupRelTVORowImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.CmsFigureGroupTVOImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.CmsFigureGroupTVORowImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.query.CmsDocGroupRelVOImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.query.CmsFigureGroupVOImpl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.RowQualifier;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 09 12:05:31 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FigureGroupAMImpl extends ApplicationModuleImpl implements FigureGroupAM {
    /**
     * This is the default constructor (do not remove).
     */
    public FigureGroupAMImpl() {
    }

    public Map<String, Object> initFigureGroup(String docIds, String libCode, String compCode) {
        if (this.getDBTransaction().isDirty()) {
            this.getDBTransaction().rollback();
        }
        CmsFigureGroupTVOImpl groupVO = this.getCmsFigureGroupTVO();
        CmsFigureGroupTVORowImpl groupRow = (CmsFigureGroupTVORowImpl)groupVO.createRow();
        groupRow.setLibCode(libCode);
        groupRow.setCompCode(compCode);
        groupVO.insertRow(groupRow);
        Map<String, Object> map = new HashMap<String, Object>();
        String[] strArray = docIds.split(",");
        int i = 0;
        List<Number> inGroupList = new ArrayList<Number>();
        List<Number> notInGroupList = new ArrayList<Number>();
        for (String id : strArray) {
            try {
                Number docId = new Number(id);
                CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
                //判断该图片是不是在组里面
                Row[] relRows = relVO.getFilteredRows("DocId", docId);
                if (relRows.length > 0) {
                    //在组里面，先将所在组的所有图片取出来，然后将这些图片全部加入新组
                    Number groupId = (Number)relRows[0].getAttribute("GroupId");
                    Row[] docGroupRelRows = relVO.getFilteredRows("GroupId", groupId);
                    inGroupList.add(groupId);
                    for (Row r : docGroupRelRows) {
                        CmsDocGroupRelTVORowImpl docGroupRelRow = (CmsDocGroupRelTVORowImpl)r;
                        if (docId.intValue() == docGroupRelRow.getDocId().intValue() && i == 0) {
                            this.insertDocGroupRel(docGroupRelRow.getDocId(), groupRow.getGroupId(), "Y");
                            map.put("defaultIndexFigureDocId", docGroupRelRow.getDocId());
                            i++;
                        } else {
                            this.insertDocGroupRel(docGroupRelRow.getDocId(), groupRow.getGroupId(), "N");
                        }
                    }
                    //将旧组里的图片加入新组后删除旧组里的图片
                    for (Row r : docGroupRelRows) {
                        r.remove();
                    }
                    //将旧组删掉
                    Row[] groupRows = groupVO.getFilteredRows("GroupId", groupId);
                    if (groupRows.length > 0) {
                        groupRows[0].remove();
                    }
                } else {
                    //不在组里面，直接加入新组
                    notInGroupList.add(docId);
                    this.insertDocGroupRel(docId, groupRow.getGroupId(), "N");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (i == 0) {
                    this.setFigureToIndex(new Number(strArray[0]));
                    map.put("defaultIndexFigureDocId", new Number(strArray[0]));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        map.put("inGroupList", inGroupList);
        map.put("notInGroupList", notInGroupList);
        return map;
    }

    public String joinNewFigureGroup(String docIds, Number newGroupId) {
        CmsFigureGroupTVOImpl groupVO = this.getCmsFigureGroupTVO();
        String result = null;
        String[] strArray = docIds.split(",");
        for (String id : strArray) {
            try {
                Number docId = new Number(id);
                CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
                //判断该图片是不是在组里面
                Row[] relRows = relVO.getFilteredRows("DocId", docId);
                if (relRows.length > 0) {
                    //在组里面，先将所在组的所有图片取出来，然后将这些图片全部加入新组
                    Number groupId = (Number)relRows[0].getAttribute("GroupId");
                    Row[] docGroupRelRows = relVO.getFilteredRows("GroupId", groupId);
                    for (Row r : docGroupRelRows) {
                        CmsDocGroupRelTVORowImpl docGroupRelRow = (CmsDocGroupRelTVORowImpl)r;
                        this.insertDocGroupRel(docGroupRelRow.getDocId(), newGroupId, "N");
                    }
                    //将旧组里的图片加入新组后删除旧组里的图片
                    for (Row r : docGroupRelRows) {
                        r.remove();
                    }
                    //将旧组删掉
                    Row[] groupRows = groupVO.getFilteredRows("GroupId", groupId);
                    if (groupRows.length > 0) {
                        groupRows[0].remove();
                    }
                } else {
                    //不在组里面，直接加入新组
                    this.insertDocGroupRel(docId, newGroupId, "N");
                }
                result = "Y";
            } catch (Exception e) {
                result = e.getMessage();
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 编辑组信息
     * @param groupId
     */
    public void editFigureGroup(Number groupId) {
        CmsFigureGroupTVOImpl groupVO = this.getCmsFigureGroupTVO4Edit();
        Row rows[] = groupVO.getFilteredRows("GroupId", groupId);
        if (rows.length > 0) {
            groupVO.setCurrentRow(rows[0]);
        }
    }

    public List<Number> initFigureGroup4Edit(Number docId) {
        List<Number> figureList = new ArrayList<Number>();
        CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
        //判断该图片是不是在组里面
        Row[] relRows = relVO.getFilteredRows("DocId", docId);
        if (relRows.length > 0) {
            Number groupId = (Number)relRows[0].getAttribute("GroupId");
            Row[] docGroupRelRows = relVO.getFilteredRows("GroupId", groupId);
            for (Row r : docGroupRelRows) {
                CmsDocGroupRelTVORowImpl docGroupRelRow = (CmsDocGroupRelTVORowImpl)r;
                figureList.add(docGroupRelRow.getDocId());
            }
            editFigureGroup(groupId);
        }
        return figureList;
    }

    /**
     * 往组里面添加图片
     * @param docId
     * @param groupId
     * @param indexFlag
     */
    public void insertDocGroupRel(Number docId, Number groupId, String indexFlag) {
        CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
        CmsDocGroupRelTVORowImpl relRow = (CmsDocGroupRelTVORowImpl)relVO.createRow();
        relRow.setDocId(docId);
        relRow.setGroupId(groupId);
        relRow.setIndexFlag(indexFlag);
        relVO.insertRow(relRow);
    }

    /**
     * 设置图片为索引图
     * @param docId
     */
    public void setFigureToIndex(Number docId) {
        CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
        Row[] rows = relVO.getFilteredRows("DocId", docId);
        if (rows.length > 0) {
            //将当前图片所在图里的索引图设为非索引图
            Number groupId = (Number)rows[0].getAttribute("GroupId");
            Row[] indexRows = relVO.getFilteredRows(new RowQualifier("GroupId =" + groupId + " and IndexFlag='Y' "));
            for (Row row : indexRows) {
                row.setAttribute("IndexFlag", "N");
            }
            //将当前图片设为索引图片
            CmsDocGroupRelTVORowImpl relRow = (CmsDocGroupRelTVORowImpl)rows[0];
            relRow.setIndexFlag("Y");
        }
    }

    /**
     * 拆分组图
     * @return
     */
    public String deleteFigureGroup(Number docId, String groupFlag) {
        String result = "Y";
        CmsFigureGroupTVOImpl groupVO = this.getCmsFigureGroupTVO();
        CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
        //在组外拆分，则全组拆分
        if ("N".equals(groupFlag)) {
            try {
                Row[] relRows = relVO.getFilteredRows("DocId", docId);
                if (relRows.length > 0) {
                    Number groupId = (Number)relRows[0].getAttribute("GroupId");
                    Row[] docGroupRelRows = relVO.getFilteredRows("GroupId", groupId);
                    for (Row r : docGroupRelRows) {
                        r.remove();
                    }
                    Row[] groupRows = groupVO.getFilteredRows("GroupId", groupId);
                    if (groupRows.length > 0) {
                        groupRows[0].remove();
                    }
                }
            } catch (Exception e) {
                result = e.getMessage();
                e.printStackTrace();
            }
        } else { //在组内拆分，只拆分指定的图片，如何拆分出去的是索引图，则需另外指定一张索引图，如所有的图片都被拆分出去了，则整组解散
            Row[] relRows = relVO.getFilteredRows("DocId", docId);
            if (relRows.length > 0) {
                Number groupId = (Number)relRows[0].getAttribute("GroupId");
                Row[] docGroupRelRows = relVO.getFilteredRows("GroupId", groupId);
                if (docGroupRelRows.length == 1) {
                    relRows[0].remove();
                    Row[] groupRows = groupVO.getFilteredRows("GroupId", groupId);
                    if (groupRows.length > 0) {
                        groupRows[0].remove();
                    }
                } else if (docGroupRelRows.length > 1) {
                    if ("Y".equals(relRows[0].getAttribute("IndexFlag"))) {
                        for (Row r : docGroupRelRows) {
                            Number relDocId = (Number)r.getAttribute("DocId");
                            if (relDocId.intValue() != docId.intValue()) {
                                this.setFigureToIndex(relDocId);
                                break;
                            }
                        }
                    }
                    relRows[0].remove();
                }
            }
        }
        return result;
    }

    public Row[] queryDocGroupRel(Number groupId) {
        CmsDocGroupRelVOImpl relVO = this.getCmsDocGroupRelVO4Query();
        relVO.setApplyViewCriteriaName("findDocGroupRelByGroupId");
        relVO.setbvGroupId(groupId);
        relVO.executeQuery();
        relVO.setRangeSize(-1);
        return relVO.getAllRowsInRange();
    }

    public void init4JionFigureGroup() {
        if (this.getDBTransaction().isDirty()) {
            this.getDBTransaction().rollback();
        }
        CmsFigureGroupVOImpl groupVO = this.getCmsFigureGroupVO();
        groupVO.executeEmptyRowSet();
    }

    public Row[] queryFigureGroup(Number groupId, String groupNameOrDesc, Date startTime, Date endTime, String libCode,
                                  String compCode) {
        CmsFigureGroupVOImpl groupVO = this.getCmsFigureGroupVO();
        groupVO.setbvGroupId(groupId);
        groupVO.setbvGroupName(groupNameOrDesc);
        groupVO.setbvGroupDesc(groupNameOrDesc);
        groupVO.setbvStartTime(startTime == null ? DateUtil.getCurrentJboDate() : startTime);
        groupVO.setbvEndTime(endTime == null ? DateUtil.getCurrentJboDate() : endTime);
        groupVO.setbvCompCode(compCode);
        groupVO.setbvLibCode(libCode);
        groupVO.executeQuery();
        groupVO.setRangeSize(-1);
        Row rows[] = groupVO.getAllRowsInRange();
        if (rows.length > 0) {
            groupVO.setCurrentRow(rows[0]);
        }
        return rows;
    }

    /**
     * Container's getter for CmsDocGroupRelTVO.
     * @return CmsDocGroupRelTVO
     */
    public CmsDocGroupRelTVOImpl getCmsDocGroupRelTVO() {
        return (CmsDocGroupRelTVOImpl)findViewObject("CmsDocGroupRelTVO");
    }

    /**
     * Container's getter for CmsFigureGroupTVO.
     * @return CmsFigureGroupTVO
     */
    public CmsFigureGroupTVOImpl getCmsFigureGroupTVO() {
        return (CmsFigureGroupTVOImpl)findViewObject("CmsFigureGroupTVO");
    }


    /**
     * Container's getter for CmsDocTVO4Query.
     * @return CmsDocTVO4Query
     */
    public CmsDocTVOImpl getCmsDocTVO4Query() {
        return (CmsDocTVOImpl)findViewObject("CmsDocTVO4Query");
    }

    /**
     * Container's getter for CmsDocGroupRelVO4Query.
     * @return CmsDocGroupRelVO4Query
     */
    public CmsDocGroupRelVOImpl getCmsDocGroupRelVO4Query() {
        return (CmsDocGroupRelVOImpl)findViewObject("CmsDocGroupRelVO4Query");
    }

    /**
     * Container's getter for CmsFigureGroupVO.
     * @return CmsFigureGroupVO
     */
    public CmsFigureGroupVOImpl getCmsFigureGroupVO() {
        return (CmsFigureGroupVOImpl)findViewObject("CmsFigureGroupVO");
    }

    /**
     * Container's getter for CmsFigureGroupTVO4Edit.
     * @return CmsFigureGroupTVO4Edit
     */
    public CmsFigureGroupTVOImpl getCmsFigureGroupTVO4Edit() {
        return (CmsFigureGroupTVOImpl)findViewObject("CmsFigureGroupTVO4Edit");
    }
}