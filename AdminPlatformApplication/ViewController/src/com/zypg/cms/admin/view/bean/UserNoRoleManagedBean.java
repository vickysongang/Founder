package com.zypg.cms.admin.view.bean;


import com.honythink.applicationframework.hadf.util.ADFUtils;
import com.honythink.applicationframework.hadf.util.JSFUtils;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class UserNoRoleManagedBean {
    private RichTree userNoRoleTree;
    private RowKeySet rks = new RowKeySetImpl(true);

    public UserNoRoleManagedBean() {
    }

    public void setUserNoRoleTree(RichTree userNoRoleTree) {
        this.userNoRoleTree = userNoRoleTree;
    }

    public RichTree getUserNoRoleTree() {
        return userNoRoleTree;
    }

    public void nodeSelectChangeListener(ValueChangeEvent valueChangeEvent) {
        System.out.println("select value---->" + valueChangeEvent.getNewValue());
    }

    public void serverSelectListener(ClientEvent clientEvent) {
        RichSelectBooleanCheckbox checkbox = (RichSelectBooleanCheckbox)clientEvent.getComponent();
        Boolean value = (Boolean)clientEvent.getParameters().get("checkValue");
        FacesCtrlHierNodeBinding node = (FacesCtrlHierNodeBinding)checkbox.getAttributes().get("node");
        Boolean hasChild = node.hasChildren();
        if (hasChild) {
            node.setAttribute("Checkbox", value);
            List childs = node.getChildren();
            if (childs != null && childs.size() > 0) {
                for (Object obj : childs) {
                    FacesCtrlHierNodeBinding cNode = (FacesCtrlHierNodeBinding)obj;
                    cNode.setAttribute("ChildCheckbox", value);
                }
            }
            ADFUtils.findIterator("CmsCompVO4UserManageIterator").executeQuery();
        } else {
            node.setAttribute("ChildCheckbox", value);
        }

        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(this.getUserNoRoleTree());
    }

    public void setRks(RowKeySet rks) {
        this.rks = rks;
    }

    public RowKeySet getRks() {
        return rks;
    }
}
