package com.zypg.cms.admin.view.bean;


import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;


public class UserManageManagedBean extends CustomManagedBean {
    private RichPanelGroupLayout userManageBtnPanelGroupLayout;
    private RichPopup selectRolePopup;
    private RichPopup deleteCompPopup;
    private RichPopup deleteUserPopup;
    private RichPopup userManagePopup;
    private RichInputText userNameInput;
    private RichInputText diplayNameInput;

    public UserManageManagedBean() {
    }

    public void compSelectListener(SelectionEvent selectionEvent) {
        RichTable rt = (RichTable)selectionEvent.getSource();
        CollectionModel cm = (CollectionModel)rt.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)cm.getWrappedData();
        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        JUCtrlHierNodeBinding selectedRowData = (JUCtrlHierNodeBinding)rt.getSelectedRowData();
        Key rowKey = selectedRowData.getRowKey();
        iter.setCurrentRowWithKey(rowKey.toStringFormat(true));
        Row row = selectedRowData.getCurrentRow();
        this.setExpressionValue("#{pageFlowScope.compCode}", row.getAttribute("CompCode"));
        this.setExpressionValue("#{pageFlowScope.compName}", row.getAttribute("CompName"));
        this.findOperation("findUserByComp").execute();
        this.getAdminAM().getDBTransaction().rollback();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getUserManageBtnPanelGroupLayout());
    }

    public void createUserActionListener(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.operType}", "NEW");
        this.findOperation("createUser").execute();
        this.showPopup(this.getUserManagePopup().getClientId());
    }

    public void editUserActionListenter(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.operType}", "EDIT");
        Number userId = (Number)actionEvent.getComponent().getAttributes().get("userId");
        this.getAdminAM().editUser(userId);
        this.showPopup(this.getUserManagePopup().getClientId());
    }

    public void deleteUserActionListener(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.userId}", actionEvent.getComponent().getAttributes().get("userId"));
        this.setExpressionValue("#{pageFlowScope.userName}",
                                actionEvent.getComponent().getAttributes().get("userName"));
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getDeleteUserPopup().show(hints);
    }

    public void resetPasswordActionListener(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.userId}", actionEvent.getComponent().getAttributes().get("userId"));
        this.findOperation("resetPassword").execute();
        this.getAdminAM().getDBTransaction().commit();
        this.getAdminAM().createAdminOperLog("用户：" + actionEvent.getComponent().getAttributes().get("userName") +
                                             " 密码重置成功", (String)this.resolveExpression("#{pageFlowScope.compCode}"));
        this.showLongMessage("重置成功!");
    }

    public boolean isUserManageFlag() {
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        if (compCode == null) {
            DCIteratorBinding dcib = this.findIterator("CmsCompVO4CommonIterator");
            Row compCurrRow = dcib.getCurrentRow();
            if (compCurrRow != null) {
                compCode = (String)compCurrRow.getAttribute("CompCode");
            }
        }
        String compStr = (String)this.resolveExpression("#{pageFlowScope.compStr}");
        if ("ALL".equals(compStr)) {
            return true;
        } else if (compStr != null && !"".equals(compStr)) {
            for (String s : compStr.split(",")) {
                if (s.equals(compCode)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public void showSelectRolePopup(ActionEvent actionEvent) {
        Number userId = (Number)actionEvent.getComponent().getAttributes().get("userId");
        // 获取当前人
        Map<String, Object> map = this.getAdminAM().getCurrentSelectedUserInfo(userId);
        if (map != null) {
            String userName = (String)map.get("userName");
            String displayName = (String)map.get("displayName");
            if (userId != null) {
                this.setExpressionValue("#{pageFlowScope.userId}", userId);
                this.setExpressionValue("#{pageFlowScope.userName}", userName);
                this.setExpressionValue("#{pageFlowScope.displayName}", displayName);
                this.setExpressionValue("#{pageFlowScope.refreshTime}", new Date().getTime() + "");
                this.showPopup(this.getSelectRolePopup().getClientId());
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getSelectRolePopup());
        }
    }

    public void setUserRoleDialogListener(DialogEvent dialogEvent) {
        if ("OK".equalsIgnoreCase(dialogEvent.getOutcome().toString())) {
            Number userId = (Number)this.resolveExpression("#{pageFlowScope.userId}");
            if (userId != null) {
                OperationBinding ob = this.findOperation("setUserRole");
                ob.getParamsMap().put("userId", userId);
                ob.execute();
                //设置成功 刷新表格
                this.findIterator("CmsUserTVOIterator").executeQuery();
                this.getAdminAM().createAdminOperLog("角色设置..",
                                                     (String)this.resolveExpression("#{pageFlowScope.compCode}"));
                this.showMessage("设置成功");
            }
        }
    }

    public void switchCompActionListener(ActionEvent actionEvent) {
        String compCode = (String)actionEvent.getComponent().getAttributes().get("compCode");
        String compName = (String)actionEvent.getComponent().getAttributes().get("compName");
        String groupFlag = (String)actionEvent.getComponent().getAttributes().get("groupFlag");
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.setExpressionValue("#{pageFlowScope.compName}", compName);
        this.setExpressionValue("#{pageFlowScope.groupFlag}", groupFlag);
        this.findOperation("findUserByComp").execute();
        this.refreshUIComponent(actionEvent.getComponent().getParent());
        this.refreshUIComponent(this.getUserManageBtnPanelGroupLayout());
    }

    public void deleteCompDialogListener(DialogEvent dialogEvent) {
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String compName = (String)this.resolveExpression("#{pageFlowScope.compName}");
        String result = this.getAdminAM().deleteComp(compCode);
        if ("S".equals(result)) {
            this.showMessage("删除成功");
            this.findIterator("CmsCompVO4CommonIterator").executeQuery();
            this.getAdminAM().createAdminOperLog("出版社:\"" + compName + "\" 删除操作成功", null);
        } else {
            this.addFormattedMessage(null, result, FacesMessage.SEVERITY_ERROR);
            this.getAdminAM().createAdminOperLog("出版社:\"" + compName + "\" 删除操作失败", null);
        }
    }

    public void newOrEditReturnListener(ReturnEvent returnEvent) {
        this.findIterator("CmsCompVO4CommonIterator").executeQuery();
    }

    public void deleteCompActionListener(ActionEvent actionEvent) {
        this.showPopup(this.getDeleteCompPopup().getClientId());
    }

    public void setDeleteCompPopup(RichPopup deleteCompPopup) {
        this.deleteCompPopup = deleteCompPopup;
    }

    public RichPopup getDeleteCompPopup() {
        return deleteCompPopup;
    }

    public void setUserManageBtnPanelGroupLayout(RichPanelGroupLayout userManageBtnPanelGroupLayout) {
        this.userManageBtnPanelGroupLayout = userManageBtnPanelGroupLayout;
    }

    public RichPanelGroupLayout getUserManageBtnPanelGroupLayout() {
        return userManageBtnPanelGroupLayout;
    }

    public void setSelectRolePopup(RichPopup selectRolePopup) {
        this.selectRolePopup = selectRolePopup;
    }

    public RichPopup getSelectRolePopup() {
        return selectRolePopup;
    }

    public void initUserManage() {
        this.getAdminAM().findUserByComp(null);
        this.getAdminAM().findComp4Common((String)this.resolveExpression("#{pageFlowScope.compStr}"));
        if ("SYSADMIN".equalsIgnoreCase(this.getAdminAM().getCustomDBTransaction().getUserName())) {
            this.setExpressionValue("#{pageFlowScope.sysadminFlag}", "Y");
        } else {
            this.setExpressionValue("#{pageFlowScope.sysadminFlag}", "N");
        }
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("roleUserID", this.getAdminAM().getUserID());
    }

    public void deleteUserDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            this.findOperation("deleteUser").execute();
            this.getAdminAM().getDBTransaction().commit();
            this.findIterator("CmsUserTVO4DisplayIterator").executeQuery();
            this.showMessage("删除成功");
            this.getAdminAM().createAdminOperLog("用户：" +
                                                 AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userName") +
                                                 " 删除成功", (String)this.resolveExpression("#{pageFlowScope.compCode}"));
        }
    }

    public void userManagePopupCanceledListener(PopupCanceledEvent popupCanceledEvent) {
        this.getAdminAM().getDBTransaction().rollback();
    }

    public boolean userValidator() {
        String userName = (String)this.getUserNameInput().getValue();
        byte[] gbkLength = this.getGBKbyte(userName);
        byte[] utfLength = this.getUTFbyte(userName);
        String type = (String)this.resolveExpression("#{pageFlowScope.operType}");
        System.out.println("type:" + type);
        System.out.println("userName:" + userName + this.getAdminAM().isUserExist(userName));
        if (userName == null || userName.length() == 0) {
            this.addFormattedMessage(this.getUserNameInput().getClientId(), "用户名不能为空", FacesMessage.SEVERITY_WARN);
            return false;
        } else if (gbkLength.length > 40 || utfLength.length > 40) {
            this.addFormattedMessage(this.getUserNameInput().getClientId(), "用户名不能超过40个字符",
                                     FacesMessage.SEVERITY_WARN);
            return false;
        } else if (!userName.matches("^[a-zA-Z]\\w{0,}$")) {
            this.addFormattedMessage(this.getUserNameInput().getClientId(), "用户名应以字母开头,只能包括字母数字与下划线",
                                     FacesMessage.SEVERITY_WARN);
            return false;
        } else if ("NEW".equals(type) && this.getAdminAM().isUserExist(userName)) {
            this.addFormattedMessage(this.getUserNameInput().getClientId(), "用户名在该社或其他社中已存在",
                                     FacesMessage.SEVERITY_WARN);
            return false;
        } else if (this.getDiplayNameInput().getValue() == null || "".equals(this.getDiplayNameInput().getValue())) {
            this.addFormattedMessage(this.getDiplayNameInput().getClientId(), "显示名不能为空", FacesMessage.SEVERITY_WARN);
            return false;
        }
        return true;
    }

    public void comfirmActionListener(ActionEvent actionEvent) {
        if (userValidator()) {
            this.getAdminAM().getDBTransaction().commit();
            this.getUserManagePopup().hide();
            this.getUserManagePopup().cancel();
            this.findIterator("CmsUserTVO4DisplayIterator").executeQuery();
            String type = (String)this.resolveExpression("#{pageFlowScope.operType}");
            if (type.equals("NEW")) {
                this.getAdminAM().createAdminOperLog("新增用户:\"" +
                                                     this.resolveExpression("#{bindings.UserName.inputValue}") +
                                                     "\" 成功!",
                                                     (String)this.resolveExpression("#{pageFlowScope.compCode}"));

            } else if (type.equals("EDIT")) {
                this.getAdminAM().createAdminOperLog("修改用户:\"" +
                                                     this.resolveExpression("#{bindings.UserName.inputValue}") +
                                                     "\" 成功!",
                                                     (String)this.resolveExpression("#{pageFlowScope.compCode}"));
            }
        }
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        this.getAdminAM().getDBTransaction().rollback();
        this.getUserManagePopup().hide();
        this.getUserManagePopup().cancel();
    }

    public void setDeleteUserPopup(RichPopup deleteUserPopup) {
        this.deleteUserPopup = deleteUserPopup;
    }

    public RichPopup getDeleteUserPopup() {
        return deleteUserPopup;
    }

    public static byte[] getGBKbyte(String str) {
        String CHARACTER_CODE = "GBK";
        try {
            return str.getBytes(CHARACTER_CODE);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static byte[] getUTFbyte(String str) {
        String CHARACTER_CODE = "UTF-8";
        try {
            return str.getBytes(CHARACTER_CODE);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public void setUserManagePopup(RichPopup userManagePopup) {
        this.userManagePopup = userManagePopup;
    }

    public RichPopup getUserManagePopup() {
        return userManagePopup;
    }

    public void setUserNameInput(RichInputText userNameInput) {
        this.userNameInput = userNameInput;
    }

    public RichInputText getUserNameInput() {
        return userNameInput;
    }

    public void setDiplayNameInput(RichInputText diplayNameInput) {
        this.diplayNameInput = diplayNameInput;
    }

    public RichInputText getDiplayNameInput() {
        return diplayNameInput;
    }
}
