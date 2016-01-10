package com.zypg.cms.work.model.entity;

import com.honythink.applicationframework.hadf.CustomEntityImpl;

import java.math.BigDecimal;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.NClobDomain;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Apr 24 14:59:33 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsTeachingPlanTEOImpl extends CustomEntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        TeachingPlanId {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getTeachingPlanId();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setTeachingPlanId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        SourceBookName {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getSourceBookName();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setSourceBookName((String)value);
            }
        }
        ,
        SourceBookIsbn {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getSourceBookIsbn();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setSourceBookIsbn((String)value);
            }
        }
        ,
        PublishingHouse {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getPublishingHouse();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setPublishingHouse((String)value);
            }
        }
        ,
        SourceBookType {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getSourceBookType();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setSourceBookType((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        Editor {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getEditor();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setEditor((String)value);
            }
        }
        ,
        TeachingPlanName {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getTeachingPlanName();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setTeachingPlanName((String)value);
            }
        }
        ,
        Producer {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getProducer();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setProducer((String)value);
            }
        }
        ,
        TeachingPlanType {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getTeachingPlanType();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setTeachingPlanType((String)value);
            }
        }
        ,
        CopyrightFlag {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getCopyrightFlag();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setCopyrightFlag((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        TeachingPlanStoreTime {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getTeachingPlanStoreTime();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setTeachingPlanStoreTime((Date)value);
            }
        }
        ,
        ObjectVersionNumber {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getObjectVersionNumber();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreatedBy {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        Attribute1 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute1();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute1((String)value);
            }
        }
        ,
        Attribute2 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute2();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute2((String)value);
            }
        }
        ,
        Attribute3 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute3();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute3((String)value);
            }
        }
        ,
        Attribute4 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute4();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute4((String)value);
            }
        }
        ,
        Attribute5 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute5();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute5((String)value);
            }
        }
        ,
        Attribute6 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute6();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute6((String)value);
            }
        }
        ,
        Attribute7 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute7();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute7((String)value);
            }
        }
        ,
        Attribute8 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute8();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute8((String)value);
            }
        }
        ,
        Attribute9 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute9();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute9((String)value);
            }
        }
        ,
        Attribute10 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute10();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute10((String)value);
            }
        }
        ,
        Attribute11 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute11();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute11((String)value);
            }
        }
        ,
        Attribute12 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute12();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute12((String)value);
            }
        }
        ,
        Attribute13 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute13();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute13((String)value);
            }
        }
        ,
        Attribute14 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute14();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute14((String)value);
            }
        }
        ,
        Attribute15 {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getAttribute15();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setAttribute15((String)value);
            }
        }
        ,
        SourceBookDocId {
            public Object get(CmsTeachingPlanTEOImpl obj) {
                return obj.getSourceBookDocId();
            }

            public void put(CmsTeachingPlanTEOImpl obj, Object value) {
                obj.setSourceBookDocId((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsTeachingPlanTEOImpl object);

        public abstract void put(CmsTeachingPlanTEOImpl object, Object value);

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


    public static final int TEACHINGPLANID = AttributesEnum.TeachingPlanId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int SOURCEBOOKNAME = AttributesEnum.SourceBookName.index();
    public static final int SOURCEBOOKISBN = AttributesEnum.SourceBookIsbn.index();
    public static final int PUBLISHINGHOUSE = AttributesEnum.PublishingHouse.index();
    public static final int SOURCEBOOKTYPE = AttributesEnum.SourceBookType.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int EDITOR = AttributesEnum.Editor.index();
    public static final int TEACHINGPLANNAME = AttributesEnum.TeachingPlanName.index();
    public static final int PRODUCER = AttributesEnum.Producer.index();
    public static final int TEACHINGPLANTYPE = AttributesEnum.TeachingPlanType.index();
    public static final int COPYRIGHTFLAG = AttributesEnum.CopyrightFlag.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int TEACHINGPLANSTORETIME = AttributesEnum.TeachingPlanStoreTime.index();
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
    public static final int SOURCEBOOKDOCID = AttributesEnum.SourceBookDocId.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsTeachingPlanTEOImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.zypg.cms.work.model.entity.CmsTeachingPlanTEO");
    }

    /**
     * Gets the attribute value for TeachingPlanId, using the alias name TeachingPlanId.
     * @return the value of TeachingPlanId
     */
    public Number getTeachingPlanId() {
        return (Number)getAttributeInternal(TEACHINGPLANID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TeachingPlanId.
     * @param value value to set the TeachingPlanId
     */
    public void setTeachingPlanId(Number value) {
        setAttributeInternal(TEACHINGPLANID, value);
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
     * Gets the attribute value for SourceBookName, using the alias name SourceBookName.
     * @return the value of SourceBookName
     */
    public String getSourceBookName() {
        return (String)getAttributeInternal(SOURCEBOOKNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceBookName.
     * @param value value to set the SourceBookName
     */
    public void setSourceBookName(String value) {
        setAttributeInternal(SOURCEBOOKNAME, value);
    }

    /**
     * Gets the attribute value for SourceBookIsbn, using the alias name SourceBookIsbn.
     * @return the value of SourceBookIsbn
     */
    public String getSourceBookIsbn() {
        return (String)getAttributeInternal(SOURCEBOOKISBN);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceBookIsbn.
     * @param value value to set the SourceBookIsbn
     */
    public void setSourceBookIsbn(String value) {
        setAttributeInternal(SOURCEBOOKISBN, value);
    }

    /**
     * Gets the attribute value for PublishingHouse, using the alias name PublishingHouse.
     * @return the value of PublishingHouse
     */
    public String getPublishingHouse() {
        return (String)getAttributeInternal(PUBLISHINGHOUSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for PublishingHouse.
     * @param value value to set the PublishingHouse
     */
    public void setPublishingHouse(String value) {
        setAttributeInternal(PUBLISHINGHOUSE, value);
    }

    /**
     * Gets the attribute value for SourceBookType, using the alias name SourceBookType.
     * @return the value of SourceBookType
     */
    public String getSourceBookType() {
        return (String)getAttributeInternal(SOURCEBOOKTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceBookType.
     * @param value value to set the SourceBookType
     */
    public void setSourceBookType(String value) {
        setAttributeInternal(SOURCEBOOKTYPE, value);
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
     * Gets the attribute value for Editor, using the alias name Editor.
     * @return the value of Editor
     */
    public String getEditor() {
        return (String)getAttributeInternal(EDITOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for Editor.
     * @param value value to set the Editor
     */
    public void setEditor(String value) {
        setAttributeInternal(EDITOR, value);
    }

    /**
     * Gets the attribute value for TeachingPlanName, using the alias name TeachingPlanName.
     * @return the value of TeachingPlanName
     */
    public String getTeachingPlanName() {
        return (String)getAttributeInternal(TEACHINGPLANNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for TeachingPlanName.
     * @param value value to set the TeachingPlanName
     */
    public void setTeachingPlanName(String value) {
        setAttributeInternal(TEACHINGPLANNAME, value);
    }

    /**
     * Gets the attribute value for Producer, using the alias name Producer.
     * @return the value of Producer
     */
    public String getProducer() {
        return (String)getAttributeInternal(PRODUCER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Producer.
     * @param value value to set the Producer
     */
    public void setProducer(String value) {
        setAttributeInternal(PRODUCER, value);
    }

    /**
     * Gets the attribute value for TeachingPlanType, using the alias name TeachingPlanType.
     * @return the value of TeachingPlanType
     */
    public String getTeachingPlanType() {
        return (String)getAttributeInternal(TEACHINGPLANTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TeachingPlanType.
     * @param value value to set the TeachingPlanType
     */
    public void setTeachingPlanType(String value) {
        setAttributeInternal(TEACHINGPLANTYPE, value);
    }

    /**
     * Gets the attribute value for CopyrightFlag, using the alias name CopyrightFlag.
     * @return the value of CopyrightFlag
     */
    public String getCopyrightFlag() {
        return (String)getAttributeInternal(COPYRIGHTFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for CopyrightFlag.
     * @param value value to set the CopyrightFlag
     */
    public void setCopyrightFlag(String value) {
        setAttributeInternal(COPYRIGHTFLAG, value);
    }

    /**
     * Gets the attribute value for Keyword, using the alias name Keyword.
     * @return the value of Keyword
     */
    public String getKeyword() {
        return (String)getAttributeInternal(KEYWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Keyword.
     * @param value value to set the Keyword
     */
    public void setKeyword(String value) {
        setAttributeInternal(KEYWORD, value);
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
     * Gets the attribute value for TeachingPlanStoreTime, using the alias name TeachingPlanStoreTime.
     * @return the value of TeachingPlanStoreTime
     */
    public Date getTeachingPlanStoreTime() {
        return (Date)getAttributeInternal(TEACHINGPLANSTORETIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for TeachingPlanStoreTime.
     * @param value value to set the TeachingPlanStoreTime
     */
    public void setTeachingPlanStoreTime(Date value) {
        setAttributeInternal(TEACHINGPLANSTORETIME, value);
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
     * Gets the attribute value for SourceBookDocId, using the alias name SourceBookDocId.
     * @return the value of SourceBookDocId
     */
    public Number getSourceBookDocId() {
        return (Number)getAttributeInternal(SOURCEBOOKDOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceBookDocId.
     * @param value value to set the SourceBookDocId
     */
    public void setSourceBookDocId(Number value) {
        setAttributeInternal(SOURCEBOOKDOCID, value);
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
     * @param teachingPlanId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number teachingPlanId) {
        return new Key(new Object[]{teachingPlanId});
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        SequenceImpl si = new SequenceImpl("CMS_TEACHING_PLAN_S", this.getDBTransaction());
        this.setTeachingPlanId(si.getSequenceNumber());
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }
}
