package com.zypg.cms.work.model.viewobject.newspaper.lov;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 21 13:21:06 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewspaperCategoryVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        NewspaperCategoryId {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getNewspaperCategoryId();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setNewspaperCategoryId((Number)value);
            }
        }
        ,
        NewspaperCategoryName {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getNewspaperCategoryName();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setNewspaperCategoryName((String)value);
            }
        }
        ,
        NewspaperType {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getNewspaperType();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setNewspaperType((String)value);
            }
        }
        ,
        NewspaperTypeMeaning {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getNewspaperTypeMeaning();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setNewspaperTypeMeaning((String)value);
            }
        }
        ,
        PaperType {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getPaperType();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setPaperType((String)value);
            }
        }
        ,
        ChiefEditor {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getChiefEditor();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setChiefEditor((String)value);
            }
        }
        ,
        Price {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getPrice();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setPrice((Integer)value);
            }
        }
        ,
        BookSize {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getBookSize();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setBookSize((String)value);
            }
        }
        ,
        BookSizeMeaning {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getBookSizeMeaning();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setBookSizeMeaning((String)value);
            }
        }
        ,
        Lang {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getLang();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setLang((String)value);
            }
        }
        ,
        LangMeaning {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getLangMeaning();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setLangMeaning((String)value);
            }
        }
        ,
        Cn {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getCn();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setCn((String)value);
            }
        }
        ,
        Issn {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getIssn();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setIssn((String)value);
            }
        }
        ,
        ProdSize {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getProdSize();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setProdSize((String)value);
            }
        }
        ,
        ProdSizeMeaning {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getProdSizeMeaning();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setProdSizeMeaning((String)value);
            }
        }
        ,
        ReaderGroup {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getReaderGroup();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setReaderGroup((String)value);
            }
        }
        ,
        ReaderGroupMeaning {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getReaderGroupMeaning();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setReaderGroupMeaning((String)value);
            }
        }
        ,
        Seq {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getSeq();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setSeq((Number)value);
            }
        }
        ,
        Publishing {
            public Object get(CmsNewspaperCategoryVORowImpl obj) {
                return obj.getPublishing();
            }

            public void put(CmsNewspaperCategoryVORowImpl obj, Object value) {
                obj.setPublishing((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsNewspaperCategoryVORowImpl object);

        public abstract void put(CmsNewspaperCategoryVORowImpl object, Object value);

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


    public static final int NEWSPAPERCATEGORYID = AttributesEnum.NewspaperCategoryId.index();
    public static final int NEWSPAPERCATEGORYNAME = AttributesEnum.NewspaperCategoryName.index();
    public static final int NEWSPAPERTYPE = AttributesEnum.NewspaperType.index();
    public static final int NEWSPAPERTYPEMEANING = AttributesEnum.NewspaperTypeMeaning.index();
    public static final int PAPERTYPE = AttributesEnum.PaperType.index();
    public static final int CHIEFEDITOR = AttributesEnum.ChiefEditor.index();
    public static final int PRICE = AttributesEnum.Price.index();
    public static final int BOOKSIZE = AttributesEnum.BookSize.index();
    public static final int BOOKSIZEMEANING = AttributesEnum.BookSizeMeaning.index();
    public static final int LANG = AttributesEnum.Lang.index();
    public static final int LANGMEANING = AttributesEnum.LangMeaning.index();
    public static final int CN = AttributesEnum.Cn.index();
    public static final int ISSN = AttributesEnum.Issn.index();
    public static final int PRODSIZE = AttributesEnum.ProdSize.index();
    public static final int PRODSIZEMEANING = AttributesEnum.ProdSizeMeaning.index();
    public static final int READERGROUP = AttributesEnum.ReaderGroup.index();
    public static final int READERGROUPMEANING = AttributesEnum.ReaderGroupMeaning.index();
    public static final int SEQ = AttributesEnum.Seq.index();
    public static final int PUBLISHING = AttributesEnum.Publishing.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewspaperCategoryVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperCategoryId.
     * @return the NewspaperCategoryId
     */
    public Number getNewspaperCategoryId() {
        return (Number) getAttributeInternal(NEWSPAPERCATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperCategoryId.
     * @param value value to set the  NewspaperCategoryId
     */
    public void setNewspaperCategoryId(Number value) {
        setAttributeInternal(NEWSPAPERCATEGORYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperCategoryName.
     * @return the NewspaperCategoryName
     */
    public String getNewspaperCategoryName() {
        return (String) getAttributeInternal(NEWSPAPERCATEGORYNAME);
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
        return (String) getAttributeInternal(NEWSPAPERTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperType.
     * @param value value to set the  NewspaperType
     */
    public void setNewspaperType(String value) {
        setAttributeInternal(NEWSPAPERTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperTypeMeaning.
     * @return the NewspaperTypeMeaning
     */
    public String getNewspaperTypeMeaning() {
        return (String) getAttributeInternal(NEWSPAPERTYPEMEANING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperTypeMeaning.
     * @param value value to set the  NewspaperTypeMeaning
     */
    public void setNewspaperTypeMeaning(String value) {
        setAttributeInternal(NEWSPAPERTYPEMEANING, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PaperType.
     * @return the PaperType
     */
    public String getPaperType() {
        return (String) getAttributeInternal(PAPERTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PaperType.
     * @param value value to set the  PaperType
     */
    public void setPaperType(String value) {
        setAttributeInternal(PAPERTYPE, value);
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
     * Gets the attribute value for the calculated attribute Price.
     * @return the Price
     */
    public Integer getPrice() {
        return (Integer) getAttributeInternal(PRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Price.
     * @param value value to set the  Price
     */
    public void setPrice(Integer value) {
        setAttributeInternal(PRICE, value);
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
     * Gets the attribute value for the calculated attribute BookSizeMeaning.
     * @return the BookSizeMeaning
     */
    public String getBookSizeMeaning() {
        return (String) getAttributeInternal(BOOKSIZEMEANING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BookSizeMeaning.
     * @param value value to set the  BookSizeMeaning
     */
    public void setBookSizeMeaning(String value) {
        setAttributeInternal(BOOKSIZEMEANING, value);
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
     * Gets the attribute value for the calculated attribute LangMeaning.
     * @return the LangMeaning
     */
    public String getLangMeaning() {
        return (String) getAttributeInternal(LANGMEANING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LangMeaning.
     * @param value value to set the  LangMeaning
     */
    public void setLangMeaning(String value) {
        setAttributeInternal(LANGMEANING, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Cn.
     * @return the Cn
     */
    public String getCn() {
        return (String) getAttributeInternal(CN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Cn.
     * @param value value to set the  Cn
     */
    public void setCn(String value) {
        setAttributeInternal(CN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Issn.
     * @return the Issn
     */
    public String getIssn() {
        return (String) getAttributeInternal(ISSN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Issn.
     * @param value value to set the  Issn
     */
    public void setIssn(String value) {
        setAttributeInternal(ISSN, value);
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
     * Gets the attribute value for the calculated attribute ProdSizeMeaning.
     * @return the ProdSizeMeaning
     */
    public String getProdSizeMeaning() {
        return (String) getAttributeInternal(PRODSIZEMEANING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProdSizeMeaning.
     * @param value value to set the  ProdSizeMeaning
     */
    public void setProdSizeMeaning(String value) {
        setAttributeInternal(PRODSIZEMEANING, value);
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
     * Gets the attribute value for the calculated attribute ReaderGroupMeaning.
     * @return the ReaderGroupMeaning
     */
    public String getReaderGroupMeaning() {
        return (String) getAttributeInternal(READERGROUPMEANING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ReaderGroupMeaning.
     * @param value value to set the  ReaderGroupMeaning
     */
    public void setReaderGroupMeaning(String value) {
        setAttributeInternal(READERGROUPMEANING, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Seq.
     * @return the Seq
     */
    public Number getSeq() {
        return (Number) getAttributeInternal(SEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Seq.
     * @param value value to set the  Seq
     */
    public void setSeq(Number value) {
        setAttributeInternal(SEQ, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Publishing.
     * @return the Publishing
     */
    public String getPublishing() {
        return (String) getAttributeInternal(PUBLISHING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Publishing.
     * @param value value to set the  Publishing
     */
    public void setPublishing(String value) {
        setAttributeInternal(PUBLISHING, value);
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