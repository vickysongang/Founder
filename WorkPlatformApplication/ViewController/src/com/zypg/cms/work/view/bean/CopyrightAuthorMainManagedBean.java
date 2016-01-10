package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.honythink.applicationframework.hadf.util.DataSourceUtil;
import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.excel.utils.ExportExcelUtil;
import com.zypg.cms.foldermanager.model.FolderConstants;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatus4AuthorCopyrightVOImpl;
import com.zypg.cms.work.model.viewobject.common.query.CmsDocStatus4AuthorCopyrightVORowImpl;
import com.zypg.cms.work.model.viewobject.copyright.query.CmsAuthorCopyrightVORowImpl;
import com.zypg.cms.work.view.model.TempShelfDownloadExcelModel;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;

import com.zypg.cms.work.view.util.DaoUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
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

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class CopyrightAuthorMainManagedBean extends CustomManagedBean {
    CustomTableModel<CmsAuthorCopyrightVORowImpl> tableModel =
        new CustomTableModel<CmsAuthorCopyrightVORowImpl>("WorkAMDataControl", "CopyrightAM", "CmsAuthorCopyrightVO",
                                                          20, "findAuthorCopyrightVC");
    CommonRenderer commonRenderer = new CommonRenderer(tableModel);
    private RichPanelGridLayout panelGridLayout;
    private RichPanelGroupLayout buttonPanelGroupLayout;
    private RichInputText searchInput;
    private RichTree categoryTree;
    private RichCommandButton exportButton;

    public CopyrightAuthorMainManagedBean() {
    }

    public void initAuthor() {
        CommonUtil.getCopyrightAM().findCategory4AuthorCopyright((String)this.resolveExpression("#{pageFlowScope.compCode}"),
                                                                 CommonUtil.getLibCode());
        commonRenderer = new CommonRenderer(tableModel);
        filterTableModel(null, null);
        commonRenderer.resetStatusSession();
    }

    public void filterTableModel(String copyrightType, String timeRange) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvCategoryId", this.resolveExpression("#{pageFlowScope.categoryId}"));
        paramMap.put("bvCategoryGroupType", this.resolveExpression("#{pageFlowScope.categoryGroupType}"));
        paramMap.put("bvDeleteFlag", this.resolveExpression("#{pageFlowScope.deleteFlag}"));
        paramMap.put("bvCompCode", this.resolveExpression("#{pageFlowScope.compCode}"));

        if (!this.resolveExpression("#{pageFlowScope.deleteFlag}").equals("Y")) {
            System.out.println("currStatusCode:" + this.resolveExpression("#{pageFlowScope.currStatusCode}"));
            paramMap.put("bvStatus", this.resolveExpression("#{pageFlowScope.currStatusCode}"));
        } else {
            paramMap.put("bvStatus", null);
        }
        paramMap.put("bvCopyrightType", copyrightType);
        System.out.println("timeRange:" + timeRange);
        if (timeRange != null) {
            String[] items = timeRange.split(",");
            try {
                Number startNum = new Number(items[0]);
                Number endNum = new Number(items[1]);
                paramMap.put("bvStartNum", startNum);
                paramMap.put("bvEndNum", endNum);
                System.out.println("startNum:" + startNum + " endNum:" + endNum);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
        System.out.println("initAuthor=" + paramMap);
    }
/**
 * 修改ing
 * */
    public void categoryTreeSelectionListener(SelectionEvent selectionEvent) {
        RowKeySet rks = categoryTree.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)categoryTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            String copyrightType = (String)nodeBinding.getAttribute("CopyrightType");
            System.out.println(nodeBinding.getAttribute("CategoryDisplayName"));
            String timeRange = (String)nodeBinding.getAttribute("TimeRange");
            System.out.println("点击树时：" + nodeBinding.getAttribute("CategoryId"));
            this.setExpressionValue("#{pageFlowScope.categoryId}", nodeBinding.getAttribute("CategoryId"));
            this.setExpressionValue("#{pageFlowScope.realCategoryId}", nodeBinding.getAttribute("CategoryId"));
            this.setExpressionValue("#{pageFlowScope.copyrightType}", copyrightType);
            this.setExpressionValue("#{pageFlowScope.timeRange}", timeRange);
            this.setExpressionValue("#{pageFlowScope.deleteFlag}", "N");

            if (((Number)nodeBinding.getAttribute("CategoryId")).intValue() == -2) {
                this.setExpressionValue("#{pageFlowScope.categoryId}", null);
            } else if (((Number)nodeBinding.getAttribute("CategoryId")).intValue() == -3) {
                this.setExpressionValue("#{pageFlowScope.categoryId}", null);
                this.setExpressionValue("#{pageFlowScope.deleteFlag}", "Y");
            }
            filterTableModel(copyrightType, timeRange);
        }
    }


    /**
     * 获取文档状态列表:待采集、待标引、待审批、已发布
     * @return
     */
    public List<SelectItem> getStatusList() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        CmsDocStatus4AuthorCopyrightVOImpl cmsDocStatusVO =
            CommonUtil.getCopyrightAM().getCmsDocStatus4AuthorCopyrightVO();
        cmsDocStatusVO.setApplyViewCriteriaName("findDocStatusVC");
        cmsDocStatusVO.setbvCategoryId((Number)this.resolveExpression("#{pageFlowScope.realCategoryId}"));
        cmsDocStatusVO.setbvCompCode(CommonUtil.getCompCode());
        cmsDocStatusVO.setbvLibCode(CommonUtil.getLibCode());
        cmsDocStatusVO.executeQuery();
        System.out.println("aaa:" + this.resolveExpression("#{pageFlowScope.realCategoryId}"));
        cmsDocStatusVO.setRangeSize(-1);
        for (Row row : cmsDocStatusVO.getAllRowsInRange()) {
            CmsDocStatus4AuthorCopyrightVORowImpl ri = (CmsDocStatus4AuthorCopyrightVORowImpl)row;
            SelectItem item = new SelectItem(ri.getStatusCode(), ri.getStatusDisplayName());
            items.add(item);
        }
        return items;
    }

    public void statusValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{pageFlowScope.currStatusCode}", valueChangeEvent.getNewValue());
        String copyrightType = (String)this.resolveExpression("#{pageFlowScope.copyrightType}");
        String timeRange = (String)this.resolveExpression("#{pageFlowScope.timeRange}");
        filterTableModel(copyrightType, timeRange);
    }


    public void itemTempleteDownloadListener(FacesContext facesContext, OutputStream outputStream) {
        String path =
            "D:\\workspace\\FounderUCM\\02 系统实现\\workspace\\WorkPlatformApplication\\ViewController\\public_html\\WEB-INF";
        //        String os = System.getProperty("os.name");
        //        if (os != null && os.startsWith("Windows")) {
        //            FacesContext context = FacesContext.getCurrentInstance();
        //            path = context.getExternalContext().getRealPath("/") + "WEB-INF";
        //        } else {
        //            path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        //            if (path.indexOf("WEB-INF") > 0) {
        //                path = path.substring(0, path.indexOf("WEB-INF") + 7);
        //            }
        //        }
        FolderConstants.xmlPath = path + "/config/author_copyright_template.xls";
        FileInputStream file;
        try {
            file = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int len = file.read(buffer);
            while (len != -1) {
                System.out.println("len--->" + len);
                outputStream.write(buffer, 0, len);
                len = file.read(buffer);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importAuthorInfoListener(ActionEvent actionEvent) {
        //        // 传入参数 【当前出版社名 和 出版社Code】
        //       String publisherCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        //       String publisherName = CommonUtil.getCompName();
        //       Number categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
        //        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        //        adfFacesContext.getPageFlowScope().put("currentPublisherName", publisherName);
        //        adfFacesContext.getPageFlowScope().put("currentPublisherCode", publisherCode);
        //        adfFacesContext.getPageFlowScope().put("currentCategoryId", categoryId);
        //        System.out.println("当前出版社---->name:"+publisherName+"  code:"+publisherCode+" categoryId--->"+categoryId);
    }

    public void tableDblClick(ClientEvent clientEvent) {
        commonRenderer.tableDblSelectAction(clientEvent);
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
            String url =
                request.getContextPath() + "/faces/work/copyright/inline/copyrightAuthorView.jsf?docId=" + docId;
            this.openWindow(url);
        }
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

    public void setPanelGridLayout(RichPanelGridLayout panelGridLayout) {
        this.panelGridLayout = panelGridLayout;
    }

    public RichPanelGridLayout getPanelGridLayout() {
        return panelGridLayout;
    }

    public void setButtonPanelGroupLayout(RichPanelGroupLayout buttonPanelGroupLayout) {
        this.buttonPanelGroupLayout = buttonPanelGroupLayout;
    }

    public RichPanelGroupLayout getButtonPanelGroupLayout() {
        return buttonPanelGroupLayout;
    }

    public void setSearchInput(RichInputText searchInput) {
        this.searchInput = searchInput;
    }

    public RichInputText getSearchInput() {
        return searchInput;
    }

    public void setCategoryTree(RichTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    public RichTree getCategoryTree() {
        return categoryTree;
    }

    public void importAuthorInfoReturnListener(ReturnEvent returnEvent) {
        String importFlag = (String)returnEvent.getReturnParameters().get("importFlag");
        if ("S".equals(importFlag)) {
            this.showMessage("导入成功");
        }
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        this.initAuthor();
        this.refreshUIComponent(this.getButtonPanelGroupLayout());
    }
    public void exportAuthorInfoListener(ActionEvent actionEvent) {
            List<CmsAuthorCopyrightVORowImpl> tc = new ArrayList<CmsAuthorCopyrightVORowImpl>();
            tc = (List<CmsAuthorCopyrightVORowImpl>)this.getCommonRenderer().getTable().getValue();
            if(tc.size()>0){
                    this.executeCompomentAction(this.getExportButton().getClientId());
            }else{
                    this.addFormattedMessage(null, "没有可导出的记录", FacesMessage.SEVERITY_WARN);
                }

    }

    public void exportAuthorFileListener(FacesContext facesContext, OutputStream outputStream) {
        
        List<CmsAuthorCopyrightVORowImpl> tc = new ArrayList<CmsAuthorCopyrightVORowImpl>();
        tc = (List<CmsAuthorCopyrightVORowImpl>)this.getCommonRenderer().getTable().getValue();
        List<String> headers = new ArrayList<String>();
        headers.add("图书名");
        headers.add("ISBN");
        headers.add("作者");
        headers.add("版次");
        headers.add("出版社");
        headers.add("授权地区");
        headers.add("纸书定价");
        headers.add("版权代理人");
        headers.add("版权形式");
        headers.add("著作权人");
        headers.add("版权登记号");
        headers.add("数字版税");
        headers.add("海外版税");
        headers.add("网络出版社");
        headers.add("纸介出版社");
        headers.add("电子出版社");
        headers.add("广播出版社");
        headers.add("音像出版社");
        headers.add("出版时间");
        headers.add("印次");
        headers.add("图书分类");
        headers.add("信息网络传播权");
        headers.add("信息网络第三方转授权");
        headers.add("授权语种");
        headers.add("电子书最低定价");
        headers.add("独有版权");
        headers.add("部分试读");
        headers.add("海外版权");
        headers.add("公众版");
        headers.add("备注");
        
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        try {
            String zipAttPath = Constants.ZIP_ATTPATH; //线上的临时目录
            File zipFile = new File(zipAttPath);
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(cs));
            out.setEncoding("GBK");
            Connection conn = DataSourceUtil.getJndiDatasource().getConnection();
                for(int i=0 ;i<tc.size();i++){
                        CmsAuthorCopyrightVORowImpl row = tc.get(i);
                        Object flag = row.getAttribute("Checked");
                        if(flag!=null && !"".equals(flag)){
                            if("true".equals(flag+"")){
                                        Map<String, String> map = new HashMap<String, String>();                                    
                                        map.put("图书名", row.getBookName() == null ? "" : row.getBookName().toString());
                                        map.put("ISBN", row.getIsbn() == null ? "" : row.getIsbn().toString());
                                        map.put("作者", row.getAuthor() == null ? "" : row.getAuthor().toString());
                                        map.put("版次", row.getEdition() == null ? "" : row.getEdition().toString());
                                        map.put("出版社", row.getPublicCopyrightDisplay() == null ? "" : row.getPublicCopyrightDisplay().toString());
                                        map.put("授权地区", row.getAuthorizedArea() == null ? "" : row.getAuthorizedArea().toString());
                                        map.put("纸书定价", row.getPrice() == null ? "" : row.getPrice().toString());
                                        map.put("版权代理人", row.getCopyrightAgent() == null ? "" : row.getCopyrightAgent().toString());
                                        map.put("版权形式", row.getCopyrightForm() == null ? "" : row.getCopyrightForm().toString());
                                        map.put("著作权人", row.getCopyrightOwner() == null ? "" : row.getCopyrightOwner().toString());
                                        map.put("版权登记号", row.getCopyrightRegistrationNumber() == null ? "" : row.getCopyrightRegistrationNumber().toString());
                                        map.put("数字版税", row.getDigitalRoyalty() == null ? "" : row.getDigitalRoyalty().toString());
                                        map.put("海外版税", row.getOverseasRoyalty() == null ? "" : row.getOverseasRoyalty().toString());
                                        map.put("网络出版社","WU".equals(row.getNetworkCopyrightFlag()+"")? "无" : "有");
                                        map.put("纸介出版社","WU".equals(row.getPaperMediumCopyrightFlag()+"" )? "无" : "有");
                                        map.put("电子出版社", "WU".equals(row.getElectronicCopyrightFlag()+"")? "无" : "有");
                                        map.put("广播出版社", "WU".equals(row.getBroadcastCopyrightFlag()+"")? "无" : "有");
                                        map.put("音像出版社", "WU".equals(row.getAudioVideoCopyrightFlag()+"" )? "无" : "有");
                                        map.put("出版时间", row.getPubTime() == null ? "" : row.getPubTime().toString());
                                        map.put("印次", row.getImpression() == null ? "" : row.getImpression().toString());
                                        map.put("图书分类", row.getCategoryName() == null ? "" : row.getCategoryName().toString());
                                        map.put("信息网络传播权", "WU".equals(row.getAttribute1() +"") ? "无" : "有");
                                        map.put("信息网络第三方转授权", "WU".equals(row.getAttribute2()+"") ? "无" : "有");
                                        map.put("授权语种", row.getAuthorizedLang() == null ? "" : row.getAuthorizedLang().toString());
                                        map.put("电子书最低定价", row.getEBookMinPrice() == null ? "" : row.getEBookMinPrice().toString());
                                        map.put("电子书定价", row.getEBookPrice() == null ? "" : row.getEBookPrice().toString());
                                        map.put("独有版权", row.getExclusiveCopyrightDisplay() == null ? "" : row.getExclusiveCopyrightDisplay().toString());
                                        map.put("部分试读", "WU".equals(row.getPartProbationFlag()+"" ) ? "无" : "有");
                                        map.put("海外版权", "WU".equals(row.getOverseasCopyrightFlag()+"" )? "无" : "有");
                                        map.put("公众版", row.getPublicCopyrightDisplay() == null ? "" : row.getPublicCopyrightDisplay().toString());
                                        map.put("备注", row.getRemarks() == null ? "" : row.getRemarks().toString());
                                        map.put("图书分类", row.getCategoryName() == null ? "" : row.getCategoryName().toString());
                                        map.put("图书作者", row.getAuthor() == null ? "" : row.getAuthor().toString());
                                        map.put("独有版权", row.getExclusiveCopyrightDisplay() == null ? "" : row.getExclusiveCopyrightDisplay().toString());
                                        map.put("公众版", row.getPublicCopyrightDisplay() == null ? "" : row.getPublicCopyrightDisplay().toString());
                                        map.put("版权形式", row.getCopyrightFormDisplay() == null ? "" : row.getCopyrightFormDisplay().toString());
                                        datas.add(map);
                                }
                        }
                    }

            
            TempShelfDownloadExcelModel shelfExcelData = new TempShelfDownloadExcelModel(datas, headers);
            Map<Integer, Integer> wm = new HashMap<Integer, Integer>();
            ExportExcelUtil excelUtil = new ExportExcelUtil("作者版权信息", wm, true);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            excelUtil.exportExcel(shelfExcelData, os);
            byte[] array = os.toByteArray();
            ByteArrayInputStream xlsIn = new ByteArrayInputStream(array);
            ZipEntry entry = new ZipEntry("作者版权信息.xls");
            out.putNextEntry(entry);
            byte[] xlsBytes = new byte[1024 * 512];
            int xlsLen = 0;
            while ((xlsLen = xlsIn.read(xlsBytes)) != -1) {
                out.write(xlsBytes, 0, xlsLen);
            }
            out.closeEntry();
            xlsIn.close();

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
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void setExportButton(RichCommandButton exportButton) {
        this.exportButton = exportButton;
    }

    public RichCommandButton getExportButton() {
        return exportButton;
    }
}
