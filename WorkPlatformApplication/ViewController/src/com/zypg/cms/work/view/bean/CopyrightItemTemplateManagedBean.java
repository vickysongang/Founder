package com.zypg.cms.work.view.bean;


import com.zypg.cms.excel.utils.ExportExcelUtil;
import com.zypg.cms.work.model.viewobject.copyright.query.Cms3rdAuthorizationItemVORowImpl;
import com.zypg.cms.work.model.viewobject.copyright.query.CmsCompanyCopyrightVOImpl;
import com.zypg.cms.work.view.model.ItemDownloadExcelModel;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;


public class CopyrightItemTemplateManagedBean extends CustomManagedBean {
    private Object[] companyArray;
    private RichSelectManyCheckbox companySelectManyChoice;

    public CopyrightItemTemplateManagedBean() {
    }


    public void itemTempleteDownloadListener(FacesContext facesContext, java.io.OutputStream outputStream) {

        companyArray = (Object[])this.resolveExpression("#{pageFlowScope.companyArray}");
        //根据公司列表 生成模板
        List<Cms3rdAuthorizationItemVORowImpl> datas = CommonUtil.getCopyrightAM().getDatas(companyArray);
        String[] headers = new String[] { "授权明细ID", "图书名称", "ISBN", "授权单位", "本次销售费用", "本次销售数量", "备注" };
        String[] headerAttrs =
            new String[] { "ItemId", "BookName", "Isbn", "CompanyName", "SalesCost", "SalesNum", "Remarks" };
        ItemDownloadExcelModel shelfExcelData = new ItemDownloadExcelModel(headers, headerAttrs, datas);
        Map<Integer, Integer> wm = new HashMap<Integer, Integer>();
        ExportExcelUtil excelUtil = new ExportExcelUtil("index", wm, true);
        excelUtil.exportExcel(shelfExcelData, outputStream);
    }

    public void preSelectCompany() {
        CmsCompanyCopyrightVOImpl companyVO = CommonUtil.getCopyrightAM().getCmsCompanyCopyrightVO4View();
        companyVO.setbvCompCode(CommonUtil.getCompCode());
        companyVO.executeQuery();
    }

    public void setCompanyArray(Object[] companyArray) {
        this.companyArray = companyArray;
    }

    public Object[] getCompanyArray() {
        return companyArray;
    }

    public void selectValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object[] companyArray = (Object[])valueChangeEvent.getNewValue();
        this.setExpressionValue("#{pageFlowScope.companyArray}", companyArray);
    }

    public void setCompanySelectManyChoice(RichSelectManyCheckbox companySelectManyChoice) {
        this.companySelectManyChoice = companySelectManyChoice;
    }

    public RichSelectManyCheckbox getCompanySelectManyChoice() {
        return companySelectManyChoice;
    }
}
