package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.copyright.CmsAuthorCopyrightTVORowImpl;
import com.zypg.cms.work.view.util.AttributePicker;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;

import oracle.jbo.domain.Number;


public class CopyrightAuthorEditManagedBean extends CustomManagedBean {
    private AttributePicker attributePicker = new AttributePicker();
    private RichSelectManyCheckbox areaSelectManyCheckBox;
    private RichSelectManyCheckbox langSelectManyCheckBox;
    private RichInputListOfValues bookNameInputListOfValues;
    private RichInputText bookNameInputText;
    private RichInputText authorInputText;
    private RichInputText editionInputText;
    private RichInputText publishingHouseInputText;
    private RichInputText priceInputText;
    private RichInputText copyrightRegistrationNumberInputText;
    private RichInputText isbnInputText;
    private RichInputDate pubTimeInputText;
    private RichInputText impressionInputText;
    private RichInputText ebookMinPriceInputText;
    private RichInputText ebookPriceInputText;
    private RichInputDate networkStartTimeInputDate;
    private RichInputDate networkEndTimeInputDate;
    private RichInputDate paperMediumStartTimeInputDate;
    private RichInputDate paperMediumEndTimeInputDate;
    private RichInputDate electronicStartTimeInputDate;
    private RichInputDate electronicEndTimeInputDate;
    private RichInputDate broadcastStartTimeInputDate;
    private RichInputDate broadcastEndTimeInputDate;
    private RichInputDate audioVideoStartTimeInputDate;
    private RichInputDate audioVideoEndTimeInputDate;

    public CopyrightAuthorEditManagedBean() {
    }

    public void preEditAuthor() {
        if (CommonUtil.getCopyrightAM().getDBTransaction().isDirty()) {
            CommonUtil.getCopyrightAM().getDBTransaction().rollback();
        }
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        CmsAuthorCopyrightTVORowImpl row =
            CommonUtil.getCopyrightAM().preEditAuthor(mode, docId, CommonUtil.getCompCode());
        this.setExpressionValue("#{pageFlowScope.docId}", row.getDocId());
        if (!mode.equals("CREATE")) {
            String[] area = row.getAuthorizedArea().split(",");
            String[] lang = row.getAuthorizedLang().split(",");
            this.setExpressionValue("#{pageFlowScope.area}", Arrays.asList(area));
            this.setExpressionValue("#{pageFlowScope.lang}", Arrays.asList(lang));
            this.setExpressionValue("#{pageFlowScope.toCreateBookFlag}", "true");
        } else {
            this.setExpressionValue("#{pageFlowScope.toCreateBookFlag}", "false");
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), "BOOK", "END_PROD_LIB");
    }

    public boolean requiredItemValidator() {
        if (this.getBookNameInputListOfValues().isVisible() &&
            this.getBookNameInputListOfValues().getValue() == null) {
            this.addFormattedMessage(this.getBookNameInputListOfValues().getClientId(), "书名不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getBookNameInputText().isVisible() && this.getBookNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getBookNameInputText().getClientId(), "书名不能为空", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getIsbnInputText().getValue() == null) {
            this.addFormattedMessage(this.getIsbnInputText().getClientId(), "ISBN不能为空", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (!CommonUtil.validateIsbn((String)this.getIsbnInputText().getValue())) {
            this.addFormattedMessage(this.getIsbnInputText().getClientId(), "ISBN不合法", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getAuthorInputText().getValue() == null) {
            this.addFormattedMessage(this.getAuthorInputText().getClientId(), "作者不能为空", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPubTimeInputText().getValue() == null) {
            this.addFormattedMessage(this.getPubTimeInputText().getClientId(), "出版时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getEditionInputText().getValue() == null) {
            this.addFormattedMessage(this.getEditionInputText().getClientId(), "版次不能为空", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getImpressionInputText().getValue() == null) {
            this.addFormattedMessage(this.getImpressionInputText().getClientId(), "印次不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPublishingHouseInputText().getValue() == null) {
            this.addFormattedMessage(this.getPublishingHouseInputText().getClientId(), "出版社不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPriceInputText().getValue() == null) {
            this.addFormattedMessage(this.getPriceInputText().getClientId(), "纸书定价不能为空", FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getAreaSelectManyCheckBox().getValue() == null) {
            this.addFormattedMessage(this.getAreaSelectManyCheckBox().getClientId(), "至少需要选择一个授权地区",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getLangSelectManyCheckBox().getValue() == null) {
            this.addFormattedMessage(this.getLangSelectManyCheckBox().getClientId(), "至少需要选择一种授权语言",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getEbookMinPriceInputText().getValue() == null) {
            this.addFormattedMessage(this.getEbookMinPriceInputText().getClientId(), "电子书最低定价不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getEbookPriceInputText().getValue() == null) {
            this.addFormattedMessage(this.getEbookPriceInputText().getClientId(), "电子书定价不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getCopyrightRegistrationNumberInputText().getValue() == null) {
            this.addFormattedMessage(this.getCopyrightRegistrationNumberInputText().getClientId(), "版权登记号不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getNetworkStartTimeInputDate().isVisible() &&
                   this.getNetworkStartTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getNetworkStartTimeInputDate().getClientId(), "开始时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getNetworkEndTimeInputDate().isVisible() &&
                   this.getNetworkEndTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getNetworkEndTimeInputDate().getClientId(), "结束时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPaperMediumStartTimeInputDate().isVisible() &&
                   this.getPaperMediumStartTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getPaperMediumStartTimeInputDate().getClientId(), "开始时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getPaperMediumEndTimeInputDate().isVisible() &&
                   this.getPaperMediumEndTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getPaperMediumEndTimeInputDate().getClientId(), "结束时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getElectronicStartTimeInputDate().isVisible() &&
                   this.getElectronicStartTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getElectronicStartTimeInputDate().getClientId(), "开始时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getElectronicEndTimeInputDate().isVisible() &&
                   this.getElectronicEndTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getElectronicEndTimeInputDate().getClientId(), "结束时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getBroadcastStartTimeInputDate().isVisible() &&
                   this.getBroadcastStartTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getBroadcastStartTimeInputDate().getClientId(), "开始时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getBroadcastEndTimeInputDate().isVisible() &&
                   this.getBroadcastEndTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getBroadcastEndTimeInputDate().getClientId(), "结束时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getAudioVideoStartTimeInputDate().isVisible() &&
                   this.getAudioVideoStartTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getAudioVideoStartTimeInputDate().getClientId(), "开始时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getAudioVideoEndTimeInputDate().isVisible() &&
                   this.getAudioVideoEndTimeInputDate().getValue() == null) {
            this.addFormattedMessage(this.getAudioVideoEndTimeInputDate().getClientId(), "结束时间不能为空",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (requiredItemValidator()) {
            CmsAuthorCopyrightTVORowImpl authorCopyrightRow =
                (CmsAuthorCopyrightTVORowImpl)CommonUtil.getCopyrightAM().getCmsAuthorCopyrightTVO().getCurrentRow();
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            if ("CREATE".equals(mode)) {
                String toCreateBookFlag = (String)this.resolveExpression("#{pageFlowScope.toCreateBookFlag}");
                System.out.println("toCreateBookFlag=" + toCreateBookFlag);
                if ("true".equals(toCreateBookFlag)) {
                    CommonUtil.getCopyrightAM().createBookWhenCreateAuthorCopyright(CommonUtil.getCompCode());
                } else {
                    CommonUtil.getCopyrightAM().updateBookWhenCreateAuthorCopyright();
                }
            } else {
                CommonUtil.getCopyrightAM().updateBookWhenCreateAuthorCopyright();
            }
            String area = "";
            String lang = "";
            RichSelectManyCheckbox box = this.getAreaSelectManyCheckBox();
            System.out.println("box.getValue()=" + box.getValue());
            List areaList = (List)box.getValue();
            if (areaList != null) {
                for (int i = 0; i < areaList.size(); i++) {
                    if (i < areaList.size() - 1) {
                        if (null != areaList.get(i))
                            area += areaList.get(i) + ",";
                    } else if (i == areaList.size() - 1) {
                        if (null != areaList.get(i))
                            area += areaList.get(i);
                    }
                }
            }

            RichSelectManyCheckbox langBox = this.getLangSelectManyCheckBox();
            List langList = (List)langBox.getValue();
            if (langList != null) {
                for (int i = 0; i < langList.size(); i++) {
                    if (i < langList.size() - 1) {
                        if (null != langList.get(i))
                            lang += langList.get(i) + ",";
                    } else if (i == langList.size() - 1) {
                        if (null != langList.get(i))
                            lang += langList.get(i);
                    }
                }
            }
            authorCopyrightRow.setAuthorizedArea(area);
            authorCopyrightRow.setAuthorizedLang(lang);
            CommonUtil.getCopyrightAM().getTransaction().commit();
            CommonUtil.getCopyrightAM().findCategory4AuthorCopyright((String)this.resolveExpression("#{pageFlowScope.compCode}"),
                                                                     CommonUtil.getLibCode());
            this.appendScript("saveExtendAttrs()");
            return null;
        }
        return null;
    }


    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getCopyrightAM().getTransaction().rollback();
    }

    public void isToCreateBookValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Boolean oldValue = (Boolean)valueChangeEvent.getOldValue();
        Boolean newValue = (Boolean)valueChangeEvent.getNewValue();
        if ((oldValue == null && newValue) || oldValue != null) {
            Boolean toCreateBookFlag = (Boolean)valueChangeEvent.getNewValue();
            this.setExpressionValue("#{pageFlowScope.toCreateBookFlag}", toCreateBookFlag.toString());
            CommonUtil.getCopyrightAM().initBookEdit4Copyright();
        }
    }

    public void setAttributePicker(AttributePicker attributePicker) {
        this.attributePicker = attributePicker;
    }

    public AttributePicker getAttributePicker() {
        return attributePicker;
    }

    public void setAreaSelectManyCheckBox(RichSelectManyCheckbox areaSelectManyCheckBox) {
        this.areaSelectManyCheckBox = areaSelectManyCheckBox;
    }

    public RichSelectManyCheckbox getAreaSelectManyCheckBox() {
        return areaSelectManyCheckBox;
    }

    public void setLangSelectManyCheckBox(RichSelectManyCheckbox langSelectManyCheckBox) {
        this.langSelectManyCheckBox = langSelectManyCheckBox;
    }

    public RichSelectManyCheckbox getLangSelectManyCheckBox() {
        return langSelectManyCheckBox;
    }

    public void setBookNameInputListOfValues(RichInputListOfValues bookNameInputListOfValues) {
        this.bookNameInputListOfValues = bookNameInputListOfValues;
    }

    public RichInputListOfValues getBookNameInputListOfValues() {
        return bookNameInputListOfValues;
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

    public void setEditionInputText(RichInputText editionInputText) {
        this.editionInputText = editionInputText;
    }

    public RichInputText getEditionInputText() {
        return editionInputText;
    }

    public void setPublishingHouseInputText(RichInputText publishingHouseInputText) {
        this.publishingHouseInputText = publishingHouseInputText;
    }

    public RichInputText getPublishingHouseInputText() {
        return publishingHouseInputText;
    }

    public void setPriceInputText(RichInputText priceInputText) {
        this.priceInputText = priceInputText;
    }

    public RichInputText getPriceInputText() {
        return priceInputText;
    }

    public void setCopyrightRegistrationNumberInputText(RichInputText copyrightRegistrationNumberInputText) {
        this.copyrightRegistrationNumberInputText = copyrightRegistrationNumberInputText;
    }

    public RichInputText getCopyrightRegistrationNumberInputText() {
        return copyrightRegistrationNumberInputText;
    }

    public void setIsbnInputText(RichInputText isbnInputText) {
        this.isbnInputText = isbnInputText;
    }

    public RichInputText getIsbnInputText() {
        return isbnInputText;
    }

    public void setPubTimeInputText(RichInputDate pubTimeInputText) {
        this.pubTimeInputText = pubTimeInputText;
    }

    public RichInputDate getPubTimeInputText() {
        return pubTimeInputText;
    }

    public void setImpressionInputText(RichInputText impressionInputText) {
        this.impressionInputText = impressionInputText;
    }

    public RichInputText getImpressionInputText() {
        return impressionInputText;
    }

    public void setEbookMinPriceInputText(RichInputText ebookMinPriceInputText) {
        this.ebookMinPriceInputText = ebookMinPriceInputText;
    }

    public RichInputText getEbookMinPriceInputText() {
        return ebookMinPriceInputText;
    }

    public void setEbookPriceInputText(RichInputText ebookPriceInputText) {
        this.ebookPriceInputText = ebookPriceInputText;
    }

    public RichInputText getEbookPriceInputText() {
        return ebookPriceInputText;
    }

    public void setNetworkStartTimeInputDate(RichInputDate networkStartTimeInputDate) {
        this.networkStartTimeInputDate = networkStartTimeInputDate;
    }

    public RichInputDate getNetworkStartTimeInputDate() {
        return networkStartTimeInputDate;
    }

    public void setNetworkEndTimeInputDate(RichInputDate networkEndTimeInputDate) {
        this.networkEndTimeInputDate = networkEndTimeInputDate;
    }

    public RichInputDate getNetworkEndTimeInputDate() {
        return networkEndTimeInputDate;
    }

    public void setPaperMediumStartTimeInputDate(RichInputDate paperMediumStartTimeInputDate) {
        this.paperMediumStartTimeInputDate = paperMediumStartTimeInputDate;
    }

    public RichInputDate getPaperMediumStartTimeInputDate() {
        return paperMediumStartTimeInputDate;
    }

    public void setPaperMediumEndTimeInputDate(RichInputDate paperMediumEndTimeInputDate) {
        this.paperMediumEndTimeInputDate = paperMediumEndTimeInputDate;
    }

    public RichInputDate getPaperMediumEndTimeInputDate() {
        return paperMediumEndTimeInputDate;
    }

    public void setElectronicStartTimeInputDate(RichInputDate electronicStartTimeInputDate) {
        this.electronicStartTimeInputDate = electronicStartTimeInputDate;
    }

    public RichInputDate getElectronicStartTimeInputDate() {
        return electronicStartTimeInputDate;
    }

    public void setElectronicEndTimeInputDate(RichInputDate electronicEndTimeInputDate) {
        this.electronicEndTimeInputDate = electronicEndTimeInputDate;
    }

    public RichInputDate getElectronicEndTimeInputDate() {
        return electronicEndTimeInputDate;
    }

    public void setBroadcastStartTimeInputDate(RichInputDate broadcastStartTimeInputDate) {
        this.broadcastStartTimeInputDate = broadcastStartTimeInputDate;
    }

    public RichInputDate getBroadcastStartTimeInputDate() {
        return broadcastStartTimeInputDate;
    }

    public void setBroadcastEndTimeInputDate(RichInputDate broadcastEndTimeInputDate) {
        this.broadcastEndTimeInputDate = broadcastEndTimeInputDate;
    }

    public RichInputDate getBroadcastEndTimeInputDate() {
        return broadcastEndTimeInputDate;
    }

    public void setAudioVideoStartTimeInputDate(RichInputDate audioVideoStartTimeInputDate) {
        this.audioVideoStartTimeInputDate = audioVideoStartTimeInputDate;
    }

    public RichInputDate getAudioVideoStartTimeInputDate() {
        return audioVideoStartTimeInputDate;
    }

    public void setAudioVideoEndTimeInputDate(RichInputDate audioVideoEndTimeInputDate) {
        this.audioVideoEndTimeInputDate = audioVideoEndTimeInputDate;
    }

    public RichInputDate getAudioVideoEndTimeInputDate() {
        return audioVideoEndTimeInputDate;
    }

    public void operDocCategroupRel(String flag, String copyrightType) {
        Number categoryId = CommonUtil.getCopyrightAM().getCategoryIdByAttribute1(copyrightType);
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        if ("YOU".equals(flag)) {
            CommonUtil.getCopyrightAM().updateCategroupRel(docId, categoryId);
        } else {
            CommonUtil.getCopyrightAM().deleteDocCategroupRel(docId, categoryId);
        }
    }

    public void networkCopyrightFlagValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{bindings.NetworkStartTime.inputValue}", null);
        this.setExpressionValue("#{bindings.NetworkEndTime.inputValue}", null);
        String networkCopyrightFlag = (String)valueChangeEvent.getNewValue();
        operDocCategroupRel(networkCopyrightFlag, "NETWORK");
    }

    public void paperMediumCopyrightFlagValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{bindings.PaperMediumStartTime.inputValue}", null);
        this.setExpressionValue("#{bindings.PaperMediumEndTime.inputValue}", null);
        String paperMediumCopyrightFlag = (String)valueChangeEvent.getNewValue();
        operDocCategroupRel(paperMediumCopyrightFlag, "PAPER");
    }

    public void electronicCopyrightFlagValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{bindings.ElectronicStartTime.inputValue}", null);
        this.setExpressionValue("#{bindings.ElectronicEndTime.inputValue}", null);
        String electronicCopyrightFlag = (String)valueChangeEvent.getNewValue();
        operDocCategroupRel(electronicCopyrightFlag, "ELEC");
    }

    public void broadcastCopyrightFlagValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{bindings.BroadcastStartTime.inputValue}", null);
        this.setExpressionValue("#{bindings.BroadcastEndTime.inputValue}", null);
        String broadcastCopyrightFlag = (String)valueChangeEvent.getNewValue();
        operDocCategroupRel(broadcastCopyrightFlag, "BROADCAST");
    }

    public void audioVideoCopyrightFlagValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{bindings.AudioVideoStartTime.inputValue}", null);
        this.setExpressionValue("#{bindings.AudioVideoEndTime.inputValue}", null);
        String audioVideoCopyrightFlag = (String)valueChangeEvent.getNewValue();
        operDocCategroupRel(audioVideoCopyrightFlag, "MEDIA");
    }
}
