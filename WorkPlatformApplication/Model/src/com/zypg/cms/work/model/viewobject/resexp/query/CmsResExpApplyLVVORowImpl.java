package com.zypg.cms.work.model.viewobject.resexp.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 23 16:34:15 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsResExpApplyLVVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        HeadId {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getHeadId();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setHeadId((Number)value);
            }
        }
        ,
        LineId {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getLineId();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setLineId((Number)value);
            }
        }
        ,
        ShelfId {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getShelfId();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setShelfId((Number)value);
            }
        }
        ,
        ResId {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getResId();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setResId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        ResName {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getResName();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setResName((String)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        UserId {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getUserId();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setUserId((Number)value);
            }
        }
        ,
        DeleteFlag {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getDeleteFlag();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setDeleteFlag((String)value);
            }
        }
        ,
        ApplySubmitFlag {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getApplySubmitFlag();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setApplySubmitFlag((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        Author {
            public Object get(CmsResExpApplyLVVORowImpl obj) {
                return obj.getAuthor();
            }

            public void put(CmsResExpApplyLVVORowImpl obj, Object value) {
                obj.setAuthor((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsResExpApplyLVVORowImpl object);

        public abstract void put(CmsResExpApplyLVVORowImpl object, Object value);

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

    public static final int HEADID = AttributesEnum.HeadId.index();
    public static final int LINEID = AttributesEnum.LineId.index();
    public static final int SHELFID = AttributesEnum.ShelfId.index();
    public static final int RESID = AttributesEnum.ResId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int RESNAME = AttributesEnum.ResName.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int USERID = AttributesEnum.UserId.index();
    public static final int DELETEFLAG = AttributesEnum.DeleteFlag.index();
    public static final int APPLYSUBMITFLAG = AttributesEnum.ApplySubmitFlag.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int AUTHOR = AttributesEnum.Author.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsResExpApplyLVVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute HeadId.
     * @return the HeadId
     */
    public Number getHeadId() {
        return (Number) getAttributeInternal(HEADID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute HeadId.
     * @param value value to set the  HeadId
     */
    public void setHeadId(Number value) {
        setAttributeInternal(HEADID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LineId.
     * @return the LineId
     */
    public Number getLineId() {
        return (Number) getAttributeInternal(LINEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LineId.
     * @param value value to set the  LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ShelfId.
     * @return the ShelfId
     */
    public Number getShelfId() {
        return (Number) getAttributeInternal(SHELFID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ShelfId.
     * @param value value to set the  ShelfId
     */
    public void setShelfId(Number value) {
        setAttributeInternal(SHELFID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ResId.
     * @return the ResId
     */
    public Number getResId() {
        return (Number) getAttributeInternal(RESID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ResId.
     * @param value value to set the  ResId
     */
    public void setResId(Number value) {
        setAttributeInternal(RESID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DocId.
     * @return the DocId
     */
    public Number getDocId() {
        return (Number) getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocId.
     * @param value value to set the  DocId
     */
    public void setDocId(Number value) {
        setAttributeInternal(DOCID, value);
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
     * Gets the attribute value for the calculated attribute UserId.
     * @return the UserId
     */
    public Number getUserId() {
        return (Number) getAttributeInternal(USERID);
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
