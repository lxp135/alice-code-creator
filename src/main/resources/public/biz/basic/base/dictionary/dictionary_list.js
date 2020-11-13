// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/basic/base/dictionary/dictionary_edit?id="+id;
        },
        // 根据ID删除数据
        deleteById : function (id) {
            jQuery.alicej.util.bootbox.showConfirm("是否删除该记录？", function() {
                jQuery.ajax({
                    type: "POST",
                    url: '/web/basic/base/dictionary/delete',
                    data : {id:id},
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("删除成功！",function () {
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
        url: "/web/basic/base/dictionary/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'主键', index:'id', sortable:false, hidden: true},
            {name:'groupCode',label:'组编码', index:'groupCode', sortable:false},
            {name:'groupName',label:'组名称', index:'groupName', sortable:false},
            {name:'dictCode',label:'KEY', index:'dictCode', sortable:false},
            {name:'dictName',label:'NAME', index:'dictName', sortable:false},
            {name:'isEnable',label:'是否启用', index:'isEnable', sortable:false,formatter:page.fn.getStatusDictionary},
            {name:'updateTime',label:'更新时间', index:'updateTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:false},
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
            url: "/web/basic/base/dictionary/selectPage",
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