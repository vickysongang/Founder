package com.zypg.cms.work.model.viewobject.common.query;

import java.math.BigDecimal;

import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat May 09 14:38:53 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsDocStatus4CheckRowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        StatusCode {
            public Object get(CmsDocStatus4CheckRowImpl obj) {
                return obj.getStatusCode();
            }

            public void put(CmsDocStatus4CheckRowImpl obj, Object value) {
                obj.setStatusCode((String)value);
            }
        }
        ,
        StatusName {
            public Object get(CmsDocStatus4CheckRowImpl obj) {
                return obj.getStatusName();
            }

            public void put(CmsDocStatus4CheckRowImpl obj, Object value) {
                obj.setStatusName((String)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsDocStatus4CheckRowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsDocStatus4CheckRowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        Seq {
            public Object get(CmsDocStatus4CheckRowImpl obj) {
                return obj.getSeq();
            }

            public void put(CmsDocStatus4CheckRowImpl obj, Object value) {
                obj.setSeq((BigDecimal)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsDocStatus4CheckRowImpl object);

        public abstract void put(CmsDocStatus4CheckRowImpl object, Object value);

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

    public static final int STATUSCODE = AttributesEnum.StatusCode.index();
    public static final int STATUSNAME = AttributesEnum.StatusName.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int SEQ = AttributesEnum.Seq.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsDocStatus4CheckRowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute StatusCode.
     * @return the StatusCode
     */
    public String getStatusCode() {
        return (String) getAttributeInternal(STATUSCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute StatusCode.
     * @param value value to set the  StatusCode
     */
    public void setStatusCode(String value) {
        setAttributeInternal(STATUSCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute StatusName.
     * @return the StatusName
     */
    public String getStatusName() {
        return (String) getAttributeInternal(STATUSNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute StatusName.
     * @param value value to set the  StatusName
     */
    public void setStatusName(String value) {
        setAttributeInternal(STATUSNAME, value);
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
     * Gets the attribute value for the calculated attribute Seq.
     * @return the Seq
     */
    public BigDecimal getSeq() {
        return (BigDecimal) getAttributeInternal(SEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Seq.
     * @param value value to set the  Seq
     */
    public void setSeq(BigDecimal value) {
        setAttributeInternal(SEQ, value);
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