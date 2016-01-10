package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioVOImpl;
import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AudioViewManagedBean extends CustomManagedBean {
    public AudioViewManagedBean() {
    }

    public void initInfo4View() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                CommonUtil.getCommonAM().preMgmtAttachTable(docId,
                                                            CommonUtil.getLibCode());
                CommonUtil.getAudioAM().initAudio4View(docId);
                HttpServletRequest request =
                    (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession();
                CmsAudioVOImpl audioVO = CommonUtil.getAudioAM().getCmsAudioVO4View();
                audioVO.setbvDocId(docId);
                CmsAudioVORowImpl audioRow = (CmsAudioVORowImpl)audioVO.first();
                if (null != audioRow) {
                    session.setAttribute("flashUrl", audioRow.getPreviewUrl());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
