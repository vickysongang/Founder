package com.zypg.cms.work.model.viewobject.theme.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Sep 10 13:32:11 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsThemeExportLibVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsThemeExportLibVOImpl() {
    }

    /**
     * Returns the bind variable value for bvDocId.
     * @return bind variable value for bvDocId
     */
    public Number getbvDocId() {
        return (Number)getNamedWhereClauseParam("bvDocId");
    }

    /**
     * Sets <code>value</code> for bind variable bvDocId.
     * @param value value to bind as bvDocId
     */
    public void setbvDocId(Number value) {
        setNamedWhereClauseParam("bvDocId", value);
    }
}
