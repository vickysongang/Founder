package com.zypg.cms.work.model.viewobject.figuregroup.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 09 20:10:47 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsDocGroupRelVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsDocGroupRelVOImpl() {
    }

    /**
     * Returns the variable value for bvGroupId.
     * @return variable value for bvGroupId
     */
    public Number getbvGroupId() {
        return (Number)ensureVariableManager().getVariableValue("bvGroupId");
    }

    /**
     * Sets <code>value</code> for variable bvGroupId.
     * @param value value to bind as bvGroupId
     */
    public void setbvGroupId(Number value) {
        ensureVariableManager().setVariableValue("bvGroupId", value);
    }
}
