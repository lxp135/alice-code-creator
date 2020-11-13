// 定义page变量
var page = {
    // 方法集合
    fn:{},
    // 对象集合
    obj:{}
};

jQuery(function(){

    // 初始化jqGrid
    page.obj.gridTable = $("#gridTable");

    page.obj.gridTable.jqGrid({
        mtype:"POST",
        url: "/web/basic/resource/file/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'主键', index:'id', sortable:false, hidden:true},
            {name:'fileKey',label:'文件KEY', index:'fileKey', sortable:true},
            {name:'fileSourceTypeName',label:'来源类型', index:'fileSourceTypeName', sortable:true,width:50},
            {name:'fileOriginName',label:'原文件名', index:'fileOriginName', sortable:true},
            {name:'fileSuffix',label:'文件后缀', index:'fileSuffix', sortable:true,width:50},
            {name:'fileSize',label:'文件大小', index:'fileSize', sortable:true,width:50},
            {name:'fileLocation',label:'文件位置（bucket）', index:'fileLocation', sortable:true},
            {name:'fileMd5',label:'文件md5', index:'fileMd5', sortable:true},
            {name:'updateTime',label:'更新时间', index:'updateTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:false}
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
            url: "/web/basic/resource/file/selectPage",
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