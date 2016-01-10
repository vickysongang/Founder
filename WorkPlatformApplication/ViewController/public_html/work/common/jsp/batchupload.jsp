<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SWFUpload Demos</title>
        <style type="text/css">
                .btn3_mouseout {
                    BORDER-RIGHT: #99CCCC 1px solid;
                    PADDING-RIGHT: 2px;
                    BORDER-TOP: #99CCCC 1px solid;
                    PADDING-LEFT: 2px;
                    FONT-SIZE: 12px;
                    FILTER: progid: DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C3DAF5);
                    BORDER-LEFT: #99CCCC 1px solid;
                    CURSOR: hand;
                    COLOR: black;
                    PADDING-TOP: 1px;
                    BORDER-BOTTOM: #99CCCC 1px solid;
                    HEIGHT: 20px
                }

                .btn3_mouseover {
                    BORDER-RIGHT: #99CCCC 1px solid;
                    PADDING-RIGHT: 2px;
                    BORDER-TOP: #99CCCC 1px solid;
                    PADDING-LEFT: 2px;
                    FONT-SIZE: 12px;
                    FILTER: progid: DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#0066CC);
                    BORDER-LEFT: #99CCCC 1px solid;
                    CURSOR: hand;
                    COLOR: black;
                    PADDING-TOP: 1px;
                    BORDER-BOTTOM: #99CCCC 1px solid;
                    HEIGHT: 20px
                }

                .btn3_mousedown {
                    BORDER-RIGHT: #FFE400 1px solid;
                    PADDING-RIGHT: 2px;
                    BORDER-TOP: #FFE400 1px solid;
                    PADDING-LEFT: 2px;
                    FONT-SIZE: 12px;
                    FILTER: progid: DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#0066CC);
                    BORDER-LEFT: #FFE400 1px solid;
                    CURSOR: hand;
                    COLOR: black;
                    PADDING-TOP: 1px;
                    BORDER-BOTTOM: #FFE400 1px solid;
                    HEIGHT: 20px
                }

                .btn3_mouseup {
                    BORDER-RIGHT: #99CCCC 1px solid;
                    PADDING-RIGHT: 2px;
                    BORDER-TOP: #99CCCC 1px solid;
                    PADDING-LEFT: 2px;
                    FONT-SIZE: 12px;
                    FILTER: progid: DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C3DAF5);
                    BORDER-LEFT: #99CCCC 1px solid;
                    CURSOR: hand;
                    COLOR: black;
                    PADDING-TOP: 1px;
                    BORDER-BOTTOM: #99CCCC 1px solid;
                    HEIGHT: 20px
                }
            </style>
        <script type="text/javascript" src="../../../resources/js/ext/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../../../resources/js/ext/ext-all.js"></script>
        <script type="text/javascript" src="../../../resources/js/ext/ext-lang-zh_CN.js"></script>
        <script type="text/javascript" src="../../../resources/js/swfupload/swfupload.js"></script>
        <script type="text/javascript" src="../../../resources/js/swfupload/handlers.js"></script>
        <script type="text/javascript" src="../../../resources/js/ztree/jquery-1.4.4.min.js"></script>
        <script type="text/javascript">
          var swfu;
          window.onload = function () {
              swfu = new SWFUpload( {
                  upload_url : "/cms/work_platform/fileBatchUpload?method=upload", post_params :  {
                      "name" : "oracle"
                  },

                  // File Upload Settings
                  file_size_limit : "1000 MB", // 1000MB
                  file_types : "*.DOCX;*.docx;", file_types_description : "文件(*.DOCX,*.docx)", file_upload_limit : "10", file_queue_error_handler : fileQueueError, file_dialog_complete_handler : fileDialogComplete, //选择好文件后提交
                  file_queued_handler : fileQueued, upload_progress_handler : uploadProgress, upload_error_handler : uploadError, upload_success_handler : uploadSuccess, upload_complete_handler : uploadComplete, 

                  // Button Settings
                  button_image_url : "images/SmallSpyGlassWithTransperancy_17x18.png", button_placeholder_id : "spanButtonPlaceholder", button_width : 180, button_height : 18, button_text : '<span class="button">选择文件 <span class="buttonSmall"><\/span><\/span>', button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }', button_text_top_padding : 0, button_text_left_padding : 18, button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT, button_cursor : SWFUpload.CURSOR.HAND, 

                  // Flash Settings
                  flash_url : "../../../resources/js/swfupload/swfupload.swf", custom_settings :  {
                      upload_target : "divFileProgressContainer"
                  },
                  // Debug Settings
                  debug : false//是否显示调试窗口
              });
          };
        </script>
    </head>
    <body>
        <div id="content">
            <form>
                <div style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
                    <span id="spanButtonPlaceholder"></span>
                </div>
            </form>
            <div id="thumbnails" style="margin-top:-10px;">
                <table id="infoTable" border="0" width="830"
                       style=" border: solid 1px #7FAAFF; background-color: #C5D9FF; margin-top:8px;">
                    <tr style="background-color:#87C2E0;height:25px;">
                        <td style="color:white;font-weight:bold;font-size:12px;" align="center" width="100">状态标识</td>
                        <td style="color:white;font-weight:bold;font-size:12px;" align="center" width="200">文件名</td>
                        <td style="color:white;font-weight:bold;font-size:12px;" align="center" width="50">文件大小</td>
                        <td style="color:white;font-weight:bold;font-size:12px;" align="center" width="100">文件状态</td>
                        <td style="color:white;font-weight:bold;font-size:12px;" align="center" width="50">操作</td>
                    </tr>
                </table>
            </div>
            <div id="divFileProgressContainer" style="display:none;"></div>
        </div>
    </body>
</html>