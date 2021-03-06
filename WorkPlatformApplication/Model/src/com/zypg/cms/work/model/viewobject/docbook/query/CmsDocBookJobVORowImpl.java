package com.zypg.cms.work.model.viewobject.docbook.query;

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
// ---    Mon May 12 17:47:05 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsDocBookJobVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        DocId {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        JobName {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getJobName();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setJobName((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        CompName {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getCompName();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setCompName((String)value);
            }
        }
        ,
        Isbn {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getIsbn();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setIsbn((String)value);
            }
        }
        ,
        Status {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        CreationDate {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getCreationDate();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        Result {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getResult();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setResult((String)value);
            }
        }
        ,
        Checked {
            public Object get(CmsDocBookJobVORowImpl obj) {
                return obj.getChecked();
            }

            public void put(CmsDocBookJobVORowImpl obj, Object value) {
                obj.setChecked((Boolean)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsDocBookJobVORowImpl object);

        public abstract void put(CmsDocBookJobVORowImpl object, Object value);

        public int index() {
            return com.zypg.cms.work.model.viewobject.docbook.query.CmsDocBookJobVORowImpl.AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return com.zypg.cms.work.model.viewobject.docbook.query.CmsDocBookJobVORowImpl.AttributesEnum.firstIndex() + com.zypg.cms.work.model.viewobject.docbook.query.CmsDocBookJobVORowImpl.AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = com.zypg.cms.work.model.viewobject.docbook.query.CmsDocBookJobVORowImpl.AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int JOBNAME = AttributesEnum.JobName.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int COMPNAME = AttributesEnum.CompName.index();
    public static final int ISBN = AttributesEnum.Isbn.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int RESULT = AttributesEnum.Result.index();
    public static final int CHECKED = AttributesEnum.Checked.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsDocBookJobVORowImpl() {
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
     * Gets the attribute value for the calculated attribute JobName.
     * @return the JobName
     */
    public String getJobName() {
        return (String)getAttributeInternal(JOBNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute JobName.
     * @param value value to set the  JobName
     */
    public void setJobName(String value) {
        setAttributeInternal(JOBNAME, value);
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
     * Gets the attribute value for the calculated attribute CompName.
     * @return the CompName
     */
    public String getCompName() {
        return (String) getAttributeInternal(COMPNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompName.
     * @param value value to set the  CompName
     */
    public void setCompName(String value) {
        setAttributeInternal(COMPNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Isbn.
     * @return the Isbn
     */
    public String getIsbn() {
        return (String)getAttributeInternal(ISBN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Isbn.
     * @param value value to set the  Isbn
     */
    public void setIsbn(String value) {
        setAttributeInternal(ISBN, value);
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
     * Gets the attribute value for the calculated attribute CreationDate.
     * @return the CreationDate
     */
    public Date getCreationDate() {
        return (Date) getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CreationDate.
     * @param value value to set the  CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Result.
     * @return the Result
     */
    public String getResult() {
        return (String)getAttributeInternal(RESULT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Result.
     * @param value value to set the  Result
     */
    public void setResult(String value) {
        setAttributeInternal(RESULT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Checked.
     * @return the Checked
     */
    public Boolean getChecked() {
        Boolean result = null;
        Hashtable map = this.getDBTransaction().getSession().getUserData();
        String selectAllFlag = (String)map.get(this.getStatus() + "_selectAllFlag");
        String key = this.getStatus() + "_" + this.getDocId();
        List<String> currSelectedList = (List<String>)map.get(this.getStatus() + "_currSelected");
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
