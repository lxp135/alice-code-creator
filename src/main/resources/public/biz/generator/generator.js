let generator = {
    fn:{
        init : function (){
            jQuery.alicej.util.ajax({
                type:"POST",
                dataType:'json',
                url: "/generator/selectDatasource",
                success:function (data) {
                    $("#datasource").empty();
                    $("#datasource").append("<option value=''>请选择</option>");
                    // $("#tableSchema").select2("val", "");
                    for (var i = 0; i < data.length; i++) {
                        $("#datasource").append("<option value='" + data[i].id + "'>" + data[i].datasourceName + "</option>");
                    }
                }
            });
        },
        initDatabase : function (datasource){
            jQuery.alicej.util.ajax({
                type:"POST",
                dataType:'json',
                data : {
                    datasourceId: datasource
                },
                url: "/generator/selectDatabase",
                success:function (data) {
                    $("#tableSchema").empty();
                    $("#tableSchema").append("<option value=''>请选择</option>");
                    // $("#tableSchema").select2("val", "");
                    for (var i = 0; i < data.length; i++) {
                        $("#tableSchema").append("<option value='" + data[i].tableSchema + "'>" + data[i].tableSchema + "</option>");
                    }
                }
            });
        }
    },
    obj:{

    }
}

/**
 * MySQL元数据代码生成页面JS
 * @author contact@liuxp.me
 */
$(function(){

    // 初始化
    generator.fn.init();

    var gridTable = jQuery("#gridTable");

    // initDatabaseData();

    // 加载数据列表
    loadGrid(gridTable);

    // 重置数据列表宽度
    $(window).resize(function(){
        gridTable.setGridWidth(gridTable.parents(".card-body").width(), false);
    });

    $('#datasource').change(function() {
        //初始化数据
        $("#tableSchema").empty();
        $("#tableSchema").append("<option value=''>请选择</option>");
        $("#tableName").empty();
        $("#tableName").append("<option value=''>请选择</option>");
        // $("#tableName").select2("val", "");
        $("#tableComment").val("");
        clear();

        var datasource = $(this).val();
        if(datasource != ""){
            generator.fn.initDatabase(datasource);
        }
    });

    $('#tableSchema').change(function() {
        //初始化数据
        $("#tableName").empty();
        $("#tableName").append("<option value=''>请选择</option>");
        // $("#tableName").select2("val", "");
        $("#tableComment").val("");
        clear();

        var tableSchema = $(this).val();
        if(tableSchema != ""){
            selectTableNames(tableSchema);
        }
    });

    $('#tableName').change(function() {
        $("#tableComment").val("");
        clear();

        if($(this).val() != ""){
            query();
        }
        var tableName = $(this).find('option:selected').text();
        var tableComment = $(this).find('option:selected').attr("tableComment");
        $("#tableComment").val(tableComment);

        // if(tableName.indexOf("_")>0) {
        //     $("#tablePrefix").val(tableName.substring(0, tableName.indexOf("_")));
        // }
    });

    $("#generator").click(download);

    // 初始化表单验证
    $('#generatorForm').validator({
        timely: 2,
        theme:'yellow_top',
        fields: {
            'tableSchema': 'required',
            'tableName': 'required',
            'author': 'required',
            'packagePath': 'required'
        }
    });
});

/**
 * 初始化数据库列表
 */
function initDatabaseData(){
    jQuery.ajax({
        type:"POST",
        dataType:'json',
        url: "/generator/selectDatabase",
        success:function (data) {
            $("#tableSchema").empty();
            $("#tableSchema").append("<option value=''>请选择</option>");
            // $("#tableSchema").select2("val", "");
            for (var i = 0; i < data.length; i++) {
                $("#tableSchema").append("<option value='" + data[i].tableSchema + "'>" + data[i].tableSchema + "</option>");
            }
        },
        error : function(data){
            Commons.showError(data.responseText);
        }
    });
}

/**
 * 获取数据表
 */
function selectTableNames(tableSchema){
    jQuery.ajax({
        type : "POST",
        dataType:'json',
        url : "/generator/selectTableNames",
        data : {
            tableSchema: tableSchema
        },
        success : function(data) {
            for(var i=0; i<data.length; i++){
                $("#tableName").append("<option value='"+data[i].tableName+"' tableComment='"+data[i].tableComment+"'>"+data[i].tableName+"</option>");
            }
        },
        error: function (data) {
            Commons.showError(data.responseText);
        }
    });
}

/**
 * 加载数据列表
 */
var lastSelectId;
function loadGrid(gridTable){
    gridTable.jqGrid({
        mtype:"POST",
        datatype: "local",
        autowidth: true,
        height: "300",
        colModel:[
            {name:'columnKey',label:'主键', hidden:true},
            {name:'extra',label:'主键策略', hidden:true},
            {name:'isNullable',label:'是否可以为空', hidden:true},
            {name:'tableSchema',label:'数据库名', width:100, fixed:false, sortable: false,  hidden:true},
            {name:'tableName',label:'数据表名', width:100, fixed:false, sortable: false,  hidden:true},
            {name:'columnName',label:'字段名称', width:100, fixed:false, sortable: false},
            {name:'columnComment',label:'字段描述', width:100, fixed:false, sortable: false,  editable : true},
            {name:'dataType',label:'数据类型', width:100, fixed:false, sortable: false},
            {name:'maxLength',label:'字段长度', width:100, fixed:false, sortable: false},
            {name:'uniqueFlag',label:'唯一标识', width:80, fixed:false, sortable: false, 
                editable : true,
                edittype: "checkbox",
                editoptions: {
                    value: "是:"
                }
            },
            {name:'likeFlag',label:'模糊标识', width:80, fixed:false, sortable: false, 
                editable : true,
                edittype: "checkbox",
                editoptions: {
                    value: "是:"
                }
            },
            {name:'extFlag',label:'扩展标识', width:80, fixed:false, sortable: false, 
                editable : true,
                edittype: "checkbox",
                editoptions: {
                    value: "是:"
                },
                formatter: function (cellValue, options, rowObject) {
                    var result = "";
                    if(rowObject.columnName == "remark"){
                        result = "是";
                    }else if(rowObject.columnName == "create_user"){
                        result = "是";
                    }else if(rowObject.columnName == "create_time"){
                        result = "是";
                    }else if(rowObject.columnName == "update_user"){
                        result = "是";
                    }else if(rowObject.columnName == "update_time"){
                        result = "是";
                    }else if(rowObject.columnName == "ts"){
                        result = "是";
                    }
                    return result;
                }
            },
            {name:'effectiveFlag',label:'逻辑标识', width:80, fixed:false, sortable: false, 
                editable : true,
                edittype: "checkbox",
                editoptions: {
                    value: "是:"
                },
                formatter: function (cellValue, options, rowObject) {
                    var result = "";
                    if(rowObject.columnName == "is_delete"){
                        result = "是";
                    }
                    return result;
                }
            },
            {name:'filterFlag',label:'过滤标识', width:80, fixed:false, sortable: false, 
                editable : true,
                edittype: "checkbox",
                editoptions: {
                    value: "是:"
                }
            },
            {name:'autoFill',label:'自动填充', width:80, fixed:false, sortable: false, 
                editable : true,
                edittype: "select",
                editoptions: {
                    value : ":;CREATER_NAME:CREATER_NAME;CREATE_TIME:CREATE_TIME;OPERATOR_NAME:OPERATOR_NAME;OPERATE_TIME:OPERATE_TIME"
                },
                formatter: function (cellValue, options, rowObject) {
                    var result = "";
                    if(rowObject.columnName == "create_user"){
                        result = "CREATER_NAME";
                    }else if(rowObject.columnName == "create_time"){
                        result = "CREATE_TIME";
                    }else if(rowObject.columnName == "update_user"){
                        result = "OPERATOR_NAME";
                    }else if(rowObject.columnName == "update_time"){
                        result = "OPERATE_TIME";
                    }
                    return result;
                }
            }
        ],
        rownumbers:true,//添加左侧行号
        viewrecords : true,
        rowNum:1000,
        altRows: true,
        //altclass: "",
        pager : "#gridPager",
        jsonReader: {
            root: "root" //对于json中数据列表
        },
        onSelectRow : function(id) {
            if (id && id !== lastSelectId) {
                gridTable.jqGrid('saveRow', lastSelectId, false, 'clientArray');
                gridTable.jqGrid('editRow', id, false);
                lastSelectId = id;
            }
        },loadComplete :function () {
            gridTable.setGridWidth(gridTable.parents(".card-body").width(), false);
        }
    });
}

/**
 * 查询
 */
function query(){
    var gridParam = {
        datatype:'json',
        url:"/generator/selectColumnNames",
        postData:{
            tableSchema : $("#tableSchema").val(),
            tableName : $("#tableName").val()
        },
        page:1
    };
    $("#gridTable").jqGrid("setGridParam", gridParam).trigger("reloadGrid");
}

/**
 * 清除列表数据
 */
function clear(){
    $("#gridTable").jqGrid("clearGridData");

}

/**
 *  代码生成
 */
function download() {

    $("#gridTable").jqGrid('saveRow', lastSelectId, false, 'clientArray');
    lastSelectId = null;

    $("#generatorForm").isValid(function(isValid) {
        if(isValid){
            var rows = $("#gridTable").jqGrid('getRowData');
            for(var i = 0; i < rows.length; i++) {
                if (rows[i].columnComment == "" || rows[i].columnComment == null) {
                    $.alicej.util.bootbox.alert("字段描述不允许为空！");
                    return;
                }
            }

            $("#columnJson").val(JSON.stringify(rows));
            var m = $('#generatorForm')[0];
            m.method = 'POST';
            m.action = '/generator/downLoad';
            m.submit();
        }
    });
}