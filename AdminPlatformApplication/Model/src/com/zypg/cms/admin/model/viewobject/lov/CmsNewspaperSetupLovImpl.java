package com.zypg.cms.admin.model.viewobject.lov;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Aug 27 20:41:42 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewspaperSetupLovImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewspaperSetupLovImpl() {
    }

    /**
     * Returns the bind variable value for bvCompCode.
     * @return bind variable value for bvCompCode
     */
    public String getbvCompCode() {
        return (String)getNamedWhereClauseParam("bvCompCode");
    }

    /**
     * Sets <code>value</code> for bind variable bvCompCode.
     * @param value value to bind as bvCompCode
     */
    public void setbvCompCode(String value) {
        setNamedWhereClauseParam("bvCompCode", value);
    }
}
