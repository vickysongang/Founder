package com.zypg.cms.admin.model.common;

import com.honythink.applicationframework.hadf.CustomDBTransaction;

import java.util.Map;

import oracle.jbo.ApplicationModule;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 04 13:38:15 CST 2014
// ---------------------------------------------------------------------
public interface AdminAM extends ApplicationModule {
    void findUserByComp(String compCode);


    void preUpdateRole(oracle.jbo.domain.Number roleId);

    void findUserByRoleId(oracle.jbo.domain.Number roleId);

    void createRole(String roleType, String compCode);


    void deleteUser(oracle.jbo.domain.Number userId);

    void resetPassword(oracle.jbo.domain.Number userId);

    void deleteRole(oracle.jbo.domain.Number roleId);

    void deleteUserSesson4AuthorizeUser();

    void Authorize4GenerateUsers(oracle.jbo.domain.Number roleId);

    void setUserRole(oracle.jbo.domain.Number userId);


    void revokeRole(oracle.jbo.domain.Number roleId, oracle.jbo.domain.Number userId);

    void initAllUserRole(oracle.jbo.domain.Number userId, String compStr);


    void synVoWithDB(String voNAme);

    CustomDBTransaction getCustomDBTransaction();


    void queryRes4WP(String libCode);

    void saveRoleAndRes4WP(oracle.jbo.domain.Number roleId, String libCode);

    void initResDownloadVO(String libCode, oracle.jbo.domain.Number roleId);

    void batchSetResDownloadAuth(String type, boolean value);

    String saveResDownloadAuth(String libCode, oracle.jbo.domain.Number roleId);

    void findComp4Common(String compStr);

    oracle.jbo.domain.Number createUser(String compCode);

    void findUserNoRole(oracle.jbo.domain.Number roleId, String compStr);

    void initAdminOpenLog();


    void initRoleAuthDisplay(String compCode, oracle.jbo.domain.Number roleId, String roleType, String platformType);

    void queryRoleLib4WorkPlatform(oracle.jbo.domain.Number roleId, String compCode, String platformType);

    void saveDisplayAuth(oracle.jbo.domain.Number roleId, String roleType, String platformType, String compCode);


    String querySyncCanel(String uniqueResName, String libName, String status, String creater, String creationDate,
                          String compCode);
}
