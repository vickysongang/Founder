var ie;
var firefox;
var generateKey;

if (document.all)
    ie = true;
else 
    ie = false;//判断是否IE
document.onkeydown = KeyPress;
document.onkeyup = KeyUP;

function KeyUP() {
    generateKey =  - 1;
}
//设置键盘事件函数
function KeyPress() {
    var key;
    if (ie)
        key = event.keyCode;
    //Ie使用event.keyCode获取键盘码
    else 
        key = KeyPress.arguments[0].keyCode;
    //FireFox使用我们定义的键盘函数的arguments[0].keyCode来获取键盘码
    generateKey = key;
}

function imageDblSelect(event) {
    var source = event.getSource();
    if (generateKey == 17) {
        AdfCustomEvent.queue(source, "ImageDblSelectAction", 
        {
        },
true);
    }
    else {
        AdfCustomEvent.queue(source, "ImageSelectAction", 
        {
        },
true);
    }
}

function tableDblSelect(event) {
    var source = event.getSource();
    if (generateKey == 17) {
        AdfCustomEvent.queue(source, "TableDblSelectAction", 
        {
        },
true);
    }
    else {
        AdfCustomEvent.queue(source, "TableSelectionAction", 
        {
        },
false);
    }
}

function elecProdClick(evt) {
    //    var source = evt.getSource();
    var k = evt.getKeyCode();
    alert(k);
}

function imageDblClick(evt) {
    var source = evt.getSource();
    var docId = source.getProperty('docId');
    AdfCustomEvent.queue(source, "ImageDblClickEvent", 
    {
        docId : docId
    },
true);
    evt.cancel();
}

function imageGroupDblClick(evt) {
    var source = evt.getSource();
    var docId = source.getProperty('docId');
    var groupId = source.getProperty('groupId');
    var figureCount = source.getProperty('figureCount');
    AdfCustomEvent.queue(source, "ImageGroupDblClickEvent", 
    {
        docId : docId, groupId : groupId, figureCount : figureCount
    },
true);
    evt.cancel();
}

function tableDblClick(evt) {
    var source = evt.getSource();
    AdfCustomEvent.queue(source, "TableDblClickEvent", 
    {
    },
true);
    evt.cancel();
}

function categoryPickDblClick(evt) {
    var table = evt.getSource();
    AdfCustomEvent.queue(table, "CategoryPickDblClickEvent", 
    {
    },
true);
    evt.cancel();
}

function categoryPick(categoryId, categoryName) {
    var component = AdfPage.PAGE.findComponent("categoryHandler");
    AdfCustomEvent.queue(component, 'passCategory', 
    {
        'categoryId' : categoryId, 'categoryName' : categoryName
    },
true);
}

function clickSelectOneCheckBox(evt) {
    var selectCheckBox = evt.getSource();
    var isChecked = selectCheckBox.getValue();
    var docId = selectCheckBox.getProperty('docId');
    AdfCustomEvent.queue(selectCheckBox, "SelectCheckBoxClickEvent", 
    {
        params : isChecked, docId : docId
    },
false);
}

function clickSelectAllCheckBox(evt) {
    var selectAllCheckBox = evt.getSource();
    var isChecked = selectAllCheckBox.getValue();
    AdfCustomEvent.queue(selectAllCheckBox, "SelectAllCheckBoxClickEvent", 
    {
        params : isChecked
    },
false);
}

function refreshCategory() {
    var component = AdfPage.PAGE.findComponent("pt1:r1:0:r1:0:refreshHandler");
    if (component == null) {
        component = AdfPage.PAGE.findComponent("pt1:r1:1:r1:0:refreshHandler");
    }
    AdfCustomEvent.queue(component, 'refreshCategory', 
    {
    },
true);
}

function forbidInput(event) {
    var source = event.getSource();
    if (generateKey != null && generateKey != 8) {
        AdfCustomEvent.queue(source, 'forbidInputKeyword', 
        {
        },
true);
    }
}

function refreshbookEdit() {
    var component = AdfPage.PAGE.findComponent("cb7");
    AdfActionEvent.queue(component, component.getPartialSubmit());
}

function recoverFormDataFormat(formId, format) {
    //      alert(formId+'----'+format);
    var docForm = document.getElementById(formId);
    docForm.enctype = format;
}

function uploadFileListener(event) {

    var inputFileId = event.getSource().getProperty("inputFileId");
    //多种格式用","隔开
    var format = event.getSource().getProperty("format");
    var formats = format.split(",");
    var maxSize = event.getSource().getProperty("maxSize");
    var serverListenerName = event.getSource().getProperty("serverListenerName");
    var docFormId = event.getSource().getProperty("docFormId");
    //      var form = AdfPage.PAGE.findComponentByAbsoluteId("f1");
    var inputFile = event.getSource().findComponent(inputFileId);
    var dinputFileId = inputFile.getClientId() + "::content";
    var dinputFile = document.getElementById(dinputFileId);
    var files = dinputFile.files;

    if (files.length > 0) {
        var fileName = files[0].name;
        var fileSize = files[0].size;
        //计算出点的位置
        var pos = fileName.lastIndexOf(".");
        //截取点之后的字符串
        var fileNameSux = fileName.substring(pos + 1);
        var flag = false;

        for (var i = 0;i < formats.length;i++) {
            if (formats[i].toLowerCase() == fileNameSux.toLowerCase()) {
                flag = true;
            }
        }
        if (!flag) {
            alert('只支持 [' + format + '] 格式的文件，请选择正确的文件类型！');
            event.cancel();
            return;
        }
        if (fileSize > maxSize) {
            alert('上传文件太大，请重新选择文件！');
            event.cancel();
            return;
        }
        var docForm = document.getElementById(docFormId);
        //上传文件
        var oldFormat = docForm.enctype;
        //1、设置数据提交格式
        docForm.enctype = "multipart/form-data";
        //            alert(serverListenerName);
        AdfCustomEvent.queue(event.getSource(), serverListenerName, 
        {
            'fileName' : fileName, 'fileSize' : fileSize, 'inputFileId' : inputFileId, 'formId' : docFormId, 'enctype' : oldFormat
        },
false);
        //2、还原默认类型
        //            docForm.enctype="application/x-www-form-urlencoded";
    }
    else {
        alert("请选择文件!");
    }

}

function showDownloadFrame(param) {
    var applet = document.getElementById('downloadApplet');
    applet.openDownloadFrame(param);
}

function invokeUploadAction(format) {
    window.frames[1].invokeUploadAction(format);
}

function queryWhenPressEnter(event) {
    var k = event.getKeyCode();
    var component = event.getSource();
    if (k == 13) {
        AdfCustomEvent.queue(component, "queryWhenPressAction", 
        {
        },
false);
    }
    // 传入查询按钮id
    AdfActionEvent.queue(component, component.getPartialSubmit());
}

function refreshZTree() {
    //    parent.location.reload();
    parent.refreshZTree();
}

function saveExtendAttrs() {
    var saveExtendAttrBtnId = $("[id$='saveExtendAttrBtn']:first").attr("id");
    var exportCmd = AdfPage.PAGE.findComponentByAbsoluteId(saveExtendAttrBtnId);
    var actionEvent = new AdfActionEvent(exportCmd);
    actionEvent.forceFullSubmit();
    actionEvent.queue();
}

function closePopup() {
    var returnBtnId = $("[id$='returnBtn']:first").attr("id");
    var exportCmd = AdfPage.PAGE.findComponentByAbsoluteId(returnBtnId);
    var actionEvent = new AdfActionEvent(exportCmd);
    actionEvent.forceFullSubmit();
    actionEvent.queue();
}