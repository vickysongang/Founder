package com.zypg.cms.work.model.viewobject.common.query;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jun 05 20:47:25 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsCategory4AuthorCopyrightVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsCategory4AuthorCopyrightVOImpl() {
    }

    /**
     * Returns the bind variable value for bvLibCode.
     * @return bind variable value for bvLibCode
     */
    public String getbvLibCode() {
        return (String)getNamedWhereClauseParam("bvLibCode");
    }

    /**
     * Sets <code>value</code> for bind variable bvLibCode.
     * @param value value to bind as bvLibCode
     */
    public void setbvLibCode(String value) {
        setNamedWhereClauseParam("bvLibCode", value);
    }

    /**
     * Returns the variable value for bvLibTypeCode.
     * @return variable value for bvLibTypeCode
     */
    public String getbvLibTypeCode() {
        return (String)ensureVariableManager().getVariableValue("bvLibTypeCode");
    }

    /**
     * Sets <code>value</code> for variable bvLibTypeCode.
     * @param value value to bind as bvLibTypeCode
     */
    public void setbvLibTypeCode(String value) {
        ensureVariableManager().setVariableValue("bvLibTypeCode", value);
    }

    /**
     * Returns the variable value for bvCompCode.
     * @return variable value for bvCompCode
     */
    public String getbvCompCode() {
        return (String)ensureVariableManager().getVariableValue("bvCompCode");
    }

    /**
     * Sets <code>value</code> for variable bvCompCode.
     * @param value value to bind as bvCompCode
     */
    public void setbvCompCode(String value) {
        ensureVariableManager().setVariableValue("bvCompCode", value);
    }
}
