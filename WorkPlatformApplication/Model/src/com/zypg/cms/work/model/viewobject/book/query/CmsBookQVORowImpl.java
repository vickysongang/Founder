package com.zypg.cms.work.model.viewobject.book.query;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun May 31 18:53:16 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsBookQVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        BookName {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getBookName();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setBookName((String)value);
            }
        }
        ,
        Isbn {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getIsbn();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setIsbn((String)value);
            }
        }
        ,
        Series {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getSeries();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setSeries((String)value);
            }
        }
        ,
        ItemCode {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getItemCode();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setItemCode((String)value);
            }
        }
        ,
        Lang {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLang();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setLang((String)value);
            }
        }
        ,
        Editor {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getEditor();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setEditor((String)value);
            }
        }
        ,
        FormatDesign {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getFormatDesign();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setFormatDesign((String)value);
            }
        }
        ,
        CoverDesign {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getCoverDesign();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setCoverDesign((String)value);
            }
        }
        ,
        Author {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getAuthor();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAuthor((String)value);
            }
        }
        ,
        ChiefEditor {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getChiefEditor();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setChiefEditor((String)value);
            }
        }
        ,
        Translator {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getTranslator();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setTranslator((String)value);
            }
        }
        ,
        PubTime {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getPubTime();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setPubTime((Date)value);
            }
        }
        ,
        PublishingHouse {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getPublishingHouse();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setPublishingHouse((String)value);
            }
        }
        ,
        Impression {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getImpression();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setImpression((Number)value);
            }
        }
        ,
        Edition {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getEdition();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setEdition((Number)value);
            }
        }
        ,
        Pagination {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getPagination();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setPagination((String)value);
            }
        }
        ,
        Price {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getPrice();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setPrice((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        ReaderGroup {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getReaderGroup();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setReaderGroup((String)value);
            }
        }
        ,
        Suggestion {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getSuggestion();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setSuggestion((String)value);
            }
        }
        ,
        ProdSize {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getProdSize();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setProdSize((String)value);
            }
        }
        ,
        ColorNum {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getColorNum();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setColorNum((String)value);
            }
        }
        ,
        CoverTypeset {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getCoverTypeset();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setCoverTypeset((String)value);
            }
        }
        ,
        AssortProd {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getAssortProd();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAssortProd((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        BookSize {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getBookSize();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setBookSize((String)value);
            }
        }
        ,
        MainPage {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getMainPage();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setMainPage((String)value);
            }
        }
        ,
        MainTypeset {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getMainTypeset();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setMainTypeset((String)value);
            }
        }
        ,
        FormatComp {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getFormatComp();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setFormatComp((String)value);
            }
        }
        ,
        Lookup4AssortProd {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4AssortProd();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4BookSize {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4BookSize();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4ColorNum {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4ColorNum();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4Typeset {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4Typeset();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4Lang {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4Lang();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4MainPage {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4MainPage();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4ProdSize {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4ProdSize();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4ReaderGroup {
            public Object get(CmsBookQVORowImpl obj) {
                return obj.getLookup4ReaderGroup();
            }

            public void put(CmsBookQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsBookQVORowImpl object);

        public abstract void put(CmsBookQVORowImpl object, Object value);

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


    public static final int BOOKNAME = AttributesEnum.BookName.index();
    public static final int ISBN = AttributesEnum.Isbn.index();
    public static final int SERIES = AttributesEnum.Series.index();
    public static final int ITEMCODE = AttributesEnum.ItemCode.index();
    public static final int LANG = AttributesEnum.Lang.index();
    public static final int EDITOR = AttributesEnum.Editor.index();
    public static final int FORMATDESIGN = AttributesEnum.FormatDesign.index();
    public static final int COVERDESIGN = AttributesEnum.CoverDesign.index();
    public static final int AUTHOR = AttributesEnum.Author.index();
    public static final int CHIEFEDITOR = AttributesEnum.ChiefEditor.index();
    public static final int TRANSLATOR = AttributesEnum.Translator.index();
    public static final int PUBTIME = AttributesEnum.PubTime.index();
    public static final int PUBLISHINGHOUSE = AttributesEnum.PublishingHouse.index();
    public static final int IMPRESSION = AttributesEnum.Impression.index();
    public static final int EDITION = AttributesEnum.Edition.index();
    public static final int PAGINATION = AttributesEnum.Pagination.index();
    public static final int PRICE = AttributesEnum.Price.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int READERGROUP = AttributesEnum.ReaderGroup.index();
    public static final int SUGGESTION = AttributesEnum.Suggestion.index();
    public static final int PRODSIZE = AttributesEnum.ProdSize.index();
    public static final int COLORNUM = AttributesEnum.ColorNum.index();
    public static final int COVERTYPESET = AttributesEnum.CoverTypeset.index();
    public static final int ASSORTPROD = AttributesEnum.AssortProd.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int BOOKSIZE = AttributesEnum.BookSize.index();
    public static final int MAINPAGE = AttributesEnum.MainPage.index();
    public static final int MAINTYPESET = AttributesEnum.MainTypeset.index();
    public static final int FORMATCOMP = AttributesEnum.FormatComp.index();
    public static final int LOOKUP4ASSORTPROD = AttributesEnum.Lookup4AssortProd.index();
    public static final int LOOKUP4BOOKSIZE = AttributesEnum.Lookup4BookSize.index();
    public static final int LOOKUP4COLORNUM = AttributesEnum.Lookup4ColorNum.index();
    public static final int LOOKUP4TYPESET = AttributesEnum.Lookup4Typeset.index();
    public static final int LOOKUP4LANG = AttributesEnum.Lookup4Lang.index();
    public static final int LOOKUP4MAINPAGE = AttributesEnum.Lookup4MainPage.index();
    public static final int LOOKUP4PRODSIZE = AttributesEnum.Lookup4ProdSize.index();
    public static final int LOOKUP4READERGROUP = AttributesEnum.Lookup4ReaderGroup.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsBookQVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute BookName.
     * @return the BookName
     */
    public String getBookName() {
        return (String) getAttributeInternal(BOOKNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BookName.
     * @param value value to set the  BookName
     */
    public void setBookName(String value) {
        setAttributeInternal(BOOKNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Isbn.
     * @return the Isbn
     */
    public String getIsbn() {
        return (String) getAttributeInternal(ISBN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Isbn.
     * @param value value to set the  Isbn
     */
    public void setIsbn(String value) {
        setAttributeInternal(ISBN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Series.
     * @return the Series
     */
    public String getSeries() {
        return (String) getAttributeInternal(SERIES);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Series.
     * @param value value to set the  Series
     */
    public void setSeries(String value) {
        setAttributeInternal(SERIES, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ItemCode.
     * @return the ItemCode
     */
    public String getItemCode() {
        return (String) getAttributeInternal(ITEMCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemCode.
     * @param value value to set the  ItemCode
     */
    public void setItemCode(String value) {
        setAttributeInternal(ITEMCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Lang.
     * @return the Lang
     */
    public String getLang() {
        return (String) getAttributeInternal(LANG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Lang.
     * @param value value to set the  Lang
     */
    public void setLang(String value) {
        setAttributeInternal(LANG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Editor.
     * @return the Editor
     */
    public String getEditor() {
        return (String) getAttributeInternal(EDITOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Editor.
     * @param value value to set the  Editor
     */
    public void setEditor(String value) {
        setAttributeInternal(EDITOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FormatDesign.
     * @return the FormatDesign
     */
    public String getFormatDesign() {
        return (String) getAttributeInternal(FORMATDESIGN);
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
        return (String) getAttributeInternal(COVERDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CoverDesign.
     * @param value value to set the  CoverDesign
     */
    public void setCoverDesign(String value) {
        setAttributeInternal(COVERDESIGN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Author.
     * @return the Author
     */
    public String getAuthor() {
        return (String) getAttributeInternal(AUTHOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Author.
     * @param value value to set the  Author
     */
    public void setAuthor(String value) {
        setAttributeInternal(AUTHOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ChiefEditor.
     * @return the ChiefEditor
     */
    public String getChiefEditor() {
        return (String) getAttributeInternal(CHIEFEDITOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ChiefEditor.
     * @param value value to set the  ChiefEditor
     */
    public void setChiefEditor(String value) {
        setAttributeInternal(CHIEFEDITOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Translator.
     * @return the Translator
     */
    public String getTranslator() {
        return (String) getAttributeInternal(TRANSLATOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Translator.
     * @param value value to set the  Translator
     */
    public void setTranslator(String value) {
        setAttributeInternal(TRANSLATOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PubTime.
     * @return the PubTime
     */
    public Date getPubTime() {
        return (Date) getAttributeInternal(PUBTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PubTime.
     * @param value value to set the  PubTime
     */
    public void setPubTime(Date value) {
        setAttributeInternal(PUBTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PublishingHouse.
     * @return the PublishingHouse
     */
    public String getPublishingHouse() {
        return (String) getAttributeInternal(PUBLISHINGHOUSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PublishingHouse.
     * @param value value to set the  PublishingHouse
     */
    public void setPublishingHouse(String value) {
        setAttributeInternal(PUBLISHINGHOUSE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Impression.
     * @return the Impression
     */
    public Number getImpression() {
        return (Number) getAttributeInternal(IMPRESSION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Impression.
     * @param value value to set the  Impression
     */
    public void setImpression(Number value) {
        setAttributeInternal(IMPRESSION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Edition.
     * @return the Edition
     */
    public Number getEdition() {
        return (Number) getAttributeInternal(EDITION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Edition.
     * @param value value to set the  Edition
     */
    public void setEdition(Number value) {
        setAttributeInternal(EDITION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Pagination.
     * @return the Pagination
     */
    public String getPagination() {
        return (String) getAttributeInternal(PAGINATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Pagination.
     * @param value value to set the  Pagination
     */
    public void setPagination(String value) {
        setAttributeInternal(PAGINATION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Price.
     * @return the Price
     */
    public String getPrice() {
        return (String) getAttributeInternal(PRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Price.
     * @param value value to set the  Price
     */
    public void setPrice(String value) {
        setAttributeInternal(PRICE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Keyword.
     * @return the Keyword
     */
    public String getKeyword() {
        return (String) getAttributeInternal(KEYWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Keyword.
     * @param value value to set the  Keyword
     */
    public void setKeyword(String value) {
        setAttributeInternal(KEYWORD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ReaderGroup.
     * @return the ReaderGroup
     */
    public String getReaderGroup() {
        return (String) getAttributeInternal(READERGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ReaderGroup.
     * @param value value to set the  ReaderGroup
     */
    public void setReaderGroup(String value) {
        setAttributeInternal(READERGROUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Suggestion.
     * @return the Suggestion
     */
    public String getSuggestion() {
        return (String) getAttributeInternal(SUGGESTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Suggestion.
     * @param value value to set the  Suggestion
     */
    public void setSuggestion(String value) {
        setAttributeInternal(SUGGESTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProdSize.
     * @return the ProdSize
     */
    public String getProdSize() {
        return (String) getAttributeInternal(PRODSIZE);
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
        return (String) getAttributeInternal(COLORNUM);
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
        return (String) getAttributeInternal(COVERTYPESET);
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
        return (String) getAttributeInternal(ASSORTPROD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AssortProd.
     * @param value value to set the  AssortProd
     */
    public void setAssortProd(String value) {
        setAttributeInternal(ASSORTPROD, value);
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
     * Gets the attribute value for the calculated attribute BookSize.
     * @return the BookSize
     */
    public String getBookSize() {
        return (String) getAttributeInternal(BOOKSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BookSize.
     * @param value value to set the  BookSize
     */
    public void setBookSize(String value) {
        setAttributeInternal(BOOKSIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MainPage.
     * @return the MainPage
     */
    public String getMainPage() {
        return (String) getAttributeInternal(MAINPAGE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MainPage.
     * @param value value to set the  MainPage
     */
    public void setMainPage(String value) {
        setAttributeInternal(MAINPAGE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MainTypeset.
     * @return the MainTypeset
     */
    public String getMainTypeset() {
        return (String) getAttributeInternal(MAINTYPESET);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MainTypeset.
     * @param value value to set the  MainTypeset
     */
    public void setMainTypeset(String value) {
        setAttributeInternal(MAINTYPESET, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FormatComp.
     * @return the FormatComp
     */
    public String getFormatComp() {
        return (String) getAttributeInternal(FORMATCOMP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FormatComp.
     * @param value value to set the  FormatComp
     */
    public void setFormatComp(String value) {
        setAttributeInternal(FORMATCOMP, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4AssortProd.
     */
    public RowSet getLookup4AssortProd() {
        return (RowSet)getAttributeInternal(LOOKUP4ASSORTPROD);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4BookSize.
     */
    public RowSet getLookup4BookSize() {
        return (RowSet)getAttributeInternal(LOOKUP4BOOKSIZE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4ColorNum.
     */
    public RowSet getLookup4ColorNum() {
        return (RowSet)getAttributeInternal(LOOKUP4COLORNUM);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4Typeset.
     */
    public RowSet getLookup4Typeset() {
        return (RowSet)getAttributeInternal(LOOKUP4TYPESET);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4Lang.
     */
    public RowSet getLookup4Lang() {
        return (RowSet)getAttributeInternal(LOOKUP4LANG);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4MainPage.
     */
    public RowSet getLookup4MainPage() {
        return (RowSet)getAttributeInternal(LOOKUP4MAINPAGE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4ProdSize.
     */
    public RowSet getLookup4ProdSize() {
        return (RowSet)getAttributeInternal(LOOKUP4PRODSIZE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4ReaderGroup.
     */
    public RowSet getLookup4ReaderGroup() {
        return (RowSet)getAttributeInternal(LOOKUP4READERGROUP);
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
