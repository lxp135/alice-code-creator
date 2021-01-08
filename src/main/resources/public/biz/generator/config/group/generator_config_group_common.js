var groupCommon = {
    obj:{

    },
    fn:{
        /**
         * 查询模板分组
         * @param callback 回调方法
         */
        choose:function (callback){
            var html = '<div class="col-lg-12">' +
                '            <div class="row">' +
                '                <div class="col-5 col-lg-5 col-xl-5">' +
                '                    <div class="input-group input-group-sm mb-3">' +
                '                        <div class="input-group-prepend">' +
                '                            <label class="input-group-text" for="groupNameLike">分组名称</label>' +
                '                        </div>' +
                '                        <input class="form-control" type="text" placeholder="" id="groupNameLike" name="groupNameLike">' +
                '                    </div>' +
                '                </div>' +
                '                <div class="col-2 col-lg-2 col-xl-2">' +
                '                    <button class="btn btn-space btn-primary" id="groupBtnQuery" style="height:36px;width:80px;">查 询</button>'+
                '                </div>' +
                '            </div>' +
                '    </div>' +
                '    <div class="col-lg-12">' +
                '        <table id="groupGridTable"></table>' +
                '        <div id="groupGridPager"></div>' +
                '    </div>';

            layer.open({
                title: "查询模板分组"
                ,content: html
                ,area: ['700px', '550px']
                ,btn: ['确认', '取消']
                ,tipsMore:true // 允许多层弹出
                ,success:function(layero, index){
                    groupCommon.obj.groupGridTable = $("#groupGridTable");

                    // 初始化jqGrid
                    groupCommon.obj.groupGridTable.jqGrid({
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
                            {name:'defaultTablePrefix',label:'表前缀', index:'defaultTablePrefix', sortable:true, hidden:true},
                            {name:'defaultFieldUnique',label:'唯一标识字段', index:'defaultFieldUnique', sortable:true, hidden:true},
                            {name:'defaultFieldExt',label:'扩展标识字段（可以存多个，按照逗号分割）', index:'defaultFieldExt', sortable:true, hidden:true},
                            {name:'defaultFieldEffective',label:'逻辑删除标识字段名称', index:'defaultFieldEffective', sortable:true, hidden:true}

                        ],
                        viewrecords : true,
                        multiselect:true,
                        rowNum:5,
                        rowList:[5,10,30],
                        sortname:"updateTime",
                        sortorder:"desc",
                        altRows: true,
                        pager : "#groupGridPager",
                        jsonReader: { //server返回Json解析设定
                            root: "content", //对于json中数据列表
                            page: "pageNum",
                            total: "total",
                            records: "records"
                        },
                        beforeSelectRow: function(rowid, e){
                            jQuery("#groupGridTable").jqGrid('resetSelection');
                            return(true);
                        },
                        loadComplete:function () {
                            groupCommon.obj.groupGridTable.setGridWidth(groupCommon.obj.groupGridTable.parents(".card-body").width(), false);
                        }
                    });

                    $("#cb_"+groupCommon.obj.groupGridTable[0].id).hide();

                    // 重置数据列表宽度
                    $(window).resize(function(){
                        groupCommon.obj.groupGridTable.setGridWidth(groupCommon.obj.groupGridTable.parents(".card-body").width(), false);
                    });

                    /**
                     * 查询
                     */
                    $("#groupBtnQuery").click(function(){

                        // 取得查询参数
                        var gridParam = {
                            url: "/web/generator/config/group/selectPage",
                            postData: {
                                groupNameLike : $("#groupNameLike").val()
                            },
                            page:1
                        };

                        groupCommon.obj.groupGridTable.jqGrid("setGridParam", gridParam).trigger("reloadGrid");
                    });

                }
                ,yes: function(index, layero){

                    var ids = $("#groupGridTable").jqGrid("getGridParam","selarrrow");
                    if(ids.length === 0){
                        alert("请选择一条记录！");
                    }else{

                        var rowData;

                        for(var i=0;i<ids.length;i++){
                            rowData = $("#groupGridTable").getRowData(ids[i]);
                        }

                        if(undefined !== callback){
                            callback(rowData,index);
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