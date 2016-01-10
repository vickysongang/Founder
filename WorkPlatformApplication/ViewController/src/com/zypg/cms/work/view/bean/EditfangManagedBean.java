package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

public class EditfangManagedBean extends CustomManagedBean {
    private CommonRenderer commonRenderer = new CommonRenderer();

    public EditfangManagedBean() {
    }

    public void preEditBook() {
        if (CommonUtil.getBookAM().getDBTransaction().isDirty()) {
            CommonUtil.getBookAM().getDBTransaction().rollback();
        }
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        if (batchFlag == null || "N".equals(batchFlag)) {
            String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
            oracle.jbo.domain.Number docId = null;
            if (!"CREATE".equals(mode)) {
                docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
            }
            CommonUtil.getBookAM().preEditBook(mode, docId, CommonUtil.getCompCode(), CommonUtil.getCompName());
        } else {
            CommonUtil.getBookAM().initBookEditVO();
        }
        CommonUtil.getWorkAM().findCategory4Pick(CommonUtil.getCompCode(), CommonUtil.getLibCode(),
                                                 CommonUtil.getLibTypeCode());
        // Add event code here...
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }
}
