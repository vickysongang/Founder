package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.copyright.Cms3rdAuthorizationOrderTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.jbo.domain.Number;

import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;


public class CopyrightOrderEditManagedBean extends CustomManagedBean {
    public CopyrightOrderEditManagedBean() {
    }

    public void preEditOrder() {
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Cms3rdAuthorizationOrderTVORowImpl row =
            CommonUtil.getCopyrightAM().preEditOrder(mode, docId, CommonUtil.getCompCode());
        if (!mode.equals("CREATE")) {
            if (null != row.getAuthorizedArea() && null != row.getAuthorizedLang()) {
                String[] area = row.getAuthorizedArea().split(",");
                String[] lang = row.getAuthorizedLang().split(",");
                this.setExpressionValue("#{pageFlowScope.area}", area);
                this.setExpressionValue("#{pageFlowScope.lang}", lang);
            }
        }
    }

    public void saveActionListener(ActionEvent actionEvent) {
        String area = "";
        String lang = "";
        RichSelectManyCheckbox box = (RichSelectManyCheckbox)actionEvent.getComponent().findComponent("smc3");
        List areaList = (List)box.getValue();
        if (null != box.getValue()) {
            for (int i = 0; i < areaList.size(); i++) {
                if (i < areaList.size() - 1) {
                    if (null != areaList.get(i))
                        area += areaList.get(i) + ",";
                } else if (i == areaList.size() - 1) {
                    if (null != areaList.get(i))
                        area += areaList.get(i);
                }
            }
        }
        RichSelectManyCheckbox langBox = (RichSelectManyCheckbox)actionEvent.getComponent().findComponent("smc1");
        if (null != langBox.getValue()) {
            List langList = (List)langBox.getValue();
            for (int i = 0; i < langList.size(); i++) {
                if (i < langList.size() - 1) {
                    if (null != langList.get(i))
                        lang += langList.get(i) + ",";
                } else if (i == langList.size() - 1) {
                    if (null != langList.get(i))
                        lang += langList.get(i);
                }
            }
        }
        Cms3rdAuthorizationOrderTVORowImpl row =
            (Cms3rdAuthorizationOrderTVORowImpl)CommonUtil.getCopyrightAM().getCms3rdAuthorizationOrderTVO().getCurrentRow();
        row.setAuthorizedArea(area);
        row.setAuthorizedLang(lang);
        CommonUtil.getCopyrightAM().getTransaction().commit();
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getCopyrightAM().getTransaction().rollback();
    }
}
