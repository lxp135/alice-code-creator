var page = {
    // 方法集合
    fn:{
        loadParent : function (menuLevel,id) {
            if(menuLevel == 0){
                // 取得一级菜单的父级，默认为0
                var options = "<option value=\"0\">默认根节点</option>";
                $("#menuParentId").html(options);
            }else{
                // 取得二、三、四、五级菜单的父级
                $.alicej.util.ajax({
                    type: "POST",
                    url: "/web/basic/base/menu/selectList",
                    data : {
                        menuLevel : menuLevel - 1
                    },
                    dataType:'json',
                    success: function(rsp){
                        var options = "<option value=\"\" selected=\"selected\">请选择</option>";
                        if(rsp){
                            for(var i=0;i<rsp.length;i++){
                                if(undefined!==id && id == rsp[i].id){
                                    options += "<option value=\""+rsp[i].id+"\" selected='selected' >"+rsp[i].menuName+"</option>";
                                }else{
                                    options += "<option value=\""+rsp[i].id+"\">"+rsp[i].menuName+"</option>";
                                }
                            }
                        }
                        $("#menuParentId").html(options);
                    }
                });
            }
        }
    }
};

$(function () {

    // 取得待修改的数据
    $.alicej.util.ajax({
        type: "POST",
        url: '/web/basic/base/menu/selectOne',
        data : {id : $.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#menuKey").val(rsp.menuKey);
            $("#menuName").val(rsp.menuName);
            $("#menuDesc").val(rsp.menuDesc);
            $("#menuUrl").val(rsp.menuUrl);
            $("#menuIcon").val(rsp.menuIcon);
            $("#menuLevel").val(rsp.menuLevel);
            $("#menuType").val(rsp.menuType);
            $("#remark").val(rsp.remark);

            // 初始化表单验证
            $('#editForm').validator({
                timely: 2,
                theme:'yellow_right',
                target: function(input) {
                    var $formitem = $(input).closest('div.form-group'),
                        $msgbox = $formitem.find('span.msg-box');
                    if (!$msgbox.length) {
                        $msgbox = $('<span class="msg-box"></span>').appendTo($formitem);
                    }
                    return $msgbox;
                },
                fields: {
                    'menuKey': 'required',
                    'menuName': 'required',
                    'parentId': 'required',
                    'menuLevel': 'required',
                    'menuType': 'required'
                }
            });

            // Select2
            $(".select2").select2({
                width: '100%',
                placeholder: '请选择',
                minimumResultsForSearch: Infinity // 隐藏搜索框
            });

            page.fn.loadParent(rsp.menuLevel,rsp.menuParentId);
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        $("#editForm").isValid(function(isValid) {
            if(isValid){
                $.ajax({
                    type: "POST",
                    url: '/web/basic/base/menu/update',
                    data : $.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        $.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/basic/base/menu/menu_list";
                        });
                    }
                });
            }
        });
    });

    $("#menuLevel").change(function () {
        page.fn.loadParent($(this).val());
    });
});