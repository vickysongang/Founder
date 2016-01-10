package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.book.query.CmsBookVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocUcmRelTVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.HashMap;
import java.util.Map;

public class ViewMoreFilesManagedBean extends CustomManagedBean {
    CustomTableModel<CmsDocUcmRelTVORowImpl> tableModel =
        new CustomTableModel<CmsDocUcmRelTVORowImpl>("WorkAMDataControl", "WorkAM", "CmsDocUcmRelTVO4MoreFiles", 20,
                                                     "findByDocId");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);

    public ViewMoreFilesManagedBean() {
    }

    public void init4ViewMoreFiles() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
    }

    public void setTableModel(CustomTableModel<CmsDocUcmRelTVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsDocUcmRelTVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }
}
