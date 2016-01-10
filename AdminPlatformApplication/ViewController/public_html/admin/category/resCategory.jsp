<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad/html" prefix="trh"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>category</title>
            <link rel="stylesheet" href="../../resources/css/demo.css" type="text/css"/>
            <link rel="stylesheet" href="../../resources/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
            <trh:script source="../../resources/js/ztree/jquery-1.4.4.min.js" id="s1"></trh:script>
            <trh:script source="../../resources/js/ztree/jquery.ztree.core-3.5.js" id="s2"></trh:script>
            <trh:script source="../../resources/js/ztree/jquery.ztree.excheck-3.5.js" id="s3"></trh:script>
            <trh:script source="../../resources/js/ztree/jquery.ztree.exedit-3.5.js" id="s4"></trh:script>
            <trh:script id="s5">
              var setting = {
                  async :  {
                      enable : true, url : "/cms/admin/resCategory?method=init", autoParam : ["id", "name=n", "level=lv"]
                  },
                  data :  {
                      simpleData :  {
                          enable : true
                      }
                  },
                  callback :  {
                      onClick : onClick
                  }

              };

              function onClick(e, treeId, treeNode) {
                  $('#add').css("display", "none");
                  $('#modify').css("display", "none");
                  $('#view').css("display", "block");
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  $('#categoryId4View').html(nodes[0].id);
                  $('#categoryName4View').html(nodes[0].name);
                  var libCode = nodes[0].libCode;
                  if(libCode == 'AUTHOR' || libCode == 'DETAIL'){
                     $('#attribute1View').css("display","block");
                     $('#attribute2View').css("display","block");
                     $('#attribute3View').css("display","none");
                     $('#attribute14View').html(nodes[0].attribute1);
                     $('#attribute24View').html(nodes[0].attribute2);
                     $('#attribute34View').html(nodes[0].attribute3);
                  }else{
                     $('#attribute1View').css("display","none");
                     $('#attribute2View').css("display","none");
                     $('#attribute3View').css("display","none");
                  }
                  $('#lastUpdateDate4View').html(nodes[0].lastUpdateDate);
              }

              $(document).ready(function () {
                  $.fn.zTree.init($("#treeDemo"), setting);
              });

              function addCategory() {
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  if (nodes.length == 0) {
                      alert('请从分类树中选中父分类节点');
                      return;
                  }
                  else if (nodes[0].id == 0) {
                      alert('不能新增库节点');
                      return;
                  }
                  var libCode = nodes[0].libCode;
                  if(libCode == 'AUTHOR' || libCode == 'DETAIL'){
                     $('#attribute1Add').css("display","block");
                     $('#attribute2Add').css("display","block");
                     $('#attribute3Add').css("display","none");
                  }else{
                     $('#attribute1Add').css("display","none");
                     $('#attribute2Add').css("display","none");
                     $('#attribute3Add').css("display","none");
                  }
                  $('#view').css("display", "none");
                  $('#modify').css("display", "none");
                  $('#add').css("display", "block");
              }

              function modifyCategory() {
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  if (nodes.length == 0) {
                      alert('请从分类树中选中父分类节点');
                      return;
                  }
                  else if (nodes[0].id == 0) {
                      alert('不能修改根节点');
                      return;
                  }
                  else if (nodes[0].id < 0) {
                      alert('不能修改库节点');
                      return;
                  }
                  $('#view').css("display", "none");
                  $('#add').css("display", "none");
                  $('#modify').css("display", "block");
                  
                  $('#categoryId4Modify').html(nodes[0].id);
                  $('#categoryName4Modify').val(nodes[0].name);
                  
                  var libCode = nodes[0].libCode;
                  if(libCode == 'AUTHOR' || libCode == 'DETAIL'){
                     $('#attribute1Modify').css("display","block");
                     $('#attribute2Modify').css("display","block");
                     $('#attribute3Modify').css("display","none");
                     $('#attribute14Modify').val(nodes[0].attribute1);
                     $('#attribute24Modify').val(nodes[0].attribute2);
                     $('#attribute34Modify').val(nodes[0].attribute3);
                  }else{
                     $('#attribute1Modify').css("display","none");
                     $('#attribute2Modify').css("display","none");
                     $('#attribute3Modify').css("display","none");
                  }
                  $('#lastUpdateDate4Modify').html(nodes[0].lastUpdateDate);
              }

              function deleteCategory() {
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  if (nodes.length == 0) {
                      alert('请从分类树中选中父分类节点');
                      return;
                  }
                  else if (nodes[0].id == 0) {
                      alert('不能删除根节点');
                      return;
                  }
                  else if (nodes[0].id < 0) {
                      alert('不能删除库节点');
                      return;
                  }
                  if (confirm("确认删除分类[" + nodes[0].name + "]？")) {
                      var param = "categoryId=" + nodes[0].id;
                      $.post("/cms/admin/resCategory?method=delete&" + param, function (data) {
                          zTree.removeNode(nodes[0]);
                          $('#view').css("display", "none");
                      });
                  }
              }

              function submit4Add() {
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  var pNode = nodes[0];
                  var categoryName = $('#categoryName4Add').val();
                  var attribute1 = $('#attribute14Add').val();
                  var attribute2 = $('#attribute24Add').val();
                  var attribute3 = $('#attribute34Add').val();
                  if (categoryName == null || categoryName == '') {
                      alert('必须填写分类名称');
                  }
                  else {
                      var param = "parentNodeId=" + pNode.id + "&categoryName=" + categoryName + "&attribute1=" + attribute1 + "&attribute2=" + attribute2 + "&attribute3=" + attribute3;
                      $.post("/cms/admin/resCategory?method=add&" + param, function (data) {
                          if ($.trim(data) != null) {
                              var newNode = eval('(' + $.trim(data) + ')');
                              zTree.addNodes(pNode, newNode);
                              $('#view').css("display", "none");
                              $('#add').css("display", "none");
                              $('#modify').css("display", "none");
                              $(":text").attr("value", "");
                          }
                      });
                  }
              }

              function cancel4Add() {
                  $('#add').css("display", "none");
                  $('#view').css("display", "block");
                  $(":text").attr("value", "");
              }

              function submit4Modify() {
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  var node = nodes[0];
                  var categoryName = $('#categoryName4Modify').val();
                  var attribute1 = $('#attribute14Modify').val();
                  var attribute2 = $('#attribute24Modify').val();
                  var attribute3 = $('#attribute34Modify').val();
                  if (categoryName == null || categoryName == '') {
                      alert('必须填写分类名称');
                  }
                  else {
                      var param = "categoryId=" + node.id + "&categoryName=" + categoryName + "&attribute1=" + attribute1 + "&attribute2=" + attribute2 + "&attribute3=" + attribute3;
                      $.post("/cms/admin/resCategory?method=modify&" + param, function (data) {
                          var jsonobj = eval('(' + data + ')');
                          node.name = jsonobj.categoryName;
                          node.attribute1 = jsonobj.attribute1;
                          node.attribute2 = jsonobj.attribute2;
                          node.attribute3 = jsonobj.attribute3;
                          zTree.updateNode(node);
                          $('#view').css("display", "none");
                          $('#add').css("display", "none");
                          $('#modify').css("display", "none");
                          $(":text").attr("value", "");
                      });
                  }
              }

              function cancel4Modify() {
                  $('#modify').css("display", "none");
                  $('#view').css("display", "block");
                  $(":text").attr("value", "");
              }
            </trh:script>
        </head>
        <body>
            <div>
                <div>
                    <table>
                        <tr>
                            <td>
                                <input type="button" id="addCategory" value="增加分类" onclick="addCategory()"/>
                            </td>
                            <td>
                                <input type="button" id="modifyCategory" value="修改分类" onclick="modifyCategory()"/>
                            </td>
                            <td>
                                <input type="button" id="deleteCategory" value="删除分类" onclick="deleteCategory()"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="content_wrap" style="float:left;">
                    <div class="zTreeDemoBackground left" style="float:left;">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>
                <div style="float:left;display:none;" id="view">
                    <div>
                        <label id="l1">分类ID：</label>
                         
                        <label id="categoryId4View"></label>
                    </div>
                    <div>
                        <label id="l2">分类名称：</label>
                         
                        <label id="categoryName4View"></label>
                    </div>
                    <div id="attribute1View">
                        <label id="l4">扩展字段1：</label>
                         
                        <label id="attribute14View"></label>
                    </div>
                    <div id="attribute2View">
                        <label id="l5">扩展字段2：</label>
                         
                        <label id="attribute24View"></label>
                    </div>
                    <div id="attribute3View">
                        <label id="l6">扩展字段3：</label>
                         
                        <label id="attribute34View"></label>
                    </div>
                    <div>
                        <label id="l3">最后修改时间：</label>
                         
                        <label id="lastUpdateDate4View"></label>
                    </div>
                </div>
                <div id="modify" style="display:none;">
                    <div>
                        <label id="l7">分类ID：</label>
                         
                        <label id="categoryId4Modify"></label>
                    </div>
                    <div>
                        <label id="l12"><span style="color:red">*</span>分类名称：</label>
                         
                        <input type="text" id="categoryName4Modify"/>
                    </div>
                    <div id="attribute1Modify">
                        <label id="l13">扩展字段1：</label>
                         
                        <input type="text" id="attribute14Modify"/>
                    </div>
                    <div id="attribute2Modify">
                        <label id="l14">扩展字段2：</label>
                         
                        <input type="text" id="attribute24Modify"/>
                    </div>
                    <div id="attribute3Modify">
                        <label id="l15">扩展字段3：</label>
                         
                        <input type="text" id="attribute34Modify"/>
                    </div>
                    <div>
                        <label id="l16">最后修改时间：</label>
                         
                        <label id="lastUpdateDate4Modify"></label>
                    </div>
                    <div>
                        <input type="button" id="submit4Modify" value="提交" onclick="submit4Modify()"/>
                         
                        <input type="button" id="cancel4Modify" value="取消" onclick="cancel4Modify()"/>
                    </div>
                </div>
                <div id="add" style="display:none;">
                    <div>
                        <label id="l8"><span style="color:red">*</span>分类名称：</label>
                         
                        <input type="text" id="categoryName4Add"/>
                    </div>
                    <div id="attribute1Add">
                        <label id="l9">扩展字段1：</label>
                         
                        <input type="text" id="attribute14Add"/>
                    </div>
                    <div id="attribute2Add">
                        <label id="l10">扩展字段2：</label>
                         
                        <input type="text" id="attribute24Add"/>
                    </div>
                    <div id="attribute3Add">
                        <label id="l11">扩展字段3：</label>
                         
                        <input type="text" id="attribute34Add"/>
                    </div>
                    <div>
                        <input type="button" id="submit4Add" value="提交" onclick="submit4Add()"/>
                         
                        <input type="button" id="cancel4Add" value="取消" onclick="cancel4Add()"/>
                    </div>
                </div>
            </div>
        </body>
    </html>
</f:view>