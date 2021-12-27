$(function () {

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/basic/base/user/selectOne',
        data : {id:jQuery.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#userAccount").val(rsp.userAccount);
            $("#userName").val(rsp.userName);
            $("#userEmail").val(rsp.userEmail);
            $("#userWechat").val(rsp.userWechat);
            $("#userPhone").val(rsp.userPhone);
            $("#remark").val(rsp.remark);

            // 性别下拉框
            $.alicej.cache.renderSimpleDropdowns({
                selectId : "userSex",
                groupCode :"USER_SEX",
                key:rsp.userSex
            });

            // 账户状态下拉框
            $.alicej.cache.renderSimpleDropdowns({
                selectId : "isEnable",
                groupCode :"STATUS",
                key:rsp.isEnable
            });

            // Select2
            $(".select2").select2({
                width: '100%',
                placeholder: '请选择',
                minimumResultsForSearch: Infinity // 隐藏搜索框
            });
        }
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
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/basic/base/user/update',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/basic/base/user/user_list";
                        });
                    }
                });
            }
        });
    });
});