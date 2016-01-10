package com.zypg.cms.work.model.viewobject.theme.origin;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Hashtable;
import java.util.List;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Aug 10 19:29:32 CST 2014
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsPhotographyFigureOriginVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        FigureId {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureId();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getDocId();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        ThumbnailUrl {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getThumbnailUrl();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setThumbnailUrl((String)value);
            }
        }
        ,
        FigureName {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureName();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureName((String)value);
            }
        }
        ,
        SeriesName {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getSeriesName();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setSeriesName((String)value);
            }
        }
        ,
        Photographer {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getPhotographer();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setPhotographer((String)value);
            }
        }
        ,
        PhotographyType {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getPhotographyType();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setPhotographyType((String)value);
            }
        }
        ,
        PhotographyTime {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getPhotographyTime();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setPhotographyTime((Date)value);
            }
        }
        ,
        PhotographyLocation {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getPhotographyLocation();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setPhotographyLocation((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        FigureDesc {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureDesc();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureDesc((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        FigureFormat {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureFormat();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureFormat((String)value);
            }
        }
        ,
        FigureSize {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureSize();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureSize((String)value);
            }
        }
        ,
        FigureDimension {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureDimension();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureDimension((String)value);
            }
        }
        ,
        FigureResolution {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureResolution();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureResolution((String)value);
            }
        }
        ,
        FigureXResolution {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureXResolution();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureXResolution((String)value);
            }
        }
        ,
        FigureYResolution {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureYResolution();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureYResolution((String)value);
            }
        }
        ,
        CoverDesign {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getCoverDesign();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setCoverDesign((String)value);
            }
        }
        ,
        Checked {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getChecked();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setChecked((Boolean)value);
            }
        }
        ,
        CategoryId {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getCategoryId();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setCategoryId((Number)value);
            }
        }
        ,
        CategoryName {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getCategoryName();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setCategoryName((String)value);
            }
        }
        ,
        FigureWidth {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureWidth();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureWidth((String)value);
            }
        }
        ,
        FigureHeight {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getFigureHeight();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setFigureHeight((String)value);
            }
        }
        ,
        Status {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getStatus();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        CompCode {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getCompCode();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setCompCode((String)value);
            }
        }
        ,
        CompName {
            public Object get(CmsPhotographyFigureOriginVORowImpl obj) {
                return obj.getCompName();
            }

            public void put(CmsPhotographyFigureOriginVORowImpl obj, Object value) {
                obj.setCompName((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsPhotographyFigureOriginVORowImpl object);

        public abstract void put(CmsPhotographyFigureOriginVORowImpl object, Object value);

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


    public static final int FIGUREID = AttributesEnum.FigureId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int THUMBNAILURL = AttributesEnum.ThumbnailUrl.index();
    public static final int FIGURENAME = AttributesEnum.FigureName.index();
    public static final int SERIESNAME = AttributesEnum.SeriesName.index();
    public static final int PHOTOGRAPHER = AttributesEnum.Photographer.index();
    public static final int PHOTOGRAPHYTYPE = AttributesEnum.PhotographyType.index();
    public static final int PHOTOGRAPHYTIME = AttributesEnum.PhotographyTime.index();
    public static final int PHOTOGRAPHYLOCATION = AttributesEnum.PhotographyLocation.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int FIGUREDESC = AttributesEnum.FigureDesc.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int FIGUREFORMAT = AttributesEnum.FigureFormat.index();
    public static final int FIGURESIZE = AttributesEnum.FigureSize.index();
    public static final int FIGUREDIMENSION = AttributesEnum.FigureDimension.index();
    public static final int FIGURERESOLUTION = AttributesEnum.FigureResolution.index();
    public static final int FIGUREXRESOLUTION = AttributesEnum.FigureXResolution.index();
    public static final int FIGUREYRESOLUTION = AttributesEnum.FigureYResolution.index();
    public static final int COVERDESIGN = AttributesEnum.CoverDesign.index();
    public static final int CHECKED = AttributesEnum.Checked.index();
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int CATEGORYNAME = AttributesEnum.CategoryName.index();
    public static final int FIGUREWIDTH = AttributesEnum.FigureWidth.index();
    public static final int FIGUREHEIGHT = AttributesEnum.FigureHeight.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int COMPCODE = AttributesEnum.CompCode.index();
    public static final int COMPNAME = AttributesEnum.CompName.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsPhotographyFigureOriginVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute FigureId.
     * @return the FigureId
     */
    public Number getFigureId() {
        return (Number)getAttributeInternal(FIGUREID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureId.
     * @param value value to set the  FigureId
     */
    public void setFigureId(Number value) {
        setAttributeInternal(FIGUREID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DocId.
     * @return the DocId
     */
    public Number getDocId() {
        return (Number)getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocId.
     * @param value value to set the  DocId
     */
    public void setDocId(Number value) {
        setAttributeInternal(DOCID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureName.
     * @return the FigureName
     */
    public String getFigureName() {
        return (String)getAttributeInternal(FIGURENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureName.
     * @param value value to set the  FigureName
     */
    public void setFigureName(String value) {
        setAttributeInternal(FIGURENAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SeriesName.
     * @return the SeriesName
     */
    public String getSeriesName() {
        return (String)getAttributeInternal(SERIESNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SeriesName.
     * @param value value to set the  SeriesName
     */
    public void setSeriesName(String value) {
        setAttributeInternal(SERIESNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Photographer.
     * @return the Photographer
     */
    public String getPhotographer() {
        return (String)getAttributeInternal(PHOTOGRAPHER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Photographer.
     * @param value value to set the  Photographer
     */
    public void setPhotographer(String value) {
        setAttributeInternal(PHOTOGRAPHER, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PhotographyType.
     * @return the PhotographyType
     */
    public String getPhotographyType() {
        return (String)getAttributeInternal(PHOTOGRAPHYTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PhotographyType.
     * @param value value to set the  PhotographyType
     */
    public void setPhotographyType(String value) {
        setAttributeInternal(PHOTOGRAPHYTYPE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PhotographyTime.
     * @return the PhotographyTime
     */
    public Date getPhotographyTime() {
        return (Date)getAttributeInternal(PHOTOGRAPHYTIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PhotographyTime.
     * @param value value to set the  PhotographyTime
     */
    public void setPhotographyTime(Date value) {
        setAttributeInternal(PHOTOGRAPHYTIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PhotographyLocation.
     * @return the PhotographyLocation
     */
    public String getPhotographyLocation() {
        return (String)getAttributeInternal(PHOTOGRAPHYLOCATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PhotographyLocation.
     * @param value value to set the  PhotographyLocation
     */
    public void setPhotographyLocation(String value) {
        setAttributeInternal(PHOTOGRAPHYLOCATION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Keyword.
     * @return the Keyword
     */
    public String getKeyword() {
        return (String)getAttributeInternal(KEYWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Keyword.
     * @param value value to set the  Keyword
     */
    public void setKeyword(String value) {
        setAttributeInternal(KEYWORD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureDesc.
     * @return the FigureDesc
     */
    public String getFigureDesc() {
        return (String)getAttributeInternal(FIGUREDESC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureDesc.
     * @param value value to set the  FigureDesc
     */
    public void setFigureDesc(String value) {
        setAttributeInternal(FIGUREDESC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Remarks.
     * @return the Remarks
     */
    public String getRemarks() {
        return (String)getAttributeInternal(REMARKS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Remarks.
     * @param value value to set the  Remarks
     */
    public void setRemarks(String value) {
        setAttributeInternal(REMARKS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureFormat.
     * @return the FigureFormat
     */
    public String getFigureFormat() {
        return (String)getAttributeInternal(FIGUREFORMAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureFormat.
     * @param value value to set the  FigureFormat
     */
    public void setFigureFormat(String value) {
        setAttributeInternal(FIGUREFORMAT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureSize.
     * @return the FigureSize
     */
    public String getFigureSize() {
        return (String)getAttributeInternal(FIGURESIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureSize.
     * @param value value to set the  FigureSize
     */
    public void setFigureSize(String value) {
        setAttributeInternal(FIGURESIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureDimension.
     * @return the FigureDimension
     */
    public String getFigureDimension() {
        return (String)getAttributeInternal(FIGUREDIMENSION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureDimension.
     * @param value value to set the  FigureDimension
     */
    public void setFigureDimension(String value) {
        setAttributeInternal(FIGUREDIMENSION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureResolution.
     * @return the FigureResolution
     */
    public String getFigureResolution() {
        return (String)getAttributeInternal(FIGURERESOLUTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureResolution.
     * @param value value to set the  FigureResolution
     */
    public void setFigureResolution(String value) {
        setAttributeInternal(FIGURERESOLUTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureXResolution.
     * @return the FigureXResolution
     */
    public String getFigureXResolution() {
        return (String)getAttributeInternal(FIGUREXRESOLUTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureXResolution.
     * @param value value to set the  FigureXResolution
     */
    public void setFigureXResolution(String value) {
        setAttributeInternal(FIGUREXRESOLUTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureYResolution.
     * @return the FigureYResolution
     */
    public String getFigureYResolution() {
        return (String)getAttributeInternal(FIGUREYRESOLUTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureYResolution.
     * @param value value to set the  FigureYResolution
     */
    public void setFigureYResolution(String value) {
        setAttributeInternal(FIGUREYRESOLUTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CoverDesign.
     * @return the CoverDesign
     */
    public String getCoverDesign() {
        return (String)getAttributeInternal(COVERDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CoverDesign.
     * @param value value to set the  CoverDesign
     */
    public void setCoverDesign(String value) {
        setAttributeInternal(COVERDESIGN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Checked.
     * @return the Checked
     */
    public Boolean getChecked() {
        Boolean result = null;
        Hashtable map = this.getDBTransaction().getSession().getUserData();
        String prefix = "ORIGIN";
        String selectAllFlag = (String)map.get(prefix + "_selectAllFlag");
        String key = prefix + "_" + this.getDocId();
        List<String> currSelectedList = (List<String>)map.get(prefix + "_currSelected");
        if ("O".equals(selectAllFlag)) {
            if (currSelectedList.contains(key)) {
                result = true;
            } else {
                result = false;
            }
        } else {
            if ("Y".equals(selectAllFlag)) {
                result = true;
            } else if ("N".equals(selectAllFlag)) {
                result = false;
            }
        }
        try {
            this.setChecked(result);
        } catch (Exception e) {
            //            e.printStackTrace();
        }
        return (Boolean)getAttributeInternal(CHECKED);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Checked.
     * @param value value to set the  Checked
     */
    public void setChecked(Boolean value) {
        setAttributeInternal(CHECKED, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryId.
     * @return the CategoryId
     */
    public Number getCategoryId() {
        return (Number)getAttributeInternal(CATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryId.
     * @param value value to set the  CategoryId
     */
    public void setCategoryId(Number value) {
        setAttributeInternal(CATEGORYID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CategoryName.
     * @return the CategoryName
     */
    public String getCategoryName() {
        return (String)getAttributeInternal(CATEGORYNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CategoryName.
     * @param value value to set the  CategoryName
     */
    public void setCategoryName(String value) {
        setAttributeInternal(CATEGORYNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureWidth.
     * @return the FigureWidth
     */
    public String getFigureWidth() {
        return (String)getAttributeInternal(FIGUREWIDTH);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureWidth.
     * @param value value to set the  FigureWidth
     */
    public void setFigureWidth(String value) {
        setAttributeInternal(FIGUREWIDTH, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureHeight.
     * @return the FigureHeight
     */
    public String getFigureHeight() {
        return (String)getAttributeInternal(FIGUREHEIGHT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureHeight.
     * @param value value to set the  FigureHeight
     */
    public void setFigureHeight(String value) {
        setAttributeInternal(FIGUREHEIGHT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Status.
     * @return the Status
     */
    public String getStatus() {
        return (String)getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Status.
     * @param value value to set the  Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ThumbnailUrl.
     * @return the ThumbnailUrl
     */
    public String getThumbnailUrl() {
        return (String)getAttributeInternal(THUMBNAILURL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ThumbnailUrl.
     * @param value value to set the  ThumbnailUrl
     */
    public void setThumbnailUrl(String value) {
        setAttributeInternal(THUMBNAILURL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompCode.
     * @return the CompCode
     */
    public String getCompCode() {
        return (String) getAttributeInternal(COMPCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompCode.
     * @param value value to set the  CompCode
     */
    public void setCompCode(String value) {
        setAttributeInternal(COMPCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CompName.
     * @return the CompName
     */
    public String getCompName() {
        return (String) getAttributeInternal(COMPNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CompName.
     * @param value value to set the  CompName
     */
    public void setCompName(String value) {
        setAttributeInternal(COMPNAME, value);
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
