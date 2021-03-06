package com.zypg.cms.admin.model.viewobject.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Aug 29 00:41:51 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCompVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsCompVOImpl() {
    }


    /**
     * Returns the variable value for bvCompCode.
     * @return variable value for bvCompCode
     */
    public String getbvCompCode() {
        return (String)ensureVariableManager().getVariableValue("bvCompCode");
    }

    /**
     * Sets <code>value</code> for variable bvCompCode.
     * @param value value to bind as bvCompCode
     */
    public void setbvCompCode(String value) {
        ensureVariableManager().setVariableValue("bvCompCode", value);
    }

    /**
     * Returns the variable value for bvRoleId.
     * @return variable value for bvRoleId
     */
    public Number getbvRoleId() {
        return (Number)ensureVariableManager().getVariableValue("bvRoleId");
    }

    /**
     * Sets <code>value</code> for variable bvRoleId.
     * @param value value to bind as bvRoleId
     */
    public void setbvRoleId(Number value) {
        ensureVariableManager().setVariableValue("bvRoleId", value);
    }

    /**
     * Returns the bind variable value for bvCompStr.
     * @return bind variable value for bvCompStr
     */
    public String getbvCompStr() {
        return (String)getNamedWhereClauseParam("bvCompStr");
    }

    /**
     * Sets <code>value</code> for bind variable bvCompStr.
     * @param value value to bind as bvCompStr
     */
    public void setbvCompStr(String value) {
        setNamedWhereClauseParam("bvCompStr", value);
    }
}
