package com.zypg.cms.work.view.util;


import java.sql.SQLException;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.domain.Number;


public class AttributePicker extends CustomManagedBean {
    private Number categoryId;
    private String categoryName;

    public AttributePicker() {

    }

    public void passCategoryServerListener(ClientEvent clientEvent) {
        this.setExpressionValue("#{pageFlowScope.categoryId}", clientEvent.getParameters().get("categoryId"));
        this.setExpressionValue("#{pageFlowScope.categoryName}", clientEvent.getParameters().get("categoryName"));
        System.out.println("categoryId:" + clientEvent.getParameters().get("categoryId") + "    categoryName：" +
                           clientEvent.getParameters().get("categoryName"));
    }

    public void categoryDialogListener(DialogEvent dialogEvent) {
        String batchFlag = (String)this.resolveExpression("#{pageFlowScope.batchFlag}");
        System.out.println("batchFlag:" + batchFlag);
        if (dialogEvent.getOutcome().name().equals("ok")) {
            if ("Y".equals(batchFlag)) {
                try {
                    this.setExpressionValue("#{bindings.CategoryId1.inputValue}",
                                            new Number(this.resolveExpression("#{pageFlowScope.categoryId}")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.setExpressionValue("#{bindings.CategoryName1.inputValue}",
                                        this.resolveExpression("#{pageFlowScope.categoryName}"));

            } else {
                try {
                    this.setExpressionValue("#{bindings.CategoryId.inputValue}",
                                            new Number(this.resolveExpression("#{pageFlowScope.categoryId}")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.setExpressionValue("#{bindings.CategoryName.inputValue}",
                                        this.resolveExpression("#{pageFlowScope.categoryName}"));
            }
        }
    }

    public void clearCategoryActionListener(ActionEvent actionEvent) {
        this.setExpressionValue("#{bindings.CategoryId.inputValue}", null);
        this.setExpressionValue("#{bindings.CategoryName.inputValue}", null);
    }

    public void setCategoryId(Number categoryId) {
        this.categoryId = categoryId;
    }

    public Number getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
