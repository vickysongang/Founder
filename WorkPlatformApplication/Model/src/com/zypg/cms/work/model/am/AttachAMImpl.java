package com.zypg.cms.work.model.am;


import com.honythink.applicationframework.hadf.CustomApplicationModuleImpl;

import com.zypg.cms.work.model.viewobject.attach.CmsAttachEditTVOImpl;
import com.zypg.cms.work.model.viewobject.attach.CmsAttachEditTVORowImpl;
import com.zypg.cms.work.model.viewobject.attach.CmsAttachTVOImpl;
import com.zypg.cms.work.model.viewobject.attach.CmsAttachTVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocCategoryRelTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 07 17:19:38 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AttachAMImpl extends CustomApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public AttachAMImpl() {
    }

    public void preMgmtAttach(Number docId, String libCode) {
        CmsAttachEditTVOImpl vo = (CmsAttachEditTVOImpl)this.getCmsAttachEditTVO();
        vo.executeQuery();
        CmsAttachEditTVORowImpl row = (CmsAttachEditTVORowImpl)vo.first();
        row.setSourceId(docId);
        row.setlibCode(libCode);
        vo.setCurrentRow(row);
        
    }

    public void addAttach() {
        CmsAttachTVOImpl tvo = this.getCmsAttachTVO();
        CmsAttachTVORowImpl attachRow = (CmsAttachTVORowImpl)tvo.createRow();
        CmsAttachEditTVOImpl editVO = (CmsAttachEditTVOImpl)this.getCmsAttachEditTVO();
        CmsAttachEditTVORowImpl editRow = (CmsAttachEditTVORowImpl)editVO.getCurrentRow();
        for (String attr : editRow.getAttributeNames()) {
            if (editRow.getAttribute(attr) != null) {
                attachRow.setAttribute(attr, editRow.getAttribute(attr));
            }
        }
        attachRow.setLastUpdatedBy(this.getCustomDBTransaction().getUserId());
        attachRow.setLastUpdateDate(new Date());
    }


    /**
     * Container's getter for CmsAttachTVO.
     * @return CmsAttachTVO
     */
    public CmsAttachTVOImpl getCmsAttachTVO() {
        return (CmsAttachTVOImpl)findViewObject("CmsAttachTVO");
    }

    /**
     * Container's getter for CmsDocTVO.
     * @return CmsDocTVO
     */
    public CmsDocTVOImpl getCmsDocTVO() {
        return (CmsDocTVOImpl)findViewObject("CmsDocTVO");
    }

    /**
     * Container's getter for CmsDocCategoryRelTVO.
     * @return CmsDocCategoryRelTVO
     */
    public CmsDocCategoryRelTVOImpl getCmsDocCategoryRelTVO() {
        return (CmsDocCategoryRelTVOImpl)findViewObject("CmsDocCategoryRelTVO");
    }

    /**
     * Container's getter for CmsAttachVO.
     * @return CmsAttachVO
     */
    public ViewObjectImpl getCmsAttachVO() {
        return (ViewObjectImpl)findViewObject("CmsAttachVO");
    }

    /**
     * Container's getter for CmsAttachTVO4List.
     * @return CmsAttachTVO4List
     */
    public CmsAttachTVOImpl getCmsAttachTVO4List() {
        return (CmsAttachTVOImpl)findViewObject("CmsAttachTVO4List");
    }

    /**
     * Container's getter for CmsAttachEditTVO.
     * @return CmsAttachEditTVO
     */
    public ViewObjectImpl getCmsAttachEditTVO() {
        return (ViewObjectImpl)findViewObject("CmsAttachEditTVO");
    }
}
