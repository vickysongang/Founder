package com.zypg.cms.work.model.viewobject.common.query;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jun 20 10:05:55 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCategoryVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        CategoryId {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getCategoryId();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setCategoryId((Number)value);
            }
        }
        ,
        CategoryName {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getCategoryName();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setCategoryName((String)value);
            }
        }
        ,
        CategoryDisplayName {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getCategoryDisplayName();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setCategoryDisplayName((String)value);
            }
        }
        ,
        ParentNodeType {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getParentNodeType();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setParentNodeType((String)value);
            }
        }
        ,
        ParentNodeId {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getParentNodeId();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setParentNodeId((Number)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        Seq {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getSeq();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setSeq((Number)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        LibTypeCode {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getLibTypeCode();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setLibTypeCode((String)value);
            }
        }
        ,
        PrimaryFlag {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getPrimaryFlag();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setPrimaryFlag((String)value);
            }
        }
        ,
        CategoryGroupCode {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getCategoryGroupCode();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setCategoryGroupCode((String)value);
            }
        }
        ,
        CategoryIdCmsCategoryVO {
            public Object get(CmsCategoryVORowImpl obj) {
                return obj.getCategoryIdCmsCategoryVO();
            }

            public void put(CmsCategoryVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsCategoryVORowImpl object);

        public abstract void put(CmsCategoryVORowImpl object, Object value);

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
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int CATEGORYNAME = AttributesEnum.CategoryName.index();
    public static final int CATEGORYDISPLAYNAME = AttributesEnum.CategoryDisplayName.index();
    public static final int PARENTNODETYPE = AttributesEnum.ParentNodeType.index();
    public static final int PARENTNODEID = AttributesEnum.ParentNodeId.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int SEQ = AttributesEnum.Seq.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int LIBTYPECODE = AttributesEnum.LibTypeCode.index();
    public static final int PRIMARYFLAG = AttributesEnum.PrimaryFlag.index();
    public static final int CATEGORYGROUPCODE = AttributesEnum.CategoryGroupCode.index();
    public static final int CATEGORYIDCMSCATEGORYVO = AttributesEnum.CategoryIdCmsCategoryVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsCategoryVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryId.
     * @return the CategoryId
     */
    public Number getCategoryId() {
        return (Number) getAttributeInternal(CATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryId.
     * @param value value to set the  CategoryId
     */
    public void setCategoryId(Number value) {
        setAttributeInternal(CATEGORYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryName.
     * @return the CategoryName
     */
    public String getCategoryName() {
        return (String) getAttributeInternal(CATEGORYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryName.
     * @param value value to set the  CategoryName
     */
    public void setCategoryName(String value) {
        setAttributeInternal(CATEGORYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryDisplayName.
     * @return the CategoryDisplayName
     */
    public String getCategoryDisplayName() {
        return (String) getAttributeInternal(CATEGORYDISPLAYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryDisplayName.
     * @param value value to set the  CategoryDisplayName
     */
    public void setCategoryDisplayName(String value) {
        setAttributeInternal(CATEGORYDISPLAYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ParentNodeType.
     * @return the ParentNodeType
     */
    public String getParentNodeType() {
        return (String) getAttributeInternal(PARENTNODETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ParentNodeType.
     * @param value value to set the  ParentNodeType
     */
    public void setParentNodeType(String value) {
        setAttributeInternal(PARENTNODETYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ParentNodeId.
     * @return the ParentNodeId
     */
    public Number getParentNodeId() {
        return (Number) getAttributeInternal(PARENTNODEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ParentNodeId.
     * @param value value to set the  ParentNodeId
     */
    public void setParentNodeId(Number value) {
        setAttributeInternal(PARENTNODEID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompCode.
     * @return the CompCode
     */
    public String getCompCode() {
        return (String) getAttributeInternal(COMPCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompCode.
     * @param value value to set the  CompCode
     */
    public void setCompCode(String value) {
        setAttributeInternal(COMPCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Seq.
     * @return the Seq
     */
    public Number getSeq() {
        return (Number) getAttributeInternal(SEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Seq.
     * @param value value to set the  Seq
     */
    public void setSeq(Number value) {
        setAttributeInternal(SEQ, value);
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
     * Gets the attribute value for the calculated attribute LibTypeCode.
     * @return the LibTypeCode
     */
    public String getLibTypeCode() {
        return (String) getAttributeInternal(LIBTYPECODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LibTypeCode.
     * @param value value to set the  LibTypeCode
     */
    public void setLibTypeCode(String value) {
        setAttributeInternal(LIBTYPECODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PrimaryFlag.
     * @return the PrimaryFlag
     */
    public String getPrimaryFlag() {
        return (String) getAttributeInternal(PRIMARYFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PrimaryFlag.
     * @param value value to set the  PrimaryFlag
     */
    public void setPrimaryFlag(String value) {
        setAttributeInternal(PRIMARYFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryGroupCode.
     * @return the CategoryGroupCode
     */
    public String getCategoryGroupCode() {
        return (String) getAttributeInternal(CATEGORYGROUPCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryGroupCode.
     * @param value value to set the  CategoryGroupCode
     */
    public void setCategoryGroupCode(String value) {
        setAttributeInternal(CATEGORYGROUPCODE, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link CategoryIdCmsCategoryVO.
     */
    public RowIterator getCategoryIdCmsCategoryVO() {
        return (RowIterator)getAttributeInternal(CATEGORYIDCMSCATEGORYVO);
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
