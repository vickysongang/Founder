package com.zypg.cms.work.view.servlet;

import com.zypg.cms.work.view.util.DaoUtil;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jbo.domain.Number;


public class DownloadServlet extends HttpServlet {
    @SuppressWarnings("compatibility:-864376084451010010")
    private static final long serialVersionUID = 1L;
    private PrintWriter out = null;

    public DownloadServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setCharacterEncoding("UTF-8");
        String task = request.getParameter("task");
        System.out.println("task:" + task);
        HttpSession session = request.getSession();
        String docId = (String)session.getAttribute("downloadDocId");
        docId = docId == null ? "-999" : docId;
        System.out.println("docId:" + docId);
        String result = "";
        String currFileCount = null;
        String totalFileCount = null;
        String resName = null;
        if ("first".equals(task)) {
            out = response.getWriter();
            result = "1";
            //此处可以对非第1次的异步访问公共的内容，做一些初始化工作
            try {
                Map<String, String> map = DaoUtil.getFileManageMonitorRecord(new Number(docId));
                resName = map.get("resName");
                currFileCount = map.get("currFileCount");
                totalFileCount = map.get("totalFileCount");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //模拟进度条
            try {
                Map<String, String> map = DaoUtil.getFileManageMonitorRecord(new Number(docId));
                resName = map.get("resName");
                currFileCount = map.get("currFileCount");
                totalFileCount = map.get("totalFileCount");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        currFileCount = currFileCount == null ? "0" : currFileCount;
        totalFileCount = totalFileCount == null ? "0" : totalFileCount;
        System.out.println("currFileCount:" + currFileCount + "totalFileCount:" + totalFileCount);
        result =
                "{\"currFileCount\":" + currFileCount + ",\"totalFileCount\":" + totalFileCount + ",\"resName\":\"" + resName +
                "\"}";
        System.out.println(result);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        out.println(result);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        doPost(request, response);
    }
}

