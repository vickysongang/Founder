package com.zypg.cms.work.model.viewobject.copyright.query;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 27 14:01:43 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCompanyCopyrightVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        CompanyId {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getCompanyId();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setCompanyId((Number)value);
            }
        }
        ,
        CompanyName {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getCompanyName();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setCompanyName((String)value);
            }
        }
        ,
        CompanyAddr {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getCompanyAddr();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setCompanyAddr((String)value);
            }
        }
        ,
        CompanyContact {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getCompanyContact();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setCompanyContact((String)value);
            }
        }
        ,
        CompanyPhone {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getCompanyPhone();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setCompanyPhone((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsCompanyCopyrightVORowImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsCompanyCopyrightVORowImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsCompanyCopyrightVORowImpl object);

        public abstract void put(CmsCompanyCopyrightVORowImpl object, Object value);

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
    public static final int COMPANYADDR = AttributesEnum.CompanyAddr.index();
    public static final int COMPANYCONTACT = AttributesEnum.CompanyContact.index();
    public static final int COMPANYPHONE = AttributesEnum.CompanyPhone.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsCompanyCopyrightVORowImpl() {
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
     * Gets the attribute value for the calculated attribute CompanyAddr.
     * @return the CompanyAddr
     */
    public String getCompanyAddr() {
        return (String) getAttributeInternal(COMPANYADDR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompanyAddr.
     * @param value value to set the  CompanyAddr
     */
    public void setCompanyAddr(String value) {
        setAttributeInternal(COMPANYADDR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompanyContact.
     * @return the CompanyContact
     */
    public String getCompanyContact() {
        return (String) getAttributeInternal(COMPANYCONTACT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompanyContact.
     * @param value value to set the  CompanyContact
     */
    public void setCompanyContact(String value) {
        setAttributeInternal(COMPANYCONTACT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompanyPhone.
     * @return the CompanyPhone
     */
    public String getCompanyPhone() {
        return (String) getAttributeInternal(COMPANYPHONE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompanyPhone.
     * @param value value to set the  CompanyPhone
     */
    public void setCompanyPhone(String value) {
        setAttributeInternal(COMPANYPHONE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Remarks.
     * @return the Remarks
     */
    public String getRemarks() {
        return (String) getAttributeInternal(REMARKS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Remarks.
     * @param value value to set the  Remarks
     */
    public void setRemarks(String value) {
        setAttributeInternal(REMARKS, value);
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
     * Gets the attribute value for the calculated attribute LastUpdateDate.
     * @return the LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date) getAttributeInternal(LASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateDate.
     * @param value value to set the  LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
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
