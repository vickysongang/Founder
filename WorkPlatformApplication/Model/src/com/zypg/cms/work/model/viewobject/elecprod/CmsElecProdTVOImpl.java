package com.zypg.cms.work.model.viewobject.elecprod;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jun 09 11:10:33 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsElecProdTVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsElecProdTVOImpl() {
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
