<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>UCM下载</title>
    </head>
    <body style="margin:0px">
         <%--<% 
           String  msg =  (String)request.getSession().getAttribute("downloadXml"); 
           System.out.println("msg--->"+msg);
          %>--%>
        <applet xmlns="http://www.w3.org/1999/xhtml" code="com.zypg.cms.swing.DownloadWindowApplet"
                    Archive="UCMDownLoadApplication_fat.jar" width="1000" height="500" border="0" align="middle">
                <param name="downloadInfo" value="${downloadXml}"></param>
            </applet>
    </body>
</html>