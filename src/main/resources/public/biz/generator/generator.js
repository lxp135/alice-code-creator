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
        },
        reloadTab:function(selectedOption){
            // 取得已存在Tab标签
            let alreadyTableList = $("#tableList").find("ul").find("li").find("a");

            let removeTable = null;
            let addTableArray = new Array();

            for(let i=0;i<alreadyTableList.length;i++){
                let flag = true;
                for(let j=0;j<selectedOption.length;j++){
                    if(alreadyTableList.eq(i).html() === selectedOption.eq(j).val()){
                        flag = false;
                    }
                }
                if(flag){
                    removeTable = alreadyTableList.eq(i).html();
                }
            }

            for(let i=0;i<selectedOption.length;i++){
                let flag = true;
                for(let j=0;j<alreadyTableList.length;j++){
                    if(alreadyTableList.eq(j).html() === selectedOption.eq(i).val()){
                        flag = false;
                    }
                }
                if(flag){
                    addTableArray.push(selectedOption.eq(i).val());
                }
            }

            if(removeTable!=null){
                $("#tableList").find("ul").find("#li_"+removeTable).remove();
                $("#"+removeTable).remove();
            }

            if(addTableArray.length>0){

                for(let i=0;i<addTableArray.length;i++){
                    let addTable = addTableArray[i];
                    $("#tableList").find("ul").find("li").find("a").removeClass("active");
                    $("#tableList").find(".tab-content").find("div").removeClass("active");
                    $("#tableList").find("ul").append("<li id=\"li_"+addTable+"\" class=\"nav-item\"><a class=\"nav-link active\" href=\"#"+addTable+"\" data-toggle=\"tab\" role=\"tab\" aria-selected=\"false\">"+addTable+"</a></li>");
                    $("#tableList").find(".tab-content").append("<div class=\"tab-pane active\" id=\""+addTable+"\" role=\"tabpanel\"><div class=\"card-body\"><table id=\""+addTable+"GridTable\"></table><div id=\""+addTable+"GridPager\"></div></div></div>");

                    // 生成jqGrid
                    generator.obj.jqGridList[addTable] = jQuery("#"+addTable+"GridTable");

                    // 加载数据列表
                    loadGrid(generator.obj.jqGridList[addTable],addTable);

                    // 重置数据列表宽度
                    $(window).resize(function(){
                        generator.obj.jqGridList[addTable].setGridWidth(generator.obj.jqGridList[addTable].parents(".card-body").width(), false);
                    });
                }
            }
        },
        /**
         * 下载代码
         */
        download :function() {

            $("#generatorForm").isValid(function(isValid) {
                if(isValid){

                    let tableList = {};

                    let alreadyTableList = $("#tableList").find("ul").find("li").find("a");
                    for(let i=0;i<alreadyTableList.length;i++){
                        let tableName = alreadyTableList.eq(i).html();
                        let tableGrid = $("#"+tableName+"GridTable");
                        tableGrid.jqGrid('saveRow', generator.obj.lastSelectId[tableName], false, 'clientArray');
                        generator.obj.lastSelectId[tableName] = null;

                        let rows = tableGrid.jqGrid('getRowData');
                        for(var j = 0; j < rows.length; j++) {
                            if (rows[j].columnComment == null || rows[j].columnComment === "" ) {
                                rows[j].columnComment = rows[j].columnName;
                            }
                        }

                        let tableComment = null;
                        var tableCommentList = $("#tableName").find('option:selected');
                        for(let j=0;j<tableCommentList.length;j++){
                            let tableCommentArray = tableCommentList.eq(j).text().split("\|");
                            if(tableCommentArray[0].trim() === tableName){
                                tableComment = tableCommentArray[1].trim();
                            }
                        }

                        tableList[tableName] = {
                            tableName:tableName,
                            tableComment:tableComment,
                            rows:rows
                        }
                    }

                    $("#tableListJson").val(JSON.stringify(tableList));
                    var m = $('#generatorForm')[0];
                    m.method = 'POST';
                    m.action = '/generator/downLoad';
                    m.submit();
                }
            });
        }
    },
    obj:{
        // 表格选中行集合
        lastSelectId:{},
        // 表格集合
        jqGridList:{},
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

    $("#tableList").delegate("ul > li > a","click",function (){
        // 这是一段兼容代码，为了解决jqGrid切换Tab后宽度为0的问题
        let tableName = $(this).html();
        setTimeout(function(){
            generator.obj.jqGridList[tableName].setGridWidth(generator.obj.jqGridList[tableName].parents(".card-body").width(), false);
        },100)
    })

    $("#tableName").select2({
        language : "zh-CN",
        minimumInputLength : 0,
        placeholder:"可多选",//默认值
        allowClear: true,
    })

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
                // 取得已存在Tab标签
                let alreadyTableList = $("#tableList").find("ul").find("li").find("a");

                for(let j=0;j<alreadyTableList.length;j++){
                    $("#"+alreadyTableList.eq(j).html()+"GridTable").jqGrid().trigger("reloadGrid");
                }
            }

            layer.close(index);
        });
    });

    // 初始化
    generator.fn.init();

    $('#datasource').change(function() {
        //初始化数据
        $("#tableSchema").empty().append("<option value=''>请选择</option>");
        $("#tableList").find("ul").empty();
        $("#tableList").find(".tab-content").empty();
        $("#tableName").empty();

        //TODO 循环销毁所有jqGird

        var datasource = $(this).val();
        if(datasource != ""){
            generator.fn.initDatabase(datasource);
        }
    });

    $('#tableSchema').change(function() {
        //初始化数据
        $("#tableName").empty();
        $("#tableList").find("ul").empty();
        $("#tableList").find(".tab-content").empty();

        //TODO 循环销毁所有jqGird

        var tableSchema = $(this).val();
        if(tableSchema != ""){
            selectTableNames(tableSchema);
        }
    });

    $('#tableName').change(function() {

        // 取得当前选中项集合
        let selectedOption = $(this).find('option:selected');
        generator.fn.reloadTab(selectedOption);

    });

    $("#generator").click(generator.fn.download);

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
                $("#tableName").append("<option value='"+data[i].tableName+"' tableComment='"+data[i].tableComment+"'>"+data[i].tableName+ " | "+data[i].tableComment+ "</option>");
            }
        }
    });
}

/**
 * 加载数据列表
 */
function loadGrid(gridTable,tableName){
    gridTable.jqGrid({
        mtype:"POST",
        datatype: "json",
        url:"/generator/selectColumnNames",
        postData:{
            tableSchema : $("#tableSchema").val(),
            tableName : tableName,
            datasourceId:$("#datasource").val()
        },
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
        pager : "#"+tableName+"GridPager",
        jsonReader: {
            root: "root" //对于json中数据列表
        },
        onSelectRow : function(id) {
            if (id && id !== generator.obj.lastSelectId[tableName]) {
                gridTable.jqGrid('saveRow', generator.obj.lastSelectId[tableName], false, 'clientArray');
                gridTable.jqGrid('editRow', id, false);
                generator.obj.lastSelectId[tableName] = id;
            }
        },loadComplete :function () {
            gridTable.setGridWidth(gridTable.parents(".card-body").width(), false);
        }
    });
}