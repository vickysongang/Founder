package com.zypg.cms.work.model.viewobject.resexp.query;

import java.math.BigDecimal;

import java.util.Hashtable;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon May 19 14:51:21 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsResTempShelfVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        RealDocId {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getRealDocId();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setRealDocId((BigDecimal)value);
            }
        }
        ,
        ResId {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getResId();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setResId((BigDecimal)value);
            }
        }
        ,
        CategoryCode {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getCategoryCode();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setCategoryCode((String)value);
            }
        }
        ,
        UserId {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getUserId();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setUserId((Number)value);
            }
        }
        ,
        DeleteFlag {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getDeleteFlag();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setDeleteFlag((String)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        ApplySubmitFlag {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getApplySubmitFlag();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setApplySubmitFlag((String)value);
            }
        }
        ,
        ResName {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getResName();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setResName((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        Author {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getAuthor();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setAuthor((String)value);
            }
        }
        ,
        Checked {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getChecked();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setChecked((Boolean)value);
            }
        }
        ,
        Status {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        DocId {
            public Object get(CmsResTempShelfVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsResTempShelfVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsResTempShelfVORowImpl object);

        public abstract void put(CmsResTempShelfVORowImpl object, Object value);

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


    public static final int REALDOCID = AttributesEnum.RealDocId.index();
    public static final int RESID = AttributesEnum.ResId.index();
    public static final int CATEGORYCODE = AttributesEnum.CategoryCode.index();
    public static final int USERID = AttributesEnum.UserId.index();
    public static final int DELETEFLAG = AttributesEnum.DeleteFlag.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int APPLYSUBMITFLAG = AttributesEnum.ApplySubmitFlag.index();
    public static final int RESNAME = AttributesEnum.ResName.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int AUTHOR = AttributesEnum.Author.index();
    public static final int CHECKED = AttributesEnum.Checked.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int DOCID = AttributesEnum.DocId.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsResTempShelfVORowImpl() {
    }


    /**
     * Gets the attribute value for the calculated attribute RealDocId.
     * @return the RealDocId
     */
    public BigDecimal getRealDocId() {
        return (BigDecimal) getAttributeInternal(REALDOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RealDocId.
     * @param value value to set the  RealDocId
     */
    public void setRealDocId(BigDecimal value) {
        setAttributeInternal(REALDOCID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResId.
     * @return the ResId
     */
    public BigDecimal getResId() {
        return (BigDecimal) getAttributeInternal(RESID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResId.
     * @param value value to set the  ResId
     */
    public void setResId(BigDecimal value) {
        setAttributeInternal(RESID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DocId.
     * @return the DocId
     */
    public Number getDocId() {
        return (Number)getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocId.
     * @param value value to set the  DocId
     */
    public void setDocId(Number value) {
        setAttributeInternal(DOCID, value);
    }


    /**
     * Gets the attribute value for the calculated attribute CategoryCode.
     * @return the CategoryCode
     */
    public String getCategoryCode() {
        return (String) getAttributeInternal(CATEGORYCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryCode.
     * @param value value to set the  CategoryCode
     */
    public void setCategoryCode(String value) {
        setAttributeInternal(CATEGORYCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute UserId.
     * @return the UserId
     */
    public Number getUserId() {
        return (Number)getAttributeInternal(USERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute UserId.
     * @param value value to set the  UserId
     */
    public void setUserId(Number value) {
        setAttributeInternal(USERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DeleteFlag.
     * @return the DeleteFlag
     */
    public String getDeleteFlag() {
        return (String) getAttributeInternal(DELETEFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DeleteFlag.
     * @param value value to set the  DeleteFlag
     */
    public void setDeleteFlag(String value) {
        setAttributeInternal(DELETEFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CreationDate.
     * @return the CreationDate
     */
    public Date getCreationDate() {
        return (Date) getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CreationDate.
     * @param value value to set the  CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ApplySubmitFlag.
     * @return the ApplySubmitFlag
     */
    public String getApplySubmitFlag() {
        return (String) getAttributeInternal(APPLYSUBMITFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ApplySubmitFlag.
     * @param value value to set the  ApplySubmitFlag
     */
    public void setApplySubmitFlag(String value) {
        setAttributeInternal(APPLYSUBMITFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResName.
     * @return the ResName
     */
    public String getResName() {
        return (String) getAttributeInternal(RESNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResName.
     * @param value value to set the  ResName
     */
    public void setResName(String value) {
        setAttributeInternal(RESNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Keyword.
     * @return the Keyword
     */
    public String getKeyword() {
        return (String) getAttributeInternal(KEYWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Keyword.
     * @param value value to set the  Keyword
     */
    public void setKeyword(String value) {
        setAttributeInternal(KEYWORD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Author.
     * @return the Author
     */
    public String getAuthor() {
        return (String) getAttributeInternal(AUTHOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Author.
     * @param value value to set the  Author
     */
    public void setAuthor(String value) {
        setAttributeInternal(AUTHOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Checked.
     * @return the Checked
     */
    public Boolean getChecked() {
        Boolean result = null;
        Hashtable map = this.getDBTransaction().getSession().getUserData();
        String status = null;
        if ("Y".equals(this.getDeleteFlag())) {
            status = "DELETED_"+this.getLibCode();
        } else {
            status = this.getStatus();
        }
        String selectAllFlag = (String)map.get(status + "_selectAllFlag");
        String key = status + "_" + this.getDocId();
        List<String> currSelectedList = (List<String>)map.get(status + "_currSelected");
        if ("O".equals(selectAllFlag)) {
            if (currSelectedList.contains(key)) {
                result = true;
            } else {
                result = false;
            }
        } else {
            if ("Y".equals(selectAllFlag)) {
                result = true;
            } else if ("N".equals(selectAllFlag)) {
                result = false;
            }
        }
        this.setChecked(result);
        return (Boolean)getAttributeInternal(CHECKED);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Checked.
     * @param value value to set the  Checked
     */
    public void setChecked(Boolean value) {
        setAttributeInternal(CHECKED, value);
    }


    /**
     * Gets the attribute value for the calculated attribute Status.
     * @return the Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Status.
     * @param value value to set the  Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LibCode.
     * @return the LibCode
     */
    public String getLibCode() {
        return (String) getAttributeInternal(LIBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LibCode.
     * @param value value to set the  LibCode
     */
    public void setLibCode(String value) {
        setAttributeInternal(LIBCODE, value);
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
