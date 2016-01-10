package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.ThemeResourceOriginUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;


public class ThemeResourceOriginManagedBean extends CustomManagedBean {
    CustomTableModel tableModel = ThemeResourceOriginUtil.getCustomTabelModel(CommonUtil.getLibCode());
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private final static String ORIGIN_PREFIX = "ORIGIN";
    private RichSelectBooleanCheckbox selectAllCheckBox;
    private RichTable table;
    private RichSelectBooleanCheckbox selectAllCoverCheckBox;
    private RichTable coverTable;

    public ThemeResourceOriginManagedBean() {

    }

    public void initTableModel(String libCode) {
        tableModel = ThemeResourceOriginUtil.getCustomTabelModel(libCode);
        commonRenderer = new CommonRenderer(tableModel);
        tableModel.getModel();
        commonRenderer.resetStatusSession();
    }

    public void initThemeResourceOrigin() {
        String libCode = CommonUtil.getLibCode();
        initTableModel(libCode);
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        CommonUtil.putStringToSession("compCode", compCode);
        String groupUserFlag = (String)this.resolveExpression("#{pageFlowScope.groupUserFlag}");
        CommonUtil.putStringToSession("groupUserFlag", groupUserFlag);
        String displayModel = (String)this.resolveExpression("#{pageFlowScope.displayModel}");
        if (displayModel == null) {
            this.setExpressionValue("#{pageFlowScope.displayModel}", "TABLE");
        }
        this.setExpressionValue("#{pageFlowScope.allselect}", false);
    }

    public void selectAllAction(ClientEvent clientEvent) {
        List list = null;
        String displayModel = (String)this.resolveExpression("#{pageFlowScope.displayModel}");
        if ("TABLE".equals(displayModel)) {
            list = (List)table.getValue();
        } else {
            list = (List)coverTable.getValue();
        }
        Boolean isSelectAllChecked = (Boolean)clientEvent.getParameters().get("params");
        String selectAllKey = ORIGIN_PREFIX + "_" + "selectAllFlag";
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(ORIGIN_PREFIX + "_currSelected");
        if (currSelectedList == null) {
            currSelectedList = new ArrayList<String>();
        }
        if (isSelectAllChecked) {
            CommonUtil.putStringToSession(selectAllKey, "Y");
            for (int i = 0; i < list.size(); i++) {
                Row row = (Row)list.get(i);
                Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
                String key = ORIGIN_PREFIX + "_" + docId;
                if (!currSelectedList.contains(key)) {
                    currSelectedList.add(key);
                }
            }
        } else {
            CommonUtil.putStringToSession(selectAllKey, "N");
            currSelectedList.clear();
        }
        System.out.println("currSelectedList:" + currSelectedList);
        CommonUtil.putObjectToSession(ORIGIN_PREFIX + "_currSelected", currSelectedList);
    }

    public void selectOneAction(ClientEvent clientEvent) {
        Boolean isSelectChecked = (Boolean)clientEvent.getParameters().get("params");
        Integer docId = ((Double)clientEvent.getParameters().get("docId")).intValue();
        String key = ORIGIN_PREFIX + "_" + docId;
        String selectAllKey = ORIGIN_PREFIX + "_" + "selectAllFlag";
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(ORIGIN_PREFIX + "_currSelected");
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
        CommonUtil.putObjectToSession(ORIGIN_PREFIX + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(selectAllKey, "O");
        String displayModel = (String)this.resolveExpression("#{pageFlowScope.displayModel}");
        if ("TABLE".equals(displayModel)) {
            this.refreshUIComponent(this.getSelectAllCheckBox());
        } else {
            this.refreshUIComponent(this.getSelectAllCoverCheckBox());
        }
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        initThemeResourceOrigin();
    }

    public void addToThemeActionListener(ActionEvent actionEvent) {
        String goPage = (String)this.resolveExpression("#{pageFlowScope.goPage}");
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(ORIGIN_PREFIX + "_currSelected");
        System.out.println("addToThemeActionListener --- currSelectedList:" + currSelectedList);
        if (currSelectedList == null || currSelectedList.size() == 0) {
            this.addFormattedMessage(null, "请选择资源", FacesMessage.SEVERITY_WARN);
            return;
        }
        String nodeId = (String)this.resolveExpression("#{pageFlowScope.nodeId}");
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        if ("addTheme".equals(goPage)) {
            StringBuffer docIds = new StringBuffer();
            for (String key : currSelectedList) {
                Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                docIds.append(docId + ",");
            }
            Map<String, String> map =
                CommonUtil.getThemeAM().checkaddThemeResource(docIds.toString(), nodeId, compCode);
            String exsitDocIds = map.get("docId");
            System.out.println("exsitDocIds:" + exsitDocIds);
            String name = map.get("name");
            if (exsitDocIds != null && exsitDocIds.length() > 0) {
                List exsitDocidList = Arrays.asList(exsitDocIds.split(","));
                List<String> allDocids = Arrays.asList(docIds.toString().split(","));
                boolean flag = false;
                for (String docid : allDocids) {
                    if (!exsitDocidList.contains(docid)) {
                        System.out.println("添加到主题的DocId:" + docid);
                        CommonUtil.getThemeAM().addThemeResource(nodeId, docid, compCode);
                        flag = true;
                    }
                }
                if (flag) {
                    this.showErrorMessageLongAutoOpenURL("添加主题,主题:(" + name + ")已经存在!其余添加成功.",
                                                         name.length() * 7 + 320);
                    this.appendScript("window.close();window.opener.location.reload()");
                } else {
                    this.addFormattedMessage(null, "添加主题,主题:(" + name + ")已经存在!", FacesMessage.SEVERITY_WARN);
                }
                flag = false;
            } else {
                for (String key : currSelectedList) {
                    String docId = key.substring(key.lastIndexOf("_") + 1);
                    System.out.println("添加到主题的DocId:" + docId);
                    CommonUtil.getThemeAM().addThemeResource(nodeId, docId, compCode);
                }
                this.showLongMessageOpenURL("添加成功");
                this.appendScript("window.opener.location.reload()");
            }
            CommonUtil.getThemeAM().getDBTransaction().commit();
            CommonUtil.getThemeAM().getCmsThemeResourceTVO().executeQuery();
        } else if ("addResource".equals(goPage)) {
            StringBuffer docIds = new StringBuffer();
            for (String key : currSelectedList) {
                Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                docIds.append(docId + ",");
            }
            long currTime = System.currentTimeMillis();
            String name = CommonUtil.getResExpAM().checkeAddTempshelf(docIds.toString());
            System.out.println("校验耗时：" + (System.currentTimeMillis() - currTime));
            if (name != null && name.length() > 0) {
                this.addFormattedMessage(null, "添加失败,资源:(" + name + ")已经存在!", FacesMessage.SEVERITY_WARN);
            } else {
                long currTime1 = System.currentTimeMillis();
                String result = CommonUtil.getResExpAM().addToTempShelf(docIds.toString());
                System.out.println("添加耗时：" + (System.currentTimeMillis() - currTime1));
                if ("0".equals(result)) {
                    this.setExpressionValue("#{sessionScope.currTime}", System.currentTimeMillis());
                    this.showLongMessageOpenURL("已添加到暂存架!");
                    this.appendScript("window.close();window.opener.location.reload()");
                } else {
                    this.addFormattedMessage(null, "添加到暂存架出错,请检查!", FacesMessage.SEVERITY_ERROR);
                }
            }
        }
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

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setSelectAllCheckBox(RichSelectBooleanCheckbox selectAllCheckBox) {
        this.selectAllCheckBox = selectAllCheckBox;
    }

    public RichSelectBooleanCheckbox getSelectAllCheckBox() {
        return selectAllCheckBox;
    }

    public void setSelectAllCoverCheckBox(RichSelectBooleanCheckbox selectAllCoverCheckBox) {
        this.selectAllCoverCheckBox = selectAllCoverCheckBox;
    }

    public RichSelectBooleanCheckbox getSelectAllCoverCheckBox() {
        return selectAllCoverCheckBox;
    }

    public void setCoverTable(RichTable coverTable) {
        this.coverTable = coverTable;
    }

    public RichTable getCoverTable() {
        return coverTable;
    }

    public void prevPage(ActionEvent actionEvent) {
        commonRenderer.prevPage(actionEvent);
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setValue(false);
            this.setExpressionValue("#{pageFlowScope.allselect}", false);
            this.refreshUIComponent(this.getSelectAllCoverCheckBox());
        }
    }

    public void nextPage(ActionEvent actionEvent) {
        commonRenderer.nextPage(actionEvent);
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setValue(false);
            this.setExpressionValue("#{pageFlowScope.allselect}", false);
            this.refreshUIComponent(this.getSelectAllCoverCheckBox());
        }
    }

    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        commonRenderer.goPageIndex(valueChangeEvent);
        if (this.getSelectAllCheckBox() != null) {
            this.getSelectAllCheckBox().setValue(false);
            this.setExpressionValue("#{pageFlowScope.allselect}", false);
            this.refreshUIComponent(this.getSelectAllCoverCheckBox());
        }
    }
}
