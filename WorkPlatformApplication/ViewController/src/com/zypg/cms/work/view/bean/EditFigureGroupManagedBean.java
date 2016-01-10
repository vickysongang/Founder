package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class EditFigureGroupManagedBean extends CustomManagedBean {
    List<List<Map<String, Object>>> figureList = new ArrayList<List<Map<String, Object>>>();
    private RichPopup indexFigureSetPopup;
    private RichPanelGroupLayout panelGroupLayout;

    public EditFigureGroupManagedBean() {
    }

    public void initFigureGroup() {
        if (CommonUtil.getFigureGroupAM().getDBTransaction().isDirty()) {
            CommonUtil.getFigureGroupAM().getDBTransaction().rollback();
        }
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        List<Number> figureList = CommonUtil.getFigureGroupAM().initFigureGroup4Edit(docId);
        this.setExpressionValue("#{pageFlowScope.figureList}", figureList);
        //        this.setExpressionValue("#{pageFlowScope.selectedThumbnailUrl}", null);
    }

    public String getIndexFigureThumbnailUrl() {
        String thumbnailUrl = null;
        CmsDocTVOImpl docVO = CommonUtil.getFigureGroupAM().getCmsDocTVO4Query();
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Row[] docRows = docVO.getFilteredRows("DocId", docId);
        if (docRows.length > 0) {
            thumbnailUrl = (String)docRows[0].getAttribute("ThumbnailUrl");
        }
        return thumbnailUrl;
    }

    public void generateFigureList() {
        int i = 4;
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> figureList = (List<Number>)this.resolveExpression("#{pageFlowScope.figureList}");
        CmsDocTVOImpl docVO = CommonUtil.getFigureGroupAM().getCmsDocTVO4Query();
        for (Number docId : figureList) {
            Row[] docRows = docVO.getFilteredRows("DocId", docId);
            if (docRows.length > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("docId", docId);
                map.put("thumbnailUrl", docRows[0].getAttribute("ThumbnailUrl"));
                l.add(map);
                if (i-- == 1) {
                    i = 4;
                    list.add(l);
                    l = new ArrayList<Map<String, Object>>();
                }
            }
        }
        //补空
        for (int j = 1; j <= i % 4; j++) {
            l.add(new HashMap<String, Object>());
        }
        list.add(l);
        this.setFigureList(list);
    }

    public void setFigureList(List<List<Map<String, Object>>> figureList) {
        this.figureList = figureList;
    }

    public List<List<Map<String, Object>>> getFigureList() {
        if (figureList.size() == 0) {
            this.generateFigureList();
        }
        return figureList;
    }

    public void saveActionListener(ActionEvent actionEvent) {
        CommonUtil.getFigureGroupAM().getDBTransaction().commit();
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getFigureGroupAM().getDBTransaction().rollback();
    }

    public void setIndexFigureSetPopup(RichPopup indexFigureSetPopup) {
        this.indexFigureSetPopup = indexFigureSetPopup;
    }

    public RichPopup getIndexFigureSetPopup() {
        return indexFigureSetPopup;
    }

    public void setFigureToIndexDialogListener(DialogEvent dialogEvent) {
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.selectedDocId}");
        String thumbnailUrl = (String)this.resolveExpression("#{pageFlowScope.selectedThumbnailUrl}");
        System.out.println("thumbnailUrl:" + thumbnailUrl);
        CommonUtil.getFigureGroupAM().setFigureToIndex(docId);
        this.refreshUIComponent(this.getPanelGroupLayout());
    }

    public void setFigureToIndexActionListener(ActionEvent actionEvent) {
        Number docId = (Number)actionEvent.getComponent().getAttributes().get("docId");
        String thumbnailUrl = (String)actionEvent.getComponent().getAttributes().get("thumbnailUrl");
        this.setExpressionValue("#{pageFlowScope.selectedDocId}", docId);
        this.setExpressionValue("#{pageFlowScope.selectedThumbnailUrl}", thumbnailUrl);
        this.showPopup(this.getIndexFigureSetPopup().getClientId());
    }

    public void setPanelGroupLayout(RichPanelGroupLayout panelGroupLayout) {
        this.panelGroupLayout = panelGroupLayout;
    }

    public RichPanelGroupLayout getPanelGroupLayout() {
        return panelGroupLayout;
    }
}
