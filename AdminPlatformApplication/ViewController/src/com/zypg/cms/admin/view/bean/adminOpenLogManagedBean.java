package com.zypg.cms.admin.view.bean;

import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class adminOpenLogManagedBean {
    private RichInputText operatorInputText;
    private RichInputText operateDescInputText;
    private RichInputText compNameInputText;
    private RichInputText operateTimeInputText;

    public adminOpenLogManagedBean() {
        super();
    }
    /**
     * 查询 openlog
     * */
    public String selectAdminOpenLogAction() {
        Object operator = this.getOperatorInputText().getValue();
        Object operateTime = this.getOperateTimeInputText().getValue();
        Object operateDesc = this.getOperateDescInputText().getValue();
        Object compName = this.getCompNameInputText().getValue();
        
        
        
        
        BindingContainer bindings = getBindings();
        OperationBinding saveBusinessLog = bindings.getOperationBinding("queryOpenLog");
        Map map = saveBusinessLog.getParamsMap();
        map.put("operator", operator);
        map.put("operateTime", operateTime);
        map.put("compName", compName);
        map.put("operateDesc", operateDesc);

        String result = (String)saveBusinessLog.execute();


        // Add event code here...
        return null;
    }
    
    public String resetQueryAction() {
        this.getCompNameInputText().setValue(null);
        this.getOperateDescInputText().setValue(null);
        this.getOperateTimeInputText().setValue(null);
        this.getOperatorInputText().setValue(null);
        
        
        // Add event code here...
        return null;
    }
    
    
    public BindingContainer getBindings() {
            return BindingContext.getCurrent().getCurrentBindingsEntry();
        }

    public void setOperatorInputText(RichInputText operatorInputText) {
        this.operatorInputText = operatorInputText;
    }

    public RichInputText getOperatorInputText() {
        return operatorInputText;
    }


    public void setOperateDescInputText(RichInputText operateDescInputText) {
        this.operateDescInputText = operateDescInputText;
    }

    public RichInputText getOperateDescInputText() {
        return operateDescInputText;
    }

    public void setCompNameInputText(RichInputText compNameInputText) {
        this.compNameInputText = compNameInputText;
    }

    public RichInputText getCompNameInputText() {
        return compNameInputText;
    }

    public void setOperateTimeInputText(RichInputText operateTimeInputText) {
        this.operateTimeInputText = operateTimeInputText;
    }

    public RichInputText getOperateTimeInputText() {
        return operateTimeInputText;
    }


}
