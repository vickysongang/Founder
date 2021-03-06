package com.zypg.cms.work.model.viewobject.entry;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jul 18 10:33:04 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsWorksEntryEditVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        Source {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getSource();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setSource((String)value);
            }
        }
        ,
        Author {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getAuthor();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setAuthor((String)value);
            }
        }
        ,
        Lang {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getLang();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setLang((String)value);
            }
        }
        ,
        Translator {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getTranslator();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setTranslator((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        AbstarctDesc {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getAbstarctDesc();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setAbstarctDesc((String)value);
            }
        }
        ,
        CategoryId {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getCategoryId();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setCategoryId((Number)value);
            }
        }
        ,
        CategoryName {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getCategoryName();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setCategoryName((String)value);
            }
        }
        ,
        Lookup4Lang {
            public Object get(CmsWorksEntryEditVORowImpl obj) {
                return obj.getLookup4Lang();
            }

            public void put(CmsWorksEntryEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsWorksEntryEditVORowImpl object);

        public abstract void put(CmsWorksEntryEditVORowImpl object, Object value);

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


    public static final int SOURCE = AttributesEnum.Source.index();
    public static final int AUTHOR = AttributesEnum.Author.index();
    public static final int LANG = AttributesEnum.Lang.index();
    public static final int TRANSLATOR = AttributesEnum.Translator.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int ABSTARCTDESC = AttributesEnum.AbstarctDesc.index();
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int CATEGORYNAME = AttributesEnum.CategoryName.index();
    public static final int LOOKUP4LANG = AttributesEnum.Lookup4Lang.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsWorksEntryEditVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Source.
     * @return the Source
     */
    public String getSource() {
        return (String) getAttributeInternal(SOURCE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Source.
     * @param value value to set the  Source
     */
    public void setSource(String value) {
        setAttributeInternal(SOURCE, value);
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
     * Gets the attribute value for the calculated attribute AbstarctDesc.
     * @return the AbstarctDesc
     */
    public String getAbstarctDesc() {
        return (String) getAttributeInternal(ABSTARCTDESC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AbstarctDesc.
     * @param value value to set the  AbstarctDesc
     */
    public void setAbstarctDesc(String value) {
        setAttributeInternal(ABSTARCTDESC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryId.
     * @return the CategoryId
     */
    public Number getCategoryId() {
        return (Number) getAttributeInternal(CATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryId.
     * @param value value to set the  CategoryId
     */
    public void setCategoryId(Number value) {
        setAttributeInternal(CATEGORYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryName.
     * @return the CategoryName
     */
    public String getCategoryName() {
        return (String) getAttributeInternal(CATEGORYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryName.
     * @param value value to set the  CategoryName
     */
    public void setCategoryName(String value) {
        setAttributeInternal(CATEGORYNAME, value);
    }


    /**
     * Gets the view accessor <code>RowSet</code> Lookup4Lang.
     */
    public RowSet getLookup4Lang() {
        return (RowSet)getAttributeInternal(LOOKUP4LANG);
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
