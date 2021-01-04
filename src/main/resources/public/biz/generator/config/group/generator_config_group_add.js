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
            var formItem = $(input).closest('div.form-group'),
            msgBox = formItem.find('span.msg-box');
            if (!msgBox.length) {
                msgBox = $('<span class="msg-box"></span>').appendTo(formItem);
            }
            return msgBox;
        },
        fields: {
            'groupName': 'required',
            'groupDesc': 'required',
            'ownerUserId': 'required',
            'ownerUserName': 'required',
            'isPublic': 'required',
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        $("#addForm").isValid(function(isValid){
        if(isValid){
            jQuery.alicej.util.ajax({
            type: "POST",
                url: '/web/generator/config/group/insert',
                data : jQuery.alicej.util.serialize("#addForm"),
                dataType:'json',
                success: function(rsp){
                    jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                        window.location.href = "#biz/generator/config/group/generator_config_group_edit?id="+rsp.id;
                        });
                    }
                });
            }
        });
    });
});