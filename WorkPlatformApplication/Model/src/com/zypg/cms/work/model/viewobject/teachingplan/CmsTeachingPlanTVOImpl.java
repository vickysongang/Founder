package com.zypg.cms.work.model.viewobject.teachingplan;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Apr 24 15:03:39 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsTeachingPlanTVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsTeachingPlanTVOImpl() {
    }

    /**
     * Returns the variable value for bvDocId.
     * @return variable value for bvDocId
     */
    public Number getbvDocId() {
        return (Number)ensureVariableManager().getVariableValue("bvDocId");
    }

    /**
     * Sets <code>value</code> for variable bvDocId.
     * @param value value to bind as bvDocId
     */
    public void setbvDocId(Number value) {
        ensureVariableManager().setVariableValue("bvDocId", value);
    }
}
