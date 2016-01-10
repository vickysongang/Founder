package com.zypg.cms.work.view.bean;


import com.honythink.mw.ucm.service.DocService;

import com.zypg.cms.work.model.am.BookAMImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataObject;

public class BookViewManagedBean extends CustomManagedBean {
    public BookViewManagedBean() {
    }

    public void initBookInfo() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        try {
            oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
            CommonUtil.getBookAM().initBookInfo4View(docId);
            long onlineReadDocCount = CommonUtil.getBookAM().initOnlineReadInfo(docId);
            if (onlineReadDocCount > 0) {
                this.setExpressionValue("#{pageFlowScope.onlineReadButtonRendered}", true);
            } else {
                this.setExpressionValue("#{pageFlowScope.onlineReadButtonRendered}", false);
            }
        } catch (SQLException e) {
            System.out.println("docIdStr:" + docIdStr);
        }
    }

    public void viewOriginalFigureActionListener(ActionEvent actionEvent) {
        oracle.jbo.domain.Number docId =
            (oracle.jbo.domain.Number)actionEvent.getComponent().getAttributes().get("docId");
        Map<String, String> map = CommonUtil.getWorkAM().getUcmDidByDocIdAndDocType(docId);
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

    public void onlineReadActionListener(ActionEvent actionEvent) {
        Object docIdStr = this.resolveExpression("#{pageFlowScope.docId}");
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getContextPath() + "/faces/work/common/inline/onlineRead.jsf?docId=" + docIdStr;
        this.openWindow(url);
    }
}
