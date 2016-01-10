package com.zypg.cms.admin.model;


import com.honythink.applicationframework.hadf.CustomApplicationModuleImpl;
import com.honythink.applicationframework.hadf.util.EncryptUtil;

import com.zypg.cms.admin.model.common.AdminAM;
import com.zypg.cms.admin.model.viewobject.CmsAdminOperLogTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsCompEditVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsCompEditVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsExtendAttrTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsExtendAttrTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsExtendValueListTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsExtendValueListTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsNewsEditVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsNewsEditVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsNewspaperEditVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsNewspaperEditVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsPeriodEditVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsPeriodEditVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsPeriodicalEditVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsPeriodicalEditVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleLibRelTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleLibRelTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleResRel4DownloadAuthTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleResRel4DownloadAuthTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleResRelTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleResRelTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleUserRelTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsRoleUserRelTVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsUserTVOImpl;
import com.zypg.cms.admin.model.viewobject.CmsUserTVORowImpl;
import com.zypg.cms.admin.model.viewobject.lov.CmsNewspaperSetupLovImpl;
import com.zypg.cms.admin.model.viewobject.lov.CmsNewspaperSetupLovRowImpl;
import com.zypg.cms.admin.model.viewobject.lov.CmsPeriodicalSetupLovImpl;
import com.zypg.cms.admin.model.viewobject.lov.CmsPeriodicalSetupLovRowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsCompQueryVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsCompVO4UserManageImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsCompVO4UserManageRowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsCompVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsCompVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsExtendAttrVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsExtendValueListVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsExtendValueListVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsFirstLevelLibVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsFirstLevelLibVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsLib4ExtendAttrVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsNewsSetupQueryVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsNewsSetupQueryVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsNewsSetupVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsNewsSetupVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsNewspaperSetupVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsNewspaperSetupVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsPeriodSetupQueryVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsPeriodSetupQueryVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsPeriodSetupVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsPeriodSetupVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsPeriodicalSetupVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsPeriodicalSetupVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsRes4WPVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsResDownloadVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsResDownloadVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsResType4WPVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsResType4WPVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsRoleLibVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsRoleUserVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsRoleVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsSecondLevelLibVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsSyncCancelVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserAllRoleVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserAllRoleVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserChecked4RoleVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserCompStrVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserCompStrVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserNoRoleVOImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsUserNoRoleVORowImpl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.adf.share.ADFContext;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 04 11:02:09 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdminAMImpl extends CustomApplicationModuleImpl implements AdminAM {
    /**
     * This is the default constructor (do not remove).
     */
    public AdminAMImpl() {
    }

    public Number getUserID() {
        return this.getCustomDBTransaction().getUserId();
    }

    public void createAdminOperLog(String masage, String compCode) {
        CmsAdminOperLogTVOImpl vo = this.getCmsAdminOperLogTVO1();
        Row newRow = vo.createRow();
        newRow.setAttribute("LogContent", masage);
        newRow.setAttribute("Attribute1", compCode);
        vo.insertRow(newRow);
        this.getDBTransaction().commit();
    }

    public void initAdminOpenLog() {
        CmsAdminOperLogTVOImpl vo = this.getCmsAdminOperLogTVO1();
        String userName = this.getCustomDBTransaction().getUserName();
        System.out.println("userName:" + userName);
        Map<String, Object> comps = findUserCompStr();
        Map<String, String> returnMsg = (Map<String, String>)comps.get("returnMsg");
        String compStr = returnMsg.get("compStr");
        if ("ALL".equals(compStr)) {
            vo.setWhereClause(null);
            vo.executeQuery();
        } else {
            if (compStr != null && compStr.length() > 0) {
                StringBuffer sb = new StringBuffer();
                for (String compCode : compStr.split(",")) {
                    sb.append("'" + compCode + "',");
                }
                String compSqlStr = sb.toString();
                compSqlStr = compSqlStr.substring(0, compSqlStr.length() - 1);
                System.out.println(compSqlStr);
                vo.setWhereClause(null);
                vo.setWhereClause("Attribute1 in (" + compSqlStr + ")");
                vo.executeQuery();
            } else {
                vo.executeEmptyRowSet();
            }
        }
    }


    public Row getPeriodInfo() {
        CmsPeriodEditVORowImpl row = (CmsPeriodEditVORowImpl)this.getCmsPeriodEditVO().getCurrentRow();
        return row;
    }

    public Row getNewsPaperInfo() {
        CmsNewsEditVORowImpl row = (CmsNewsEditVORowImpl)this.getCmsNewsEditVO().getCurrentRow();
        return row;
    }


    public String saveNewsBatchSetup(String mode, Number setupId, Number categoryId, Number year, Number periodNum,
                                     String date, String remarks, String compCode) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.save_news_setup(?,?,?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, mode);
            st.setObject(3, setupId);
            st.setObject(4, categoryId);
            st.setObject(5, year);
            st.setObject(6, periodNum);
            st.setObject(7, new oracle.jbo.domain.Date(date));
            st.setObject(8, remarks);
            st.setObject(9, this.getCustomDBTransaction().getUserId());
            st.setObject(10, compCode);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void preBatchCreateNews(String mode, Number categoryId, Number setupId, Number year) {
        CmsNewsEditVOImpl editVO = this.getCmsNewsEditVO();
        editVO.executeQuery();
        CmsNewsEditVORowImpl row = (CmsNewsEditVORowImpl)editVO.first();
        row.setCategoryId(categoryId);
        row.setStartyear(year);
        row.setEndyear(year);
        row.setNewsNum(new Number(0));
        editVO.setCurrentRow(row);

    }


    public String savePeriodBatchSetup(String mode, Number setupId, Number categoryId, Number year, Number periodNum,
                                       String date, String remarks, String compCode) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.save_period_setup(?,?,?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, mode);
            st.setObject(3, setupId);
            st.setObject(4, categoryId);
            st.setObject(5, year);
            st.setObject(6, periodNum);
            st.setObject(7, new oracle.jbo.domain.Date(date));
            st.setObject(8, remarks);
            st.setObject(9, this.getCustomDBTransaction().getUserId());
            st.setObject(10, compCode);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public List getAllNewsNumForCategoryId(Number categoryId) {
        List list = new ArrayList();
        ViewObject vo = this.getCmsNewsSetupVO1();

        Row[] rowSet = vo.getFilteredRows("NewspaperCategoryId", categoryId);
        for (Row row : rowSet) {
            list.add(((Number)row.getAttribute("NewsNum")).intValue());
        }
        return list;
    }

    public List getAllPeriodNumForCategoryId(Number categoryId) {
        List list = new ArrayList();
        ViewObject vo = this.getCmsPeriodSetupVO1();
        Row[] rowSet = vo.getFilteredRows("PeriodicalCategoryId", categoryId);
        for (Row row : rowSet) {
            list.add(((Number)row.getAttribute("PeriodNum")).intValue());
        }
        return list;
    }

    public void preBatchCreatePeriod(Number categoryId, Number setupId, Number year) {
        CmsPeriodEditVOImpl editVO = this.getCmsPeriodEditVO();
        editVO.executeQuery();
        CmsPeriodEditVORowImpl row = (CmsPeriodEditVORowImpl)editVO.first();
        row.setCategoryId(categoryId);
        row.setStartyear(year);
        row.setEndyear(year);
        row.setCategoryNum(new Number(0));

        editVO.setCurrentRow(row);
    }


    public String saveResDownloadAuth(String libCode, Number roleId) {
        String flag = "S";
        CmsResDownloadVOImpl resDownload = this.getCmsResDownloadVO();
        //删除所有
        CmsRoleResRel4DownloadAuthTVOImpl saveDownloadVo = this.getCmsRoleResRel4DownloadAuthTVO();
        saveDownloadVo.setbvResTypeCode("ATTACH_TYPE#" + libCode);
        saveDownloadVo.setbvRoleId(roleId);
        saveDownloadVo.executeQuery();
        saveDownloadVo.setRangeSize(-1);
        for (Row rw : saveDownloadVo.getAllRowsInRange()) {
            rw.remove();
        }

        for (Row rw : resDownload.getAllRowsInRange()) {
            CmsResDownloadVORowImpl downloadRow = (CmsResDownloadVORowImpl)rw;
            String authFlag = getAuthFlag(downloadRow);
            if (authFlag != null) {
                CmsRoleResRel4DownloadAuthTVORowImpl saveRow =
                    (CmsRoleResRel4DownloadAuthTVORowImpl)saveDownloadVo.createRow();
                saveRow.setRoleId(roleId);
                saveRow.setResCode(downloadRow.getResCode());
                saveRow.setResTypeCode(downloadRow.getResTypeCode());
                saveRow.setAuthority(authFlag);
            }
        }

        try {
            this.getDBTransaction().commit();
            //查询VO
            CmsResDownloadVOImpl qresDownload = this.getCmsResDownloadVO();
            qresDownload.setbvLibCode(libCode);
            qresDownload.setbvRoleId(roleId);
            qresDownload.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            this.getDBTransaction().rollback();
            flag = "E";
        }
        return flag;
    }

    private String getAuthFlag(CmsResDownloadVORowImpl downloadRow) {
        String authFlag = null;
        if (downloadRow.getIsNo()) {
            authFlag = "0";
        } else if (downloadRow.getIsAuth()) {
            authFlag = "1";
        } else if (downloadRow.getIsFree()) {
            authFlag = "2";
        }
        return authFlag;
    }

    public void batchSetResDownloadAuth(String type, boolean value) {
        System.out.println(type + "---->" + value);
        CmsResDownloadVOImpl resDownload = this.getCmsResDownloadVO();
        resDownload.setRangeStart(0);
        for (Row rw : resDownload.getAllRowsInRange()) {
            CmsResDownloadVORowImpl downloadRow = (CmsResDownloadVORowImpl)rw;
            if ("no".equalsIgnoreCase(type)) {
                downloadRow.setIsNo(value);
                downloadRow.setIsAuth(!value);
                downloadRow.setIsFree(!value);
            } else if ("auth".equalsIgnoreCase(type)) {
                downloadRow.setIsNo(!value);
                downloadRow.setIsAuth(value);
                downloadRow.setIsFree(!value);
            } else if ("free".equalsIgnoreCase(type)) {
                downloadRow.setIsNo(!value);
                downloadRow.setIsAuth(!value);
                downloadRow.setIsFree(value);
            }
        }
    }

    public void initResDownloadVO(String libCode, Number roleId) {
        System.out.println("init  libCode--->" + libCode + " roleId---->" + roleId);
        CmsResDownloadVOImpl resDownload = this.getCmsResDownloadVO();
        resDownload.setbvLibCode(libCode);
        resDownload.setbvRoleId(roleId);
        resDownload.executeQuery();
        resDownload.setRangeSize(-1);
    }

    /**
     * 保存角色和资源操作的对应关系
     * @param roleId
     * @param libCode
     */
    public void saveRoleAndRes4WP(Number roleId, String libCode) {
        CmsRoleResRelTVOImpl rel = this.getCmsRoleResRelTVO1();
        rel.setbvRoleId(roleId);
        rel.setbvLibCode(libCode);
        rel.executeQuery();
        rel.setRangeSize(-1);
        //删除
        for (Row rw : rel.getAllRowsInRange()) {
            rw.remove();
        }

        CmsResType4WPVOImpl resTypeVo = this.getCmsResType4WPVO1();
        resTypeVo.setRangeSize(-1);
        for (Row rw1 : resTypeVo.getAllRowsInRange()) {
            CmsResType4WPVORowImpl typeRw = (CmsResType4WPVORowImpl)rw1;
            RowIterator ri = typeRw.getCmsRes4WPVO();
            if (ri != null) {
                for (Row rw2 : ri.getAllRowsInRange()) {
                    CmsRes4WPVORowImpl resRw = (CmsRes4WPVORowImpl)rw2;
                    if (resRw.getIsSelected()) {
                        CmsRoleResRelTVORowImpl relRw = (CmsRoleResRelTVORowImpl)rel.createRow();
                        relRw.setRoleId(roleId);
                        relRw.setResCode(resRw.getResCode());
                        relRw.setResTypeCode(resRw.getResTypeCode());
                        relRw.setAuthority("1");
                    }
                }
            }
        }
        this.getDBTransaction().commit();
    }

    /**
     * 点击库时，查询资源
     * @param libCode
     */
    public void queryRes4WP(String libCode) {
        CmsResType4WPVOImpl resTypeVo = this.getCmsResType4WPVO1();
        resTypeVo.setbvLibCode(libCode);
        resTypeVo.executeQuery();
    }

    /**
     * 工作平台初始化操作权限库列表
     * @param roleId
     * @param compCode
     */
    public void queryRoleLib4WorkPlatform(Number roleId, String compCode, String platformType) {
        CmsRoleLibVOImpl roleLibVo = this.getCmsRoleLibVO1();
        roleLibVo.setbvCompCode(compCode);
        roleLibVo.setbvRoleId(roleId);
        roleLibVo.setbvPlatformType(platformType);
        roleLibVo.executeQuery();
    }

    /**
     *  存储display权限
     * @param roleId
     */
    public void saveDisplayAuth(Number roleId, String roleType, String platformType, String compCode) {
        System.out.println("roleId:" + roleId + "  roleType:" + roleType + " platformType:" + platformType +
                           " compCode:" + compCode);
        CmsRoleLibRelTVOImpl relvo = this.getCmsRoleLibRelTVO1();
        relvo.setbvRoleId(roleId);
        relvo.setbvPlatformType(platformType);
        relvo.executeQuery();
        relvo.setRangeSize(-1);
        //删除所有
        for (Row rw : relvo.getAllRowsInRange()) {
            rw.remove();
        }
        List<String> unselectedLibs = new ArrayList<String>();
        CmsFirstLevelLibVOImpl f1vo = this.getCmsFirstLevelLibVO1();
        f1vo.setRangeSize(-1);
        for (Row rw : f1vo.getAllRowsInRange()) {
            CmsFirstLevelLibVORowImpl frw = (CmsFirstLevelLibVORowImpl)rw;
            if (frw.getIsSelected()) {
                RowIterator iter = frw.getCmsSecondLevelLibVO();
                if (iter != null)
                    for (Row rw2 : iter.getAllRowsInRange()) {
                        CmsSecondLevelLibVORowImpl srw = (CmsSecondLevelLibVORowImpl)rw2;
                        if (srw.getIsSelected()) {
                            CmsRoleLibRelTVORowImpl rel = (CmsRoleLibRelTVORowImpl)relvo.createRow();
                            rel.setRoleId(roleId);
                            rel.setLibCode(srw.getLibCode());
                            rel.setLibTypeCode(frw.getLibCode());
                            rel.setAttribute1(platformType);
                            relvo.insertRow(rel);
                        } else {
                            if ("ADMIN_PLATFORM".equals(roleType)) {
                                System.out.println(srw.getLibName());
                                unselectedLibs.add(srw.getLibCode());
                            }
                        }
                    }
            }
        }
        //提交
        this.getDBTransaction().commit();
        for (String libCode : unselectedLibs) {
            System.out.println("libCode:" + libCode);
            this.revokePrivilege(compCode, libCode, platformType);
        }
    }

    /**
     * 回收权限
     * @param compCode
     * @param libCode
     * @param platformType
     */
    public void revokePrivilege(String compCode, String libCode, String platformType) {
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin CMS_ADMIN_PLATFORM_PKG.revoke_privilege(?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.setObject(1, compCode);
            st.setObject(2, libCode);
            st.setObject(3, platformType);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化权限
     * @param compCode
     * @param roleId
     */
    public void initRoleAuthDisplay(String compCode, Number roleId, String roleType, String platformType) {
        System.out.println("compCode--->" + compCode + "   roleId--->" + roleId + "  roleType:" + roleType +
                           "   platformType:" + platformType + "  userId:" +
                           this.getCustomDBTransaction().getUserId());
        this.getTransaction().rollback();
        CmsFirstLevelLibVOImpl f1vo = this.getCmsFirstLevelLibVO1();
        f1vo.setbvCompCode(compCode);
        f1vo.setbvRoleId(roleId);
        f1vo.executeQuery();
        ADFContext.getCurrent().getSessionScope().put("secondLevelRoleId", roleId);
        ADFContext.getCurrent().getSessionScope().put("platformType", platformType);
        ADFContext.getCurrent().getSessionScope().put("roleType", roleType);
        ADFContext.getCurrent().getSessionScope().put("userId", this.getCustomDBTransaction().getUserId());
    }

    /**
     * 同步vo
     * @param voNAme
     */
    public void synVoWithDB(String voNAme) {
        ViewObjectImpl vo = (ViewObjectImpl)this.findViewObject(voNAme);
        int oldMode = vo.getQueryMode();
        vo.setQueryMode(ViewObject.QUERY_MODE_SCAN_DATABASE_TABLES);
        vo.executeQuery();
        vo.setQueryMode(oldMode);
    }

    private void maintainUserRole(CmsRoleUserRelTVOImpl relvo, CmsUserAllRoleVORowImpl nr, Number userId) {
        relvo.setRangeSize(-1);
        boolean flag = false;
        CmsRoleUserRelTVORowImpl existRw = null;
        for (Row rw : relvo.getAllRowsInRange()) {
            CmsRoleUserRelTVORowImpl or = (CmsRoleUserRelTVORowImpl)rw;
            if (or.getRoleId().intValue() == nr.getRoleId().intValue()) {
                flag = true;
                existRw = or;
                break;
            }
        }
        if (flag) {
            if (!nr.getHasAuthority()) {
                existRw.remove();
            }
        } else if (nr.getHasAuthority()) {
            CmsRoleUserRelTVORowImpl relRw = (CmsRoleUserRelTVORowImpl)relvo.createRow();
            relRw.setUserId(userId);
            relRw.setRoleId(nr.getRoleId());
            relvo.insertRow(relRw);
        }
    }


    /**
     * 设置用户角色
     */
    public void setUserRole(Number userId) {
        //根据用户name 获取当前用户已有角色
        CmsRoleUserRelTVOImpl relVo = this.getCmsRoleUserRelTVO4SetUserRole();
        relVo.setbvUserId(userId);
        relVo.executeQuery();
        relVo.setRangeSize(-1);

        //新增所有角色
        CmsUserAllRoleVOImpl wv = this.getCmsUserAllRoleVO4WorkPF();
        wv.setRangeSize(-1);
        for (Row rw1 : wv.getAllRowsInRange()) {
            CmsUserAllRoleVORowImpl wr = (CmsUserAllRoleVORowImpl)rw1;
            maintainUserRole(relVo, wr, userId);
        }
        CmsUserAllRoleVOImpl pv = this.getCmsUserAllRoleVO4PubPF();
        pv.setRangeSize(-1);
        for (Row rw2 : pv.getAllRowsInRange()) {
            CmsUserAllRoleVORowImpl pr = (CmsUserAllRoleVORowImpl)rw2;
            maintainUserRole(relVo, pr, userId);
        }
        CmsUserAllRoleVOImpl av = this.getCmsUserAllRoleVO4AdminPF();
        av.setRangeSize(-1);
        for (Row rw3 : av.getAllRowsInRange()) {
            CmsUserAllRoleVORowImpl ar = (CmsUserAllRoleVORowImpl)rw3;
            maintainUserRole(relVo, ar, userId);
        }
        this.getDBTransaction().commit();
    }

    /**
     * 用户角色设置 获取当前选择用户ID
     */
    public Map<String, Object> getCurrentSelectedUserInfo(Number userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        CmsUserTVOImpl vo = this.getCmsUserTVO4RoleSet();
        vo.setbvUserId(userId);
        vo.executeQuery();
        CmsUserTVORowImpl row = (CmsUserTVORowImpl)vo.first();
        if (row != null) {
            map.put("userId", row.getUserId());
            map.put("userName", row.getUserName());
            map.put("displayName", row.getDiplayName());
        }
        return map;
    }

    /**
     * 用户角色设置 初始化
     */
    public void initAllUserRole(Number userId, String compStr) {
        CmsUserAllRoleVOImpl wv = this.getCmsUserAllRoleVO4WorkPF();
        wv.setbvUserId(userId);
        wv.setbvCompStr(compStr);
        wv.executeQuery();
        CmsUserAllRoleVOImpl pv = this.getCmsUserAllRoleVO4PubPF();
        pv.setbvUserId(userId);
        pv.setbvCompStr(compStr);
        pv.executeQuery();
        CmsUserAllRoleVOImpl av = this.getCmsUserAllRoleVO4AdminPF();
        av.setbvUserId(userId);
        av.setbvCompStr(compStr);
        av.executeQuery();
        System.out.println("userId--->" + userId + "  compStr--->" + compStr);
    }

    /**
     * 管理平台-角色管理 ：为所选择到的用户授权
     */
    public void Authorize4GenerateUsers(Number roleId) {
        CmsCompVO4UserManageImpl compVo = this.getCmsCompVO4UserManage();
        compVo.setRangeSize(-1);
        boolean flag = false;
        for (Row rw : compVo.getAllRowsInRange()) {
            CmsCompVO4UserManageRowImpl compRw = (CmsCompVO4UserManageRowImpl)rw;
            RowIterator ri = compRw.getCmsUserNoRoleVO();
            ri.setRangeSize(-1);
            if (ri != null) {
                for (Row nrw : ri.getAllRowsInRange()) {
                    CmsUserNoRoleVORowImpl rrw = (CmsUserNoRoleVORowImpl)nrw;
                    if (rrw.getChildCheckbox() && !validateUserExistInRole(roleId, rrw.getUserId())) {
                        CmsRoleUserRelTVOImpl roleUserRelVO = this.getCmsRoleUserRelTVO();
                        CmsRoleUserRelTVORowImpl roleUserRelNewRow =
                            (CmsRoleUserRelTVORowImpl)roleUserRelVO.createRow();
                        roleUserRelNewRow.setUserId(rrw.getUserId());
                        roleUserRelNewRow.setRoleId(roleId);
                        roleUserRelVO.insertRow(roleUserRelNewRow);
                        flag = true;
                    }
                }
            }
        }
        if (flag) {
            this.getDBTransaction().commit();
            this.getCmsRoleUserVO().executeQuery();
        }
    }

    /**
     * 管理平台-角色管理： 删除批量操作 userData 数据this.getDBTransaction().getSession().getUserData().get("CmsUserNoRoleVORowImplMap")
     */
    public void deleteUserSesson4AuthorizeUser() {
        CmsCompVO4UserManageImpl compVo = this.getCmsCompVO4UserManage();
        compVo.setRangeSize(-1);
        for (Row rw : compVo.getAllRowsInRange()) {
            CmsCompVO4UserManageRowImpl compRow = (CmsCompVO4UserManageRowImpl)rw;
            compRow.setCheckbox(null);
        }
        this.getDBTransaction().getSession().getUserData().remove("CmsUserNoRoleVORowImplMap");
    }

    public boolean validateUserExistInRole(Number roleId, Number userId) {
        CmsRoleUserRelTVOImpl vo = this.getCmsRoleUserRelTVO4Query();
        vo.setbvRoleId(roleId);
        vo.setbvUserId(userId);
        vo.executeQuery();
        if (vo.getEstimatedRowCount() > 0) {
            return true;
        }
        return false;
    }

    public String getGroupFlagByCompCode(String compCode) {
        String result = "N";
        CmsCompQueryVOImpl vo = this.getCmsCompQueryVO();
        vo.setbvCompCode(compCode);
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            result = (String)row.getAttribute("GroupFlag");
        }
        return result;
    }

    /**
     * 用户管理
     */
    public Map<String, Object> findUserCompStr() {
        Map<String, Object> result = new HashMap<String, Object>();
        CmsUserCompStrVOImpl userCompStrVO = this.getCmsUserCompStrVO();
        userCompStrVO.setbvUserId(this.getCustomDBTransaction().getUserId());
        userCompStrVO.executeQuery();
        CmsUserCompStrVORowImpl userCompStrRow = (CmsUserCompStrVORowImpl)userCompStrVO.first();
        if (userCompStrRow != null && userCompStrRow.getCompStr() != null) {
            Map<String, String> returnMsg = new HashMap<String, String>();
            returnMsg.put("compStr", userCompStrRow.getCompStr());
            if (userCompStrRow.getCompStr().split(",").length == 1) {
                String groupFlag = this.getGroupFlagByCompCode(userCompStrRow.getCompStr());
                returnMsg.put("onlyHasGroupFlag", groupFlag);
            } else {
                returnMsg.put("onlyHasGroupFlag", "N");
            }
            result.put("returnCode", "S");
            result.put("returnMsg", returnMsg);
        } else {
            result.put("returnCode", "E");
            result.put("returnMsg", "No Permission");
        }
        result.put("LOGGED_USERNAME", this.getCustomDBTransaction().getUserName());
        return result;
    }

    public void findUserByComp(String compCode) {
        if (compCode == null) {
            compCode = "NO DATA";
        }
        CmsUserTVOImpl userVO = this.getCmsUserTVO4Display();
        userVO.setbvCompCode(compCode);
        userVO.executeQuery();
    }

    public Number createUser(String compCode) {
        if (compCode == null) {
            CmsCompVORowImpl compRow = (CmsCompVORowImpl)this.getCmsCompVO4Common().first();
            if (compRow != null) {
                compCode = compRow.getCompCode();
            }
        }
        CmsUserTVOImpl userVO = this.getCmsUserTVO();
        CmsUserTVORowImpl userNewRow = (CmsUserTVORowImpl)userVO.createRow();
        userNewRow.setEnableFlag("Y");
        userNewRow.setPassword(EncryptUtil.getMD5String("123456"));
        userNewRow.setCompCode(compCode);
        userVO.insertRowAtRangeIndex(0, userNewRow);
        userVO.setCurrentRow(userNewRow);
        return userNewRow.getUserId();
    }

    public void editUser(Number userId) {
        CmsUserTVOImpl userVO = this.getCmsUserTVO();
        userVO.setbvUserId(userId);
        userVO.executeQuery();
        Row row = userVO.first();
        if (row != null) {
            userVO.setCurrentRow(row);
        }
    }

    public boolean isUserExist(String userName) {
        CmsUserTVOImpl vo = this.getCmsUserTVO4Query();
        vo.setbvUserName(userName);
        vo.executeQuery();
        if (vo.getEstimatedRowCount() > 0) {
            return true;
        }
        return false;
    }

    public void deleteUser(Number userId) {
        System.out.println("userid:" + userId);
        CmsUserTVOImpl userVO = this.getCmsUserTVO();
        userVO.setbvUserId(userId);
        userVO.executeQuery();
        Row row = userVO.first();
        if (row != null) {
            row.remove();
        }
    }

    public void resetPassword(Number userId) {
        CmsUserTVOImpl userVO = this.getCmsUserTVO();
        Row[] rows = userVO.getFilteredRows("UserId", userId);
        if (rows.length > 0) {
            CmsUserTVORowImpl userRow = (CmsUserTVORowImpl)rows[0];
            userRow.setPassword(EncryptUtil.getMD5String("123456"));
        }
    }

    /**
     * 角色管理
     */
    public void findComp4RoleManage(String compStr, String roleType) {
        System.out.println("compStr:" + compStr);
        CmsCompVOImpl compVO = this.getCmsCompVO4RoleManage();
        if (!"ADMIN_PLATFORM".equals(roleType)) {
            compVO.setApplyViewCriteriaName("findCompByGroupVC");
        }
        compVO.setbvCompStr(compStr);
        compVO.executeQuery();
        compVO.setCurrentRow(null);
        this.getCmsRoleUserVO().executeEmptyRowSet();
    }

    public void findComp4Common(String compStr) {
        CmsCompVOImpl compVO = this.getCmsCompVO4Common();
        compVO.setbvCompStr(compStr);
        compVO.executeQuery();
        compVO.setCurrentRow(null);
    }

    public void createRole(String roleType, String compCode) {
        CmsRoleTVOImpl roleVO = this.getCmsRoleTVO();
        CmsRoleTVORowImpl roleNewRow = (CmsRoleTVORowImpl)roleVO.createRow();
        roleNewRow.setRoleType(roleType);
        roleNewRow.setCompCode(compCode);
        roleNewRow.setEnableFlag("Y");
        roleVO.insertRow(roleNewRow);
    }

    public void preUpdateRole(Number roleId) {
        CmsRoleTVOImpl roleVO = this.getCmsRoleTVO();
        roleVO.setbvRoleId(roleId);
        roleVO.executeQuery();
        roleVO.setCurrentRow(roleVO.first());
    }

    public void deleteRole(Number roleId) {
        System.out.println("role id--->" + roleId);
        CmsRoleTVOImpl roleVO = this.getCmsRoleTVO();
        roleVO.setbvRoleId(roleId);
        roleVO.executeQuery();
        roleVO.setRangeSize(-1);

        Row[] rows = roleVO.getAllRowsInRange();
        System.out.println(rows.length);
        if (rows.length > 0) {
            rows[0].remove();
        }
    }

    public void findUserByRoleId(Number roleId) {
        CmsRoleUserVOImpl roleUserVO = this.getCmsRoleUserVO();
        roleUserVO.setbvRoleId(roleId);
        roleUserVO.executeQuery();
    }

    public boolean validateUserCheck4Role(Number roleId, Number userId) {
        CmsUserChecked4RoleVOImpl checkVO = this.getCmsUserChecked4RoleVO();
        checkVO.setbvRoleId(roleId);
        checkVO.setbvUserId(userId);
        checkVO.executeQuery();
        if (checkVO.getEstimatedRowCount() > 0) {
            return true;
        }
        return false;
    }

    public void findUserNoRole(Number roleId, String compStr) {
        System.out.println("roleId--->" + roleId + "   compStr:" + compStr);
        CmsCompVO4UserManageImpl compVO = this.getCmsCompVO4UserManage();
        compVO.setbvCompStr(compStr);
        compVO.executeQuery();
        for (Row row : compVO.getAllRowsInRange()) {
            CmsCompVO4UserManageRowImpl compRow = (CmsCompVO4UserManageRowImpl)row;
            for (Row r : compRow.getCmsUserNoRoleVO().getAllRowsInRange()) {
                CmsUserNoRoleVORowImpl userRow = (CmsUserNoRoleVORowImpl)r;
                if (this.validateUserCheck4Role(roleId, userRow.getUserId())) {
                    userRow.setChildCheckbox(true);
                }
            }
        }
    }

    public void revokeRole(Number roleId, Number userId) {
        System.out.println("revoke role--->" + roleId + "  " + userId);
        CmsRoleUserRelTVOImpl roleUserRelVO = this.getCmsRoleUserRelTVO();
        roleUserRelVO.setbvRoleId(roleId);
        roleUserRelVO.setbvUserId(userId);
        roleUserRelVO.executeQuery();
        CmsRoleUserRelTVORowImpl roleUserRelRow = (CmsRoleUserRelTVORowImpl)roleUserRelVO.first();
        if (roleUserRelRow != null) {
            roleUserRelRow.remove();
        }
    }

    public void initPeriodicalSetup(String compCode) {
        System.out.println("compCode:" + compCode);
        CmsPeriodicalSetupVOImpl vo = this.getCmsPeriodicalSetupVO();
        vo.setbvCompCode(compCode);
        vo.executeQuery();
    }

    public void initNewspaperSetup(String compCode) {
        CmsNewspaperSetupVOImpl vo = this.getCmsNewspaperSetupVO();
        vo.setbvCompCode(compCode);
        vo.executeQuery();
    }

    public void preEditPeriodical(String mode, Number categoryId) {
        CmsPeriodicalEditVOImpl editVO = this.getCmsPeriodicalEditVO();
        editVO.executeQuery();
        CmsPeriodicalEditVORowImpl row = (CmsPeriodicalEditVORowImpl)editVO.first();
        if ("NEW".equals(mode)) {
            row.setPeriodicalType("WEEKLY1");
            row.setPaperType("PAPER");
            row.setLang("CHINESE");
            row.setBookSize("FULL");
            row.setProdSize("787*1092");
            row.setReaderGroup("STUDENT");
            row.setWeekly1("1");
        } else if ("EDIT".equals(mode)) {
            CmsPeriodicalSetupVORowImpl setupRow = getPeriodicalSetupRow(categoryId);
            row.setBookSize(setupRow.getBookSize());
            row.setChiefEditor(setupRow.getChiefEditor());
            row.setCn(setupRow.getCn());
            row.setComp(setupRow.getAttribute2());
            row.setDescription(setupRow.getDescription());
            row.setIssn(setupRow.getIssn());
            row.setLang(setupRow.getLang());
            row.setOrg(setupRow.getOrg());
            row.setPaperType(setupRow.getPaperType());
            row.setPeriodicalName(setupRow.getCategoryName());
            row.setPeriodicalType(setupRow.getPeriodicalType());
            try {
                row.setPrice(new Number(setupRow.getPrice() == null ? 0 : setupRow.getPrice()));
                row.setWords(new Number(setupRow.getWords() == null ? 0 : setupRow.getWords()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            row.setProdSize(setupRow.getProdSize());
            row.setPublishing(setupRow.getPublishing());
            row.setReaderGroup(setupRow.getReaderGroup());
            String periodicalType = setupRow.getPeriodicalType();
            if (setupRow.getAttribute1() != null && setupRow.getAttribute1().length() > 0) {
                String[] array = setupRow.getAttribute1().split(",");
                if ("WEEKLY1".equals(periodicalType)) {
                    row.setWeekly1(array[0]);
                } else if ("BIWEEKLY".equals(periodicalType)) {
                    row.setOldEven(array[0]);
                    row.setWeekly1(array[1]);
                } else if ("MONTHLY".equals(periodicalType)) {
                    row.setMonthly(array[0]);
                } else if ("SEMIMONTHLY".equals(periodicalType)) {
                    row.setFisrtHalfMonth(array[0]);
                    row.setSecondHalfMonth(array[1]);
                } else if ("BIMONTHLY".equals(periodicalType)) {
                    row.setOldEven(array[0]);
                    row.setMonthly(array[1]);
                } else if ("TEN_DAYS".equals(periodicalType)) {
                    row.setFirstTenDays(array[0]);
                    row.setSecondTenDays(array[1]);
                    row.setThirdTenDays(array[2]);
                } else if ("QUARTERLY".equals(periodicalType)) {
                    row.setQuarterly(array[0]);
                    row.setMonthly(array[1]);
                } else if ("WEEKLY2".equals(periodicalType)) {

                }
            }
        }
        editVO.setCurrentRow(row);
    }

    public void preEditNewspaper(String mode, Number categoryId) {
        CmsNewspaperEditVOImpl editVO = this.getCmsNewspaperEditVO();
        editVO.executeQuery();
        CmsNewspaperEditVORowImpl row = (CmsNewspaperEditVORowImpl)editVO.first();
        if ("NEW".equals(mode)) {
            row.setNewspaperType("WEEKLY1");
            row.setPaperType("PAPER");
            row.setLang("CHINESE");
            row.setBookSize("FULL");
            row.setProdSize("787*1092");
            row.setReaderGroup("STUDENT");
            row.setWeekly1("1");
        } else if ("EDIT".equals(mode)) {
            CmsNewspaperSetupVORowImpl setupRow = getNewspaperSetupRow(categoryId);
            row.setBookSize(setupRow.getBookSize());
            row.setChiefEditor(setupRow.getChiefEditor());
            row.setCn(setupRow.getCn());
            row.setComp(setupRow.getAttribute2());
            row.setDescription(setupRow.getDescription());
            row.setIssn(setupRow.getIssn());
            row.setLang(setupRow.getLang());
            row.setOrg(setupRow.getOrg());
            row.setPaperType(setupRow.getPaperType());
            row.setNewspaperName(setupRow.getCategoryName());
            row.setNewspaperType(setupRow.getNewspaperType());
            try {
                row.setPrice(new Number(setupRow.getPrice() == null ? 0 : setupRow.getPrice()));
                row.setWords(new Number(setupRow.getWords() == null ? 0 : setupRow.getWords()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            row.setProdSize(setupRow.getProdSize());
            row.setPublishing(setupRow.getPublishing());
            row.setReaderGroup(setupRow.getReaderGroup());
            String newspaperType = setupRow.getNewspaperType();
            if (setupRow.getAttribute1() != null && setupRow.getAttribute1().length() > 0) {
                String[] array = setupRow.getAttribute1().split(",");
                if ("WEEKLY1".equals(newspaperType)) {
                    row.setWeekly1(array[0]);
                } else if ("BIWEEKLY".equals(newspaperType)) {
                    row.setOldEven(array[0]);
                    row.setWeekly1(array[1]);
                } else if ("MONTHLY".equals(newspaperType)) {
                    row.setMonthly(array[0]);
                } else if ("SEMIMONTHLY".equals(newspaperType)) {
                    row.setFisrtHalfMonth(array[0]);
                    row.setSecondHalfMonth(array[1]);
                } else if ("BIMONTHLY".equals(newspaperType)) {
                    row.setOldEven(array[0]);
                    row.setMonthly(array[1]);
                } else if ("TEN_DAYS".equals(newspaperType)) {
                    row.setFirstTenDays(array[0]);
                    row.setSecondTenDays(array[1]);
                    row.setThirdTenDays(array[2]);
                } else if ("QUARTERLY".equals(newspaperType)) {
                    row.setQuarterly(array[0]);
                    row.setMonthly(array[1]);
                } else if ("WEEKLY2".equals(newspaperType)) {

                }
            }
        }
        editVO.setCurrentRow(row);
    }

    public void preEditPeriod(String mode, Number categoryId, Number setupId, Number year) {
        System.out.println("mode------->>>" + mode + "<----categoryId----->" + categoryId + "<----setupId---->" +
                           setupId + "<------year---->" + year);
        CmsPeriodEditVOImpl editVO = this.getCmsPeriodEditVO();
        editVO.executeQuery();
        CmsPeriodEditVORowImpl row = (CmsPeriodEditVORowImpl)editVO.first();
        if ("NEW".equals(mode)) {
            row.setCategoryId(categoryId);
            row.setYear(year);
            row.setCategoryNum(new Number(0));
        } else if ("EDIT".equals(mode)) {
            CmsPeriodSetupVORowImpl setupRow = getPeriodSetupRow(setupId);
            row.setCategoryId(setupRow.getPeriodicalCategoryId());
            try {
                row.setYear(new Number(setupRow.getYearNum()));
            } catch (SQLException e) {
                System.out.println("preEditPeriod:" + e.getMessage());
            }
            row.setCategoryNum(setupRow.getPeriodNum());
            row.setPublishedDate(setupRow.getPublicDate());
            row.setRemarks(setupRow.getRemarks());
        }
        editVO.setCurrentRow(row);
    }

    public void preEditNews(String mode, Number categoryId, Number setupId, Number year) {
        CmsNewsEditVOImpl editVO = this.getCmsNewsEditVO();
        editVO.executeQuery();
        CmsNewsEditVORowImpl row = (CmsNewsEditVORowImpl)editVO.first();
        if ("NEW".equals(mode)) {
            row.setCategoryId(categoryId);
            row.setYear(year);
            row.setNewsNum(new Number(0));
        } else if ("EDIT".equals(mode)) {
            CmsNewsSetupVORowImpl setupRow = getNewsSetupRow(setupId);
            row.setCategoryId(setupRow.getNewspaperCategoryId());
            row.setYear(setupRow.getYear());
            row.setNewsNum(setupRow.getNewsNum());
            row.setPublishedDate(setupRow.getPublicDate());
            row.setRemarks(setupRow.getRemarks());
        }
        editVO.setCurrentRow(row);
    }

    public String getPeriodicalPublishedDate(CmsPeriodicalEditVORowImpl row) {
        String publishedDate = null;
        String periodicalType = row.getPeriodicalType();
        if ("WEEKLY1".equals(periodicalType)) {
            publishedDate = row.getWeekly1();
        } else if ("BIWEEKLY".equals(periodicalType)) {
            publishedDate = row.getOldEven() + "," + row.getWeekly1();
        } else if ("MONTHLY".equals(periodicalType)) {
            publishedDate = row.getMonthly();
        } else if ("SEMIMONTHLY".equals(periodicalType)) {
            publishedDate = row.getFisrtHalfMonth() + "," + row.getSecondHalfMonth();
        } else if ("BIMONTHLY".equals(periodicalType)) {
            publishedDate = row.getOldEven() + "," + row.getMonthly();
        } else if ("TEN_DAYS".equals(periodicalType)) {
            publishedDate = row.getFirstTenDays() + "," + row.getSecondTenDays() + "," + row.getThirdTenDays();
        } else if ("QUARTERLY".equals(periodicalType)) {
            publishedDate = row.getQuarterly() + "," + row.getMonthly();
        } else if ("WEEKLY2".equals(periodicalType)) {
            publishedDate = row.getWeekly2();
        }
        return publishedDate;
    }

    public String getNewspaperPublishedDate(CmsNewspaperEditVORowImpl row) {
        String publishedDate = null;
        String newspaperType = row.getNewspaperType();
        if ("WEEKLY1".equals(newspaperType)) {
            publishedDate = row.getWeekly1();
        } else if ("BIWEEKLY".equals(newspaperType)) {
            publishedDate = row.getOldEven() + "," + row.getWeekly1();
        } else if ("MONTHLY".equals(newspaperType)) {
            publishedDate = row.getMonthly();
        } else if ("SEMIMONTHLY".equals(newspaperType)) {
            publishedDate = row.getFisrtHalfMonth() + "," + row.getSecondHalfMonth();
        } else if ("BIMONTHLY".equals(newspaperType)) {
            publishedDate = row.getOldEven() + "," + row.getMonthly();
        } else if ("TEN_DAYS".equals(newspaperType)) {
            publishedDate = row.getFirstTenDays() + "," + row.getSecondTenDays() + "," + row.getThirdTenDays();
        } else if ("QUARTERLY".equals(newspaperType)) {
            publishedDate = row.getQuarterly() + "," + row.getMonthly();
        } else if ("WEEKLY2".equals(newspaperType)) {
            publishedDate = row.getWeekly2();
        }
        return publishedDate;
    }

    public String savePeriodicalSetup(Number categoryId, String compCode) {
        CmsPeriodicalEditVORowImpl row = (CmsPeriodicalEditVORowImpl)this.getCmsPeriodicalEditVO().getCurrentRow();
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.save_periodical_setup(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, categoryId);
            st.setObject(3, row.getPeriodicalName());
            st.setObject(4, row.getPeriodicalType());
            st.setObject(5, row.getPaperType());
            st.setObject(6, row.getLang());
            st.setObject(7, row.getChiefEditor());
            st.setObject(8, getPeriodicalPublishedDate(row));
            st.setObject(9, row.getPrice());
            st.setObject(10, row.getCn());
            st.setObject(11, row.getIssn());
            st.setObject(12, row.getBookSize());
            st.setObject(13, row.getWords());
            st.setObject(14, row.getProdSize());
            st.setObject(15, row.getReaderGroup());
            st.setObject(16, row.getComp());
            st.setObject(17, row.getOrg());
            st.setObject(18, row.getPublishing());
            st.setObject(19, row.getDescription());
            st.setObject(20, compCode);
            st.setObject(21, this.getCustomDBTransaction().getUserId());
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public String saveNewspaperSetup(Number categoryId, String compCode) {
        CmsNewspaperEditVORowImpl row = (CmsNewspaperEditVORowImpl)this.getCmsNewspaperEditVO().getCurrentRow();
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.save_newspaper_setup(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, categoryId);
            st.setObject(3, row.getNewspaperName());
            st.setObject(4, row.getNewspaperType());
            st.setObject(5, row.getPaperType());
            st.setObject(6, row.getLang());
            st.setObject(7, row.getChiefEditor());
            st.setObject(8, getNewspaperPublishedDate(row));
            st.setObject(9, row.getPrice());
            st.setObject(10, row.getCn());
            st.setObject(11, row.getIssn());
            st.setObject(12, row.getBookSize());
            st.setObject(13, row.getWords());
            st.setObject(14, row.getProdSize());
            st.setObject(15, row.getReaderGroup());
            st.setObject(16, row.getComp());
            st.setObject(17, row.getOrg());
            st.setObject(18, row.getPublishing());
            st.setObject(19, row.getDescription());
            st.setObject(20, compCode);
            st.setObject(21, this.getCustomDBTransaction().getUserId());
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String savePeriodSetup(String mode, Number setupId, String compCode) {
        CmsPeriodEditVORowImpl row = (CmsPeriodEditVORowImpl)this.getCmsPeriodEditVO().getCurrentRow();
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.save_period_setup(?,?,?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, mode);
            st.setObject(3, setupId);
            st.setObject(4, row.getCategoryId());
            st.setObject(5, row.getYear());
            st.setObject(6, row.getCategoryNum());
            st.setObject(7, row.getPublishedDate());
            st.setObject(8, row.getRemarks());
            st.setObject(9, this.getCustomDBTransaction().getUserId());
            st.setObject(10, compCode);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String saveNewsSetup(String mode, Number setupId, String compCode) {
        CmsNewsEditVORowImpl row = (CmsNewsEditVORowImpl)this.getCmsNewsEditVO().getCurrentRow();
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.save_news_setup(?,?,?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, mode);
            st.setObject(3, setupId);
            st.setObject(4, row.getCategoryId());
            st.setObject(5, row.getYear());
            st.setObject(6, row.getNewsNum());
            st.setObject(7, row.getPublishedDate());
            st.setObject(8, row.getRemarks());
            st.setObject(9, this.getCustomDBTransaction().getUserId());
            st.setObject(10, compCode);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String deletePeriodicalSetup(Number categoryId) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.delete_periodical_setup(?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, categoryId);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            this.getDBTransaction().rollback();
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String deleteNewspaperSetup(Number categoryId) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.delete_newspaper_setup(?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, categoryId);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            this.getDBTransaction().rollback();
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public String deletePeriodSetup(Number setupId) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.delete_period_setup(?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, setupId);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            this.getDBTransaction().rollback();
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String deleteNewsSetup(Number setupId) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.delete_news_setup(?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, setupId);
            st.execute();
            result = (String)st.getObject(1);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            }
        } catch (SQLException e) {
            this.getDBTransaction().rollback();
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public CmsPeriodicalSetupVORowImpl getPeriodicalSetupRow(Number categoryId) {
        CmsPeriodicalSetupVOImpl vo = this.getCmsPeriodicalSetupVO4Query();
        vo.setbvCategoryId(categoryId);
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            return (CmsPeriodicalSetupVORowImpl)row;
        }
        return null;
    }

    public CmsNewspaperSetupVORowImpl getNewspaperSetupRow(Number categoryId) {
        CmsNewspaperSetupVOImpl vo = this.getCmsNewspaperSetupVO4Query();
        vo.setbvCategoryId(categoryId);
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            return (CmsNewspaperSetupVORowImpl)row;
        }
        return null;
    }

    public CmsPeriodSetupVORowImpl getPeriodSetupRow(Number setupId) {
        CmsPeriodSetupVOImpl vo = this.getCmsPeriodSetupVO4Query();
        vo.setbvSetupId(setupId);
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            return (CmsPeriodSetupVORowImpl)row;
        }
        return null;
    }

    public CmsNewsSetupVORowImpl getNewsSetupRow(Number setupId) {
        CmsNewsSetupVOImpl vo = this.getCmsNewsSetupVO4Query();
        vo.setbvSetupId(setupId);
        vo.executeQuery();
        Row row = vo.first();
        if (row != null) {
            return (CmsNewsSetupVORowImpl)row;
        }
        return null;
    }

    public void initPeriodSetupQueryVO(String compCode) {
        this.getDBTransaction().getSession().getUserData().put("compCode", compCode);
        CmsPeriodSetupQueryVOImpl queryVO = this.getCmsPeriodSetupQueryVO();
        queryVO.executeQuery();
        CmsPeriodSetupQueryVORowImpl queryRow = (CmsPeriodSetupQueryVORowImpl)queryVO.first();
        Calendar c = Calendar.getInstance();
        queryRow.setYear(new Number(c.get(Calendar.YEAR)));
        CmsPeriodicalSetupLovImpl lov = this.getCmsPeriodicalSetupLov();
        lov.executeQuery();
        CmsPeriodicalSetupLovRowImpl lovRow = (CmsPeriodicalSetupLovRowImpl)lov.first();
        if (lovRow != null) {
            queryRow.setCategoryId(lovRow.getCategoryId());
        }
        queryVO.setCurrentRow(queryRow);
    }

    public void initNewsSetupQueryVO(String compCode) {
        this.getDBTransaction().getSession().getUserData().put("compCode", compCode);
        CmsNewsSetupQueryVOImpl queryVO = this.getCmsNewsSetupQueryVO();
        queryVO.executeQuery();
        CmsNewsSetupQueryVORowImpl queryRow = (CmsNewsSetupQueryVORowImpl)queryVO.first();
        Calendar c = Calendar.getInstance();
        queryRow.setYear(new Number(c.get(Calendar.YEAR)));
        CmsNewspaperSetupLovImpl lov = this.getCmsNewspaperSetupLov();
        lov.executeQuery();
        CmsNewspaperSetupLovRowImpl lovRow = (CmsNewspaperSetupLovRowImpl)lov.first();
        if (lovRow != null) {
            queryRow.setCategoryId(lovRow.getCategoryId());
        }
        queryVO.setCurrentRow(queryRow);
    }

    public void queryPeriodSetup(Number categoryId, Number year) {
        CmsPeriodSetupQueryVORowImpl queryRow =
            (CmsPeriodSetupQueryVORowImpl)this.getCmsPeriodSetupQueryVO().getCurrentRow();
        if (queryRow != null) {
            CmsPeriodSetupVOImpl periodSetupVO = this.getCmsPeriodSetupVO();
            periodSetupVO.setbvCategoryId(categoryId == null ? queryRow.getCategoryId() : categoryId);
            periodSetupVO.setbvYear(year == null ? queryRow.getYear() : year);
            periodSetupVO.executeQuery();
        }
    }

    public void queryNewsSetup(Number categoryId, Number year) {
        CmsNewsSetupQueryVORowImpl queryRow =
            (CmsNewsSetupQueryVORowImpl)this.getCmsNewsSetupQueryVO().getCurrentRow();
        if (queryRow != null) {
            CmsNewsSetupVOImpl newsSetupVO = this.getCmsNewsSetupVO();
            newsSetupVO.setbvCategoryId(categoryId == null ? queryRow.getCategoryId() : categoryId);
            newsSetupVO.setbvYear(year == null ? queryRow.getYear() : year);
            newsSetupVO.executeQuery();
            System.out.println("newsSetupVO--->>" + newsSetupVO.getRowCount());
        }
    }

    public Map<String, String> addComp() {
        Map<String, String> map = new HashMap<String, String>();
        CmsCompEditVORowImpl row = (CmsCompEditVORowImpl)this.getCmsCompEditVO().getCurrentRow();
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin CMS_ADMIN_PLATFORM_PKG.add_comp(?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.setObject(1, row.getCompCode().trim());
            st.setObject(2, row.getCompName().trim());
            st.setObject(3, row.getCompDesc());
            st.setObject(4, row.getGroupFlag());
            st.setObject(5, this.getCustomDBTransaction().getUserId());
            st.registerOutParameter(6, Types.VARCHAR);
            st.registerOutParameter(7, Types.VARCHAR);
            st.executeUpdate();
            result = (String)st.getObject(6);
            if ("S".equals(result)) {
                map.put("compCode", row.getCompCode().trim());
                this.getDBTransaction().commit();
            } else {
                map.put("compCode", null);
            }
            map.put("returnCode", result);
            map.put("returnMsg", (String)st.getObject(7));
        } catch (SQLException e) {
            result = e.getMessage();
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public Map<String, String> modifyComp() {
        Map<String, String> map = new HashMap<String, String>();
        CmsCompEditVORowImpl row = (CmsCompEditVORowImpl)this.getCmsCompEditVO().getCurrentRow();
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin CMS_ADMIN_PLATFORM_PKG.modify_comp(?,?,?,?,?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            System.out.println(row.getCompCode() + "  " + row.getCompName());
            st.setObject(1, row.getCompCode().trim());
            st.setObject(2, row.getCompName().trim());
            st.setObject(3, row.getCompDesc());
            System.out.println("row.getGroupFlag():" + row.getGroupFlag());
            st.setObject(4, row.getGroupFlag());
            st.setObject(5, this.getCustomDBTransaction().getUserId());
            st.registerOutParameter(6, Types.VARCHAR);
            st.registerOutParameter(7, Types.VARCHAR);
            st.executeUpdate();
            result = (String)st.getObject(6);
            if ("S".equals(result)) {
                map.put("compCode", row.getCompCode().trim());
                this.getDBTransaction().commit();
            } else {
                map.put("compCode", null);
            }
            map.put("returnCode", result);
            map.put("returnMsg", (String)st.getObject(7));
        } catch (SQLException e) {
            result = e.getMessage();
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public String deleteComp(String compCode) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin CMS_ADMIN_PLATFORM_PKG.delete_comp(?,?,?); end;",
                                                 this.getDBTransaction().DEFAULT);
            st.setObject(1, compCode);
            st.registerOutParameter(2, Types.VARCHAR);
            st.registerOutParameter(3, Types.VARCHAR);
            st.executeUpdate();
            result = (String)st.getObject(2);
            if ("S".equals(result)) {
                this.getDBTransaction().commit();
            } else {
                result = (String)st.getObject(3);
            }
        } catch (SQLException e) {
            result = e.getMessage();
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public void preEditComp(String mode, String compCode) {
        CmsCompEditVOImpl editVO = this.getCmsCompEditVO();
        editVO.executeQuery();
        CmsCompEditVORowImpl row = (CmsCompEditVORowImpl)editVO.first();
        if ("EDIT".equals(mode)) {
            CmsCompVORowImpl compRow = getCompRow(compCode);
            row.setCompCode(compRow.getCompCode());
            row.setCompName(compRow.getCompName());
            row.setCompDesc(compRow.getCompDesc());
            row.setGroupFlag(compRow.getGroupFlag());
        }
        editVO.setCurrentRow(row);
    }

    public CmsCompVORowImpl getCompRow(String compCode) {
        CmsCompVOImpl compVO = this.getCmsCompVO4Query();
        compVO.setbvCompStr("ALL");
        compVO.setbvCompCode(compCode);
        compVO.executeQuery();
        Row row = compVO.first();
        if (row != null) {
            return (CmsCompVORowImpl)row;
        }
        return null;
    }

    public String getFirstCompCode() {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := CMS_ADMIN_PLATFORM_PKG.get_first_comp_code; end;",
                                                 this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.execute();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void queryDoc4SyncCancel(String compCode) {
        CmsSyncCancelVOImpl vo = this.getCmsSyncCancelVO();
        vo.setbvCompCode(compCode);
        vo.executeQuery();
    }

    public String cancelSync(Number docId) {
        String result = null;
        CallableStatement st = null;
        try {
            st =
 this.getDBTransaction().createCallableStatement("begin ? := cms_common_pkg.cancel_sync(?); end;", this.getDBTransaction().DEFAULT);
            st.registerOutParameter(1, Types.VARCHAR);
            st.setObject(2, docId.toString());
            st.execute();
            result = (String)st.getObject(1);
        } catch (SQLException e) {
            this.getDBTransaction().rollback();
            result = null;
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 查询openlog
     * */
    public String queryOpenLog(String operator, String operateTime, String compName, String operateDesc) {
        CmsAdminOperLogTVOImpl vo = this.getCmsAdminOperLogTVO1();
        vo.setWhereClause(null);
        vo.setWhereClauseParams(null);
        try {
            String s = "";
            if (operator != null && !"".equals(operator)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " display_name like '%" + operator + "%'";
            }

            if (operateTime != null && !"".equals(operateTime)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + "  creation_date_display like '%" + operateTime + "%'";
            }

            if (compName != null && !"".equals(compName)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " comp_name like '%" + compName + "%'";
            }

            if (operateDesc != null && !"".equals(operateDesc)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " LOG_CONTENT like '%" + operateDesc + "%'";
            }

            if (s != null && !"".equals(s)) {
                vo.setWhereClause(s.toString());
                vo.executeQuery();
            } else {
                vo.executeQuery();
            }
        } catch (Exception e) {
            return "failed";
        }
        return null;
    }


    /**
     * 查询syncCanel
     * */
    public String querySyncCanel(String uniqueResName, String libName, String status, String creater,
                                 String creationDate, String compCode) {
        CmsSyncCancelVOImpl vo = this.getCmsSyncCancelVO();
        vo.setWhereClause(null);
        vo.setWhereClauseParams(null);
        vo.setNamedWhereClauseParam("bvCompCode", compCode);
        try {
            String s = "";
            if (uniqueResName != null && !"".equals(uniqueResName)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " unique_res_name like '%" + uniqueResName + "%'";
            }

            if (libName != null && !"".equals(libName)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + "  lib_name like '%" + libName + "%'";
            }

            if (status != null && !"".equals(status)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " status_name like '%" + status + "%'";
            }

            if (creater != null && !"".equals(creater)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " diplay_name like '%" + creater + "%'";
            }

            if (creationDate != null && !"".equals(creationDate)) {
                if (s != null && !"".equals(s)) {
                    s = s + " and ";
                }
                s = s + " creation_date like '%" + creationDate + "%'";
            }

            if (s != null && !"".equals(s)) {
                vo.setWhereClause(s.toString());
                vo.executeQuery();

            } else {
                vo.executeQuery();
            }
        } catch (Exception e) {
            return "failed";
        }
        return null;
    }

    public void queryExtendAttr(String compCode, String libCode) {
        CmsExtendAttrVOImpl vo = this.getCmsExtendAttrVO();
        vo.setbvCompCode(compCode);
        vo.setbvLibCode(libCode);
        vo.executeQuery();
    }

    public Number preEditExtendAttr(String operType, Number extendAttrId, String compCode, String libCode) {
        CmsExtendAttrTVOImpl vo = this.getCmsExtendAttrTVO();
        if ("CREATE".equals(operType)) {
            CmsExtendAttrTVORowImpl row = (CmsExtendAttrTVORowImpl)vo.createRow();
            row.setCompCode(compCode);
            row.setLibCode(libCode);
            vo.insertRow(row);
            vo.setCurrentRow(row);
            return row.getExtendAttrId();
        } else {
            vo.setbvExtendAttrId(extendAttrId);
            vo.executeQuery();
            CmsExtendAttrTVORowImpl row = (CmsExtendAttrTVORowImpl)vo.first();
            if (row != null) {
                vo.setCurrentRow(row);
            }
            return extendAttrId;
        }
    }

    public String deleteExtendAttr(Number extendAttrId) {
        String result = "S";
        CmsExtendAttrTVOImpl vo = this.getCmsExtendAttrTVO();
        vo.setbvExtendAttrId(extendAttrId);
        vo.executeQuery();
        CmsExtendAttrTVORowImpl row = (CmsExtendAttrTVORowImpl)vo.first();
        if (row != null) {
            CmsExtendValueListVOImpl valueListVO = this.getCmsExtendValueListVO();
            valueListVO.setbvExtendAttrId(extendAttrId);
            valueListVO.executeQuery();
            for (Row r : valueListVO.getAllRowsInRange()) {
                CmsExtendValueListVORowImpl valueListRow = (CmsExtendValueListVORowImpl)r;
                this.deleteExtendAttrValueList(valueListRow.getListId());
            }
            row.remove();
            this.getDBTransaction().commit();
        }
        return result;
    }

    public boolean isAttrCodeExsit(String attrCode, String compCode, String libCode) {
        CmsExtendAttrVOImpl vo = this.getCmsExtendAttrVO4Query();
        vo.setbvAttrCode(attrCode);
        vo.setbvCompCode(compCode);
        vo.setbvLibCode(libCode);
        vo.executeQuery();
        if (vo.getEstimatedRowCount() > 0) {
            return true;
        }
        return false;
    }

    public void queryExtendAttrValueList(Number extendAttrId) {
        CmsExtendValueListVOImpl vo = this.getCmsExtendValueListVO();
        vo.setbvExtendAttrId(extendAttrId);
        vo.executeQuery();
    }

    public Number preEditExtendAttrValueList(String operType, Number extendAttrId, Number listId) {
        CmsExtendValueListTVOImpl vo = this.getCmsExtendValueListTVO();
        if ("CREATE".equals(operType)) {
            CmsExtendValueListTVORowImpl row = (CmsExtendValueListTVORowImpl)vo.createRow();
            row.setExtendAttrId(extendAttrId);
            vo.insertRow(row);
            vo.setCurrentRow(row);
            return row.getListId();
        } else {
            vo.setbvListId(listId);
            vo.executeQuery();
            CmsExtendValueListTVORowImpl row = (CmsExtendValueListTVORowImpl)vo.first();
            if (row != null) {
                vo.setCurrentRow(row);
            }
            return listId;
        }
    }

    public String deleteExtendAttrValueList(Number listId) {
        String result = "S";
        CmsExtendValueListTVOImpl vo = this.getCmsExtendValueListTVO();
        vo.setbvListId(listId);
        vo.executeQuery();
        CmsExtendValueListTVORowImpl row = (CmsExtendValueListTVORowImpl)vo.first();
        if (row != null) {
            row.remove();
            this.getDBTransaction().commit();
        }
        return result;
    }

    public boolean isAttrValueCodeExsit(String valueCode, Number extendAttrId) {
        CmsExtendValueListVOImpl vo = this.getCmsExtendValueListVO4Query();
        vo.setbvValueCode(valueCode);
        vo.setbvExtendAttrId(extendAttrId);
        vo.executeQuery();
        if (vo.getEstimatedRowCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Container's getter for CmsUserTVO1.
     * @return CmsUserTVO1
     */
    public CmsUserTVOImpl getCmsUserTVO() {
        return (CmsUserTVOImpl)findViewObject("CmsUserTVO");
    }

    /**
     * Container's getter for CmsUserCompStrVO1.
     * @return CmsUserCompStrVO1
     */
    public CmsUserCompStrVOImpl getCmsUserCompStrVO() {
        return (CmsUserCompStrVOImpl)findViewObject("CmsUserCompStrVO");
    }


    /**
     * Container's getter for CmsCompVO1.
     * @return CmsCompVO1
     */
    public CmsCompVOImpl getCmsCompVO4RoleManage() {
        return (CmsCompVOImpl)findViewObject("CmsCompVO4RoleManage");
    }


    /**
     * Container's getter for CmsRoleTVO1.
     * @return CmsRoleTVO1
     */
    public CmsRoleTVOImpl getCmsRoleTVO() {
        return (CmsRoleTVOImpl)findViewObject("CmsRoleTVO");
    }

    /**
     * Container's getter for CmsRoleUserVO1.
     * @return CmsRoleUserVO1
     */
    public CmsRoleUserVOImpl getCmsRoleUserVO() {
        return (CmsRoleUserVOImpl)findViewObject("CmsRoleUserVO");
    }

    /**
     * Container's getter for CmsRoleVO1.
     * @return CmsRoleVO1
     */
    public CmsRoleVOImpl getCmsRoleVO() {
        return (CmsRoleVOImpl)findViewObject("CmsRoleVO");
    }


    /**
     * Container's getter for CuxUserAllRoleVO4WorkPF.
     * @return CuxUserAllRoleVO4WorkPF
     */
    public CmsUserAllRoleVOImpl getCmsUserAllRoleVO4WorkPF() {
        return (CmsUserAllRoleVOImpl)findViewObject("CmsUserAllRoleVO4WorkPF");
    }

    /**
     * Container's getter for CuxUserAllRoleVO4PubPF.
     * @return CuxUserAllRoleVO4PubPF
     */
    public CmsUserAllRoleVOImpl getCmsUserAllRoleVO4PubPF() {
        return (CmsUserAllRoleVOImpl)findViewObject("CmsUserAllRoleVO4PubPF");
    }

    /**
     * Container's getter for CuxUserAllRoleVO4AdminPF.
     * @return CuxUserAllRoleVO4AdminPF
     */
    public CmsUserAllRoleVOImpl getCmsUserAllRoleVO4AdminPF() {
        return (CmsUserAllRoleVOImpl)findViewObject("CmsUserAllRoleVO4AdminPF");
    }

    /**
     * Container's getter for CmsRoleUserRelTVO4SetUserRole.
     * @return CmsRoleUserRelTVO4SetUserRole
     */
    public CmsRoleUserRelTVOImpl getCmsRoleUserRelTVO4SetUserRole() {
        return (CmsRoleUserRelTVOImpl)findViewObject("CmsRoleUserRelTVO4SetUserRole");
    }

    /**
     * Container's getter for CmsRoleUserRelTVO1.
     * @return CmsRoleUserRelTVO1
     */
    public CmsRoleUserRelTVOImpl getCmsRoleUserRelTVO() {
        return (CmsRoleUserRelTVOImpl)findViewObject("CmsRoleUserRelTVO");
    }

    /**
     * Container's getter for CmsFirstLevelLibVO1.
     * @return CmsFirstLevelLibVO1
     */
    public CmsFirstLevelLibVOImpl getCmsFirstLevelLibVO1() {
        return (CmsFirstLevelLibVOImpl)findViewObject("CmsFirstLevelLibVO1");
    }


    /**
     * Container's getter for CmsRoleLibRelTVO1.
     * @return CmsRoleLibRelTVO1
     */
    public CmsRoleLibRelTVOImpl getCmsRoleLibRelTVO1() {
        return (CmsRoleLibRelTVOImpl)findViewObject("CmsRoleLibRelTVO1");
    }

    /**
     * Container's getter for CmsRoleLibVO1.
     * @return CmsRoleLibVO1
     */
    public CmsRoleLibVOImpl getCmsRoleLibVO1() {
        return (CmsRoleLibVOImpl)findViewObject("CmsRoleLibVO1");
    }

    /**
     * Container's getter for CmsResType4WPVO1.
     * @return CmsResType4WPVO1
     */
    public CmsResType4WPVOImpl getCmsResType4WPVO1() {
        return (CmsResType4WPVOImpl)findViewObject("CmsResType4WPVO1");
    }

    /**
     * Container's getter for CmsRoleResRelTVO1.
     * @return CmsRoleResRelTVO1
     */
    public CmsRoleResRelTVOImpl getCmsRoleResRelTVO1() {
        return (CmsRoleResRelTVOImpl)findViewObject("CmsRoleResRelTVO1");
    }

    /**
     * Container's getter for CmsResDownloadVO.
     * @return CmsResDownloadVO
     */
    public CmsResDownloadVOImpl getCmsResDownloadVO() {
        return (CmsResDownloadVOImpl)findViewObject("CmsResDownloadVO");
    }

    /**
     * Container's getter for CmsRoleResRel4DownloadAuthTVO.
     * @return CmsRoleResRel4DownloadAuthTVO
     */
    public CmsRoleResRel4DownloadAuthTVOImpl getCmsRoleResRel4DownloadAuthTVO() {
        return (CmsRoleResRel4DownloadAuthTVOImpl)findViewObject("CmsRoleResRel4DownloadAuthTVO");
    }


    /**
     * Container's getter for CmsPeriodicalSetupVO.
     * @return CmsPeriodicalSetupVO
     */
    public CmsPeriodicalSetupVOImpl getCmsPeriodicalSetupVO() {
        return (CmsPeriodicalSetupVOImpl)findViewObject("CmsPeriodicalSetupVO");
    }


    /**
     * Container's getter for CmsPeriodicalEditVO.
     * @return CmsPeriodicalEditVO
     */
    public CmsPeriodicalEditVOImpl getCmsPeriodicalEditVO() {
        return (CmsPeriodicalEditVOImpl)findViewObject("CmsPeriodicalEditVO");
    }

    /**
     * Container's getter for WeeklyNewspaperVO.
     * @return WeeklyNewspaperVO
     */
    public ViewObjectImpl getWeeklyNewspaperVO() {
        return (ViewObjectImpl)findViewObject("WeeklyNewspaperVO");
    }

    /**
     * Container's getter for CmsPeriodicalSetupVO4Query.
     * @return CmsPeriodicalSetupVO4Query
     */
    public CmsPeriodicalSetupVOImpl getCmsPeriodicalSetupVO4Query() {
        return (CmsPeriodicalSetupVOImpl)findViewObject("CmsPeriodicalSetupVO4Query");
    }


    /**
     * Container's getter for CmsPeriodSetupQueryVO.
     * @return CmsPeriodSetupQueryVO
     */
    public CmsPeriodSetupQueryVOImpl getCmsPeriodSetupQueryVO() {
        return (CmsPeriodSetupQueryVOImpl)findViewObject("CmsPeriodSetupQueryVO");
    }

    /**
     * Container's getter for CmsPeriodSetupVO.
     * @return CmsPeriodSetupVO
     */
    public CmsPeriodSetupVOImpl getCmsPeriodSetupVO() {
        return (CmsPeriodSetupVOImpl)findViewObject("CmsPeriodSetupVO");
    }

    /**
     * Container's getter for CmsPeriodicalSetupLov.
     * @return CmsPeriodicalSetupLov
     */
    public CmsPeriodicalSetupLovImpl getCmsPeriodicalSetupLov() {
        return (CmsPeriodicalSetupLovImpl)findViewObject("CmsPeriodicalSetupLov");
    }

    /**
     * Container's getter for CmsPeriodEditVO.
     * @return CmsPeriodEditVO
     */
    public CmsPeriodEditVOImpl getCmsPeriodEditVO() {
        return (CmsPeriodEditVOImpl)findViewObject("CmsPeriodEditVO");
    }

    /**
     * Container's getter for CmsPeriodSetupVO4Query.
     * @return CmsPeriodSetupVO4Query
     */
    public CmsPeriodSetupVOImpl getCmsPeriodSetupVO4Query() {
        return (CmsPeriodSetupVOImpl)findViewObject("CmsPeriodSetupVO4Query");
    }


    /**
     * Container's getter for CmsNewsEditVO.
     * @return CmsNewsEditVO
     */
    public CmsNewsEditVOImpl getCmsNewsEditVO() {
        return (CmsNewsEditVOImpl)findViewObject("CmsNewsEditVO");
    }

    /**
     * Container's getter for CmsNewspaperEditVO.
     * @return CmsNewspaperEditVO
     */
    public CmsNewspaperEditVOImpl getCmsNewspaperEditVO() {
        return (CmsNewspaperEditVOImpl)findViewObject("CmsNewspaperEditVO");
    }

    /**
     * Container's getter for CmsNewspaperSetupLov.
     * @return CmsNewspaperSetupLov
     */
    public CmsNewspaperSetupLovImpl getCmsNewspaperSetupLov() {
        return (CmsNewspaperSetupLovImpl)findViewObject("CmsNewspaperSetupLov");
    }

    /**
     * Container's getter for CmsNewspaperSetupVO.
     * @return CmsNewspaperSetupVO
     */
    public CmsNewspaperSetupVOImpl getCmsNewspaperSetupVO() {
        return (CmsNewspaperSetupVOImpl)findViewObject("CmsNewspaperSetupVO");
    }

    /**
     * Container's getter for CmsNewspaperSetupVO4Query.
     * @return CmsNewspaperSetupVO4Query
     */
    public CmsNewspaperSetupVOImpl getCmsNewspaperSetupVO4Query() {
        return (CmsNewspaperSetupVOImpl)findViewObject("CmsNewspaperSetupVO4Query");
    }

    /**
     * Container's getter for CmsNewsSetupQueryVO.
     * @return CmsNewsSetupQueryVO
     */
    public CmsNewsSetupQueryVOImpl getCmsNewsSetupQueryVO() {
        return (CmsNewsSetupQueryVOImpl)findViewObject("CmsNewsSetupQueryVO");
    }

    /**
     * Container's getter for CmsNewsSetupVO.
     * @return CmsNewsSetupVO
     */
    public CmsNewsSetupVOImpl getCmsNewsSetupVO() {
        return (CmsNewsSetupVOImpl)findViewObject("CmsNewsSetupVO");
    }

    /**
     * Container's getter for CmsNewsSetupVO4Query.
     * @return CmsNewsSetupVO4Query
     */
    public CmsNewsSetupVOImpl getCmsNewsSetupVO4Query() {
        return (CmsNewsSetupVOImpl)findViewObject("CmsNewsSetupVO4Query");
    }

    /**
     * Container's getter for CmsCompEditVO.
     * @return CmsCompEditVO
     */
    public CmsCompEditVOImpl getCmsCompEditVO() {
        return (CmsCompEditVOImpl)findViewObject("CmsCompEditVO");
    }

    /**
     * Container's getter for CmsCompVO4Query.
     * @return CmsCompVO4Query
     */
    public CmsCompVOImpl getCmsCompVO4Query() {
        return (CmsCompVOImpl)findViewObject("CmsCompVO4Query");
    }

    /**
     * Container's getter for CmsCompVO4Common.
     * @return CmsCompVO4Common
     */
    public CmsCompVOImpl getCmsCompVO4Common() {
        return (CmsCompVOImpl)findViewObject("CmsCompVO4Common");
    }


    /**
     * Container's getter for CmsNewsSetupVO1.
     * @return CmsNewsSetupVO1
     */
    public CmsNewsSetupVOImpl getCmsNewsSetupVO1() {
        return (CmsNewsSetupVOImpl)findViewObject("CmsNewsSetupVO1");
    }

    /**
     * Container's getter for CmsPeriodSetupVO1.
     * @return CmsPeriodSetupVO1
     */
    public CmsPeriodSetupVOImpl getCmsPeriodSetupVO1() {
        return (CmsPeriodSetupVOImpl)findViewObject("CmsPeriodSetupVO1");
    }

    /**
     * Container's getter for CmsWsConfigTVO1.
     * @return CmsWsConfigTVO1
     */
    public ViewObjectImpl getCmsWsConfigTVO1() {
        return (ViewObjectImpl)findViewObject("CmsWsConfigTVO1");
    }


    /**
     * Container's getter for CmsUserTVO2.
     * @return CmsUserTVO2
     */
    public CmsUserTVOImpl getCmsUserTVO1() {
        return (CmsUserTVOImpl)findViewObject("CmsUserTVO1");
    }

    /**
     * Container's getter for CmsSyncCancelVO.
     * @return CmsSyncCancelVO
     */
    public CmsSyncCancelVOImpl getCmsSyncCancelVO() {
        return (CmsSyncCancelVOImpl)findViewObject("CmsSyncCancelVO");
    }

    /**
     * Container's getter for CmsRoleUserRelTVO4Query.
     * @return CmsRoleUserRelTVO4Query
     */
    public CmsRoleUserRelTVOImpl getCmsRoleUserRelTVO4Query() {
        return (CmsRoleUserRelTVOImpl)findViewObject("CmsRoleUserRelTVO4Query");
    }

    /**
     * Container's getter for CmsAdminOperLogTVO1.
     * @return CmsAdminOperLogTVO1
     */
    public CmsAdminOperLogTVOImpl getCmsAdminOperLogTVO1() {
        return (CmsAdminOperLogTVOImpl)findViewObject("CmsAdminOperLogTVO1");
    }

    /**
     * Container's getter for CmsLib4ExtendAttrVO.
     * @return CmsLib4ExtendAttrVO
     */
    public CmsLib4ExtendAttrVOImpl getCmsLib4ExtendAttrVO() {
        return (CmsLib4ExtendAttrVOImpl)findViewObject("CmsLib4ExtendAttrVO");
    }

    /**
     * Container's getter for CmsExtendAttrVO.
     * @return CmsExtendAttrVO
     */
    public CmsExtendAttrVOImpl getCmsExtendAttrVO() {
        return (CmsExtendAttrVOImpl)findViewObject("CmsExtendAttrVO");
    }

    /**
     * Container's getter for CmsExtendAttrTVO.
     * @return CmsExtendAttrTVO
     */
    public CmsExtendAttrTVOImpl getCmsExtendAttrTVO() {
        return (CmsExtendAttrTVOImpl)findViewObject("CmsExtendAttrTVO");
    }

    /**
     * Container's getter for CmsExtendAttrVO4Query.
     * @return CmsExtendAttrVO4Query
     */
    public CmsExtendAttrVOImpl getCmsExtendAttrVO4Query() {
        return (CmsExtendAttrVOImpl)findViewObject("CmsExtendAttrVO4Query");
    }

    /**
     * Container's getter for CmsExtendValueListVO.
     * @return CmsExtendValueListVO
     */
    public CmsExtendValueListVOImpl getCmsExtendValueListVO() {
        return (CmsExtendValueListVOImpl)findViewObject("CmsExtendValueListVO");
    }

    /**
     * Container's getter for CmsExtendValueListTVO.
     * @return CmsExtendValueListTVO
     */
    public CmsExtendValueListTVOImpl getCmsExtendValueListTVO() {
        return (CmsExtendValueListTVOImpl)findViewObject("CmsExtendValueListTVO");
    }

    /**
     * Container's getter for CmsExtendValueListVO4Query.
     * @return CmsExtendValueListVO4Query
     */
    public CmsExtendValueListVOImpl getCmsExtendValueListVO4Query() {
        return (CmsExtendValueListVOImpl)findViewObject("CmsExtendValueListVO4Query");
    }

    /**
     * Container's getter for CmsUserTVO4Display.
     * @return CmsUserTVO4Display
     */
    public CmsUserTVOImpl getCmsUserTVO4Display() {
        return (CmsUserTVOImpl)findViewObject("CmsUserTVO4Display");
    }

    /**
     * Container's getter for CmsUserTVO4Query.
     * @return CmsUserTVO4Query
     */
    public CmsUserTVOImpl getCmsUserTVO4Query() {
        return (CmsUserTVOImpl)findViewObject("CmsUserTVO4Query");
    }

    /**
     * Container's getter for CmsCompVO4UserManage.
     * @return CmsCompVO4UserManage
     */
    public CmsCompVO4UserManageImpl getCmsCompVO4UserManage() {
        return (CmsCompVO4UserManageImpl)findViewObject("CmsCompVO4UserManage");
    }

    /**
     * Container's getter for CmsUserNoRoleVO.
     * @return CmsUserNoRoleVO
     */
    public CmsUserNoRoleVOImpl getCmsUserNoRoleVO() {
        return (CmsUserNoRoleVOImpl)findViewObject("CmsUserNoRoleVO");
    }

    /**
     * Container's getter for CmsCompUserVL4Select1.
     * @return CmsCompUserVL4Select1
     */
    public ViewLinkImpl getCmsCompUserVL4Select1() {
        return (ViewLinkImpl)findViewLink("CmsCompUserVL4Select1");
    }

    /**
     * Container's getter for CmsUserTVO4RoleSet.
     * @return CmsUserTVO4RoleSet
     */
    public CmsUserTVOImpl getCmsUserTVO4RoleSet() {
        return (CmsUserTVOImpl)findViewObject("CmsUserTVO4RoleSet");
    }

    /**
     * Container's getter for CmsCompQueryVO.
     * @return CmsCompQueryVO
     */
    public CmsCompQueryVOImpl getCmsCompQueryVO() {
        return (CmsCompQueryVOImpl)findViewObject("CmsCompQueryVO");
    }

    /**
     * Container's getter for CmsUserChecked4RoleVO.
     * @return CmsUserChecked4RoleVO
     */
    public CmsUserChecked4RoleVOImpl getCmsUserChecked4RoleVO() {
        return (CmsUserChecked4RoleVOImpl)findViewObject("CmsUserChecked4RoleVO");
    }
}
