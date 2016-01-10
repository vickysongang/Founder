package com.zypg.cms.work.model.viewobject.docbook.query;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon May 12 17:47:05 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsDocBookJobVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CmsDocBookJobVOImpl() {
    }

    /**
     * Returns the variable value for bvStatus.
     * @return variable value for bvStatus
     */
    public String getbvStatus() {
        return (String)ensureVariableManager().getVariableValue("bvStatus");
    }

    /**
     * Sets <code>value</code> for variable bvStatus.
     * @param value value to bind as bvStatus
     */
    public void setbvStatus(String value) {
        ensureVariableManager().setVariableValue("bvStatus", value);
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

    /**
     * Returns the variable value for bvCategoryId.
     * @return variable value for bvCategoryId
     */
    public Number getbvCategoryId() {
        return (Number)ensureVariableManager().getVariableValue("bvCategoryId");
    }

    /**
     * Sets <code>value</code> for variable bvCategoryId.
     * @param value value to bind as bvCategoryId
     */
    public void setbvCategoryId(Number value) {
        ensureVariableManager().setVariableValue("bvCategoryId", value);
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
}
