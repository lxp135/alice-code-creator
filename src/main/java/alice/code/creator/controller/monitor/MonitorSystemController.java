package alice.code.creator.controller.monitor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.system.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 监控系统基本信息
 * @author contact@liuxp.me
 */
@Controller
@RequestMapping("/web/monitor/system")
@Secured("MONITOR_SYSTEM")
public class MonitorSystemController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 返回Java虚拟机参数
     * @return Java虚拟机参数
     */
    @RequestMapping(value = "/getJvmInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getJvmInfo(){

        JvmInfo jvmInfo = SystemUtil.getJvmInfo();

        Map<String, String> content = new HashMap<>();
        content.put("JavaVM Name",jvmInfo.getName());
        content.put("JavaVM Version",jvmInfo.getVersion());
        content.put("JavaVM Vendor",jvmInfo.getVendor());
        content.put("JavaVM Info",jvmInfo.getInfo());

        return new Result<>(content);
    }

    /**
     * 返回Java参数
     * @return Java参数
     */
    @RequestMapping(value = "/getJavaInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getJavaInfo(){

        JavaInfo javaInfo = SystemUtil.getJavaInfo();

        Map<String, String> content = new HashMap<>();
        content.put("Java Version",javaInfo.getVersion());
        content.put("Java Vendor",javaInfo.getVendor());
        content.put("Java Vendor URL",javaInfo.getVendorURL());

        return new Result<>(content);
    }

    /**
     * 返回Java运行时参数
     * @return Java运行时参数
     */
    @RequestMapping(value = "/getJavaRuntimeInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getJavaRuntimeInfo(){

        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();

        Map<String, String> content = new HashMap<>();
        content.put("Java Runtime Name",javaRuntimeInfo.getName());
        content.put("Java Runtime Version",javaRuntimeInfo.getVersion());
        content.put("Java Home Dir",javaRuntimeInfo.getHomeDir());
        content.put("Java Extension Dirs",javaRuntimeInfo.getExtDirs());
        content.put("Java Endorsed Dirs",javaRuntimeInfo.getEndorsedDirs());
        content.put("Java Class Path",javaRuntimeInfo.getClassPath());
        content.put("Java Class Version",javaRuntimeInfo.getClassVersion());
        content.put("Java Library Path",javaRuntimeInfo.getLibraryPath());
        content.put("Java Protocol Packages",javaRuntimeInfo.getProtocolPackages());

        return new Result<>(content);
    }

    /**
     * 返回系统信息
     * @return 系统信息
     */
    @RequestMapping(value = "/getOsInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getOsInfo(){

        OsInfo osInfo = SystemUtil.getOsInfo();

        Map<String, String> content = new HashMap<>();
        content.put("OS Arch",osInfo.getArch());
        content.put("OS Name",osInfo.getName());
        content.put("OS Version",osInfo.getVersion());
        content.put("File Separator",osInfo.getFileSeparator());
        content.put("Line Separator",osInfo.getLineSeparator());
        content.put("Path Separator",osInfo.getPathSeparator());

        return new Result<>(content);
    }

    /**
     * 返回系统用户信息
     * @return 系统用户信息
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getUserInfo(){

        UserInfo userInfo = SystemUtil.getUserInfo();

        Map<String, String> content = new HashMap<>();
        content.put("User Name",userInfo.getName());
        content.put("User Home Dir",userInfo.getHomeDir());
        content.put("User Current Dir",userInfo.getCurrentDir());
        content.put("User Temp Dir",userInfo.getTempDir());
        content.put("User Language",userInfo.getLanguage());
        content.put("User Country",userInfo.getCountry());

        return new Result<>(content);
    }

    /**
     * 返回系统网络信息
     * @return 系统网络信息
     */
    @RequestMapping(value = "/getHostInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getHostInfo(){

        HostInfo hostInfo = SystemUtil.getHostInfo();

        Map<String, String> content = new HashMap<>();
        content.put("Host Name",hostInfo.getName());
        content.put("Host Address",hostInfo.getAddress());

        return new Result<>(content);
    }

    /**
     * 返回运行时信息
     * @return 运行时信息
     */
    @RequestMapping(value = "/getRuntimeInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, String>> getRuntimeInfo(){
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();

        Map<String, String> content = new HashMap<>();
        content.put("Max Memory", FileUtil.readableFileSize(runtimeInfo.getMaxMemory()));
        content.put("Total Memory", FileUtil.readableFileSize(runtimeInfo.getTotalMemory()));
        content.put("Free Memory", FileUtil.readableFileSize(runtimeInfo.getFreeMemory()));
        content.put("Usable Memory", FileUtil.readableFileSize(runtimeInfo.getUsableMemory()));

        return new Result<>(content);
    }

}