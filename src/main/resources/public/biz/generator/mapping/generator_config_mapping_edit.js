$(function () {

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/generator/mapping/selectOne',
        data : {id:jQuery.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#dbType").val(rsp.dbType);
            $("#jdbcType").val(rsp.jdbcType);
            $("#javaType").val(rsp.javaType);

            // 初始化数据源类型下拉框
            $.alicej.cache.renderSimpleDropdowns({
                selectId : "datasourceType",
                groupCode :"DATASOURCE_TYPE",
                key: rsp.datasourceType
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
            'datasourceType': 'required',
            'dbType': 'required',
            'jdbcType': 'required',
            'javaType': 'required'
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/generator/mapping/update',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/generator/mapping/generator_config_mapping_list";
                        });
                    }
                });
            }
        });
    });

});