package com.zypg.cms.work.view.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class uploadProgress
 */
@WebServlet("/uploadProgress")
public class UploadProgress extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadProgress() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.print("{uploadedBytes:'" + session.getAttribute("readBytes") + "',totalBytes:'" +
                  session.getAttribute("totalBytes") + "'}");
        out.close();
    }
}

