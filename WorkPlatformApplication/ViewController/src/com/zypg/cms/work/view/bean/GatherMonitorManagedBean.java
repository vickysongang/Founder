
package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.WorkAMImpl;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;

import oracle.adf.view.rich.util.ResetUtils;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import oracle.jbo.domain.Number;

public class GatherMonitorManagedBean extends CustomManagedBean {
    private WorkAMImpl workAm;
    private RichPanelTabbed panelTabbed;

    public GatherMonitorManagedBean() {
        this.workAm = CommonUtil.getWorkAM();
    }

    public void initGatherMonitor() {
        String docIds = (String)this.resolveExpression("#{pageFlowScope.docIds}");
        if (docIds != null && docIds.length() > 0) {
            Number docId = workAm.initGatherMonitorHVO(docIds);
            this.setExpressionValue("#{pageFlowScope.docId}", docId);
            workAm.initGatherMonitorLVO(docId, "SUCCESS");
            Map map = workAm.getGatherMonitorCount(docId);
            if (map != null) {
                this.setExpressionValue("#{pageFlowScope.successCount}", map.get("successCount"));
                this.setExpressionValue("#{pageFlowScope.failCount}", map.get("failCount"));
                this.setExpressionValue("#{pageFlowScope.processingCount}", map.get("processingCount"));
            }
            this.setExpressionValue("#{pageFlowScope.tabCode}", "SUCCESS");
        }
    }

    public void refreshActionListener(ActionEvent actionEvent) {
        String tabCode = (String)actionEvent.getComponent().getAttributes().get("tabCode");
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        workAm.initGatherMonitorLVO(docId, tabCode);
        Map map = workAm.getGatherMonitorCount(docId);
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
            Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            workAm.initGatherMonitorLVO(docId, tabCode);
            Map map = workAm.getGatherMonitorCount(docId);
            if (map != null) {
                this.setExpressionValue("#{pageFlowScope.successCount}", map.get("successCount"));
                this.setExpressionValue("#{pageFlowScope.failCount}", map.get("failCount"));
                this.setExpressionValue("#{pageFlowScope.processingCount}", map.get("processingCount"));
            }
        }
    }

    public void switchResActionListener(ActionEvent actionEvent) {
        Number docId = (Number)actionEvent.getComponent().getAttributes().get("docId");
        this.setExpressionValue("#{pageFlowScope.docId}", docId);
        String tabCode = (String)this.resolveExpression("#{pageFlowScope.tabCode}");
        workAm.initGatherMonitorLVO(docId, tabCode == null ? "SUCCESS" : tabCode);
        Map map = workAm.getGatherMonitorCount(docId);
        if (map != null) {
            this.setExpressionValue("#{pageFlowScope.successCount}", map.get("successCount"));
            this.setExpressionValue("#{pageFlowScope.failCount}", map.get("failCount"));
            this.setExpressionValue("#{pageFlowScope.processingCount}", map.get("processingCount"));
        }
    }

    public void setPanelTabbed(RichPanelTabbed panelTabbed) {
        this.panelTabbed = panelTabbed;
    }

    public RichPanelTabbed getPanelTabbed() {
        return panelTabbed;
    }
}
