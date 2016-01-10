package com.zypg.cms.admin.view.bean;


import com.zypg.cms.admin.view.util.Constants;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import com.zypg.cms.admin.view.util.DaoUtil;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.util.ResetUtils;

import oracle.binding.OperationBinding;

import oracle.wsm.common.util.CommonUtil;

import org.apache.myfaces.trinidad.event.DisclosureEvent;


public class HomeManagedBean extends CustomManagedBean {
    private RichRegion adminRegion;
    private static String SPLIT = "@quot@";

    public HomeManagedBean() {
    }

    public void findUserCompStr() {
        OperationBinding op = this.findOperation("findUserCompStr");
        op.execute();
        Map<String, Object> result = (Map<String, Object>)op.getResult();
        String currUserName = (String)result.get("LOGGED_USERNAME");
        System.out.println("currUserName:" + currUserName);
        this.setExpressionValue("#{sessionScope.currUserName}", currUserName.toUpperCase());
        if ("S".equals(result.get("returnCode"))) {
            Map<String, String> map = (Map<String, String>)result.get("returnMsg");
            this.setExpressionValue("#{pageFlowScope.compStr}", map.get("compStr"));
            this.setExpressionValue("#{pageFlowScope.onlyHasGroupFlag}", map.get("onlyHasGroupFlag"));
            System.out.println("findUserCompStr-->compStr:" + this.resolveExpression("#{pageFlowScope.compStr}"));
            System.out.println("findUserCompStr-->onlyHasGroupFlag:" +
                               this.resolveExpression("#{pageFlowScope.onlyHasGroupFlag}"));
        } else {
            HttpServletResponse response =
                (HttpServletResponse)this.getFacesContext().getExternalContext().getResponse();
            try {
                response.sendRedirect("/cms/faces/noAuth.jsf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initPlatformPrivilege() {
        String pubFlag = (String)this.resolveExpression("#{pageFlowScope.pubFlag}");
        String workFlag = (String)this.resolveExpression("#{pageFlowScope.workFlag}");
        String serverAddress = Constants.SERVER_ADDRESS;
        if ("Y".equals(pubFlag)) {
            String pubAddress = "http://" + serverAddress + ":" + Constants.PUB_PLATFORM_PORT;
            String userName = this.getAdminAM().getCustomDBTransaction().getUserName();
            String userPass = DaoUtil.getUserPassword(userName);
            String mingwen = userName.toUpperCase() + SPLIT + userPass;
            String encrypt = encrypt(mingwen);
            String pubUrl =
                pubAddress + "/cms_pub/publish/base/pubLoginUser.jsp?source='home'&encrypt='" + encrypt + "'";
            this.setExpressionValue("#{pageFlowScope.pubUrl}", pubUrl);
        }
        if ("Y".equals(workFlag)) {
            String workAddress = "http://" + serverAddress + ":" + 8001;
            String userName = this.getAdminAM().getCustomDBTransaction().getUserName();
            String userPass = DaoUtil.getUserPassword(userName);
            String mingwen = userName.toUpperCase() + SPLIT + userPass;
            System.out.println(mingwen);
            String encrypt = encrypt(mingwen);
            String workUrl = workAddress + "/cms/login?target=WORK_PLATFORM&encrypt=" + encrypt;
            this.setExpressionValue("#{pageFlowScope.workUrl}", workUrl);
        }
    }

    public void funcNavActionListener(ActionEvent actionEvent) {
        //先回滚 脏事务
        BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("Rollback").execute();
        this.setExpressionValue("#{pageFlowScope.outcome}", actionEvent.getComponent().getAttributes().get("outcome"));
        this.setExpressionValue("#{pageFlowScope.roleType}",
                                actionEvent.getComponent().getAttributes().get("roleType"));
        this.setExpressionValue("#{pageFlowScope.currTime}", System.currentTimeMillis() + "");
        //        this.getAdminRegion().refresh(FacesContext.getCurrentInstance());
        ResetUtils.reset(this.getAdminRegion());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getAdminRegion());
    }


    public void detailItemDisclosureListener(DisclosureEvent disclosureEvent) {
        if (disclosureEvent.isExpanded()) {
            String firstLibCode = (String)disclosureEvent.getComponent().getAttributes().get("tabCode");
            this.setExpressionValue("#{pageFlowScope.tabCode}", firstLibCode);
        }
    }

    public void setAdminRegion(RichRegion adminRegion) {
        this.adminRegion = adminRegion;
    }

    public RichRegion getAdminRegion() {
        return adminRegion;
    }

    public static String encrypt(String mingwen) {
        String ret = null;
        if (mingwen == null) {
            return null;
        }
        byte[] chs;
        try {
            chs = mingwen.getBytes("UTF-8");
            int i = 0;
            for (byte ch : chs) {
                if (ret == null) {
                    ret = (byte)(ch + 5) + "";
                } else {
                    ret = ret + SPLIT + (byte)(ch + 5) + "";
                }
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
