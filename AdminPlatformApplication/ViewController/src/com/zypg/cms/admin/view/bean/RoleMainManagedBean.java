package com.zypg.cms.admin.view.bean;


import com.honythink.applicationframework.hadf.util.JSFUtils;

import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.TreeModel;


public class RoleMainManagedBean extends CustomManagedBean {
    private RichTree compWorkRoleTree;
    private RichPopup roleManagePopup;
    private RichTree compAdminRoleTree;
    private RichPopup userPickPopup;
    private RichRegion routRegion;
    private RichTree compPubRoleTree;
    private RichPopup deleteRolePopup;
    private RichCommandButton roleSetButton;
    private RowKeySet rks = new RowKeySetImpl(true);

    public RoleMainManagedBean() {

    }


    public void findComp4RoleManage() {
        String roleType = (String)this.resolveExpression("#{pageFlowScope.roleType}");
        String compStr = (String)this.resolveExpression("#{pageFlowScope.compStr}");
        this.getAdminAM().findComp4RoleManage(compStr, roleType);
    }

    public void init4RoleMain() {
        if (this.getCompWorkRoleTree() != null) {
            RowKeySet rks = this.getCompWorkRoleTree().getSelectedRowKeys();
            if (rks != null) {
                rks.clear();
            }
        }
        if (this.getCompPubRoleTree() != null) {
            RowKeySet rks = this.getCompPubRoleTree().getSelectedRowKeys();
            if (rks != null) {
                rks.clear();
            }
        }
        if (this.getCompAdminRoleTree() != null) {
            RowKeySet rks = this.getCompAdminRoleTree().getSelectedRowKeys();
            if (rks != null) {
                rks.clear();
            }
        }
    }

    public void createRoleActionListener(ActionEvent actionEvent) {
        RichTree compRoleTree = null;
        if ("WORK_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompWorkRoleTree();
        } else if ("ADMIN_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompAdminRoleTree();
        } else if ("PUB_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompPubRoleTree();
        } else {
            return;
        }

        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        if (rks.size() == 0) {
            this.addFormattedMessage(null, "请先选择出版社再创建角色!", FacesMessage.SEVERITY_INFO);
        } else {
            this.setExpressionValue("#{pageFlowScope.operType}", "NEW");
            this.findOperation("createRole").execute();
            this.showPopup(this.getRoleManagePopup().getClientId());
        }
    }


    public void roleManagePopupCanceledListener(PopupCanceledEvent popupCanceledEvent) {
        this.findOperation("Rollback").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCompWorkRoleTree());
    }

    public void roleManageDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            this.findOperation("Commit").execute();
            String type = (String)this.resolveExpression("#{pageFlowScope.operType}");
            if (type.equals("NEW")) {
                this.getAdminAM().createAdminOperLog("新增角色:\"" +
                                                     this.resolveExpression("#{bindings.RoleName.inputValue}") +
                                                     "\" 成功!",
                                                     (String)this.resolveExpression("#{pageFlowScope.compCode}"));

            } else if (type.equals("EDIT")) {
                this.getAdminAM().createAdminOperLog("修改角色:\"" +
                                                     this.resolveExpression("#{bindings.RoleName.inputValue}") +
                                                     "\" 成功!",
                                                     (String)this.resolveExpression("#{pageFlowScope.compCode}"));
            }

        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            this.findOperation("Rollback").execute();
        }
        if ("WORK_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCompWorkRoleTree());
        } else if ("PUB_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCompPubRoleTree());
        }
    }

    public void updateRoleActionListener(ActionEvent actionEvent) {
        String roleType = (String)actionEvent.getComponent().getAttributes().get("roleType");
        RichTree compRoleTree = null;
        if ("WORK_PLATFORM".equals(roleType)) {
            compRoleTree = this.getCompWorkRoleTree();
        } else if ("PUB_PLATFORM".equals(roleType)) {
            compRoleTree = this.getCompPubRoleTree();
        }
        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        if (rks == null) {
            return;
        }
        if (rks.size() == 0) {
            this.addFormattedMessage(null, "请选择要修改的角色!", FacesMessage.SEVERITY_INFO);
            return;
        }
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            if (key.size() != 2) {
                this.addFormattedMessage(null, "请选择要修改的角色!", FacesMessage.SEVERITY_INFO);
                return;
            } else {
                JUCtrlHierBinding treeBinding = null;
                TreeModel treeModel = (TreeModel)compRoleTree.getValue();
                treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
                JUCtrlHierNodeBinding nodeBinding = null;
                nodeBinding = treeBinding.findNodeByKeyPath(key);
                String Attribute1 = (String)nodeBinding.getRow().getAttribute("Attribute1");
                if (Attribute1 != null && Attribute1.length() > 0) {
                    this.addFormattedMessage(null, "该角色是固化角色，不能被修改!", FacesMessage.SEVERITY_WARN);
                    return;
                }
            }
        }
        this.setExpressionValue("#{pageFlowScope.operType}", "EDIT");
        this.findOperation("preUpdateRole").execute();
        this.showPopup(this.getRoleManagePopup().getClientId());
    }

    public void deleteRoleActionListener(ActionEvent actionEvent) {
        String roleType = (String)actionEvent.getComponent().getAttributes().get("roleType");
        RichTree compRoleTree = null;
        if ("WORK_PLATFORM".equals(roleType)) {
            compRoleTree = this.getCompWorkRoleTree();
        } else if ("PUB_PLATFORM".equals(roleType)) {
            compRoleTree = this.getCompPubRoleTree();
        }
        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        if (rks != null) {
            if (rks.size() == 0) {
                this.addFormattedMessage(null, "请选择要删除的角色!", FacesMessage.SEVERITY_INFO);
                return;
            }
            Iterator keys = rks.iterator();
            while (keys.hasNext()) {
                List key = (List)keys.next();
                if (key.size() != 2) {
                    this.addFormattedMessage(null, "请选择要删除的角色!", FacesMessage.SEVERITY_INFO);
                    return;
                } else {
                    JUCtrlHierBinding treeBinding = null;
                    TreeModel treeModel = (TreeModel)compRoleTree.getValue();
                    treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
                    JUCtrlHierNodeBinding nodeBinding = null;
                    nodeBinding = treeBinding.findNodeByKeyPath(key);
                    String Attribute1 = (String)nodeBinding.getRow().getAttribute("Attribute1");
                    if (Attribute1 != null && Attribute1.length() > 0) {
                        this.addFormattedMessage(null, "该角色是固化角色，不能被删除!", FacesMessage.SEVERITY_WARN);
                        return;
                    }
                }
            }
            this.showPopup(this.getDeleteRolePopup().getClientId());
        }
    }

    public void deleteRoleDialogListener(DialogEvent dialogEvent) {
        this.findOperation("deleteRole").execute();
        this.findOperation("Commit").execute();
        //查询iterater
        this.findIterator("CmsCompVORoleManageIterator").executeQuery();
        if ("WORK_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCompWorkRoleTree());
        }
        this.getAdminAM().createAdminOperLog("角色:\"" + this.resolveExpression("#{pageFlowScope.roleName}") + "\" 删除成功",
                                             (String)this.resolveExpression("#{pageFlowScope.compCode}"));
    }

    public void compRoleTreeSelectionListener(SelectionEvent selectionEvent) {
        RichTree compRoleTree = null;
        if ("WORK_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompWorkRoleTree();
        } else if ("ADMIN_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompAdminRoleTree();
        } else if ("PUB_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompPubRoleTree();
        } else {
            return;
        }

        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)compRoleTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            if (!nodeBinding.hasChildren()) {
                //获取父node 的compCode值
                JUCtrlHierNodeBinding parent = nodeBinding.getParent();
                this.setExpressionValue("#{pageFlowScope.compCode}", parent.getRow().getAttribute("CompCode"));
                this.setExpressionValue("#{pageFlowScope.roleId}", nodeBinding.getRow().getAttribute("RoleId"));
                this.setExpressionValue("#{pageFlowScope.roleName}", nodeBinding.getRow().getAttribute("RoleName"));
                this.setExpressionValue("#{pageFlowScope.isApprover}",
                                        nodeBinding.getRow().getAttribute("Attribute1") != null);
                this.setExpressionValue("#{pageFlowScope.groupFlag}", parent.getRow().getAttribute("GroupFlag"));
                this.findOperation("findUserByRoleId").execute();
            } else {
                this.setExpressionValue("#{pageFlowScope.roleId}", new Number(-999)); //代表没有选中role
                this.setExpressionValue("#{pageFlowScope.compCode}", nodeBinding.getRow().getAttribute("CompCode"));
                this.findOperation("findUserByRoleId").execute();
            }
        }
        //刷新roleUser
        this.setExpressionValue("#{pageFlowScope.routType}", "ROLEUSER");
        this.getRoutRegion().refresh(FacesContext.getCurrentInstance());
        if (roleSetButton.isRendered()) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(roleSetButton);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getRoutRegion());
    }


    public void grantUserActionListener(ActionEvent actionEvent) {
        RichTree compRoleTree = null;
        if ("WORK_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompWorkRoleTree();
        } else if ("ADMIN_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompAdminRoleTree();
        } else if ("PUB_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompPubRoleTree();
        } else {
            return;
        }
        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        if (rks.size() == 0) {
            this.addFormattedMessage(null, "请先选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
            return;
        }
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            if (key.size() != 2) {
                this.addFormattedMessage(null, "请先选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
                return;
            }
        }
        //弹出popup之前，清楚批量操作记录
        this.findOperation("deleteUserSesson4AuthorizeUser").execute();
        JSFUtils.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis());
        this.showPopup(this.getUserPickPopup().getClientId());

        //内容区域为用户列表
        String routType = (String)this.resolveExpression("#{pageFlowScope.routType}");
        if (!"ROLEUSER".equalsIgnoreCase(routType)) {
            this.setExpressionValue("#{pageFlowScope.routType}", "ROLEUSER");
            this.getRoutRegion().refresh(FacesContext.getCurrentInstance());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getRoutRegion());
        }
    }


    public void userPickupDialogListener(DialogEvent dialogEvent) {
        if ("OK".equalsIgnoreCase(dialogEvent.getOutcome().toString())) {
            Number roleId = (Number)this.resolveExpression("#{pageFlowScope.roleId}");
            OperationBinding op = this.findOperation("Authorize4GenerateUsers");
            op.getParamsMap().put("roleId", roleId);
            op.execute();
        }
    }

    public void deleteAdminAuthActionListener(ActionEvent actionEvent) {
        Number roleId = (Number)this.resolveExpression("#{pageFlowScope.roleId}");
        Number userId = (Number)actionEvent.getComponent().getAttributes().get("userId");
        OperationBinding op = this.findOperation("revokeRole");
        op.getParamsMap().put("roleId", roleId);
        op.getParamsMap().put("userId", userId);
        op.execute();
        this.findOperation("Commit").execute();
        DCIteratorBinding iter = this.findIterator("CmsRoleUserVOIterator");
        iter.executeQuery();
    }


    public void setRoleOperationActionListener(ActionEvent actionEvent) {
        RichTree compRoleTree = null;
        if ("WORK_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompWorkRoleTree();
        } else if ("PUB_PLATFORM".equals(this.resolveExpression("#{pageFlowScope.roleType}"))) {
            compRoleTree = this.getCompPubRoleTree();
        } else {
            return;
        }
        this.setExpressionValue("#{pageFlowScope.platformType}", this.resolveExpression("#{pageFlowScope.roleType}"));
        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        if (rks.size() == 0) {
            this.addFormattedMessage(null, "请先选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
            return;
        }
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            if (key.size() != 2) {
                this.addFormattedMessage(null, "请先选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
                return;
            }
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)compRoleTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            if (!nodeBinding.hasChildren()) {
                //获取父node 的compCode值
                JUCtrlHierNodeBinding parent = nodeBinding.getParent();
                this.setExpressionValue("#{pageFlowScope.compCode}", parent.getRow().getAttribute("CompCode"));
                this.setExpressionValue("#{pageFlowScope.roleId}", nodeBinding.getRow().getAttribute("RoleId"));
                //                this.findOperation("findUserByRoleId").execute();
            } else {
                //                this.setExpressionValue("#{pageFlowScope.roleId}", new Number(-999)); //代表没有选中role
                this.setExpressionValue("#{pageFlowScope.compCode}", nodeBinding.getRow().getAttribute("CompCode"));
                //                this.findOperation("findUserByRoleId").execute();
            }
        }
        Object compCodeObj = this.resolveExpression("#{pageFlowScope.compCode}");
        Object roleIdObj = this.resolveExpression("#{pageFlowScope.roleId}");
        if (compCodeObj == null || roleIdObj == null) {
            this.addFormattedMessage(null, "请重新选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
            return;
        }
        this.setExpressionValue("#{pageFlowScope.routType}", "ROLEAUTH");
        this.getRoutRegion().refresh(FacesContext.getCurrentInstance());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getRoutRegion());
    }

    public void setAdminRoleOperationActionListener(ActionEvent actionEvent) {
        RichTree compRoleTree = this.getCompAdminRoleTree();
        RowKeySet rks = compRoleTree.getSelectedRowKeys();
        if (rks.size() == 0) {
            this.addFormattedMessage(null, "请先选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
            return;
        }
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            if (key.size() != 2) {
                this.addFormattedMessage(null, "请先选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
                return;
            }
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)compRoleTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            if (!nodeBinding.hasChildren()) {
                //获取父node 的compCode值
                JUCtrlHierNodeBinding parent = nodeBinding.getParent();
                this.setExpressionValue("#{pageFlowScope.compCode}", parent.getRow().getAttribute("CompCode"));
                this.setExpressionValue("#{pageFlowScope.roleId}", nodeBinding.getRow().getAttribute("RoleId"));
            } else {
                this.setExpressionValue("#{pageFlowScope.compCode}", nodeBinding.getRow().getAttribute("CompCode"));
            }
        }
        Object compCodeObj = this.resolveExpression("#{pageFlowScope.compCode}");
        Object roleIdObj = this.resolveExpression("#{pageFlowScope.roleId}");
        if (compCodeObj == null || roleIdObj == null) {
            this.addFormattedMessage(null, "请重新选择角色再进行授权操作!", FacesMessage.SEVERITY_INFO);
            return;
        }
        String platformType = (String)actionEvent.getComponent().getAttributes().get("platformType");
        this.setExpressionValue("#{pageFlowScope.platformType}", platformType);
        this.setExpressionValue("#{pageFlowScope.routType}", "ROLEAUTH");
        this.getRoutRegion().refresh(FacesContext.getCurrentInstance());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getRoutRegion());
    }


    public void setCompPubRoleTree(RichTree compPubRoleTree) {
        this.compPubRoleTree = compPubRoleTree;
    }

    public RichTree getCompPubRoleTree() {
        return compPubRoleTree;
    }

    public void setDeleteRolePopup(RichPopup deleteRolePopup) {
        this.deleteRolePopup = deleteRolePopup;
    }

    public RichPopup getDeleteRolePopup() {
        return deleteRolePopup;
    }

    public void setCompWorkRoleTree(RichTree compWorkRoleTree) {
        this.compWorkRoleTree = compWorkRoleTree;
    }

    public RichTree getCompWorkRoleTree() {
        return compWorkRoleTree;
    }

    public void setRoleManagePopup(RichPopup roleManagePopup) {
        this.roleManagePopup = roleManagePopup;
    }

    public RichPopup getRoleManagePopup() {
        return roleManagePopup;
    }

    public void setRoutRegion(RichRegion routRegion) {
        this.routRegion = routRegion;
    }

    public RichRegion getRoutRegion() {
        return routRegion;
    }

    public void setUserPickPopup(RichPopup userPickPopup) {
        this.userPickPopup = userPickPopup;
    }

    public RichPopup getUserPickPopup() {
        return userPickPopup;
    }

    public void setCompAdminRoleTree(RichTree compAdminRoleTree) {
        this.compAdminRoleTree = compAdminRoleTree;
    }

    public RichTree getCompAdminRoleTree() {
        return compAdminRoleTree;
    }

    public void setRoleSetButton(RichCommandButton roleSetButton) {
        this.roleSetButton = roleSetButton;
    }

    public RichCommandButton getRoleSetButton() {
        return roleSetButton;
    }

    public void setRks(RowKeySet rks) {
        this.rks = rks;
    }

    public RowKeySet getRks() {
        return rks;
    }

}
