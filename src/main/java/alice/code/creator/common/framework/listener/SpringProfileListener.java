package alice.code.creator.common.framework.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * Spring启动参数监听
 * @author contact@liuxp.me
 */
@Component
public class SpringProfileListener implements ServletContextListener {

	private Log logger = LogFactory.getLog(SpringProfileListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 内存使用情况
		MemoryMXBean mb = ManagementFactory.getMemoryMXBean();
		logger.info("*********************************************************************");
		logger.info("应用堆内存使用情况: " + mb.getHeapMemoryUsage());
		logger.info("应用非堆内存使用情况: " + mb.getNonHeapMemoryUsage());
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
			}
		}
		logger.info("应用当前剖面为【"+profile+"】");
		logger.info("*********************************************************************");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("应用停止");
	}

}
