package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioQueryVOImpl;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQueryVOImpl;
import com.zypg.cms.work.model.viewobject.book.query.CmsBookQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.courseware.query.CmsCoursewareQueryVOImpl;
import com.zypg.cms.work.model.viewobject.courseware.query.CmsCoursewareQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.elecprod.query.CmsElecProdQueryVOImpl;
import com.zypg.cms.work.model.viewobject.elecprod.query.CmsElecProdQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsEncyclopediasEntryQueryVOImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsEncyclopediasEntryQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsWorksEntryQueryVOImpl;
import com.zypg.cms.work.model.viewobject.entry.query.CmsWorksEntryQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.illustration.query.CmsIllustrationQueryVOImpl;
import com.zypg.cms.work.model.viewobject.illustration.query.CmsIllustrationQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureQueryVOImpl;
import com.zypg.cms.work.model.viewobject.material.query.CmsMaterialFigureQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.newspaper.query.CmsNewspaperQueryVOImpl;
import com.zypg.cms.work.model.viewobject.newspaper.query.CmsNewspaperQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.periodical.query.CmsPeriodicalQueryVOImpl;
import com.zypg.cms.work.model.viewobject.periodical.query.CmsPeriodicalQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.photography.query.CmsPhotographyQueryVOImpl;
import com.zypg.cms.work.model.viewobject.photography.query.CmsPhotographyQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.teachingplan.query.CmsTeachingPlanQueryVOImpl;
import com.zypg.cms.work.model.viewobject.teachingplan.query.CmsTeachingPlanQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.video.query.CmsVideoQueryVOImpl;
import com.zypg.cms.work.model.viewobject.video.query.CmsVideoQueryVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.jbo.domain.Number;


public class RelatedInfoViewManagedBean extends CustomManagedBean {
    private List<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> elecProdList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> periodicalList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> newspaperList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> teachingPlanList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> coursewareList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> audioList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> videoList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> illustrationList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> materialList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> photographyList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> worksEntryList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> encyclopediasEntryList = new ArrayList<Map<String, Object>>();

    public RelatedInfoViewManagedBean() {
    }

    public void initRelatedInfo() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        try {
            Number docId = new Number(docIdStr);
            Map<String, List<Number>> map = CommonUtil.getWorkAM().getDocRelRowsByRelType(docId);
            for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
                String key = (String)it.next();
                this.setExpressionValue("#{pageFlowScope." + key + "}", map.get(key));
            }
        } catch (SQLException e) {
            System.out.println("docIdStr:" + docIdStr);
        }
    }

    public void generateBookList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> bookList = (List<Number>)this.resolveExpression("#{pageFlowScope.bookList}");
        if (bookList == null) {
            return;
        }
        CmsBookQueryVOImpl bookVO = CommonUtil.getBookAM().getCmsBookQueryVO();
        int i = 0;
        for (Number docId : bookList) {
            i++;
            bookVO.setbvDocId(docId);
            bookVO.executeQuery();
            if (bookVO.getEstimatedRowCount() > 0) {
                CmsBookQueryVORowImpl row = (CmsBookQueryVORowImpl)bookVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("bookName", row.getBookName());
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setBookList(l);
    }

    public void generateElecProdList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> elecProdList = (List<Number>)this.resolveExpression("#{pageFlowScope.elecProdList}");
        if (elecProdList == null) {
            return;
        }
        CmsElecProdQueryVOImpl elecProdVO = CommonUtil.getElecProdAM().getCmsElecProdQueryVO();
        int i = 0;
        for (Number docId : elecProdList) {
            i++;
            elecProdVO.setbvDocId(docId);
            elecProdVO.executeQuery();
            if (elecProdVO.getEstimatedRowCount() > 0) {
                CmsElecProdQueryVORowImpl row = (CmsElecProdQueryVORowImpl)elecProdVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("elecProdName", row.getElecProdName());
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setElecProdList(l);
    }

    public void generatePeriodicalList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> periodicalList = (List<Number>)this.resolveExpression("#{pageFlowScope.periodicalList}");
        if (periodicalList == null) {
            return;
        }
        CmsPeriodicalQueryVOImpl periodicalVO = CommonUtil.getPeriodicalAM().getCmsPeriodicalQueryVO();
        int i = 0;
        for (Number docId : periodicalList) {
            i++;
            periodicalVO.setbvDocId(docId);
            periodicalVO.executeQuery();
            if (periodicalVO.getEstimatedRowCount() > 0) {
                CmsPeriodicalQueryVORowImpl row = (CmsPeriodicalQueryVORowImpl)periodicalVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("periodicalCategoryName",
                        row.getAttribute("PeriodicalCategoryName") + "(" + row.getAttribute("Year") + "-" +
                        row.getAttribute("PeriodNum") + ")");
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setPeriodicalList(l);
    }

    public void generateNewspaperList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> newspaperList = (List<Number>)this.resolveExpression("#{pageFlowScope.newspaperList}");
        if (newspaperList == null) {
            return;
        }
        CmsNewspaperQueryVOImpl newspaperVO = CommonUtil.getNewspaperAM().getCmsNewspaperQueryVO();
        int i = 0;
        for (Number docId : newspaperList) {
            i++;
            newspaperVO.setbvDocId(docId);
            newspaperVO.executeQuery();
            if (newspaperVO.getEstimatedRowCount() > 0) {
                CmsNewspaperQueryVORowImpl row = (CmsNewspaperQueryVORowImpl)newspaperVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("newspaperCategoryName",
                        row.getAttribute("NewspaperCategoryName") + "(" + row.getAttribute("Year") + "-" +
                        row.getAttribute("NewsNum") + ")");
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setNewspaperList(l);
    }

    public void generaTeachingPlanList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> teachingPlanList = (List<Number>)this.resolveExpression("#{pageFlowScope.teachingPlanList}");
        if (teachingPlanList == null) {
            return;
        }
        CmsTeachingPlanQueryVOImpl teachingPlanVO = CommonUtil.getTeachingPlanAM().getCmsTeachingPlanQueryVO();
        for (Number docId : teachingPlanList) {
            teachingPlanVO.setbvDocId(docId);
            teachingPlanVO.executeQuery();
            if (teachingPlanVO.getEstimatedRowCount() > 0) {
                CmsTeachingPlanQueryVORowImpl row = (CmsTeachingPlanQueryVORowImpl)teachingPlanVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("teachingPlanName", row.getTeachingPlanName());
                l.add(map);
            }
        }
        this.setTeachingPlanList(l);
    }

    public void generateCoursewareList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> coursewareList = (List<Number>)this.resolveExpression("#{pageFlowScope.coursewareList}");
        if (coursewareList == null) {
            return;
        }
        CmsCoursewareQueryVOImpl coursewareVO = CommonUtil.getCoursewareAM().getCmsCoursewareQueryVO();
        for (Number docId : coursewareList) {
            coursewareVO.setbvDocId(docId);
            coursewareVO.executeQuery();
            if (coursewareVO.getEstimatedRowCount() > 0) {
                CmsCoursewareQueryVORowImpl row = (CmsCoursewareQueryVORowImpl)coursewareVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("coursewareName", row.getCoursewareName());
                l.add(map);
            }
        }
        this.setCoursewareList(l);
    }

    public void generateAudioList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> audioList = (List<Number>)this.resolveExpression("#{pageFlowScope.audioList}");
        if (audioList == null) {
            return;
        }
        CmsAudioQueryVOImpl audioVO = CommonUtil.getAudioAM().getCmsAudioQueryVO();
        for (Number docId : audioList) {
            audioVO.setbvDocId(docId);
            audioVO.executeQuery();
            if (audioVO.getEstimatedRowCount() > 0) {
                CmsAudioQueryVORowImpl row = (CmsAudioQueryVORowImpl)audioVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("fileName", row.getFileName());
                l.add(map);
            }
        }
        this.setAudioList(l);
    }

    public void generateVideoList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> videoList = (List<Number>)this.resolveExpression("#{pageFlowScope.videoList}");
        if (videoList == null) {
            return;
        }
        CmsVideoQueryVOImpl videoVO = CommonUtil.getVideoAM().getCmsVideoQueryVO();
        for (Number docId : videoList) {
            videoVO.setbvDocId(docId);
            videoVO.executeQuery();
            if (videoVO.getEstimatedRowCount() > 0) {
                CmsVideoQueryVORowImpl row = (CmsVideoQueryVORowImpl)videoVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("fileName", row.getFileName());
                l.add(map);
            }
        }
        this.setVideoList(l);
    }

    public void generateIllustrationList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> illustrationList = (List<Number>)this.resolveExpression("#{pageFlowScope.illustrationList}");
        if (illustrationList == null) {
            return;
        }
        CmsIllustrationQueryVOImpl illustrationVO = CommonUtil.getIllustrationAM().getCmsIllustrationQueryVO();
        int i = 0;
        for (Number docId : illustrationList) {
            i++;
            illustrationVO.setbvDocId(docId);
            illustrationVO.executeQuery();
            if (illustrationVO.getEstimatedRowCount() > 0) {
                CmsIllustrationQueryVORowImpl row = (CmsIllustrationQueryVORowImpl)illustrationVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("illustrationName", row.getIllustrationName());
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setIllustrationList(l);
    }

    public void generateMaterialList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> materialList = (List<Number>)this.resolveExpression("#{pageFlowScope.materialList}");
        if (materialList == null) {
            return;
        }
        CmsMaterialFigureQueryVOImpl materialFigureVO = CommonUtil.getMaterialFigureAM().getCmsMaterialFigureQueryVO();
        int i = 0;
        for (Number docId : materialList) {
            i++;
            materialFigureVO.setbvDocId(docId);
            materialFigureVO.executeQuery();
            if (materialFigureVO.getEstimatedRowCount() > 0) {
                CmsMaterialFigureQueryVORowImpl row = (CmsMaterialFigureQueryVORowImpl)materialFigureVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("figureName", row.getFigureName());
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setMaterialList(l);
    }

    public void generatePhotographyList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> photographyList = (List<Number>)this.resolveExpression("#{pageFlowScope.photographyList}");
        if (photographyList == null) {
            return;
        }
        CmsPhotographyQueryVOImpl photographyFigureVO = CommonUtil.getPhotographyFigureAM().getCmsPhotographyQueryVO();
        int i = 0;
        for (Number docId : photographyList) {
            i++;
            photographyFigureVO.setbvDocId(docId);
            photographyFigureVO.executeQuery();
            if (photographyFigureVO.getEstimatedRowCount() > 0) {
                CmsPhotographyQueryVORowImpl row = (CmsPhotographyQueryVORowImpl)photographyFigureVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("figureName", row.getFigureName());
                map.put("thumbnailUrl", row.getThumbnailUrl());
                l.add(map);
            }
            if (i == 2) {
                break;
            }
        }
        this.setPhotographyList(l);
    }

    public void generateWorksEntryList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> worksEntryList = (List<Number>)this.resolveExpression("#{pageFlowScope.worksEntryList}");
        if (worksEntryList == null) {
            return;
        }
        CmsWorksEntryQueryVOImpl worksEntryQueryVO = CommonUtil.getEntryAM().getCmsWorksEntryQueryVO();
        for (Number docId : worksEntryList) {
            worksEntryQueryVO.setbvDocId(docId);
            worksEntryQueryVO.executeQuery();
            if (worksEntryQueryVO.getEstimatedRowCount() > 0) {
                CmsWorksEntryQueryVORowImpl row = (CmsWorksEntryQueryVORowImpl)worksEntryQueryVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("title", row.getTitle());
                l.add(map);
            }
        }
        this.setWorksEntryList(l);
    }

    public void generateEncyclopediasEntryList() {
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> encyclopediasEntryList =
            (List<Number>)this.resolveExpression("#{pageFlowScope.encyclopediasEntryList}");
        if (encyclopediasEntryList == null) {
            return;
        }
        CmsEncyclopediasEntryQueryVOImpl cmsEncyclopediasEntryQueryVO =
            CommonUtil.getEntryAM().getCmsEncyclopediasEntryQueryVO();
        for (Number docId : encyclopediasEntryList) {
            cmsEncyclopediasEntryQueryVO.setbvDocId(docId);
            cmsEncyclopediasEntryQueryVO.executeQuery();
            if (cmsEncyclopediasEntryQueryVO.getEstimatedRowCount() > 0) {
                CmsEncyclopediasEntryQueryVORowImpl row =
                    (CmsEncyclopediasEntryQueryVORowImpl)cmsEncyclopediasEntryQueryVO.first();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("entryHeader", row.getEntryHeader());
                l.add(map);
            }
        }
        this.setEncyclopediasEntryList(l);
    }

    public void setBookList(List<Map<String, Object>> bookList) {
        this.bookList = bookList;
    }

    public List<Map<String, Object>> getBookList() {
        if (bookList.size() == 0) {
            this.generateBookList();
        }
        return bookList;
    }

    public int getBookListSize() {
        List<Number> bookList = (List<Number>)this.resolveExpression("#{pageFlowScope.bookList}");
        return (bookList == null ? 0 : bookList.size());
    }

    public void setElecProdList(List<Map<String, Object>> elecProdList) {
        this.elecProdList = elecProdList;
    }

    public List<Map<String, Object>> getElecProdList() {
        if (elecProdList.size() == 0) {
            this.generateElecProdList();
        }
        return elecProdList;
    }

    public int getElecProdListSize() {
        List<Number> elecProdList = (List<Number>)this.resolveExpression("#{pageFlowScope.elecProdList}");
        return (elecProdList == null ? 0 : elecProdList.size());
    }

    public void setPeriodicalList(List<Map<String, Object>> periodicalList) {
        this.periodicalList = periodicalList;
    }

    public List<Map<String, Object>> getPeriodicalList() {
        if (periodicalList.size() == 0) {
            this.generatePeriodicalList();
        }
        return periodicalList;
    }

    public int getPeriodicalListSize() {
        List<Number> periodicalList = (List<Number>)this.resolveExpression("#{pageFlowScope.periodicalList}");
        return (periodicalList == null ? 0 : periodicalList.size());
    }

    public void setTeachingPlanList(List<Map<String, Object>> teachingPlanList) {
        this.teachingPlanList = teachingPlanList;
    }

    public List<Map<String, Object>> getTeachingPlanList() {
        if (teachingPlanList.size() == 0) {
            this.generaTeachingPlanList();
        }
        return teachingPlanList;
    }

    public int getTeachingPlanListSize() {
        List<Number> teachingPlanList = (List<Number>)this.resolveExpression("#{pageFlowScope.teachingPlanList}");
        return (teachingPlanList == null ? 0 : teachingPlanList.size());
    }

    public void setCoursewareList(List<Map<String, Object>> coursewareList) {
        this.coursewareList = coursewareList;
    }

    public List<Map<String, Object>> getCoursewareList() {
        if (coursewareList.size() == 0) {
            this.generateCoursewareList();
        }
        return coursewareList;
    }

    public int getCoursewareListSize() {
        List<Number> coursewareList = (List<Number>)this.resolveExpression("#{pageFlowScope.coursewareList}");
        return (coursewareList == null ? 0 : coursewareList.size());
    }

    public void setAudioList(List<Map<String, Object>> audioList) {
        this.audioList = audioList;
    }

    public List<Map<String, Object>> getAudioList() {
        if (audioList.size() == 0) {
            this.generateAudioList();
        }

        return audioList;
    }

    public int getAudioListSize() {
        List<Number> audioList = (List<Number>)this.resolveExpression("#{pageFlowScope.audioList}");
        return (audioList == null ? 0 : audioList.size());
    }

    public void setVideoList(List<Map<String, Object>> videoList) {
        this.videoList = videoList;
    }

    public List<Map<String, Object>> getVideoList() {
        if (videoList.size() == 0) {
            this.generateVideoList();
        }
        return videoList;
    }

    public int getVideoListSize() {
        List<Number> videoList = (List<Number>)this.resolveExpression("#{pageFlowScope.videoList}");
        return (videoList == null ? 0 : videoList.size());
    }

    public void setIllustrationList(List<Map<String, Object>> illustrationList) {
        this.illustrationList = illustrationList;
    }

    public List<Map<String, Object>> getIllustrationList() {
        if (illustrationList.size() == 0) {
            this.generateIllustrationList();
        }
        return illustrationList;
    }

    public int getIllustrationListSize() {
        List<Number> illustrationList = (List<Number>)this.resolveExpression("#{pageFlowScope.illustrationList}");
        return (illustrationList == null ? 0 : illustrationList.size());
    }

    public void setMaterialList(List<Map<String, Object>> materialList) {
        this.materialList = materialList;
    }

    public List<Map<String, Object>> getMaterialList() {
        if (materialList.size() == 0) {
            this.generateMaterialList();
        }
        return materialList;
    }

    public int getMaterialListSize() {
        List<Number> materialList = (List<Number>)this.resolveExpression("#{pageFlowScope.materialList}");
        return (materialList == null ? 0 : materialList.size());
    }

    public void setPhotographyList(List<Map<String, Object>> photographyList) {
        this.photographyList = photographyList;
    }

    public List<Map<String, Object>> getPhotographyList() {
        if (photographyList.size() == 0) {
            this.generatePhotographyList();
        }
        return photographyList;
    }

    public int getPhotographyListSize() {
        List<Number> photographyList = (List<Number>)this.resolveExpression("#{pageFlowScope.photographyList}");
        return (photographyList == null ? 0 : photographyList.size());
    }

    public void setNewspaperList(List<Map<String, Object>> newspaperList) {
        this.newspaperList = newspaperList;
    }

    public List<Map<String, Object>> getNewspaperList() {
        if (newspaperList.size() == 0) {
            this.generateNewspaperList();
        }
        return newspaperList;
    }

    public int getNewspaperListSize() {
        List<Number> newspaperList = (List<Number>)this.resolveExpression("#{pageFlowScope.newspaperList}");
        return (newspaperList == null ? 0 : newspaperList.size());
    }

    public void setWorksEntryList(List<Map<String, Object>> worksEntryList) {
        this.worksEntryList = worksEntryList;
    }

    public List<Map<String, Object>> getWorksEntryList() {
        if (worksEntryList.size() == 0) {
            this.generateWorksEntryList();
        }
        return worksEntryList;
    }

    public int getWorksEntryListSize() {
        List<Number> worksEntryList = (List<Number>)this.resolveExpression("#{pageFlowScope.worksEntryList}");
        return (worksEntryList == null ? 0 : worksEntryList.size());
    }

    public void setEncyclopediasEntryList(List<Map<String, Object>> encyclopediasEntryList) {
        this.encyclopediasEntryList = encyclopediasEntryList;
    }

    public List<Map<String, Object>> getEncyclopediasEntryList() {
        if (encyclopediasEntryList.size() == 0) {
            this.generateEncyclopediasEntryList();
        }
        return encyclopediasEntryList;
    }

    public int getEncyclopediasEntryListSize() {
        List<Number> encyclopediasEntryList =
            (List<Number>)this.resolveExpression("#{pageFlowScope.encyclopediasEntryList}");
        return (encyclopediasEntryList == null ? 0 : encyclopediasEntryList.size());
    }

    public void viewRelatedBookActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/book/inline/relatedBookView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedIllustrationActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/illustration/inline/relatedIllustrationView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedMaterialActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/material/inline/relatedMaterialView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedPhotographyActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/photography/inline/relatedPhotographyView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedElecProdActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/elecprod/inline/relatedElecProdView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedAudioActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/audio/inline/relatedAudioView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedVideoActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/video/inline/relatedVideoView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedPriodicalActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/periodical/inline/relatedPeriodicalView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedNewspaperActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/newspaper/inline/relatedNewspaperView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedTeachingplanActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/teachingplan/inline/relatedTeachingPlanView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedCoursewareActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/courseware/inline/relatedCoursewareView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedWorksEntryActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/entry/inline/relatedWorksEntryView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }

    public void viewRelatedEncyclopediasEntryActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url =
            request.getContextPath() + "/faces/work/entry/inline/relatedEncyclopediasEntryView.jsf?docId=" + relatedDocId;
        this.openWindow(url);
    }
}
