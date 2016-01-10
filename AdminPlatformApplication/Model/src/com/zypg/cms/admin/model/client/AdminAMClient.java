package com.zypg.cms.admin.model.client;


import com.honythink.applicationframework.hadf.CustomDBTransaction;

import com.zypg.cms.admin.model.common.AdminAM;

import java.util.Collections;
import java.util.Map;

import oracle.jbo.client.remote.ApplicationModuleImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 04 13:38:15 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdminAMClient extends ApplicationModuleImpl implements AdminAM {
    /**
     * This is the default constructor (do not remove).
     */
    public AdminAMClient() {
    }


    public String queryOpenLog(String operator, String operateTime, String compName, String operateDesc) {
        Object _ret =
            this.riInvokeExportedMethod(this,"queryOpenLog",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {operator, operateTime, compName, operateDesc});
        return (String)_ret;
    }


    public void Authorize4GenerateUsers(oracle.jbo.domain.Number roleId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"Authorize4GenerateUsers",new String [] {"oracle.jbo.domain.Number"},new Object[] {roleId});
        return;
    }

    public void batchSetResDownloadAuth(String type, boolean value) {
        Object _ret =
            this.riInvokeExportedMethod(this,"batchSetResDownloadAuth",new String [] {"java.lang.String","boolean"},new Object[] {type, new Boolean(value)});
        return;
    }

    public void createRole(String roleType, String compCode) {
        Object _ret =
            this.riInvokeExportedMethod(this,"createRole",new String [] {"java.lang.String","java.lang.String"},new Object[] {roleType, compCode});
        return;
    }

    public oracle.jbo.domain.Number createUser(String compCode) {
        Object _ret = this.riInvokeExportedMethod(this,"createUser",new String [] {"java.lang.String"},new Object[] {compCode});
        return (oracle.jbo.domain.Number)_ret;
    }

    public void deleteRole(oracle.jbo.domain.Number roleId) {
        Object _ret = this.riInvokeExportedMethod(this,"deleteRole",new String [] {"oracle.jbo.domain.Number"},new Object[] {roleId});
        return;
    }

    public void deleteUser(oracle.jbo.domain.Number userId) {
        Object _ret = this.riInvokeExportedMethod(this,"deleteUser",new String [] {"oracle.jbo.domain.Number"},new Object[] {userId});
        return;
    }

    public void deleteUserSesson4AuthorizeUser() {
        Object _ret = this.riInvokeExportedMethod(this,"deleteUserSesson4AuthorizeUser",null,null);
        return;
    }

    public void findComp4Common(String compStr) {
        Object _ret = this.riInvokeExportedMethod(this,"findComp4Common",new String [] {"java.lang.String"},new Object[] {compStr});
        return;
    }

    public void findUserByComp(String compCode) {
        Object _ret = this.riInvokeExportedMethod(this,"findUserByComp",new String [] {"java.lang.String"},new Object[] {compCode});
        return;
    }

    public void findUserByRoleId(oracle.jbo.domain.Number roleId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"findUserByRoleId",new String [] {"oracle.jbo.domain.Number"},new Object[] {roleId});
        return;
    }

    public void findUserNoRole(oracle.jbo.domain.Number roleId, String compStr) {
        Object _ret =
            this.riInvokeExportedMethod(this,"findUserNoRole",new String [] {"oracle.jbo.domain.Number","java.lang.String"},new Object[] {roleId, compStr});
        return;
    }

    public CustomDBTransaction getCustomDBTransaction() {
        Object _ret = this.riInvokeExportedMethod(this,"getCustomDBTransaction",null,null);
        return (CustomDBTransaction)_ret;
    }

    public void initAdminOpenLog() {
        Object _ret = this.riInvokeExportedMethod(this,"initAdminOpenLog",null,null);
        return;
    }

    public void initAllUserRole(oracle.jbo.domain.Number userId, String compStr) {
        Object _ret =
            this.riInvokeExportedMethod(this,"initAllUserRole",new String [] {"oracle.jbo.domain.Number","java.lang.String"},new Object[] {userId, compStr});
        return;
    }

    public void initResDownloadVO(String libCode, oracle.jbo.domain.Number roleId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"initResDownloadVO",new String [] {"java.lang.String","oracle.jbo.domain.Number"},new Object[] {libCode, roleId});
        return;
    }

    public void initRoleAuthDisplay(String compCode, oracle.jbo.domain.Number roleId, String roleType,
                                    String platformType) {
        Object _ret =
            this.riInvokeExportedMethod(this,"initRoleAuthDisplay",new String [] {"java.lang.String","oracle.jbo.domain.Number","java.lang.String","java.lang.String"},new Object[] {compCode, roleId, roleType, platformType});
        return;
    }

    public void preUpdateRole(oracle.jbo.domain.Number roleId) {
        Object _ret = this.riInvokeExportedMethod(this,"preUpdateRole",new String [] {"oracle.jbo.domain.Number"},new Object[] {roleId});
        return;
    }

    public void queryRes4WP(String libCode) {
        Object _ret = this.riInvokeExportedMethod(this,"queryRes4WP",new String [] {"java.lang.String"},new Object[] {libCode});
        return;
    }

    public void queryRoleLib4WorkPlatform(oracle.jbo.domain.Number roleId, String compCode, String platformType) {
        Object _ret =
            this.riInvokeExportedMethod(this,"queryRoleLib4WorkPlatform",new String [] {"oracle.jbo.domain.Number","java.lang.String","java.lang.String"},new Object[] {roleId, compCode, platformType});
        return;
    }

    public String querySyncCanel(String uniqueResName, String libName, String status, String creater,
                                 String creationDate, String compCode) {
        Object _ret =
            this.riInvokeExportedMethod(this,"querySyncCanel",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {uniqueResName, libName, status, creater, creationDate, compCode});
        return (String)_ret;
    }

    public void resetPassword(oracle.jbo.domain.Number userId) {
        Object _ret = this.riInvokeExportedMethod(this,"resetPassword",new String [] {"oracle.jbo.domain.Number"},new Object[] {userId});
        return;
    }

    public void revokeRole(oracle.jbo.domain.Number roleId, oracle.jbo.domain.Number userId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"revokeRole",new String [] {"oracle.jbo.domain.Number","oracle.jbo.domain.Number"},new Object[] {roleId, userId});
        return;
    }

    public void saveDisplayAuth(oracle.jbo.domain.Number roleId, String roleType, String platformType,
                                String compCode) {
        Object _ret =
            this.riInvokeExportedMethod(this,"saveDisplayAuth",new String [] {"oracle.jbo.domain.Number","java.lang.String","java.lang.String","java.lang.String"},new Object[] {roleId, roleType, platformType, compCode});
        return;
    }

    public String saveResDownloadAuth(String libCode, oracle.jbo.domain.Number roleId) {
        Object _ret =
            this.riInvokeExportedMethod(this,"saveResDownloadAuth",new String [] {"java.lang.String","oracle.jbo.domain.Number"},new Object[] {libCode, roleId});
        return (String)_ret;
    }

    public void saveRoleAndRes4WP(oracle.jbo.domain.Number roleId, String libCode) {
        Object _ret =
            this.riInvokeExportedMethod(this,"saveRoleAndRes4WP",new String [] {"oracle.jbo.domain.Number","java.lang.String"},new Object[] {roleId, libCode});
        return;
    }

    public void setUserRole(oracle.jbo.domain.Number userId) {
        Object _ret = this.riInvokeExportedMethod(this,"setUserRole",new String [] {"oracle.jbo.domain.Number"},new Object[] {userId});
        return;
    }

    public void synVoWithDB(String voNAme) {
        Object _ret = this.riInvokeExportedMethod(this,"synVoWithDB",new String [] {"java.lang.String"},new Object[] {voNAme});
        return;
    }
}
