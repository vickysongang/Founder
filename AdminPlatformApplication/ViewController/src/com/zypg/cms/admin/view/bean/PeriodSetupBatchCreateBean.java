package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.model.viewobject.CmsPeriodEditVORowImpl;
import com.zypg.cms.admin.view.model.BacthCreateModel;
import com.zypg.cms.admin.view.util.CalculatePeriodUtil;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class PeriodSetupBatchCreateBean extends CustomManagedBean {
    public PeriodSetupBatchCreateBean() {
    }


    private List<BacthCreateModel> list = new ArrayList<BacthCreateModel>();

    public void initPeriodSetupBatchCreate() {
        if (this.getAdminAM().getDBTransaction().isDirty()) {
            this.getAdminAM().getDBTransaction().rollback();
        }
        Number categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
        Number setupId = (Number)this.resolveExpression("#{pageFlowScope.setupId}");
        Number year = (Number)this.resolveExpression("#{pageFlowScope.year}");
        this.getAdminAM().preBatchCreatePeriod(categoryId, setupId, year);

    }

    public void calculatePeriod(ActionEvent actionEvent) {
        CmsPeriodEditVORowImpl row = (CmsPeriodEditVORowImpl)this.getAdminAM().getPeriodInfo();
        oracle.jbo.domain.Number categoryid = row.getCategoryId();
        String periodDate = row.getPerioddate();
        String periodType = row.getPeriodtype();
        oracle.jbo.domain.Number startYear = row.getStartyear();
        oracle.jbo.domain.Number endYear = row.getEndyear();
        String categoryname = row.getCategoryName();
        oracle.jbo.domain.Number categoryNum = row.getCategoryNum();
        List numberList = this.getAdminAM().getAllPeriodNumForCategoryId(categoryid);
        this.setExpressionValue("#{pageFlowScope.categoryId}", categoryid);
        System.out.println("categoryid----->>" + categoryid + "<---periodDate---->" + periodDate +
                           "<0----periodType---->>" + periodType + "<----startYear-->" + startYear +
                           "<---categoryname---->>" + categoryname + "<----categoryNum--------->" + categoryNum);

        if (categoryNum == null) {
            categoryNum = new oracle.jbo.domain.Number(0);
        }

        if (periodDate != null) {
            String[] date = periodDate.split(",");
            List<BacthCreateModel> list = new ArrayList<BacthCreateModel>();
            CalculatePeriodUtil calculatePeriodUtil = new CalculatePeriodUtil();
            if (periodType.equals("MONTHLY")) {
                list =
calculatePeriodUtil.getAllMONTHLY(list, categoryname, startYear, endYear, "MONTHLY", date, numberList, categoryNum);

            } else if (periodType.equals("WEEKLY1")) { //周刊 2
                list =
calculatePeriodUtil.getAllWeekly(list, categoryname, startYear, endYear, "WEEKLY1", date, numberList, categoryNum);

            } else if (periodType.equals("BIWEEKLY")) { //双周刊 2,3
                list =
calculatePeriodUtil.getAllWeekly(list, categoryname, startYear, endYear, "BIWEEKLY", date, numberList, categoryNum);

            } else if (periodType.equals("WEEKLY2")) { //周报 1,2,3,4
                list =
calculatePeriodUtil.getAllWeekly(list, categoryname, startYear, endYear, "WEEKLY2", date, numberList, categoryNum);

            } else if (periodType.equals("SEMIMONTHLY")) { //半月刊 11,21
                list =
calculatePeriodUtil.getAllMONTHLY(list, categoryname, startYear, endYear, "SEMIMONTHLY", date, numberList,
                                  categoryNum);

            } else if (periodType.equals("BIMONTHLY")) { //双月刊 2,9 SEMIMONTHLY MONTHLY BIMONTHLY
                list =
calculatePeriodUtil.getAllMONTHLY(list, categoryname, startYear, endYear, "BIMONTHLY", date, numberList, categoryNum);

            } else if (periodType.equals("TEN_DAYS")) { //旬刊 5,16,27
                list =
calculatePeriodUtil.getAllMONTHLY(list, categoryname, startYear, endYear, "TEN_DAYS", date, numberList, categoryNum);

            } else if (periodType.equals("QUARTERLY")) { //季刊 2,5
                int n = 1;
                for (int i = startYear.intValue(); i <= endYear.intValue(); i++) {
                    for (int j = Integer.parseInt(date[0]); j < 13; ) {
                        BacthCreateModel bc = new BacthCreateModel();
                        bc.setName(categoryname);

                        if (j < 10) {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-0" + j + "-0" + date[1]);
                            } else {
                                bc.setDate(i + "-0" + j + "-" + date[1]);
                            }
                        } else {
                            if (Integer.parseInt(date[0]) < 10) {
                                bc.setDate(i + "-" + j + "-0" + date[1]);
                            } else {
                                bc.setDate(i + "-" + j + "-" + date[1]);
                            }
                        }

                        bc.setPeriodNum(n);
                        bc.setYear(i);
                        if (numberList.contains(n)) {
                            bc.setIsExist("刊期已存在");
                        }
                        j = j + 3;
                        n++;
                        if (n >= categoryNum.intValue() + 1) {
                            list.add(bc);
                        }
                    }
                }

            }

            this.setList(list);
            AdfFacesContext.getCurrentInstance().getPageFlowScope().put("model", list);
        }
        //        for (int i = 0; i < list.size(); i++) {
        //            BacthCreateModel l = list.get(i);
        //
        //            System.out.println(l.getName() + " ---->>" + l.getDate() + "<---刊期 -->>" + l.getPeriodNum());
        //
        //
        //        }
    }


    public void setList(List<BacthCreateModel> list) {
        this.list = list;
    }

    public List<BacthCreateModel> getList() {
        return list;
    }

    public void allSelectChoice(ActionEvent actionEvent) {
        List<BacthCreateModel> list =
            (List<BacthCreateModel>)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("model");

        for (int i = 0; i < list.size(); i++) {
            BacthCreateModel l = list.get(i);
            if (l.getIsExist() == null) {
                l.setFlag(true);
            }

        }
    }

    public void allNotSelect(ActionEvent actionEvent) {
        List<BacthCreateModel> list =
            (List<BacthCreateModel>)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("model");

        for (int i = 0; i < list.size(); i++) {
            BacthCreateModel l = list.get(i);
            l.setFlag(false);


        }
    }

    public String saveAction() {
        List<BacthCreateModel> list = this.getSelectNode();
        for (int i = 0; i < list.size(); i++) {
            BacthCreateModel l = list.get(i);
            new oracle.jbo.domain.Number(0);
            oracle.jbo.domain.Number year = new oracle.jbo.domain.Number(l.getYear().intValue());
            oracle.jbo.domain.Number numb = new oracle.jbo.domain.Number(l.getPeriodNum().intValue());
            String result =
                this.getAdminAM().savePeriodBatchSetup("NEW", (Number)this.resolveExpression("#{pageFlowScope.setupId}"),
                                                       (Number)this.resolveExpression("#{pageFlowScope.categoryId}"),
                                                       year, numb, l.getDate(), "批量创建.....",
                                                       (String)this.resolveExpression("#{pageFlowScope.compCode}"));

            if (!"S".equals(result)) {
                this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
                return null;
            }
        }
        this.showMessage("保存成功");

        return "end";
    }

    public String cancelAction() {
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("model", null);
        return "end";
    }

    public List<BacthCreateModel> getSelectNode() {
        List<BacthCreateModel> list =
            (List<BacthCreateModel>)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("model");
        List<BacthCreateModel> list1 = new ArrayList<BacthCreateModel>();
        for (int i = 0; i < list.size(); i++) {
            BacthCreateModel l = list.get(i);
            if (l.isFlag()) {
                list1.add(l);
            }
        }
        return list1;
    }
}
