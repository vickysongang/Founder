package com.zypg.cms.work.model.viewobject.common.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jun 06 17:54:40 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsDocStatus4AuthorCopyrightVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsDocStatus4AuthorCopyrightVOImpl() {
    }

    /**
     * Returns the bind variable value for bvCategoryId.
     * @return bind variable value for bvCategoryId
     */
    public Number getbvCategoryId() {
        return (Number)getNamedWhereClauseParam("bvCategoryId");
    }

    /**
     * Sets <code>value</code> for bind variable bvCategoryId.
     * @param value value to bind as bvCategoryId
     */
    public void setbvCategoryId(Number value) {
        setNamedWhereClauseParam("bvCategoryId", value);
    }

    /**
     * Returns the variable value for bvLibCode.
     * @return variable value for bvLibCode
     */
    public String getbvLibCode() {
        return (String)ensureVariableManager().getVariableValue("bvLibCode");
    }

    /**
     * Sets <code>value</code> for variable bvLibCode.
     * @param value value to bind as bvLibCode
     */
    public void setbvLibCode(String value) {
        ensureVariableManager().setVariableValue("bvLibCode", value);
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
