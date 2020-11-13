$(function () {

    // 取得待修改的数据
    jQuery.alicej.util.ajax({
        type: "POST",
        url: '/web/basic/base/dictionary/country/selectOne',
        data : {id:jQuery.alicej.util.getUrlParam("id")},
        dataType:'json',
        success: function(rsp){
            $("#id").val(rsp.id);
            $("#countryNo").val(rsp.countryNo);
            $("#countryCode").val(rsp.countryCode);
            $("#countryName").val(rsp.countryName);
            $("#countryNameShort").val(rsp.countryNameShort);
            $("#countryNameTable").val(rsp.countryNameTable);
            $("#countryNameLong").val(rsp.countryNameLong);
            $("#countryAlphaCode").val(rsp.countryAlphaCode);
            $("#countryWbCode").val(rsp.countryWbCode);
            $("#phonePrefix").val(rsp.phonePrefix);
            $("#currencyUnit").val(rsp.currencyUnit);
            $("#countryArea").val(rsp.countryArea);
            $("#countryTimeDifference").val(rsp.countryTimeDifference);
            $("#region").val(rsp.region);
            $("#incomeGroup").val(rsp.incomeGroup);
            $("#specialNotes").val(rsp.specialNotes);
            $("#categoryCode").val(rsp.categoryCode);
            $("#categoryName").val(rsp.categoryName);
            $("#remark").val(rsp.remark);

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
            'countryCode': 'required',
        }
    });

    // 保存数据
    $("#btnSave").click(function(){
        // 进行表单输入验证
        jQuery("#editForm").isValid(function(isValid){
            if(isValid){
                jQuery.alicej.util.ajax({
                    type: "POST",
                    url: 'web/basic/base/dictionary/country/update',
                    data : jQuery.alicej.util.serialize("#editForm"),
                    dataType:'json',
                    success: function(rsp){
                        jQuery.alicej.util.bootbox.alert("保存成功！",function () {
                            window.location.href = "#biz/basic/base/dictionary/country/base_dictionary_country_list";
                        });
                    }
                });
            }
        });
    });

});