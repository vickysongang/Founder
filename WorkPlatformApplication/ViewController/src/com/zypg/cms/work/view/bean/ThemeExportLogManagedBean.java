package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

public class ThemeExportLogManagedBean extends CustomManagedBean {
    public ThemeExportLogManagedBean() {
    }

    public void initExportLog() {
        CommonUtil.putObjectToSession("userid", CommonUtil.getThemeAM().getCustomDBTransaction().getUserId());
        String exportType = (String)this.resolveExpression("#{pageFlowScope.exportType}");
        CommonUtil.putObjectToSession("exportType", exportType);
    }
}
