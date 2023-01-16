$(function () {

    // 初始化数据源类型下拉框
    $.alicej.cache.renderSimpleDropdowns({
        selectId : "datasourceType",
        groupCode :"DATASOURCE_TYPE"
    });

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
            'datasourceName': 'required',
            'datasourceType': 'required',
            'driverClassName': 'required',
            'url': 'required'
        }
    });

    $('#datasourceType').change(function (){
        // 根据数据库类型带出JDBC驱动
        $('#driverClassName').val($.alicej.constant.jdbc[$(this).val()]);
    })

    // 默认使用MySQL的JDBC驱动
    $('#driverClassName').val('com.mysql.cj.jdbc.Driver');

    // 保存数据
    $("#btnSave").click(function(){
        $("#addForm").isValid(function(isValid){
        if(isValid){
            jQuery.alicej.util.ajax({
            type: "POST",
                url: '/web/generator/config/datasource/insert',
                data : jQuery.alicej.util.serialize("#addForm"),
                dataType:'json',
                success: function(rsp){
                    jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                        window.location.href = "#biz/generator/config/datasource/generator_config_datasource_list";
                        });
                    }
                });
            }
        });
    });

    // 测试链接
    $("#testDatasource").click(function(){
        $("#addForm").isValid(function(isValid){
            if(isValid){
                $("#testDatasourceLoading").show();
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: '/generator/testDatasource',
                    data : jQuery.alicej.util.serialize("#addForm"),
                    dataType:'json',
                    success: function(rsp){
                        layer.msg("链接成功！");
                        $("#testDatasourceLoading").hide();
                    },
                    error: function(rsp){
                        $("#testDatasourceLoading").hide();
                    }
                });
            }
        });
    });
});