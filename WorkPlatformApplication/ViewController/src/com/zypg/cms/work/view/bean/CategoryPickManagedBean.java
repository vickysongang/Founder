package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.Iterator;
import java.util.List;

import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;


public class CategoryPickManagedBean extends CustomManagedBean {
    private RichTree categoryTree;

    public CategoryPickManagedBean() {
    }

    public void setCategoryTree(RichTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    public RichTree getCategoryTree() {
        return categoryTree;
    }

    public void categoryTreeSelectionListener(SelectionEvent selectionEvent) {
        RowKeySet rks = categoryTree.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)categoryTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            this.appendScript("categoryPick('" + nodeBinding.getAttribute("CategoryId") + "','" +
                              nodeBinding.getAttribute("CategoryName") + "')");
        }
    }
}
