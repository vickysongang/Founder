package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.event.ActionEvent;

public class CopyrightCompanyEditManagedBean extends CustomManagedBean {
    public CopyrightCompanyEditManagedBean() {
    }

    public void preEditCompany() {
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        oracle.jbo.domain.Number companyId = null;
        if (!"CREATE".equals(mode)) {
            companyId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.companyId}");
        }
        CommonUtil.getCopyrightAM().preEditCompany(mode, companyId, CommonUtil.getCompCode());
    }

    public void saveActionListener(ActionEvent actionEvent) {
        CommonUtil.getCopyrightAM().getTransaction().commit();
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getCopyrightAM().getTransaction().rollback();
    }
}
