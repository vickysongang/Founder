package com.zypg.cms.work.view.bean;


import com.zypg.cms.work.view.util.CustomManagedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;
import oracle.adf.view.rich.util.ResetUtils;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowSetImpl;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.event.FocusEvent;
import org.apache.myfaces.trinidad.event.FocusListener;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.TreeModel;


public class WorkManagedBean extends CustomManagedBean {
    private RichRegion workRouterRegion;
    private RichRegion workRouterRegion2;
    private RichTree categoryTree;
    private UIXSwitcher switcher;

    public WorkManagedBean() {
    }

    public List<Map<String, Object>> getFirstLevelLibList() {
        List<Map<String, Object>> firstLevelLibList = new ArrayList<Map<String, Object>>();
        DCIteratorBinding dcib = this.findIterator("CmsFirstLevelLibVOIterator");
        dcib.setRangeSize(-1);
        for (Row row : dcib.getAllRowsInRange()) {
            if (this.resolveExpression("#{pageFlowScope.libTypeCode}") == null) {
                this.setExpressionValue("#{pageFlowScope.libTypeCode}", row.getAttribute("LibCode"));
            }
            Map<String, Object> lib = new HashMap<String, Object>();
            lib.put("libCode", row.getAttribute("LibCode"));
            lib.put("libName", row.getAttribute("LibName"));
            ViewRowSetImpl rs = (ViewRowSetImpl)row.getAttribute("CmsSecondLevelLibVO");
            List<Map<String, String>> secondLevelLibList = new ArrayList<Map<String, String>>();
            for (Row r : rs.getAllRowsInRange()) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("libCode", (String)r.getAttribute("LibCode"));
                m.put("libName", (String)r.getAttribute("LibName"));
                m.put("libFlag", (String)r.getAttribute("LibFlag"));
                secondLevelLibList.add(m);
            }
            lib.put("secondLevelLib", secondLevelLibList);
            firstLevelLibList.add(lib);
        }
        return firstLevelLibList;
    }

    public void findCategoryActionListener(ActionEvent actionEvent) {
        this.setExpressionValue("#{pageFlowScope.libCode}", actionEvent.getComponent().getAttributes().get("libCode"));
        this.setExpressionValue("#{pageFlowScope.libTypeCode}",
                                actionEvent.getComponent().getAttributes().get("libTypeCode"));
        this.setExpressionValue("#{pageFlowScope.libFlag}", actionEvent.getComponent().getAttributes().get("libFlag"));
        this.setExpressionValue("#{pageFlowScope.categoryId}", null);
        this.refreshUIComponent(this.getSwitcher());
        if ("N".equals(this.resolveExpression("#{pageFlowScope.libFlag}"))) {
            this.setExpressionValue("#{sessionScope.currTime}", System.currentTimeMillis());
            this.getWorkRouterRegion2().refresh(FacesContext.getCurrentInstance());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getWorkRouterRegion2());
        } else {
            this.reFindCategory();
            if (categoryTree != null) {
                TreeModel treeModel = (TreeModel)categoryTree.getValue();
                JUCtrlHierBinding treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
                RowKeySet rks = categoryTree.getSelectedRowKeys();
                if (rks != null) {
                    rks.clear();
                    this.setExpressionValue("#{pageFlowScope.categoryId}",
                                            treeBinding.getCurrentRow().getAttribute("CategoryId"));
                    this.setExpressionValue("#{pageFlowScope.deleteFlag}", "N");
                    if (((Number)treeBinding.getCurrentRow().getAttribute("CategoryId")).intValue() == -2) {
                        this.setExpressionValue("#{pageFlowScope.categoryId}", null);
                    } else if (((Number)treeBinding.getCurrentRow().getAttribute("CategoryId")).intValue() == -3) {
                        this.setExpressionValue("#{pageFlowScope.categoryId}", null);
                        this.setExpressionValue("#{pageFlowScope.deleteFlag}", "Y");
                    }
                }
            }
            this.getWorkRouterRegion().refresh(FacesContext.getCurrentInstance());
            this.refreshUIComponent(this.getWorkRouterRegion());
        }
    }

    public void setWorkRouterRegion(RichRegion workRouterRegion) {
        this.workRouterRegion = workRouterRegion;
    }

    public RichRegion getWorkRouterRegion() {
        return workRouterRegion;
    }

    public void setWorkRouterRegion2(RichRegion workRouterRegion2) {
        this.workRouterRegion2 = workRouterRegion2;
    }

    public RichRegion getWorkRouterRegion2() {
        return workRouterRegion2;
    }

    public void categoryTreeSelectionListener(SelectionEvent selectionEvent) {
        RowKeySet rks = categoryTree.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List)keys.next();
            JUCtrlHierBinding treeBinding = null;
            TreeModel treeModel = (TreeModel)categoryTree.getValue();
            treeBinding = (JUCtrlHierBinding)treeModel.getWrappedData();
            System.out.println("key:" + key.get(0));
            JUCtrlHierNodeBinding nodeBinding = treeBinding.findNodeByKeyPath(key);
            if (nodeBinding != null) {
                this.setExpressionValue("#{pageFlowScope.categoryId}", nodeBinding.getAttribute("CategoryId"));
                System.out.println("SSS:" + nodeBinding.getAttribute("CategoryId"));
                this.setExpressionValue("#{pageFlowScope.deleteFlag}", "N");
                if (((Number)nodeBinding.getAttribute("CategoryId")).intValue() == -2) {
                    System.out.println("ssssssssssssssssssssssssssssssssssssssssssssss");
                    this.setExpressionValue("#{pageFlowScope.categoryId}", null);
                } else if (((Number)nodeBinding.getAttribute("CategoryId")).intValue() == -3) {
                    this.setExpressionValue("#{pageFlowScope.categoryId}", null);
                    this.setExpressionValue("#{pageFlowScope.deleteFlag}", "Y");
                }
            }
            this.getWorkRouterRegion().refresh(FacesContext.getCurrentInstance());
            this.refreshUIComponent(this.getWorkRouterRegion());
        }
    }

    public void setCategoryTree(RichTree categoryTree) {
        this.categoryTree = categoryTree;
    }

    public RichTree getCategoryTree() {
        return categoryTree;
    }

    public void refreshCategoryServerListener(ClientEvent clientEvent) {
        System.out.println("刷新分类树。。。");
        this.refreshUIComponent(this.getCategoryTree());
    }

    public void setSwitcher(UIXSwitcher switcher) {
        this.switcher = switcher;
    }

    public UIXSwitcher getSwitcher() {
        return switcher;
    }

    public void firstLibCodeDisclosureListener(DisclosureEvent disclosureEvent) {
        if (disclosureEvent.isExpanded()) {
            String firstLibCode = (String)disclosureEvent.getComponent().getAttributes().get("firstLibCode");
            this.setExpressionValue("#{pageFlowScope.firstLibCode}", firstLibCode);
        }
    }
}
