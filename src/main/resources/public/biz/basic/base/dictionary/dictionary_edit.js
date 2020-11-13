$(function () {

    // 取得待修改的数据
    $.alicej.util.ajax({
        type: "POST",
        url: '/web/basic/base/dictionary/selectOne',
        data : {id : $.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#groupCode").val(rsp.groupCode);
            $("#groupName").val(rsp.groupName);
            $("#dictCode").val(rsp.dictCode);
            $("#dictName").val(rsp.dictName);
            $("#isEnable").attr("checked",rsp.isEnable == 1);
            $("#remark").val(rsp.remark);

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
                    'groupCode': 'required',
                    'groupName': 'required',
                    'dictCode': 'required',
                    'dictName': 'required'
                }
            });
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        $("#editForm").isValid(function(isValid) {
            if(isValid){

                const params = $.alicej.util.serialize("#editForm");
                params.isEnable = $("#isEnable").prop('checked') ? 1 : 0;

                $.alicej.util.ajax({
                    type: "POST",
                    url: '/web/basic/base/dictionary/update',
                    data : params,
                    dataType:'json',
                    success: function(rsp){
                        $.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/basic/base/dictionary/dictionary_list";
                        });
                    }
                });
            }
        });
    });
});