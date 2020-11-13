$(function () {

    // 初始化表单验证
    $('#addForm').validator({
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

    // 保存数据
    $("#btnSave").click(function(){
        $("#addForm").isValid(function(isValid) {

            const params = $.alicej.util.serialize("#addForm");
            params.isEnable = $("#isEnable").prop('checked') ? 1 : 0;

            if(isValid){
                $.alicej.util.ajax({
                    type: "POST",
                    url: '/web/basic/base/dictionary/insert',
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