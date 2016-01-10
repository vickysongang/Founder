package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.excel.utils.ExportExcelUtil;
import com.zypg.cms.foldermanager.manager.FolderManager;
import com.zypg.cms.work.model.am.BookAMImpl;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQVORowImpl;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookVORowImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatus4CheckImpl;
import com.zypg.cms.work.view.model.TempShelfDownloadExcelModel;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.DaoUtil;
import com.zypg.cms.work.view.util.ResourcePushThread;
import com.zypg.cms.work.view.util.RowComparator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SortEvent;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.SortCriterion;


public class BookMainManagedBean extends CustomManagedBean {
    private List<List<Map<String, Object>>> bookList = new ArrayList<List<Map<String, Object>>>();
    CustomTableModel<CmsBookVORowImpl> tableModel =
        new CustomTableModel<CmsBookVORowImpl>("WorkAMDataControl", "BookAM", "CmsBookVO", 20, "findBookVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGridLayout panelGridLayout;
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichPopup coursewarePopup;
    private RichPopup teachingPlanPopup;
    private RichInputText searchInput;
    private RichPopup deleteChapterPopup;
    private RichCommandImageLink exportBookImageLink;
    private RichPopup exportBookPopup;
    private RichSelectManyCheckbox selectManyCheckbox;

    public BookMainManagedBean() {

    }

    public void initBook() {
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));
        if (!"Y".equals(this.resolveExpression("#{pageFlowScope.deleteFlag}"))) {
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        System.out.println(paramMap);
        tableModel.setParamMap(paramMap);
        commonRenderer.resetStatusSession();
        generateBookList();
    }

    public void generateBookList() {
        int i = 5;
        List<CmsBookVORowImpl> model = tableModel.getModel();
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        for (Row row : model) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bookId", row.getAttribute("BookId"));
            String author = (String)row.getAttribute("Author");
            if (author != null) {
                author = "(" + author + ")";
            } else {
                author = "";
            }
            String bookName = (String)row.getAttribute("BookName");
            if (bookName != null) {
                bookName = bookName + author;
                map.put("bookName", bookName.length() > 12 ? bookName.substring(0, 12) + "..." : bookName);
            } else {
                bookName = "";
                map.put("bookName", bookName + author);
            }
            map.put("edition", row.getAttribute("Edition"));
            map.put("impression", row.getAttribute("Impression"));
            map.put("checked", row.getAttribute("Checked"));
            map.put("docId", row.getAttribute("DocId"));
            map.put("thumbnailUrl", row.getAttribute("ThumbnailUrl"));
            map.put("chapterFlag", row.getAttribute("ChapterFlag"));
            map.put("syncFlag", row.getAttribute("SyncFlag"));
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
        this.setBookList(list);
    }

    public void bookDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)map.get("docId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/book/inline/relatedBookView.jsf?docId=" + docId;
        this.openWindow(url);
    }

    public void bookTableDblClick(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        List list = (List)map.get("value");
        RowKeySetImpl selectedRowKey = (RowKeySetImpl)map.get("selectedRowKeys");
        String indexStr = selectedRowKey.toString().substring(1, selectedRowKey.toString().length() - 1);
        if (indexStr != null && indexStr.length() > 0 && (indexStr.split(",").length == 1)) {
            Integer index = Integer.parseInt(indexStr);
            Row row = (Row)list.get(index);
            Integer docId = ((oracle.jbo.domain.Number)row.getAttribute("DocId")).intValue();
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = request.getContextPath() + "/faces/work/book/inline/relatedBookView.jsf?docId=" + docId;
            this.openWindow(url);
        }
    }

    public void imageSelectAction(ClientEvent clientEvent) {
        Map<String, Object> map = clientEvent.getComponent().getAttributes();
        Boolean isSelectChecked = (Boolean)map.get("checked");
        Integer docId = ((oracle.jbo.domain.Number)map.get("docId")).intValue();
        String currStatusCode = CommonUtil.getCurrStatusCode();
        String key = currStatusCode + "_" + docId;
        List<String> currSelectedList = (List<String>)CommonUtil.getValueFromSession(currStatusCode + "_currSelected");
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
        CommonUtil.putObjectToSession(currStatusCode + "_currSelected", currSelectedList);
        CommonUtil.putStringToSession(currStatusCode + "_selectAllFlag", "O");

        this.generateBookList();

        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
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

        this.generateBookList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }


    public void coursewareReturnListener(ReturnEvent returnEvent) {
        String returnValue = (String)returnEvent.getReturnParameters().get("returnValue");
        if (null != returnValue && returnValue.equals("COMMIT")) {
            this.showPopup(this.getCoursewarePopup().getClientId());
        }
    }


    public void teachingPlanReturnListener(ReturnEvent returnEvent) {
        String returnValue = (String)returnEvent.getReturnParameters().get("returnValue");
        if (null != returnValue && returnValue.equals("COMMIT")) {
            this.showPopup(this.getTeachingPlanPopup().getClientId());
        }
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", new ArrayList<String>());
        this.generateBookList();
        this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }

    public void viewChapterActionListener(ActionEvent actionEvent) {
        // chapterView 章节浏览
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (null != currSelectedList && currSelectedList.size() > 0) {
                String key = currSelectedList.get(0);
                Integer docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
                this.setExpressionValue("#{pageFlowScope.pDocId}", new Number(docId));
            } else {
                this.setExpressionValue("#{pageFlowScope.pDocId}", new Number(-99));
            }
            this.navToOutCome("toChapter");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chapterSortActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (null != currSelectedList && currSelectedList.size() > 0) {
                String key = currSelectedList.get(0);
                Integer docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
                this.setExpressionValue("#{pageFlowScope.pDocId}", new Number(docId));
            } else {
                this.setExpressionValue("#{pageFlowScope.pDocId}", new Number(-99));
            }
            this.navToOutCome("toChapterSort");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChaptersDialogListener(DialogEvent dialogEvent) {
        if ("ok".equalsIgnoreCase(dialogEvent.getOutcome().toString())) {
            //删除章节信息
            String flag = "E";
            List<String> currSelectedList =
                (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
            try {
                if (null != currSelectedList && currSelectedList.size() > 0) {
                    String key = currSelectedList.get(0);
                    Integer docId = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
                    flag = CommonUtil.getBookAM().deleteChapter(docId);
                }
            } catch (Exception e) {
                e.printStackTrace();
                flag = "E";
            }
            if ("S".equalsIgnoreCase(flag)) {
                this.showMessage("章节删除成功！");
            } else {
                this.showMessage("章节删除失败！");
            }

        }
    }

    public void importBookInfoListener(ActionEvent actionEvent) {
        // 传入参数 【当前出版社名 和 出版社Code】
        String publisherCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String publisherName = CommonUtil.getCompName();
        Number categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.getPageFlowScope().put("currentPublisherName", publisherName);
        adfFacesContext.getPageFlowScope().put("currentPublisherCode", publisherCode);
        adfFacesContext.getPageFlowScope().put("currentCategoryId", categoryId);
        System.out.println("当前出版社---->name:" + publisherName + "  code:" + publisherCode + " categoryId--->" +
                           categoryId);
    }

    public void importBookInfoReturnListener(ReturnEvent returnEvent) {
        String importFlag = (String)returnEvent.getReturnParameters().get("importFlag");
        String tip = (String)returnEvent.getReturnParameters().get("tip");
        if ("S".equals(importFlag)) {
            this.reFindCategory();
            this.appendScript("refreshCategory()");
            if (tip != null && tip.length() > 0) {
                this.showBigContext(tip);
            } else {
                this.showMessage("导入成功");
            }
        }
    }

    /**
     * 到指定页
     **/
    public void goPageIndex(ValueChangeEvent valueChangeEvent) {
        commonRenderer.goPageIndex(valueChangeEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateBookList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public String refreshCoverWhenSycnAction() {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        if (currSelectedList != null) {
            for (String key : currSelectedList) {
                String docId = key.substring(key.lastIndexOf("_") + 1);
                try {
                    String docName = CommonUtil.getDocNameByDocId(new Number(docId));
                    String path = "/" + CommonUtil.getCompName() + "/" + CommonUtil.getLibName() + "/" + docName;
                    CommonUtil.initFtpConn();
                    FolderManager fm = new FolderManager();
                    if (fm.exists(path)) {
                        CommonUtil.getCommonAM().startSync(docId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return refreshCoverAction();
    }

    public void pubToSiteActionListener(ActionEvent actionEvent) {
        List<String> currSelectedList =
            (List<String>)CommonUtil.getValueFromSession(CommonUtil.getCurrStatusCode() + "_currSelected");
        try {
            if (currSelectedList != null) {
                for (String key : currSelectedList) {
                    Integer docId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
                    ResourcePushThread push = new ResourcePushThread(new Number(docId));
                    new Thread(push).start();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String refreshCoverAction() {
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateBookList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        CommonUtil.putObjectToSession(CommonUtil.getCurrStatusCode() + "_currSelected", new ArrayList<String>());
        commonRenderer.resetStatusSession();
        return null;
    }

    public void reFileManageDialogListener(DialogEvent dialogEvent) {
        commonRenderer.reFileManageAction();
        this.refreshCoverAction();
    }

    public void searchActionListenser(ActionEvent actionEvent) {
        commonRenderer.searchActionListenser(actionEvent);
        this.refreshCoverAction();
    }

    public void queryWhenPressEnterListener(ClientEvent clientEvent) {
        commonRenderer.queryWhenPressEnterListener(clientEvent);
        this.refreshCoverAction();
    }

    public void complexRearchReturnListener(ReturnEvent returnEvent) {
        String comfirmFlag = (String)returnEvent.getReturnParameters().get("comfirmFlag");
        if ("Y".equals(comfirmFlag)) {
            CmsBookQVORowImpl queryRow = (CmsBookQVORowImpl)CommonUtil.getBookAM().getCmsBookQVO().getCurrentRow();
            Map<String, Object> additionalParamMap = new HashMap<String, Object>();
            for (AttributeDef def : CommonUtil.getBookAM().getCmsBookQVO().getAttributeDefs()) {
                Object value = queryRow.getAttribute(def.getName());
                if (value != null && !value.toString().contains("oracle.jbo.server.ViewRowSetImpl")) {
                    additionalParamMap.put(def.getName(), value);
                }
            }
            commonRenderer.complexSearch(additionalParamMap);
            this.refreshCoverAction();
        }
    }

    public void rollbackReturnListener(ReturnEvent returnEvent) {
        commonRenderer.rollbackReturnListener(returnEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateBookList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void deleteDocDialogActionListener(DialogEvent dialogEvent) {
        commonRenderer.deleteDocDialogActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateBookList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void destroyDocActionListener(DialogEvent dialogEvent) {
        commonRenderer.destroyDocActionListener(dialogEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null) {
            this.generateBookList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
        if (commonRenderer.getTable() != null) {
            this.refreshUIComponent(commonRenderer.getTable());
        }
    }

    public void switchStatusActionListener(ActionEvent actionEvent) {
        commonRenderer.switchStatusActionListener(actionEvent);
        if (commonRenderer.getCoverPanelGroupLayout() != null &&
            commonRenderer.getCoverPanelGroupLayout().isRendered()) {
            this.generateBookList();
            this.refreshUIComponent(commonRenderer.getCoverPanelGroupLayout());
        }
    }

    public void tableSortListener(SortEvent sortEvent) {
        List<SortCriterion> tableSortList = new ArrayList<SortCriterion>();
        List<SortCriterion> sortList = sortEvent.getSortCriteria();
        SortCriterion sortCriterion = sortList.get(0);
        Collections.sort(tableModel.getModel(), new RowComparator("BookName", sortCriterion.isAscending()));
        removeIfPresent(sortCriterion, tableSortList);
        tableSortList.add(0, sortCriterion);
        RichTable richTable = (RichTable)sortEvent.getComponent();
        richTable.setSortCriteria(tableSortList);
        this.refreshUIComponent(richTable);
    }

    private boolean removeIfPresent(SortCriterion sortCriterion, List<SortCriterion> tableSortList) {
        if (tableSortList != null && tableSortList.size() > 0) {
            for (SortCriterion sc : tableSortList) {
                if (sc.getProperty().equals(sortCriterion.getProperty())) {
                    tableSortList.remove(sc);
                    return true;
                }
            }
        }
        return false;
    }

    public void exportBookActionListener(ActionEvent actionEvent) {
        this.showPopup(this.getExportBookPopup().getClientId());
    }

    public void exportBookDialogListener(DialogEvent dialogEvent) {
        JUCtrlListBinding listBindings =
            (JUCtrlListBinding)BindingContext.getCurrent().getCurrentBindingsEntry().get("CmsDocStatus4ExportBook");
        Object[] selectedValues = listBindings.getSelectedValues();
        String statusStr = "";
        if (selectedValues != null && selectedValues.length > 0) {
            for (Object str : selectedValues) {
                statusStr = statusStr + ",'" + str + "'";
            }
        }
        if (statusStr != null && statusStr.length() > 0) {
            statusStr = statusStr.substring(1);
        }
        this.setExpressionValue("#{pageFlowScope.statusStr}", statusStr);
        this.executeCompomentAction(this.getExportBookImageLink().getClientId());
        this.getSelectManyCheckbox().resetValue();
        this.setExpressionValue("#{bindings.CmsDocStatus4ExportBook.inputValue}", null);
    }

    public void exportBookPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        this.resetPopupData(popupFetchEvent.getComponent());
    }

    public void exportBookListListener(FacesContext facesContext, OutputStream outputStream) {
        List<String> headers = new ArrayList<String>();
        try {
            headers = DaoUtil.getBookExportExcelHeader();
            List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
            String statusStr = (String)this.resolveExpression("#{pageFlowScope.statusStr}");
            datas = DaoUtil.getBookExportExcelDatas(CommonUtil.getCompCode(), statusStr);
            TempShelfDownloadExcelModel shelfExcelData = new TempShelfDownloadExcelModel(datas, headers);
            Map<Integer, Integer> wm = new HashMap<Integer, Integer>();
            ExportExcelUtil excelUtil = new ExportExcelUtil("index", wm, true);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            excelUtil.exportExcel(shelfExcelData, os);
            byte[] array = os.toByteArray();
            ByteArrayInputStream xlsIn = new ByteArrayInputStream(array);
            byte[] xlsBytes = new byte[1024 * 512];
            int xlsLen = 0;
            while ((xlsLen = xlsIn.read(xlsBytes)) != -1) {
                outputStream.write(xlsBytes, 0, xlsLen);
            }
            outputStream.close();
            xlsIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDeleteChapterPopup(RichPopup deleteChapterPopup) {
        this.deleteChapterPopup = deleteChapterPopup;
    }

    public RichPopup getDeleteChapterPopup() {
        return deleteChapterPopup;
    }

    public void setCoursewarePopup(RichPopup coursewarePopup) {
        this.coursewarePopup = coursewarePopup;
    }

    public RichPopup getCoursewarePopup() {
        return coursewarePopup;
    }

    public void setTeachingPlanPopup(RichPopup teachingPlanPopup) {
        this.teachingPlanPopup = teachingPlanPopup;
    }

    public RichPopup getTeachingPlanPopup() {
        return teachingPlanPopup;
    }

    public List<List<Map<String, Object>>> getBookList() {
        if (bookList.size() == 0) {
            generateBookList();
        }
        return bookList;
    }

    public void setBookList(List<List<Map<String, Object>>> bookList) {
        this.bookList = bookList;
    }

    public void setTableModel(CustomTableModel<CmsBookVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsBookVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setSearchInput(RichInputText searchInput) {
        this.searchInput = searchInput;
    }

    public RichInputText getSearchInput() {
        return searchInput;
    }

    public void setPanelGridLayout(RichPanelGridLayout panelGridLayout) {
        this.panelGridLayout = panelGridLayout;
    }

    public RichPanelGridLayout getPanelGridLayout() {
        return panelGridLayout;
    }

    public void setExportBookImageLink(RichCommandImageLink exportBookImageLink) {
        this.exportBookImageLink = exportBookImageLink;
    }

    public RichCommandImageLink getExportBookImageLink() {
        return exportBookImageLink;
    }

    public void setExportBookPopup(RichPopup exportBookPopup) {
        this.exportBookPopup = exportBookPopup;
    }

    public RichPopup getExportBookPopup() {
        return exportBookPopup;
    }

    public void setSelectManyCheckbox(RichSelectManyCheckbox selectManyCheckbox) {
        this.selectManyCheckbox = selectManyCheckbox;
    }

    public RichSelectManyCheckbox getSelectManyCheckbox() {
        return selectManyCheckbox;
    }
}
