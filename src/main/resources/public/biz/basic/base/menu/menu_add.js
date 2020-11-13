var page = {
    // 方法集合
    fn:{
        loadParent : function (menuLevel) {
            if(menuLevel == 0){
                // 取得一级菜单的父级，默认为0
                var options = "<option value=\"0\">默认根节点</option>";
                $("#menuParentId").html(options);
            }else if(menuLevel == 1 || menuLevel == 2){
                // 取得二、三级菜单的父级
                $.alicej.util.ajax({
                    type: "POST",
                    url: "/web/basic/base/menu/selectList",
                    data : {
                        menuType : 0
                        ,menuLevel : menuLevel - 1
                    },
                    dataType:'json',
                    success: function(rsp){
                        var options = "<option value=\"\" selected=\"selected\">请选择</option>";
                        for(var i in rsp){
                            options += "<option value=\""+rsp[i].id+"\">"+rsp[i].menuName+"</option>";
                        }
                        $("#menuParentId").html(options);
                    }
                });
            }
        }
    }
};

$(function () {

    // Select2
    $(".select2").select2({
        width: '100%',
        placeholder: '请选择',
        minimumResultsForSearch: Infinity // 隐藏搜索框
    });

    // 初始化表单验证
    $('#addForm').validator({
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

    // 保存数据
    $("#btnSave").click(function(){
        $("#addForm").isValid(function(isValid) {
            if(isValid){
                $.alicej.util.ajax({
                    type: "POST",
                    url: '/web/basic/base/menu/insert',
                    data : $.alicej.util.serialize("#addForm"),
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

