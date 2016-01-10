package com.zypg.cms.work.view.util;

import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.theme.origin.CmsBookOriginVORowImpl;

import java.util.HashMap;
import java.util.Map;

import oracle.jbo.Row;

public class ThemeResourceOriginUtil {
    public ThemeResourceOriginUtil() {
        super();
    }

    public static CustomTableModel getCustomTabelModel(String libCode) {
        CustomTableModel tableModel = null;
        tableModel =
                new CustomTableModel("WorkAMDataControl", "ThemeAM", getViewObjectName(libCode), 20, getCriteriaName(libCode));
        return tableModel;
    }

    public static String getViewObjectName(String libCode) {
        String viewObjectName = null;
        if ("BOOK".equals(libCode)) {
            viewObjectName = "CmsBookOriginVO";
        } else if ("ILLUSTRATION".equals(libCode)) {
            viewObjectName = "CmsIllustrationOriginVO";
        } else if ("MATERIAL_FIGURE".equals(libCode)) {
            viewObjectName = "CmsMaterialFigureOriginVO";
        } else if ("PHOTOGRAPHY_FIGURE".equals(libCode)) {
            viewObjectName = "CmsPhotographyFigureOriginVO";
        } else if ("AUDIO".equals(libCode)) {
            viewObjectName = "CmsAudioOriginVO";
        } else if ("VIDEO".equals(libCode)) {
            viewObjectName = "CmsVideoOriginVO";
        } else if ("ENCYCLOPEDIAS_ENTRY".equals(libCode)) {
            viewObjectName = "CmsEncyclopediasEntryOriginVO";
        } else if ("WORKS_ENTRY".equals(libCode)) {
            viewObjectName = "CmsWorksEntryOriginVO";
        } else if ("TEACHING_PLAN".equals(libCode)) {
            viewObjectName = "CmsTeachingPlanOriginVO";
        } else if ("COURSEWARE".equals(libCode)) {
            viewObjectName = "CmsCoursewareOriginVO";
        } else if ("PERIODICAL".equals(libCode)) {
            viewObjectName = "CmsPeriodicalOriginVO";
        } else if ("NEWSPAPER".equals(libCode)) {
            viewObjectName = "CmsNewspaperOriginVO";
        } else if ("ELEC_PROD".equals(libCode)) {
            viewObjectName = "CmsElecProdOriginVO";
        }
        return viewObjectName;
    }

    public static String getCriteriaName(String libCode) {
        String criteriaName = null;
        if ("BOOK".equals(libCode)) {
            criteriaName = "findBookOriginVC";
        } else if ("ILLUSTRATION".equals(libCode)) {
            criteriaName = "findIllustrationOriginVC";
        } else if ("MATERIAL_FIGURE".equals(libCode)) {
            criteriaName = "findMaterialFigureOriginVC";
        } else if ("PHOTOGRAPHY_FIGURE".equals(libCode)) {
            criteriaName = "findPhotographyFigureVC";
        } else if ("AUDIO".equals(libCode)) {
            criteriaName = "findAudioOriginVC";
        } else if ("VIDEO".equals(libCode)) {
            criteriaName = "findVideoOriginVC";
        } else if ("ENCYCLOPEDIAS_ENTRY".equals(libCode)) {
            criteriaName = "findEncyclopediasEntryOriginVC";
        } else if ("WORKS_ENTRY".equals(libCode)) {
            criteriaName = "findWorksEntryOriginVC";
        } else if ("TEACHING_PLAN".equals(libCode)) {
            criteriaName = "findOriginTeachingPlanVC";
        } else if ("COURSEWARE".equals(libCode)) {
            criteriaName = "findOriginCoursewareVC";
        } else if ("PERIODICAL".equals(libCode)) {
            criteriaName = "findOriginPeriodicalVC";
        } else if ("NEWSPAPER".equals(libCode)) {
            criteriaName = "findOriginNewspaperVC";
        } else if ("ELEC_PROD".equals(libCode)) {
            criteriaName = "findOriginElecProdVC";
        }
        return criteriaName;
    }

    public static Map<String, Object> getCoverDisplayAttr(String libCode, Row row) {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }
}
