package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.model.viewobject.courseware.CmsCoursewareTVORowImpl;
import com.zypg.cms.work.model.viewobject.relation.CmsDocRelTVOImpl;
import com.zypg.cms.work.model.viewobject.relation.CmsDocRelTVORowImpl;
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

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class CoursewareEditManagedBean extends CustomManagedBean {
    private CommonRenderer commonRenderer = new CommonRenderer();
    private RichInputListOfValues teachingMaterialNameListOfValues;
    private RichInputText coursewareNameInputText;
    private RichSelectOneChoice coursewareTypeSelectOneChoice;
    private RichSelectOneChoice copyrightFlagSelectOneChoice;

    public CoursewareEditManagedBean() {
    }

    public void preEditCourseware() {
        if (CommonUtil.getCoursewareAM().getDBTransaction().isDirty()) {
            CommonUtil.getCoursewareAM().getDBTransaction().rollback();
        }
        String mode = (String)this.resolveExpression("#{pageFlowScope.mode}");
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        Number returnDocId = CommonUtil.getCoursewareAM().preEditCourseware(mode, docId, CommonUtil.getCompCode());
        this.setExpressionValue("#{pageFlowScope.docId}", returnDocId);
    }

    public boolean itemValidator() {
        if (this.getTeachingMaterialNameListOfValues().getValue() == null) {
            this.addFormattedMessage(this.getTeachingMaterialNameListOfValues().getClientId(), "所属教材必须选择",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getCoursewareNameInputText().getValue() == null) {
            this.addFormattedMessage(this.getCoursewareNameInputText().getClientId(), "课件名称必填",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getCoursewareTypeSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getCoursewareTypeSelectOneChoice().getClientId(), "课件类型必须选择",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        } else if (this.getCopyrightFlagSelectOneChoice().getValue() == null) {
            this.addFormattedMessage(this.getCopyrightFlagSelectOneChoice().getClientId(), "版权信息必须选择",
                                     FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }

    public void teachingMaterialNameReturnPopupListener(ReturnPopupEvent returnPopupEvent) {
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
            CommonUtil.getCoursewareAM().preDocCategroupRel(docId, categoryId);
        }
    }

    public String saveAction() {
        if (!itemValidator()) {
            return null;
        }
        if (!"EDIT".equals(this.resolveExpression("#{pageFlowScope.mode}"))) {
            //获取选取的图书Id 进行关联
            CmsCoursewareTVORowImpl row =
                (CmsCoursewareTVORowImpl)CommonUtil.getCoursewareAM().getCmsCoursewareTVO().getCurrentRow();
            Number sourceBookDocId = row.getSourceBookDocId();
            CmsDocRelTVOImpl cmsDocRelTVO = CommonUtil.getCoursewareAM().getCmsDocRelTVO();
            CmsDocRelTVORowImpl cmsDocRelTVORow = (CmsDocRelTVORowImpl)cmsDocRelTVO.createRow();
            cmsDocRelTVORow.setDocId(row.getDocId());
            cmsDocRelTVORow.setParentDocId(sourceBookDocId);
            cmsDocRelTVORow.setRelType("BOOK");
            CmsDocRelTVORowImpl cmsDocRelTVOParentRow = (CmsDocRelTVORowImpl)cmsDocRelTVO.createRow();
            cmsDocRelTVOParentRow.setDocId(sourceBookDocId);
            cmsDocRelTVOParentRow.setParentDocId(row.getDocId());
            cmsDocRelTVOParentRow.setRelType("COURSEWARE");
        }
        CommonUtil.getCoursewareAM().getTransaction().commit();
        this.appendScript("saveExtendAttrs()");
        return null;
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        CommonUtil.getCoursewareAM().getTransaction().rollback();
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setTeachingMaterialNameListOfValues(RichInputListOfValues teachingMaterialNameListOfValues) {
        this.teachingMaterialNameListOfValues = teachingMaterialNameListOfValues;
    }

    public RichInputListOfValues getTeachingMaterialNameListOfValues() {
        return teachingMaterialNameListOfValues;
    }

    public void setCoursewareNameInputText(RichInputText coursewareNameInputText) {
        this.coursewareNameInputText = coursewareNameInputText;
    }

    public RichInputText getCoursewareNameInputText() {
        return coursewareNameInputText;
    }

    public void setCoursewareTypeSelectOneChoice(RichSelectOneChoice coursewareTypeSelectOneChoice) {
        this.coursewareTypeSelectOneChoice = coursewareTypeSelectOneChoice;
    }

    public RichSelectOneChoice getCoursewareTypeSelectOneChoice() {
        return coursewareTypeSelectOneChoice;
    }

    public void setCopyrightFlagSelectOneChoice(RichSelectOneChoice copyrightFlagSelectOneChoice) {
        this.copyrightFlagSelectOneChoice = copyrightFlagSelectOneChoice;
    }

    public RichSelectOneChoice getCopyrightFlagSelectOneChoice() {
        return copyrightFlagSelectOneChoice;
    }
}
