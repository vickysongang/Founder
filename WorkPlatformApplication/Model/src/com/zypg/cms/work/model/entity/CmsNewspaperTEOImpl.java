package com.zypg.cms.work.model.entity;


import com.honythink.applicationframework.hadf.CustomEntityImpl;

import java.math.BigDecimal;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowInconsistentException;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 21 13:11:07 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewspaperTEOImpl extends CustomEntityImpl {
    public void lock() {
        try {
            super.lock();
        } catch (RowInconsistentException e) {
            refresh(REFRESH_WITH_DB_ONLY_IF_UNCHANGED | REFRESH_CONTAINEES);
            super.lock();
        }
    }
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        NewspaperId {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getNewspaperId();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setNewspaperId((Number)value);
            }
        }
        ,
        NewspaperCategoryId {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getNewspaperCategoryId();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setNewspaperCategoryId((Number)value);
            }
        }
        ,
        NewsId {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getNewsId();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setNewsId((Number)value);
            }
        }
        ,
        Editor {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getEditor();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setEditor((String)value);
            }
        }
        ,
        FormatDesign {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getFormatDesign();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setFormatDesign((String)value);
            }
        }
        ,
        CoverDesign {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getCoverDesign();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setCoverDesign((String)value);
            }
        }
        ,
        PubTime {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getPubTime();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setPubTime((Date)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        ProdSize {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getProdSize();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setProdSize((String)value);
            }
        }
        ,
        ColorNum {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getColorNum();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setColorNum((String)value);
            }
        }
        ,
        CoverTypeset {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getCoverTypeset();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setCoverTypeset((String)value);
            }
        }
        ,
        AssortProd {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAssortProd();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAssortProd((String)value);
            }
        }
        ,
        BookSize {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getBookSize();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setBookSize((String)value);
            }
        }
        ,
        MainTypeset {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getMainTypeset();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setMainTypeset((String)value);
            }
        }
        ,
        MainPage {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getMainPage();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setMainPage((String)value);
            }
        }
        ,
        Pagination {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getPagination();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setPagination((Number)value);
            }
        }
        ,
        Suggestion {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getSuggestion();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setSuggestion((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        ObjectVersionNumber {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getObjectVersionNumber();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CreatedBy {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        Attribute1 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute1();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute1((String)value);
            }
        }
        ,
        Attribute2 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute2();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute2((String)value);
            }
        }
        ,
        Attribute3 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute3();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute3((String)value);
            }
        }
        ,
        Attribute4 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute4();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute4((String)value);
            }
        }
        ,
        Attribute5 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute5();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute5((String)value);
            }
        }
        ,
        Attribute6 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute6();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute6((String)value);
            }
        }
        ,
        Attribute7 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute7();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute7((String)value);
            }
        }
        ,
        Attribute8 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute8();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute8((String)value);
            }
        }
        ,
        Attribute9 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute9();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute9((String)value);
            }
        }
        ,
        Attribute10 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute10();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute10((String)value);
            }
        }
        ,
        Attribute11 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute11();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute11((String)value);
            }
        }
        ,
        Attribute12 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute12();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute12((String)value);
            }
        }
        ,
        Attribute13 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute13();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute13((String)value);
            }
        }
        ,
        Attribute14 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute14();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute14((String)value);
            }
        }
        ,
        Attribute15 {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getAttribute15();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setAttribute15((String)value);
            }
        }
        ,
        DocId {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        TypesetComp {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getTypesetComp();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setTypesetComp((String)value);
            }
        }
        ,
        TotalNum {
            public Object get(CmsNewspaperTEOImpl obj) {
                return obj.getTotalNum();
            }

            public void put(CmsNewspaperTEOImpl obj, Object value) {
                obj.setTotalNum((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsNewspaperTEOImpl object);

        public abstract void put(CmsNewspaperTEOImpl object, Object value);

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


    public static final int NEWSPAPERID = AttributesEnum.NewspaperId.index();
    public static final int NEWSPAPERCATEGORYID = AttributesEnum.NewspaperCategoryId.index();
    public static final int NEWSID = AttributesEnum.NewsId.index();
    public static final int EDITOR = AttributesEnum.Editor.index();
    public static final int FORMATDESIGN = AttributesEnum.FormatDesign.index();
    public static final int COVERDESIGN = AttributesEnum.CoverDesign.index();
    public static final int PUBTIME = AttributesEnum.PubTime.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int PRODSIZE = AttributesEnum.ProdSize.index();
    public static final int COLORNUM = AttributesEnum.ColorNum.index();
    public static final int COVERTYPESET = AttributesEnum.CoverTypeset.index();
    public static final int ASSORTPROD = AttributesEnum.AssortProd.index();
    public static final int BOOKSIZE = AttributesEnum.BookSize.index();
    public static final int MAINTYPESET = AttributesEnum.MainTypeset.index();
    public static final int MAINPAGE = AttributesEnum.MainPage.index();
    public static final int PAGINATION = AttributesEnum.Pagination.index();
    public static final int SUGGESTION = AttributesEnum.Suggestion.index();
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
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int TYPESETCOMP = AttributesEnum.TypesetComp.index();
    public static final int TOTALNUM = AttributesEnum.TotalNum.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewspaperTEOImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.zypg.cms.work.model.entity.CmsNewspaperTEO");
    }

    /**
     * Gets the attribute value for NewspaperId, using the alias name NewspaperId.
     * @return the value of NewspaperId
     */
    public oracle.jbo.domain.Number getNewspaperId() {
        return (oracle.jbo.domain.Number)getAttributeInternal(NEWSPAPERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for NewspaperId.
     * @param value value to set the NewspaperId
     */
    public void setNewspaperId(oracle.jbo.domain.Number value) {
        setAttributeInternal(NEWSPAPERID, value);
    }

    /**
     * Gets the attribute value for NewspaperCategoryId, using the alias name NewspaperCategoryId.
     * @return the value of NewspaperCategoryId
     */
    public Number getNewspaperCategoryId() {
        return (Number)getAttributeInternal(NEWSPAPERCATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for NewspaperCategoryId.
     * @param value value to set the NewspaperCategoryId
     */
    public void setNewspaperCategoryId(Number value) {
        setAttributeInternal(NEWSPAPERCATEGORYID, value);
    }

    /**
     * Gets the attribute value for NewsId, using the alias name NewsId.
     * @return the value of NewsId
     */
    public Number getNewsId() {
        return (Number)getAttributeInternal(NEWSID);
    }

    /**
     * Sets <code>value</code> as the attribute value for NewsId.
     * @param value value to set the NewsId
     */
    public void setNewsId(Number value) {
        setAttributeInternal(NEWSID, value);
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
     * Gets the attribute value for FormatDesign, using the alias name FormatDesign.
     * @return the value of FormatDesign
     */
    public String getFormatDesign() {
        return (String)getAttributeInternal(FORMATDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for FormatDesign.
     * @param value value to set the FormatDesign
     */
    public void setFormatDesign(String value) {
        setAttributeInternal(FORMATDESIGN, value);
    }

    /**
     * Gets the attribute value for CoverDesign, using the alias name CoverDesign.
     * @return the value of CoverDesign
     */
    public String getCoverDesign() {
        return (String)getAttributeInternal(COVERDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for CoverDesign.
     * @param value value to set the CoverDesign
     */
    public void setCoverDesign(String value) {
        setAttributeInternal(COVERDESIGN, value);
    }

    /**
     * Gets the attribute value for PubTime, using the alias name PubTime.
     * @return the value of PubTime
     */
    public Date getPubTime() {
        return (Date)getAttributeInternal(PUBTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for PubTime.
     * @param value value to set the PubTime
     */
    public void setPubTime(Date value) {
        setAttributeInternal(PUBTIME, value);
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
     * Gets the attribute value for ProdSize, using the alias name ProdSize.
     * @return the value of ProdSize
     */
    public String getProdSize() {
        return (String)getAttributeInternal(PRODSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProdSize.
     * @param value value to set the ProdSize
     */
    public void setProdSize(String value) {
        setAttributeInternal(PRODSIZE, value);
    }

    /**
     * Gets the attribute value for ColorNum, using the alias name ColorNum.
     * @return the value of ColorNum
     */
    public String getColorNum() {
        return (String)getAttributeInternal(COLORNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for ColorNum.
     * @param value value to set the ColorNum
     */
    public void setColorNum(String value) {
        setAttributeInternal(COLORNUM, value);
    }

    /**
     * Gets the attribute value for CoverTypeset, using the alias name CoverTypeset.
     * @return the value of CoverTypeset
     */
    public String getCoverTypeset() {
        return (String)getAttributeInternal(COVERTYPESET);
    }

    /**
     * Sets <code>value</code> as the attribute value for CoverTypeset.
     * @param value value to set the CoverTypeset
     */
    public void setCoverTypeset(String value) {
        setAttributeInternal(COVERTYPESET, value);
    }

    /**
     * Gets the attribute value for AssortProd, using the alias name AssortProd.
     * @return the value of AssortProd
     */
    public String getAssortProd() {
        return (String)getAttributeInternal(ASSORTPROD);
    }

    /**
     * Sets <code>value</code> as the attribute value for AssortProd.
     * @param value value to set the AssortProd
     */
    public void setAssortProd(String value) {
        setAttributeInternal(ASSORTPROD, value);
    }

    /**
     * Gets the attribute value for BookSize, using the alias name BookSize.
     * @return the value of BookSize
     */
    public String getBookSize() {
        return (String)getAttributeInternal(BOOKSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookSize.
     * @param value value to set the BookSize
     */
    public void setBookSize(String value) {
        setAttributeInternal(BOOKSIZE, value);
    }

    /**
     * Gets the attribute value for MainTypeset, using the alias name MainTypeset.
     * @return the value of MainTypeset
     */
    public String getMainTypeset() {
        return (String)getAttributeInternal(MAINTYPESET);
    }

    /**
     * Sets <code>value</code> as the attribute value for MainTypeset.
     * @param value value to set the MainTypeset
     */
    public void setMainTypeset(String value) {
        setAttributeInternal(MAINTYPESET, value);
    }

    /**
     * Gets the attribute value for MainPage, using the alias name MainPage.
     * @return the value of MainPage
     */
    public String getMainPage() {
        return (String)getAttributeInternal(MAINPAGE);
    }

    /**
     * Sets <code>value</code> as the attribute value for MainPage.
     * @param value value to set the MainPage
     */
    public void setMainPage(String value) {
        setAttributeInternal(MAINPAGE, value);
    }

    /**
     * Gets the attribute value for Pagination, using the alias name Pagination.
     * @return the value of Pagination
     */
    public Number getPagination() {
        return (Number)getAttributeInternal(PAGINATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Pagination.
     * @param value value to set the Pagination
     */
    public void setPagination(Number value) {
        setAttributeInternal(PAGINATION, value);
    }

    /**
     * Gets the attribute value for Suggestion, using the alias name Suggestion.
     * @return the value of Suggestion
     */
    public String getSuggestion() {
        return (String)getAttributeInternal(SUGGESTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Suggestion.
     * @param value value to set the Suggestion
     */
    public void setSuggestion(String value) {
        setAttributeInternal(SUGGESTION, value);
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
     * Gets the attribute value for TypesetComp, using the alias name TypesetComp.
     * @return the value of TypesetComp
     */
    public String getTypesetComp() {
        return (String)getAttributeInternal(TYPESETCOMP);
    }

    /**
     * Sets <code>value</code> as the attribute value for TypesetComp.
     * @param value value to set the TypesetComp
     */
    public void setTypesetComp(String value) {
        setAttributeInternal(TYPESETCOMP, value);
    }


    /**
     * Gets the attribute value for TotalNum, using the alias name TotalNum.
     * @return the value of TotalNum
     */
    public Number getTotalNum() {
        return (Number)getAttributeInternal(TOTALNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for TotalNum.
     * @param value value to set the TotalNum
     */
    public void setTotalNum(Number value) {
        setAttributeInternal(TOTALNUM, value);
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
     * @param newspaperId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number newspaperId) {
        return new Key(new Object[]{newspaperId});
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        SequenceImpl si = new SequenceImpl("CMS_NEWSPAPER_S", this.getDBTransaction());
        this.setNewspaperId(si.getSequenceNumber());
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }
}
