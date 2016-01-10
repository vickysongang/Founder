package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;
import com.zypg.cms.work.view.util.CustomManagedBean;
import com.zypg.cms.work.view.util.UserServiceDao;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;


public class HomeManagedBean extends CustomManagedBean {
    private static String SPLIT = "@quot@";
    private RichRegion workRegion;

    public HomeManagedBean() {

    }

    public void initPlatformPrivilege() {
        String pubFlag = (String)this.resolveExpression("#{pageFlowScope.pubFlag}");
        String adminFlag = (String)this.resolveExpression("#{pageFlowScope.adminFlag}");
        String serverAddress = Constants.SERVER_ADDRESS;
        if ("Y".equals(pubFlag)) {
            String pubAddress = "http://" + serverAddress + ":" + Constants.PUB_PLATFORM_PORT;
            String userName = CommonUtil.getWorkAM().getCustomDBTransaction().getUserName();
            String userPass = UserServiceDao.getUserPassword(userName);
            String mingwen = userName.toUpperCase() + SPLIT + userPass;
            String encrypt = CommonUtil.encrypt(mingwen);
            String pubUrl =
                pubAddress + "/cms_pub/publish/base/pubLoginUser.jsp?source='home'&encrypt='" + encrypt + "'";
            this.setExpressionValue("#{pageFlowScope.pubUrl}", pubUrl);
        }
        if ("Y".equals(adminFlag)) {
            String adminAddress = "http://" + serverAddress + ":" + 8001;
            String userName = CommonUtil.getWorkAM().getCustomDBTransaction().getUserName();
            String userPass = UserServiceDao.getUserPassword(userName);
            String mingwen = userName.toUpperCase() + SPLIT + userPass;
            String encrypt = CommonUtil.encrypt(mingwen);
            String adminUrl = adminAddress + "/cms/login?target=ADMIN_PLATFORM&encrypt=" + encrypt;
            this.setExpressionValue("#{pageFlowScope.adminUrl}", adminUrl);
        }
    }

    public void findUserComp() {
        String compInfo = CommonUtil.getWorkAM().findUserComp();
        String compCode = compInfo.split(",")[0];
        String groupUserFlag = compInfo.split(",")[1];
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.setExpressionValue("#{pageFlowScope.groupUserFlag}", groupUserFlag);
    }

    public void userCompValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.setExpressionValue("#{pageFlowScope.compName}", valueChangeEvent.getNewValue());
        OperationBinding op = this.findOperation("findCompByName");
        op.execute();
        String compInfo = (String)op.getResult();
        String compCode = compInfo.split(",")[0];
        String groupUserFlag = compInfo.split(",")[1];
        this.setExpressionValue("#{pageFlowScope.compCode}", compCode);
        this.setExpressionValue("#{pageFlowScope.groupUserFlag}", groupUserFlag);
        this.appendScript("window.location.reload()");
        //        this.getWorkRegion().refresh(FacesContext.getCurrentInstance());
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getWorkRegion());
    }


    public void setWorkRegion(RichRegion workRegion) {
        this.workRegion = workRegion;
    }

    public RichRegion getWorkRegion() {
        return workRegion;
    }
}
