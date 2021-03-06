package com.zypg.cms.work.model.am;


import com.honythink.applicationframework.hadf.CustomApplicationModuleImpl;

import com.zypg.cms.work.model.am.common.AudioAM;
import com.zypg.cms.work.model.viewobject.audio.CmsAudioEditVOImpl;
import com.zypg.cms.work.model.viewobject.audio.CmsAudioEditVORowImpl;
import com.zypg.cms.work.model.viewobject.audio.CmsAudioTVOImpl;
import com.zypg.cms.work.model.viewobject.audio.CmsAudioTVORowImpl;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioQVOImpl;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioQueryVOImpl;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioVOImpl;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocCategoryRelTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocCategoryRelTVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocUcmRelTVOImpl;

import com.zypg.cms.work.model.viewobject.video.query.CmsVideoQueryVOImpl;

import java.sql.SQLException;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 18 13:24:31 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AudioAMImpl extends CustomApplicationModuleImpl implements AudioAM {
    /**
     * This is the default constructor (do not remove).
     */
    public AudioAMImpl() {
    }

    public Number preEditAudio(String mode, Number docId, String compCode) {
        CmsAudioTVOImpl audioVO = this.getCmsAudioTVO();
        if ("CREATE".equals(mode)) {
            CmsDocTVOImpl docVO = this.getCmsDocTVO();
            CmsDocTVORowImpl docNewRow = (CmsDocTVORowImpl)docVO.createRow();
            docNewRow.setStatus("TO_INDEX_AUDIO");
            docNewRow.setLibCode("AUDIO");
            docNewRow.setLibTypeCode("MATERIAL_LIB");
            docNewRow.setCompCode(compCode);
            docVO.insertRow(docNewRow);
            CmsAudioTVORowImpl audioNewRow = (CmsAudioTVORowImpl)audioVO.createRow();
            audioNewRow.setDocId(docNewRow.getDocId());
            audioVO.insertRow(audioNewRow);

            preDocCategroupRel(mode, docNewRow.getDocId());
            return docNewRow.getDocId();
        } else {
            audioVO.setbvDocId(docId);
            audioVO.executeQuery();
            Row row = audioVO.first();
            if (row != null) {
                row.setAttribute("LastUpdateDate", new Date());
                audioVO.setCurrentRow(row);
            }
            preDocCategroupRel(mode, docId);
            return docId;
        }
    }

    public Number preScriptAudio(Number docId) {
        CmsAudioTVOImpl audioVO = this.getCmsAudioTVO();
        audioVO.setbvDocId(docId);
        audioVO.executeQuery();
        Row r = audioVO.first();
        if (r != null) {
            CmsAudioTVORowImpl row = (CmsAudioTVORowImpl)r;
            audioVO.setCurrentRow(row);
        }
        return null;
    }

    public void initAudioEditVO() {
        CmsAudioEditVOImpl vo = this.getCmsAudioEditVO();
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            vo.setCurrentRow(row);
        }
    }

    public void batchEditAudio(String docIds) {
        String[] docId = docIds.split(",");
        CmsAudioTVOImpl audioVo = this.getCmsAudioTVO();
        CmsAudioEditVOImpl editVO = this.getCmsAudioEditVO();
        CmsAudioEditVORowImpl editRow = (CmsAudioEditVORowImpl)editVO.getCurrentRow();
        for (String id : docId) {
            //            Row[] rows = audio.getFilteredRows("DocId", id);
            try {
                audioVo.setbvDocId(new Number(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            audioVo.executeQuery();
            Row row = audioVo.first();
            if (row != null) {
                CmsAudioTVORowImpl audioRow = (CmsAudioTVORowImpl)row;
                for (String attr : editRow.getAttributeNames()) {
                    if (editRow.getAttribute(attr) != null) {
                        if ("Keyword".equals(attr)) {
                            String oldKeyword = audioRow.getKeyword();
                            String newKeyword = null;
                            if (oldKeyword == null) {
                                newKeyword = editRow.getAttribute(attr) + "";
                            } else {
                                newKeyword = oldKeyword + "," + editRow.getAttribute(attr);
                            }
                            audioRow.setAttribute(attr, newKeyword);
                        } else {
                            audioRow.setAttribute(attr, editRow.getAttribute(attr));
                        }
                    }
                }
                audioRow.setLastUpdatedBy(this.getCustomDBTransaction().getUserId());
                audioRow.setLastUpdateDate(new Date());
                try {
                    if (editRow.getCategoryId() != null) {
                        updateCategoryRel(editRow.getCategoryId(), new Number(id));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

    private void preDocCategroupRel(String mode, Number docId) {
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

    public void initAudio4View(Number docId) {
        CmsAudioTVOImpl audioVO = this.getCmsAudioTVO4View();
        audioVO.setbvDocId(docId);
        audioVO.executeQuery();
        CmsDocUcmRelTVOImpl durTVO = this.getCmsDocUcmRelTVO4FileList();
        durTVO.setbvDocId(docId);
        durTVO.executeQuery();
        Row row = audioVO.first();
        if (row != null) {
            audioVO.setCurrentRow(row);
        }
    }

    /**
     * Container's getter for CmsAudioTVO.
     * @return CmsAudioTVO
     */
    public CmsAudioTVOImpl getCmsAudioTVO() {
        return (CmsAudioTVOImpl)findViewObject("CmsAudioTVO");
    }

    //rollBack

    public String rollBack() {
        String retCode = "S";
        try {
            this.getDBTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
            retCode = "E";
        }
        return retCode;
    }

    public String commit() {
        String retCode = "S";
        try {
            this.getDBTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            retCode = "E";
        }
        return retCode;
    }

    /**
     * 初始化高级查询面板
     */
    public void initComplexSearch4Audio() {
        CmsAudioQVOImpl vo = this.getCmsAudioQVO();
        vo.executeQuery();
        vo.setCurrentRow(vo.first());
    }


    /**
     * Container's getter for CmsDocTVO1.
     * @return CmsDocTVO1
     */
    public CmsDocTVOImpl getCmsDocTVO() {
        return (CmsDocTVOImpl)findViewObject("CmsDocTVO");
    }

    /**
     * Container's getter for CmsDocCategoryRelTVO1.
     * @return CmsDocCategoryRelTVO1
     */
    public CmsDocCategoryRelTVOImpl getCmsDocCategoryRelTVO() {
        return (CmsDocCategoryRelTVOImpl)findViewObject("CmsDocCategoryRelTVO");
    }

    /**
     * Container's getter for CmsAudioVO.
     * @return CmsAudioVO
     */
    public CmsAudioVOImpl getCmsAudioVO() {
        return (CmsAudioVOImpl)findViewObject("CmsAudioVO");
    }

    /**
     * Container's getter for CmsAudioEditVO.
     * @return CmsAudioEditVO
     */
    public CmsAudioEditVOImpl getCmsAudioEditVO() {
        return (CmsAudioEditVOImpl)findViewObject("CmsAudioEditVO");
    }

    /**
     * Container's getter for CmsDocUcmRelTVO.
     * @return CmsDocUcmRelTVO
     */
    public CmsDocUcmRelTVOImpl getCmsDocUcmRelTVO() {
        return (CmsDocUcmRelTVOImpl)findViewObject("CmsDocUcmRelTVO");
    }

    /**
     * Container's getter for CmsAudio4ViewTVO.
     * @return CmsAudio4ViewTVO
     */
    public CmsAudioTVOImpl getCmsAudio4ViewTVO() {
        return (CmsAudioTVOImpl)findViewObject("CmsAudio4ViewTVO");
    }

    /**
     * Container's getter for CmsAudioTVO4View.
     * @return CmsAudioTVO4View
     */
    public CmsAudioTVOImpl getCmsAudioTVO4View() {
        return (CmsAudioTVOImpl)findViewObject("CmsAudioTVO4View");
    }

    /**
     * Container's getter for CmsAudioVO4View.
     * @return CmsAudioVO4View
     */
    public CmsAudioVOImpl getCmsAudioVO4View() {
        return (CmsAudioVOImpl)findViewObject("CmsAudioVO4View");
    }

    /**
     * Container's getter for CmsDocUcmRelTVO4FileList.
     * @return CmsDocUcmRelTVO4FileList
     */
    public CmsDocUcmRelTVOImpl getCmsDocUcmRelTVO4FileList() {
        return (CmsDocUcmRelTVOImpl)findViewObject("CmsDocUcmRelTVO4FileList");
    }

    /**
     * Container's getter for CmsVideoQueryVO.
     * @return CmsVideoQueryVO
     */
    public CmsVideoQueryVOImpl getCmsVideoQueryVO() {
        return (CmsVideoQueryVOImpl)findViewObject("CmsVideoQueryVO");
    }

    /**
     * Container's getter for CmsAudioQueryVO.
     * @return CmsAudioQueryVO
     */
    public CmsAudioQueryVOImpl getCmsAudioQueryVO() {
        return (CmsAudioQueryVOImpl)findViewObject("CmsAudioQueryVO");
    }

    /**
     * Container's getter for CmsAudioQVO.
     * @return CmsAudioQVO
     */
    public CmsAudioQVOImpl getCmsAudioQVO() {
        return (CmsAudioQVOImpl)findViewObject("CmsAudioQVO");
    }
}
