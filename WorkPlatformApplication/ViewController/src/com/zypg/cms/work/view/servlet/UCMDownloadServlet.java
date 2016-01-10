package com.zypg.cms.work.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UCMDownloadServlet extends HttpServlet {


    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doPost(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        super.doPost(httpServletRequest, httpServletResponse);
        
         String downloadXml = (String)httpServletRequest.getSession().getAttribute("tempResDownloadMetadata");
//         httpServletRequest.getSession().setAttribute("tempResDownloadMetadata", null);  
         httpServletRequest.setAttribute("downloadXml", downloadXml);
         System.out.println("servlet downloadXml--->"+downloadXml);
         httpServletRequest.getRequestDispatcher("/work/resexp/inline/download.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
