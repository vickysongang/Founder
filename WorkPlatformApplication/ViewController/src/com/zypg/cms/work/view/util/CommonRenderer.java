package com.zypg.cms.work.view.util;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.foldermanager.model.Folder;
import com.zypg.cms.foldermanager.model.FolderFactory;
import com.zypg.cms.foldermanager.model.FtpFile;
import com.zypg.cms.foldermanager.model.LoadBatchUtil;
import com.zypg.cms.work.model.WorkAMImpl;
import com.zypg.cms.work.model.am.ResExpAMImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocDisplayWayVOImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocDisplayWayVORowImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatusVOImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatusVORowImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocUcmRelVORowImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import oracle.stellent.ridc.IdcClientException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SortEvent;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.SortCriterion;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class CommonRenderer extends CustomManagedBean {
    private Map<String, String> resAuthMap = new HashMap<String, String>();
    private WorkAMImpl workAm;
    private ResExpAMImpl resExpAm;
    private RichSelectBooleanCheckbox selectAllCheckBox;
    private RichTable table;
    private CustomTableModel tableModel;
    private RichInputText keyworkInputText;
    private RichInputText keywordsInputText;
    private RichInputText keywork4BatchEditInputText;
    private RichInputText keywords4BatchEditInputText;
    private RichInputText searchInput;
    private RichPopup deletePopup;
    private RichPopup destoryPopup;
    private RichPopup fileManagePopup;
    private RichPopup viewSyncLogPopup;
    private RichPanelGroupLayout coverPanelGroupLayout;

    public CommonRenderer() {

    }

    public CommonRenderer(CustomTableModel tableModel) {
        this.tableModel = tableModel;
        this.setWorkAm(CommonUtil.getWorkAM());
        this.setResExpAm(CommonUtil.getResExpAM());
        this.setResAuthMap(workAm.filterResAuth(CommonUtil.getLibCode(), CommonUtil.getCurrStatusCode(),
                                                CommonUtil.getDeleteFlag()));
    }

    /**
     * table 按汉字拼音首字母排序
     * @param sortEvent
     */
    public void tableSortListener(SortEvent sortEvent) {
        List<SortCriterion> sortList = sortEvent.getSortCriteria();
        SortCriterion sortCriterion = sortList.get(0);
        RichTable richTable = (RichTable)sortEvent.getComponent();
        String isAsending = sortEvent.getSortCriteria().get(0).isAscending() == true ? "asc" : "desc";
        String a = this.stringUpperCase(sortCriterion.getProperty());
        ViewObject vo = tableModel.getViewObject();
        if (vo.getAttributeDef(vo.getAttributeIndexOf(a)).getJavaType().toString().endsWith("String")) {
            vo.setOrderByClause("nlssort(" + this.stringLowerCase(sortCriterion.getProperty()) +
                                ",'NLS_SORT=SCHINESE_PINYIN_M') " + isAsending);
            vo.executeQuery();
            vo.setSortBy(null);
            richTable.setSortCriteria(null);
        }
        this.refreshUIComponent(richTable);
    }

    public String stringUpperCase(String str) {
        StringBuffer bsf = new StringBuffer();
        char[] b = str.toCharArray();
        for (int i = 0; i < b.length; i++) {
            String st = String.valueOf(b[i]);
            if (i == 0) {
                bsf.append(st.toUpperCase());
            } else {
                bsf.append(st);
            }
        }
        return bsf.toString();
    }

    public String stringLowerCase(String str) {
        StringBuffer bsf = new StringBuffer();
        char[] b = str.toCharArray();
        for (char c : b) {
            int d = (int)c;
            String st = String.valueOf(c);
            String lowerCase = st.toLowerCase();
            if (65 <= d && d <= 90) {
                bsf.append("_");
                bsf.append(lowerCase);
            } else {
                bsf.append(st);
            }
        }
        return bsf.toString();
    }


    /**
     * 文档状态变更
     * @param statusCode
     */
    public void statusValueChange(String statusCode) {
        System.out.println("statusCode:" + statusCode);
        //将旧状态中选中的记录清除掉
        List<String> oldSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (oldSelectedList != null && oldSelectedList.size() > 0) {
            CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", new ArrayList<String>());
        }
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "N");
        this.setExpressionValue("#{pageFlowScope.currStatusCode}", statusCode);
        this.setResAuthMap(workAm.filterResAuth(CommonUtil.getLibCode(), statusCode, CommonUtil.getDeleteFlag()));
        String searchMode = (String)this.resolveExpression("#{pageFlowScope.searchMode}");
        System.out.println("searchMode:" + searchMode);
        if ("SIMPLE".equals(searchMode)) {
            String searchInput = (String)this.resolveExpression("#{pageFlowScope.searchInput}");
            System.out.println("searchInput::" + searchInput);
            if (searchInput != null && searchInput.length() > 0) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
                paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
                paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
                paramMap.put("bvSearchInput", this.getSearchInput().getValue());
                paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
                this.setExpressionValue("#{pageFlowScope.searchInput}", this.getSearchInput().getValue());
                CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "N");
                paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
                tableModel.setParamMap(paramMap);
                tableModel.getModel();
                //            CommonUtil.getWorkAM().getSearchStatusCode(CommonUtil.getLibCode(),
                //                                                       (Number)this.resolveExpression("#{pageFlowScope.categoryId}"),
                //                                                       (String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"),
                //                                                       (String)this.resolveExpression("#{pageFlowScope.deleteFlag}"),
                //                                                       searchInput, CommonUtil.getCompCode());
            } else {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                if (!"Y".equals(this.resolveExpression("#{pageFlowScope.deleteFlag}"))) {
                    paramMap.put("bvStatus", statusCode);
                } else {
                    paramMap.put("bvStatus", null);
                }
                if ("APPLY".equals(CommonUtil.getLibCode())) {
                    paramMap.put("bvUserId", CommonUtil.getUserId());
                }
                paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
                paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
                paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
                paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
                if ("PHOTOGRAPHY_FIGURE".equals(CommonUtil.getLibCode()) ||
                    "MATERIAL_FIGURE".equals(CommonUtil.getLibCode())) {
                    String groupFlag = (String)this.resolveExpression("#{pageFlowScope.groupFlag}");
                    if ("Y".equals(groupFlag)) {
                        Number groupId = (Number)this.resolveExpression("#{pageFlowScope.groupId}");
                        paramMap.put("bvGroupDetailFlag", "Y");
                        paramMap.put("bvGroupId", groupId);
                    } else {
                        paramMap.put("bvGroupDetailFlag", "N");
                        paramMap.put("bvGroupId", null);
                    }
                }
                tableModel.setParamMap(paramMap);
                tableModel.getModel();
                //            CommonUtil.getWorkAM().getDefaultStatusCode(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                //                                                        (Number)this.resolveExpression("#{pageFlowScope.categoryId}"),
                //                                                        (String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"),
                //                                                        (String)this.resolveExpression("#{pageFlowScope.deleteFlag}"));
            }
        } else {
            Map<String, Object> additionalParamMap =
                (Map<String, Object>)this.resolveExpression("#{pageFlowScope.additionalParamMap}");
            System.out.println("additionalParamMap:" + additionalParamMap);
            if (additionalParamMap != null && additionalParamMap.size() > 0) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
                paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
                paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
                paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
                CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "N");
                paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
                paramMap.put("QueryParam", additionalParamMap);
                tableModel.setParamMap(paramMap);
                tableModel.getModel();
            } else {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                if (!"Y".equals(this.resolveExpression("#{pageFlowScope.deleteFlag}"))) {
                    paramMap.put("bvStatus", statusCode);
                } else {
                    paramMap.put("bvStatus", null);
                }
                if ("APPLY".equals(CommonUtil.getLibCode())) {
                    paramMap.put("bvUserId", CommonUtil.getUserId());
                }
                paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
                paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
                paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
                paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
                if ("PHOTOGRAPHY_FIGURE".equals(CommonUtil.getLibCode()) ||
                    "MATERIAL_FIGURE".equals(CommonUtil.getLibCode())) {
                    String groupFlag = (String)this.resolveExpression("#{pageFlowScope.groupFlag}");
                    if ("Y".equals(groupFlag)) {
                        Number groupId = (Number)this.resolveExpression("#{pageFlowScope.groupId}");
                        paramMap.put("bvGroupDetailFlag", "Y");
                        paramMap.put("bvGroupId", groupId);
                    } else {
                        paramMap.put("bvGroupDetailFlag", "N");
                        paramMap.put("bvGroupId", null);
                    }
                }
                tableModel.setParamMap(paramMap);
                tableModel.getModel();
            }
        }
    }


    /**
     * 切换状态
     * @param actionEvent
     */
    public void switchStatusActionListener(ActionEvent actionEvent) {
        String statusCode = (String)actionEvent.getComponent().getAttributes().get("statusCode");
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setSelected(false);
        }
        statusValueChange(statusCode);
        if (this.getTable() != null) {
            this.refreshUIComponent(this.getTable());
        }
    }

    /**
     * 文档状态切换事件
     * @param valueChangeEvent
     */
    public void statusValueChangeListener(ValueChangeEvent valueChangeEvent) {
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setSelected(false);
        }
        statusValueChange((String)valueChangeEvent.getNewValue());
        if (this.getCoverPanelGroupLayout() != null) {
            this.refreshUIComponent(this.getCoverPanelGroupLayout());
        }
        if (this.getTable() != null) {
            this.refreshUIComponent(this.getTable());
        }
    }

    /**
     * 获取文档状态列表:待采集、待标引、待审批、已发布
     * @return
     */
    public List<SelectItem> getStatusList() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        CmsDocStatusVOImpl cmsDocStatusVO = workAm.getCmsDocStatusVO();
        cmsDocStatusVO.setbvLibCode(CommonUtil.getLibCode());
        cmsDocStatusVO.setbvCompCode(CommonUtil.getCompCode());
        cmsDocStatusVO.setbvCategoryId((Number)this.resolveExpression("#{pageFlowScope.categoryId}"));
        cmsDocStatusVO.setbvCategoryGroupType((String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        cmsDocStatusVO.setbvDeleteFlag(CommonUtil.getDeleteFlag());
        cmsDocStatusVO.setbvUserId(CommonUtil.getUserId());

        cmsDocStatusVO.executeQuery();
        cmsDocStatusVO.setRangeSize(-1);
        for (Row row : cmsDocStatusVO.getAllRowsInRange()) {
            CmsDocStatusVORowImpl ri = (CmsDocStatusVORowImpl)row;
            SelectItem item = new SelectItem(ri.getStatusCode(), ri.getStatusDisplayName());
            items.add(item);
        }
        return items;
    }

    public List<Map<String, String>> getStatusMapList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        CmsDocStatusVOImpl cmsDocStatusVO = workAm.getCmsDocStatusVO();
        cmsDocStatusVO.setbvLibCode(CommonUtil.getLibCode());
        cmsDocStatusVO.setbvCompCode(CommonUtil.getCompCode());
        cmsDocStatusVO.setbvCategoryId((Number)this.resolveExpression("#{pageFlowScope.categoryId}"));
        cmsDocStatusVO.setbvCategoryGroupType((String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        cmsDocStatusVO.setbvDeleteFlag(CommonUtil.getDeleteFlag());
        cmsDocStatusVO.setbvUserId(CommonUtil.getUserId());
        cmsDocStatusVO.executeQuery();
        cmsDocStatusVO.setRangeSize(-1);
        for (Row row : cmsDocStatusVO.getAllRowsInRange()) {
            CmsDocStatusVORowImpl ri = (CmsDocStatusVORowImpl)row;
            Map<String, String> map = new HashMap<String, String>();
            map.put("statusCode", ri.getStatusCode());
            map.put("displayName", ri.getStatusDisplayName());
            list.add(map);
        }
        return list;
    }


    /**
     * 重置记录选中的Session
     */
    public void resetStatusSession() {
        CmsDocStatusVOImpl cmsDocStatusVO = workAm.getCmsDocStatusVO();
        cmsDocStatusVO.setbvLibCode(CommonUtil.getLibCode());
        cmsDocStatusVO.setbvCompCode(CommonUtil.getCompCode());
        cmsDocStatusVO.setbvCategoryId((Number)this.resolveExpression("#{pageFlowScope.categoryId}"));
        cmsDocStatusVO.setbvCategoryGroupType((String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        cmsDocStatusVO.setbvDeleteFlag(CommonUtil.getDeleteFlag());
        cmsDocStatusVO.setbvUserId(CommonUtil.getUserId());
        cmsDocStatusVO.executeQuery();
        cmsDocStatusVO.setRangeSize(-1);
        for (Row row : cmsDocStatusVO.getAllRowsInRange()) {
            CmsDocStatusVORowImpl ri = (CmsDocStatusVORowImpl)row;
            CommonUtil.putStringToSession(ri.getStatusCode() + "_selectAllFlag", "N");
            CommonUtil.putObjectToSession(ri.getStatusCode() + "_currSelected", new ArrayList<String>());
        }
        CommonUtil.putStringToSession("ORIGIN_selectAllFlag", "N");
        CommonUtil.putObjectToSession("ORIGIN_currSelected", new ArrayList<String>());
    }

    /**
     * 获取文档的展示方式列表：封面列表或表格列表
     * @return
     */
    public List<SelectItem> getDocDisplayWayList() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        CmsDocDisplayWayVOImpl displayWayVO = workAm.getCmsDocDisplayWayVO();
        displayWayVO.setbvLibCode(CommonUtil.getLibCode());
        displayWayVO.executeQuery();
        displayWayVO.setRangeSize(-1);
        for (Row row : displayWayVO.getAllRowsInRange()) {
            CmsDocDisplayWayVORowImpl ri = (CmsDocDisplayWayVORowImpl)row;
            SelectItem item = new SelectItem(ri.getStatusCode(), ri.getStatusName());
            items.add(item);
        }
        return items;
    }

    public List<Map<String, String>> getDocDisplayWayMapList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        CmsDocDisplayWayVOImpl displayWayVO = workAm.getCmsDocDisplayWayVO();
        displayWayVO.setbvLibCode(CommonUtil.getLibCode());
        displayWayVO.executeQuery();
        displayWayVO.setRangeSize(-1);
        for (Row row : displayWayVO.getAllRowsInRange()) {
            CmsDocDisplayWayVORowImpl ri = (CmsDocDisplayWayVORowImpl)row;
            Map<String, String> map = new HashMap<String, String>();
            map.put("statusCode", ri.getStatusCode());
            map.put("statusName", ri.getStatusName());
            list.add(map);
        }
        return list;
    }

    /**
     * 文档展示方式切换事件
     * @param valueChangeEvent
     */
    public void displayWayValueChangeListener(ValueChangeEvent valueChangeEvent) {
        String displayWay = (String)valueChangeEvent.getNewValue();
        this.setExpressionValue("#{pageFlowScope.defaultDisplayWay}", displayWay);
    }

    public void switchDisplayWayActionListenter(ActionEvent actionEvent) {
        String displayWay = (String)actionEvent.getComponent().getAttributes().get("statusCode");
        this.setExpressionValue("#{pageFlowScope.defaultDisplayWay}", displayWay);
    }

    public void createOrEditReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            this.reFindCategory();
            this.appendScript("refreshCategory()");
            this.showMessage("保存成功");
        }
    }

    private boolean isHaveAuth(String res) {
        String auth = resAuthMap.get(res);
        if (auth != null && "1".equals(auth)) {
            return true;
        }
        return false;
    }

    public boolean isCreateEnable() {
        return isHaveAuth("CREATE");
    }

    public boolean isIndexEnable() {
        return isHaveAuth("INDEX");
    }

    public boolean isScriptEnable() {
        return isHaveAuth("SCRIPT");
    }

    public boolean isAttachmentMgmtEnable() {
        return isHaveAuth("ATTACHMENT_MGMT");
    }

    public boolean isCreateGroupEnable() {
        return isHaveAuth("CREATE_GROUP");
    }

    public boolean isAddToGroupEnable() {
        return isHaveAuth("ADD_TO_GROUP");
    }

    public boolean isSplitGroupEnable() {
        return isHaveAuth("SPLIT_GROUP");
    }

    public boolean isChangeImgEnable() {
        return isHaveAuth("CHANGE_IMG");
    }

    public boolean isSubmitEnable() {
        return isHaveAuth("SUBMIT");
    }

    public boolean isApproveEnable() {
        return isHaveAuth("APPROVE");
    }

    public boolean isPubEnable() {
        return isHaveAuth("PUB");
    }

    public boolean isDeleteEnable() {
        return isHaveAuth("DELETE");
    }

    public boolean isRecoverEnable() {
        return isHaveAuth("RECOVER");
    }

    public boolean isDestroyEnable() {
        return isHaveAuth("DESTROY");
    }

    public boolean isBackEnable() {
        return isHaveAuth("BACK");
    }

    public boolean isUndoPubEnable() {
        return isHaveAuth("UNDO_PUB");
    }

    public boolean isFileMgmtEnable() {
        return isHaveAuth("FILE_MGMT");
    }

    public boolean isBatchIndexEnable() {
        return isHaveAuth("BATCH_INDEX");
    }

    public boolean isSyncEnable() {
        return isHaveAuth("SYNC");
    }

    public boolean isBatchFgmtEnable() {
        return isHaveAuth("BATCH_FMGT");
    }

    public boolean isBatchSyncEnable() {
        return isHaveAuth("BATCH_SYNC");
    }

    public boolean isBatchSyncLogEnable() {
        return isHaveAuth("BATCH_SYNC_LOG");
    }

    public boolean isJingBianEnable() {
        return isHaveAuth("JINGBIAN");
    }

    public boolean isXuanChuanEnable() {
        return isHaveAuth("XUANCHUAN");
    }

    public boolean isRelationEnable() {
        return isHaveAuth("RELATION");
    }

    public boolean isDetailMgmtEnable() {
        return isHaveAuth("DETAIL_MGMT");
    }

    public boolean isScanChapterEnable() {
        return isHaveAuth("SCAN_CHAPTER");
    }

    public boolean isTeachingPlanEnable() {
        return isHaveAuth("CREATE_TEACHINGPLAN");
    }

    public boolean isCoursewareEnable() {
        return isHaveAuth("CREATE_COURSEWARE");
    }

    public boolean isDeleteChapterEnable() {
        return isHaveAuth("DELETE_CHAPTER");
    }

    public boolean isCatalogSortEnable() {
        return isHaveAuth("CATALOG_SORT");
    }

    public boolean isImportCateInfoEnable() {
        return isHaveAuth("IMPORT_CATA_INFO");
    }

    public boolean isImpEnable() {
        return isHaveAuth("IMP");
    }

    public boolean isDownloadEnable() {
        return isHaveAuth("DOWNLOAD");
    }

    public boolean isAddToTempShelfEnable() {
        return isHaveAuth("ADD_TO_TEMP_SHELF");
    }

    public boolean isEntryDownloadEnable() {
        return isHaveAuth("ENTRY_DOWNLOAD");
    }

    public boolean isMarkSystemDownloadEnable() {
        return isHaveAuth("MARKSYSTEM_DOWNLOAD");
    }

    public boolean isCommonDeleteEnable() {
        return isHaveAuth("COMMON_DELETE");
    }

    public boolean isCommonIndexEnable() {
        return isHaveAuth("COMMON_INDEX");
    }

    public boolean isImportCostDetailEnable() {
        return isHaveAuth("IMPORT_COST_DETAIL");
    }

    public boolean isImportCostTemplateEnable() {
        return isHaveAuth("IMPORT_COST_TEMPLATE");
    }

    public boolean isThemeMakeEnable() {
        return isHaveAuth("THEME_MAKE");
    }

    public boolean isThemeExportEnable() {
        return isHaveAuth("THEME_EXPORT");
    }

    public void updateDocStatusActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        List<Number> list = new ArrayList<Number>();
        this.setExpressionValue("#{pageFlowScope.currStatusCode}",
                                workAm.findNextStatusCode((String)this.resolveExpression("#{pageFlowScope.currStatusCode}")));
        try {
            if (currSelectedList != null) {
                for (String key : currSelectedList) {
                    Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                    list.add(new Number(docId));
//                    ResourcePushThread push = new ResourcePushThread(new Number(docId));
//                    new Thread(push).start();
                }
                String operation = (String)actionEvent.getComponent().getAttributes().get("operation");
                //更新状态 并log
                CommonUtil.getCommonAM().updateDocStatusLog(list, CommonUtil.getCurrStatusCode(), operation,
                                                            CommonUtil.getLibCode());
                statusValueChange(CommonUtil.getCurrStatusCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 回退文档
     * @param list
     */
    public void backDocStatus(List<Number> list) {
        this.setExpressionValue("#{pageFlowScope.currStatusCode}",
                                workAm.findPreStatusCode((String)this.resolveExpression("#{pageFlowScope.currStatusCode}")));
        CommonUtil.getCommonAM().updateDocStatus(list, CommonUtil.getCurrStatusCode());
        statusValueChange(CommonUtil.getCurrStatusCode());
        CommonUtil.getLogAM().getTransaction().commit();
    }

    public String getWebInfPath() throws IllegalAccessException {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(1, path.indexOf("WEB-INF"));
        } else {
            throw new IllegalAccessException("路径获取错误");
        }
        return path;
    }

    //    public void fileManageActionListener(ActionEvent actionEvent) {
    //        CommonUtil.initFtpConn();
    //        FolderManager fm = new FolderManager();
    //        List<String> currSelectedList =
    //            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
    //        if (currSelectedList == null) {
    //            return;
    //        }
    //        this.setExpressionValue("#{pageFlowScope.currStatusCode}",
    //                                workAm.findSpecificStatusCode(CommonUtil.getLibCode()));
    //        long startTime = System.currentTimeMillis();
    //        System.out.println("执行到获取下一状态完成耗时-------->" + (System.currentTimeMillis() - startTime));
    //        List<Number> list = new ArrayList<Number>();
    //        String libCode = CommonUtil.getLibCode();
    //        String libName = CommonUtil.getLibName();
    //        try {
    //            for (String key : currSelectedList) {
    //                Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
    //                CommonUtil.getCommonAM().fileManage(docId, null);
    //                list.add(docId);
    //                //参数 用户登录名 出版社名(中文) LibCode “具体库名（中文）”
    //                List<String> folders = new ArrayList<String>();
    //                //根据lookup表获取图书库的所有文件结构
    //                String directorys = CommonUtil.getCommonAM().getFileManageDirectory(docId);
    //                if (directorys == null || directorys.length() == 0) {
    //                    continue;
    //                }
    //                for (String directory : directorys.split(",")) {
    //                    if (directory != null && directory.length() > 0) {
    //                        folders.add(directory);
    //                    }
    //                }
    //                System.out.println("执行到获取目录结构耗时-------->" + (System.currentTimeMillis() - startTime));
    //                Folder rootFolder =
    //                    FolderFactory.createFolderByList(CommonUtil.getCompName(), libName, CommonUtil.getDocNameByDocId(docId),
    //                                                     folders);
    //                fm.mkAllDir(rootFolder);
    //                System.out.println("执行到生成FTP目录结构耗时-------->" + (System.currentTimeMillis() - startTime));
    //                //生产占位符
    //                List<Row> ucmFileList = this.getWorkAm().getUCMFileByDocId(docId);
    //                if (ucmFileList != null && ucmFileList.size() > 0) {
    //                    for (Row fileRow : ucmFileList) {
    //                        CmsDocUcmRelVORowImpl file = (CmsDocUcmRelVORowImpl)fileRow;
    //                        String ftpRelativePath = file.getFtpPath();
    //                        String ftpPath =
    //                            "/" + CommonUtil.getCompName() + "/" + libName + "/" + CommonUtil.getDocNameByDocId(docId) +
    //                            "/" + ftpRelativePath;
    //                        String fileName = file.getFileName();
    //                        Number dId = file.getUcmDid();
    //                        String ucmDocName = file.getUcmDocname();
    //                        String placeholderFlag = "FEILIUZHIXIASANQIANCHIPLACEHOLDER";
    //                        String placeholderStr =
    //                            placeholderFlag + "/docId---->" + docId + "#@#" + "fileName" + "---->" + fileName + "#@#" +
    //                            "filePath" + "---->" + ftpPath + "#@#" + "ucmDocId" + "---->" + dId.toString() + "#@#" +
    //                            "ucmDocName" + "---->" + ucmDocName;
    //                        ByteArrayInputStream in = null;
    //                        try {
    //                            in = new ByteArrayInputStream(placeholderStr.getBytes("UTF-8"));
    //                            fm.getConn().sendFtpCommand(FtpConnection.CWD + " " + ftpPath);
    //                            fm.getConn().updatePWD();
    //                            fm.getConn().upload(fileName, fileName, in);
    //                        } catch (UnsupportedEncodingException e) {
    //                            e.printStackTrace();
    //                        } finally {
    //                            if (in != null) {
    //                                in.close();
    //                            }
    //                        }
    //                    }
    //                    fm.getConn().sendFtpCommand(FtpConnection.CWD + " /");
    //                    fm.getConn().updatePWD();
    //                }
    //            }
    //            System.out.println("执行到FTP文件全部就绪耗时-------->" + (System.currentTimeMillis() - startTime));
    //            String operation = (String)actionEvent.getComponent().getAttributes().get("operation");
    //            CommonUtil.getCommonAM().updateDocStatusLog(list, CommonUtil.getCurrStatusCode(), operation, libCode);
    //            statusValueChange(CommonUtil.getCurrStatusCode());
    //            System.out.println("执行到更改状态耗时-------->" + (System.currentTimeMillis() - startTime));
    //            this.showLongMessage("创建目录成功");
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

    public void fileManageActionListener(ActionEvent actionEvent) {
        System.out.println("fileManageActionListener-->从待采集状态点击文件管理...");
        String operation = (String)actionEvent.getComponent().getAttributes().get("operation");
        String currStatus = CommonUtil.getCurrStatusCode();
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(currStatus + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        this.setExpressionValue("#{pageFlowScope.currStatusCode}",
                                workAm.findSpecificStatusCode(CommonUtil.getLibCode()));
        List<Number> list = new ArrayList<Number>();
        for (String key : currSelectedList) {
            Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
            list.add(docId);
            CommonUtil.getCommonAM().fileManage(docId, null);
            //            if (currStatus.contains(Constants.GATHER_STATUS_PREFIX)) {
            //                CommonUtil.getCommonAM().fileManage(docId, null);
            //            } else {
            //                CommonUtil.getCommonAM().fileManage(docId, "FILE_MANAGING");
            //            }
        }
        CommonUtil.getCommonAM().updateDocStatusLog(list, CommonUtil.getCurrStatusCode(), operation,
                                                    CommonUtil.getLibCode());
        statusValueChange(CommonUtil.getCurrStatusCode());
        FileManageThread fileManageThread =
            new FileManageThread(currSelectedList, CommonUtil.getCompName(), CommonUtil.getLibName(), currStatus);
        new Thread(fileManageThread).start();
    }

    public void reFileManageAction() {
        System.out.println("reFileManageAction-->从标引状态点击文件管理...");
        String currStatus = CommonUtil.getCurrStatusCode();
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(currStatus + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        this.setExpressionValue("#{pageFlowScope.currStatusCode}",
                                workAm.findSpecificStatusCode(CommonUtil.getLibCode()));
        List<Number> list = new ArrayList<Number>();
        for (String key : currSelectedList) {
            Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
            list.add(docId);
            CommonUtil.getCommonAM().fileManage(docId, "FILE_MANAGING");
        }
        CommonUtil.getCommonAM().updateDocStatusLog(list, CommonUtil.getCurrStatusCode(), "文件管理",
                                                    CommonUtil.getLibCode());
        statusValueChange(CommonUtil.getCurrStatusCode());
        FileManageThread fileManageThread =
            new FileManageThread(currSelectedList, CommonUtil.getCompName(), CommonUtil.getLibName(), currStatus);
        new Thread(fileManageThread).start();
    }

    public String getFilePath(FtpFile file, String path) {
        String filePath = file.getFilePath().replace(path, "");
        return filePath.substring(1, filePath.length() - 1);
    }

    //    public void syncToUCMActionListener(ActionEvent actionEvent) {
    //        String libCode = CommonUtil.getLibCode();
    //        List<String> currSelectedList =
    //            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
    //        List<Number> list = new ArrayList<Number>();
    //        try {
    //            CommonUtil.initFtpConn();
    //            FolderManager fm = new FolderManager();
    //            LoadBatchUtil util = new LoadBatchUtil();
    //            StringBuffer sb = new StringBuffer();
    //            StringBuffer syncInfo = new StringBuffer();
    //            for (String key : currSelectedList) {
    //                Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
    //                String docName = CommonUtil.getDocNameByDocId(docId, CommonUtil.getLibCode());
    //                String syncResult = CommonUtil.getCommonAM().getSyncFlag(docId);
    //                if ("SYNCING".equals(syncResult)) {
    //                    syncInfo.append(docName + "正在同步中...\n");
    //                    continue;
    //                } else if ("SUCCESS".equals(syncResult)) {
    //                    syncInfo.append(docName + "已经同步完成...\n");
    //                    continue;
    //                }
    //                String path = "/" + CommonUtil.getCompName() + "/" + CommonUtil.getLibName() + "/" + docName;
    //                if (!fm.exists(path)) {
    //                    syncInfo.append(docName + "在FTP上不存在对应的目录,请先上传对应的目录结构...\n");
    //                    continue;
    //                }
    //                list.add(docId);
    //                sb.append(docId + ",");
    //                List<FtpFile> files = fm.getFtpFiles(path);
    //                Collections.sort(files);
    //                //获取已经存在的文件
    //                List<Row> ucmFileList = this.getWorkAm().getUCMFileByDocId(docId);
    //                //删除上次的同步监控日志
    //                CommonUtil.getCommonAM().deleteGatherMonitorRecord(docId);
    //                int syncFileCount = 0;
    //                if (ucmFileList.size() == 0) {
    //                    for (FtpFile file : files) {
    //                        if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
    //                            String docType = getDocType(libCode, file, path);
    //                            Map<String, String> map =
    //                                UCMUtil.checkinFileToUCM(file, docId, null, null, docType, path, CommonUtil.getCompCode());
    //                            util.addData(map);
    //                            CommonUtil.getCommonAM().processSyncLog(docId, file.getFileName(), getFilePath(file, path),
    //                                                                    null, CommonUtil.getCompCode(),
    //                                                                    CommonUtil.getLibCode(), "新增");
    //                            syncFileCount++;
    //                        }
    //                    }
    //                } else {
    //                    List<Row> deleteList = new ArrayList<Row>();
    //                    for (Row row : ucmFileList) {
    //                        boolean existFlag = false;
    //                        String ucmPath =
    //                            (String)row.getAttribute("FtpPath") + "/" + (String)row.getAttribute("FileName");
    //                        for (FtpFile file : files) {
    //                            if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
    //                                if (ucmPath.equals(getFilePath(file, path) + "/" + file.getFileName())) {
    //                                    existFlag = true;
    //                                    break;
    //                                }
    //                            }
    //                        }
    //                        if (!existFlag) {
    //                            Number ucmDid = (Number)row.getAttribute("UcmDid");
    //                            Map<String, String> map =
    //                                UCMUtil.deleteFromUCM(ucmDid.toString(), (String)row.getAttribute("UcmDocname"));
    //                            util.addData(map);
    //                            CommonUtil.getCommonAM().processSyncLog(docId, (String)row.getAttribute("FileName"),
    //                                                                    (String)row.getAttribute("FtpPath"), null,
    //                                                                    CommonUtil.getCompCode(), CommonUtil.getLibCode(),
    //                                                                    "删除");
    //                            syncFileCount++;
    //                            deleteList.add(row);
    //                        }
    //                    }
    //                    for (Row row : deleteList) {
    //                        ucmFileList.remove(row);
    //                    }
    //                    for (FtpFile file : files) {
    //                        if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
    //                            String pathName = getFilePath(file, path) + file.getFileName();
    //                            boolean existFlag = false;
    //                            String uploadDate = null;
    //                            Number ucmDid = null;
    //                            String ucmDocname = null;
    //                            Number refDocId = null;
    //                            boolean b = PlaceHolderUtil.isPlaceHolder(fm, file);
    //                            if (b) {
    //                                continue;
    //                            }
    //                            for (Row row : ucmFileList) {
    //                                String pathName1 =
    //                                    (String)row.getAttribute("FtpPath") + (String)row.getAttribute("FileName");
    //                                if (pathName.equals(pathName1)) {
    //                                    existFlag = true;
    //                                    uploadDate = (String)row.getAttribute("FtpUploadDate");
    //                                    ucmDid = (Number)row.getAttribute("UcmDid");
    //                                    ucmDocname = (String)row.getAttribute("UcmDocname");
    //                                    refDocId = (Number)row.getAttribute("RefDocId");
    //                                    break;
    //                                }
    //                            }
    //                            if (existFlag) {
    //                                if (!file.getLastUploadDate().equals(uploadDate)) {
    //                                    Map<String, String> map1 = UCMUtil.checkoutFromUCM(ucmDid.toString());
    //                                    util.addData(map1);
    //                                    String docType = getDocType(libCode, file, path);
    //                                    Map<String, String> map2 =
    //                                        UCMUtil.checkinFileToUCM(file, docId, ucmDocname, refDocId, docType, path,
    //                                                                 CommonUtil.getCompCode());
    //                                    util.addData(map2);
    //                                    CommonUtil.getCommonAM().processSyncLog(docId, file.getFileName(),
    //                                                                            getFilePath(file, path), null,
    //                                                                            CommonUtil.getCompCode(),
    //                                                                            CommonUtil.getLibCode(), "更新");
    //                                    syncFileCount++;
    //                                }
    //                            } else {
    //                                String docType = getDocType(libCode, file, path);
    //                                Map<String, String> map =
    //                                    UCMUtil.checkinFileToUCM(file, docId, null, refDocId, docType, path,
    //                                                             CommonUtil.getCompCode());
    //                                util.addData(map);
    //                                CommonUtil.getCommonAM().processSyncLog(docId, file.getFileName(),
    //                                                                        getFilePath(file, path), null,
    //                                                                        CommonUtil.getCompCode(),
    //                                                                        CommonUtil.getLibCode(), "新增");
    //                                syncFileCount++;
    //                            }
    //                        }
    //                    }
    //                }
    //                if (syncFileCount == 0) {
    //                    CommonUtil.getCommonAM().endSync(docId.toString());
    //                }
    //            }
    //            if (syncInfo != null && syncInfo.length() > 0) {
    //                this.addFormattedMessage(null, syncInfo.toString(), FacesMessage.SEVERITY_WARN);
    //            } else {
    //                this.showLongMessage("同步开始进行");
    //            }
    //            String transData = util.getSplitTransData(Constants.SYNC_PER_BATCH_COUNT);
    //            UCMUtil.invokeWebService(new Object[] { transData + "###" + sb.toString() + "###" +
    //                                                    CommonUtil.getUserId() }, "doBatchLoadWithParam",
    //                                     Constants.UCM_SYNC_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

    public void syncToUCMActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        List<String> syncList = new ArrayList<String>();
        try {
            CommonUtil.initFtpConn();
            FolderManager fm = new FolderManager();
            StringBuffer syncInfo = new StringBuffer();
            for (String key : currSelectedList) {
                Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
                String docName = CommonUtil.getDocNameByDocId(docId);
                String syncResult = CommonUtil.getCommonAM().getSyncFlag(docId);
                String path = "/" + CommonUtil.getCompName() + "/" + CommonUtil.getLibName() + "/" + docName;
                if ("SYNCING".equals(syncResult)) {
                    syncInfo.append(docName + "正在同步中...\n");
                    continue;
                } else if ("SUCCESS".equals(syncResult)) {
                    syncInfo.append(docName + "已经同步完成...\n");
                    continue;
                } else if ("FILE_MANAGING".equals(syncResult)) {
                    syncInfo.append(docName + "正在从UCM上下载文件...\n");
                    continue;
                } else if (!fm.exists(path)) {
                    syncInfo.append(docName + "在FTP上不存在对应的目录,请先上传对应的目录结构...\n");
                    continue;
                } else {
                    syncList.add(key);
                }
            }
            if (syncInfo != null && syncInfo.length() > 0) {
                this.addFormattedMessage(null, syncInfo.toString(), FacesMessage.SEVERITY_WARN);
            } else {
                this.showLongMessage("同步开始进行");
            }
            if (syncList != null && syncList.size() > 0) {
                SyncThread syncThread =
                    new SyncThread(syncList, CommonUtil.getCompCode(), CommonUtil.getCompName(), CommonUtil.getLibCode(),
                                   CommonUtil.getLibName(), CommonUtil.getUserId(), CommonUtil.getUsername());
                new Thread(syncThread).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void batchFileManageActionListener(ActionEvent actionEvent) {
        CommonUtil.initFtpConn();
        FolderManager fm = new FolderManager();
        String nodePaths =
            CommonUtil.getWorkAM().getCategoryTreeNodePath(CommonUtil.getCompCode(), CommonUtil.getLibCode());
        String[] nodePathArray = nodePaths.split(",");
        Folder rootFolder =
            FolderFactory.createFolderByList(CommonUtil.getCompName(), CommonUtil.getLibName(), CommonUtil.getUsername().toUpperCase(),
                                             Arrays.asList(nodePathArray));
        fm.mkAllDir(rootFolder);
        this.showMessage("创建目录成功");
    }

    public void batchSyncActionListener(ActionEvent actionEvent) {
        CommonUtil.initFtpConn();
        FolderManager fm = new FolderManager();
        LoadBatchUtil util = new LoadBatchUtil();
        String path =
            "/" + CommonUtil.getCompName() + "/" + CommonUtil.getLibName() + "/" + CommonUtil.getUsername().toUpperCase();
        List<FtpFile> files = fm.getFtpFiles(path);
        if (files == null || files.size() == 0) {
            this.addFormattedMessage(null, "没有可同步的资源，请先上传资源。", FacesMessage.SEVERITY_WARN);
            return;
        }
        Collections.sort(files);
        CommonUtil.getCommonAM().clearSyncLog(CommonUtil.getCompCode(), CommonUtil.getLibCode());
        for (FtpFile file : files) {
            if (!ArrayUtils.contains(Constants.ftpExcludeFiles, file.getFileName().toUpperCase())) {
                if (CommonUtil.isValidFormatWhenBatchSync(CommonUtil.getLibCode(), file.getFileFormat())) {
                    CommonUtil.getCommonAM().processSyncLog(new Number(-99), file.getFileName(), file.getFilePath(),
                                                            CommonUtil.getUsername(), CommonUtil.getCompCode(),
                                                            CommonUtil.getLibCode(), "新增", null, null, null, "1");
                    Map<String, String> map =
                        UCMUtil.batchCheckinFileToUCM(file, null, null, null, CommonUtil.getLibCode(),
                                                      CommonUtil.getCompCode(), CommonUtil.getUsername());
                    util.addData(map);
                } else {
                    CommonUtil.getCommonAM().processSyncLog(new Number(-99), file.getFileName(), file.getFilePath(),
                                                            CommonUtil.getUsername(), CommonUtil.getCompCode(),
                                                            CommonUtil.getLibCode(), "新增", "FAIL", null, "不支持该格式的文件",
                                                            "1");
                    continue;
                }
            }
        }
        String transData = util.getSplitTransData(Constants.SYNC_PER_BATCH_COUNT);
        String path1 =
            CommonUtil.getCompCode() + "/" + CommonUtil.getLibCode() + "/" + CommonUtil.getUsername().toUpperCase();
        UCMUtil.invokeWebService(new Object[] { transData + "###" + path1 + "###" + CommonUtil.getUserId() },
                                 "doBatchLoadWithParam", Constants.UCM_SYNC_WS_WSDL, Constants.UCM_SYNC_WS_NAMESPACE);

        this.addFormattedMessage(null, "正在同步，请耐心等候。同步结束后，请点击刷新按钮刷新页面。同步情况可以查看批量同步日志。", FacesMessage.SEVERITY_INFO);
    }

    public void deleteDocActionListener(ActionEvent actionEvent) {
        List<Number> list = new ArrayList<Number>();
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList == null) {
                return;
            }
            for (String key : currSelectedList) {
                Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                list.add(new Number(docId));
            }
            CommonUtil.getCommonAM().deleteAndRecoverDoc(list, "Y");
            statusValueChange(CommonUtil.getCurrStatusCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDocDialogActionListener(DialogEvent dialogEvent) {
        if ("ok".equalsIgnoreCase(dialogEvent.getOutcome().toString())) {
            List<Number> list = new ArrayList<Number>();
            try {
                List<String> currSelectedList =
                    (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
                if (currSelectedList == null) {
                    return;
                }
                for (String key : currSelectedList) {
                    Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                    list.add(new Number(docId));
                }
                CommonUtil.getCommonAM().deleteAndRecoverDoc(list, "Y");
                statusValueChange(CommonUtil.getCurrStatusCode());
                if (this.getCoverPanelGroupLayout() != null) {
                    this.refreshUIComponent(this.getCoverPanelGroupLayout());
                }
                if (this.getTable() != null) {
                    this.refreshUIComponent(this.getTable());
                }
                this.reFindCategory();
                this.appendScript("refreshCategory()");
            } catch (SQLException e) {
                this.showMessage("删除失败！");
            }
            this.showMessage("删除成功！");
        }
    }

    public void destroyDocActionListener(DialogEvent dialogEvent) {
        if ("ok".equalsIgnoreCase(dialogEvent.getOutcome().toString())) {
            List<Number> list = new ArrayList<Number>();
            try {
                List<String> currSelectedList =
                    (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
                if (currSelectedList == null) {
                    return;
                }
                for (String key : currSelectedList) {
                    Number docId = new Number(key.substring(key.lastIndexOf("_") + 1));
                    //删除UCM上的文件
                    try {
                        List<Row> ucmFileRows = this.getWorkAm().getUCMFileByDocId(docId);
                        for (Row row : ucmFileRows) {
                            CmsDocUcmRelVORowImpl ucmFileRow = (CmsDocUcmRelVORowImpl)row;
                            DocService.getInstance().deleteDocByDID(ucmFileRow.getUcmDid().toString());
                        }
                    } catch (Exception e) {
                        System.out.println("UCM上的文件未删除...");
                        e.printStackTrace();
                    }
                    String docName = CommonUtil.getDocNameByDocId(docId);
                    String ftpPath = CommonUtil.getCompName() + "/" + CommonUtil.getLibName() + "/" + docName;
                    CommonUtil.removeFtpDirs(ftpPath);
                    list.add(docId);
                }
                workAm.destroyDoc(list);
                this.reFindCategory();
                statusValueChange(CommonUtil.getCurrStatusCode());
                if (this.getCoverPanelGroupLayout() != null) {
                    this.refreshUIComponent(this.getCoverPanelGroupLayout());
                }
                if (this.getTable() != null) {
                    this.refreshUIComponent(this.getTable());
                }
            } catch (Exception e) {
                this.showMessage("删除失败！");
            }
            this.showMessage("删除成功！");
        }
    }

    public void recoverDocActionListener(ActionEvent actionEvent) {
        List<Number> list = new ArrayList<Number>();
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList == null) {
                return;
            }
            for (String key : currSelectedList) {
                Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                list.add(new Number(docId));
            }
            CommonUtil.getCommonAM().deleteAndRecoverDoc(list, "N");
            statusValueChange(CommonUtil.getCurrStatusCode());
            this.reFindCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void docIdSetActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (null != currSelectedList && currSelectedList.size() > 0) {
                String key = currSelectedList.get(0);
                Integer docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
                this.setExpressionValue("#{pageFlowScope.docId}", new Number(docId));
            } else {
                this.setExpressionValue("#{pageFlowScope.docId}", new Number(-99));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void docIdTypeSetActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (null != currSelectedList && currSelectedList.size() > 0) {
                String key = currSelectedList.get(0);
                Integer docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
                String type = (String)actionEvent.getComponent().getAttributes().get("type");
                System.out.println("type=" + type);
                if (null != type) {
                    this.setExpressionValue("#{pageFlowScope.type}", type);
                }
                this.setExpressionValue("#{pageFlowScope.docId}", new Number(docId));
            } else {
                this.setExpressionValue("#{pageFlowScope.docId}", new Number(-99));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchActionListenser(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.searchMode}", "SIMPLE");
        String searchValue = (String)this.getSearchInput().getValue();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvSearchInput", searchValue);
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        this.setExpressionValue("#{pageFlowScope.searchInput}", searchValue);
        System.out.println("查询时参数:" + paramMap);
        tableModel.setParamMap(paramMap);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "N");
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            currSelectedList.clear();
        }
        //搜索后刷新DocStatus对应数量
        tableModel.getModel();
        tableModel.getViewObject().setRangeSize(-1);
        String docIdStr = tableModel.getAttributeStr("DocId");
        if (docIdStr == null) {
            //查询为空 与无输入值
            docIdStr = "-1";
        }
        if (searchValue == null || searchValue.length() == 0) {
            docIdStr = null;
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            tableModel.setParamMap(paramMap);
        }
        String searchResultStatus =
            CommonUtil.getWorkAM().getSearchStatusCode(CommonUtil.getLibCode(), (Number)this.resolveExpression("#{pageFlowScope.categoryId}"),
                                                       (String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"),
                                                       (String)this.resolveExpression("#{pageFlowScope.deleteFlag}"),
                                                       docIdStr, CommonUtil.getCompCode());
        if (searchValue != null && searchValue.length() > 0) {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", searchResultStatus);
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            tableModel.setParamMap(paramMap);
        }
    }

    public void queryWhenPressEnterListener(ClientEvent clientEvent) {
        System.out.println("按下enter");
        this.setExpressionValue("#{pageFlowScope.searchMode}", "SIMPLE");
        String searchValue = (String)this.getSearchInput().getValue();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvSearchInput", searchValue);
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        this.setExpressionValue("#{pageFlowScope.searchInput}", searchValue);
        tableModel.setParamMap(paramMap);
        System.out.println("查询参数:" + paramMap);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "N");
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            currSelectedList.clear();
        }
        //搜索后刷新DocStatus对应数量
        tableModel.getModel();
        tableModel.getViewObject().setRangeSize(-1);
        String docIdStr = tableModel.getAttributeStr("DocId");
        if (docIdStr == null) {
            //查询为空 与无输入值
            docIdStr = "-1";
        }
        if (searchValue == null || searchValue.length() == 0) {
            docIdStr = null;
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            tableModel.setParamMap(paramMap);
        }
        String searchResultStatus =
            CommonUtil.getWorkAM().getSearchStatusCode(CommonUtil.getLibCode(), (Number)this.resolveExpression("#{pageFlowScope.categoryId}"),
                                                       (String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"),
                                                       (String)this.resolveExpression("#{pageFlowScope.deleteFlag}"),
                                                       docIdStr, CommonUtil.getCompCode());
        if (searchValue != null && searchValue.length() > 0) {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", searchResultStatus);
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            tableModel.setParamMap(paramMap);
        }
    }

    public void complexSearch(Map<String, Object> additionalParamMap) {
        this.setExpressionValue("#{pageFlowScope.searchMode}", "COMPLEX");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        paramMap.put("QueryParam", additionalParamMap);
        this.setExpressionValue("#{pageFlowScope.additionalParamMap}", additionalParamMap);
        tableModel.setParamMap(paramMap);
        System.out.println("查询参数:" + paramMap);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "N");
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            currSelectedList.clear();
        }
        //搜索后刷新DocStatus对应数量
        tableModel.getModel();
        tableModel.getViewObject().setRangeSize(-1);
        String docIdStr = tableModel.getAttributeStr("DocId");
        if (docIdStr == null) {
            //查询为空 与无输入值
            docIdStr = "-1";
        }
        if (additionalParamMap == null || additionalParamMap.size() == 0) {
            docIdStr = null;
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            tableModel.setParamMap(paramMap);
        }
        String searchResultStatus =
            CommonUtil.getWorkAM().getSearchStatusCode(CommonUtil.getLibCode(), (Number)this.resolveExpression("#{pageFlowScope.categoryId}"),
                                                       (String)this.resolveExpression("#{pageFlowScope.categoryGroupType}"),
                                                       (String)this.resolveExpression("#{pageFlowScope.deleteFlag}"),
                                                       docIdStr, CommonUtil.getCompCode());
        if (additionalParamMap != null && additionalParamMap.size() > 0) {
            this.setExpressionValue("#{pageFlowScope.currStatusCode}", searchResultStatus);
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            tableModel.setParamMap(paramMap);
        }
    }

    public void docIdBatchSetActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            sb.append("," + docId);
        }
        this.setExpressionValue("#{pageFlowScope.docId}", sb.toString().substring(1));
    }


    public String syncEnableDocRollbackAction() {
        String currStatusCode = CommonUtil.getCurrStatusCode();
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(currStatusCode + "_currSelected");
        if (currSelectedList == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        StringBuffer syncInfo = new StringBuffer();
        for (String key : currSelectedList) {
            Number docId = new Number(Integer.parseInt(key.substring(key.lastIndexOf("_") + 1)));
            if (currStatusCode.contains("TO_SYNC")) {
                String docName = CommonUtil.getDocNameByDocId(docId);
                String syncResult = CommonUtil.getCommonAM().getSyncFlag(docId);
                if ("SYNCING".equals(syncResult)) {
                    syncInfo.append(docName + "正在同步中,不能进行该操作...\n");
                    continue;
                } else if ("SUCCESS".equals(syncResult)) {
                    syncInfo.append(docName + "已经同步完成,不能进行该操作...\n");
                    continue;
                } else if ("FILE_MANAGING".equals(syncResult)) {
                    syncInfo.append(docName + "正在从UCM上下载文件...\n");
                    continue;
                }
            }
            sb.append("," + docId);
        }
        if (syncInfo != null && syncInfo.length() > 0) {
            this.addFormattedMessage(null, syncInfo.toString(), FacesMessage.SEVERITY_WARN);
            return null;
        }
        this.setExpressionValue("#{pageFlowScope.docId}", sb.toString().substring(1));
        return "toRollback";
    }

    public void rollbackReturnListener(ReturnEvent returnEvent) {
        String operatorResult = (String)returnEvent.getReturnParameters().get("operatorResult");
        String docIds = (String)returnEvent.getReturnParameters().get("docId");
        if ("SAVE".equals(operatorResult)) {
            String[] docIdArray = docIds.split(",");
            List<Number> list = new ArrayList<Number>();
            for (String str : docIdArray) {
                try {
                    Number docId = new Number(str);
                    list.add(docId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            backDocStatus(list);
        }
    }

    public void refreshCategory() {
        this.appendScript("refreshCategory()");
    }

    /**
     * 表格全选事件
     * @param clientEvent
     */
    public void selectAllAction(ClientEvent clientEvent) {
        List list = (List)table.getValue();
        Boolean isSelectAllChecked = (Boolean)clientEvent.getParameters().get("params");
        String selectAllKey = CommonUtil.getCurrStatusCode() + "_" + "selectAllFlag";
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (isSelectAllChecked) {
            CommonUtil.putStringToSession(selectAllKey, "Y");
            for (int i = 0; i < list.size(); i++) {
                Row row = (Row)list.get(i);
                Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
                String key = CommonUtil.getCurrStatusCode() + "_" + docId;
                if (!currSelectedList.contains(key)) {
                    currSelectedList.add(key);
                }
            }
        } else {
            CommonUtil.putStringToSession(selectAllKey, "N");
            currSelectedList.clear();
        }
        System.out.println("selectAllAction--->currSelectedList:" + currSelectedList);
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
    }

    public void switchPageWhenComplexSearch() {
        String searchMode = (String)this.resolveExpression("#{pageFlowScope.searchMode}");
        if ("COMPLEX".equals(searchMode)) {
            Map<String, Object> additionalParamMap =
                (Map<String, Object>)this.resolveExpression("#{pageFlowScope.additionalParamMap}");
            if (additionalParamMap != null && additionalParamMap.size() > 0) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
                paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
                paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
                paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
                paramMap.put("bvStatus", CommonUtil.getCurrStatusCode());
                paramMap.put("QueryParam", additionalParamMap);
                tableModel.setParamMap(paramMap);
            }
        }
    }

    public void prevPage(ActionEvent actionEvent) {
        switchPageWhenComplexSearch();
        tableModel.prevPage(actionEvent);
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setValue(false);
        }
        this.resetStatusSession();
    }

    public void nextPage(ActionEvent actionEvent) {
        tableModel.nextPage(actionEvent);
        switchPageWhenComplexSearch();
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setValue(false);
        }
        this.resetStatusSession();
    }

    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        switchPageWhenComplexSearch();
        tableModel.goPageIndex(valueChangeEvent);
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setValue(false);
        }
        this.resetStatusSession();
    }

    /**
     * 表格单选事件
     * @param clientEvent
     */
    public void selectOneAction(ClientEvent clientEvent) {
        Boolean isSelectChecked = (Boolean)clientEvent.getParameters().get("params");
        Integer docId = ((Double)clientEvent.getParameters().get("docId")).intValue();
        String currStatusCode = CommonUtil.getCurrStatusCode();
        String key = currStatusCode + "_" + docId;
        String selectAllKey = currStatusCode + "_" + "selectAllFlag";
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(currStatusCode + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (!isSelectChecked) {
            this.getSelectAllCheckBox().setSelected(isSelectChecked);
            if (currSelectedList.contains(key)) {
                currSelectedList.remove(key);
            }
        } else {
            this.getSelectAllCheckBox().setSelected(!isSelectChecked);
            if (!currSelectedList.contains(key)) {
                currSelectedList.add(key);
            }
        }
        CommonUtil.putObjectToSession(currStatusCode + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(selectAllKey, "O");
        this.refreshUIComponent(this.getSelectAllCheckBox());
    }

    /**
     * 表格行单击事件
     * @param clientEvent
     */
    public void tableSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row;
            try {
                row = (Row)list.get(index);
            } catch (IndexOutOfBoundsException e) {
                this.refreshUIComponent(this.getTable());
                return;
            }
            Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();

            String key = CommonUtil.getCurrStatusCode() + "_" + docId;
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList == null) {
                currSelectedList = new ArrayList<String>();
            }
            if ((currSelectedList.size() > 1) || (currSelectedList.size() == 1 && !currSelectedList.contains(key))) {
                currSelectedList.clear();
                currSelectedList.add(key);
            } else if (currSelectedList.size() == 1 && currSelectedList.contains(key)) {
                currSelectedList.clear();
            } else {
                currSelectedList.add(key);
            }
            CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
            CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "O");
            this.refreshUIComponent(this.getTable());
        }
    }

    /**
     * 表格多选事件
     * @param clientEvent
     */
    public void tableDblSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        //只选择一个的时候 docId赋值

        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr.trim() != null && indexStr.trim().length() > 0) {
            String[] rowkeys = indexStr.split(",");
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            for (String k : rowkeys) {
                Integer index = Integer.parseInt(k.trim());
                Row row;
                try {
                    row = (Row)list.get(index);
                } catch (IndexOutOfBoundsException e) {
                    this.refreshUIComponent(this.getTable());
                    return;
                }
                Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
                String key = CommonUtil.getCurrStatusCode() + "_" + docId;
                if (currSelectedList == null) {
                    currSelectedList = new ArrayList<String>();
                }
                if (!currSelectedList.contains(key)) {
                    currSelectedList.add(key);
                }
            }
            CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
            CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "O");
            this.refreshUIComponent(this.getTable());
        }
    }

    /**
     * 获取当前选中文档数量
     * @return
     */
    public int getCurrSelectedListSize() {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            return 0;
        }
        return currSelectedList.size();
    }

    /**
     * 禁止输入关键字事件
     * @param clientEvent
     */
    public void forbidInputKeywordAction(ClientEvent clientEvent) {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            this.addFormattedMessage(this.getKeywordsInputText().getClientId(), "此处禁止输入", FacesMessage.SEVERITY_WARN);
            String keywords = (String)this.resolveExpression("#{bindings.Keyword.inputValue}");
            this.getKeywordsInputText().setValue(keywords);
            this.refreshUIComponent(this.getKeywordsInputText());
        } else {
            this.addFormattedMessage(this.getKeywords4BatchEditInputText().getClientId(), "此处禁止输入",
                                     FacesMessage.SEVERITY_WARN);
            String keywords = (String)this.resolveExpression("#{bindings.Keyword1.inputValue}");
            this.getKeywords4BatchEditInputText().setValue(keywords);
            this.refreshUIComponent(this.getKeywords4BatchEditInputText());
        }
    }

    /**
     * 增加关键字事件
     * @param actionEvent
     */
    public void addKeyworkActionListener(ActionEvent actionEvent) {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        RichInputText inputText = null;
        String keywords = null;
        if (batchFlag == null || "N".equals(batchFlag)) {
            inputText = this.getKeyworkInputText();
            keywords = (String)this.resolveExpression("#{bindings.Keyword.inputValue}");
        } else {
            inputText = this.getKeywork4BatchEditInputText();
            keywords = (String)this.resolveExpression("#{bindings.Keyword1.inputValue}");
        }
        String keyword = (String)inputText.getValue();
        if (keyword == null || keyword.trim().length() == 0) {
            this.addFormattedMessage(inputText.getClientId(), "关键字不能为空", FacesMessage.SEVERITY_ERROR);
            return;
        }
        if (keywords == null) {
            keywords = keyword;
        } else {
            keywords = keywords + "," + keyword;
        }
        if (batchFlag == null || "N".equals(batchFlag)) {
            this.setExpressionValue("#{bindings.Keyword.inputValue}", keywords);
        } else {
            this.setExpressionValue("#{bindings.Keyword1.inputValue}", keywords);
        }
        inputText.setValue(null);
    }

    public void addToTempShelf(ActionEvent actionEvent) {
        StringBuffer docIds = new StringBuffer();
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        for (String key : currSelectedList) {
            Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
            docIds.append(docId + ",");
        }
        String result = resExpAm.addToTempShelf(docIds.toString());
        if ("0".equals(result)) {
            this.showLongMessage("已添加到暂存架!");
        } else {
            this.addFormattedMessage(null, "添加到暂存架出错,请检查!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void viewSyncLogActionListener(ActionEvent actionEvent) {
        StringBuffer docIds = new StringBuffer();
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            return;
        }
        if (currSelectedList != null) {
            for (String key : currSelectedList) {
                Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                docIds.append(docId + ",");
            }
            if (docIds.length() > 0) {
                this.setExpressionValue("#{pageFlowScope.docIds}", docIds.substring(0, docIds.length() - 1));
            }
        }
    }

    public void viewDownloadProgressActionListener(ActionEvent actionEvent) {
        String docId = actionEvent.getComponent().getAttributes().get("docId").toString();
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.setExpressionValue("#{sessionScope.downloadDocId}", docId);
        String url = request.getContextPath() + "/work/common/jsp/downloadProgress.jsp";
        this.openWindow(url, 800, 100);
    }


    public void downloadMarkSystemListener(FacesContext facesContext, OutputStream outputStream) {
        try {
            String compCode = CommonUtil.getCompCode();
            String libCode = CommonUtil.getLibCode();
            System.out.println("compCode:" + compCode + "  libCode:" + libCode);
            boolean isOnline = Constants.IS_ONLINE_MODE; //true是线上环境，false是本地环境
            String zipAttPath = null; //线上的临时目录
            String directoryPath = null;
            File zipFile = null;
            String entryPath = null;
            if (isOnline) {
                entryPath = "/home/oracle/cms/entry/";
                directoryPath = entryPath + libCode + "/";
                zipAttPath = entryPath + "export.zip";
            } else {
                entryPath = "C:\\Users\\vicky\\Desktop\\entry\\";
                directoryPath = "C:\\Users\\vicky\\Desktop\\entry\\" + libCode + "\\";
                zipAttPath = "D://templatePath/export.zip";
            }
            zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            Map<String, String> fileMap = new HashMap<String, String>();
            CommonUtil.listDirectory(directoryPath, fileMap);
            if (fileMap.containsKey("index.xml")) {
                fileMap.remove("index.xml");
            }
            for (Iterator it = fileMap.keySet().iterator(); it.hasNext(); ) {
                String fileName = (String)it.next();
                String filePath = fileMap.get(fileName);
                if (fileName.endsWith("AttributeXml.xml")) {
                    Map<String, String> attrMap = new HashMap<String, String>();
                    attrMap.put("subjectType", CommonUtil.getWorkAM().getLibIdByCode(libCode));
                    XmlUtil.replaceXmlNodeAttrValue(filePath, filePath, "attrCollection", "subjectType", attrMap);
                }
                FileInputStream fis = new FileInputStream(filePath);
                ZipEntry entry = new ZipEntry(filePath.replace(directoryPath, ""));
                out.putNextEntry(entry);
                byte[] b = new byte[1024 * 512];
                int len = 0;
                while ((len = fis.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                out.closeEntry();
                fis.close();
            }
            String indexFilePath = directoryPath + "index.xml";
            Map<String, String> nodeMap = new HashMap<String, String>();
            nodeMap.put("DocLibID", CommonUtil.getWorkAM().getLibIdByCode(libCode));
            XmlUtil.replaceXmlNodeValue(indexFilePath, indexFilePath, "param", nodeMap);

            FileInputStream fis = new FileInputStream(indexFilePath);
            ZipEntry entry = new ZipEntry("index.xml");
            out.putNextEntry(entry);
            byte[] b = new byte[1024 * 512];
            int len1 = 0;
            while ((len1 = fis.read(b)) != -1) {
                out.write(b, 0, len1);
            }
            out.closeEntry();
            fis.close();
            out.close();
            FileInputStream fisRar = new FileInputStream(zipAttPath);
            int len = 0;
            byte[] bytes = new byte[100000];
            while ((len = fisRar.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            fisRar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void batchDownloadEntryActionListener(FacesContext facesContext, OutputStream outputStream) {
        try {
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            if (currSelectedList != null) {
                String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
                File zipFile = null;
                zipFile = new File(zipAttPath);
                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(zipFile);
                CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
                ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
                out.setEncoding("GBK");
                for (String key : currSelectedList) {
                    Number docId = new Number(key.substring(key.lastIndexOf("_") + 1));
                    try {
                        List<Row> ucmFileList = CommonUtil.getWorkAM().getUCMFileByDocId(docId);
                        for (Row row : ucmFileList) {
                            String entryTitle = CommonUtil.getCommonAM().getEntryTitle(docId);
                            Number ucmDID = (Number)row.getAttribute("UcmDid");
                            String fileName = (String)row.getAttribute("FileName");
                            String attribute15 = (String)row.getAttribute("Attribute15");
                            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
                            String suffix = fileName.substring(fileName.lastIndexOf("."));
                            InputStream is = null;
                            if (attribute15 != null) {
                                is = CommonUtil.getInputStreamByUrl(attribute15);
                            } else {
                                is = DocService.getInstance().getFile(ucmDID.toString());
                            }
                            entryTitle = (entryTitle == null ? prefix : entryTitle);
                            ZipEntry entry = new ZipEntry(entryTitle + "_" + docId + suffix);
                            out.putNextEntry(entry);
                            byte[] b = new byte[1024 * 512];
                            int len = 0;
                            while ((len = is.read(b)) != -1) {
                                out.write(b, 0, len);
                            }
                            out.closeEntry();
                            is.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                out.close();
                FileInputStream fisRar = new FileInputStream(zipAttPath);
                int len = 0;
                byte[] bytes = new byte[100000];
                while ((len = fisRar.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                outputStream.flush();
                outputStream.close();
                fisRar.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载Word智能客户端
     * @param facesContext
     * @param outputStream
     */
    public void downloadWordClientListener(FacesContext facesContext, OutputStream outputStream) {
        try {
            FileInputStream fis = new FileInputStream(Constants.WORD_CLIENT_PATH);
            int len = 0;
            byte[] bytes = new byte[100000];
            while ((len = fis.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新全选框
     */
    public void refreshSelectAllCheckBox() {
        if (this.getSelectAllCheckBox() != null) {
            Object o = this.getSelectAllCheckBox().getValue();
            if (o != null && (Boolean)o) {
                this.getSelectAllCheckBox().setValue(false);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getSelectAllCheckBox());
        }
    }

    public void setResAuthMap(Map<String, String> resAuthMap) {
        this.resAuthMap = resAuthMap;
    }

    public Map<String, String> getResAuthMap() {
        return resAuthMap;
    }

    public void setWorkAm(WorkAMImpl workAm) {
        this.workAm = workAm;
    }

    public WorkAMImpl getWorkAm() {
        return workAm;
    }

    public void setSelectAllCheckBox(RichSelectBooleanCheckbox selectAllCheckBox) {
        this.selectAllCheckBox = selectAllCheckBox;
    }

    public RichSelectBooleanCheckbox getSelectAllCheckBox() {
        return selectAllCheckBox;
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        return table;
    }

    public void setTableModel(CustomTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel getTableModel() {
        return tableModel;
    }

    public void setKeyworkInputText(RichInputText keyworkInputText) {
        this.keyworkInputText = keyworkInputText;
    }

    public RichInputText getKeyworkInputText() {
        return keyworkInputText;
    }

    public void setKeywordsInputText(RichInputText keywordsInputText) {
        this.keywordsInputText = keywordsInputText;
    }

    public RichInputText getKeywordsInputText() {
        return keywordsInputText;
    }

    public void setKeywork4BatchEditInputText(RichInputText keywork4BatchEditInputText) {
        this.keywork4BatchEditInputText = keywork4BatchEditInputText;
    }

    public RichInputText getKeywork4BatchEditInputText() {
        return keywork4BatchEditInputText;
    }

    public void setKeywords4BatchEditInputText(RichInputText keywords4BatchEditInputText) {
        this.keywords4BatchEditInputText = keywords4BatchEditInputText;
    }

    public RichInputText getKeywords4BatchEditInputText() {
        return keywords4BatchEditInputText;
    }

    public void setSearchInput(RichInputText searchInput) {
        this.searchInput = searchInput;
    }

    public RichInputText getSearchInput() {
        return searchInput;
    }

    public void setResExpAm(ResExpAMImpl resExpAm) {
        this.resExpAm = resExpAm;
    }

    public ResExpAMImpl getResExpAm() {
        return resExpAm;
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void setViewSyncLogPopup(RichPopup viewSyncLogPopup) {
        this.viewSyncLogPopup = viewSyncLogPopup;
    }

    public RichPopup getViewSyncLogPopup() {
        return viewSyncLogPopup;
    }

    public void setCoverPanelGroupLayout(RichPanelGroupLayout coverPanelGroupLayout) {
        this.coverPanelGroupLayout = coverPanelGroupLayout;
    }

    public RichPanelGroupLayout getCoverPanelGroupLayout() {
        return coverPanelGroupLayout;
    }

    public void setDestoryPopup(RichPopup destoryPopup) {
        this.destoryPopup = destoryPopup;
    }

    public RichPopup getDestoryPopup() {
        return destoryPopup;
    }

    public void setFileManagePopup(RichPopup fileManagePopup) {
        this.fileManagePopup = fileManagePopup;
    }

    public RichPopup getFileManagePopup() {
        return fileManagePopup;
    }
}
