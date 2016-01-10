package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

public class TeachingplanViewManagedBean extends CustomManagedBean {
    public TeachingplanViewManagedBean() {
    }

    public void initInfo4Teachingplan() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                CommonUtil.getTeachingPlanAM().initTeachingplanInfo4View(docId);
                long onlineReadDocCount = CommonUtil.getTeachingPlanAM().initOnlineReadInfo(docId);
                if (onlineReadDocCount > 0) {
                    this.setExpressionValue("#{pageFlowScope.onlineReadButtonRendered}", true);
                } else {
                    this.setExpressionValue("#{pageFlowScope.onlineReadButtonRendered}", false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
