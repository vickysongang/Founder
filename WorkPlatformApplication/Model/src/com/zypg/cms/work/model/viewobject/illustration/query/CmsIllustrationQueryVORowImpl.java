package com.zypg.cms.work.model.viewobject.illustration.query;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Aug 29 12:18:51 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsIllustrationQueryVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        IllustrationId {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getIllustrationId();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setIllustrationId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        IllustrationName {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getIllustrationName();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setIllustrationName((String)value);
            }
        }
        ,
        IllustrationSource {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getIllustrationSource();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setIllustrationSource((String)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        Status {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        DeleteFlag {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getDeleteFlag();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setDeleteFlag((String)value);
            }
        }
        ,
        ThumbnailUrl {
            public Object get(CmsIllustrationQueryVORowImpl obj) {
                return obj.getThumbnailUrl();
            }

            public void put(CmsIllustrationQueryVORowImpl obj, Object value) {
                obj.setThumbnailUrl((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsIllustrationQueryVORowImpl object);

        public abstract void put(CmsIllustrationQueryVORowImpl object, Object value);

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


    public static final int ILLUSTRATIONID = AttributesEnum.IllustrationId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int ILLUSTRATIONNAME = AttributesEnum.IllustrationName.index();
    public static final int ILLUSTRATIONSOURCE = AttributesEnum.IllustrationSource.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int DELETEFLAG = AttributesEnum.DeleteFlag.index();
    public static final int THUMBNAILURL = AttributesEnum.ThumbnailUrl.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsIllustrationQueryVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute IllustrationId.
     * @return the IllustrationId
     */
    public Number getIllustrationId() {
        return (Number) getAttributeInternal(ILLUSTRATIONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IllustrationId.
     * @param value value to set the  IllustrationId
     */
    public void setIllustrationId(Number value) {
        setAttributeInternal(ILLUSTRATIONID, value);
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
     * Gets the attribute value for the calculated attribute IllustrationName.
     * @return the IllustrationName
     */
    public String getIllustrationName() {
        return (String) getAttributeInternal(ILLUSTRATIONNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IllustrationName.
     * @param value value to set the  IllustrationName
     */
    public void setIllustrationName(String value) {
        setAttributeInternal(ILLUSTRATIONNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IllustrationSource.
     * @return the IllustrationSource
     */
    public String getIllustrationSource() {
        return (String) getAttributeInternal(ILLUSTRATIONSOURCE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IllustrationSource.
     * @param value value to set the  IllustrationSource
     */
    public void setIllustrationSource(String value) {
        setAttributeInternal(ILLUSTRATIONSOURCE, value);
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
     * Gets the attribute value for the calculated attribute ThumbnailUrl.
     * @return the ThumbnailUrl
     */
    public String getThumbnailUrl() {
        return (String) getAttributeInternal(THUMBNAILURL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ThumbnailUrl.
     * @param value value to set the  ThumbnailUrl
     */
    public void setThumbnailUrl(String value) {
        setAttributeInternal(THUMBNAILURL, value);
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
