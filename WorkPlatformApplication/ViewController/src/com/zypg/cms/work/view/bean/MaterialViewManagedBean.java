package com.zypg.cms.work.view.bean;


import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.stellent.ridc.model.DataObject;

public class MaterialViewManagedBean extends CustomManagedBean {
    public MaterialViewManagedBean() {
    }

    public void initInfo4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        try {
            oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
            CommonUtil.getCommonAM().preMgmtAttachTable(docId, CommonUtil.getLibCode());
            CommonUtil.getMaterialFigureAM().initInfo4View(docId);
            //根据docId判断是否有exif信息
            if (CommonUtil.getWorkAM().findExif(docId)) {
                this.setExpressionValue("#{pageFlowScope.exif}", true);
            }
        } catch (SQLException e) {
            System.out.println("docIdStr:" + docIdStr);
        }
    }

    public void viewOriginalFigureActionListener(ActionEvent actionEvent) {
        oracle.jbo.domain.Number docId =
            (oracle.jbo.domain.Number)actionEvent.getComponent().getAttributes().get("docId");
        Map<String, String> map = CommonUtil.getWorkAM().getUcmDidByDocId(docId);
        String ucmDid = map.get("ucmDid");
        String attribute15 = map.get("attribute15");
        try {
            HttpServletRequest request =
                (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (attribute15 != null) {
                String url =
                    request.getContextPath() + "/faces/work/common/inline/originalFigureView.jsf?docUrl=" + attribute15;
                this.openWindow(url);
            } else {
                DataObject docInfo = DocService.getInstance().getDocInfoByDID(ucmDid);
                if (docInfo != null) {
                    String docUrl = docInfo.get("DocUrl");
                    String url =
                        request.getContextPath() + "/faces/work/common/inline/originalFigureView.jsf?docUrl=" + docUrl;
                    this.openWindow(url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
