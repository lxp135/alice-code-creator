var tenantCommon = {
    obj:{

    },
    fn:{
        /**
         * 选择数据源
         * @param callback 回调方法
         */
        chooseTenant:function (callback){
            var html = '<div class="col-lg-12">\n' +
                '                    <div class="row">\n' +
                '                        <div class="col-5 col-lg-5 col-xl-5">\n' +
                '                            <div class="input-group input-group-sm mb-3">\n' +
                '                                <div class="input-group-prepend">\n' +
                '                                    <label class="input-group-text" for="datasourceNameLike">数据源名称</label>\n' +
                '                                </div>\n' +
                '                                <input class="form-control" type="text" placeholder="" id="datasourceNameLike" name="datasourceNameLike">\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="col-5 col-lg-5 col-xl-5">\n' +
                '                            <div class="input-group input-group-sm mb-3">\n' +
                '                                <div class="input-group-prepend">\n' +
                '                                    <label class="input-group-text" for="commonTenantIsDisabled">数据源类型</label>\n' +
                '                                </div>\n' +
                '                                <select class="form-select2 form-control" id="commonTenantIsDisabled" name="commonTenantIsDisabled">\n' +
                '                                    <option value="" selected="selected">请选择</option>\n' +
                '                                    <option value="0">未停用</option>\n' +
                '                                    <option value="1">已停用</option>\n' +
                '                                </select>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="col-2 col-lg-2 col-xl-2">\n' +
                '                            <button class="btn btn-space btn-primary" id="tenantBtnQuery" style="height:36px;width:80px;">查 询</button>'+
                '                        </div>\n' +
                '                    </div>\n' +
                '    </div>\n' +
                '\n' +
                '    <div class="col-lg-12">\n' +
                '        <table id="tenantGridTable"></table>\n' +
                '        <div id="tenantGridPager"></div>\n' +
                '    </div>'

            layer.open({
                title: "关联租户"
                ,content: html
                ,area: ['700px', '550px']
                ,btn: ['确认', '取消']
                ,tipsMore:true // 允许多层弹出
                ,success:function(layero, index){
                    tenantCommon.obj.tenantGridTable = $("#tenantGridTable");

                    // 初始化jqGrid
                    tenantCommon.obj.tenantGridTable.jqGrid({
                        mtype:"POST",
                        loadBeforeSend: function(jqXHR) {
                            jqXHR.setRequestHeader("gateway_token",jQuery.alicej.cache.token);
                        },
                        url: jQuery.alicej.constant.platform.url() + "/web/auth/tenant/page",
                        datatype: "json",
                        autowidth: true,
                        height: "auto",
                        colModel:[
                            {name:'id',label:'id', index:'id', sortable:false, hidden: true},
                            {name:'tenantName',label:'租户名称', index:'tenant_name', sortable:false},
                            {name:'isDisabled',label:'是否停用', index:'is_disabled',formatter:function (cellValue,options,rowObject){
                                    if(cellValue){
                                        return '<span style="color:red;">是</span>'
                                    }else{
                                        return '<span style="color:green;">否</span>'
                                    }

                                }, sortable:false},
                            {name:'lastModifiedTime',label:'更新日期', index:'last_modified_time',formatter:$.alicej.util.getSmpFormatDateTime, sortable:true}
                        ],
                        viewrecords : true,
                        multiselect:true,
                        rowNum:5,
                        rowList:[5,10,30],
                        sortname:"last_modified_time",
                        sortorder:"desc",
                        altRows: true,
                        pager : "#tenantGridPager",
                        jsonReader: { //server返回Json解析设定
                            root: "content.records", //对于json中数据列表
                            page: "content.current",
                            total: "content.pages", // 页数
                            records: "content.total" // 记录总数
                        },
                        beforeSelectRow: function(rowid, e){
                            jQuery("#tenantGridTable").jqGrid('resetSelection');
                            return(true);
                        },
                        loadComplete:function () {
                            tenantCommon.obj.tenantGridTable.setGridWidth(tenantCommon.obj.tenantGridTable.parents(".card-body").width(), false);
                        }
                    });

                    $("#cb_"+tenantCommon.obj.tenantGridTable[0].id).hide();

                    // 重置数据列表宽度
                    $(window).resize(function(){
                        tenantCommon.obj.tenantGridTable.setGridWidth(tenantCommon.obj.tenantGridTable.parents(".card-body").width(), false);
                    });

                    /**
                     * 查询
                     */
                    $("#tenantBtnQuery").click(function(){

                        // 取得查询参数
                        var gridParam = {
                            url: jQuery.alicej.constant.platform.url() + "/web/auth/tenant/page",
                            postData: {
                                tenantName : $("#commonTenantName").val()
                                ,isDisabled : $("#commonTenantIsDisabled").val()
                            },
                            page:1
                        };

                        tenantCommon.obj.tenantGridTable.jqGrid("setGridParam", gridParam).trigger("reloadGrid");
                    });

                }
                ,yes: function(index, layero){

                    var ids = $("#tenantGridTable").jqGrid("getGridParam","selarrrow");
                    if(ids.length === 0){
                        alert("请选择一条记录！");
                    }else{

                        var tenantId;
                        var tenantName;

                        for(var i=0;i<ids.length;i++){
                            var rowData = $("#tenantGridTable").getRowData(ids[i]);
                            tenantId = rowData.id;
                            tenantName = rowData.tenantName;
                        }

                        if(undefined !== callback){
                            callback(tenantId,tenantName,index);
                        }
                    }
                }
                ,btn2: function(index, layero){
                    layer.close(index);
                }
                ,cancel: function(index){
                    layer.close(index);
                }
            });
        }
    }
}