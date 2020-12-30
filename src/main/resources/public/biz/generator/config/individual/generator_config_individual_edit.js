$(function () {

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/generator/config/individual/mine',
        data : {id:jQuery.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){

            if(rsp.content!=null){
                $("#id").val(rsp.content.id);
                $("#defaultSign").val(rsp.content.defaultSign);
                $("#defaultPackage").val(rsp.content.defaultPackage);
                $("#defaultTablePrefix").val(rsp.content.defaultTablePrefix);
                $("#defaultGroupId").val(rsp.content.defaultGroupId);
                $("#defaultGroupName").val(rsp.content.defaultGroupName);
                $("#defaultDatasourceId").val(rsp.content.defaultDatasourceId);
                $("#defaultDatasourceName").val(rsp.content.defaultDatasourceName);
                $("#defaultFieldUnique").val(rsp.content.defaultFieldUnique);
                $("#defaultFieldExt").val(rsp.content.defaultFieldExt);
                $("#defaultFieldEffective").val(rsp.content.defaultFieldEffective);
            }

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
            'defaultSign': 'required',
            'defaultPackage': 'required'
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/generator/config/individual/save',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        $("#id").val(rsp.content.id);
                        jQuery.alicej.util.bootbox.alert("保存成功！");
                    }
                });
            }
        });
    });

});