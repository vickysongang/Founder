package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

public class CoursewareViewManagedBean extends CustomManagedBean {
    public CoursewareViewManagedBean() {
    }

    public void initInfo4Courseware() {
        String docIdStr = (String)this.resolveExpression("#{pageFlowScope.docId}");
        if (docIdStr != null) {
            try {
                oracle.jbo.domain.Number docId = new oracle.jbo.domain.Number(docIdStr);
                CommonUtil.getCoursewareAM().initCoursewareInfo4View(docId);
                long onlineReadDocCount = CommonUtil.getCoursewareAM().initOnlineReadInfo(docId);
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
