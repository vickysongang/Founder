package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.Iterator;

import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.jbo.domain.Number;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;


public class ThemeMakeManagedBean extends CustomManagedBean {

    public ThemeMakeManagedBean() {
    }

    public void initThemeMaker() {
        System.out.println("initThemeMakerinitThemeMakerinitThemeMakerinitThemeMaker");
        Number docId = (Number)this.resolveExpression("#{pageFlowScope.docId}");
        String compCode = (String)this.resolveExpression("#{pageFlowScope.compCode}");
        String nodeId = CommonUtil.getThemeAM().preMakeTheme(compCode, docId);
        this.setExpressionValue("#{pageFlowScope.nodeId}", nodeId);
        this.setExpressionValue("#{sessionScope.docId}", docId);
        this.setExpressionValue("#{sessionScope.compCode}", compCode);
    }
}
