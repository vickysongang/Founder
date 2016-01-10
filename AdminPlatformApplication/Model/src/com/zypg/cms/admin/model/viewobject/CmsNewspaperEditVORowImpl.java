package com.zypg.cms.admin.model.viewobject;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Aug 27 20:51:12 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsNewspaperEditVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        NewspaperName {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getNewspaperName();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setNewspaperName((String)value);
            }
        }
        ,
        NewspaperType {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getNewspaperType();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setNewspaperType((String)value);
            }
        }
        ,
        PaperType {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getPaperType();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setPaperType((String)value);
            }
        }
        ,
        Lang {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLang();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setLang((String)value);
            }
        }
        ,
        ChiefEditor {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getChiefEditor();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setChiefEditor((String)value);
            }
        }
        ,
        PublishedDate {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getPublishedDate();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setPublishedDate((String)value);
            }
        }
        ,
        Price {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getPrice();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setPrice((Number)value);
            }
        }
        ,
        Cn {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getCn();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setCn((String)value);
            }
        }
        ,
        Issn {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getIssn();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setIssn((String)value);
            }
        }
        ,
        BookSize {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getBookSize();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setBookSize((String)value);
            }
        }
        ,
        Words {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getWords();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setWords((Number)value);
            }
        }
        ,
        ProdSize {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getProdSize();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setProdSize((String)value);
            }
        }
        ,
        ReaderGroup {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getReaderGroup();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setReaderGroup((String)value);
            }
        }
        ,
        Comp {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getComp();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setComp((String)value);
            }
        }
        ,
        Org {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getOrg();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setOrg((String)value);
            }
        }
        ,
        Publishing {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getPublishing();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setPublishing((String)value);
            }
        }
        ,
        Description {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getDescription();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setDescription((String)value);
            }
        }
        ,
        Weekly1 {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getWeekly1();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setWeekly1((String)value);
            }
        }
        ,
        Monthly {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getMonthly();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setMonthly((String)value);
            }
        }
        ,
        OldEven {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getOldEven();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setOldEven((String)value);
            }
        }
        ,
        FirstTenDays {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getFirstTenDays();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setFirstTenDays((String)value);
            }
        }
        ,
        SecondTenDays {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getSecondTenDays();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setSecondTenDays((String)value);
            }
        }
        ,
        ThirdTenDays {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getThirdTenDays();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setThirdTenDays((String)value);
            }
        }
        ,
        Quarterly {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getQuarterly();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setQuarterly((String)value);
            }
        }
        ,
        FisrtHalfMonth {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getFisrtHalfMonth();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setFisrtHalfMonth((String)value);
            }
        }
        ,
        SecondHalfMonth {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getSecondHalfMonth();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setSecondHalfMonth((String)value);
            }
        }
        ,
        Weekly2 {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getWeekly2();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setWeekly2((String)value);
            }
        }
        ,
        Lookup4NewspaperType {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLookup4NewspaperType();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4PaperType {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLookup4PaperType();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4Lang {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLookup4Lang();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4BookSize {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLookup4BookSize();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4ProdSize {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLookup4ProdSize();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        Lookup4ReaderGroup {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getLookup4ReaderGroup();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        WeekVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getWeekVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        MonthVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getMonthVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        OddEvenVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getOddEvenVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        FisrtTenDaysVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getFisrtTenDaysVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        SecondTenDaysVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getSecondTenDaysVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        ThirdTenDaysVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getThirdTenDaysVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        QuarterlyVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getQuarterlyVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        FisrtHalfMonthVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getFisrtHalfMonthVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        SecondHalfMonthVO {
            public Object get(CmsNewspaperEditVORowImpl obj) {
                return obj.getSecondHalfMonthVO();
            }

            public void put(CmsNewspaperEditVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsNewspaperEditVORowImpl object);

        public abstract void put(CmsNewspaperEditVORowImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int NEWSPAPERNAME = AttributesEnum.NewspaperName.index();
    public static final int NEWSPAPERTYPE = AttributesEnum.NewspaperType.index();
    public static final int PAPERTYPE = AttributesEnum.PaperType.index();
    public static final int LANG = AttributesEnum.Lang.index();
    public static final int CHIEFEDITOR = AttributesEnum.ChiefEditor.index();
    public static final int PUBLISHEDDATE = AttributesEnum.PublishedDate.index();
    public static final int PRICE = AttributesEnum.Price.index();
    public static final int CN = AttributesEnum.Cn.index();
    public static final int ISSN = AttributesEnum.Issn.index();
    public static final int BOOKSIZE = AttributesEnum.BookSize.index();
    public static final int WORDS = AttributesEnum.Words.index();
    public static final int PRODSIZE = AttributesEnum.ProdSize.index();
    public static final int READERGROUP = AttributesEnum.ReaderGroup.index();
    public static final int COMP = AttributesEnum.Comp.index();
    public static final int ORG = AttributesEnum.Org.index();
    public static final int PUBLISHING = AttributesEnum.Publishing.index();
    public static final int DESCRIPTION = AttributesEnum.Description.index();
    public static final int WEEKLY1 = AttributesEnum.Weekly1.index();
    public static final int MONTHLY = AttributesEnum.Monthly.index();
    public static final int OLDEVEN = AttributesEnum.OldEven.index();
    public static final int FIRSTTENDAYS = AttributesEnum.FirstTenDays.index();
    public static final int SECONDTENDAYS = AttributesEnum.SecondTenDays.index();
    public static final int THIRDTENDAYS = AttributesEnum.ThirdTenDays.index();
    public static final int QUARTERLY = AttributesEnum.Quarterly.index();
    public static final int FISRTHALFMONTH = AttributesEnum.FisrtHalfMonth.index();
    public static final int SECONDHALFMONTH = AttributesEnum.SecondHalfMonth.index();
    public static final int WEEKLY2 = AttributesEnum.Weekly2.index();
    public static final int LOOKUP4NEWSPAPERTYPE = AttributesEnum.Lookup4NewspaperType.index();
    public static final int LOOKUP4PAPERTYPE = AttributesEnum.Lookup4PaperType.index();
    public static final int LOOKUP4LANG = AttributesEnum.Lookup4Lang.index();
    public static final int LOOKUP4BOOKSIZE = AttributesEnum.Lookup4BookSize.index();
    public static final int LOOKUP4PRODSIZE = AttributesEnum.Lookup4ProdSize.index();
    public static final int LOOKUP4READERGROUP = AttributesEnum.Lookup4ReaderGroup.index();
    public static final int WEEKVO = AttributesEnum.WeekVO.index();
    public static final int MONTHVO = AttributesEnum.MonthVO.index();
    public static final int ODDEVENVO = AttributesEnum.OddEvenVO.index();
    public static final int FISRTTENDAYSVO = AttributesEnum.FisrtTenDaysVO.index();
    public static final int SECONDTENDAYSVO = AttributesEnum.SecondTenDaysVO.index();
    public static final int THIRDTENDAYSVO = AttributesEnum.ThirdTenDaysVO.index();
    public static final int QUARTERLYVO = AttributesEnum.QuarterlyVO.index();
    public static final int FISRTHALFMONTHVO = AttributesEnum.FisrtHalfMonthVO.index();
    public static final int SECONDHALFMONTHVO = AttributesEnum.SecondHalfMonthVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsNewspaperEditVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperName.
     * @return the NewspaperName
     */
    public String getNewspaperName() {
        return (String) getAttributeInternal(NEWSPAPERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperName.
     * @param value value to set the  NewspaperName
     */
    public void setNewspaperName(String value) {
        setAttributeInternal(NEWSPAPERNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NewspaperType.
     * @return the NewspaperType
     */
    public String getNewspaperType() {
        return (String) getAttributeInternal(NEWSPAPERTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NewspaperType.
     * @param value value to set the  NewspaperType
     */
    public void setNewspaperType(String value) {
        setAttributeInternal(NEWSPAPERTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PaperType.
     * @return the PaperType
     */
    public String getPaperType() {
        return (String) getAttributeInternal(PAPERTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PaperType.
     * @param value value to set the  PaperType
     */
    public void setPaperType(String value) {
        setAttributeInternal(PAPERTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Lang.
     * @return the Lang
     */
    public String getLang() {
        return (String) getAttributeInternal(LANG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Lang.
     * @param value value to set the  Lang
     */
    public void setLang(String value) {
        setAttributeInternal(LANG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ChiefEditor.
     * @return the ChiefEditor
     */
    public String getChiefEditor() {
        return (String) getAttributeInternal(CHIEFEDITOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ChiefEditor.
     * @param value value to set the  ChiefEditor
     */
    public void setChiefEditor(String value) {
        setAttributeInternal(CHIEFEDITOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PublishedDate.
     * @return the PublishedDate
     */
    public String getPublishedDate() {
        return (String) getAttributeInternal(PUBLISHEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PublishedDate.
     * @param value value to set the  PublishedDate
     */
    public void setPublishedDate(String value) {
        setAttributeInternal(PUBLISHEDDATE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Price.
     * @return the Price
     */
    public Number getPrice() {
        return (Number) getAttributeInternal(PRICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Price.
     * @param value value to set the  Price
     */
    public void setPrice(Number value) {
        setAttributeInternal(PRICE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Cn.
     * @return the Cn
     */
    public String getCn() {
        return (String) getAttributeInternal(CN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Cn.
     * @param value value to set the  Cn
     */
    public void setCn(String value) {
        setAttributeInternal(CN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Issn.
     * @return the Issn
     */
    public String getIssn() {
        return (String) getAttributeInternal(ISSN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Issn.
     * @param value value to set the  Issn
     */
    public void setIssn(String value) {
        setAttributeInternal(ISSN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BookSize.
     * @return the BookSize
     */
    public String getBookSize() {
        return (String) getAttributeInternal(BOOKSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BookSize.
     * @param value value to set the  BookSize
     */
    public void setBookSize(String value) {
        setAttributeInternal(BOOKSIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Words.
     * @return the Words
     */
    public Number getWords() {
        return (Number) getAttributeInternal(WORDS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Words.
     * @param value value to set the  Words
     */
    public void setWords(Number value) {
        setAttributeInternal(WORDS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProdSize.
     * @return the ProdSize
     */
    public String getProdSize() {
        return (String) getAttributeInternal(PRODSIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProdSize.
     * @param value value to set the  ProdSize
     */
    public void setProdSize(String value) {
        setAttributeInternal(PRODSIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ReaderGroup.
     * @return the ReaderGroup
     */
    public String getReaderGroup() {
        return (String) getAttributeInternal(READERGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ReaderGroup.
     * @param value value to set the  ReaderGroup
     */
    public void setReaderGroup(String value) {
        setAttributeInternal(READERGROUP, value);
    }


    /**
     * Gets the attribute value for the calculated attribute Comp.
     * @return the Comp
     */
    public String getComp() {
        return (String) getAttributeInternal(COMP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Comp.
     * @param value value to set the  Comp
     */
    public void setComp(String value) {
        setAttributeInternal(COMP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Org.
     * @return the Org
     */
    public String getOrg() {
        return (String) getAttributeInternal(ORG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Org.
     * @param value value to set the  Org
     */
    public void setOrg(String value) {
        setAttributeInternal(ORG, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Publishing.
     * @return the Publishing
     */
    public String getPublishing() {
        return (String) getAttributeInternal(PUBLISHING);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Publishing.
     * @param value value to set the  Publishing
     */
    public void setPublishing(String value) {
        setAttributeInternal(PUBLISHING, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Description.
     * @return the Description
     */
    public String getDescription() {
        return (String) getAttributeInternal(DESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Description.
     * @param value value to set the  Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Weekly1.
     * @return the Weekly1
     */
    public String getWeekly1() {
        return (String) getAttributeInternal(WEEKLY1);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Weekly1.
     * @param value value to set the  Weekly1
     */
    public void setWeekly1(String value) {
        setAttributeInternal(WEEKLY1, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Monthly.
     * @return the Monthly
     */
    public String getMonthly() {
        return (String) getAttributeInternal(MONTHLY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Monthly.
     * @param value value to set the  Monthly
     */
    public void setMonthly(String value) {
        setAttributeInternal(MONTHLY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OldEven.
     * @return the OldEven
     */
    public String getOldEven() {
        return (String) getAttributeInternal(OLDEVEN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OldEven.
     * @param value value to set the  OldEven
     */
    public void setOldEven(String value) {
        setAttributeInternal(OLDEVEN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FirstTenDays.
     * @return the FirstTenDays
     */
    public String getFirstTenDays() {
        return (String) getAttributeInternal(FIRSTTENDAYS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FirstTenDays.
     * @param value value to set the  FirstTenDays
     */
    public void setFirstTenDays(String value) {
        setAttributeInternal(FIRSTTENDAYS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SecondTenDays.
     * @return the SecondTenDays
     */
    public String getSecondTenDays() {
        return (String) getAttributeInternal(SECONDTENDAYS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SecondTenDays.
     * @param value value to set the  SecondTenDays
     */
    public void setSecondTenDays(String value) {
        setAttributeInternal(SECONDTENDAYS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ThirdTenDays.
     * @return the ThirdTenDays
     */
    public String getThirdTenDays() {
        return (String) getAttributeInternal(THIRDTENDAYS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ThirdTenDays.
     * @param value value to set the  ThirdTenDays
     */
    public void setThirdTenDays(String value) {
        setAttributeInternal(THIRDTENDAYS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Quarterly.
     * @return the Quarterly
     */
    public String getQuarterly() {
        return (String) getAttributeInternal(QUARTERLY);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Quarterly.
     * @param value value to set the  Quarterly
     */
    public void setQuarterly(String value) {
        setAttributeInternal(QUARTERLY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FisrtHalfMonth.
     * @return the FisrtHalfMonth
     */
    public String getFisrtHalfMonth() {
        return (String) getAttributeInternal(FISRTHALFMONTH);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FisrtHalfMonth.
     * @param value value to set the  FisrtHalfMonth
     */
    public void setFisrtHalfMonth(String value) {
        setAttributeInternal(FISRTHALFMONTH, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SecondHalfMonth.
     * @return the SecondHalfMonth
     */
    public String getSecondHalfMonth() {
        return (String) getAttributeInternal(SECONDHALFMONTH);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SecondHalfMonth.
     * @param value value to set the  SecondHalfMonth
     */
    public void setSecondHalfMonth(String value) {
        setAttributeInternal(SECONDHALFMONTH, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Weekly2.
     * @return the Weekly2
     */
    public String getWeekly2() {
        return (String) getAttributeInternal(WEEKLY2);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Weekly2.
     * @param value value to set the  Weekly2
     */
    public void setWeekly2(String value) {
        setAttributeInternal(WEEKLY2, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4NewspaperType.
     */
    public RowSet getLookup4NewspaperType() {
        return (RowSet)getAttributeInternal(LOOKUP4NEWSPAPERTYPE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4PaperType.
     */
    public RowSet getLookup4PaperType() {
        return (RowSet)getAttributeInternal(LOOKUP4PAPERTYPE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4Lang.
     */
    public RowSet getLookup4Lang() {
        return (RowSet)getAttributeInternal(LOOKUP4LANG);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4BookSize.
     */
    public RowSet getLookup4BookSize() {
        return (RowSet)getAttributeInternal(LOOKUP4BOOKSIZE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4ProdSize.
     */
    public RowSet getLookup4ProdSize() {
        return (RowSet)getAttributeInternal(LOOKUP4PRODSIZE);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4ReaderGroup.
     */
    public RowSet getLookup4ReaderGroup() {
        return (RowSet)getAttributeInternal(LOOKUP4READERGROUP);
    }

    /**
     * Gets the view accessor <code>RowSet</code> WeekVO.
     */
    public RowSet getWeekVO() {
        return (RowSet)getAttributeInternal(WEEKVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> MonthVO.
     */
    public RowSet getMonthVO() {
        return (RowSet)getAttributeInternal(MONTHVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> OddEvenVO.
     */
    public RowSet getOddEvenVO() {
        return (RowSet)getAttributeInternal(ODDEVENVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> FisrtTenDaysVO.
     */
    public RowSet getFisrtTenDaysVO() {
        return (RowSet)getAttributeInternal(FISRTTENDAYSVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> SecondTenDaysVO.
     */
    public RowSet getSecondTenDaysVO() {
        return (RowSet)getAttributeInternal(SECONDTENDAYSVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> ThirdTenDaysVO.
     */
    public RowSet getThirdTenDaysVO() {
        return (RowSet)getAttributeInternal(THIRDTENDAYSVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> QuarterlyVO.
     */
    public RowSet getQuarterlyVO() {
        return (RowSet)getAttributeInternal(QUARTERLYVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> FisrtHalfMonthVO.
     */
    public RowSet getFisrtHalfMonthVO() {
        return (RowSet)getAttributeInternal(FISRTHALFMONTHVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> SecondHalfMonthVO.
     */
    public RowSet getSecondHalfMonthVO() {
        return (RowSet)getAttributeInternal(SECONDHALFMONTHVO);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}