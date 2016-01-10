package com.zypg.cms.work.model.entity;


import com.honythink.applicationframework.hadf.CustomEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 27 11:59:42 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCompanyCopyrightTEOImpl extends CustomEntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        CompanyId {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCompanyId();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCompanyId((Number)value);
            }
        }
        ,
        CompanyName {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCompanyName();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCompanyName((String)value);
            }
        }
        ,
        CompanyAddr {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCompanyAddr();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCompanyAddr((String)value);
            }
        }
        ,
        CompanyContact {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCompanyContact();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCompanyContact((String)value);
            }
        }
        ,
        CompanyPhone {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCompanyPhone();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCompanyPhone((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        ObjectVersionNumber {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getObjectVersionNumber();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setObjectVersionNumber((Number)value);
            }
        }
        ,
        CreatedBy {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        Attribute1 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute1();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute1((String)value);
            }
        }
        ,
        Attribute2 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute2();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute2((String)value);
            }
        }
        ,
        Attribute3 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute3();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute3((String)value);
            }
        }
        ,
        Attribute4 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute4();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute4((String)value);
            }
        }
        ,
        Attribute5 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute5();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute5((String)value);
            }
        }
        ,
        Attribute6 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute6();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute6((String)value);
            }
        }
        ,
        Attribute7 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute7();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute7((String)value);
            }
        }
        ,
        Attribute8 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute8();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute8((String)value);
            }
        }
        ,
        Attribute9 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute9();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute9((String)value);
            }
        }
        ,
        Attribute10 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute10();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute10((String)value);
            }
        }
        ,
        Attribute11 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute11();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute11((String)value);
            }
        }
        ,
        Attribute12 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute12();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute12((String)value);
            }
        }
        ,
        Attribute13 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute13();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute13((String)value);
            }
        }
        ,
        Attribute14 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute14();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute14((String)value);
            }
        }
        ,
        Attribute15 {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getAttribute15();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setAttribute15((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsCompanyCopyrightTEOImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsCompanyCopyrightTEOImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsCompanyCopyrightTEOImpl object);

        public abstract void put(CmsCompanyCopyrightTEOImpl object, Object value);

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
    public static final int OBJECTVERSIONNUMBER = AttributesEnum.ObjectVersionNumber.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int ATTRIBUTE1 = AttributesEnum.Attribute1.index();
    public static final int ATTRIBUTE2 = AttributesEnum.Attribute2.index();
    public static final int ATTRIBUTE3 = AttributesEnum.Attribute3.index();
    public static final int ATTRIBUTE4 = AttributesEnum.Attribute4.index();
    public static final int ATTRIBUTE5 = AttributesEnum.Attribute5.index();
    public static final int ATTRIBUTE6 = AttributesEnum.Attribute6.index();
    public static final int ATTRIBUTE7 = AttributesEnum.Attribute7.index();
    public static final int ATTRIBUTE8 = AttributesEnum.Attribute8.index();
    public static final int ATTRIBUTE9 = AttributesEnum.Attribute9.index();
    public static final int ATTRIBUTE10 = AttributesEnum.Attribute10.index();
    public static final int ATTRIBUTE11 = AttributesEnum.Attribute11.index();
    public static final int ATTRIBUTE12 = AttributesEnum.Attribute12.index();
    public static final int ATTRIBUTE13 = AttributesEnum.Attribute13.index();
    public static final int ATTRIBUTE14 = AttributesEnum.Attribute14.index();
    public static final int ATTRIBUTE15 = AttributesEnum.Attribute15.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsCompanyCopyrightTEOImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.zypg.cms.work.model.entity.CmsCompanyCopyrightTEO");
    }

    /**
     * Gets the attribute value for CompanyId, using the alias name CompanyId.
     * @return the value of CompanyId
     */
    public oracle.jbo.domain.Number getCompanyId() {
        return (oracle.jbo.domain.Number)getAttributeInternal(COMPANYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CompanyId.
     * @param value value to set the CompanyId
     */
    public void setCompanyId(oracle.jbo.domain.Number value) {
        setAttributeInternal(COMPANYID, value);
    }

    /**
     * Gets the attribute value for CompanyName, using the alias name CompanyName.
     * @return the value of CompanyName
     */
    public String getCompanyName() {
        return (String)getAttributeInternal(COMPANYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for CompanyName.
     * @param value value to set the CompanyName
     */
    public void setCompanyName(String value) {
        setAttributeInternal(COMPANYNAME, value);
    }

    /**
     * Gets the attribute value for CompanyAddr, using the alias name CompanyAddr.
     * @return the value of CompanyAddr
     */
    public String getCompanyAddr() {
        return (String)getAttributeInternal(COMPANYADDR);
    }

    /**
     * Sets <code>value</code> as the attribute value for CompanyAddr.
     * @param value value to set the CompanyAddr
     */
    public void setCompanyAddr(String value) {
        setAttributeInternal(COMPANYADDR, value);
    }

    /**
     * Gets the attribute value for CompanyContact, using the alias name CompanyContact.
     * @return the value of CompanyContact
     */
    public String getCompanyContact() {
        return (String)getAttributeInternal(COMPANYCONTACT);
    }

    /**
     * Sets <code>value</code> as the attribute value for CompanyContact.
     * @param value value to set the CompanyContact
     */
    public void setCompanyContact(String value) {
        setAttributeInternal(COMPANYCONTACT, value);
    }

    /**
     * Gets the attribute value for CompanyPhone, using the alias name CompanyPhone.
     * @return the value of CompanyPhone
     */
    public String getCompanyPhone() {
        return (String)getAttributeInternal(COMPANYPHONE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CompanyPhone.
     * @param value value to set the CompanyPhone
     */
    public void setCompanyPhone(String value) {
        setAttributeInternal(COMPANYPHONE, value);
    }

    /**
     * Gets the attribute value for Remarks, using the alias name Remarks.
     * @return the value of Remarks
     */
    public String getRemarks() {
        return (String)getAttributeInternal(REMARKS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Remarks.
     * @param value value to set the Remarks
     */
    public void setRemarks(String value) {
        setAttributeInternal(REMARKS, value);
    }

    /**
     * Gets the attribute value for ObjectVersionNumber, using the alias name ObjectVersionNumber.
     * @return the value of ObjectVersionNumber
     */
    public Number getObjectVersionNumber() {
        return (Number)getAttributeInternal(OBJECTVERSIONNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for ObjectVersionNumber.
     * @param value value to set the ObjectVersionNumber
     */
    public void setObjectVersionNumber(Number value) {
        setAttributeInternal(OBJECTVERSIONNUMBER, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CreationDate, using the alias name CreationDate.
     * @return the value of CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreationDate.
     * @param value value to set the CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy.
     * @return the value of LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedBy.
     * @param value value to set the LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate.
     * @return the value of LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdateDate.
     * @param value value to set the LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for Attribute1, using the alias name Attribute1.
     * @return the value of Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute1.
     * @param value value to set the Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**
     * Gets the attribute value for Attribute2, using the alias name Attribute2.
     * @return the value of Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute2.
     * @param value value to set the Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**
     * Gets the attribute value for Attribute3, using the alias name Attribute3.
     * @return the value of Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute3.
     * @param value value to set the Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**
     * Gets the attribute value for Attribute4, using the alias name Attribute4.
     * @return the value of Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute4.
     * @param value value to set the Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**
     * Gets the attribute value for Attribute5, using the alias name Attribute5.
     * @return the value of Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute5.
     * @param value value to set the Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**
     * Gets the attribute value for Attribute6, using the alias name Attribute6.
     * @return the value of Attribute6
     */
    public String getAttribute6() {
        return (String)getAttributeInternal(ATTRIBUTE6);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute6.
     * @param value value to set the Attribute6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**
     * Gets the attribute value for Attribute7, using the alias name Attribute7.
     * @return the value of Attribute7
     */
    public String getAttribute7() {
        return (String)getAttributeInternal(ATTRIBUTE7);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute7.
     * @param value value to set the Attribute7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**
     * Gets the attribute value for Attribute8, using the alias name Attribute8.
     * @return the value of Attribute8
     */
    public String getAttribute8() {
        return (String)getAttributeInternal(ATTRIBUTE8);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute8.
     * @param value value to set the Attribute8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**
     * Gets the attribute value for Attribute9, using the alias name Attribute9.
     * @return the value of Attribute9
     */
    public String getAttribute9() {
        return (String)getAttributeInternal(ATTRIBUTE9);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute9.
     * @param value value to set the Attribute9
     */
    public void setAttribute9(String value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**
     * Gets the attribute value for Attribute10, using the alias name Attribute10.
     * @return the value of Attribute10
     */
    public String getAttribute10() {
        return (String)getAttributeInternal(ATTRIBUTE10);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute10.
     * @param value value to set the Attribute10
     */
    public void setAttribute10(String value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**
     * Gets the attribute value for Attribute11, using the alias name Attribute11.
     * @return the value of Attribute11
     */
    public String getAttribute11() {
        return (String)getAttributeInternal(ATTRIBUTE11);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute11.
     * @param value value to set the Attribute11
     */
    public void setAttribute11(String value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**
     * Gets the attribute value for Attribute12, using the alias name Attribute12.
     * @return the value of Attribute12
     */
    public String getAttribute12() {
        return (String)getAttributeInternal(ATTRIBUTE12);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute12.
     * @param value value to set the Attribute12
     */
    public void setAttribute12(String value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**
     * Gets the attribute value for Attribute13, using the alias name Attribute13.
     * @return the value of Attribute13
     */
    public String getAttribute13() {
        return (String)getAttributeInternal(ATTRIBUTE13);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute13.
     * @param value value to set the Attribute13
     */
    public void setAttribute13(String value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**
     * Gets the attribute value for Attribute14, using the alias name Attribute14.
     * @return the value of Attribute14
     */
    public String getAttribute14() {
        return (String)getAttributeInternal(ATTRIBUTE14);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute14.
     * @param value value to set the Attribute14
     */
    public void setAttribute14(String value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**
     * Gets the attribute value for Attribute15, using the alias name Attribute15.
     * @return the value of Attribute15
     */
    public String getAttribute15() {
        return (String)getAttributeInternal(ATTRIBUTE15);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute15.
     * @param value value to set the Attribute15
     */
    public void setAttribute15(String value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**
     * Gets the attribute value for CompCode, using the alias name CompCode.
     * @return the value of CompCode
     */
    public String getCompCode() {
        return (String)getAttributeInternal(COMPCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CompCode.
     * @param value value to set the CompCode
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


    /**
     * @param companyId key constituent
     * @param creationDate key constituent
     * @param lastUpdateDate key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number companyId, Date creationDate, Date lastUpdateDate) {
        return new Key(new Object[]{companyId, creationDate, lastUpdateDate});
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        SequenceImpl si = new SequenceImpl("CMS_COMPANY_COPYRIGHT_S", this.getDBTransaction());
        this.setCompanyId(si.getSequenceNumber());
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }
}
