package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.model.viewobject.book.query.CmsBookVORowImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVOImpl;
import com.zypg.cms.work.model.viewobject.common.CmsDocTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.commons.lang.ArrayUtils;

public class MoreRelatedInfoManagedBean extends CustomManagedBean {
    private List<List<Map<String, Object>>> docList = new ArrayList<List<Map<String, Object>>>();

    public MoreRelatedInfoManagedBean() {
    }

    public void initMoreRelatedInfo() {
        List<Number> list = (List<Number>)this.resolveExpression("#{pageFlowScope.docIdList}");
        if (list != null) {
            for (Number docId : list) {
                //                System.out.println("docId:" + docId);
            }
        }
    }

    public void generateDocList() {
        int i = 5;
        List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        List<Number> docIdList = (List<Number>)this.resolveExpression("#{pageFlowScope.docIdList}");
        if (docIdList == null) {
            return;
        }
        CmsDocTVOImpl docVO = CommonUtil.getWorkAM().getCmsDocTVO4Query();
        String[] libCodeArr = { "BOOK", "ELEC_PROD", "NEWSPAPER", "PERIODICAL" };
        for (Number docId : docIdList) {
            Row[] rows = docVO.getFilteredRows("DocId", docId);
            if (rows.length > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                CmsDocTVORowImpl row = (CmsDocTVORowImpl)rows[0];
                map.put("docId", row.getDocId());
                map.put("thumbnailUrl", row.getThumbnailUrl());
                map.put("libCode", row.getLibCode());
                map.put("docName", CommonUtil.getWorkAM().getDocNameByDocId(docId));
                if (ArrayUtils.contains(libCodeArr, row.getLibCode())) {
                    map.put("coverFlag", "Y");
                } else {
                    map.put("coverFlag", "N");
                }
                l.add(map);
                if (i-- == 1) {
                    i = 5;
                    list.add(l);
                    l = new ArrayList<Map<String, Object>>();
                }
            }
        }
        //补空
        for (int j = 1; j <= i % 5; j++) {
            l.add(new HashMap<String, Object>());
        }
        list.add(l);
        this.setDocList(list);
    }


    public void setDocList(List<List<Map<String, Object>>> docList) {
        this.docList = docList;
    }

    public List<List<Map<String, Object>>> getDocList() {
        if (docList.size() == 0) {
            this.generateDocList();
        }
        return docList;
    }

    public void viewRelatedDocActionListener(ActionEvent actionEvent) {
        Number relatedDocId = (Number)actionEvent.getComponent().getAttributes().get("relatedDocId");
        String libCode = (String)actionEvent.getComponent().getAttributes().get("libCode");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath();
        if ("ILLUSTRATION".equals(libCode)) {
            url = url + "/faces/work/illustration/inline/relatedIllustrationView.jsf?docId=" + relatedDocId;
        } else if ("MATERIAL_FIGURE".equals(libCode)) {
            url = url + "/faces/work/material/inline/relatedMaterialView.jsf?docId=" + relatedDocId;
        } else if ("PHOTOGRAPHY_FIGURE".equals(libCode)) {
            url = url + "/faces/work/photography/inline/relatedPhotographyView.jsf?docId=" + relatedDocId;
        } else if ("BOOK".equals(libCode)) {
            url = url + "/faces/work/book/inline/relatedBookView.jsf?docId=" + relatedDocId;
        } else if ("ELEC_PROD".equals(libCode)) {
            url = url + "/faces/work/elecprod/inline/relatedElecProdView.jsf?docId=" + relatedDocId;
        } else if ("PERIODICAL".equals(libCode)) {
            url = url + "/faces/work/periodical/inline/relatedPeriodicalView.jsf?docId=" + relatedDocId;
        } else if ("NEWSPAPER".equals(libCode)) {
            url = url + "/faces/work/newspaper/inline/relatedNewspaperView.jsf?docId=" + relatedDocId;
        }
        this.openWindow(url);
    }
}
