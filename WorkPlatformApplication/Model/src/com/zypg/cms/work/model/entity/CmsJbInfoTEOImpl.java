package com.zypg.cms.work.model.entity;

import com.honythink.applicationframework.hadf.CustomEntityImpl;

import java.math.BigDecimal;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.ClobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 06 12:34:26 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsJbInfoTEOImpl extends CustomEntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        JbInfoId {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getJbInfoId();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setJbInfoId((Number)value);
            }
        }
        ,
        BriefIntroduction {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getBriefIntroduction();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setBriefIntroduction((ClobDomain)value);
            }
        }
        ,
        Catalog {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getCatalog();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setCatalog((ClobDomain)value);
            }
        }
        ,
        Foreword {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getForeword();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setForeword((ClobDomain)value);
            }
        }
        ,
        Postscript {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getPostscript();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setPostscript((ClobDomain)value);
            }
        }
        ,
        Highlights {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getHighlights();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setHighlights((ClobDomain)value);
            }
        }
        ,
        Preface {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getPreface();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setPreface((ClobDomain)value);
            }
        }
        ,
        Glossary {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getGlossary();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setGlossary((ClobDomain)value);
            }
        }
        ,
        References1 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getReferences1();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setReferences1((ClobDomain)value);
            }
        }
        ,
        AuthorIntroduction {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAuthorIntroduction();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAuthorIntroduction((ClobDomain)value);
            }
        }
        ,
        ObjectVersionNumber {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getObjectVersionNumber();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreatedBy {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        Attribute1 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute1();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute1((String)value);
            }
        }
        ,
        Attribute2 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute2();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute2((String)value);
            }
        }
        ,
        Attribute3 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute3();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute3((String)value);
            }
        }
        ,
        Attribute4 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute4();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute4((String)value);
            }
        }
        ,
        Attribute5 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute5();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute5((String)value);
            }
        }
        ,
        Attribute6 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute6();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute6((String)value);
            }
        }
        ,
        Attribute7 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute7();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute7((String)value);
            }
        }
        ,
        Attribute8 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute8();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute8((String)value);
            }
        }
        ,
        Attribute9 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute9();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute9((String)value);
            }
        }
        ,
        Attribute10 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute10();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute10((String)value);
            }
        }
        ,
        Attribute11 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute11();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute11((String)value);
            }
        }
        ,
        Attribute12 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute12();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute12((String)value);
            }
        }
        ,
        Attribute13 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute13();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute13((String)value);
            }
        }
        ,
        Attribute14 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute14();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute14((String)value);
            }
        }
        ,
        Attribute15 {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getAttribute15();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setAttribute15((String)value);
            }
        }
        ,
        DocId {
            public Object get(CmsJbInfoTEOImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsJbInfoTEOImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsJbInfoTEOImpl object);

        public abstract void put(CmsJbInfoTEOImpl object, Object value);

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


    public static final int JBINFOID = AttributesEnum.JbInfoId.index();
    public static final int BRIEFINTRODUCTION = AttributesEnum.BriefIntroduction.index();
    public static final int CATALOG = AttributesEnum.Catalog.index();
    public static final int FOREWORD = AttributesEnum.Foreword.index();
    public static final int POSTSCRIPT = AttributesEnum.Postscript.index();
    public static final int HIGHLIGHTS = AttributesEnum.Highlights.index();
    public static final int PREFACE = AttributesEnum.Preface.index();
    public static final int GLOSSARY = AttributesEnum.Glossary.index();
    public static final int REFERENCES1 = AttributesEnum.References1.index();
    public static final int AUTHORINTRODUCTION = AttributesEnum.AuthorIntroduction.index();
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
    public static final int DOCID = AttributesEnum.DocId.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsJbInfoTEOImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.zypg.cms.work.model.entity.CmsJbInfoTEO");
    }

    /**
     * Gets the attribute value for JbInfoId, using the alias name JbInfoId.
     * @return the value of JbInfoId
     */
    public Number getJbInfoId() {
        return (Number)getAttributeInternal(JBINFOID);
    }

    /**
     * Sets <code>value</code> as the attribute value for JbInfoId.
     * @param value value to set the JbInfoId
     */
    public void setJbInfoId(Number value) {
        setAttributeInternal(JBINFOID, value);
    }

    /**
     * Gets the attribute value for BriefIntroduction, using the alias name BriefIntroduction.
     * @return the value of BriefIntroduction
     */
    public ClobDomain getBriefIntroduction() {
        return (ClobDomain)getAttributeInternal(BRIEFINTRODUCTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for BriefIntroduction.
     * @param value value to set the BriefIntroduction
     */
    public void setBriefIntroduction(ClobDomain value) {
        setAttributeInternal(BRIEFINTRODUCTION, value);
    }

    /**
     * Gets the attribute value for Catalog, using the alias name Catalog.
     * @return the value of Catalog
     */
    public ClobDomain getCatalog() {
        return (ClobDomain)getAttributeInternal(CATALOG);
    }

    /**
     * Sets <code>value</code> as the attribute value for Catalog.
     * @param value value to set the Catalog
     */
    public void setCatalog(ClobDomain value) {
        setAttributeInternal(CATALOG, value);
    }

    /**
     * Gets the attribute value for Foreword, using the alias name Foreword.
     * @return the value of Foreword
     */
    public ClobDomain getForeword() {
        return (ClobDomain)getAttributeInternal(FOREWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Foreword.
     * @param value value to set the Foreword
     */
    public void setForeword(ClobDomain value) {
        setAttributeInternal(FOREWORD, value);
    }

    /**
     * Gets the attribute value for Postscript, using the alias name Postscript.
     * @return the value of Postscript
     */
    public ClobDomain getPostscript() {
        return (ClobDomain)getAttributeInternal(POSTSCRIPT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Postscript.
     * @param value value to set the Postscript
     */
    public void setPostscript(ClobDomain value) {
        setAttributeInternal(POSTSCRIPT, value);
    }

    /**
     * Gets the attribute value for Highlights, using the alias name Highlights.
     * @return the value of Highlights
     */
    public ClobDomain getHighlights() {
        return (ClobDomain)getAttributeInternal(HIGHLIGHTS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Highlights.
     * @param value value to set the Highlights
     */
    public void setHighlights(ClobDomain value) {
        setAttributeInternal(HIGHLIGHTS, value);
    }

    /**
     * Gets the attribute value for Preface, using the alias name Preface.
     * @return the value of Preface
     */
    public ClobDomain getPreface() {
        return (ClobDomain)getAttributeInternal(PREFACE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Preface.
     * @param value value to set the Preface
     */
    public void setPreface(ClobDomain value) {
        setAttributeInternal(PREFACE, value);
    }

    /**
     * Gets the attribute value for Glossary, using the alias name Glossary.
     * @return the value of Glossary
     */
    public ClobDomain getGlossary() {
        return (ClobDomain)getAttributeInternal(GLOSSARY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Glossary.
     * @param value value to set the Glossary
     */
    public void setGlossary(ClobDomain value) {
        setAttributeInternal(GLOSSARY, value);
    }

    /**
     * Gets the attribute value for References1, using the alias name References1.
     * @return the value of References1
     */
    public ClobDomain getReferences1() {
        return (ClobDomain)getAttributeInternal(REFERENCES1);
    }

    /**
     * Sets <code>value</code> as the attribute value for References1.
     * @param value value to set the References1
     */
    public void setReferences1(ClobDomain value) {
        setAttributeInternal(REFERENCES1, value);
    }

    /**
     * Gets the attribute value for AuthorIntroduction, using the alias name AuthorIntroduction.
     * @return the value of AuthorIntroduction
     */
    public ClobDomain getAuthorIntroduction() {
        return (ClobDomain)getAttributeInternal(AUTHORINTRODUCTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for AuthorIntroduction.
     * @param value value to set the AuthorIntroduction
     */
    public void setAuthorIntroduction(ClobDomain value) {
        setAttributeInternal(AUTHORINTRODUCTION, value);
    }

    /**
     * Gets the attribute value for ObjectVersionNumber, using the alias name ObjectVersionNumber.
     * @return the value of ObjectVersionNumber
     */
    public Number getObjectVersionNumber() {
        return (Number)getAttributeInternal(OBJECTVERSIONNUMBER);
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
     * Gets the attribute value for DocId, using the alias name DocId.
     * @return the value of DocId
     */
    public Number getDocId() {
        return (Number)getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocId.
     * @param value value to set the DocId
     */
    public void setDocId(Number value) {
        setAttributeInternal(DOCID, value);
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
     * @param jbInfoId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number jbInfoId) {
        return new Key(new Object[]{jbInfoId});
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        SequenceImpl si = new SequenceImpl("CMS_JB_INFO_S", this.getDBTransaction());
        this.setJbInfoId(si.getSequenceNumber());
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }
}
