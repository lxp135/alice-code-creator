// 定义page变量
var page = {
    // 方法集合
    fn:{
        openEditPage : function (id) {
            // 跳转到修改页面
            window.location.href = "#biz/basic/base/role/role_edit?id="+id;
        },
        openConfigMenuPage : function (id) {
            // 跳转到菜单配置页面
            window.location.href = "#biz/basic/base/role/role_config_menu?id="+id;
        },
        // 根据ID删除数据
        deleteById : function (id) {

            $.alicej.util.bootbox.showConfirm("是否删除该记录？", function() {
                $.ajax({
                    type: "POST",
                    url: '/web/basic/base/role/delete',
                    data : {id:id},
                    dataType:'json',
                    success: function(rsp){
                        $.alicej.util.bootbox.alert("删除成功！",function () {
                            page.obj.gridTable.trigger("reloadGrid");
                        });
                    }
                });
            });
        },getStatusDictionary : function (cellValue){
            return $.alicej.cache.getCellValueByDictionary(cellValue,"STATUS");
        }
    },
    // 对象集合
    obj:{}
};

$(function () {

    page.obj.gridTable = $("#gridTable");

    // 初始化jqGrid
    page.obj.gridTable.jqGrid({
        mtype:"POST",
        url: "/web/basic/base/role/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'角色编号', index:'id', sortable:false, hidden: true},
            {name:'roleName',label:'角色名称', index:'roleName', sortable:false},
            {name:'roleDescription',label:'描述', index:'roleDescription', sortable:false},
            {name:'isEnable',label:'状态', index:'isEnable', formatter:page.fn.getStatusDictionary,sortable:false},
            {name:'updateTime',label:'更新时间', index:'updateTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:false},
            {name:'createTime',label:'创建时间', index:'createTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:false},
            {name:'', label:'操作', index:'', width:200, fixed:true, sortable:false, resize:false,
                formatter:function(cellValue,options,rowObject){
                    var context = "";
                    context += '<button title="菜单" class="btn btn-xs btn-primary" onclick="page.fn.openConfigMenuPage(' + rowObject.id + ')"><i class="fa fa-bars bigger-120"></i></button> ';
                    context += '<button title="修改" class="btn btn-xs btn-info" onclick="page.fn.openEditPage(' + rowObject.id + ')"><i class="fa fa-pencil-square-o bigger-120"></i></button> ';
                    context += '<button title="删除" class="btn btn-xs btn-danger" onclick="page.fn.deleteById(' + rowObject.id + ')"><i class="fa fa-trash-o bigger-120"></i></button> ';
                    return context;
                }
            }
        ],
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,50],
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
            url: "/web/basic/base/role/selectPage",
            postData:$.alicej.util.serialize("#searchForm"),
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