package com.zypg.cms.work.model.viewobject.newspaper.query;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Hashtable;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;

import oracle.ord.im.OrdDocDomain;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jun 17 21:12:16 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewspaperVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        NewspaperId {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getNewspaperId();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setNewspaperId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        NewspaperCategoryId {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getNewspaperCategoryId();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setNewspaperCategoryId((Number)value);
            }
        }
        ,
        Editor {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getEditor();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setEditor((String)value);
            }
        }
        ,
        NewsId {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getNewsId();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setNewsId((Number)value);
            }
        }
        ,
        FormatDesign {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getFormatDesign();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setFormatDesign((String)value);
            }
        }
        ,
        CoverDesign {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getCoverDesign();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setCoverDesign((String)value);
            }
        }
        ,
        PubTime {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getPubTime();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setPubTime((Date)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        ProdSize {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getProdSize();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setProdSize((String)value);
            }
        }
        ,
        ColorNum {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getColorNum();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setColorNum((String)value);
            }
        }
        ,
        CoverTypeset {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getCoverTypeset();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setCoverTypeset((String)value);
            }
        }
        ,
        AssortProd {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getAssortProd();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setAssortProd((String)value);
            }
        }
        ,
        BookSize {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getBookSize();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setBookSize((String)value);
            }
        }
        ,
        MainTypeset {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getMainTypeset();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setMainTypeset((String)value);
            }
        }
        ,
        MainPage {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getMainPage();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setMainPage((String)value);
            }
        }
        ,
        Pagination {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getPagination();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setPagination((Number)value);
            }
        }
        ,
        Suggestion {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getSuggestion();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setSuggestion((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        Status {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        Checked {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getChecked();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setChecked((Boolean)value);
            }
        }
        ,
        CategoryId {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getCategoryId();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setCategoryId((Number)value);
            }
        }
        ,
        CategoryGroupType {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getCategoryGroupType();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setCategoryGroupType((String)value);
            }
        }
        ,
        DeleteFlag {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getDeleteFlag();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setDeleteFlag((String)value);
            }
        }
        ,
        ThumbnailUrl {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getThumbnailUrl();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setThumbnailUrl((String)value);
            }
        }
        ,
        NewsNum {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getNewsNum();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setNewsNum((Number)value);
            }
        }
        ,
        NewspaperCategoryName {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getNewspaperCategoryName();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setNewspaperCategoryName((String)value);
            }
        }
        ,
        NewspaperType {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getNewspaperType();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setNewspaperType((String)value);
            }
        }
        ,
        Total {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getTotal();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setTotal((Number)value);
            }
        }
        ,
        Year {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getYear();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setYear((String)value);
            }
        }
        ,
        SyncFlag {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getSyncFlag();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setSyncFlag((String)value);
            }
        }
        ,
        TotalNum {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getTotalNum();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setTotalNum((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        TypesetComp {
            public Object get(CmsNewspaperVORowImpl obj) {
                return obj.getTypesetComp();
            }

            public void put(CmsNewspaperVORowImpl obj, Object value) {
                obj.setTypesetComp((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsNewspaperVORowImpl object);

        public abstract void put(CmsNewspaperVORowImpl object, Object value);

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
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int NEWSPAPERCATEGORYID = AttributesEnum.NewspaperCategoryId.index();
    public static final int EDITOR = AttributesEnum.Editor.index();
    public static final int NEWSID = AttributesEnum.NewsId.index();
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
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int CHECKED = AttributesEnum.Checked.index();
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int CATEGORYGROUPTYPE = AttributesEnum.CategoryGroupType.index();
    public static final int DELETEFLAG = AttributesEnum.DeleteFlag.index();
    public static final int THUMBNAILURL = AttributesEnum.ThumbnailUrl.index();
    public static final int NEWSNUM = AttributesEnum.NewsNum.index();
    public static final int NEWSPAPERCATEGORYNAME = AttributesEnum.NewspaperCategoryName.index();
    public static final int NEWSPAPERTYPE = AttributesEnum.NewspaperType.index();
    public static final int TOTAL = AttributesEnum.Total.index();
    public static final int YEAR = AttributesEnum.Year.index();
    public static final int SYNCFLAG = AttributesEnum.SyncFlag.index();
    public static final int TOTALNUM = AttributesEnum.TotalNum.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int TYPESETCOMP = AttributesEnum.TypesetComp.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewspaperVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperId.
     * @return the NewspaperId
     */
    public Number getNewspaperId() {
        return (Number)getAttributeInternal(NEWSPAPERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperId.
     * @param value value to set the  NewspaperId
     */
    public void setNewspaperId(Number value) {
        setAttributeInternal(NEWSPAPERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DocId.
     * @return the DocId
     */
    public Number getDocId() {
        return (Number)getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocId.
     * @param value value to set the  DocId
     */
    public void setDocId(Number value) {
        setAttributeInternal(DOCID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperCategoryId.
     * @return the NewspaperCategoryId
     */
    public Number getNewspaperCategoryId() {
        return (Number)getAttributeInternal(NEWSPAPERCATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperCategoryId.
     * @param value value to set the  NewspaperCategoryId
     */
    public void setNewspaperCategoryId(Number value) {
        setAttributeInternal(NEWSPAPERCATEGORYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Editor.
     * @return the Editor
     */
    public String getEditor() {
        return (String)getAttributeInternal(EDITOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Editor.
     * @param value value to set the  Editor
     */
    public void setEditor(String value) {
        setAttributeInternal(EDITOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewsId.
     * @return the NewsId
     */
    public Number getNewsId() {
        return (Number)getAttributeInternal(NEWSID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewsId.
     * @param value value to set the  NewsId
     */
    public void setNewsId(Number value) {
        setAttributeInternal(NEWSID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FormatDesign.
     * @return the FormatDesign
     */
    public String getFormatDesign() {
        return (String)getAttributeInternal(FORMATDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FormatDesign.
     * @param value value to set the  FormatDesign
     */
    public void setFormatDesign(String value) {
        setAttributeInternal(FORMATDESIGN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CoverDesign.
     * @return the CoverDesign
     */
    public String getCoverDesign() {
        return (String)getAttributeInternal(COVERDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CoverDesign.
     * @param value value to set the  CoverDesign
     */
    public void setCoverDesign(String value) {
        setAttributeInternal(COVERDESIGN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PubTime.
     * @return the PubTime
     */
    public Date getPubTime() {
        return (Date)getAttributeInternal(PUBTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PubTime.
     * @param value value to set the  PubTime
     */
    public void setPubTime(Date value) {
        setAttributeInternal(PUBTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Keyword.
     * @return the Keyword
     */
    public String getKeyword() {
        return (String)getAttributeInternal(KEYWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Keyword.
     * @param value value to set the  Keyword
     */
    public void setKeyword(String value) {
        setAttributeInternal(KEYWORD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProdSize.
     * @return the ProdSize
     */
    public String getProdSize() {
        return (String)getAttributeInternal(PRODSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProdSize.
     * @param value value to set the  ProdSize
     */
    public void setProdSize(String value) {
        setAttributeInternal(PRODSIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ColorNum.
     * @return the ColorNum
     */
    public String getColorNum() {
        return (String)getAttributeInternal(COLORNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ColorNum.
     * @param value value to set the  ColorNum
     */
    public void setColorNum(String value) {
        setAttributeInternal(COLORNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CoverTypeset.
     * @return the CoverTypeset
     */
    public String getCoverTypeset() {
        return (String)getAttributeInternal(COVERTYPESET);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CoverTypeset.
     * @param value value to set the  CoverTypeset
     */
    public void setCoverTypeset(String value) {
        setAttributeInternal(COVERTYPESET, value);
    }

    /**
     * Gets the attribute value for the calculated attribute AssortProd.
     * @return the AssortProd
     */
    public String getAssortProd() {
        return (String)getAttributeInternal(ASSORTPROD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AssortProd.
     * @param value value to set the  AssortProd
     */
    public void setAssortProd(String value) {
        setAttributeInternal(ASSORTPROD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BookSize.
     * @return the BookSize
     */
    public String getBookSize() {
        return (String)getAttributeInternal(BOOKSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BookSize.
     * @param value value to set the  BookSize
     */
    public void setBookSize(String value) {
        setAttributeInternal(BOOKSIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MainTypeset.
     * @return the MainTypeset
     */
    public String getMainTypeset() {
        return (String)getAttributeInternal(MAINTYPESET);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MainTypeset.
     * @param value value to set the  MainTypeset
     */
    public void setMainTypeset(String value) {
        setAttributeInternal(MAINTYPESET, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MainPage.
     * @return the MainPage
     */
    public String getMainPage() {
        return (String)getAttributeInternal(MAINPAGE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MainPage.
     * @param value value to set the  MainPage
     */
    public void setMainPage(String value) {
        setAttributeInternal(MAINPAGE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Pagination.
     * @return the Pagination
     */
    public Number getPagination() {
        return (Number)getAttributeInternal(PAGINATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Pagination.
     * @param value value to set the  Pagination
     */
    public void setPagination(Number value) {
        setAttributeInternal(PAGINATION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Suggestion.
     * @return the Suggestion
     */
    public String getSuggestion() {
        return (String)getAttributeInternal(SUGGESTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Suggestion.
     * @param value value to set the  Suggestion
     */
    public void setSuggestion(String value) {
        setAttributeInternal(SUGGESTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Remarks.
     * @return the Remarks
     */
    public String getRemarks() {
        return (String)getAttributeInternal(REMARKS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Remarks.
     * @param value value to set the  Remarks
     */
    public void setRemarks(String value) {
        setAttributeInternal(REMARKS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Status.
     * @return the Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
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
        return (String)getAttributeInternal(LIBCODE);
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
        return (String)getAttributeInternal(COMPCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompCode.
     * @param value value to set the  CompCode
     */
    public void setCompCode(String value) {
        setAttributeInternal(COMPCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Checked.
     * @return the Checked
     */
    public Boolean getChecked() {
        Boolean result = null;
        Hashtable map = this.getDBTransaction().getSession().getUserData();
        String status = null;
        if ("Y".equals(this.getDeleteFlag())) {
            status = "DELETED_" + this.getLibCode();
        } else {
            status = this.getStatus();
        }
        String selectAllFlag = (String)map.get(status + "_selectAllFlag");
        String key = status + "_" + this.getDocId();
        List<String> currSelectedList = (List<String>)map.get(status + "_currSelected");
        if ("O".equals(selectAllFlag)) {
            if (currSelectedList.contains(key)) {
                result = true;
            } else {
                result = false;
            }
        } else {
            if ("Y".equals(selectAllFlag)) {
                result = true;
            } else if ("N".equals(selectAllFlag)) {
                result = false;
            }
        }
        this.setChecked(result);
        return (Boolean)getAttributeInternal(CHECKED);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Checked.
     * @param value value to set the  Checked
     */
    public void setChecked(Boolean value) {
        setAttributeInternal(CHECKED, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryId.
     * @return the CategoryId
     */
    public Number getCategoryId() {
        return (Number)getAttributeInternal(CATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryId.
     * @param value value to set the  CategoryId
     */
    public void setCategoryId(Number value) {
        setAttributeInternal(CATEGORYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryGroupType.
     * @return the CategoryGroupType
     */
    public String getCategoryGroupType() {
        return (String)getAttributeInternal(CATEGORYGROUPTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryGroupType.
     * @param value value to set the  CategoryGroupType
     */
    public void setCategoryGroupType(String value) {
        setAttributeInternal(CATEGORYGROUPTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DeleteFlag.
     * @return the DeleteFlag
     */
    public String getDeleteFlag() {
        return (String)getAttributeInternal(DELETEFLAG);
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
        return (String)getAttributeInternal(THUMBNAILURL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ThumbnailUrl.
     * @param value value to set the  ThumbnailUrl
     */
    public void setThumbnailUrl(String value) {
        setAttributeInternal(THUMBNAILURL, value);
    }


    /**
     * Gets the attribute value for the calculated attribute NewsNum.
     * @return the NewsNum
     */
    public Number getNewsNum() {
        return (Number)getAttributeInternal(NEWSNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewsNum.
     * @param value value to set the  NewsNum
     */
    public void setNewsNum(Number value) {
        setAttributeInternal(NEWSNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperCategoryName.
     * @return the NewspaperCategoryName
     */
    public String getNewspaperCategoryName() {
        return (String)getAttributeInternal(NEWSPAPERCATEGORYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperCategoryName.
     * @param value value to set the  NewspaperCategoryName
     */
    public void setNewspaperCategoryName(String value) {
        setAttributeInternal(NEWSPAPERCATEGORYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperType.
     * @return the NewspaperType
     */
    public String getNewspaperType() {
        return (String)getAttributeInternal(NEWSPAPERTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperType.
     * @param value value to set the  NewspaperType
     */
    public void setNewspaperType(String value) {
        setAttributeInternal(NEWSPAPERTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Total.
     * @return the Total
     */
    public Number getTotal() {
        return (Number)getAttributeInternal(TOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Total.
     * @param value value to set the  Total
     */
    public void setTotal(Number value) {
        setAttributeInternal(TOTAL, value);
    }


    /**
     * Gets the attribute value for the calculated attribute Year.
     * @return the Year
     */
    public String getYear() {
        return (String) getAttributeInternal(YEAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Year.
     * @param value value to set the  Year
     */
    public void setYear(String value) {
        setAttributeInternal(YEAR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SyncFlag.
     * @return the SyncFlag
     */
    public String getSyncFlag() {
        return (String) getAttributeInternal(SYNCFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SyncFlag.
     * @param value value to set the  SyncFlag
     */
    public void setSyncFlag(String value) {
        setAttributeInternal(SYNCFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TotalNum.
     * @return the TotalNum
     */
    public Number getTotalNum() {
        return (Number) getAttributeInternal(TOTALNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TotalNum.
     * @param value value to set the  TotalNum
     */
    public void setTotalNum(Number value) {
        setAttributeInternal(TOTALNUM, value);
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
     * Gets the attribute value for the calculated attribute TypesetComp.
     * @return the TypesetComp
     */
    public String getTypesetComp() {
        return (String) getAttributeInternal(TYPESETCOMP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TypesetComp.
     * @param value value to set the  TypesetComp
     */
    public void setTypesetComp(String value) {
        setAttributeInternal(TYPESETCOMP, value);
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
