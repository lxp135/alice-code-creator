(function ($) {

    // 实例化路由
    var router = Router();
    router.configure({
        "notfound": function () {
            // 判断URL中是否存在入参
            var i = this.path.indexOf("?");

            // 截取URL中地址部分
            var url = i == -1 ? this.path : this.path.substr(0, i);

            var mainFrame = $("#main_frame_xqkdiem32zks0"); // 主页面
            mainFrame.find("*").unbind(); // 解除原绑定事件
            $.alicej.util.clearInterval(); // 清除定时任务
            $(window).unbind("resize"); // 解除窗口缩放绑定事件

            // 判断是否已经登录
            $.ajax({
                type: "GET",
                url: '/isLogin',
                dataType:'json',
                success: function(rsp){
                    if(rsp){
                        try {
                            // 渲染页面
                            mainFrame.setTemplateURL(url + ".html").processTemplate({});
                        } catch (e) {
                            // 发生错误
                            mainFrame.setTemplateURL("framework/error.html").processTemplate(e);
                        }
                    }else{
                        window.location.href = "/login";
                    }
                },
                error:function () {
                    window.location.href = "/login?error=3";
                }
            });
        }
    });

    // 初始化
    router.init();

    // 默认跳转首页
    if (window.location.href.indexOf("#") == -1) {
        window.location.hash = "#biz/default";
    }

})(jQuery);