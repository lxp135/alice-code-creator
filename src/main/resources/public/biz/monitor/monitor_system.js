$(function () {

    // 取得Java虚拟机参数
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getJvmInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>JavaVM Name</td>" +
                "    <td>"+data.content["JavaVM Name"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>JavaVM Version</td>" +
                "    <td>"+data.content["JavaVM Version"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>JavaVM Vendor</td>" +
                "    <td>"+data.content["JavaVM Vendor"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>JavaVM Info</td>" +
                "    <td>"+data.content["JavaVM Info"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#jvmTable").html(html);

        }
    });

    // 取得Java参数
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getJavaInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>Java Version</td>" +
                "    <td>"+data.content["Java Version"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Java Vendor</td>" +
                "    <td>"+data.content["Java Vendor"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Java Vendor URL</td>" +
                "    <td>"+data.content["Java Vendor URL"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#javaTable").html(html);

        }
    });

    // 取得Java运行时参数
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getJavaRuntimeInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>Java Runtime Name</td>" +
                "    <td>"+data.content["Java Runtime Name"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Java Runtime Version</td>" +
                "    <td>"+data.content["Java Runtime Version"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Java Home Dir</td>" +
                "    <td>"+data.content["Java Home Dir"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Java Extension Dirs</td>" +
                "    <td>"+data.content["Java Extension Dirs"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Java Endorsed Dirs</td>" +
                "    <td>"+data.content["Java Endorsed Dirs"]+"</td>" +
                "</tr>"+
                // "<tr>" +
                // "    <td>Java Class Path</td>" +
                // "    <td>"+data.content["Java Class Path"]+"</td>" +
                // "</tr>"+
                "<tr>" +
                "    <td>Java Class Version</td>" +
                "    <td>"+data.content["Java Class Version"]+"</td>" +
                "</tr>"+
                // "<tr>" +
                // "    <td>Java Library Path</td>" +
                // "    <td>"+data.content["Java Library Path"]+"</td>" +
                // "</tr>"+
                "<tr>" +
                "    <td>Java Protocol Packages</td>" +
                "    <td>"+data.content["Java Protocol Packages"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#javaRuntimeTable").html(html);

        }
    });

    // 取得系统信息
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getOsInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>OS Arch</td>" +
                "    <td>"+data.content["OS Arch"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>OS Name</td>" +
                "    <td>"+data.content["OS Name"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>OS Version</td>" +
                "    <td>"+data.content["OS Version"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>File Separator</td>" +
                "    <td>"+data.content["File Separator"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Line Separator</td>" +
                "    <td>"+data.content["Line Separator"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Path Separator</td>" +
                "    <td>"+data.content["Path Separator"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#osTable").html(html);

        }
    });

    // 取得系统用户信息
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getUserInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>User Name</td>" +
                "    <td>"+data.content["User Name"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>User Home Dir</td>" +
                "    <td>"+data.content["User Home Dir"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>User Current Dir</td>" +
                "    <td>"+data.content["User Current Dir"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>User Temp Dir</td>" +
                "    <td>"+data.content["User Temp Dir"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>User Language</td>" +
                "    <td>"+data.content["User Language"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>User Country</td>" +
                "    <td>"+data.content["User Country"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#userTable").html(html);

        }
    });

    // 取得系统网络信息
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getHostInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>Host Name</td>" +
                "    <td>"+data.content["Host Name"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Host Address</td>" +
                "    <td>"+data.content["Host Address"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#hostTable").html(html);

        }
    });

    // 取得运行时信息
    $.ajax({
        type: "GET",
        url: '/web/monitor/system/getRuntimeInfo',
        dataType:'json',
        success: function(data){
            var html = "<thead>" +
                "<tr>" +
                "<th>项目名称</th>" +
                "<th>项目值</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>" +
                "<tr>" +
                "    <td>Max Memory</td>" +
                "    <td>"+data.content["Max Memory"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Total Memory</td>" +
                "    <td>"+data.content["Total Memory"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Free Memory</td>" +
                "    <td>"+data.content["Free Memory"]+"</td>" +
                "</tr>"+
                "<tr>" +
                "    <td>Usable Memory</td>" +
                "    <td>"+data.content["Usable Memory"]+"</td>" +
                "</tr>"+
                "</tbody>";

            $("#runtimeTable").html(html);
        }
    });
});