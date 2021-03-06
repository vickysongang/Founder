package com.zypg.cms.work.model.viewobject.log.query;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.NClobDomain;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 29 12:06:27 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsProcessLogVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        LogId {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getLogId();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setLogId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setDocId((BigDecimal)value);
            }
        }
        ,
        ProcessOperator {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getProcessOperator();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setProcessOperator((String)value);
            }
        }
        ,
        ProcessOperatorName {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getProcessOperatorName();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setProcessOperatorName((String)value);
            }
        }
        ,
        Operation {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getOperation();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setOperation((String)value);
            }
        }
        ,
        ProcessStartPoint {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getProcessStartPoint();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setProcessStartPoint((String)value);
            }
        }
        ,
        ProcessEndPoint {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getProcessEndPoint();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setProcessEndPoint((String)value);
            }
        }
        ,
        OperationTime {
            public Object get(CmsProcessLogVORowImpl obj) {
                return obj.getOperationTime();
            }

            public void put(CmsProcessLogVORowImpl obj, Object value) {
                obj.setOperationTime((Date)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsProcessLogVORowImpl object);

        public abstract void put(CmsProcessLogVORowImpl object, Object value);

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


    public static final int LOGID = AttributesEnum.LogId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int PROCESSOPERATOR = AttributesEnum.ProcessOperator.index();
    public static final int PROCESSOPERATORNAME = AttributesEnum.ProcessOperatorName.index();
    public static final int OPERATION = AttributesEnum.Operation.index();
    public static final int PROCESSSTARTPOINT = AttributesEnum.ProcessStartPoint.index();
    public static final int PROCESSENDPOINT = AttributesEnum.ProcessEndPoint.index();
    public static final int OPERATIONTIME = AttributesEnum.OperationTime.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsProcessLogVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute LogId.
     * @return the LogId
     */
    public Number getLogId() {
        return (Number)getAttributeInternal(LOGID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LogId.
     * @param value value to set the  LogId
     */
    public void setLogId(Number value) {
        setAttributeInternal(LOGID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DocId.
     * @return the DocId
     */
    public BigDecimal getDocId() {
        return (BigDecimal)getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocId.
     * @param value value to set the  DocId
     */
    public void setDocId(BigDecimal value) {
        setAttributeInternal(DOCID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProcessOperator.
     * @return the ProcessOperator
     */
    public String getProcessOperator() {
        return (String)getAttributeInternal(PROCESSOPERATOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProcessOperator.
     * @param value value to set the  ProcessOperator
     */
    public void setProcessOperator(String value) {
        setAttributeInternal(PROCESSOPERATOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProcessOperatorName.
     * @return the ProcessOperatorName
     */
    public String getProcessOperatorName() {
        return (String) getAttributeInternal(PROCESSOPERATORNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProcessOperatorName.
     * @param value value to set the  ProcessOperatorName
     */
    public void setProcessOperatorName(String value) {
        setAttributeInternal(PROCESSOPERATORNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Operation.
     * @return the Operation
     */
    public String getOperation() {
        return (String)getAttributeInternal(OPERATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Operation.
     * @param value value to set the  Operation
     */
    public void setOperation(String value) {
        setAttributeInternal(OPERATION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProcessStartPoint.
     * @return the ProcessStartPoint
     */
    public String getProcessStartPoint() {
        return (String)getAttributeInternal(PROCESSSTARTPOINT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProcessStartPoint.
     * @param value value to set the  ProcessStartPoint
     */
    public void setProcessStartPoint(String value) {
        setAttributeInternal(PROCESSSTARTPOINT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProcessEndPoint.
     * @return the ProcessEndPoint
     */
    public String getProcessEndPoint() {
        return (String)getAttributeInternal(PROCESSENDPOINT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProcessEndPoint.
     * @param value value to set the  ProcessEndPoint
     */
    public void setProcessEndPoint(String value) {
        setAttributeInternal(PROCESSENDPOINT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OperationTime.
     * @return the OperationTime
     */
    public Date getOperationTime() {
        return (Date)getAttributeInternal(OPERATIONTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OperationTime.
     * @param value value to set the  OperationTime
     */
    public void setOperationTime(Date value) {
        setAttributeInternal(OPERATIONTIME, value);
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
