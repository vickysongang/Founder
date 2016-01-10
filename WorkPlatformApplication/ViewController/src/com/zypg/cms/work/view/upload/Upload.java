package com.zypg.cms.work.view.upload;


import com.zypg.cms.work.view.util.Constants;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Upload() {
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
        System.out.println("开始上传。。。");
        response.setContentType("text/html");
        //设置字符编码为UTF-8，支持汉字显示
        //        response.setCharacterEncoding("UTF-8");
        final long MAX_SIZE = 10 * 1024 * 1024 * 1024; //设置上传文件最大为1G

        //上传文件路径
        String filePath = Constants.MATERIAL_UPLOAD_PATH;
        String tempPath = Constants.MATERIAL_UPLOAD_TEMP_PATH;

        // 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setSizeThreshold(10240000);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }

        // 设置临时文件存储位置
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        dfif.setRepository(tempFile);

        //        dfif.setRepository(new File(tempPath)); // 设置存放临时文件的目录

        //用以上工厂实例化上传组件
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        //设置最大上传尺寸
        sfu.setSizeMax(MAX_SIZE);
        sfu.setHeaderEncoding("UTF-8");
        final HttpSession session = request.getSession();
        sfu.setProgressListener(new ProgressListener() {
            private long temp = -1;

            public void update(long readBytes, long totalBytes, int item) {
                long size = readBytes / 1024 * 1024 * 10;
                if (temp == size) {
                    return;
                }
                temp = size;
                if (readBytes != -1) {
                    session.setAttribute("readBytes", "" + readBytes);
                    session.setAttribute("totalBytes", "" + totalBytes);
                }
            }
        });

        //从request得到 所有 上传域的列表
        List fileList = null;
        try {
            fileList = sfu.parseRequest(request);
        } catch (FileUploadException e) { //处理文件尺寸过大异常
            if (e instanceof SizeLimitExceededException) {
                return;
            }
            e.printStackTrace();
        }
        //得到所有上传的文件
        Iterator fileItr = fileList.iterator();
        //循环处理所有文件
        while (fileItr.hasNext()) {
            FileItem fileItem = null;
            double size = 0;
            //得到当前文件
            fileItem = (FileItem)fileItr.next();
            //忽略简单form字段而不是上传域的文件域(<input type="text" />等)
            if (fileItem == null || fileItem.isFormField()) {
                continue;
            }
            //得到文件的大小
            size = fileItem.getSize();
            //保存的最终文件完整路径,保存在指定目录下
            String itemName = fileItem.getName();
            String u_name = filePath + itemName;
            String u_size = "";
            try {
                //保存文件
                fileItem.write(new File(u_name));
                if (size > 1024 * 1024) {
                    u_size = (float)Math.round(size * 100 / (1024 * 1024)) / 100 + "MB";
                } else {
                    u_size = (float)Math.round(size * 100 / 1024) / 100 + "KB";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            session.setAttribute("fileItem", fileItem);
        }
    }
}
