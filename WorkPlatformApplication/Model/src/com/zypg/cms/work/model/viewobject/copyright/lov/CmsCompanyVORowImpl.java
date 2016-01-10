package com.zypg.cms.work.model.viewobject.copyright.lov;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jun 12 12:04:17 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCompanyVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        CompanyId {
            public Object get(CmsCompanyVORowImpl obj) {
                return obj.getCompanyId();
            }

            public void put(CmsCompanyVORowImpl obj, Object value) {
                obj.setCompanyId((Number)value);
            }
        }
        ,
        CompanyName {
            public Object get(CmsCompanyVORowImpl obj) {
                return obj.getCompanyName();
            }

            public void put(CmsCompanyVORowImpl obj, Object value) {
                obj.setCompanyName((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsCompanyVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsCompanyVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsCompanyVORowImpl object);

        public abstract void put(CmsCompanyVORowImpl object, Object value);

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
    public static final int COMPANYID = AttributesEnum.CompanyId.index();
    public static final int COMPANYNAME = AttributesEnum.CompanyName.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsCompanyVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute CompanyId.
     * @return the CompanyId
     */
    public Number getCompanyId() {
        return (Number) getAttributeInternal(COMPANYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompanyId.
     * @param value value to set the  CompanyId
     */
    public void setCompanyId(Number value) {
        setAttributeInternal(COMPANYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompanyName.
     * @return the CompanyName
     */
    public String getCompanyName() {
        return (String) getAttributeInternal(COMPANYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompanyName.
     * @param value value to set the  CompanyName
     */
    public void setCompanyName(String value) {
        setAttributeInternal(COMPANYNAME, value);
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
