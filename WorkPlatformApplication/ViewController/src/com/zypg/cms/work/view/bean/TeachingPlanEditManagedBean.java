package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.relation.CmsDocRelTVOImpl;
import com.zypg.cms.work.model.viewobject.relation.CmsDocRelTVORowImpl;
import com.zypg.cms.work.model.viewobject.teachingplan.CmsTeachingPlanTVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.event.ReturnPopupEvent;
import oracle.adf.view.rich.model.ListOfValuesModel;

import oracle.jbo.domain.Number;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;


public class TeachingPlanEditManagedBean extends CustomManagedBean {
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputListOfValues sourceBookNameListOfValues;
    private RichInputText teachingPlanNameInputText;
    private RichSelectOneChoice teachingPlanTypeSelectOneChoice;
    private RichSelectOneChoice copyrightFlagSelectOneChoice;

    public TeachingPlanEditManagedBean() {
    }

    public void preEditTeachingPlan() {
        if (CommonUtil.getTeachingPlanAM().getDBTransaction().isDirty()) {
            CommonUtil.getTeachingPlanAM().getDBTransaction().rollback();
        }
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Number returnDocId = CommonUtil.getTeachingPlanAM().preEditTeachingPlan(mode, docId, CommonUtil.getCompCode());
        this.setExpressionValue("#{pageFlowScope.docId}", returnDocId);
    }

    public void sourceBookNameReturnPopupListener(ReturnPopupEvent returnPopupEvent) {
        RichInputListOfValues lovField = (RichInputListOfValues)returnPopupEvent.getSource();
        ListOfValuesModel lovModel = lovField.getModel();
        CollectionModel collectionModel = lovModel.getTableModel().getCollectionModel();
        JUCtrlHierBinding treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();
        RowKeySet rks = (RowKeySet)returnPopupEvent.getReturnValue();
        List tableRowKey = (List)rks.iterator().next();
        DCIteratorBinding dciter = treeBinding.getDCIteratorBinding();
        Key key = (Key)tableRowKey.get(0);
        Row row = dciter.findRowByKeyString(key.toStringFormat(true));
        if (row != null) {
            Number categoryId = (Number)row.getAttribute("CategoryId");
            Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
            CommonUtil.getTeachingPlanAM().preDocCategroupRel(docId, categoryId);
        }
    }

    public boolean itemValidator() {
        if (this.getSourceBookNameListOfValues().getValue() == null) {
            this.addFormattedMessage(this.getSourceBookNameListOfValues().getClientId(), "来源图书必须选择",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getTeachingPlanNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getTeachingPlanNameInputText().getClientId(), "教案名称必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getTeachingPlanTypeSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getTeachingPlanTypeSelectOneChoice().getClientId(), "教案类型必须选择",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getCopyrightFlagSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getCopyrightFlagSelectOneChoice().getClientId(), "版权信息必须选择",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public String saveAction() {
        if (!itemValidator()) {
            return null;
        }
        if ("CREATE".equals(this.resolveExpression("#{pageFlowScope.mode}"))) {
            //获取选取的图书Id 进行关联
            CmsTeachingPlanTVORowImpl row =
                (CmsTeachingPlanTVORowImpl)CommonUtil.getTeachingPlanAM().getCmsTeachingPlanTVO().getCurrentRow();
            oracle.jbo.domain.Number sourceBookDocId = row.getSourceBookDocId();
            CmsDocRelTVOImpl cmsDocRelTVO = CommonUtil.getTeachingPlanAM().getCmsDocRelTVO();
            CmsDocRelTVORowImpl cmsDocRelTVORow = (CmsDocRelTVORowImpl)cmsDocRelTVO.createRow();
            cmsDocRelTVORow.setDocId(row.getDocId());
            cmsDocRelTVORow.setParentDocId(sourceBookDocId);
            cmsDocRelTVORow.setRelType("BOOK");
            CmsDocRelTVORowImpl cmsDocRelTVOParentRow = (CmsDocRelTVORowImpl)cmsDocRelTVO.createRow();
            cmsDocRelTVOParentRow.setDocId(sourceBookDocId);
            cmsDocRelTVOParentRow.setParentDocId(row.getDocId());
            cmsDocRelTVOParentRow.setRelType("TEACHING_PLAN");
        }
        CommonUtil.getTeachingPlanAM().getTransaction().commit();
        this.appendScript("saveExtendAttrs()");
        return null;
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getTeachingPlanAM().getTransaction().rollback();
        CommonUtil.getRelationAM().getTransaction().rollback();
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setSourceBookNameListOfValues(RichInputListOfValues sourceBookNameListOfValues) {
        this.sourceBookNameListOfValues = sourceBookNameListOfValues;
    }

    public RichInputListOfValues getSourceBookNameListOfValues() {
        return sourceBookNameListOfValues;
    }

    public void setTeachingPlanNameInputText(RichInputText teachingPlanNameInputText) {
        this.teachingPlanNameInputText = teachingPlanNameInputText;
    }

    public RichInputText getTeachingPlanNameInputText() {
        return teachingPlanNameInputText;
    }

    public void setTeachingPlanTypeSelectOneChoice(RichSelectOneChoice teachingPlanTypeSelectOneChoice) {
        this.teachingPlanTypeSelectOneChoice = teachingPlanTypeSelectOneChoice;
    }

    public RichSelectOneChoice getTeachingPlanTypeSelectOneChoice() {
        return teachingPlanTypeSelectOneChoice;
    }

    public void setCopyrightFlagSelectOneChoice(RichSelectOneChoice copyrightFlagSelectOneChoice) {
        this.copyrightFlagSelectOneChoice = copyrightFlagSelectOneChoice;
    }

    public RichSelectOneChoice getCopyrightFlagSelectOneChoice() {
        return copyrightFlagSelectOneChoice;
    }
}
