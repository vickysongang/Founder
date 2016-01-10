package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;

import com.zypg.cms.work.view.util.CustomManagedBean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import org.apache.myfaces.trinidad.event.DisclosureEvent;


public class XcInfoManagedBean extends CustomManagedBean {
    private RichPopup xcInfoPopup;
    private RichTable xcInfoTable;
    private RichPopup deleteXcInfoPopup;

    public XcInfoManagedBean() {
    }

    public void initXcInfo() {
        String initType = "BOOK_REVIEW";
        if ("ELEC_PROD".equals(CommonUtil.getLibCode())) {
            initType = "EDITOR_CHOICE";
        }
        this.setExpressionValue("#{pageFlowScope.xcInfoType}", initType);
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getBookAM().initXcInfo(docId, initType);
    }

    public void tabDisclosureListener(DisclosureEvent disclosureEvent) {
        if (disclosureEvent.isExpanded()) {
            String xcInfoType = (String)this.resolveExpression("#{pageFlowScope.xcInfoType}");
            oracle.jbo.domain.Number docId =
                (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
            CommonUtil.getBookAM().initXcInfo(docId, xcInfoType);
        }
    }

    public void addXcInfoActionListener(ActionEvent actionEvent) {
        String xcInfoType = (String)this.resolveExpression("#{pageFlowScope.xcInfoType}");
        oracle.jbo.domain.Number docId = (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getBookAM().addXcInfo(docId, xcInfoType);
        this.showPopup(this.getXcInfoPopup().getClientId());
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getBookAM().getDBTransaction().rollback();
    }

    public void popupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        CommonUtil.getBookAM().resetXcInfo();
    }

    public void operXcInfoDialogListener(DialogEvent dialogEvent) {
        CommonUtil.getBookAM().getDBTransaction().commit();
        this.findIterator("CmsXcInfoTVO4QueryIterator").executeQuery();
    }


    public void updateXcInfoActionListener(ActionEvent actionEvent) {
        oracle.jbo.domain.Number xcInfoId =
            (oracle.jbo.domain.Number)actionEvent.getComponent().getAttributes().get("xcInfoId");
        CommonUtil.getBookAM().updateXcInfo(xcInfoId);
        this.showPopup(this.getXcInfoPopup().getClientId());
    }

    public void deleteXcInfoActionListener(ActionEvent actionEvent) {
        oracle.jbo.domain.Number xcInfoId =
            (oracle.jbo.domain.Number)actionEvent.getComponent().getAttributes().get("xcInfoId");
        this.setExpressionValue("#{pageFlowScope.xcInfoId}", xcInfoId);
        this.showPopup(this.getDeleteXcInfoPopup().getClientId());
    }

    public void deleteXcInfoDialogListener(DialogEvent dialogEvent) {
        if ("ok".equals(dialogEvent.getOutcome().toString())) {
            oracle.jbo.domain.Number xcInfoId =
                (oracle.jbo.domain.Number)this.resolveExpression("#{pageFlowScope.xcInfoId}");
            CommonUtil.getBookAM().deleteXcInfo(xcInfoId);
            CommonUtil.getBookAM().getDBTransaction().commit();
            this.findIterator("CmsXcInfoTVO4QueryIterator").executeQuery();
            this.showMessage("删除成功");
        }
    }

    public void setXcInfoPopup(RichPopup xcInfoPopup) {
        this.xcInfoPopup = xcInfoPopup;
    }

    public RichPopup getXcInfoPopup() {
        return xcInfoPopup;
    }

    public void setXcInfoTable(RichTable xcInfoTable) {
        this.xcInfoTable = xcInfoTable;
    }

    public RichTable getXcInfoTable() {
        return xcInfoTable;
    }

    public void setDeleteXcInfoPopup(RichPopup deleteXcInfoPopup) {
        this.deleteXcInfoPopup = deleteXcInfoPopup;
    }

    public RichPopup getDeleteXcInfoPopup() {
        return deleteXcInfoPopup;
    }
}
