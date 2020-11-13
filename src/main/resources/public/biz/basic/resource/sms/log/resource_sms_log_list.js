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
        url: "/web/basic/resource/sms/log/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'自增主键', index:'id', sortable:false, hidden:true},
            {name:'smsSendPhone',label:'手机号', index:'smsSendPhone', sortable:true},
            {name:'smsTemplateName',label:'短信模板', index:'smsTemplateName', sortable:true},
            {name:'smsGatewayType',label:'短信网关', index:'smsGatewayType', sortable:true},
            {name:'smsSendContent',label:'短信内容', index:'smsSendContent', sortable:true},
            {name:'smsSendStatus',label:'发送状态', index:'smsSendStatus', sortable:true},
            {name:'smsSendTime',label:'短信发送时间', index:'smsSendTime', sortable:true},
            {name:'smsSendResult',label:'接口返回值', index:'smsSendResult', sortable:true},
            {name:'smsSendExceptionText',label:'发送异常信息', index:'smsSendExceptionText', sortable:true}
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
            url: "/web/basic/resource/sms/log/selectPage",
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