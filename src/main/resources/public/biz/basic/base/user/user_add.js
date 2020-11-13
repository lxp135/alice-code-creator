$(function () {
    // Select2
    $(".select2").select2({
        width: '100%',
        placeholder: '请选择',
        minimumResultsForSearch: Infinity // 隐藏搜索框
    });

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
            'userName': 'required',
            'userAccount': 'required',
            'userPassword': '密码:required;password;',
            'userPasswordReInput': '确认密码:match(userPassword);',
            'userSex': 'required',
            'isEnable': 'required'
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        $("#addForm").isValid(function(isValid) {
            if(isValid){
                $.alicej.util.ajax({
                    type: "POST",
                    url: '/web/basic/base/user/insert',
                    data : $.alicej.util.serialize("#addForm"),
                    dataType:'json',
                    success: function(rsp){
                        if(rsp)
                            $.alicej.util.bootbox.alert("保存成功！",function () {
                                window.location.href = "#biz/basic/base/user/user_list";
                            });
                    }
                });
            }
        });
    });
});