package com.zypg.cms.admin.view.servlet;

import com.zypg.cms.admin.view.model.ChoiceCategoryModel;
import com.zypg.cms.admin.view.model.ResCategoryModel;
import com.zypg.cms.admin.view.util.DaoUtil;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChoiceCategoryServlet extends HttpServlet {
    public ChoiceCategoryServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if ("init".equals(method)) {
            String jsonStr = getJsonStr();
            System.out.println(jsonStr);
            PrintWriter writer = response.getWriter();
            writer.write(jsonStr);
            writer.close();
        } else if ("delete".equals(method)) {
            System.out.println("删除分类");
            String categoryId = request.getParameter("categoryId");
            try {
                DaoUtil.deleteChoiceCategory(categoryId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("modify".equals(method)) {
            System.out.println("修改分类");
            String categoryId = request.getParameter("categoryId");
            String categoryName = request.getParameter("categoryName");
            String categoryDesc = request.getParameter("categoryDesc");
            try {
                DaoUtil.modifyChoiceCategory(categoryId, categoryName, categoryDesc);
                PrintWriter writer = response.getWriter();
                String jsonStr =
                    "{categoryName:\"" + categoryName + "\",categoryDescription:\"" + categoryDesc + "\"}";
                writer.write(jsonStr);
                writer.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("add".equals(method)) {
            System.out.println("增加分类");
            String parentNodeId = request.getParameter("parentNodeId");
            String categoryName = request.getParameter("categoryName");
            String categoryCode = request.getParameter("categoryCode");
            String categoryType = request.getParameter("categoryType");
            String categoryDesc = request.getParameter("categoryDesc");
            try {
                String categoryId =
                    DaoUtil.addChoiceCategory(parentNodeId, categoryCode, categoryName, categoryType, categoryDesc);
                PrintWriter writer = response.getWriter();
                String data = null;
                if (categoryId.startsWith("E")) {
                    data = categoryId;
                } else {
                    data =
"{name:\"" + categoryName + "\",id:" + categoryId + ",pId:" + parentNodeId + ",open:false,isParent:false,code:\"" +
 categoryCode + "\",type:\"" + categoryType + "\",lastUpdateDate:\"" + "" + "\",categoryDescription:\"" +
 categoryDesc + "\"}";
                }
                writer.write(data);
                writer.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getJsonStr() {
        String str = null;
        try {
            List<ChoiceCategoryModel> choiceCategoryNodes = DaoUtil.getChoiceCategoryNodes();
            str = "[";
            for (ChoiceCategoryModel node : choiceCategoryNodes) {
                if (choiceCategoryNodes.size() > 1 && "N".equals(node.getIsLeafNode())) {
                    String open = "false";
                    if ("0".equals(node.getCategoryId())) {
                        open = "true";
                    }
                    str +=
"{name:\"" + node.getCategoryName() + "\",id:" + node.getCategoryId() + ",pId:" + node.getParentNodeId() + ",open:" +
 open + ",isParent:true,code:\"" + node.getCategoryCode() + "\",type:\"" + node.getCategoryType() +
 "\",lastUpdateDate:\"" + node.getLastUpdateDate() + "\",categoryDescription:\"" + node.getCategoryDescription() +
 "\"},";
                } else {
                    str +=
"{name:\"" + node.getCategoryName() + "\",id:" + node.getCategoryId() + ",pId:" + node.getParentNodeId() +
 ",open:false,isParent:false,code:\"" + node.getCategoryCode() + "\",type:\"" + node.getCategoryType() +
 "\",lastUpdateDate:\"" + node.getLastUpdateDate() + "\",categoryDescription:\"" + node.getCategoryDescription() +
 "\"},";
                }
            }
            str = str.substring(0, str.length() - 1);
            str += "]";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
