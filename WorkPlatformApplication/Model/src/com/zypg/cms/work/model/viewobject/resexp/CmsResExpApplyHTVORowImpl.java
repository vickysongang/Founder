package com.zypg.cms.work.model.viewobject.resexp;

import com.zypg.cms.work.model.entity.CmsResExpApplyHTEOImpl;

import java.math.BigDecimal;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 20 12:11:34 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsResExpApplyHTVORowImpl extends ViewRowImpl {


    public static final int ENTITY_CMSRESEXPAPPLYHTEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        ApplyId {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getApplyId();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setApplyId((Number)value);
            }
        }
        ,
        ApplyName {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getApplyName();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setApplyName((String)value);
            }
        }
        ,
        Attribute1 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute1();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute1((String)value);
            }
        }
        ,
        Attribute10 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute10();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute10((String)value);
            }
        }
        ,
        Attribute11 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute11();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute11((String)value);
            }
        }
        ,
        Attribute12 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute12();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute12((String)value);
            }
        }
        ,
        Attribute13 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute13();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute13((String)value);
            }
        }
        ,
        Attribute14 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute14();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute14((String)value);
            }
        }
        ,
        Attribute15 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute15();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute15((String)value);
            }
        }
        ,
        Attribute2 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute2();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute2((String)value);
            }
        }
        ,
        Attribute3 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute3();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute3((String)value);
            }
        }
        ,
        Attribute4 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute4();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute4((String)value);
            }
        }
        ,
        Attribute5 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute5();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute5((String)value);
            }
        }
        ,
        Attribute6 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute6();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute6((String)value);
            }
        }
        ,
        Attribute7 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute7();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute7((String)value);
            }
        }
        ,
        Attribute8 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute8();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute8((String)value);
            }
        }
        ,
        Attribute9 {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getAttribute9();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttribute9((String)value);
            }
        }
        ,
        CreatedBy {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        ObjectVersionNumber {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getObjectVersionNumber();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setObjectVersionNumber((Number)value);
            }
        }
        ,
        TemplateId {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getTemplateId();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setTemplateId((Number)value);
            }
        }
        ,
        UserId {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getUserId();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setUserId((Number)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        BatchCode {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getBatchCode();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setBatchCode((String)value);
            }
        }
        ,
        Status {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        CmsResExpApplyLTVO {
            public Object get(CmsResExpApplyHTVORowImpl obj) {
                return obj.getCmsResExpApplyLTVO();
            }

            public void put(CmsResExpApplyHTVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsResExpApplyHTVORowImpl object);

        public abstract void put(CmsResExpApplyHTVORowImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int APPLYID = AttributesEnum.ApplyId.index();
    public static final int APPLYNAME = AttributesEnum.ApplyName.index();
    public static final int ATTRIBUTE1 = AttributesEnum.Attribute1.index();
    public static final int ATTRIBUTE10 = AttributesEnum.Attribute10.index();
    public static final int ATTRIBUTE11 = AttributesEnum.Attribute11.index();
    public static final int ATTRIBUTE12 = AttributesEnum.Attribute12.index();
    public static final int ATTRIBUTE13 = AttributesEnum.Attribute13.index();
    public static final int ATTRIBUTE14 = AttributesEnum.Attribute14.index();
    public static final int ATTRIBUTE15 = AttributesEnum.Attribute15.index();
    public static final int ATTRIBUTE2 = AttributesEnum.Attribute2.index();
    public static final int ATTRIBUTE3 = AttributesEnum.Attribute3.index();
    public static final int ATTRIBUTE4 = AttributesEnum.Attribute4.index();
    public static final int ATTRIBUTE5 = AttributesEnum.Attribute5.index();
    public static final int ATTRIBUTE6 = AttributesEnum.Attribute6.index();
    public static final int ATTRIBUTE7 = AttributesEnum.Attribute7.index();
    public static final int ATTRIBUTE8 = AttributesEnum.Attribute8.index();
    public static final int ATTRIBUTE9 = AttributesEnum.Attribute9.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int OBJECTVERSIONNUMBER = AttributesEnum.ObjectVersionNumber.index();
    public static final int TEMPLATEID = AttributesEnum.TemplateId.index();
    public static final int USERID = AttributesEnum.UserId.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int BATCHCODE = AttributesEnum.BatchCode.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int CMSRESEXPAPPLYLTVO = AttributesEnum.CmsResExpApplyLTVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsResExpApplyHTVORowImpl() {
    }

    /**
     * Gets CmsResExpApplyHTEO entity object.
     * @return the CmsResExpApplyHTEO
     */
    public CmsResExpApplyHTEOImpl getCmsResExpApplyHTEO() {
        return (CmsResExpApplyHTEOImpl)getEntity(ENTITY_CMSRESEXPAPPLYHTEO);
    }

    /**
     * Gets the attribute value for APPLY_ID using the alias name ApplyId.
     * @return the APPLY_ID
     */
    public Number getApplyId() {
        return (Number) getAttributeInternal(APPLYID);
    }

    /**
     * Sets <code>value</code> as attribute value for APPLY_ID using the alias name ApplyId.
     * @param value value to set the APPLY_ID
     */
    public void setApplyId(Number value) {
        setAttributeInternal(APPLYID, value);
    }

    /**
     * Gets the attribute value for APPLY_NAME using the alias name ApplyName.
     * @return the APPLY_NAME
     */
    public String getApplyName() {
        return (String) getAttributeInternal(APPLYNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for APPLY_NAME using the alias name ApplyName.
     * @param value value to set the APPLY_NAME
     */
    public void setApplyName(String value) {
        setAttributeInternal(APPLYNAME, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE1 using the alias name Attribute1.
     * @return the ATTRIBUTE1
     */
    public String getAttribute1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE1 using the alias name Attribute1.
     * @param value value to set the ATTRIBUTE1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE10 using the alias name Attribute10.
     * @return the ATTRIBUTE10
     */
    public String getAttribute10() {
        return (String) getAttributeInternal(ATTRIBUTE10);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE10 using the alias name Attribute10.
     * @param value value to set the ATTRIBUTE10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE11 using the alias name Attribute11.
     * @return the ATTRIBUTE11
     */
    public String getAttribute11() {
        return (String) getAttributeInternal(ATTRIBUTE11);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE11 using the alias name Attribute11.
     * @param value value to set the ATTRIBUTE11
     */
    public void setAttribute11(String value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE12 using the alias name Attribute12.
     * @return the ATTRIBUTE12
     */
    public String getAttribute12() {
        return (String) getAttributeInternal(ATTRIBUTE12);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE12 using the alias name Attribute12.
     * @param value value to set the ATTRIBUTE12
     */
    public void setAttribute12(String value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE13 using the alias name Attribute13.
     * @return the ATTRIBUTE13
     */
    public String getAttribute13() {
        return (String) getAttributeInternal(ATTRIBUTE13);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE13 using the alias name Attribute13.
     * @param value value to set the ATTRIBUTE13
     */
    public void setAttribute13(String value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE14 using the alias name Attribute14.
     * @return the ATTRIBUTE14
     */
    public String getAttribute14() {
        return (String) getAttributeInternal(ATTRIBUTE14);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE14 using the alias name Attribute14.
     * @param value value to set the ATTRIBUTE14
     */
    public void setAttribute14(String value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE15 using the alias name Attribute15.
     * @return the ATTRIBUTE15
     */
    public String getAttribute15() {
        return (String) getAttributeInternal(ATTRIBUTE15);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE15 using the alias name Attribute15.
     * @param value value to set the ATTRIBUTE15
     */
    public void setAttribute15(String value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE2 using the alias name Attribute2.
     * @return the ATTRIBUTE2
     */
    public String getAttribute2() {
        return (String) getAttributeInternal(ATTRIBUTE2);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE2 using the alias name Attribute2.
     * @param value value to set the ATTRIBUTE2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE3 using the alias name Attribute3.
     * @return the ATTRIBUTE3
     */
    public String getAttribute3() {
        return (String) getAttributeInternal(ATTRIBUTE3);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE3 using the alias name Attribute3.
     * @param value value to set the ATTRIBUTE3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE4 using the alias name Attribute4.
     * @return the ATTRIBUTE4
     */
    public String getAttribute4() {
        return (String) getAttributeInternal(ATTRIBUTE4);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE4 using the alias name Attribute4.
     * @param value value to set the ATTRIBUTE4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE5 using the alias name Attribute5.
     * @return the ATTRIBUTE5
     */
    public String getAttribute5() {
        return (String) getAttributeInternal(ATTRIBUTE5);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE5 using the alias name Attribute5.
     * @param value value to set the ATTRIBUTE5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE6 using the alias name Attribute6.
     * @return the ATTRIBUTE6
     */
    public String getAttribute6() {
        return (String) getAttributeInternal(ATTRIBUTE6);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE6 using the alias name Attribute6.
     * @param value value to set the ATTRIBUTE6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE7 using the alias name Attribute7.
     * @return the ATTRIBUTE7
     */
    public String getAttribute7() {
        return (String) getAttributeInternal(ATTRIBUTE7);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE7 using the alias name Attribute7.
     * @param value value to set the ATTRIBUTE7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE8 using the alias name Attribute8.
     * @return the ATTRIBUTE8
     */
    public String getAttribute8() {
        return (String) getAttributeInternal(ATTRIBUTE8);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE8 using the alias name Attribute8.
     * @param value value to set the ATTRIBUTE8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE9 using the alias name Attribute9.
     * @return the ATTRIBUTE9
     */
    public String getAttribute9() {
        return (String) getAttributeInternal(ATTRIBUTE9);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTRIBUTE9 using the alias name Attribute9.
     * @param value value to set the ATTRIBUTE9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**
     * Gets the attribute value for CREATED_BY using the alias name CreatedBy.
     * @return the CREATED_BY
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for CREATED_BY using the alias name CreatedBy.
     * @param value value to set the CREATED_BY
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CREATION_DATE using the alias name CreationDate.
     * @return the CREATION_DATE
     */
    public Date getCreationDate() {
        return (Date) getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for CREATION_DATE using the alias name CreationDate.
     * @param value value to set the CREATION_DATE
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate.
     * @return the LAST_UPDATE_DATE
     */
    public Date getLastUpdateDate() {
        return (Date) getAttributeInternal(LASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate.
     * @param value value to set the LAST_UPDATE_DATE
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy.
     * @return the LAST_UPDATED_BY
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy.
     * @param value value to set the LAST_UPDATED_BY
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for OBJECT_VERSION_NUMBER using the alias name ObjectVersionNumber.
     * @return the OBJECT_VERSION_NUMBER
     */
    public Number getObjectVersionNumber() {
        return (Number) getAttributeInternal(OBJECTVERSIONNUMBER);
    }

    /**
     * Sets <code>value</code> as attribute value for OBJECT_VERSION_NUMBER using the alias name ObjectVersionNumber.
     * @param value value to set the OBJECT_VERSION_NUMBER
     */
    public void setObjectVersionNumber(Number value) {
        setAttributeInternal(OBJECTVERSIONNUMBER, value);
    }

    /**
     * Gets the attribute value for TEMPLATE_ID using the alias name TemplateId.
     * @return the TEMPLATE_ID
     */
    public Number getTemplateId() {
        return (Number) getAttributeInternal(TEMPLATEID);
    }

    /**
     * Sets <code>value</code> as attribute value for TEMPLATE_ID using the alias name TemplateId.
     * @param value value to set the TEMPLATE_ID
     */
    public void setTemplateId(Number value) {
        setAttributeInternal(TEMPLATEID, value);
    }

    /**
     * Gets the attribute value for USER_ID using the alias name UserId.
     * @return the USER_ID
     */
    public Number getUserId() {
        return (Number)getAttributeInternal(USERID);
    }

    /**
     * Sets <code>value</code> as attribute value for USER_ID using the alias name UserId.
     * @param value value to set the USER_ID
     */
    public void setUserId(Number value) {
        setAttributeInternal(USERID, value);
    }

    /**
     * Gets the attribute value for COMP_CODE using the alias name CompCode.
     * @return the COMP_CODE
     */
    public String getCompCode() {
        return (String) getAttributeInternal(COMPCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for COMP_CODE using the alias name CompCode.
     * @param value value to set the COMP_CODE
     */
    public void setCompCode(String value) {
        setAttributeInternal(COMPCODE, value);
    }

    /**
     * Gets the attribute value for BATCH_CODE using the alias name BatchCode.
     * @return the BATCH_CODE
     */
    public String getBatchCode() {
        return (String) getAttributeInternal(BATCHCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for BATCH_CODE using the alias name BatchCode.
     * @param value value to set the BATCH_CODE
     */
    public void setBatchCode(String value) {
        setAttributeInternal(BATCHCODE, value);
    }

    /**
     * Gets the attribute value for STATUS using the alias name Status.
     * @return the STATUS
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for STATUS using the alias name Status.
     * @param value value to set the STATUS
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for REMARKS using the alias name Remarks.
     * @return the REMARKS
     */
    public String getRemarks() {
        return (String) getAttributeInternal(REMARKS);
    }

    /**
     * Sets <code>value</code> as attribute value for REMARKS using the alias name Remarks.
     * @param value value to set the REMARKS
     */
    public void setRemarks(String value) {
        setAttributeInternal(REMARKS, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link CmsResExpApplyLTVO.
     */
    public RowIterator getCmsResExpApplyLTVO() {
        return (RowIterator)getAttributeInternal(CMSRESEXPAPPLYLTVO);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
