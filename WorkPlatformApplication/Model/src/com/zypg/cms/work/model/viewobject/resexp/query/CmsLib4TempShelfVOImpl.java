package com.zypg.cms.work.model.viewobject.resexp.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon May 19 18:08:42 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsLib4TempShelfVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsLib4TempShelfVOImpl() {
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

    /**
     * Returns the bind variable value for bvUserId.
     * @return bind variable value for bvUserId
     */
    public Number getbvUserId() {
        return (Number)getNamedWhereClauseParam("bvUserId");
    }

    /**
     * Sets <code>value</code> for bind variable bvUserId.
     * @param value value to bind as bvUserId
     */
    public void setbvUserId(Number value) {
        setNamedWhereClauseParam("bvUserId", value);
    }

}