// 取得登录用户信息
$.ajax({
    type: "POST",
    url: '/web/account',
    dataType:'json',
    success: function(data){
        // 用户名称
        $("[name=loginUserName]").html(data.userName);

        // 用户头像
        $("#loginUserFace").attr("src","/assets/img/avatar.png");

        // 缓存登录用户信息
        $.alicej.cache.user = data;
    }
});

// 取得登录用户菜单
$.ajax({
    type: "POST",
    url: '/web/menu',
    dataType:'json',
    success: function(data){

        // 取得当前菜单地址
        var menuActive = $.alicej.cookie.get("menuHistory");

        // 渲染左侧菜单
        var menuList = '<ul class="sidebar-elements"><li class="divider">MENU</li>';
        if(0 == menuActive || null == menuActive){
            menuList += '<li class="active">';
        }else{
            menuList += '<li>';
        }
        menuList += '    <a id="0" href="#biz/default">';
        menuList += '        <i class="icon mdi mdi-home"></i>';
        menuList += '        <span> 首页 </span>';
        menuList += '    </a>';
        menuList += '</li>';
        $.each(data, function(i, menu) {
            var firstMenu = menu.childMenus;
            if(firstMenu == null || firstMenu.length == 0){ // 判断是菜单还是路径
                if(menu.id == menuActive){
                    menuList += '<li class="active">';
                }else{
                    menuList += '<li>';
                }
                menuList += '    <a id="'+menu.id+'" href="'+menu.menuUrl+'">';
                menuList += '        <i class="'+menu.menuIcon+'"></i>';
                menuList += '        <span> '+menu.menuName+' </span>';
                menuList += '    </a>';
                menuList += '</li>';
            }else{
                menuList += '<li class="parent">';
                menuList += '    <a id="'+menu.id+'" href="#">';
                menuList += '        <i class="'+menu.menuIcon+'"></i>';
                menuList += '        <span>'+menu.menuName+'</span>';
                menuList += '    </a>';
                menuList += '<ul class="sub-menu">';
                $.each(firstMenu, function(i, menu) {
                    var secondMenu = menu.childMenus;
                    if(secondMenu == null || secondMenu.length == 0){ // 判断是菜单还是路径
                        if(menu.id == menuActive){
                            menuList += '<li class="active">';
                        }else{
                            menuList += '<li>';
                        }
                        menuList += '    <a id="'+menu.id+'" href="'+menu.menuUrl+'">';
                        menuList += '        <i class="'+menu.menuIcon+'"></i>';
                        menuList += '        <span> '+menu.menuName+' </span>';
                        menuList += '    </a>';
                        menuList += '</li>';
                    }else{
                        menuList += '<li class="parent">';
                        menuList += '    <a id="'+menu.id+'"href="#">';
                        menuList += '        <i class="'+menu.menuIcon+'"></i>';
                        menuList += '        <span>'+menu.menuName+'</span>';
                        menuList += '    </a>';
                        menuList += '<ul class="sub-menu">';
                        $.each(secondMenu, function(i, menu) {
                            if(menu.id == menuActive){
                                menuList += '<li class="active">';
                            }else{
                                menuList += '<li>';
                            }
                            menuList += '    <a id="'+menu.id+'" href="'+menu.menuUrl+'">';
                            menuList += '        <i class="'+menu.menuIcon+'"></i>';
                            menuList += '        <span> '+menu.menuName+' </span>';
                            menuList += '    </a>';
                            menuList += '</li>';
                        });
                        menuList += '</ul>';
                        menuList += '</li>';
                    }
                });
                menuList += '</ul>';
                menuList += '</li>';
            }
        });
        menuList += '</ul>';
        var $menus = $("#menus");
        $menus.html(menuList);

        // 绑定菜单点击事件
        $menus.find("a").click(function () {
            // console.log($(this).attr("id"));
            $.alicej.cookie.set("menuHistory",$(this).attr("id"));
            App.activeItemLeftSidebar(this);
        });

        App.init();

        // $.jgrid.defaults.width = 780;
        // $.jgrid.defaults.responsive = true;
        $.jgrid.defaults.styleUI = 'Bootstrap4';
        $.jgrid.defaults.iconSet = "fontAwesome";

    }
});