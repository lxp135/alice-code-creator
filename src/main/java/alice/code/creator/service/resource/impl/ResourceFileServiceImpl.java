package alice.code.creator.service.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.resource.ResourceFileDao;
import alice.code.creator.service.resource.ResourceFileService;

/**
 * 文件存储信息表Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2018-07-30
 **/
@Service
public class ResourceFileServiceImpl extends AbstractService implements ResourceFileService {

    @Autowired
    public void setDao(ResourceFileDao dao) {this.iDao = dao;}

}