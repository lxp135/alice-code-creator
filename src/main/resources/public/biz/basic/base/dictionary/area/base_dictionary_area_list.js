// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/basic/base/dictionary/area/base_dictionary_area_edit?id="+id;
        },
        // 根据ID删除数据
        deleteById : function (id) {
            jQuery.alicej.util.bootbox.showConfirm("是否删除该记录？",function(){
                jQuery.ajax({
                    type: "POST",
                    url: '/web/basic/base/dictionary/area/delete',
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

    // 初始化jqGrid
    page.obj.gridTable = $("#gridTable");

    page.obj.gridTable.jqGrid({
        mtype:"POST",
        url: "/web/basic/base/dictionary/area/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'主键', index:'id', sortable:false},
            {name:'areaParentId',label:'父级主键', index:'areaParentId', sortable:true},
            {name:'areaCode',label:'地区编号', index:'areaCode', sortable:true},
            {name:'areaName',label:'地区名称', index:'areaName', sortable:true},
            {name:'areaLevel',label:'地区级别', index:'areaLevel', sortable:true,formatter:function (cellValue,options,rowObject) {
                    if(cellValue==1){
                        return '省';
                    }else if(cellValue==2){
                        return '地市';
                    }else if(cellValue==3){
                        return '区县';
                    }else{
                        return '其他';
                    }
                }},
            {name:'areaLatitude',label:'经度', index:'areaLatitude', sortable:true},
            {name:'areaLongitude',label:'维度', index:'areaLongitude', sortable:true},
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
        sortname:"areaCode",
        sortorder:"asc",
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
            url: "/web/basic/base/dictionary/area/selectPage",
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