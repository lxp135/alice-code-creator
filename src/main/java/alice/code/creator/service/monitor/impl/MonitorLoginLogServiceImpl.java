package alice.code.creator.service.monitor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.monitor.MonitorLoginLogDao;
import alice.code.creator.service.monitor.MonitorLoginLogService;

/**
 * 登录日志Service实现类
 * @author contact@liuxp.me
 */
@Service
public class MonitorLoginLogServiceImpl extends AbstractService implements MonitorLoginLogService {

	@Autowired
	public void setDao(MonitorLoginLogDao dao) {this.iDao = dao;}
}