package com.zypg.cms.work.model.viewobject.resexp.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 23 16:21:10 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsResAttachExpVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsResAttachExpVOImpl() {
    }

    /**
     * Returns the variable value for bvApplyId.
     * @return variable value for bvApplyId
     */
    public Number getbvApplyId() {
        return (Number)ensureVariableManager().getVariableValue("bvApplyId");
    }

    /**
     * Sets <code>value</code> for variable bvApplyId.
     * @param value value to bind as bvApplyId
     */
    public void setbvApplyId(Number value) {
        ensureVariableManager().setVariableValue("bvApplyId", value);
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
