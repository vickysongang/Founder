package com.zypg.cms.work.view.bean;

import com.zypg.cms.work.model.viewobject.book.CmsChapterTVOImpl;
import com.zypg.cms.work.model.viewobject.book.CmsChapterTVORowImpl;
import com.zypg.cms.work.view.util.CommonUtil;

import com.zypg.cms.work.view.util.CustomManagedBean;

import java.sql.SQLException;

import javax.faces.component.UIComponent;

import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;

import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.dnd.DnDAction;
import oracle.adf.view.rich.event.DropEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.component.UIXIterator;

public class ChapterSortBean extends CustomManagedBean {
    private UIXIterator iteratorBinding;

    public ChapterSortBean() {
    }

    public void init() {
        //进来之前 rollback
        CommonUtil.getBookAM().getDBTransaction().rollback();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        Object docIdObj = adfFacesContext.getPageFlowScope().get("pDocId");
        Number docId;

        try {
            docId = (docIdObj == null ? null : new Number(docIdObj.toString()));
            CommonUtil.getBookAM().initChapterSort(docId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DnDAction rowDropListener(DropEvent dropEvent) {
        // Add event code here...
        String dragClientId = dropEvent.getDragClientId();
        String dropClientId = dropEvent.getDropClientId();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.getPageFlowScope().put("dragClientId", dragClientId);
        adfFacesContext.getPageFlowScope().put("dropClientId", dropClientId);
        this.iteratorBinding.visitChildren(VisitContext.createVisitContext(FacesContext.getCurrentInstance()),
                                           iteratorBinding, new VisitCallback() {
                @Override
                public VisitResult visit(VisitContext visitContext, UIComponent uiComponent) {
                    AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
                    String dragClientId = (String)adfFacesContext.getPageFlowScope().get("dragClientId");
                    String dropClientId = (String)adfFacesContext.getPageFlowScope().get("dropClientId");
                    if (dragClientId.equals(uiComponent.getClientId())) {
                        adfFacesContext.getPageFlowScope().put("dragRowKey",
                                                               uiComponent.getAttributes().get("rowKey"));
                    } else if (dropClientId.equals(uiComponent.getClientId())) {
                        adfFacesContext.getPageFlowScope().put("dropRowKey",
                                                               uiComponent.getAttributes().get("rowKey"));
                    }
                    return null;
                }
            });

        Object dragRowKey = adfFacesContext.getPageFlowScope().get("dragRowKey");
        Object dropRowKey = adfFacesContext.getPageFlowScope().get("dropRowKey");

        if (dragRowKey == null || dropRowKey == null) {
            return DnDAction.NONE;
        }

        BindingContext bindingContext = BindingContext.getCurrent();
        DCBindingContainer bc = (DCBindingContainer)bindingContext.getCurrentBindingsEntry();
        DCIteratorBinding iter = bc.findIteratorBinding("CmsChapterTVOIterator");

        CmsChapterTVORowImpl dragRw =
            (CmsChapterTVORowImpl)CommonUtil.getBookAM().getCmsChapterTVO().getRow(new Key(new Object[] { dragRowKey }));
        CmsChapterTVORowImpl dropRw =
            (CmsChapterTVORowImpl)CommonUtil.getBookAM().getCmsChapterTVO().getRow(new Key(new Object[] { dropRowKey }));

        Number dragNum = dragRw.getSeq();
        Number dropNum = dropRw.getSeq();

        try {
            Number startNumber = dragNum.add(dropNum.mul(-1)).intValue() < 0 ? dragNum : dropNum;
            Number endNumber = dragNum.add(dropNum.mul(-1)).intValue() > 0 ? dragNum : dropNum;

            iter.setRangeSize(-1);
            for (Row rw : iter.getAllRowsInRange()) {
                CmsChapterTVORowImpl crw = (CmsChapterTVORowImpl)rw;
                if (dragNum.add(dropNum.mul(-1)).intValue() < 0) {
                    if (crw.getSeq().intValue() > startNumber.intValue() &&
                        crw.getSeq().intValue() <= endNumber.intValue()) {
                        crw.setSeq(crw.getSeq().add(new Number(-1)));
                    }
                } else if (dragNum.add(dropNum.mul(-1)).intValue() > 0) {
                    if (crw.getSeq().intValue() >= startNumber.intValue() &&
                        crw.getSeq().intValue() < endNumber.intValue()) {
                        crw.setSeq(crw.getSeq().add(new Number(1)));
                    }
                }
            }

            dragRw.setSeq(dropNum);

        } catch (Exception e) {
            e.printStackTrace();
        }

        CommonUtil.getBookAM().getCmsChapterTVO().setSortBy("Seq");
        iter.executeQuery();

        //        for (Row ir : iter.getAllRowsInRange()) {
        //            CmsChapterTVORowImpl icr = (CmsChapterTVORowImpl)ir;
        //            System.out.println("name-->" + icr.getChapterName() + "   seq--->" + icr.getSeq());
        //        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(dropEvent.getDragComponent().getParent());

        return DnDAction.NONE;
    }

    public void setIteratorBinding(UIXIterator iteratorBinding) {
        this.iteratorBinding = iteratorBinding;
    }

    public UIXIterator getIteratorBinding() {
        return iteratorBinding;
    }

    public void sortCommit(ActionEvent actionEvent) {
        CommonUtil.getBookAM().getDBTransaction().commit();
        this.showMessage("排序保存成功！");
    }

    public void sortCancel(ActionEvent actionEvent) {
        CommonUtil.getBookAM().getDBTransaction().rollback();
    }
}
