$(function () {

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/generator/config/datasource/selectOne',
        data : {id:jQuery.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#datasourceName").val(rsp.datasourceName);
            $("#datasourceType").val(rsp.datasourceType);
            $("#driverClassName").val(rsp.driverClassName);
            $("#url").val(rsp.url);
            $("#username").val(rsp.username);
            $("#password").val(rsp.password);

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
            'datasourceName': 'required',
            'datasourceType': 'required',
            'driverClassName': 'required',
            'url': 'required'
        }
    });

    $('#datasourceType').change(function (){
        // 根据数据库类型带出JDBC驱动
        let datasourceJdbc = {
            'MySQL' : 'com.mysql.cj.jdbc.Driver',
            'Oracle' : 'oracle.jdbc.driver.OracleDriver',
            'SQLServer' : 'com.microsoft.sqlserver.jdbc.SQLServerDriver'
        }
        $('#driverClassName').val(datasourceJdbc[$(this).val()]);
    })

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/generator/config/datasource/update',
                    data : jQuery.alicej.util.serialize("#editForm"),
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
        $("#editForm").isValid(function(isValid){
            if(isValid){
                $("#testDatasourceLoading").show();
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: '/generator/testDatasource',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        $("#testDatasourceLoading").hide();
                        layer.msg("链接成功！");
                    },
                    error: function(rsp){
                        $("#testDatasourceLoading").hide();
                    }
                });
            }
        });
    });

});