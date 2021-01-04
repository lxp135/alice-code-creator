// 定义page变量
var page = {
    // 方法集合
    fn:{},
    // 对象集合
    obj:{
        language:{
            xml : 'application/xml',
            java : 'text/x-java',
            html : 'text/html',
            js : 'text/javascript'
        }
        ,groupId:null
    }
};

$(function () {

    page.obj.groupId = jQuery.alicej.util.getUrlParam("groupId");

    page.obj.codeMirror = CodeMirror.fromTextArea(document.getElementById("console"),{
        lineNumbers: true,
        theme: 'monokai',
        mode:  page.obj.language.java,
        tabSize: 2
    });

    setTimeout(function(){
        page.obj.codeMirror.refresh();
    },200);

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
            var formItem = $(input).closest('div.form-group'),
            msgBox = formItem.find('span.msg-box');
            if (!msgBox.length) {
                msgBox = $('<span class="msg-box"></span>').appendTo(formItem);
            }
            return msgBox;
        },
        fields: {
            'templateCode': 'required',
            'templateName': 'required',
            'filePath': 'required',
            'fileName': 'required'
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        $("#addForm").isValid(function(isValid){
        if(isValid){

            // 保存代码
            page.obj.codeMirror.save();
            var params = jQuery.alicej.util.serialize("#addForm");
            params.templateContent = page.obj.codeMirror.getTextArea().value;
            params.groupId = page.obj.groupId; // 分组编号

            jQuery.alicej.util.ajax({
            type: "POST",
                url: '/web/generator/template/insert',
                data : params,
                dataType:'json',
                success: function(rsp){
                    jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                        window.location.href = "#biz/generator/config/group/generator_config_group_edit?id="+page.obj.groupId;
                        });
                    }
                });
            }
        });
    });
});