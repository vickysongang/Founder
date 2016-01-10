package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.model.WorkAMImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class BatchSyncLogManagedBean extends CustomManagedBean {
    private RichPanelTabbed panelTabbed;

    public BatchSyncLogManagedBean() {
    }

    public void initBatchSyncLog() {
        WorkAMImpl workAm = CommonUtil.getWorkAM();
        CommonUtil.getWorkAM().initBatchSyncLog(CommonUtil.getCompCode(), CommonUtil.getLibCode(), "SUCCESS");
        Map map = workAm.getBatchGatherMonitorCount(CommonUtil.getCompCode(), CommonUtil.getLibCode());
        if (map != null) {
            this.setExpressionValue("#{pageFlowScope.successCount}", map.get("successCount"));
            this.setExpressionValue("#{pageFlowScope.failCount}", map.get("failCount"));
            this.setExpressionValue("#{pageFlowScope.processingCount}", map.get("processingCount"));
        }
        this.setExpressionValue("#{pageFlowScope.tabCode}", "SUCCESS");
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        String tabCode = (String)actionEvent.getComponent().getAttributes().get("tabCode");
        WorkAMImpl workAm = CommonUtil.getWorkAM();
        CommonUtil.getWorkAM().initBatchSyncLog(CommonUtil.getCompCode(), CommonUtil.getLibCode(), tabCode);
        Map map = workAm.getBatchGatherMonitorCount(CommonUtil.getCompCode(), CommonUtil.getLibCode());
        if (map != null) {
            this.setExpressionValue("#{pageFlowScope.successCount}", map.get("successCount"));
            this.setExpressionValue("#{pageFlowScope.failCount}", map.get("failCount"));
            this.setExpressionValue("#{pageFlowScope.processingCount}", map.get("processingCount"));
        }
    }


    public void tabDisclousureListener(DisclosureEvent disclosureEvent) {
        if (disclosureEvent.isExpanded()) {
            String tabCode = (String)disclosureEvent.getComponent().getAttributes().get("tabCode");
            this.setExpressionValue("#{pageFlowScope.tabCode}", tabCode);
            WorkAMImpl workAm = CommonUtil.getWorkAM();
            CommonUtil.getWorkAM().initBatchSyncLog(CommonUtil.getCompCode(), CommonUtil.getLibCode(), tabCode);
            Map map = workAm.getBatchGatherMonitorCount(CommonUtil.getCompCode(), CommonUtil.getLibCode());
            if (map != null) {
                this.setExpressionValue("#{pageFlowScope.successCount}", map.get("successCount"));
                this.setExpressionValue("#{pageFlowScope.failCount}", map.get("failCount"));
                this.setExpressionValue("#{pageFlowScope.processingCount}", map.get("processingCount"));
            }
        }
    }

    public void setPanelTabbed(RichPanelTabbed panelTabbed) {
        this.panelTabbed = panelTabbed;
    }

    public RichPanelTabbed getPanelTabbed() {
        return panelTabbed;
    }
}
