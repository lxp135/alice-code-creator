// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/generator/config/group/generator_config_group_edit?id="+id;
        },
        // 根据ID删除数据
        deleteById : function (id) {
            jQuery.alicej.util.bootbox.showConfirm("是否删除该模板分组及其全部模板？",function(){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: '/web/generator/config/group/delete',
                    data : {id:id},
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("删除成功！",function (){
                            page.obj.gridTable.trigger("reloadGrid");
                        });
                    }
                });
            });
        },
        // 复制分组
        copy : function (id){
            jQuery.alicej.util.ajax({
                type: "POST",
                url: '/web/generator/config/group/copy',
                data : {id:id},
                dataType:'json',
                success: function(rsp){
                    jQuery.alicej.util.bootbox.alert("复制成功！",function (){
                        page.obj.gridTable.trigger("reloadGrid");
                    });
                }
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
        url: "/web/generator/config/group/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'自增主键', index:'id', sortable:false, hidden:true},
            {name:'groupName',label:'分组名称', index:'groupName', sortable:true},
            {name:'defaultSign',label:'签名', index:'defaultSign', sortable:true},
            {name:'defaultPackage',label:'包路径', index:'defaultPackage', sortable:true},
            {name:'templateAmount',label:'模板数量', index:'templateAmount', sortable:true},
            {name:'ownerUserName',label:'所有者', index:'ownerUserName', sortable:true},
            {name:'isPublic',label:'是否公开', index:'isPublic', sortable:true,formatter:function (cellValue,options,rowObject){
                return cellValue == "0" ? '私有' : '公开';
                }},
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
            url: "/web/generator/config/group/selectPage",
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

    /**
     * 拷贝一条记录
     */
    $("#copy").click(function (){
        let id = page.obj.gridTable.jqGrid("getGridParam","selrow");
        if(id == null){
            jQuery.alicej.util.bootbox.showWarn("请选择一条记录！");
        }else{
            page.fn.copy(id);
        }
    })

});