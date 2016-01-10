package com.zypg.cms.work.model.viewobject.activity.query;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Apr 24 15:14:48 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsActivityVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        ActivityId {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getActivityId();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setActivityId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        ActivityTheme {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getActivityTheme();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setActivityTheme((String)value);
            }
        }
        ,
        ActivityLocation {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getActivityLocation();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setActivityLocation((String)value);
            }
        }
        ,
        ActivityStartTime {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getActivityStartTime();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setActivityStartTime((Date)value);
            }
        }
        ,
        ActivityEndTime {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getActivityEndTime();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setActivityEndTime((Date)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        Status {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        LibCode {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getLibCode();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setLibCode((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        Checked {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getChecked();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setChecked((Boolean)value);
            }
        }
        ,
        CategoryId {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getCategoryId();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setCategoryId((Number)value);
            }
        }
        ,
        CategoryGroupType {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getCategoryGroupType();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setCategoryGroupType((String)value);
            }
        }
        ,
        DeleteFlag {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getDeleteFlag();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setDeleteFlag((String)value);
            }
        }
        ,
        SyncFlag {
            public Object get(CmsActivityVORowImpl obj) {
                return obj.getSyncFlag();
            }

            public void put(CmsActivityVORowImpl obj, Object value) {
                obj.setSyncFlag((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsActivityVORowImpl object);

        public abstract void put(CmsActivityVORowImpl object, Object value);

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


    public static final int ACTIVITYID = AttributesEnum.ActivityId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int ACTIVITYTHEME = AttributesEnum.ActivityTheme.index();
    public static final int ACTIVITYLOCATION = AttributesEnum.ActivityLocation.index();
    public static final int ACTIVITYSTARTTIME = AttributesEnum.ActivityStartTime.index();
    public static final int ACTIVITYENDTIME = AttributesEnum.ActivityEndTime.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int LIBCODE = AttributesEnum.LibCode.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int CHECKED = AttributesEnum.Checked.index();
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int CATEGORYGROUPTYPE = AttributesEnum.CategoryGroupType.index();
    public static final int DELETEFLAG = AttributesEnum.DeleteFlag.index();
    public static final int SYNCFLAG = AttributesEnum.SyncFlag.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsActivityVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityId.
     * @return the ActivityId
     */
    public Number getActivityId() {
        return (Number)getAttributeInternal(ACTIVITYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityId.
     * @param value value to set the  ActivityId
     */
    public void setActivityId(Number value) {
        setAttributeInternal(ACTIVITYID, value);
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
     * Gets the attribute value for the calculated attribute ActivityTheme.
     * @return the ActivityTheme
     */
    public String getActivityTheme() {
        return (String)getAttributeInternal(ACTIVITYTHEME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityTheme.
     * @param value value to set the  ActivityTheme
     */
    public void setActivityTheme(String value) {
        setAttributeInternal(ACTIVITYTHEME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ActivityLocation.
     * @return the ActivityLocation
     */
    public String getActivityLocation() {
        return (String)getAttributeInternal(ACTIVITYLOCATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityLocation.
     * @param value value to set the  ActivityLocation
     */
    public void setActivityLocation(String value) {
        setAttributeInternal(ACTIVITYLOCATION, value);
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
        return (Date)getAttributeInternal(ACTIVITYENDTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActivityEndTime.
     * @param value value to set the  ActivityEndTime
     */
    public void setActivityEndTime(Date value) {
        setAttributeInternal(ACTIVITYENDTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LastUpdateDate.
     * @return the LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
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
     * Gets the attribute value for the calculated attribute Checked.
     * @return the Checked
     */
    public Boolean getChecked() {
        Boolean result = null;
        Hashtable map = this.getDBTransaction().getSession().getUserData();
        String status = null;
        if ("Y".equals(this.getDeleteFlag())) {
            status = "DELETED_"+this.getLibCode();
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
