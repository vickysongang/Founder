package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.model.viewobject.audio.query.CmsAudioVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.jbo.Row;

public class ActivityViewManagedBean extends CustomManagedBean {
    public ActivityViewManagedBean() {
    }

    public void initInfo4Activity() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                CommonUtil.getActivityAM().initActivity4View(docId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
