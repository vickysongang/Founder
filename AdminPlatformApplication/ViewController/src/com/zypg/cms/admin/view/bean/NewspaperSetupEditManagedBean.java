package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.model.viewobject.CmsNewspaperEditVORowImpl;
import com.zypg.cms.admin.model.viewobject.CmsPeriodicalEditVORowImpl;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;

import oracle.jbo.domain.Number;

public class NewspaperSetupEditManagedBean extends CustomManagedBean {
    private RichInputText newspaperNameInputText;
    private RichSelectManyCheckbox weeklyNewspaperSelectManyCheckBox;
    private RichInputText priceInputText;
    private RichInputText cnInputText;
    private RichInputText issnInputText;
    private RichInputText compCodeInputText;

    public NewspaperSetupEditManagedBean() {
    }

    public void init4NewspaperSetupEdit() {
        if (this.getAdminAM().getDBTransaction().isDirty()) {
            this.getAdminAM().getDBTransaction().rollback();
        }
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number categoryId = null;
        if ("NEW".equals(mode)) {
            categoryId = new Number(-99);
            this.setExpressionValue("#{pageFlowScope.categoryId}", categoryId);
        } else {
            categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
        }
        this.getAdminAM().preEditNewspaper(mode, categoryId);
    }

    public void newspaperTypeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        String newspaperType = (String)valueChangeEvent.getNewValue();
        CmsNewspaperEditVORowImpl row =
            (CmsNewspaperEditVORowImpl)this.getAdminAM().getCmsNewspaperEditVO().getCurrentRow();
        if ("WEEKLY1".equals(newspaperType)) {
            row.setWeekly1("1");
        } else if ("BIWEEKLY".equals(newspaperType)) {
            row.setOldEven("1");
            row.setWeekly1("1");
        } else if ("MONTHLY".equals(newspaperType)) {
            row.setMonthly("1");
        } else if ("SEMIMONTHLY".equals(newspaperType)) {
            row.setFisrtHalfMonth("1");
            row.setSecondHalfMonth("16");
        } else if ("BIMONTHLY".equals(newspaperType)) {
            row.setOldEven("1");
            row.setMonthly("1");
        } else if ("TEN_DAYS".equals(newspaperType)) {
            row.setFirstTenDays("1");
            row.setSecondTenDays("11");
            row.setThirdTenDays("21");
        } else if ("QUARTERLY".equals(newspaperType)) {
            row.setQuarterly("1");
            row.setMonthly("1");
        } else if ("WEEKLY2".equals(newspaperType)) {
            this.getWeeklyNewspaperSelectManyCheckBox().setValue(null);
            this.refreshUIComponent(this.getWeeklyNewspaperSelectManyCheckBox());
        }
    }

    public void weeklyNewspaperValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object[] ns = (Object[])valueChangeEvent.getNewValue();
        String weeklyNewspaperDayStr = "";
        if (ns != null) {
            for (Object o : ns) {
                weeklyNewspaperDayStr = weeklyNewspaperDayStr + "," + o;
            }
        }
        if (weeklyNewspaperDayStr != null && weeklyNewspaperDayStr.length() > 0) {
            weeklyNewspaperDayStr = weeklyNewspaperDayStr.substring(1);
        }
        this.getAdminAM().getCmsNewspaperEditVO().getCurrentRow().setAttribute("Weekly2", weeklyNewspaperDayStr);
        System.out.println(weeklyNewspaperDayStr);
    }

    public boolean requiredItemValidator() {
        if (this.isEmpty(this.getNewspaperNameInputText().getValue())) {
            this.addFormattedMessage(this.getNewspaperNameInputText().getClientId(), "刊名必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getWeeklyNewspaperSelectManyCheckBox().isVisible() &&
                   isEmpty(this.getWeeklyNewspaperSelectManyCheckBox().getValue())) {
            this.addFormattedMessage(this.getWeeklyNewspaperSelectManyCheckBox().getClientId(), "日期必选",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.isEmpty(this.getPriceInputText().getValue())) {
            this.addFormattedMessage(this.getPriceInputText().getClientId(), "定价必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.isEmpty(this.getCnInputText().getValue())) {
            this.addFormattedMessage(this.getCnInputText().getClientId(), "CN必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.isEmpty(this.getIssnInputText().getValue())) {
            this.addFormattedMessage(this.getIssnInputText().getClientId(), "ISSN必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.isEmpty(this.getCompCodeInputText().getValue())) {
            this.addFormattedMessage(this.getCompCodeInputText().getClientId(), "主办单位", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (requiredItemValidator()) {
            Number categoryId = null;
            try {
                categoryId = (Number)this.resolveExpression("#{pageFlowScope.categoryId}");
            } catch (Exception e) {
                categoryId = new Number(-99);
                e.printStackTrace();
            }
            String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
            String result = this.getAdminAM().saveNewspaperSetup(categoryId, compCode);
            if ("S".equals(result)) {
                this.showMessage("保存成功");
                return "toReturn";
            } else {
                this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
            }
        }
        return null;
    }

    public String cancelAction() {
        this.getAdminAM().getDBTransaction().rollback();
        return "toReturn";
    }

    public void setWeeklyNewspaperSelectManyCheckBox(RichSelectManyCheckbox weeklyNewspaperSelectManyCheckBox) {
        this.weeklyNewspaperSelectManyCheckBox = weeklyNewspaperSelectManyCheckBox;
    }

    public RichSelectManyCheckbox getWeeklyNewspaperSelectManyCheckBox() {
        return weeklyNewspaperSelectManyCheckBox;
    }

    public void setPriceInputText(RichInputText priceInputText) {
        this.priceInputText = priceInputText;
    }

    public RichInputText getPriceInputText() {
        return priceInputText;
    }

    public void setCnInputText(RichInputText cnInputText) {
        this.cnInputText = cnInputText;
    }

    public RichInputText getCnInputText() {
        return cnInputText;
    }

    public void setIssnInputText(RichInputText issnInputText) {
        this.issnInputText = issnInputText;
    }

    public RichInputText getIssnInputText() {
        return issnInputText;
    }

    public void setCompCodeInputText(RichInputText compCodeInputText) {
        this.compCodeInputText = compCodeInputText;
    }

    public RichInputText getCompCodeInputText() {
        return compCodeInputText;
    }

    public void setNewspaperNameInputText(RichInputText newspaperNameInputText) {
        this.newspaperNameInputText = newspaperNameInputText;
    }

    public RichInputText getNewspaperNameInputText() {
        return newspaperNameInputText;
    }
}
