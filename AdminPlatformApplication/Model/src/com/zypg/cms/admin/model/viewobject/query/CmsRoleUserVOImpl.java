package com.zypg.cms.admin.model.viewobject.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Apr 06 00:49:32 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsRoleUserVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsRoleUserVOImpl() {
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
}
