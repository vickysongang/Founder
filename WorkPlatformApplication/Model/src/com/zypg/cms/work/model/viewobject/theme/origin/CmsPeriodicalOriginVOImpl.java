package com.zypg.cms.work.model.viewobject.theme.origin;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Sep 17 10:32:14 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsPeriodicalOriginVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsPeriodicalOriginVOImpl() {
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
     * Returns the bind variable value for bvGroupUserFlag.
     * @return bind variable value for bvGroupUserFlag
     */
    public String getbvGroupUserFlag() {
        return (String)getNamedWhereClauseParam("bvGroupUserFlag");
    }

    /**
     * Sets <code>value</code> for bind variable bvGroupUserFlag.
     * @param value value to bind as bvGroupUserFlag
     */
    public void setbvGroupUserFlag(String value) {
        setNamedWhereClauseParam("bvGroupUserFlag", value);
    }
}
