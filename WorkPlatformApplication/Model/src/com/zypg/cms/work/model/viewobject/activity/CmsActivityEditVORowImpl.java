package com.zypg.cms.work.model.viewobject.activity;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 06 11:56:06 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsActivityEditVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        ActivityTheme {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getActivityTheme();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setActivityTheme((String)value);
            }
        }
        ,
        ActivityStartTime {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getActivityStartTime();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setActivityStartTime((Date)value);
            }
        }
        ,
        ActivityEndTime {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getActivityEndTime();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setActivityEndTime((Date)value);
            }
        }
        ,
        ActivityLocation {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getActivityLocation();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setActivityLocation((String)value);
            }
        }
        ,
        ActivityType {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getActivityType();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setActivityType((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        ActivityDesc {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getActivityDesc();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setActivityDesc((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        CategoryId {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getCategoryId();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setCategoryId((Number)value);
            }
        }
        ,
        CategoryName {
            public Object get(CmsActivityEditVORowImpl obj) {
                return obj.getCategoryName();
            }

            public void put(CmsActivityEditVORowImpl obj, Object value) {
                obj.setCategoryName((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsActivityEditVORowImpl object);

        public abstract void put(CmsActivityEditVORowImpl object, Object value);

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


    public static final int ACTIVITYTHEME = AttributesEnum.ActivityTheme.index();
    public static final int ACTIVITYSTARTTIME = AttributesEnum.ActivityStartTime.index();
    public static final int ACTIVITYENDTIME = AttributesEnum.ActivityEndTime.index();
    public static final int ACTIVITYLOCATION = AttributesEnum.ActivityLocation.index();
    public static final int ACTIVITYTYPE = AttributesEnum.ActivityType.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int ACTIVITYDESC = AttributesEnum.ActivityDesc.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int CATEGORYNAME = AttributesEnum.CategoryName.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsActivityEditVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityTheme.
     * @return the ActivityTheme
     */
    public String getActivityTheme() {
        return (String) getAttributeInternal(ACTIVITYTHEME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityTheme.
     * @param value value to set the  ActivityTheme
     */
    public void setActivityTheme(String value) {
        setAttributeInternal(ACTIVITYTHEME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityStartTime.
     * @return the ActivityStartTime
     */
    public Date getActivityStartTime() {
        return (Date)getAttributeInternal(ACTIVITYSTARTTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityStartTime.
     * @param value value to set the  ActivityStartTime
     */
    public void setActivityStartTime(Date value) {
        setAttributeInternal(ACTIVITYSTARTTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityEndTime.
     * @return the ActivityEndTime
     */
    public Date getActivityEndTime() {
        return (Date) getAttributeInternal(ACTIVITYENDTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityEndTime.
     * @param value value to set the  ActivityEndTime
     */
    public void setActivityEndTime(Date value) {
        setAttributeInternal(ACTIVITYENDTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityLocation.
     * @return the ActivityLocation
     */
    public String getActivityLocation() {
        return (String) getAttributeInternal(ACTIVITYLOCATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityLocation.
     * @param value value to set the  ActivityLocation
     */
    public void setActivityLocation(String value) {
        setAttributeInternal(ACTIVITYLOCATION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityType.
     * @return the ActivityType
     */
    public String getActivityType() {
        return (String) getAttributeInternal(ACTIVITYTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityType.
     * @param value value to set the  ActivityType
     */
    public void setActivityType(String value) {
        setAttributeInternal(ACTIVITYTYPE, value);
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
     * Gets the attribute value for the calculated attribute ActivityDesc.
     * @return the ActivityDesc
     */
    public String getActivityDesc() {
        return (String) getAttributeInternal(ACTIVITYDESC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityDesc.
     * @param value value to set the  ActivityDesc
     */
    public void setActivityDesc(String value) {
        setAttributeInternal(ACTIVITYDESC, value);
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
