package com.zypg.cms.work.model.viewobject.common.query;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 15 18:02:29 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsDocDisplayWayVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsDocDisplayWayVOImpl() {
    }

    /**
     * Returns the variable value for bvLibCode.
     * @return variable value for bvLibCode
     */
    public String getbvLibCode() {
        return (String)ensureVariableManager().getVariableValue("bvLibCode");
    }

    /**
     * Sets <code>value</code> for variable bvLibCode.
     * @param value value to bind as bvLibCode
     */
    public void setbvLibCode(String value) {
        ensureVariableManager().setVariableValue("bvLibCode", value);
    }
}
