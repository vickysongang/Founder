package com.zypg.cms.admin.view.bean;


import com.zypg.cms.admin.view.util.CustomManagedBean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;


public class ExtendAttrManagedBean extends CustomManagedBean {
    private RichRegion compExtendAttrRegion;

    public ExtendAttrManagedBean() {
    }

    public void initExtendAttr() {
        this.setExpressionValue("#{pageFlowScope.compCode}", this.getAdminAM().getFirstCompCode());
        this.getAdminAM().findComp4Common((String)this.resolveExpression("#{pageFlowScope.compStr}"));
    }

    public void switchCompActionListener(ActionEvent actionEvent) {
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        this.getCompExtendAttrRegion().refresh(FacesContext.getCurrentInstance());
        this.refreshUIComponent(this.getCompExtendAttrRegion());
    }

    public void setCompExtendAttrRegion(RichRegion compExtendAttrRegion) {
        this.compExtendAttrRegion = compExtendAttrRegion;
    }

    public RichRegion getCompExtendAttrRegion() {
        return compExtendAttrRegion;
    }
}
