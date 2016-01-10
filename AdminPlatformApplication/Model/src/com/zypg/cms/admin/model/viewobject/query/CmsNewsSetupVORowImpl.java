package com.zypg.cms.admin.model.viewobject.query;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Aug 27 20:35:53 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewsSetupVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        SetupId {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getSetupId();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setSetupId((Number)value);
            }
        }
        ,
        NewspaperCategoryId {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getNewspaperCategoryId();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setNewspaperCategoryId((Number)value);
            }
        }
        ,
        Year {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getYear();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setYear((Number)value);
            }
        }
        ,
        NewsNum {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getNewsNum();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setNewsNum((Number)value);
            }
        }
        ,
        PublicDate {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getPublicDate();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setPublicDate((Date)value);
            }
        }
        ,
        PublicDateDisplay {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getPublicDateDisplay();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setPublicDateDisplay((String)value);
            }
        }
        ,
        Weekday {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getWeekday();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setWeekday((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsNewsSetupVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsNewsSetupVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsNewsSetupVORowImpl object);

        public abstract void put(CmsNewsSetupVORowImpl object, Object value);

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


    public static final int SETUPID = AttributesEnum.SetupId.index();
    public static final int NEWSPAPERCATEGORYID = AttributesEnum.NewspaperCategoryId.index();
    public static final int YEAR = AttributesEnum.Year.index();
    public static final int NEWSNUM = AttributesEnum.NewsNum.index();
    public static final int PUBLICDATE = AttributesEnum.PublicDate.index();
    public static final int PUBLICDATEDISPLAY = AttributesEnum.PublicDateDisplay.index();
    public static final int WEEKDAY = AttributesEnum.Weekday.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewsSetupVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute SetupId.
     * @return the SetupId
     */
    public Number getSetupId() {
        return (Number) getAttributeInternal(SETUPID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SetupId.
     * @param value value to set the  SetupId
     */
    public void setSetupId(Number value) {
        setAttributeInternal(SETUPID, value);
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
     * Gets the attribute value for the calculated attribute Year.
     * @return the Year
     */
    public Number getYear() {
        return (Number) getAttributeInternal(YEAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Year.
     * @param value value to set the  Year
     */
    public void setYear(Number value) {
        setAttributeInternal(YEAR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewsNum.
     * @return the NewsNum
     */
    public Number getNewsNum() {
        return (Number) getAttributeInternal(NEWSNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewsNum.
     * @param value value to set the  NewsNum
     */
    public void setNewsNum(Number value) {
        setAttributeInternal(NEWSNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PublicDate.
     * @return the PublicDate
     */
    public Date getPublicDate() {
        return (Date) getAttributeInternal(PUBLICDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PublicDate.
     * @param value value to set the  PublicDate
     */
    public void setPublicDate(Date value) {
        setAttributeInternal(PUBLICDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PublicDateDisplay.
     * @return the PublicDateDisplay
     */
    public String getPublicDateDisplay() {
        return (String) getAttributeInternal(PUBLICDATEDISPLAY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PublicDateDisplay.
     * @param value value to set the  PublicDateDisplay
     */
    public void setPublicDateDisplay(String value) {
        setAttributeInternal(PUBLICDATEDISPLAY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Weekday.
     * @return the Weekday
     */
    public String getWeekday() {
        return (String) getAttributeInternal(WEEKDAY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Weekday.
     * @param value value to set the  Weekday
     */
    public void setWeekday(String value) {
        setAttributeInternal(WEEKDAY, value);
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
