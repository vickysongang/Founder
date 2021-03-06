package com.zypg.cms.work.model.viewobject.resexp.query;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 27 13:41:20 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsResExpApplyHVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        DocId {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        ApplyName {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getApplyName();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setApplyName((String)value);
            }
        }
        ,
        UserId {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getUserId();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setUserId((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        Status {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        StatusName {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getStatusName();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setStatusName((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        Checked {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getChecked();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setChecked((Boolean)value);
            }
        }
        ,
        SimpleStatus {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getSimpleStatus();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setSimpleStatus((String)value);
            }
        }
        ,
        ExportReason {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getExportReason();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setExportReason((String)value);
            }
        }
        ,
        DownloadType {
            public Object get(CmsResExpApplyHVORowImpl obj) {
                return obj.getDownloadType();
            }

            public void put(CmsResExpApplyHVORowImpl obj, Object value) {
                obj.setDownloadType((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsResExpApplyHVORowImpl object);

        public abstract void put(CmsResExpApplyHVORowImpl object, Object value);

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


    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int APPLYNAME = AttributesEnum.ApplyName.index();
    public static final int USERID = AttributesEnum.UserId.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int STATUSNAME = AttributesEnum.StatusName.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int CHECKED = AttributesEnum.Checked.index();
    public static final int SIMPLESTATUS = AttributesEnum.SimpleStatus.index();
    public static final int EXPORTREASON = AttributesEnum.ExportReason.index();
    public static final int DOWNLOADTYPE = AttributesEnum.DownloadType.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsResExpApplyHVORowImpl() {
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
     * Gets the attribute value for the calculated attribute ApplyName.
     * @return the ApplyName
     */
    public String getApplyName() {
        return (String)getAttributeInternal(APPLYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ApplyName.
     * @param value value to set the  ApplyName
     */
    public void setApplyName(String value) {
        setAttributeInternal(APPLYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute UserId.
     * @return the UserId
     */
    public Number getUserId() {
        return (Number)getAttributeInternal(USERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute UserId.
     * @param value value to set the  UserId
     */
    public void setUserId(Number value) {
        setAttributeInternal(USERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CreationDate.
     * @return the CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CreationDate.
     * @param value value to set the  CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
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
     * Gets the attribute value for the calculated attribute StatusName.
     * @return the StatusName
     */
    public String getStatusName() {
        return (String)getAttributeInternal(STATUSNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute StatusName.
     * @param value value to set the  StatusName
     */
    public void setStatusName(String value) {
        setAttributeInternal(STATUSNAME, value);
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
     * Gets the attribute value for the calculated attribute SimpleStatus.
     * @return the SimpleStatus
     */
    public String getSimpleStatus() {
        return (String)getAttributeInternal(SIMPLESTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SimpleStatus.
     * @param value value to set the  SimpleStatus
     */
    public void setSimpleStatus(String value) {
        setAttributeInternal(SIMPLESTATUS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ExportReason.
     * @return the ExportReason
     */
    public String getExportReason() {
        return (String) getAttributeInternal(EXPORTREASON);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ExportReason.
     * @param value value to set the  ExportReason
     */
    public void setExportReason(String value) {
        setAttributeInternal(EXPORTREASON, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DownloadType.
     * @return the DownloadType
     */
    public String getDownloadType() {
        return (String) getAttributeInternal(DOWNLOADTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DownloadType.
     * @param value value to set the  DownloadType
     */
    public void setDownloadType(String value) {
        setAttributeInternal(DOWNLOADTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Checked.
     * @return the Checked
     */
    public Boolean getChecked() {
        Boolean result = null;
        Hashtable map = this.getDBTransaction().getSession().getUserData();
        String selectAllFlag = (String)map.get(this.getSimpleStatus() + "_selectAllFlag");
        String key = this.getSimpleStatus() + "_" + this.getDocId();
        List<String> currSelectedList = (List<String>)map.get(this.getSimpleStatus() + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
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
