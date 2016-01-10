package com.zypg.cms.admin.view.bean;

import com.zypg.cms.admin.view.util.CategoryProcessor;
import com.zypg.cms.admin.view.util.CustomManagedBean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.domain.Number;

public class CompEditManagedBean extends CustomManagedBean {
    private RichInputText compCodeInputText;
    private RichInputText compNameInputText;

    public CompEditManagedBean() {
    }

    public void initCompEdit() {
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        this.getAdminAM().preEditComp(mode, compCode);
    }

    public boolean requiredItemValidator() {
        if (this.isEmpty(this.getCompCodeInputText().getValue())) {
            this.addFormattedMessage(this.getCompCodeInputText().getClientId(), "出版社代码必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (isEmpty(this.getCompNameInputText().getValue())) {
            this.addFormattedMessage(this.getCompNameInputText().getClientId(), "出版社名称必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (requiredItemValidator()) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            Map<String, String> map = new HashMap<String, String>();
            if ("NEW".equals(mode)) {
                map = this.getAdminAM().addComp();
                String compCode = map.get("compCode");
                String returnCode = map.get("returnCode");
                String returnMsg = map.get("returnMsg");
                if ("S".equals(returnCode)) {
                    this.showMessage("保存成功");
                    CategoryProcessor categoryProcessor = new CategoryProcessor(compCode);
                    Thread thread = new Thread(categoryProcessor);
                    thread.start();
                    this.getAdminAM().createAdminOperLog("新增出版社:\"" +
                                                         this.resolveExpression("#{bindings.CompName.inputValue}" +
                                                                                "\""), compCode);
                    return "toReturn";
                } else {
                    this.addFormattedMessage(null, returnMsg, FacesMessage.SEVERITY_ERROR);
                }

            } else {
                map = this.getAdminAM().modifyComp();
                String compCode = map.get("compCode");
                String returnCode = map.get("returnCode");
                String returnMsg = map.get("returnMsg");
                if ("S".equals(returnCode)) {
                    this.showMessage("保存成功");
                    this.getAdminAM().createAdminOperLog("编辑出版社:\"" +
                                                         this.resolveExpression("#{bindings.CompName.inputValue}" +
                                                                                "\""), compCode);
                    return "toReturn";
                } else {
                    this.addFormattedMessage(null, returnMsg, FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        return null;
    }

    public String cancelAction() {
        this.getAdminAM().getDBTransaction().rollback();
        return "toReturn";
    }

    public void setCompCodeInputText(RichInputText compCodeInputText) {
        this.compCodeInputText = compCodeInputText;
    }

    public RichInputText getCompCodeInputText() {
        return compCodeInputText;
    }

    public void setCompNameInputText(RichInputText compNameInputText) {
        this.compNameInputText = compNameInputText;
    }

    public RichInputText getCompNameInputText() {
        return compNameInputText;
    }
}
