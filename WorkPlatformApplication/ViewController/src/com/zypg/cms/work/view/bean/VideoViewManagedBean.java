package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.video.query.CmsVideoVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.jbo.Row;


public class VideoViewManagedBean extends CustomManagedBean {

    public VideoViewManagedBean() {
    }

    public void initInfo4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                CommonUtil.getCommonAM().preMgmtAttachTable(docId,
                                                            CommonUtil.getLibCode());
                CommonUtil.getVideoAM().initInfo4View(docId);
                Row[] rows = CommonUtil.getVideoAM().getCmsVideoVO().getFilteredRows("DocId", docId);
                if (rows.length > 0) {
                    HttpServletRequest request =
                        (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    HttpSession session = request.getSession();
                    CmsVideoVORowImpl cmsVideoVORow = (CmsVideoVORowImpl)rows[0];
                    session.setAttribute("flashUrl", cmsVideoVORow.getPreviewUrl());
                }
            } catch (SQLException e) {
                System.out.println("docIdStr:" + docIdStr);
            }
        }
    }
}
