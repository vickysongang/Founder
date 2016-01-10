package com.zypg.cms.work.model.viewobject.material.query;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Date;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun May 31 22:40:28 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CmsMaterialFigureQVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        FigureName {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureName();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureName((String)value);
            }
        }
        ,
        SeriesName {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getSeriesName();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setSeriesName((String)value);
            }
        }
        ,
        FigureAuthor {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureAuthor();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureAuthor((String)value);
            }
        }
        ,
        FigureStyle {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureStyle();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureStyle((String)value);
            }
        }
        ,
        Keyword {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getKeyword();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setKeyword((String)value);
            }
        }
        ,
        FigureDesc {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureDesc();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureDesc((String)value);
            }
        }
        ,
        Remarks {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getRemarks();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setRemarks((String)value);
            }
        }
        ,
        FigureFormat {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureFormat();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureFormat((String)value);
            }
        }
        ,
        FigureDimension {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureDimension();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureDimension((String)value);
            }
        }
        ,
        FigureSize {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureSize();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureSize((String)value);
            }
        }
        ,
        CoverDesign {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getCoverDesign();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setCoverDesign((String)value);
            }
        }
        ,
        FigureResolution {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureResolution();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureResolution((String)value);
            }
        }
        ,
        FigureXResolution {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureXResolution();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureXResolution((String)value);
            }
        }
        ,
        FigureYResolution {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureYResolution();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureYResolution((String)value);
            }
        }
        ,
        FigureWidth {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureWidth();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureWidth((String)value);
            }
        }
        ,
        FigureHeight {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureHeight();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureHeight((String)value);
            }
        }
        ,
        FigureStoreTime {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureStoreTime();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureStoreTime((Date)value);
            }
        }
        ,
        FigureCode {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getFigureCode();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setFigureCode((String)value);
            }
        }
        ,
        Lookup4FigureStyle {
            public Object get(CmsMaterialFigureQVORowImpl obj) {
                return obj.getLookup4FigureStyle();
            }

            public void put(CmsMaterialFigureQVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(CmsMaterialFigureQVORowImpl object);

        public abstract void put(CmsMaterialFigureQVORowImpl object, Object value);

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


    public static final int FIGURENAME = AttributesEnum.FigureName.index();
    public static final int SERIESNAME = AttributesEnum.SeriesName.index();
    public static final int FIGUREAUTHOR = AttributesEnum.FigureAuthor.index();
    public static final int FIGURESTYLE = AttributesEnum.FigureStyle.index();
    public static final int KEYWORD = AttributesEnum.Keyword.index();
    public static final int FIGUREDESC = AttributesEnum.FigureDesc.index();
    public static final int REMARKS = AttributesEnum.Remarks.index();
    public static final int FIGUREFORMAT = AttributesEnum.FigureFormat.index();
    public static final int FIGUREDIMENSION = AttributesEnum.FigureDimension.index();
    public static final int FIGURESIZE = AttributesEnum.FigureSize.index();
    public static final int COVERDESIGN = AttributesEnum.CoverDesign.index();
    public static final int FIGURERESOLUTION = AttributesEnum.FigureResolution.index();
    public static final int FIGUREXRESOLUTION = AttributesEnum.FigureXResolution.index();
    public static final int FIGUREYRESOLUTION = AttributesEnum.FigureYResolution.index();
    public static final int FIGUREWIDTH = AttributesEnum.FigureWidth.index();
    public static final int FIGUREHEIGHT = AttributesEnum.FigureHeight.index();
    public static final int FIGURESTORETIME = AttributesEnum.FigureStoreTime.index();
    public static final int FIGURECODE = AttributesEnum.FigureCode.index();
    public static final int LOOKUP4FIGURESTYLE = AttributesEnum.Lookup4FigureStyle.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CmsMaterialFigureQVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute FigureName.
     * @return the FigureName
     */
    public String getFigureName() {
        return (String) getAttributeInternal(FIGURENAME);
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
        return (String) getAttributeInternal(SERIESNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SeriesName.
     * @param value value to set the  SeriesName
     */
    public void setSeriesName(String value) {
        setAttributeInternal(SERIESNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureAuthor.
     * @return the FigureAuthor
     */
    public String getFigureAuthor() {
        return (String) getAttributeInternal(FIGUREAUTHOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureAuthor.
     * @param value value to set the  FigureAuthor
     */
    public void setFigureAuthor(String value) {
        setAttributeInternal(FIGUREAUTHOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureStyle.
     * @return the FigureStyle
     */
    public String getFigureStyle() {
        return (String) getAttributeInternal(FIGURESTYLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureStyle.
     * @param value value to set the  FigureStyle
     */
    public void setFigureStyle(String value) {
        setAttributeInternal(FIGURESTYLE, value);
    }


    /**
     * Gets the attribute value for the calculated attribute Keyword.
     * @return the Keyword
     */
    public String getKeyword() {
        return (String) getAttributeInternal(KEYWORD);
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
        return (String) getAttributeInternal(FIGUREDESC);
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
        return (String) getAttributeInternal(REMARKS);
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
        return (String) getAttributeInternal(FIGUREFORMAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureFormat.
     * @param value value to set the  FigureFormat
     */
    public void setFigureFormat(String value) {
        setAttributeInternal(FIGUREFORMAT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureDimension.
     * @return the FigureDimension
     */
    public String getFigureDimension() {
        return (String) getAttributeInternal(FIGUREDIMENSION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureDimension.
     * @param value value to set the  FigureDimension
     */
    public void setFigureDimension(String value) {
        setAttributeInternal(FIGUREDIMENSION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureSize.
     * @return the FigureSize
     */
    public String getFigureSize() {
        return (String) getAttributeInternal(FIGURESIZE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureSize.
     * @param value value to set the  FigureSize
     */
    public void setFigureSize(String value) {
        setAttributeInternal(FIGURESIZE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CoverDesign.
     * @return the CoverDesign
     */
    public String getCoverDesign() {
        return (String) getAttributeInternal(COVERDESIGN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CoverDesign.
     * @param value value to set the  CoverDesign
     */
    public void setCoverDesign(String value) {
        setAttributeInternal(COVERDESIGN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureResolution.
     * @return the FigureResolution
     */
    public String getFigureResolution() {
        return (String) getAttributeInternal(FIGURERESOLUTION);
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
        return (String) getAttributeInternal(FIGUREXRESOLUTION);
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
        return (String) getAttributeInternal(FIGUREYRESOLUTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureYResolution.
     * @param value value to set the  FigureYResolution
     */
    public void setFigureYResolution(String value) {
        setAttributeInternal(FIGUREYRESOLUTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureWidth.
     * @return the FigureWidth
     */
    public String getFigureWidth() {
        return (String) getAttributeInternal(FIGUREWIDTH);
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
        return (String) getAttributeInternal(FIGUREHEIGHT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureHeight.
     * @param value value to set the  FigureHeight
     */
    public void setFigureHeight(String value) {
        setAttributeInternal(FIGUREHEIGHT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureStoreTime.
     * @return the FigureStoreTime
     */
    public Date getFigureStoreTime() {
        return (Date) getAttributeInternal(FIGURESTORETIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureStoreTime.
     * @param value value to set the  FigureStoreTime
     */
    public void setFigureStoreTime(Date value) {
        setAttributeInternal(FIGURESTORETIME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FigureCode.
     * @return the FigureCode
     */
    public String getFigureCode() {
        return (String) getAttributeInternal(FIGURECODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FigureCode.
     * @param value value to set the  FigureCode
     */
    public void setFigureCode(String value) {
        setAttributeInternal(FIGURECODE, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> Lookup4FigureStyle.
     */
    public RowSet getLookup4FigureStyle() {
        return (RowSet)getAttributeInternal(LOOKUP4FIGURESTYLE);
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
