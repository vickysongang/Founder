package com.zypg.cms.work.model.viewobject.copyright.query;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 04 17:02:25 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Cms3rdAuthorizationItemVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        ItemId {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getItemId();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setItemId((Number)value);
            }
        }
        ,
        BookName {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getBookName();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setBookName((String)value);
            }
        }
        ,
        Isbn {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getIsbn();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setIsbn((String)value);
            }
        }
        ,
        Author {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getAuthor();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setAuthor((String)value);
            }
        }
        ,
        AuthorizedCompany {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getAuthorizedCompany();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setAuthorizedCompany((Number)value);
            }
        }
        ,
        OrderName {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getOrderName();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setOrderName((String)value);
            }
        }
        ,
        OrderId {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getOrderId();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setOrderId((Number)value);
            }
        }
        ,
        OrderDocId {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getOrderDocId();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setOrderDocId((Number)value);
            }
        }
        ,
        AuthorDocId {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getAuthorDocId();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setAuthorDocId((Number)value);
            }
        }
        ,
        AuthorizedStartTime {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getAuthorizedStartTime();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setAuthorizedStartTime((Date)value);
            }
        }
        ,
        AuthorizedEndTime {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getAuthorizedEndTime();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setAuthorizedEndTime((Timestamp)value);
            }
        }
        ,
        ExclusiveCopyrightFlag {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getExclusiveCopyrightFlag();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setExclusiveCopyrightFlag((String)value);
            }
        }
        ,
        ExclusiveCopyrightDisplay {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getExclusiveCopyrightDisplay();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setExclusiveCopyrightDisplay((String)value);
            }
        }
        ,
        SalesTotalCost {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getSalesTotalCost();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setSalesTotalCost((Number)value);
            }
        }
        ,
        SalesTotalNum {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getSalesTotalNum();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setSalesTotalNum((Number)value);
            }
        }
        ,
        CompanyName {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getCompanyName();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setCompanyName((String)value);
            }
        }
        ,
        ExpireMonths {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getExpireMonths();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setExpireMonths((BigDecimal)value);
            }
        }
        ,
        Cms3rdAuthorizationItemsVO {
            public Object get(Cms3rdAuthorizationItemVORowImpl obj) {
                return obj.getCms3rdAuthorizationItemsVO();
            }

            public void put(Cms3rdAuthorizationItemVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(Cms3rdAuthorizationItemVORowImpl object);

        public abstract void put(Cms3rdAuthorizationItemVORowImpl object, Object value);

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


    public static final int ITEMID = AttributesEnum.ItemId.index();
    public static final int BOOKNAME = AttributesEnum.BookName.index();
    public static final int ISBN = AttributesEnum.Isbn.index();
    public static final int AUTHOR = AttributesEnum.Author.index();
    public static final int AUTHORIZEDCOMPANY = AttributesEnum.AuthorizedCompany.index();
    public static final int ORDERNAME = AttributesEnum.OrderName.index();
    public static final int ORDERID = AttributesEnum.OrderId.index();
    public static final int ORDERDOCID = AttributesEnum.OrderDocId.index();
    public static final int AUTHORDOCID = AttributesEnum.AuthorDocId.index();
    public static final int AUTHORIZEDSTARTTIME = AttributesEnum.AuthorizedStartTime.index();
    public static final int AUTHORIZEDENDTIME = AttributesEnum.AuthorizedEndTime.index();
    public static final int EXCLUSIVECOPYRIGHTFLAG = AttributesEnum.ExclusiveCopyrightFlag.index();
    public static final int EXCLUSIVECOPYRIGHTDISPLAY = AttributesEnum.ExclusiveCopyrightDisplay.index();
    public static final int SALESTOTALCOST = AttributesEnum.SalesTotalCost.index();
    public static final int SALESTOTALNUM = AttributesEnum.SalesTotalNum.index();
    public static final int COMPANYNAME = AttributesEnum.CompanyName.index();
    public static final int EXPIREMONTHS = AttributesEnum.ExpireMonths.index();
    public static final int CMS3RDAUTHORIZATIONITEMSVO = AttributesEnum.Cms3rdAuthorizationItemsVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Cms3rdAuthorizationItemVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute ItemId.
     * @return the ItemId
     */
    public Number getItemId() {
        return (Number) getAttributeInternal(ITEMID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemId.
     * @param value value to set the  ItemId
     */
    public void setItemId(Number value) {
        setAttributeInternal(ITEMID, value);
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
     * Gets the attribute value for the calculated attribute AuthorizedCompany.
     * @return the AuthorizedCompany
     */
    public Number getAuthorizedCompany() {
        return (Number) getAttributeInternal(AUTHORIZEDCOMPANY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AuthorizedCompany.
     * @param value value to set the  AuthorizedCompany
     */
    public void setAuthorizedCompany(Number value) {
        setAttributeInternal(AUTHORIZEDCOMPANY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OrderName.
     * @return the OrderName
     */
    public String getOrderName() {
        return (String) getAttributeInternal(ORDERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OrderName.
     * @param value value to set the  OrderName
     */
    public void setOrderName(String value) {
        setAttributeInternal(ORDERNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OrderId.
     * @return the OrderId
     */
    public Number getOrderId() {
        return (Number) getAttributeInternal(ORDERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OrderId.
     * @param value value to set the  OrderId
     */
    public void setOrderId(Number value) {
        setAttributeInternal(ORDERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OrderDocId.
     * @return the OrderDocId
     */
    public Number getOrderDocId() {
        return (Number) getAttributeInternal(ORDERDOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OrderDocId.
     * @param value value to set the  OrderDocId
     */
    public void setOrderDocId(Number value) {
        setAttributeInternal(ORDERDOCID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute AuthorDocId.
     * @return the AuthorDocId
     */
    public Number getAuthorDocId() {
        return (Number) getAttributeInternal(AUTHORDOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AuthorDocId.
     * @param value value to set the  AuthorDocId
     */
    public void setAuthorDocId(Number value) {
        setAttributeInternal(AUTHORDOCID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute AuthorizedStartTime.
     * @return the AuthorizedStartTime
     */
    public Date getAuthorizedStartTime() {
        return (Date) getAttributeInternal(AUTHORIZEDSTARTTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AuthorizedStartTime.
     * @param value value to set the  AuthorizedStartTime
     */
    public void setAuthorizedStartTime(Date value) {
        setAttributeInternal(AUTHORIZEDSTARTTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute AuthorizedEndTime.
     * @return the AuthorizedEndTime
     */
    public Timestamp getAuthorizedEndTime() {
        return (Timestamp) getAttributeInternal(AUTHORIZEDENDTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AuthorizedEndTime.
     * @param value value to set the  AuthorizedEndTime
     */
    public void setAuthorizedEndTime(Timestamp value) {
        setAttributeInternal(AUTHORIZEDENDTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ExclusiveCopyrightFlag.
     * @return the ExclusiveCopyrightFlag
     */
    public String getExclusiveCopyrightFlag() {
        return (String) getAttributeInternal(EXCLUSIVECOPYRIGHTFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ExclusiveCopyrightFlag.
     * @param value value to set the  ExclusiveCopyrightFlag
     */
    public void setExclusiveCopyrightFlag(String value) {
        setAttributeInternal(EXCLUSIVECOPYRIGHTFLAG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ExclusiveCopyrightDisplay.
     * @return the ExclusiveCopyrightDisplay
     */
    public String getExclusiveCopyrightDisplay() {
        return (String) getAttributeInternal(EXCLUSIVECOPYRIGHTDISPLAY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ExclusiveCopyrightDisplay.
     * @param value value to set the  ExclusiveCopyrightDisplay
     */
    public void setExclusiveCopyrightDisplay(String value) {
        setAttributeInternal(EXCLUSIVECOPYRIGHTDISPLAY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SalesTotalCost.
     * @return the SalesTotalCost
     */
    public Number getSalesTotalCost() {
        return (Number) getAttributeInternal(SALESTOTALCOST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SalesTotalCost.
     * @param value value to set the  SalesTotalCost
     */
    public void setSalesTotalCost(Number value) {
        setAttributeInternal(SALESTOTALCOST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SalesTotalNum.
     * @return the SalesTotalNum
     */
    public Number getSalesTotalNum() {
        return (Number) getAttributeInternal(SALESTOTALNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SalesTotalNum.
     * @param value value to set the  SalesTotalNum
     */
    public void setSalesTotalNum(Number value) {
        setAttributeInternal(SALESTOTALNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompanyName.
     * @return the CompanyName
     */
    public String getCompanyName() {
        return (String) getAttributeInternal(COMPANYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompanyName.
     * @param value value to set the  CompanyName
     */
    public void setCompanyName(String value) {
        setAttributeInternal(COMPANYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ExpireMonths.
     * @return the ExpireMonths
     */
    public BigDecimal getExpireMonths() {
        return (BigDecimal) getAttributeInternal(EXPIREMONTHS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ExpireMonths.
     * @param value value to set the  ExpireMonths
     */
    public void setExpireMonths(BigDecimal value) {
        setAttributeInternal(EXPIREMONTHS, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link Cms3rdAuthorizationItemsVO.
     */
    public RowIterator getCms3rdAuthorizationItemsVO() {
        return (RowIterator)getAttributeInternal(CMS3RDAUTHORIZATIONITEMSVO);
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
