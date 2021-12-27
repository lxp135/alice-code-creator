// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/basic/base/user/user_edit?id="+id;
        },
        // 分配角色
        showRoleRelBox : function (id) {
            $.alicej.util.ajax({
                type: "POST",
                url: '/web/basic/base/role/selectUserRel',
                data : {userId : id},
                dataType:'json',
                success: function(rsp){

                    var html = '<form id="relEditForm" class="form-horizontal" method="post" >';
                    html += '<input id="userId" name="userId" style="display:none;" value="'+ id +'"/>';

                    if(rsp != null){

                        for(var i in rsp){
                            html += '<div class="checkbox">';
                            html += '<label style="width: 100px;">';
                            if(rsp[i].isCheck){
                                html += '<input name="roleIds" type="checkbox" class="ace" value="'+rsp[i].id+'" checked/>';
                            }else{
                                html += '<input name="roleIds" type="checkbox" class="ace" value="'+rsp[i].id+'"/>';
                            }

                            html += '<span class="lbl">'+ rsp[i].roleName +'</span>';
                            html += '</label>';
                            if(rsp[i].roleDescription){
                                html += '<label>'+rsp[i].roleDescription+'</label>';
                            }
                            html += '</div>';
                        }

                    }
                    html += '</form>';

                    layer.open({
                        title: "分配角色",
                        content: html

                        ,btn: ['确认', '取消']
                        ,yes: function(index, layero){
                            var checked = [];
                            $("input:checkbox[name='roleIds']:checked").each(function() {
                                checked.push(parseInt($(this).val()));
                            });
                            $.alicej.util.ajax({
                                type: "POST",
                                url: '/web/basic/base/role/updateUserRel',
                                data : {
                                    userId:$("#relEditForm").find("#userId").val(),
                                    roleIds:checked
                                },
                                dataType:'json',
                                success: function(rsp){
                                    layer.close(index);
                                    $.alicej.util.bootbox.alert("保存成功！");
                                }
                            });
                        }
                        ,btn2: function(index, layero){
                            layer.close(index);
                            }
                        ,cancel: function(){
                            layer.close(index);
                        }
                    });
                }
            });
        },
        // 根据ID删除数据
        deleteById : function (id) {

            $.alicej.util.bootbox.showConfirm("是否删除该记录？", function() {
                $.ajax({
                    type: "POST",
                    url: '/web/basic/base/user/delete',
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
        },getSexDictionary : function (cellValue){
            return $.alicej.cache.getCellValueByDictionary(cellValue,"USER_SEX");
        }
    },
    // 对象集合
    obj:{}
};

$(function(){

    // 初始化性别下拉框
    $.alicej.cache.renderSimpleDropdowns({
        selectId : "userSex",
        groupCode :"USER_SEX"
    });

    // 初始化账户状态下拉框
    $.alicej.cache.renderSimpleDropdowns({
        selectId : "isEnable",
        groupCode :"STATUS"
    });

    page.obj.gridTable = $("#gridTable");

    // 初始化jqGrid
    page.obj.gridTable.jqGrid({
        mtype:"POST",
        url: "/web/basic/base/user/selectPage",
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'id', index:'id', sortable:false, hidden: true},
            {name:'userName',label:'用户名', index:'userName', sortable:false},
            {name:'userAccount',label:'账号', index:'userAccount', sortable:false},
            {name:'userSex',label:'性别', index:'userSex',formatter:page.fn.getSexDictionary, sortable:false},
            {name:'isEnable',label:'账户状态', index:'isEnable',formatter:page.fn.getStatusDictionary, sortable:false},
            {name:'userPhone',label:'手机号', index:'userPhone', sortable:false},
            {name:'userEmail',label:'电子邮件', index:'userEmail', sortable:false},
            {name:'createTime',label:'创建日期', index:'createTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:true},
            {name:'updateTime',label:'更新日期', index:'updateTime',formatter:$.alicej.util.getSmpFormatDateTime, sortable:true},
            {name:'', label:'操作', index:'', width:150, fixed:true, sortable:false, resize:false,
                formatter:function(cellValue,options,rowObject){
                    var context = "";

                    context += '<button title="分配角色" class="btn btn-xs btn-success" onclick="page.fn.showRoleRelBox(' + rowObject.id + ')"><i class="fa fa-user-circle"></i></button> ';
                    context += '<button title="修改" class="btn btn-xs btn-info" onclick="page.fn.openEditPage(' + rowObject.id + ')"><i class="fa fa-pencil blue"></i></button> ';
                    context += '<button title="删除" class="btn btn-xs btn-danger" onclick="page.fn.deleteById(' + rowObject.id + ')"><i class="fa fa-trash-o"></i></button> ';
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
            url: "/web/basic/base/user/selectPage",
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