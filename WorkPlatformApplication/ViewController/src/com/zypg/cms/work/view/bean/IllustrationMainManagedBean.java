package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.model.viewobject.illustration.query.CmsIllustrationQVORowImpl;
import com.zypg.cms.work.model.viewobject.illustration.query.CmsIllustrationVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.ReturnEvent;


public class IllustrationMainManagedBean extends CustomManagedBean {
    private List<List<Map<String, Object>>> illustrationList = new ArrayList<List<Map<String, Object>>>();
    CustomTableModel<CmsIllustrationVORowImpl> tableModel =
        new CustomTableModel<CmsIllustrationVORowImpl>("WorkAMDataControl", "IllustrationAM", "CmsIllustrationVO", 20,
                                                       "findIllustrationVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichPopup viewPopup;
    private RichPopup deleteFigureGroupPopup;
    private RichCommandImageLink returnLink;

    public IllustrationMainManagedBean() {
    }

    public void initIllustration() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!"Y".equals(this.resolveExpression("#{pageFlowScope.deleteFlag}"))) {
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
        this.generateList();
    }

    public void illustrationImageDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)map.get("docId");

        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/illustration/inline/relatedIllustrationView.jsf?docId=" + docId;
        this.openWindow(url);
    }

    public void imageSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Boolean isSelectChecked = (Boolean)map.get("checked");
        Integer docId = ((oracle.jbo.domain.Number)map.get("docId")).intValue();
        String key = CommonUtil.getCurrStatusCode() + "_" + docId;
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (!isSelectChecked) {
            if (currSelectedList.size() > 1) {
                currSelectedList.clear();
                currSelectedList.add(key);
            } else {
                currSelectedList.clear();
            }
        } else {
            currSelectedList.clear();
            currSelectedList.add(key);
        }
        //代表当前选中
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "O");
        this.generateList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void generateList() {
        int i = 5;
        List<CmsIllustrationVORowImpl> model = tableModel.getModel();
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        for (Row row : model) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("illustrationId", row.getAttribute("IllustrationId"));
            String illustrationName = (String)row.getAttribute("IllustrationName");
            if (illustrationName != null) {
                map.put("illustrationName",
                        illustrationName.length() > 10 ? illustrationName.substring(0, 10) + "..." : illustrationName);
            } else {
                illustrationName = "";
                map.put("illustrationName", illustrationName);
            }
            String illustrationSource = (String)row.getAttribute("IllustrationSource");
            if (illustrationSource != null) {
                map.put("illustrationSource",
                        illustrationSource.length() > 10 ? illustrationSource.substring(0, 10) + "..." :
                        illustrationSource);
            } else {
                illustrationSource = "";
                map.put("illustrationSource", illustrationSource);
            }
            map.put("docId", row.getAttribute("DocId"));
            map.put("checked", row.getAttribute("Checked"));
            map.put("thumbnailUrl", row.getAttribute("ThumbnailUrl"));
            l.add(map);
            if (i-- == 1) {
                i = 5;
                list.add(l);
                l = new ArrayList<Map<String, Object>>();
            }
        }
        //补空
        for (int j = 1; j <= i % 5; j++) {
            l.add(new HashMap<String, Object>());
        }
        list.add(l);

        this.setIllustrationList(list);
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", new ArrayList<String>());
        this.generateList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }


    /**
     * 到指定页
     **/
    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        commonRenderer.goPageIndex(valueChangeEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public String refreshCoverAction() {
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        return null;
    }

    public void searchActionListenser(ActionEvent actionEvent) {
        commonRenderer.searchActionListenser(actionEvent);
        this.refreshCoverAction();
    }

    public void queryWhenPressEnterListener(ClientEvent clientEvent) {
        commonRenderer.queryWhenPressEnterListener(clientEvent);
        this.refreshCoverAction();
    }

    public void imageDblSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Boolean isSelectChecked = (Boolean)map.get("checked");

        Integer docId = ((oracle.jbo.domain.Number)map.get("docId")).intValue();
        String key = CommonUtil.getCurrStatusCode() + "_" + docId;
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (!isSelectChecked) {
            if (currSelectedList.contains(key)) {
                currSelectedList.remove(key);
            }
        } else {
            if (!currSelectedList.contains(key)) {
                currSelectedList.add(key);
            }
        }
        //代表当前选中
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(CommonUtil.getCurrStatusCode() + "_selectAllFlag", "O");

        this.generateList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void complexRearchReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            CmsIllustrationQVORowImpl queryRow =
                (CmsIllustrationQVORowImpl)CommonUtil.getIllustrationAM().getCmsIllustrationQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getIllustrationAM().getCmsIllustrationQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
            this.refreshCoverAction();
        }
    }

    public void setTableModel(CustomTableModel<CmsIllustrationVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsIllustrationVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setDeleteFigureGroupPopup(RichPopup deleteFigureGroupPopup) {
        this.deleteFigureGroupPopup = deleteFigureGroupPopup;
    }

    public RichPopup getDeleteFigureGroupPopup() {
        return deleteFigureGroupPopup;
    }

    public void setReturnLink(RichCommandImageLink returnLink) {
        this.returnLink = returnLink;
    }

    public RichCommandImageLink getReturnLink() {
        return returnLink;
    }

    public void setIllustrationList(List<List<Map<String, Object>>> illustrationList) {
        this.illustrationList = illustrationList;
    }

    public List<List<Map<String, Object>>> getIllustrationList() {
        if (illustrationList.size() == 0) {
            this.generateList();
        }
        return illustrationList;
    }

    public void setViewPopup(RichPopup viewPopup) {
        this.viewPopup = viewPopup;
    }

    public RichPopup getViewPopup() {
        return viewPopup;
    }
}
