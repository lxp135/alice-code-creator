$(function(){
    loadResourceTree();

    $("#expandAllBtn").click(function(){
        $.fn.zTree.getZTreeObj("resourceTree").expandAll(true);
        $("#expandAllBtn").addClass("hide");
        $("#foldAllBtn").removeClass("hide");
    });

    $("#foldAllBtn").click(function(){
        $.fn.zTree.getZTreeObj("resourceTree").expandAll(false);
        $("#expandAllBtn").removeClass("hide");
        $("#foldAllBtn").addClass("hide");
    });

    $("#saveBtn").click(function(){
        save();
    });
});

/**
 * 加载菜单树
 */
function loadResourceTree(){

    var setting = {
        check: {
            enable: true, // 设置 zTree 的节点上是否显示 checkbox / radio
            chkboxType: { "Y": "ps", "N": "ps" } // 勾选 checkbox 对于父子节点的关联关系
        },
        data: {
            key: {
                name: "menuName", // 节点数据保存节点名称的属性名称
                checked: "isCheck" // 节点数据中保存 check 状态的属性名称
            },
            simpleData: {
                enable: true, // 使用简单数据模式
                idKey: "id", // 节点数据中保存唯一标识的属性名称
                pIdKey: "menuParentId" // 节点数据中保存其父节点唯一标识的属性名称
            }
        }
    };

    $.alicej.util.ajax({
        type:"post",
        cache:false,
        dataType:'json',
        url:"/web/basic/base/role/selectMenuRel",
        data:{
            roleId:$.alicej.util.getUrlParam("id")
        },
        success:function (data) {
            $.fn.zTree.init($("#resourceTree"), setting, data);
            $.fn.zTree.getZTreeObj("resourceTree").expandAll(true);
        }
    });
}

/**
 * 保存
 */
function save(){

    var menuIds = [];
    var treeObj = $.fn.zTree.getZTreeObj('resourceTree');
    var nodes = treeObj.getCheckedNodes(true);

    for (var i = 0; i < nodes.length; i++) {
        menuIds.push(nodes[i].id);
    }
    $.alicej.util.ajax({
        type:"POST",
        dataType:'json',
        url:"/web/basic/base/role/updateMenuRel",
        data:{
            roleId:$.alicej.util.getUrlParam("id"),
            menuIds : menuIds
        },
        success:function (data) {
            $.alicej.util.bootbox.alert("保存成功！",function () {
                window.location.href = "#biz/basic/base/role/role_list";
            });
        }
    });
}