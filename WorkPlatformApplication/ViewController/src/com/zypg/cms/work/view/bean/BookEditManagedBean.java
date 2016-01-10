package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.book.query.CmsBookValidateVORowImpl;
import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;


public class BookEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputText isbnInput;
    private RichInputText bookNameInputText;
    private RichInputText authorInputText;
    private RichInputText publishingHouseInputText;
    private RichInputText impressionInputText;
    private RichInputDate pubTimeInputDate;
    private RichInputText priceInputText;
    private RichInputText editionInputText;

    public BookEditManagedBean() {

    }

    public void preEditBook() {
        if (CommonUtil.getBookAM().getDBTransaction().isDirty()) {
            CommonUtil.getBookAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            Number newDocId =
                CommonUtil.getBookAM().preEditBook(mode, docId, CommonUtil.getCompCode(), CommonUtil.getCompName());
            this.setExpressionValue("#{pageFlowScope.docId}", newDocId);
        } else {
            CommonUtil.getBookAM().initBookEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }


    public boolean itemValidator() {
        if (this.getBookNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getBookNameInputText().getClientId(), "图书名必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getIsbnInput().getValue() == null) {
            this.addFormattedMessage(this.getIsbnInput().getClientId(), "ISBN必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (!CommonUtil.validateIsbn((String)this.getIsbnInput().getValue())) {
            this.addFormattedMessage(this.getIsbnInput().getClientId(), "请输入合法的ISBN", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getAuthorInputText().getValue() == null) {
            this.addFormattedMessage(this.getAuthorInputText().getClientId(), "作者必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPublishingHouseInputText().getValue() == null) {
            this.addFormattedMessage(this.getPublishingHouseInputText().getClientId(), "出版社必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getImpressionInputText().getValue() == null) {
            this.addFormattedMessage(this.getImpressionInputText().getClientId(), "印次必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPubTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getPubTimeInputDate().getClientId(), "出版时间必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getEditionInputText().getValue() == null) {
            this.addFormattedMessage(this.getEditionInputText().getClientId(), "版次必填", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPriceInputText().getValue() == null) {
            this.addFormattedMessage(this.getPriceInputText().getClientId(), "定价必填", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if ("Y".equals(batchFlag)) {
            CommonUtil.getBookAM().batchEditBook((String)this.resolveExpression("#{pageFlowScope.docId}"));
            CommonUtil.getBookAM().getDBTransaction().commit();
            return "toReturn";
        } else {
            if (!itemValidator()) {
                return null;
            }
            Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            CmsBookValidateVORowImpl validateRow =
                (CmsBookValidateVORowImpl)CommonUtil.getBookAM().getBookRowByAltKey((String)this.getBookNameInputText().getValue(),
                                                                                    (String)this.getIsbnInput().getValue(),
                                                                                    (Number)this.getEditionInputText().getValue(),
                                                                                    (Number)this.getImpressionInputText().getValue(),
                                                                                    CommonUtil.getCompCode());
            if (validateRow != null && !validateRow.getDocId().equals(docId)) {
                this.addFormattedMessage(null, "此书已存在", FacesMessage.SEVERITY_ERROR);
                return null;
            }
            CommonUtil.getBookAM().getDBTransaction().commit();
            CommonUtil.getBookAM().updateAssortCategoryWhenBookCategoryUpdated(docId, null);
            System.out.println("保存扩展字段saveExtendAttrs");
            this.appendScript("saveExtendAttrs()");
            return null;
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getBookAM().getDBTransaction().rollback();
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setIsbnInput(RichInputText isbnInput) {
        this.isbnInput = isbnInput;
    }

    public RichInputText getIsbnInput() {
        return isbnInput;
    }

    public void setBookNameInputText(RichInputText bookNameInputText) {
        this.bookNameInputText = bookNameInputText;
    }

    public RichInputText getBookNameInputText() {
        return bookNameInputText;
    }

    public void setAuthorInputText(RichInputText authorInputText) {
        this.authorInputText = authorInputText;
    }

    public RichInputText getAuthorInputText() {
        return authorInputText;
    }

    public void setPublishingHouseInputText(RichInputText publishingHouseInputText) {
        this.publishingHouseInputText = publishingHouseInputText;
    }

    public RichInputText getPublishingHouseInputText() {
        return publishingHouseInputText;
    }

    public void setImpressionInputText(RichInputText impressionInputText) {
        this.impressionInputText = impressionInputText;
    }

    public RichInputText getImpressionInputText() {
        return impressionInputText;
    }

    public void setPubTimeInputDate(RichInputDate pubTimeInputDate) {
        this.pubTimeInputDate = pubTimeInputDate;
    }

    public RichInputDate getPubTimeInputDate() {
        return pubTimeInputDate;
    }

    public void setPriceInputText(RichInputText priceInputText) {
        this.priceInputText = priceInputText;
    }

    public RichInputText getPriceInputText() {
        return priceInputText;
    }

    public void setEditionInputText(RichInputText editionInputText) {
        this.editionInputText = editionInputText;
    }

    public RichInputText getEditionInputText() {
        return editionInputText;
    }
}
