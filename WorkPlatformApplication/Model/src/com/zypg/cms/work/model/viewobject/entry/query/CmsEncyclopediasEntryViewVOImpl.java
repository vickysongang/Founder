package com.zypg.cms.work.model.viewobject.entry.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 23 10:13:06 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsEncyclopediasEntryViewVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsEncyclopediasEntryViewVOImpl() {
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
