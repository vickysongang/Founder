package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.event.ActionEvent;

public class ComplexSearchManagedBean extends CustomManagedBean {
    public ComplexSearchManagedBean() {
    }

    public void initComplexSearch() {
        String libCode = (String)this.resolveExpression("#{pageFlowScope.libCode}");
        if ("BOOK".equals(libCode)) {
            CommonUtil.getBookAM().initComplexSearch4Book();
        } else if ("AUDIO".equals(libCode)) {
            CommonUtil.getAudioAM().initComplexSearch4Audio();
        } else if ("COURSEWARE".equals(libCode)) {
            CommonUtil.getCoursewareAM().initComplexSearch4Courseware();
        } else if ("ELEC_PROD".equals(libCode)) {
            CommonUtil.getElecProdAM().initComplexSearch4ElecProd();
        } else if ("ILLUSTRATION".equals(libCode)) {
            CommonUtil.getIllustrationAM().initComplexSearch4Illustration();
        } else if ("MATERIAL_FIGURE".equals(libCode)) {
            CommonUtil.getMaterialFigureAM().initComplexSearch4MaterialFigure();
        } else if ("NEWSPAPER".equals(libCode)) {
            CommonUtil.getNewspaperAM().initComplexSearch4Newspaper();
        } else if ("PERIODICAL".equals(libCode)) {
            CommonUtil.getPeriodicalAM().initComplexSearch4Periodical();
        } else if ("PHOTOGRAPHY_FIGURE".equals(libCode)) {
            CommonUtil.getPhotographyFigureAM().initComplexSearch4Photography();
        } else if ("TEACHING_PLAN".equals(libCode)) {
            CommonUtil.getTeachingPlanAM().initComplexSearch4TeachingPlan();
        } else if ("VIDEO".equals(libCode)) {
            CommonUtil.getVideoAM().initComplexSearch4Video();
        }
    }

    public String searchAction() {
        return "toReturn";
    }

    public void cancelActionListener(ActionEvent actionEvent) {

    }
}
