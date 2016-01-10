package com.zypg.cms.work.model.viewobject.newspaper;

import oracle.jbo.RowSet;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 21 14:41:07 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewspaperEditVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Year {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getYear();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setYear((String)value);
            }
        }
        ,
        ChiefEditor {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getChiefEditor();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setChiefEditor((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        FormatDesign {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getFormatDesign();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setFormatDesign((String)value);
            }
        }
        ,
        CoverDesign {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCoverDesign();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setCoverDesign((String)value);
            }
        }
        ,
        Price {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getPrice();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setPrice((String)value);
            }
        }
        ,
        MainPage {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getMainPage();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setMainPage((String)value);
            }
        }
        ,
        ColorNum {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getColorNum();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setColorNum((String)value);
            }
        }
        ,
        MainTypeset {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getMainTypeset();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setMainTypeset((String)value);
            }
        }
        ,
        CoverTypeset {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCoverTypeset();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setCoverTypeset((String)value);
            }
        }
        ,
        TypesetComp {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getTypesetComp();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setTypesetComp((String)value);
            }
        }
        ,
        AssortProd {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getAssortProd();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAssortProd((String)value);
            }
        }
        ,
        Suggestion {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getSuggestion();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setSuggestion((String)value);
            }
        }
        ,
        Editor {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getEditor();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setEditor((String)value);
            }
        }
        ,
        Pagination {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getPagination();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setPagination((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        CmsLookupValueVO1 {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCmsLookupValueVO1();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CmsLookupValueVO2 {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCmsLookupValueVO2();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CmsLookupValueVO3 {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCmsLookupValueVO3();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        CmsLookupValueVO4 {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCmsLookupValueVO4();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsNewspaperEditVORowImpl object);

        public abstract void put(CmsNewspaperEditVORowImpl object, Object value);

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


    public static final int YEAR = AttributesEnum.Year.index();
    public static final int CHIEFEDITOR = AttributesEnum.ChiefEditor.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int FORMATDESIGN = AttributesEnum.FormatDesign.index();
    public static final int COVERDESIGN = AttributesEnum.CoverDesign.index();
    public static final int PRICE = AttributesEnum.Price.index();
    public static final int MAINPAGE = AttributesEnum.MainPage.index();
    public static final int COLORNUM = AttributesEnum.ColorNum.index();
    public static final int MAINTYPESET = AttributesEnum.MainTypeset.index();
    public static final int COVERTYPESET = AttributesEnum.CoverTypeset.index();
    public static final int TYPESETCOMP = AttributesEnum.TypesetComp.index();
    public static final int ASSORTPROD = AttributesEnum.AssortProd.index();
    public static final int SUGGESTION = AttributesEnum.Suggestion.index();
    public static final int EDITOR = AttributesEnum.Editor.index();
    public static final int PAGINATION = AttributesEnum.Pagination.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int CMSLOOKUPVALUEVO1 = AttributesEnum.CmsLookupValueVO1.index();
    public static final int CMSLOOKUPVALUEVO2 = AttributesEnum.CmsLookupValueVO2.index();
    public static final int CMSLOOKUPVALUEVO3 = AttributesEnum.CmsLookupValueVO3.index();
    public static final int CMSLOOKUPVALUEVO4 = AttributesEnum.CmsLookupValueVO4.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewspaperEditVORowImpl() {
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
     * Gets the view accessor <code>RowSet</code> CmsLookupValueVO1.
     */
    public RowSet getCmsLookupValueVO1() {
        return (RowSet)getAttributeInternal(CMSLOOKUPVALUEVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> CmsLookupValueVO2.
     */
    public RowSet getCmsLookupValueVO2() {
        return (RowSet)getAttributeInternal(CMSLOOKUPVALUEVO2);
    }

    /**
     * Gets the view accessor <code>RowSet</code> CmsLookupValueVO3.
     */
    public RowSet getCmsLookupValueVO3() {
        return (RowSet)getAttributeInternal(CMSLOOKUPVALUEVO3);
    }

    /**
     * Gets the view accessor <code>RowSet</code> CmsLookupValueVO4.
     */
    public RowSet getCmsLookupValueVO4() {
        return (RowSet)getAttributeInternal(CMSLOOKUPVALUEVO4);
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
