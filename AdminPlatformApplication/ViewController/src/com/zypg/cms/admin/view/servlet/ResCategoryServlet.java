package com.zypg.cms.admin.view.servlet;

import com.zypg.cms.admin.view.model.ResCategoryModel;
import com.zypg.cms.admin.view.util.DaoUtil;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResCategoryServlet extends HttpServlet {
    @SuppressWarnings("compatibility:-1730954661877137086")
    private static final long serialVersionUID = 1L;

    public ResCategoryServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        System.out.println("method:" + method);
        HttpSession session = request.getSession();
        String compCode = (String)session.getAttribute("compCode");
        System.out.println("compCode:" + compCode);
        if ("init".equals(method)) {
            String jsonStr = getJsonStr(compCode);
            PrintWriter writer = response.getWriter();
            writer.write(jsonStr);
            writer.close();
        } else if ("delete".equals(method)) {
            System.out.println("删除分类");
            String categoryId = request.getParameter("categoryId");
            try {
                DaoUtil.deleteResCategory(categoryId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("modify".equals(method)) {
            System.out.println("修改分类");
            String categoryId = request.getParameter("categoryId");
            String categoryName = request.getParameter("categoryName");
            String attribute1 = request.getParameter("attribute1");
            String attribute2 = request.getParameter("attribute2");
            String attribute3 = request.getParameter("attribute3");
            try {
                DaoUtil.modifyResCategory(categoryId, categoryName, attribute1, attribute2, attribute3);
                PrintWriter writer = response.getWriter();
                String jsonStr =
                    "{categoryName:\"" + categoryName + "\",attribute1:\"" + attribute1 + "\",attribute2:\"" +
                    attribute2 + "\",attribute3:\"" + attribute3 + "\"}";
                writer.write(jsonStr);
                writer.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("add".equals(method)) {
            System.out.println("增加分类");
            String parentNodeId = request.getParameter("parentNodeId");
            String categoryName = request.getParameter("categoryName");
            String attribute1 = request.getParameter("attribute1");
            String attribute2 = request.getParameter("attribute2");
            String attribute3 = request.getParameter("attribute3");
            try {
                String categoryId =
                    DaoUtil.addResCategory(parentNodeId, categoryName, attribute1, attribute2, attribute3);
                PrintWriter writer = response.getWriter();
                String jsonStr =
                    "{name:\"" + categoryName + "\",id:" + categoryId + ",pId:" + parentNodeId + ",open:false,isParent:false,lastUpdateDate:\"" +
                    "" + "\",attribute1:\"" + (attribute1 == null ? "" : attribute1) + "\",attribute2:\"" +
                    (attribute2 == null ? "" : attribute2) + "\",attribute3:\"" +
                    (attribute3 == null ? "" : attribute3) + "\"}";
                writer.write(jsonStr);
                writer.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        doPost(request, response);
    }

    public String getJsonStr(String compCode) {
        String str = null;
        try {
            List<ResCategoryModel> resCategoryNodes = DaoUtil.getResCategoryNodes(compCode);
            str = "[";
            for (ResCategoryModel node : resCategoryNodes) {
                if (resCategoryNodes.size() > 1 && "N".equals(node.getIsLeafNode())) {
                    String open = "false";
                    if ("0".equals(node.getCategoryId())) {
                        open = "true";
                    }
                    str +=
"{name:\"" + node.getCategoryName() + "\",id:" + node.getCategoryId() + ",pId:" + node.getParentNodeId() + ",open:" +
 open + ",isParent:true,lastUpdateDate:\"" + node.getLastUpdateDate() + "\",attribute1:\"" +
 (node.getAttribute1() == null ? "" : node.getAttribute1()) + "\",attribute2:\"" +
 (node.getAttribute2() == null ? "" : node.getAttribute2()) + "\",attribute3:\"" +
 (node.getAttribute3() == null ? "" : node.getAttribute3()) + "\",libCode:\"" + node.getLibCode() + "\"},";
                } else {
                    str +=
"{name:\"" + node.getCategoryName() + "\",id:" + node.getCategoryId() + ",pId:" + node.getParentNodeId() +
 ",open:false,isParent:false,lastUpdateDate:\"" + node.getLastUpdateDate() + "\",attribute1:\"" +
 (node.getAttribute1() == null ? "" : node.getAttribute1()) + "\",attribute2:\"" +
 (node.getAttribute2() == null ? "" : node.getAttribute2()) + "\",attribute3:\"" +
 (node.getAttribute3() == null ? "" : node.getAttribute3()) + "\",libCode:\"" + node.getLibCode() + "\"},";
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
