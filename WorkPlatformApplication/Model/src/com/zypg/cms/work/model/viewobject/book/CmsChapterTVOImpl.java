package com.zypg.cms.work.model.viewobject.book;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon May 19 14:45:34 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsChapterTVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsChapterTVOImpl() {
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

    /**
     * Returns the variable value for bvStartSort.
     * @return variable value for bvStartSort
     */
    public Number getbvStartSort() {
        return (Number)ensureVariableManager().getVariableValue("bvStartSort");
    }

    /**
     * Sets <code>value</code> for variable bvStartSort.
     * @param value value to bind as bvStartSort
     */
    public void setbvStartSort(Number value) {
        ensureVariableManager().setVariableValue("bvStartSort", value);
    }

    /**
     * Returns the variable value for bvEndSort.
     * @return variable value for bvEndSort
     */
    public Number getbvEndSort() {
        return (Number)ensureVariableManager().getVariableValue("bvEndSort");
    }

    /**
     * Sets <code>value</code> for variable bvEndSort.
     * @param value value to bind as bvEndSort
     */
    public void setbvEndSort(Number value) {
        ensureVariableManager().setVariableValue("bvEndSort", value);
    }
}
