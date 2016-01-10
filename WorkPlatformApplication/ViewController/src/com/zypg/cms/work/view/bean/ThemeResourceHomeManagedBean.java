package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.util.ResetUtils;

public class ThemeResourceHomeManagedBean extends CustomManagedBean {
    private RichRegion resourceRegion;

    public ThemeResourceHomeManagedBean() {
    }

    public void swithcLibActionListener(ActionEvent actionEvent) {
        String libCode = (String)actionEvent.getComponent().getAttributes().get("libCode");
        String displayModel = (String)actionEvent.getComponent().getAttributes().get("displayModel");
        this.setExpressionValue("#{pageFlowScope.libCode}", libCode);
        this.setExpressionValue("#{pageFlowScope.displayModel}", displayModel);
        this.getResourceRegion().refresh(FacesContext.getCurrentInstance());
        this.refreshUIComponent(this.getResourceRegion());
    }

    public void setResourceRegion(RichRegion resourceRegion) {
        this.resourceRegion = resourceRegion;
    }

    public RichRegion getResourceRegion() {
        return resourceRegion;
    }
}
