var generator = {
    fn:{
        /**
         * 初始化方法
         * 查询数据源码表
         */
        init : function (){
            jQuery.alicej.util.ajax({
                type:"POST",
                dataType:'json',
                url: "/generator/selectDatasource",
                success:function (data) {
                    $("#datasource").empty().append("<option value=''>请选择</option>");
                    for (var i = 0; i < data.length; i++) {
                        $("#datasource").append("<option value='" + data[i].id + "'>" + data[i].datasourceName + "</option>");
                    }
                }
            });
        },
        /**
         * 查询数据库列表
         * @param datasourceId 数据源编号
         */
        initDatabase : function (datasourceId){
            jQuery.alicej.util.ajax({
                type:"POST",
                dataType:'json',
                data : {
                    datasourceId: datasourceId
                },
                url: "/generator/selectDatabase",
                success:function (data) {
                    $("#tableSchema").empty().append("<option value=''>请选择</option>");
                    for (var i = 0; i < data.length; i++) {
                        $("#tableSchema").append("<option value='" + data[i].tableSchema + "'>" + data[i].tableSchema + "</option>");
                    }
                }
            });
        }
    },
    obj:{
        // 唯一标识字段，用户有自定义配置时会被覆盖
        defaultFieldUnique:'id',
        // 系统默认扩展字段，用户有自定义配置时会被覆盖
        defaultFieldExt:['remark','create_user','create_time','update_user','update_time','ts'],
        // 系统默认逻辑删除字段，用户有自定义配置时会被覆盖
        defaultFieldEffective:'is_delete'
    }
}

/**
 * MySQL元数据代码生成页面JS
 * @author contact@liuxp.me
 */
$(function(){

    $("#groupName").click(function (){
        groupCommon.fn.choose(function (rowData,index){

            $("#groupId").val(rowData.id);
            $("#groupName").val(rowData.groupName);
            $("#tablePrefix").val(rowData.defaultTablePrefix);
            $("#author").val(rowData.defaultSign);
            $("#packagePath").val(rowData.defaultPackage);

            generator.obj.defaultFieldUnique = rowData.defaultFieldUnique;
            generator.obj.defaultFieldExt = [];

            if(null!=rowData.defaultFieldExt){
                var defaultFieldExt = rowData.defaultFieldExt.split(',');
                for(var i=0;i<defaultFieldExt.length;i++){
                    generator.obj.defaultFieldExt.push(defaultFieldExt[i]);
                }
            }
            generator.obj.defaultFieldEffective = rowData.defaultFieldEffective;

            if($("#tableName").val() != null){
                query();
            }

            layer.close(index);
        });
    });

    // 初始化
    generator.fn.init();

    var gridTable = jQuery("#gridTable");

    // 加载数据列表
    loadGrid(gridTable);

    // 重置数据列表宽度
    $(window).resize(function(){
        gridTable.setGridWidth(gridTable.parents(".card-body").width(), false);
    });

    $('#datasource').change(function() {
        //初始化数据
        $("#tableSchema").empty().append("<option value=''>请选择</option>");
        $("#tableName").empty().append("<option value=''>请选择</option>");
        $("#tableComment").val("");
        clear();

        var datasource = $(this).val();
        if(datasource != ""){
            generator.fn.initDatabase(datasource);
        }
    });

    $('#tableSchema').change(function() {
        //初始化数据
        $("#tableName").empty().append("<option value=''>请选择</option>");
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

        if(null===tableComment||""===tableComment||"null"===tableComment){
            $("#tableComment").val(tableName);
        }

    });

    $("#generator").click(download);

    // 初始化表单验证
    $('#generatorForm').validator({
        timely: 2,
        theme:'yellow_top',
        fields: {
            'datasource': 'required',
            'tableSchema': 'required',
            'tableName': 'required',
            'author': 'required',
            'packagePath': 'required',
            'groupName': 'required'
        }
    });
});

/**
 * 获取数据表
 */
function selectTableNames(tableSchema){
    jQuery.alicej.util.ajax({
        type : "POST",
        dataType:'json',
        url : "/generator/selectTableNames",
        data : {
            tableSchema: tableSchema,
            datasourceId:$("#datasource").val()
        },
        success : function(data) {
            for(var i=0; i<data.length; i++){
                $("#tableName").append("<option value='"+data[i].tableName+"' tableComment='"+data[i].tableComment+"'>"+data[i].tableName+"</option>");
            }
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
                },
                formatter: function (cellValue, options, rowObject) {
                    var result = "";
                    if(cellValue !== undefined){
                        result = cellValue;
                    }
                    if(rowObject.columnName === generator.obj.defaultFieldUnique){
                        result = "是";
                    }
                    return result;
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
                    if(cellValue !== undefined){
                        result = cellValue;
                    }

                    for(var i=0;i<generator.obj.defaultFieldExt.length;i++){
                        if(rowObject.columnName === generator.obj.defaultFieldExt[i]){
                            result = "是";
                        }
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
                    if(cellValue !== undefined){
                        result = cellValue;
                    }
                    if(rowObject.columnName === generator.obj.defaultFieldEffective){
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
                    value : ":;CREATOR_NAME:CREATOR_NAME;CREATE_TIME:CREATE_TIME;OPERATOR_NAME:OPERATOR_NAME;OPERATE_TIME:OPERATE_TIME"
                },
                formatter: function (cellValue, options, rowObject) {
                    var result = "";
                    if(rowObject.columnName == "create_user"){
                        result = "CREATOR_NAME";
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
            tableName : $("#tableName").val(),
            datasourceId:$("#datasource").val()
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
                if (rows[i].columnComment == null || rows[i].columnComment === "" ) {
                    rows[i].columnComment = rows[i].columnName;
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