function fileQueueError(file, errorCode, message) {
    try {
        var imageName = "<font color='red'>文件上传错误<\/font>";
        var errorName = "";
        if (errorCode === SWFUpload.errorCode_QUEUE_LIMIT_EXCEEDED) {
            errorName = "You have attempted to queue too many files.";
        }

        if (errorName !== "") {
            alert(errorName);
            return;
        }
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                imageName = "<font color='red'>文件大小为0<\/font>";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                imageName = "<font color='red'>文件大小超过限制<\/font>";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
            default :
                alert(message);
                break;
        }
        addReadyFileInfo(file.id, file.name, file.size, imageName, "无法上传");
    }
    catch (ex) {
        this.debug(ex);
    }
}

/**
 * 当文件选择对话框关闭消失时，如果选择的文件成功加入上传队列，
 * 那么针对每个成功加入的文件都会触发一次该事件（N个文件成功加入队列，就触发N次此事件）。
 * @param {} file
 * id : string,			    // SWFUpload控制的文件的id,通过指定该id可启动此文件的上传、退出上传等
 * index : number,			// 文件在选定文件队列（包括出错、退出、排队的文件）中的索引，getFile可使用此索引
 * name : string,			// 文件名，不包括文件的路径。
 * size : number,			// 文件字节数
 * type : string,			// 客户端操作系统设置的文件类型
 * creationdate : Date,		// 文件的创建时间
 * modificationdate : Date,	// 文件的最后修改时间
 * filestatus : number		// 文件的当前状态，对应的状态代码可查看SWFUpload.FILE_STATUS }
 */
function fileQueued(file) {
    addReadyFileInfo(file.id, file.name, file.size, "成功加载到上传队列");
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
    try {
        if (numFilesQueued > 0) {
            this.startUpload();
        }
    }
    catch (ex) {
        this.debug(ex);
    }
}

function uploadProgress(file, bytesLoaded) {
    try {
        var percent = Math.ceil((bytesLoaded / file.size) * 100);
        var progress = new FileProgress(file, this.customSettings.upload_target);
        progress.setProgress(percent);
        if (percent === 100) {
            progress.setStatus("");//正在创建缩略图...
            progress.toggleCancel(false, this);
        }
        else {
            progress.setStatus("正在上传...");
            if (percent > 100) {
                percent = 100;
            }
            addFileInfo(file.id, "正在上传..." + percent + "%");
            progress.toggleCancel(true, this);
        }
    }
    catch (ex) {
        this.debug(ex);
    }
}

function uploadSuccess(file, serverData) {
    try {
        var progress = new FileProgress(file, this.customSettings.upload_target);
        addFileInfo(file.id, "文件上传完成");
    }
    catch (ex) {
        this.debug(ex);
    }
}

function addFileInfo(fileId, message) {
    var row = document.getElementById(fileId);
    row.cells[3].innerHTML = "<font color='green'>" + message + "<\/font>";
}

function renderSize(value) {
    if (null == value || value == '') {
        return "0 Bytes";
    }
    var unitArr = new Array("Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB");
    var index = 0;
    var srcsize = parseFloat(value);
    var quotient = srcsize;
    while (quotient > 1024) {
        index += 1;
        quotient = quotient / 1024;
    }
    return roundFun(quotient, 2) + " " + unitArr[index];
}

function roundFun(numberRound, roundDigit)//处理金额 -by hailang    
{
    if (numberRound >= 0) {
        var tempNumber = parseInt((numberRound * Math.pow(10, roundDigit) + 0.5)) / Math.pow(10, roundDigit);
        return tempNumber;
    }
    else {
        numberRound1 =  - numberRound
        var tempNumber1 = parseInt((numberRound1 * Math.pow(10, roundDigit) + 0.5)) / Math.pow(10, roundDigit);
        return  - tempNumber1;
    }
}

function validateFileExsits(fileName) {
    var infoTable = document.getElementById("infoTable");
    var rows = infoTable.rows;
    if (rows == null) {
        return false;
    }
    for (var i = 1;i < rows.length;i++) {
        var row = rows[i];
        if (fileName == row.cells[1].innerHTML) {
            return true;
        }
    }
    return false;
}

function addReadyFileInfo(fileid, fileName, fileSize, message, status) {
    if (validateFileExsits(fileName)) {
        alert(fileName + '已经存在，不能重复添加');
        this.stopUpload();
        return;
    }
    //用表格显示
    var size = renderSize(fileSize);
    var infoTable = document.getElementById("infoTable");
    var rows = infoTable.rows;
    var row = infoTable.insertRow(rows.length);
    row.id = fileid;
    var col1 = row.insertCell(0);
    var col2 = row.insertCell(1);
    var col3 = row.insertCell(2);
    var col4 = row.insertCell(3);
    var col5 = row.insertCell(4);
    col1.innerHTML = message + " : ";
    col2.innerHTML = fileName;
    col3.innerHTML = size;
    if (status != null && status != "") {
        col4.innerHTML = "<font color='red'>" + status + "<\/font>";
    }
    else {
        col4.innerHTML = "";
    }
    col5.innerHTML = "<a href='javascript:deleteFile(\"" + fileid + "\",\"" + fileName + "\")'>删除<\/a>";
    col1.style.width = "100";
    col1.style.fontSize = "12px";
    col2.style.width = "200";
    col2.style.fontSize = "12px";
    col3.style.width = "50";
    col3.align = "center";
    col3.style.fontSize = "12px";
    col4.style.width = "100";
    col4.align = "center";
    col4.style.fontSize = "12px";
    col5.style.width = "50";
    col5.align = "center";
    col5.style.fontSize = "12px";
}

function cancelUpload() {
    var infoTable = document.getElementById("infoTable");
    var rows = infoTable.rows;
    var ids = new Array();
    if (rows == null) {
        return false;
    }
    for (var i = 0;i < rows.length;i++) {
        ids[i] = rows[i].id;
    }
    for (var k = 0;k < ids.length;k++) {
        deleteFile(ids[k], null);
    }
}

function deleteFile(fileId, fileName) {
    //用表格显示
    var infoTable = document.getElementById("infoTable");
    var row = document.getElementById(fileId);
    infoTable.deleteRow(row.rowIndex);
    swfu.cancelUpload(fileId, false);
    var url = "/cms/work_platform/fileBatchUpload?method=delete&filename=" + fileName;
    url = encodeURI(url);
    url = encodeURI(url);
    $.post(url);
}

function uploadComplete(file) {
    try {
        /*  I want the next upload to continue automatically so I'll call startUpload here */
        if (this.getStats().files_queued > 0) {
            this.startUpload();
        }
        else {
            var progress = new FileProgress(file, this.customSettings.upload_target);
            progress.setComplete();
            progress.setStatus("<font color='red'>所有文件上传完毕!<\/b><\/font>");
            progress.toggleCancel(false);
        }
    }
    catch (ex) {
        this.debug(ex);
    }
}

function uploadError(file, errorCode, message) {
    var imageName = "<font color='red'>文件上传出错!<\/font>";
    var progress;
    try {
        switch (errorCode) {
            case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
                try {
                    progress = new FileProgress(file, this.customSettings.upload_target);
                    progress.setCancelled();
                    progress.setStatus("<font color='red'>取消上传!<\/font>");
                    progress.toggleCancel(false);
                }
                catch (ex1) {
                    this.debug(ex1);
                }
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
                try {
                    progress = new FileProgress(file, this.customSettings.upload_target);
                    progress.setCancelled();
                    progress.setStatus("<font color='red'>停止上传!<\/font>");
                    progress.toggleCancel(true);
                }
                catch (ex2) {
                    this.debug(ex2);
                }
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
                imageName = "<font color='red'>文件大小超过限制!<\/font>";
                break;
            default :
                alert(message);
                break;
        }
        addFileInfo(file.id, imageName);
    }
    catch (ex3) {
        this.debug(ex3);
    }

}

function addImage(src) {
    var newImg = document.createElement("img");
    newImg.style.margin = "5px";

    document.getElementById("thumbnails").appendChild(newImg);
    if (newImg.filters) {
        try {
            newImg.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 0;
        }
        catch (e) {
            // If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
            newImg.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + 0 + ')';
        }
    }
    else {
        newImg.style.opacity = 0;
    }

    newImg.onload = function () {
        fadeIn(newImg, 0);
    };
    newImg.src = src;
}

function fadeIn(element, opacity) {
    var reduceOpacityBy = 5;
    var rate = 30;// 15 fps
    if (opacity < 100) {
        opacity += reduceOpacityBy;
        if (opacity > 100) {
            opacity = 100;
        }

        if (element.filters) {
            try {
                element.filters.item("DXImageTransform.Microsoft.Alpha").opacity = opacity;
            }
            catch (e) {
                // If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
                element.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + opacity + ')';
            }
        }
        else {
            element.style.opacity = opacity / 100;
        }
    }

    if (opacity < 100) {
        setTimeout(function () {
            fadeIn(element, opacity);
        },
        rate);
    }
}

/* ******************************************
 *	FileProgress Object
 *	Control object for displaying file info
 * ****************************************** */

function FileProgress(file, targetID) {
    this.fileProgressID = "divFileProgress";

    this.fileProgressWrapper = document.getElementById(this.fileProgressID);
    if (!this.fileProgressWrapper) {
        this.fileProgressWrapper = document.createElement("div");
        this.fileProgressWrapper.className = "progressWrapper";
        this.fileProgressWrapper.id = this.fileProgressID;

        this.fileProgressElement = document.createElement("div");
        this.fileProgressElement.className = "progressContainer";

        var progressCancel = document.createElement("a");
        progressCancel.className = "progressCancel";
        progressCancel.href = "#";
        progressCancel.style.visibility = "hidden";
        progressCancel.appendChild(document.createTextNode(" "));

        var progressText = document.createElement("div");
        progressText.className = "progressName";
        progressText.appendChild(document.createTextNode("上传文件: " + file.name));

        var progressBar = document.createElement("div");
        progressBar.className = "progressBarInProgress";

        var progressStatus = document.createElement("div");
        progressStatus.className = "progressBarStatus";
        progressStatus.innerHTML = "&nbsp;";

        this.fileProgressElement.appendChild(progressCancel);
        this.fileProgressElement.appendChild(progressText);
        this.fileProgressElement.appendChild(progressStatus);
        this.fileProgressElement.appendChild(progressBar);

        this.fileProgressWrapper.appendChild(this.fileProgressElement);
        document.getElementById(targetID).style.height = "75px";
        document.getElementById(targetID).appendChild(this.fileProgressWrapper);
        fadeIn(this.fileProgressWrapper, 0);

    }
    else {
        this.fileProgressElement = this.fileProgressWrapper.firstChild;
        this.fileProgressElement.childNodes[1].firstChild.nodeValue = "上传文件: " + file.name;
    }

    this.height = this.fileProgressWrapper.offsetHeight;

}
FileProgress.prototype.setProgress = function (percentage) {
    this.fileProgressElement.className = "progressContainer green";
    this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
    this.fileProgressElement.childNodes[3].style.width = percentage + "%";
};
FileProgress.prototype.setComplete = function () {
    this.fileProgressElement.className = "progressContainer blue";
    this.fileProgressElement.childNodes[3].className = "progressBarComplete";
    this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setError = function () {
    this.fileProgressElement.className = "progressContainer red";
    this.fileProgressElement.childNodes[3].className = "progressBarError";
    this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setCancelled = function () {
    this.fileProgressElement.className = "progressContainer";
    this.fileProgressElement.childNodes[3].className = "progressBarError";
    this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setStatus = function (status) {
    this.fileProgressElement.childNodes[2].innerHTML = status;
};

FileProgress.prototype.toggleCancel = function (show, swfuploadInstance) {
    this.fileProgressElement.childNodes[0].style.visibility = show ? "visible" : "hidden";
    if (swfuploadInstance) {
        var fileID = this.fileProgressID;
        this.fileProgressElement.childNodes[0].onclick = function () {
            swfuploadInstance.cancelUpload(fileID);
            return false;
        };
    }
};