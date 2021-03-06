package com.zypg.cms.admin.model.viewobject.query;

import java.math.BigDecimal;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Apr 05 23:35:56 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsRoleVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        RoleId {
            public Object get(CmsRoleVORowImpl obj) {
                return obj.getRoleId();
            }

            public void put(CmsRoleVORowImpl obj, Object value) {
                obj.setRoleId((Number)value);
            }
        }
        ,
        RoleName {
            public Object get(CmsRoleVORowImpl obj) {
                return obj.getRoleName();
            }

            public void put(CmsRoleVORowImpl obj, Object value) {
                obj.setRoleName((String)value);
            }
        }
        ,
        RoleType {
            public Object get(CmsRoleVORowImpl obj) {
                return obj.getRoleType();
            }

            public void put(CmsRoleVORowImpl obj, Object value) {
                obj.setRoleType((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsRoleVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsRoleVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        Attribute1 {
            public Object get(CmsRoleVORowImpl obj) {
                return obj.getAttribute1();
            }

            public void put(CmsRoleVORowImpl obj, Object value) {
                obj.setAttribute1((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsRoleVORowImpl object);

        public abstract void put(CmsRoleVORowImpl object, Object value);

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


    public static final int ROLEID = AttributesEnum.RoleId.index();
    public static final int ROLENAME = AttributesEnum.RoleName.index();
    public static final int ROLETYPE = AttributesEnum.RoleType.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int ATTRIBUTE1 = AttributesEnum.Attribute1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsRoleVORowImpl() {
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
     * Gets the attribute value for the calculated attribute RoleName.
     * @return the RoleName
     */
    public String getRoleName() {
        return (String) getAttributeInternal(ROLENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RoleName.
     * @param value value to set the  RoleName
     */
    public void setRoleName(String value) {
        setAttributeInternal(ROLENAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute RoleType.
     * @return the RoleType
     */
    public String getRoleType() {
        return (String) getAttributeInternal(ROLETYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RoleType.
     * @param value value to set the  RoleType
     */
    public void setRoleType(String value) {
        setAttributeInternal(ROLETYPE, value);
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
     * Gets the attribute value for the calculated attribute Attribute1.
     * @return the Attribute1
     */
    public String getAttribute1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Attribute1.
     * @param value value to set the  Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
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
