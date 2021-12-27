// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/generator/mapping/generator_config_mapping_edit?id="+id;
        },
        // 根据ID删除数据
        deleteById : function (id) {
            jQuery.alicej.util.bootbox.showConfirm("是否删除该记录？",function(){
                jQuery.ajax({
                    type: "POST",
                    url: '/web/generator/mapping/delete',
                    data : {id:id},
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("删除成功！",function (){
                            page.obj.gridTable.trigger("reloadGrid");
                        });
                    }
                });
            });
        }
    },
    // 对象集合
    obj:{}
};

jQuery(function(){

    // 初始化数据源类型下拉框
    $.alicej.cache.renderSimpleDropdowns({
        selectId : "datasourceType",
        groupCode :"DATASOURCE_TYPE"
    });

    // 初始化jqGrid
    page.obj.gridTable = $("#gridTable");

    page.obj.gridTable.jqGrid({
        mtype:"POST",
        url: "/web/generator/mapping/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'自增主键', index:'id', sortable:false, hidden:true},
            {name:'datasourceType',label:'数据源类型', index:'datasourceType', sortable:true},
            {name:'dbType',label:'数据库类型', index:'dbType', sortable:true},
            {name:'jdbcType',label:'jdbc类型', index:'jdbcType', sortable:true},
            {name:'javaType',label:'java类型', index:'javaType', sortable:true},
            {name:'', label:'操作', index:'', width:100, fixed:true, sortable:false, resize:false,
                formatter:function(cellValue,options,rowObject){
                    var context = "";
                    context += '<button class="btn btn-xs btn-info" onclick="page.fn.openEditPage(' + rowObject.id + ')"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></button> ';
                    context += '<button class="btn btn-xs btn-danger" onclick="page.fn.deleteById(' + rowObject.id + ')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button> ';
                    return context;
                }
            }
        ],
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,50],
        sortname:"updateTime",
        sortorder:"desc",
        altRows: true,
        pager : "#gridPager",
        jsonReader: { //server返回Json解析设定
            root: "content", //对于json中数据列表
            page: "pageNum",
            total: "total",
            records: "records"
        },
        loadComplete:function () {
            page.obj.gridTable.setGridWidth(page.obj.gridTable.parents(".card-body").width(), false);
        }
    });

    // 重置数据列表宽度
    $(window).resize(function(){
        page.obj.gridTable.setGridWidth(page.obj.gridTable.parents(".card-body").width(), false);
    });

    /**
    * 查询
    */
    $("#btnQuery").click(function(){

        // 取得查询参数
        var gridParam = {
            url: "/web/generator/mapping/selectPage",
            postData:jQuery.alicej.util.serialize("#searchForm"),
            page:1
        };

        page.obj.gridTable.jqGrid("setGridParam", gridParam).trigger("reloadGrid");
    });

    /**
    * 重置输入框
    */
    $("#btnReset").click(function () {
        var searchForm = $("#searchForm");
        searchForm.find("input").val("");
        searchForm.find("select").val("");
    });

});