package com.zypg.cms.work.model.viewobject.entry.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 15 15:58:39 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsWorksEntryVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsWorksEntryVOImpl() {
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
     * Returns the bind variable value for bvStatus.
     * @return bind variable value for bvStatus
     */
    public String getbvStatus() {
        return (String)getNamedWhereClauseParam("bvStatus");
    }

    /**
     * Sets <code>value</code> for bind variable bvStatus.
     * @param value value to bind as bvStatus
     */
    public void setbvStatus(String value) {
        setNamedWhereClauseParam("bvStatus", value);
    }

    /**
     * Returns the variable value for bvCategoryGroupType.
     * @return variable value for bvCategoryGroupType
     */
    public String getbvCategoryGroupType() {
        return (String)ensureVariableManager().getVariableValue("bvCategoryGroupType");
    }

    /**
     * Sets <code>value</code> for variable bvCategoryGroupType.
     * @param value value to bind as bvCategoryGroupType
     */
    public void setbvCategoryGroupType(String value) {
        ensureVariableManager().setVariableValue("bvCategoryGroupType", value);
    }

    /**
     * Returns the variable value for bvDeleteFlag.
     * @return variable value for bvDeleteFlag
     */
    public String getbvDeleteFlag() {
        return (String)ensureVariableManager().getVariableValue("bvDeleteFlag");
    }

    /**
     * Sets <code>value</code> for variable bvDeleteFlag.
     * @param value value to bind as bvDeleteFlag
     */
    public void setbvDeleteFlag(String value) {
        ensureVariableManager().setVariableValue("bvDeleteFlag", value);
    }

    /**
     * Returns the variable value for bvSearchInput.
     * @return variable value for bvSearchInput
     */
    public String getbvSearchInput() {
        return (String)ensureVariableManager().getVariableValue("bvSearchInput");
    }

    /**
     * Sets <code>value</code> for variable bvSearchInput.
     * @param value value to bind as bvSearchInput
     */
    public void setbvSearchInput(String value) {
        ensureVariableManager().setVariableValue("bvSearchInput", value);
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
