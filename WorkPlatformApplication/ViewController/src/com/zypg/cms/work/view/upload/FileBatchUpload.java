package com.zypg.cms.work.view.upload;


import com.zypg.cms.work.view.util.CommonUtil;
import com.zypg.cms.work.view.util.Constants;

import java.io.File;
import java.io.IOException;

import java.net.URLDecoder;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileBatchUpload extends HttpServlet {
    private static final long serialVersionUID = -7825355637448948879L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());

        String entryCompName = (String)session.getAttribute("entryCompName");
        String path = null;
        if (Constants.IS_ONLINE_MODE) {
            path = Constants.FTP_ADDRESS_PREFIX + "/" + entryCompName + "/条目库" + "/" + dateStr;
        } else {
            path = "D:\\" + "/" + entryCompName + "/条目库" + "/" + dateStr;
        }

        if ("upload".equals(method)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置内存缓冲区，超过后写入临时文件
            factory.setSizeThreshold(10240000);
            
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdir();
            }

            // 设置临时文件存储位置
            String base = path;
            File file = new File(base);
            if (!file.exists())
                file.mkdirs();
            factory.setRepository(file);
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置单个文件的最大上传值
            upload.setFileSizeMax(10002400000l);
            // 设置整个request的最大值
            upload.setSizeMax(10002400000l);
            upload.setHeaderEncoding("UTF-8");
            List<Map<String, Object>> fileItemList = (List<Map<String, Object>>)session.getAttribute("fileItemList");
            if (fileItemList == null) {
                fileItemList = new ArrayList<Map<String, Object>>();
            }
            try {
                List<?> items = upload.parseRequest(request);
                FileItem item = null;
                Map<String, Object> fileInfoMap = new HashMap<String, Object>();
                for (int i = 0; i < items.size(); i++) {
                    item = (FileItem)items.get(i);
                    String itemName = item.getName();
                    // 保存文件
                    if (!item.isFormField() && itemName.length() > 0) {
                        if (validateFileExsits(fileItemList, itemName)) {
                            continue;
                        }
                        String suffix = itemName.substring(itemName.lastIndexOf("."));
                        String uuidFileName = CommonUtil.getUuidString() + suffix;
                        if (itemName.contains("_")) {
                            String docId =
                                itemName.substring(itemName.lastIndexOf("_") + 1, itemName.lastIndexOf("."));
                            if (CommonUtil.isNumeric(docId)) {
                                uuidFileName = CommonUtil.getUuidString() + "_" + docId + suffix;
                            }
                        }
                        String saveFileName = base + File.separator + uuidFileName;
                        item.write(new File(saveFileName));
                        fileInfoMap.put("fileName", item.getName());
                        fileInfoMap.put("fileItem", item);
                        System.out.println("upload uuidFileName:" + uuidFileName);
                        fileInfoMap.put("uuidFileName", uuidFileName);
                    }
                }
                fileItemList.add(fileInfoMap);
                session.setAttribute("fileItemList", fileItemList);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(method)) {
            String fileName = request.getParameter("filename");
            fileName = URLDecoder.decode(fileName, "utf8");
            System.out.println("fileName:" + fileName);
            List<Map<String, Object>> fileItemList = (List<Map<String, Object>>)session.getAttribute("fileItemList");
            Map<String, Object> deleteMap = new HashMap<String, Object>();
            for (int i = 0; i < fileItemList.size(); i++) {
                Map<String, Object> map = fileItemList.get(i);
                if (fileName.equals(map.get("fileName").toString())) {
                    System.out.println("删除" + fileName);
                    String filePath = path + "/" + map.get("uuidFileName");
                    File file = new File(filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    deleteMap = map;
                    break;
                }
            }
            fileItemList.remove(deleteMap);
            session.setAttribute("fileItemList", fileItemList);
        }
    }

    public boolean validateFileExsits(List<Map<String, Object>> fileItemList, String fileName) {
        for (int i = 0; i < fileItemList.size(); i++) {
            Map<String, Object> map = fileItemList.get(i);
            if (fileName.equals(map.get("fileName").toString())) {
                return true;
            }
        }
        return false;
    }
}
