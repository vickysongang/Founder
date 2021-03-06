package com.zypg.cms.work.model.am;


import com.honythink.applicationframework.hadf.CustomApplicationModuleImpl;

import com.zypg.cms.work.model.am.common.MaterialFigureAM;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocCategoryRelTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocCategoryRelTVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocUcmRelTVOImpl;
import com.zypg.cms.work.model.viewobject.exif.CmsFigureExifInfoTVOImpl;
import com.zypg.cms.work.model.viewobject.exif.CmsFigureExifInfoTVORowImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.CmsDocGroupRelTVOImpl;
import com.zypg.cms.work.model.viewobject.figuregroup.CmsDocGroupRelTVORowImpl;
import com.zypg.cms.work.model.viewobject.material.CmsMaterialFigureEditVOImpl;
import com.zypg.cms.work.model.viewobject.material.CmsMaterialFigureEditVORowImpl;
import com.zypg.cms.work.model.viewobject.material.CmsMaterialFigureTVOImpl;
import com.zypg.cms.work.model.viewobject.material.CmsMaterialFigureTVORowImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureQVOImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureQueryVOImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureVOImpl;
import com.zypg.cms.work.model.viewobject.relation.CmsDocRelTVOImpl;

import java.sql.SQLException;

import java.util.Iterator;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.commons.lang.ArrayUtils;
import org.apache.sanselan.ImageInfo;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Apr 23 11:54:29 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class MaterialFigureAMImpl extends CustomApplicationModuleImpl implements MaterialFigureAM {
    /**
     * This is the default constructor (do not remove).
     */
    public MaterialFigureAMImpl() {
    }

    /****************************************素材图库相关**************************************************************/
    public Number preEditMaterial(String mode, Number docId, String compCode) {
        Number result = null;
        CmsMaterialFigureTVOImpl cmsMaterialFigureTVO = this.getCmsMaterialFigureTVO();
        if ("CREATE".equals(mode)) {
            CmsDocTVOImpl docVO = this.getCmsDocTVO();
            CmsDocTVORowImpl docNewRow = (CmsDocTVORowImpl)docVO.createRow();
            docNewRow.setStatus("TO_INDEX_MATERIAL_FIGURE");
            docNewRow.setLibCode("MATERIAL_FIGURE");
            docNewRow.setLibTypeCode("MATERIAL_LIB");
            docNewRow.setCompCode(compCode);
            docVO.insertRow(docNewRow);
            CmsMaterialFigureTVORowImpl newRow = (CmsMaterialFigureTVORowImpl)cmsMaterialFigureTVO.createRow();
            newRow.setDocId(docNewRow.getDocId());
            cmsMaterialFigureTVO.insertRow(newRow);

            preDocCategroupRel(mode, docNewRow.getDocId());
            result = docNewRow.getDocId();
        } else {
            result = docId;
            cmsMaterialFigureTVO.setbvDocId(docId);
            cmsMaterialFigureTVO.executeQuery();
            Row row = cmsMaterialFigureTVO.first();
            if (row != null) {
                row.setAttribute("LastUpdateDate", new Date());
                cmsMaterialFigureTVO.setCurrentRow(row);
            }
            preDocCategroupRel(mode, docId);
        }
        return result;
    }

    public void updateMaterialFigureInfo(ImageInfo info, String size) {
        CmsMaterialFigureTVOImpl cmsMaterialFigureTVO = this.getCmsMaterialFigureTVO();
        CmsMaterialFigureTVORowImpl cmsMaterialFigureTVORow =
            (CmsMaterialFigureTVORowImpl)cmsMaterialFigureTVO.getCurrentRow();
        cmsMaterialFigureTVORow.setFigureFormat(info.getFormat().name);
        cmsMaterialFigureTVORow.setFigureResolution(info.getPhysicalWidthDpi() + " DPI");
        cmsMaterialFigureTVORow.setFigureXResolution(info.getPhysicalWidthDpi() + " DPI");
        cmsMaterialFigureTVORow.setFigureYResolution(info.getPhysicalHeightDpi() + " DPI");
        cmsMaterialFigureTVORow.setFigureSize(size);
        cmsMaterialFigureTVORow.setFigureWidth(info.getWidth() + "px");
        cmsMaterialFigureTVORow.setFigureHeight(info.getHeight() + "px");
        cmsMaterialFigureTVORow.setFigureDimension(info.getWidth() + "x" + info.getHeight());
    }

    public void updateMaterialFigure(String format, String size, int width, int height) {
        CmsMaterialFigureTVOImpl cmsMaterialFigureTVO = this.getCmsMaterialFigureTVO();
        CmsMaterialFigureTVORowImpl cmsMaterialFigureTVORow =
            (CmsMaterialFigureTVORowImpl)cmsMaterialFigureTVO.getCurrentRow();
        cmsMaterialFigureTVORow.setFigureFormat(format);
        cmsMaterialFigureTVORow.setFigureSize(size);
        if (width != 0) {
            cmsMaterialFigureTVORow.setFigureWidth(width + "px");
        }
        if (height != 0) {
            cmsMaterialFigureTVORow.setFigureHeight(height + "px");
        }
        if (width != 0 && height != 0) {
            cmsMaterialFigureTVORow.setFigureDimension(width + "x" + height);
        }
    }

    public void initInfo4View(Number docId) {
        CmsDocTVOImpl docVO = this.getCmsDocTVO4View();
        docVO.setbvDocId(docId);
        docVO.executeQuery();
        //        CmsDocUcmRelTVOImpl durTVO = this.getCmsDocUcmRelTVO4FileList();
        //        durTVO.setbvDocId(docId);
        //        durTVO.executeQuery();
        Row row = docVO.first();
        if (row != null) {
            docVO.setCurrentRow(row);
        }
    }

    public void preDocCategroupRel(String mode, Number docId) {
        CmsDocCategoryRelTVOImpl docCategoryRelVO = this.getCmsDocCategoryRelTVO();
        if ("CREATE".equals(mode)) {
            CmsDocCategoryRelTVORowImpl docCategoryRelNewRow =
                (CmsDocCategoryRelTVORowImpl)docCategoryRelVO.createRow();
            docCategoryRelNewRow.setDocId(docId);
            docCategoryRelNewRow.setCategoryGroupType("BY_CATEGORY");
            docCategoryRelNewRow.setCategoryId(new Number(-1));
            docCategoryRelVO.insertRow(docCategoryRelNewRow);
        } else {
            docCategoryRelVO.setbvDocId(docId);
            docCategoryRelVO.executeQuery();
            Row row = docCategoryRelVO.first();
            if (row != null) {
                docCategoryRelVO.setCurrentRow(row);
            }
        }
    }

    public void initMaterialFigureEditVO() {
        CmsMaterialFigureEditVOImpl vo = (CmsMaterialFigureEditVOImpl)this.getCmsMaterialFigureEditVO();
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            vo.setCurrentRow(row);
        }
    }

    public void updateDocInfo(Number docId, String thumbnailUrl) {
        CmsDocTVOImpl docVO = this.getCmsDocTVO();
        docVO.setbvDocId(docId);
        docVO.executeQuery();
        Row r = docVO.first();
        if (r != null) {
            CmsDocTVORowImpl row = (CmsDocTVORowImpl)r;
            row.setThumbnailUrl(thumbnailUrl);
        }
    }

    public void batchEditMaterialFigure(String docIds) {
        //分解id
        //循环 查询id的组 然后找到该组下所有文件
        //批量修改
        CmsMaterialFigureTVOImpl materialVO = this.getCmsMaterialFigureTVO();
        CmsMaterialFigureEditVOImpl editVO = (CmsMaterialFigureEditVOImpl)this.getCmsMaterialFigureEditVO();
        CmsMaterialFigureEditVORowImpl editRow = (CmsMaterialFigureEditVORowImpl)editVO.getCurrentRow();
        String[] strArray = docIds.split(",");
        for (String id : strArray) {
            try {
                Number docId = new Number(id);
                CmsDocGroupRelTVOImpl relVO = this.getCmsDocGroupRelTVO();
                //通过id 找到组
                Row[] relRows = relVO.getFilteredRows("DocId", docId);
                if (relRows.length > 0) {
                    Number groupId = (Number)relRows[0].getAttribute("GroupId");
                    Row[] docGroupRelRows = relVO.getFilteredRows("GroupId", groupId);
                    //循环该组所有图片
                    for (Row r : docGroupRelRows) {
                        CmsDocGroupRelTVORowImpl docGroupRelRow = (CmsDocGroupRelTVORowImpl)r;
                        //                        Row[] rows = materialVO.getFilteredRows("DocId", docGroupRelRow.getDocId());
                        materialVO.setbvDocId(docGroupRelRow.getDocId());
                        materialVO.executeQuery();
                        Row firstRow = materialVO.first();
                        if (firstRow != null) {
                            CmsMaterialFigureTVORowImpl materialRow = (CmsMaterialFigureTVORowImpl)firstRow;
                            for (String attr : editRow.getAttributeNames()) {
                                if (editRow.getAttribute(attr) != null) {
                                    if ("Keyword".equals(attr)) {
                                        String oldKeyword = materialRow.getKeyword();
                                        String newKeyword = null;
                                        if (oldKeyword == null) {
                                            newKeyword = editRow.getAttribute(attr) + "";
                                        } else {
                                            newKeyword = oldKeyword + "," + editRow.getAttribute(attr);
                                        }
                                        materialRow.setAttribute(attr, newKeyword);
                                    } else {
                                        materialRow.setAttribute(attr, editRow.getAttribute(attr));
                                    }
                                }
                            }
                            materialRow.setLastUpdatedBy(this.getCustomDBTransaction().getUserId());
                            materialRow.setLastUpdateDate(new Date());
                            try {
                                if (editRow.getCategoryId() != null) {
                                    updateCategoryRel(editRow.getCategoryId(), new Number(id));
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    //单个的图片 不是组
                    //                    Row[] rows = materialVO.getFilteredRows("DocId", docId);
                    materialVO.setbvDocId(docId);
                    materialVO.executeQuery();
                    Row firstRow = materialVO.first();
                    if (firstRow != null) {
                        CmsMaterialFigureTVORowImpl materialRow = (CmsMaterialFigureTVORowImpl)firstRow;
                        for (String attr : editRow.getAttributeNames()) {
                            if (editRow.getAttribute(attr) != null) {
                                if ("Keyword".equals(attr)) {
                                    String oldKeyword = materialRow.getKeyword();
                                    String newKeyword = null;
                                    if (oldKeyword == null) {
                                        newKeyword = editRow.getAttribute(attr) + "";
                                    } else {
                                        newKeyword = oldKeyword + "," + editRow.getAttribute(attr);
                                    }
                                    materialRow.setAttribute(attr, newKeyword);
                                } else {
                                    materialRow.setAttribute(attr, editRow.getAttribute(attr));
                                }
                            }
                        }
                        materialRow.setLastUpdatedBy(this.getCustomDBTransaction().getUserId());
                        materialRow.setLastUpdateDate(new Date());
                        try {
                            if (editRow.getCategoryId() != null) {
                                updateCategoryRel(editRow.getCategoryId(), new Number(id));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCategoryRel(Number categoryId, Number docId) {
        CmsDocCategoryRelTVOImpl docCategoryRelVO = this.getCmsDocCategoryRelTVO();
        docCategoryRelVO.setbvDocId(docId);
        docCategoryRelVO.executeQuery();
        Row row = docCategoryRelVO.first();
        if (row != null) {
            CmsDocCategoryRelTVORowImpl docCategoryRelNewRow = (CmsDocCategoryRelTVORowImpl)row;
            docCategoryRelNewRow.setCategoryId(categoryId);
        }
    }

    public void processExifInfo4MaterialFigure(Number docId, Map<String, String> exifMap, String mode) {
        CmsFigureExifInfoTVOImpl exifInfoVO = this.getCmsFigureExifInfoTVO();
        if ("CREATE".equals(mode)) {
            CmsFigureExifInfoTVORowImpl exifInfoRow = (CmsFigureExifInfoTVORowImpl)exifInfoVO.createRow();
            exifInfoRow.setDocId(docId);
            for (String attrName : exifInfoRow.getAttributeNames()) {
                String[] whoAttribute =
                    new String[] { "InfoId", "DocId", "ObjectVersionNumber", "CreatedBy", "CreationDate",
                                   "LastUpdatedBy", "LastUpdateDate" };
                if (!attrName.contains("Attribute") && !ArrayUtils.contains(whoAttribute, attrName)) {
                    for (Iterator it = exifMap.keySet().iterator(); it.hasNext(); ) {
                        String key = (String)it.next();
                        String newKey = key.replace(" ", "").replace("-", "").replace("/", "");
                        if (newKey.equalsIgnoreCase(attrName)) {
                            exifInfoRow.setAttribute(attrName, exifMap.get(key));
                            break;
                        }
                    }
                }
            }
            exifInfoVO.insertRow(exifInfoRow);
        } else {
            Row[] rows = exifInfoVO.getFilteredRows("DocId", docId);
            if (rows.length > 0) {
                CmsFigureExifInfoTVORowImpl exifInfoRow = (CmsFigureExifInfoTVORowImpl)rows[0];
                for (String attrName : exifInfoRow.getAttributeNames()) {
                    String[] whoAttribute =
                        new String[] { "InfoId", "DocId", "ObjectVersionNumber", "CreatedBy", "CreationDate",
                                       "LastUpdatedBy", "LastUpdateDate" };
                    if (!attrName.contains("Attribute") && !ArrayUtils.contains(whoAttribute, attrName)) {
                        for (Iterator it = exifMap.keySet().iterator(); it.hasNext(); ) {
                            String key = (String)it.next();
                            String newKey = key.replace(" ", "").replace("-", "").replace("/", "");
                            if (newKey.equalsIgnoreCase(attrName)) {
                                exifInfoRow.setAttribute(attrName, exifMap.get(key));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 初始化高级查询面板
     */
    public void initComplexSearch4MaterialFigure() {
        CmsMaterialFigureQVOImpl vo = this.getCmsMaterialFigureQVO();
        vo.executeQuery();
        vo.setCurrentRow(vo.first());
    }

    /**
     * Container's getter for CmsDocCategoryRelTVO.
     * @return CmsDocCategoryRelTVO
     */
    public CmsDocCategoryRelTVOImpl getCmsDocCategoryRelTVO() {
        return (CmsDocCategoryRelTVOImpl)findViewObject("CmsDocCategoryRelTVO");
    }

    /**
     * Container's getter for CmsDocTVO.
     * @return CmsDocTVO
     */
    public CmsDocTVOImpl getCmsDocTVO() {
        return (CmsDocTVOImpl)findViewObject("CmsDocTVO");
    }

    /**
     * Container's getter for CmsMaterialFigureTVO.
     * @return CmsMaterialFigureTVO
     */
    public CmsMaterialFigureTVOImpl getCmsMaterialFigureTVO() {
        return (CmsMaterialFigureTVOImpl)findViewObject("CmsMaterialFigureTVO");
    }

    /**
     * Container's getter for CmsMaterialFigureVO.
     * @return CmsMaterialFigureVO
     */
    public CmsMaterialFigureVOImpl getCmsMaterialFigureVO() {
        return (CmsMaterialFigureVOImpl)findViewObject("CmsMaterialFigureVO");
    }

    /**
     * Container's getter for CmsMaterialFigureEditVO.
     * @return CmsMaterialFigureEditVO
     */
    public ViewObjectImpl getCmsMaterialFigureEditVO() {
        return (ViewObjectImpl)findViewObject("CmsMaterialFigureEditVO");
    }

    /**
     * Container's getter for CmsDocGroupRelTVO.
     * @return CmsDocGroupRelTVO
     */
    public CmsDocGroupRelTVOImpl getCmsDocGroupRelTVO() {
        return (CmsDocGroupRelTVOImpl)findViewObject("CmsDocGroupRelTVO");
    }

    /**
     * Container's getter for CmsDocTVO4View.
     * @return CmsDocTVO4View
     */
    public CmsDocTVOImpl getCmsDocTVO4View() {
        return (CmsDocTVOImpl)findViewObject("CmsDocTVO4View");
    }

    /**
     * Container's getter for CmsMaterialFigureTVO4View.
     * @return CmsMaterialFigureTVO4View
     */
    public CmsMaterialFigureTVOImpl getCmsMaterialFigureTVO4View() {
        return (CmsMaterialFigureTVOImpl)findViewObject("CmsMaterialFigureTVO4View");
    }

    /**
     * Container's getter for CmsDoc2MaterialVL.
     * @return CmsDoc2MaterialVL
     */
    public ViewLinkImpl getCmsDoc2MaterialVL() {
        return (ViewLinkImpl)findViewLink("CmsDoc2MaterialVL");
    }

    /**
     * Container's getter for CmsDocRelTVO4View.
     * @return CmsDocRelTVO4View
     */
    public CmsDocRelTVOImpl getCmsDocRelTVO4View() {
        return (CmsDocRelTVOImpl)findViewObject("CmsDocRelTVO4View");
    }

    /**
     * Container's getter for CmsDoc2DocRelVL.
     * @return CmsDoc2DocRelVL
     */
    public ViewLinkImpl getCmsDoc2DocRelVL() {
        return (ViewLinkImpl)findViewLink("CmsDoc2DocRelVL");
    }

    /**
     * Container's getter for CmsFigureExifInfoTVO.
     * @return CmsFigureExifInfoTVO
     */
    public CmsFigureExifInfoTVOImpl getCmsFigureExifInfoTVO() {
        return (CmsFigureExifInfoTVOImpl)findViewObject("CmsFigureExifInfoTVO");
    }

    /**
     * Container's getter for CmsFigureExifInfoTVO4View.
     * @return CmsFigureExifInfoTVO4View
     */
    public CmsFigureExifInfoTVOImpl getCmsFigureExifInfoTVO4View() {
        return (CmsFigureExifInfoTVOImpl)findViewObject("CmsFigureExifInfoTVO4View");
    }

    /**
     * Container's getter for CmsDoc2ExifVL.
     * @return CmsDoc2ExifVL
     */
    public ViewLinkImpl getCmsDoc2ExifVL() {
        return (ViewLinkImpl)findViewLink("CmsDoc2ExifVL");
    }

    /**
     * Container's getter for CmsDocUcmRelTVO4FileList.
     * @return CmsDocUcmRelTVO4FileList
     */
    public CmsDocUcmRelTVOImpl getCmsDocUcmRelTVO4FileList() {
        return (CmsDocUcmRelTVOImpl)findViewObject("CmsDocUcmRelTVO4FileList");
    }

    /**
     * Container's getter for CmsMaterialFigureQueryVO.
     * @return CmsMaterialFigureQueryVO
     */
    public CmsMaterialFigureQueryVOImpl getCmsMaterialFigureQueryVO() {
        return (CmsMaterialFigureQueryVOImpl)findViewObject("CmsMaterialFigureQueryVO");
    }

    /**
     * Container's getter for CmsMaterialFigureQVO.
     * @return CmsMaterialFigureQVO
     */
    public CmsMaterialFigureQVOImpl getCmsMaterialFigureQVO() {
        return (CmsMaterialFigureQVOImpl)findViewObject("CmsMaterialFigureQVO");
    }
}
