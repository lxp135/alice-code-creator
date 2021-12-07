$(function () {

    // 取得待修改的数据
    $.ajax({
        type: "POST",
        url: '/web/basic/base/role/selectOne',
        data : {id : $.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#roleName").val(rsp.roleName);
            $("#roleDescription").val(rsp.roleDescription);
            $("#isEnable").val(rsp.isEnable);

            // 初始化表单验证
            $('#editForm').validator({
                timely: 2,
                theme:'yellow_right',
                target: function(input) {
                    var $formitem = $(input).closest('div.form-group'),
                        $msgbox = $formitem.find('span.msg-box');
                    if (!$msgbox.length) {
                        $msgbox = $('<span class="msg-box"></span>').appendTo($formitem);
                    }
                    return $msgbox;
                },
                fields: {
                    'roleName': 'required',
                    'isEnable': 'required'
                }
            });

            // Select2
            $(".select2").select2({
                width: '100%',
                placeholder: '请选择',
                minimumResultsForSearch: Infinity // 隐藏搜索框
            });
        }
    });

// 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        $("#editForm").isValid(function(isValid) {
            if(isValid){
                $.alicej.util.ajax({
                    type: "POST",
                    url: '/web/basic/base/role/update',
                    data : $.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        $.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/basic/base/role/role_list";
                        });
                    }
                });
            }
        });
    });
});