package alice.code.creator.common.framework.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
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

	private final Log logger = LogFactory.getLog(SpringProfileListener.class);

	/**
	 * 服务名称
	 */
	@Value("${spring.application.name}")
	private String applicationName;

	/**
	 * spring上下文
	 */
	@Resource
	WebApplicationContext applicationContext;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 内存使用情况
		MemoryMXBean mb = ManagementFactory.getMemoryMXBean();
		logger.info("*********************************************************************");
		logger.info("应用堆内存使用情况: " + mb.getHeapMemoryUsage());
		logger.info("应用非堆内存使用情况: " + mb.getNonHeapMemoryUsage());
		logger.info("应用"+applicationName+"当前剖面为【"+applicationContext.getEnvironment().getActiveProfiles()[0]+"】");
		logger.info("*********************************************************************");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("应用停止");
	}

}
