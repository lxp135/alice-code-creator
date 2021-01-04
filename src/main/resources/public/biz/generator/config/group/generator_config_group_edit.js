// 定义page变量
var page = {
    // 方法集合
    fn:{
        // 跳转到修改页面
        openEditPage : function (id) {
            window.location.href = "#biz/generator/config/group/template/group_template_edit?id="+id+"&groupId="+page.obj.groupId;
        },
        // 根据ID删除数据
        deleteById : function (id) {
            jQuery.alicej.util.bootbox.showConfirm("是否删除该记录？",function(){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: '/web/generator/template/delete',
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
    obj:{
        groupId:null
    }
};

$(function () {

    // 取得分组编号
    page.obj.groupId = jQuery.alicej.util.getUrlParam("id");

    $("#addButton").click(function (){
        // 跳转到新增模板页面
        window.location.href = "#biz/generator/config/group/template/group_template_add?groupId="+page.obj.groupId;
    })

    $("#btnBack").click(function (){
        // 跳转到模板分组列表页面
        window.location.href = "#biz/generator/config/group/generator_config_group_list";
    })

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/generator/config/group/selectOne',
        data : {id:page.obj.groupId},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#groupName").val(rsp.groupName);
            $("#groupDesc").val(rsp.groupDesc);
            $("#defaultSign").val(rsp.defaultSign);
            $("#defaultPackage").val(rsp.defaultPackage);
            $("#defaultTablePrefix").val(rsp.defaultTablePrefix);
            $("#defaultFieldUnique").val(rsp.defaultFieldUnique);
            $("#defaultFieldExt").val(rsp.defaultFieldExt);
            $("#defaultFieldEffective").val(rsp.defaultFieldEffective);
            $("#isPublic").val(rsp.isPublic);

            // Select2
            $(".select2").select2({
                width: '100%',
                placeholder: '请选择',
                minimumResultsForSearch: Infinity // 隐藏搜索框
            });
        }
    });

    // 初始化表单验证
    $('#editForm').validator({
        timely: 2,
        theme:'yellow_right',
        target: function(input) {
            var formItem = $(input).closest('div.form-group'),
                msgBox = formItem.find('span.msg-box');
            if (!msgBox.length) {
                msgBox = $('<span class="msg-box"></span>').appendTo(formItem);
            }
            return msgBox;
        },
        fields: {
            'groupName': 'required',
            'groupDesc': 'required',
            'ownerUserId': 'required',
            'ownerUserName': 'required',
            'isPublic': 'required',
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/generator/config/group/update',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/generator/config/group/generator_config_group_list";
                        });
                    }
                });
            }
        });
    });

    // 初始化jqGrid
    page.obj.gridTable = $("#gridTable");

    page.obj.gridTable.jqGrid({
        mtype:"POST",
        url: "/web/generator/template/selectPage",
        postData:{groupId:page.obj.groupId},
        datatype: "json",
        autowidth: true,
        height: "auto",
        colModel:[
            {name:'id',label:'自增主键', index:'id', sortable:false, hidden:true},
            {name:'templateCode',label:'模板英文名', index:'templateCode', sortable:true},
            {name:'templateName',label:'模板中文名', index:'templateName', sortable:true},
            {name:'filePath',label:'生成地址相对路径', index:'filePath', sortable:true},
            {name:'fileName',label:'文件后缀与扩展名', index:'fileName', sortable:true},
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


});