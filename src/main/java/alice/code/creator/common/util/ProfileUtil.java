package alice.code.creator.common.util;

import org.apache.commons.logging.Log;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * 系统级工具类
 * @author contact@liuxp.me
 */
public class ProfileUtil {

    /**
     * 打印服务启动日志
     * @param logger 日志句柄
     * @param applicationName 服务名称
     */
    public static void print(Log logger, String applicationName){
        // 内存使用情况
        MemoryMXBean mb = ManagementFactory.getMemoryMXBean();
        logger.info("*********************************************************************");
        logger.info("应用"+applicationName+"堆内存使用情况: " + mb.getHeapMemoryUsage());
        logger.info("应用"+applicationName+"非堆内存使用情况: " + mb.getNonHeapMemoryUsage());

        // 执行参数
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        List<String> vmArgs = rb.getInputArguments();
        String profile = "";
        String prefix = "-Dspring.profiles.active=";
        int len = prefix.length();
        if (vmArgs != null) {
            for (String arg : vmArgs) {
                if (arg.startsWith(prefix)) {
                    profile = arg.substring(len);
                }

                logger.info(arg);

            }
        }

        logger.info("应用"+applicationName+"当前剖面为【"+profile+"】");
        logger.info("*********************************************************************");
    }

}
