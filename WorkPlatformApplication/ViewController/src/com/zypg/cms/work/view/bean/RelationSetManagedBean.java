package com.zypg.cms.work.view.bean;


import com.honythink.applicationframework.hadf.paging.CustomTableModel;

import com.zypg.cms.work.model.viewobject.relation.query.CmsDoc4RelationQueryVORowImpl;
import com.zypg.cms.work.model.viewobject.relation.query.CmsDocRelVORowImpl;
import com.zypg.cms.work.view.util.CommonRenderer;
import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;


public class RelationSetManagedBean extends CustomManagedBean {
    private Integer pageCount = 5;
    private CustomTableModel<CmsDocRelVORowImpl> tableModel =
        new CustomTableModel<CmsDocRelVORowImpl>("WorkAMDataControl", "RelationAM", "CmsDocRelVO", pageCount,
                                                 "findRelDocVC");
    private CommonRenderer commonRenderer = new CommonRenderer(tableModel);

    public RelationSetManagedBean() {

    }

    public void init4RelationSet() {
        if (CommonUtil.getRelationAM().getDBTransaction().isDirty()) {
            CommonUtil.getRelationAM().getDBTransaction().rollback();
        }
        commonRenderer = new CommonRenderer(tableModel);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvParentDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        System.out.println("initBook paramMap:" + paramMap);
        tableModel.setParamMap(paramMap);
        tableModel.getModel();

        CommonUtil.getRelationAM().init4RelationSet();
    }

    public void deleteDocRelActionListener(ActionEvent actionEvent) {
        Map map = actionEvent.getComponent().getAttributes();
        Number docId = (Number)map.get("docId");
        Number parentDocId = (Number)map.get("parentDocId");
        CommonUtil.getRelationAM().deleteDocRel(docId, parentDocId);
        CommonUtil.getRelationAM().getDBTransaction().commit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvParentDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
    }

    public void selectAllActionListener(ActionEvent actionEvent) {
        DCIteratorBinding dcib = this.findIterator("CmsLibRetrivalVOIterator");
        for (Row row : dcib.getAllRowsInRange()) {
            row.setAttribute("Checked", true);
        }
    }

    public void selectNoneActionListener(ActionEvent actionEvent) {
        DCIteratorBinding dcib = this.findIterator("CmsLibRetrivalVOIterator");
        for (Row row : dcib.getAllRowsInRange()) {
            row.setAttribute("Checked", false);
        }
    }

    public void setTableModel(CustomTableModel<CmsDocRelVORowImpl> tableModel) {
        this.tableModel = tableModel;
    }

    public CustomTableModel<CmsDocRelVORowImpl> getTableModel() {
        return tableModel;
    }

    public void setCommonRenderer(CommonRenderer commonRenderer) {
        this.commonRenderer = commonRenderer;
    }

    public CommonRenderer getCommonRenderer() {
        return commonRenderer;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void searchActionListener(ActionEvent actionEvent) {
        DCIteratorBinding dcib = this.findIterator("CmsLibRetrivalVOIterator");
        int k = 0;
        for (Row r : dcib.getAllRowsInRange()) {
            if ((Boolean)r.getAttribute("Checked")) {
                k++;
                break;
            }
        }
        if (k == 0) {
            this.addFormattedMessage(null, "请选择资源类型", FacesMessage.SEVERITY_WARN);
            return;
        }
        CmsDoc4RelationQueryVORowImpl queryRow =
            (CmsDoc4RelationQueryVORowImpl)CommonUtil.getRelationAM().getCmsDoc4RelationQueryVO().getCurrentRow();
        int i = 0;
        for (String attrName : queryRow.getAttributeNames()) {
            if (queryRow.getAttribute(attrName) != null) {
                i++;
                break;
            }
        }
        //        if (i == 0) {
        //            this.addFormattedMessage(null, "请输入检索条件", FacesMessage.SEVERITY_WARN);
        //        } else {
        //            Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        //            CommonUtil.getRelationAM().queryDoc4Relation(docId, "search");
        //        }
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getRelationAM().queryDoc4Relation(docId, "search");
    }

    public void resetActionListener(ActionEvent actionEvent) {
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        CommonUtil.getRelationAM().queryDoc4Relation(docId, null);
    }

    public void setRelationActionListener(ActionEvent actionEvent) {
        Number relDocId = (Number)actionEvent.getComponent().getAttributes().get("relDocId");
        String relLibCode = (String)actionEvent.getComponent().getAttributes().get("relLibCode");
        Number parentDocId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        String libCode = (String)this.resolveExpression("#{pageFlowScope.libCode}");
        CommonUtil.getRelationAM().setRelation(relDocId, parentDocId, libCode, relLibCode);
        CommonUtil.getRelationAM().getDBTransaction().commit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bvParentDocId", this.resolveExpression("#{pageFlowScope.docId}"));
        tableModel.setParamMap(paramMap);
        tableModel.getModel();
    }
}
