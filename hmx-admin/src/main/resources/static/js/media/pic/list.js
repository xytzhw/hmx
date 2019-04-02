/**
 * Created by shi on 2018/10/4.
 */

$(function () {
    initTable();
});

function initTable() {
    var option = {
        url: '/media/pic/getList',
        queryParams: function (params) {
            var param = {};
            param.page = params.pageNumber - 1;
            param.size = params.pageSize;
            return param;
        },//传递参数
        columns: [
            {
                field: 'id',
                title: '编号',
                halign: 'center',
                align: 'center'
            }, {
                field: 'title',
                title: '标题',
                halign: 'center',
                align: 'center'
            }, {
                field: 'url',
                title: '链接地址',
                halign: 'center',
                align: 'center',
            }, {
                field: 'isShow',
                title: '状态',
                halign: 'center',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return "显示";
                    } else {
                        return "不显示";
                    }
                }
            }, {
                field: 'createTime',
                title: '创建时间',
                halign: 'center',
                align: 'center',
                formatter: function (value, row, index) {
                    var dateee  = new Date(row.createTime).toJSON();
                    var date = new Date(+new Date(dateee )+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
                    return date;
                }
            }, {
                title: '操作',
                halign: 'center',
                align: 'center',
                formatter: function (value, row, index) {
                    var isShow;
                    if (row.isShow) {
                        isShow = '<a href="javascript:void(0)" class="delete" title="不显示" onclick="isShow(' + row.id + ',0)">不显示</a>';
                    } else {
                        isShow = '<a href="javascript:void(0)" class="delete" title="显示" onclick="isShow(' + row.id + ',1)">显示</a>';
                    }
                    var isDelete = '<a href="javascript:void(0)" class="delete" title="删除" onclick="deleteModel(' + row.id + ')">删除</a>';
                    return isShow + isDelete;
                }
            }
        ]
    };
    $('#garouselList').bootstrapTable($.initTableArg(option));
} //表格

//列表搜索
function searchList() {
    $("#garouselList").bootstrapTable('refresh');
}


function imgPreview(fileDom) {
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function (e) {
        //获取图片dom
        var img = document.getElementById("preview");
        //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
}

function isShow(id, isShow) {
    $.fn.getAjaxJSON('post', '/media/pic/update?id=' + id + "&show=" + isShow, null, function (result, e) {
        if (result.status == 10000) {
            $.fn.messageBox('success', '修改成功！', function () {
                searchList();
            });
        } else {
            $.fn.messageBox('error', '修改失败！', function () {
            });
        }
    });
}

function deleteModel(id) {
    $.fn.getAjaxJSON('post', '/garousel/delete?id=' + id, null, function (result, e) {
        if (result.status == 10000) {
            $.fn.messageBox('success', '删除成功！', function () {
                searchList()
            });
        } else {
            $.fn.messageBox('error', '删除失败！', function () {
            });
        }
    });
}

$('#addModal').on('hidden.bs.modal', '.modal', function () {
    $("#addModal input").val("");
});

function openAdd() {
    var para = getParameter();
    if (para != null) {
        $.ajax({
            url: "/garousel/addOrUpdate",
            data: para,
            type: "Post",
            dataType: "json",
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (result) {
                if (result.status == 10000) {
                    $.fn.messageBox('success', '操作成功！', function () {
                        $("#addModal").modal('hide');  //手动关闭
                        searchList()
                    });
                } else {
                    $.fn.messageBox('error', '操作失败！', function () {
                    });
                }
            },
        })
    }
}

function getParameter() {
    var formFile = new FormData();
    var fileObj = document.getElementById("img").files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        var imgUrl = $("#imgUrl").attr("name");
        if (imgUrl != null && imgUrl != "" && imgUrl != "undefined") {
            formFile.append("img", imgUrl);
        } else {
            alert("请选择图片");
            return;
        }
    }
    formFile.append("title", $("#titleAdd").val());
    formFile.append("grade", $("#gradeAdd").val());
    formFile.append("url", $("#urlAdd").val());
    formFile.append("isShow", $("#showAdd").val());
    formFile.append("file", fileObj); //加入文件对象
    return formFile;
}