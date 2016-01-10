package com.zypg.cms.work.view.servlet;

import com.zypg.cms.work.view.model.ThemeTreeModel;

import com.zypg.cms.work.view.util.DaoUtil;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ThemeTreeServlet extends HttpServlet {
    @SuppressWarnings("compatibility:1537526039250833502")
    private static final long serialVersionUID = 1L;

    public ThemeTreeServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");
        System.out.println("method:" + method);
        HttpSession session = request.getSession();
        System.out.println("sessionScope.docId:" + session.getAttribute("docId"));
        if ("init".equals(method)) {
            String rootNodeId = session.getAttribute("docId").toString();
            String jsonStr = getThemeNodeJsonStr(rootNodeId);
            System.out.println(jsonStr);
            PrintWriter writer = response.getWriter();
            writer.write(jsonStr);
            writer.close();
        } else if ("delete".equals(method)) {
            String nodeId = request.getParameter("id");
            try {
                DaoUtil.deleteThemeNode(nodeId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("update".equals(method)) {
            String nodeId = request.getParameter("id");
            String name = request.getParameter("name");
            System.out.println(nodeId + "    " + name);
            try {
                DaoUtil.updateThemeNodeName(nodeId, name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("add".equals(method)) {
            String pid = request.getParameter("pid");
            String name = request.getParameter("name");
            System.out.println(pid + "    " + name);
            try {
                String compCode = (String)session.getAttribute("compCode");
                String nodeId = DaoUtil.insertThemeNode(name, "CHILD", pid, "5", compCode);
                PrintWriter writer = response.getWriter();
                writer.write(nodeId);
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

    public String getThemeNodeJsonStr(String rootNodeId) {
        String str = null;
        try {
            List<ThemeTreeModel> treeNodes = DaoUtil.getThemeTreeNodes(rootNodeId);
            str = "[";
            for (ThemeTreeModel node : treeNodes) {
                if ("ROOT".equals(node.getType()) && treeNodes.size() > 1) {
                    str +=
"{name:\"" + node.getName() + "[" + node.getResCount() + "]" + "\",id:" + node.getId() + ",pId:" + node.getPid() +
 ",open:false,isParent:true},";
                } else {
                    str +=
"{name:\"" + node.getName() + "[" + node.getResCount() + "]" + "\",id:" + node.getId() + ",pId:" + node.getPid() +
 ",open:false,isParent:false},";
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
