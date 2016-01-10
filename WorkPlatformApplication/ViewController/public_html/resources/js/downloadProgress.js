$(function () {
    $.ajax( {
        type : "POST", url : "/cms/work_platform/download?task=first", dataType : "text", async : false, success : function (data) {
            setTimeout("progress()", 10);
        }
    });

    $('#divprogressbar').progressbar( {
        value : 1
    });

    $('#divprogressbar').progressbar( {
        value : 0, change : function () {
            $(".progress-label").text($("#divprogressbar").progressbar("value") + "%");
        },
        complete : function () {
            $(".progress-label").text("Complete!");
        }

    });

    function progress() {
        $.ajax( {
            type : "POST", url : "/cms/work_platform/download?task=poll", dataType : "text", async : false, success : function (data) {
                var jsonobj = eval('(' + data + ')');
                var resName = jsonobj.resName;
                var currFileCount = jsonobj.currFileCount;
                var totalFileCount = jsonobj.totalFileCount;
                $('#res').html(resName);
                var val = $('#divprogressbar').progressbar("value") || 0;
                var percent = (currFileCount / (totalFileCount == 0 ? 1 : totalFileCount)) * 100;
                $('#divprogressbar').progressbar("option", "value", Math.floor(percent));
                $('#progress-num').html(currFileCount + "/" + totalFileCount);
                if (val < 100 && totalFileCount > 0) {
                    setTimeout(progress, 100);
                }
                else if (val == 100 && totalFileCount > 0) {
                    $(".progress-label").text("Complete!");
                }
                else {
                    $(".progress-label").text("No Files!");
                }
            }
        });
    }
    setTimeout(progress, 10);
});