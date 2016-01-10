package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.render.ClientEvent;

public class ResCategoryManagedBean extends CustomManagedBean {
    public ResCategoryManagedBean() {
    }

    public void switchCompActionListener(ActionEvent actionEvent) {
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        this.setExpressionValue("#{sessionScope.compCode}", compCode);
        this.refreshUIComponent(actionEvent.getComponent().getParent());
    }

    public void initResCategory() {
        this.setExpressionValue("#{sessionScope.compCode}", this.getAdminAM().getFirstCompCode());
        this.getAdminAM().findComp4Common((String)this.resolveExpression("#{pageFlowScope.compStr}"));
    }
}
