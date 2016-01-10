package com.zypg.cms.work.view.bean;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.activity.query.CmsActivityVORowImpl;
import com.zypg.cms.work.model.viewobject.copyright.query.CmsAuthorCopyrightVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.render.ClientEvent;

public class AuthorCopyrightManagedBean extends CustomManagedBean {
    CustomTableModel<CmsAuthorCopyrightVORowImpl> tableModel =
        new CustomTableModel<CmsAuthorCopyrightVORowImpl>("WorkAMDataControl", "CopyrightAM", "CmsAuthorCopyrightVO", 20,
                                                   "findAuthorCopyrightVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;

    public AuthorCopyrightManagedBean() {
    }

    public void initAuthorCopyright() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!this.resolveExpression("#{pageFlowScope.deleteFlag}").equals("Y")) {
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupTyp}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setTableModel(CustomTableModel<CmsAuthorCopyrightVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsAuthorCopyrightVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void authorCopyrightTableDblClick(ClientEvent clientEvent) {
        // Add event code here...
    }
}
