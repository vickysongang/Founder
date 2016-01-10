<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <style type="text/css">
                body {
                    font-family: Verdana, Arial, sans-serif;
                    font-size: 90%;
                }

                h1, h2, h3, h4 {
                    margin-top: 0px;
                }

                div.row {
                    margin-bottom: 10px;
                }

                *.focus {
                    outline: none;
                }

                .floatLeft {
                    float: left;
                }

                .floatRight {
                    float: right;
                }

                .clear {
                    clear: both;
                }

                form {
                    padding: 5px 10px 5px 10px;
                    border: 1px solid #cccccc;
                    border-radius: 10px;
                    -moz-border-radius: 10px;
                    -webkit-box-shadow: 0 0 10px #ccc;
                    box-shadow: 0 0 10px #ccc;
                    width: 550px;
                    margin: 5px;
                    background-image: -moz-linear-gradient(top, #ffffff, #f2f2f2);
                    background-image: -webkit-gradient(linear, left top, bottom, from(#ffffff), to(#f2f2f2));
                }

                #file {
                    width: 300px;
                }

                #progressIndicator {
                    font-size: 10pt;
                }

                .fileInfo {
                    font-size: 10pt;
                    font-style: italic;
                    color: #aaa;
                    padding-top: 6px;
                }

                #fileFormat {
                    font-size: 10pt;
                    word-break: normal;
                    word-wrap: break-word;
                    color: #aaa;
                    width: 550px;
                }

                #progressBar {
                    height: 10px;
                    border: 1px solid #cccccc;
                    display: none;
                    border-radius: 10px;
                    -moz-border-radius: 10px;
                    background-image: -moz-linear-gradient(top, #66cc00, #4b9500);
                    background-image: -webkit-gradient(linear, left top, left bottom, from(#66cc00), to(#4b9500));
                    background-color: #4b9500;
                }

                #uploadResponse {
                    color: red;
                }

                #left, #right {
                    float: left;
                }
            </style>
        <script type="text/javascript">
          var format;

          function invokeUploadAction(f) {
              format = f;
              var upload = document.getElementById('upload');
              upload.click();
          }
        </script>
    </head>
    <body>
        <form id="form" enctype="multipart/form-data" method="post" action="/cms/work_platform/upload">
            <div id="row">
                <div id='left'>
                    <input type="file" name="file" id="file"/>
                </div>
                <div id="right">
                    <div id="fileSize" class="fileInfo"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="row" style="display:none;">
                <input type="button" id="upload" value="上传"/>
            </div>
            <div id="fileFormat">
                允许上传的文件格式为： 
                ${sessionScope.fileFormat}
            </div>
            <div id="progressIndicator">
                <div id="progressBar" class="floatLeft"></div>
                <div id="progressNumber" class="floatRight" style="margin-left:10px;"></div>
                <!--<div class="clear"></div>-->
                <div>
                    <div id="transferBytesInfo" class="floatRight" style="text-align: right;"></div>
                    <div class="clear"></div>
                </div>
                <div id="uploadResponse"></div>
            </div>
        </form>
        <script type="text/javascript" src="../../../resources/js/jsupload.js"></script>
        <script type="text/javascript">
;(function (document) {
              //file 对象
              var file = document.getElementById('file'), 
              //不支持File API的浏览器调用该接口，预览上传文件信息
              validUrl = '/cms/work_platform/fileMessage', 
              //上传文件处理接口
              uploadUrl = '/cms/work_platform/upload', 
              //不支持File API的浏览器调用该接口，显示文件上传进度
              progressUrl = '/cms/work_platform/uploadProgress', 
              //上传按钮
              upload = document.getElementById('upload'), 
              //上传进度条
              progressBar = document.getElementById('progressBar'), 
              //上传百分比
              progressNumber = document.getElementById('progressNumber'), 
              //已上传文件大小
              transferBytesInfo = document.getElementById('transferBytesInfo'), 
              //服务器端处理结果
              uploadResponse = document.getElementById('uploadResponse'), 
              //初始化一个JsUpload对象
              jsUpload = new JsUpload( {
                  name : '__jsupload__', file : file, validUrl : validUrl, uploadUrl : uploadUrl, progressUrl : progressUrl
              });

              JsUpload.bind(file, 'change', function (event) {
                  //隐藏上次上传完成后提示信息、进度条、上传大小
                  progressBar.style.display = 'none';
                  progressNumber.style.display = 'none';
                  transferBytesInfo.style.display = 'none';
                  uploadResponse.style.display = 'none';

                  jsUpload.change(this, function (name, size, type) {
                      //预览上传文件信息 
                      JsUpload.get('fileSize').innerHTML = size;
                  });
              });

              //上传文件
              JsUpload.bind(upload, 'click', function (event) {

                  //隐藏上次上传完成后提示信息、进度条、上传大小
                  progressBar.style.display = 'none';
                  progressNumber.style.display = 'none';
                  transferBytesInfo.style.display = 'none';
                  uploadResponse.style.display = 'none';
                  uploadResponse.innerHTML = '';
                  var path = file.value;
                  if (path == null) {
                      uploadResponse.innerHTML = '请选择上传文件';
                      uploadResponse.style.display = 'block';
                      return;
                  }
                  var pos1 = path.lastIndexOf('/');
                  var pos2 = path.lastIndexOf('\\');
                  var pos = Math.max(pos1, pos2);
                  var fileName = path.substring(pos+1);
                  if (format.indexOf(fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length).toLowerCase()) < 0) {
                      uploadResponse.innerHTML = '文件格式不符合要求';
                      uploadResponse.style.display = 'block';
                  }
                  else {
                      jsUpload.upload(this, file, function (cur, args1, args2) {
                          switch (cur) {
                              case 'progress':
                              //显示上传文件进度条
                                  progressBar.style.display = 'block';
                                  progressNumber.style.display = 'block';
                                  transferBytesInfo.style.display = 'block';
                                  progressBar.style.width = '0px';

                                  progressNumber.innerHTML = args1 + '%';
                                  progressBar.style.width = args1 * 3.55 + 'px';
                                  transferBytesInfo.innerHTML = args2;
                                  break;
                              case 'complete':
                                  uploadResponse.style.display = 'block';
                                  parent.setFileName(fileName);
                                  break;
                              case 'fail':
                              case 'load':
                              case 'error':
                              case 'abort':
                                  uploadResponse.innerHTML = args1;
                                  uploadResponse.style.display = 'block';
                                  break;
                              default :
                                  break;
                          }
                      });
                  }
              });
          })(document);
        </script>
    </body>
</html>