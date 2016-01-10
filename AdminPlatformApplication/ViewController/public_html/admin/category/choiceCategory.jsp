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
                      enable : true, url : "/cms/admin/choiceCategory?method=init", autoParam : ["id", "name=n", "level=lv"]
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
                  if (nodes[0].id != 0 && nodes[0].id >  - 100000000) {
                      $('#categoryId4View').html(nodes[0].id);
                      $('#categoryName4View').html(nodes[0].name);
                      $('#categoryCode4View').html(nodes[0].code);
                      $('#parentCategory4View').html(nodes[0].getParentNode().name);
                      $('#lastUpdateDate4View').html(nodes[0].lastUpdateDate);
                      $('#categoryDescription4View').html(nodes[0].categoryDescription);
                  }
                  else {
                      $('#view').css("display", "none");
                      $('#add').css("display", "none");
                      $('#modify').css("display", "none");
                  }
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
                  else if (nodes[0].id >= 0) {
                      alert('不能在该分类下增加分类');
                      return;
                  }
                  $('#parentCategory4Add').html(nodes[0].name);
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
                  else if (nodes[0].id == 0 || nodes[0].id <=  - 100000000) {
                      alert('不能修改该分类节点');
                      return;
                  }
                  var pNode = nodes[0].getParentNode();
                  $('#view').css("display", "none");
                  $('#add').css("display", "none");
                  $('#modify').css("display", "block");
                  $('#parentCategory4Modify').html(pNode.name);
                  $('#categoryId4Modify').html(nodes[0].id);
                  $('#categoryName4Modify').val(nodes[0].name);
                  $('#categoryCode4Modify').html(nodes[0].code);
                  $('#lastUpdateDate4Modify').html(nodes[0].lastUpdateDate);
                  $('#categoryDescription4Modify').val(nodes[0].categoryDescription);
              }

              function deleteCategory() {
                  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                  var nodes = zTree.getSelectedNodes();
                  if (nodes.length == 0) {
                      alert('请从分类树中选中父分类节点');
                      return;
                  }
                  else if (nodes[0].id == 0 || nodes[0].id <=  - 100000000) {
                      alert('不能删除该分类节点');
                      return;
                  }
                  if (confirm("确认删除分类[" + nodes[0].name + "]？")) {
                      var param = "categoryId=" + nodes[0].id;
                      $.post("/cms/admin/choiceCategory?method=delete&" + param, function (data) {
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
                  var categoryType = pNode.type;
                  var categoryCode = $('#categoryCode4Add').val();
                  var categoryDesc = $('#categoryDescription4Add').val();
                  if (categoryName == null || categoryName == '') {
                      alert('必须填写分类名称');
                  }
                  if (categoryCode == null || categoryCode == '') {
                      alert('必须填写分类Code');
                  }

                  var param = "parentNodeId=" + pNode.id + "&categoryName=" + categoryName + "&categoryCode=" + categoryCode + "&categoryType=" + categoryType + "&categoryDesc=" + categoryDesc;
                  $.post("/cms/admin/choiceCategory?method=add&" + param, function (data) {
                      var d = $.trim(data);
                      if (d != null) {
                          var firstLetter = d.substring(0, 1);
                          if (firstLetter == 'E') {
                              alert(d.substring(1));
                              return;
                          }
                          var newNode = eval('(' + d + ')');
                          zTree.addNodes(pNode, newNode);
                          $('#view').css("display", "none");
                          $('#add').css("display", "none");
                          $('#modify').css("display", "none");
                          $(":text").attr("value", "");
                      }
                  });
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
                  var categoryDesc = $('#categoryDescription4Modify').val();
                  if (categoryName == null || categoryName == '') {
                      alert('必须填写分类名称');
                  }
                  else {
                      var param = "categoryId=" + node.id + "&categoryName=" + categoryName + "&categoryDesc=" + categoryDesc;
                      $.post("/cms/admin/choiceCategory?method=modify&" + param, function (data) {
                          var jsonobj = eval('(' + data + ')');
                          node.name = jsonobj.categoryName;
                          node.categoryDescription = jsonobj.categoryDescription;
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
        <body onload="loadCategory()">
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
                    <div>
                        <label id="l10">分类Code：</label>
                         
                        <label id="categoryCode4View"></label>
                    </div>
                    <div>
                        <label id="l5">所属分类：</label>
                         
                        <label id="parentCategory4View"></label>
                    </div>
                    <div>
                        <label id="l3">最后修改时间：</label>
                         
                        <label id="lastUpdateDate4View"></label>
                    </div>
                    <div>
                        <label id="l4">描述：</label>
                         
                        <label id="categoryDescription4View"></label>
                    </div>
                </div>
                <div id="modify" style="display:none;">
                    <div>
                        <label id="l7">分类ID：</label>
                         
                        <label id="categoryId4Modify"></label>
                    </div>
                    <div>
                        <label id="l12">*分类名称：</label>
                         
                        <input type="text" id="categoryName4Modify"/>
                    </div>
                    <div>
                        <label id="l11">分类Code：</label>
                         
                        <label id="categoryCode4Modify"></label>
                    </div>
                    <div>
                        <label id="l9">所属分类：</label>
                         
                        <label id="parentCategory4Modify"></label>
                    </div>
                    <div>
                        <label id="l16">最后修改时间：</label>
                         
                        <label id="lastUpdateDate4Modify"></label>
                    </div>
                    <div>
                        <label id="l14">描述：</label>
                         
                        <input type="text" id="categoryDescription4Modify"/>
                    </div>
                    <div>
                        <input type="button" id="submit4Modify" value="提交" onclick="submit4Modify()"/>
                         
                        <input type="button" id="cancel4Modify" value="取消" onclick="cancel4Modify()"/>
                    </div>
                </div>
                <div id="add" style="display:none;">
                    <div>
                        <label id="l8">*分类名称：</label>
                         
                        <input type="text" id="categoryName4Add"/>
                    </div>
                    <div>
                        <label id="l13">*分类Code：</label>
                         
                        <input type="text" id="categoryCode4Add"/>
                    </div>
                    <div>
                        <label id="l6">所属分类：</label>
                         
                        <label id="parentCategory4Add"></label>
                    </div>
                    <div>
                        <label id="l15">描述：</label>
                         
                        <input type="text" id="categoryDescription4Add"/>
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