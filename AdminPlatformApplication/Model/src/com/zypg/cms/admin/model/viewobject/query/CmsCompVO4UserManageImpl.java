package com.zypg.cms.admin.model.viewobject.query;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 20 22:53:12 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCompVO4UserManageImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsCompVO4UserManageImpl() {
    }

    /**
     * Returns the bind variable value for bvCompStr.
     * @return bind variable value for bvCompStr
     */
    public String getbvCompStr() {
        return (String)getNamedWhereClauseParam("bvCompStr");
    }

    /**
     * Sets <code>value</code> for bind variable bvCompStr.
     * @param value value to bind as bvCompStr
     */
    public void setbvCompStr(String value) {
        setNamedWhereClauseParam("bvCompStr", value);
    }
}
