<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 自定义标签的引入 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>进度条</title>
        <meta http-equiv="pragma" content="no-cache"></meta>
        <meta http-equiv="cache-control" content="no-cache"></meta>
        <meta http-equiv="expires" content="0"></meta>
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
        <style type="text/css">
                #divprogressbar {
                    width: 600px;
                    height: 30px;
                }

                .progress-label {
                    float: left;
                    margin-left: 40%;
                    margin-top: 3px;
                }
            </style>
        <!-- js文件和代码 -->
        <link rel="stylesheet" href="../../../resources/css/jqueryUI/jquery-ui-1.10.0.custom.min.css" media="screen"/>
        <script type="text/javascript" src="../../../resources/js/jquery_min.js"></script>
        <script type="text/javascript" src="../../../resources/js/jquery-ui-1.10.4.min.js"></script>
        <script type="text/javascript" src="../../../resources/js/downloadProgress.js"></script>
    </head>
    <body id="process" onload="process();" style="top:200px;">
        <div style="top:145px;border: none;">
            <table align="center">
                <tr>
                    <td colspan="2">
                        <div id="res" style="font-size:14px;font-weight:bold;"></div>
                    </td>
                </tr>
                 
                <tr>
                    <td>
                        <div id="divprogressbar">
                            <div class="progress-label">loading...</div>
                        </div>
                    </td>
                    <td>
                        <div id="progress-num"></div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>