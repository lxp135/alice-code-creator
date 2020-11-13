$(function () {

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/basic/base/dictionary/area/selectOne',
        data : {id:jQuery.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#areaParentId").val(rsp.areaParentId);
            $("#areaCode").val(rsp.areaCode);
            $("#areaName").val(rsp.areaName);
            $("#areaLevel").val(rsp.areaLevel);
            $("#areaLatitude").val(rsp.areaLatitude);
            $("#areaLongitude").val(rsp.areaLongitude);
            $("#remark").val(rsp.remark);

            // Select2
            $(".select2").select2({
                width: '100%',
                placeholder: '请选择',
                minimumResultsForSearch: Infinity // 隐藏搜索框
            });
        }
    });

    // 初始化表单验证
    $('#editForm').validator({
        timely: 2,
        theme:'yellow_right',
        target: function(input) {
            var formItem = $(input).closest('div.form-group'),
                msgBox = formItem.find('span.msg-box');
            if (!msgBox.length) {
                msgBox = $('<span class="msg-box"></span>').appendTo(formItem);
            }
            return msgBox;
        },
        fields: {
            'areaParentId': 'required',
            'areaName': 'required',
            'areaLevel': 'required',
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/basic/base/dictionary/area/update',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/basic/base/dictionary/area/base_dictionary_area_list";
                        });
                    }
                });
            }
        });
    });

});