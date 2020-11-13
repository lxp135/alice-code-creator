package alice.code.creator.service.monitor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.monitor.ResourceSmsLogDao;
import alice.code.creator.service.monitor.ResourceSmsLogService;

/**
 * 短信发送日志表Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2018-12-12
 **/
@Service
public class ResourceSmsLogServiceImpl extends AbstractService implements ResourceSmsLogService {

    @Autowired
    public void setDao(ResourceSmsLogDao dao) {this.iDao = dao;}

}