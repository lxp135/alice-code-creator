// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/basic/base/menu/menu_edit?id="+id;
        },
        // 根据ID删除数据
        deleteById : function (id) {

            $.alicej.util.bootbox.showConfirm("是否删除该记录？", function() {
                $.ajax({
                    type: "POST",
                    url: '/web/basic/base/menu/delete',
                    data : {id:id},
                    dataType:'json',
                    success: function(rsp){
                        $.alicej.util.bootbox.alert("删除成功！",function () {
                            page.obj.gridTable.trigger("reloadGrid");
                        });
                    }
                });
            });
        },getMenuTypeDictionary : function (cellValue){
            // 取得菜单类别字典
            return $.alicej.cache.getCellValueByDictionary(cellValue,"MENU_TYPE");
        },getMenuLevelDictionary : function (cellValue){
            // 取得菜单级别字典
            return $.alicej.cache.getCellValueByDictionary(cellValue,"MENU_LEVEL");
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
        url: "/web/basic/base/menu/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'菜单编号', index:'id', sortable:false, hidden: true},
            {name:'menuKey',label:'英文名称', index:'menuKey', sortable:false},
            {name:'menuName',label:'中文名称', index:'menuName', sortable:false},
            {name:'menuIcon',label:'图标', index:'menuIcon', sortable:false},
            {name:'menuUrl',label:'跳转地址', index:'menuUrl', sortable:false},
            {name:'menuParentId',label:'父级菜单编号', index:'menuParentId', sortable:false},
            {name:'menuLevel',label:'级别', index:'menuLevel', formatter:page.fn.getMenuLevelDictionary,sortable:false},
            {name:'menuType',label:'类别', index:'menuType', formatter:page.fn.getMenuTypeDictionary,sortable:false},
            {name:'updateTime',label:'更新时间', index:'updateTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:false},
            {name:'', label:'操作', index:'',  fixed:true, sortable:false,
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
            url: "/web/basic/base/menu/selectPage",
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