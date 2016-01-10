package com.zypg.cms.admin.view.bean;


import com.honythink.applicationframework.hadf.util.JSFUtils;

import com.zypg.cms.admin.model.viewobject.query.CmsRes4WPVORowImpl;
import com.zypg.cms.admin.model.viewobject.query.CmsResType4WPVORowImpl;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;

import oracle.wsm.common.util.CommonUtil;

import org.apache.myfaces.trinidad.component.UIXIterator;


public class RoleResSettingBean extends CustomManagedBean {
    private UIXIterator cmsResSettingTypeIterator;
    private RichSelectBooleanCheckbox isAllSelectedBox;

    public RoleResSettingBean() {
    }

    public void initResSetting() {
        if (this.getIsAllSelectedBox() != null) {
            this.getIsAllSelectedBox().setValue(false);
            this.refreshUIComponent(this.getIsAllSelectedBox());
        }
    }

    public void selectAllResServerListener(ClientEvent clientEvent) {
        FacesCtrlHierNodeBinding node =
            (FacesCtrlHierNodeBinding)clientEvent.getComponent().getAttributes().get("node");
        CmsResType4WPVORowImpl typeRw = (CmsResType4WPVORowImpl)node.getRow();
        Boolean newValue = !typeRw.getIsSelected();
        typeRw.setIsSelected(newValue);
        List childs = node.getChildren();
        if (childs != null && childs.size() > 0) {
            for (Object obj : childs) {
                FacesCtrlHierNodeBinding childsNode = (FacesCtrlHierNodeBinding)obj;
                CmsRes4WPVORowImpl resRw = (CmsRes4WPVORowImpl)childsNode.getRow();
                resRw.setIsSelected(newValue);
            }
        }
        for (Row rw : typeRw.getCmsRes4WPVO().getAllRowsInRange()) {
            System.out.println("IsSelected--->" + rw.getAttribute("IsSelected"));
        }

        UIXIterator iterator = (UIXIterator)clientEvent.getComponent().findComponent("i5");
        iterator.resetStampState();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(iterator);
    }

    public void saveRoleResActionListener(ActionEvent actionEvent) {
        this.findOperation("saveRoleAndRes4WP").execute();
        this.showMessage("保存成功！");
    }

    public void roleResSettingSelectChangeListener(ValueChangeEvent valueChangeEvent) {
        BindingContainer bindings = getBindings();
        DCIteratorBinding iter = (DCIteratorBinding)bindings.get("CmsResType4WPVO1Iterator");
        for (Row typeRow : iter.getAllRowsInRange()) {
            CmsResType4WPVORowImpl currentTypeRow = (CmsResType4WPVORowImpl)typeRow;
            currentTypeRow.setIsSelected((Boolean)valueChangeEvent.getNewValue());
            RowIterator ri = currentTypeRow.getCmsRes4WPVO();
            for (Row row1 : ri.getAllRowsInRange()) {
                CmsRes4WPVORowImpl row2 = (CmsRes4WPVORowImpl)row1;
                row2.setIsSelected((Boolean)valueChangeEvent.getNewValue());
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCmsResSettingTypeIterator());
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setCmsResSettingTypeIterator(UIXIterator cmsResSettingTypeIterator) {
        this.cmsResSettingTypeIterator = cmsResSettingTypeIterator;
    }

    public UIXIterator getCmsResSettingTypeIterator() {
        return cmsResSettingTypeIterator;
    }


    public void setIsAllSelectedBox(RichSelectBooleanCheckbox isAllSelectedBox) {
        this.isAllSelectedBox = isAllSelectedBox;
    }

    public RichSelectBooleanCheckbox getIsAllSelectedBox() {
        return isAllSelectedBox;
    }
}
