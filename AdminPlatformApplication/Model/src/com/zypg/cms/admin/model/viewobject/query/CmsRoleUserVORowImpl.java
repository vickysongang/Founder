package com.zypg.cms.admin.model.viewobject.query;

import java.math.BigDecimal;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Apr 06 00:49:32 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsRoleUserVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        UserId {
            public Object get(CmsRoleUserVORowImpl obj) {
                return obj.getUserId();
            }

            public void put(CmsRoleUserVORowImpl obj, Object value) {
                obj.setUserId((Number)value);
            }
        }
        ,
        UserName {
            public Object get(CmsRoleUserVORowImpl obj) {
                return obj.getUserName();
            }

            public void put(CmsRoleUserVORowImpl obj, Object value) {
                obj.setUserName((String)value);
            }
        }
        ,
        DiplayName {
            public Object get(CmsRoleUserVORowImpl obj) {
                return obj.getDiplayName();
            }

            public void put(CmsRoleUserVORowImpl obj, Object value) {
                obj.setDiplayName((String)value);
            }
        }
        ,
        EnableFlag {
            public Object get(CmsRoleUserVORowImpl obj) {
                return obj.getEnableFlag();
            }

            public void put(CmsRoleUserVORowImpl obj, Object value) {
                obj.setEnableFlag((String)value);
            }
        }
        ,
        RoleId {
            public Object get(CmsRoleUserVORowImpl obj) {
                return obj.getRoleId();
            }

            public void put(CmsRoleUserVORowImpl obj, Object value) {
                obj.setRoleId((Number)value);
            }
        }
        ,
        YNVO {
            public Object get(CmsRoleUserVORowImpl obj) {
                return obj.getYNVO();
            }

            public void put(CmsRoleUserVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsRoleUserVORowImpl object);

        public abstract void put(CmsRoleUserVORowImpl object, Object value);

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


    public static final int USERID = AttributesEnum.UserId.index();
    public static final int USERNAME = AttributesEnum.UserName.index();
    public static final int DIPLAYNAME = AttributesEnum.DiplayName.index();
    public static final int ENABLEFLAG = AttributesEnum.EnableFlag.index();
    public static final int ROLEID = AttributesEnum.RoleId.index();
    public static final int YNVO = AttributesEnum.YNVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsRoleUserVORowImpl() {
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
     * Gets the attribute value for the calculated attribute UserName.
     * @return the UserName
     */
    public String getUserName() {
        return (String) getAttributeInternal(USERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute UserName.
     * @param value value to set the  UserName
     */
    public void setUserName(String value) {
        setAttributeInternal(USERNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DiplayName.
     * @return the DiplayName
     */
    public String getDiplayName() {
        return (String) getAttributeInternal(DIPLAYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DiplayName.
     * @param value value to set the  DiplayName
     */
    public void setDiplayName(String value) {
        setAttributeInternal(DIPLAYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute EnableFlag.
     * @return the EnableFlag
     */
    public String getEnableFlag() {
        return (String) getAttributeInternal(ENABLEFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute EnableFlag.
     * @param value value to set the  EnableFlag
     */
    public void setEnableFlag(String value) {
        setAttributeInternal(ENABLEFLAG, value);
    }


    /**
     * Gets the attribute value for the calculated attribute RoleId.
     * @return the RoleId
     */
    public Number getRoleId() {
        return (Number)getAttributeInternal(ROLEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RoleId.
     * @param value value to set the  RoleId
     */
    public void setRoleId(Number value) {
        setAttributeInternal(ROLEID, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> YNVO.
     */
    public RowSet getYNVO() {
        return (RowSet)getAttributeInternal(YNVO);
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
