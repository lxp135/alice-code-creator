package alice.code.creator.service.generator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigMappingDao;
import alice.code.creator.service.generator.GeneratorConfigMappingService;

/**
 * 代码生成器映射关系配置Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2018-12-26
 **/
@Service
public class GeneratorConfigMappingServiceImpl extends AbstractService implements GeneratorConfigMappingService {

    @Autowired
    public void setDao(GeneratorConfigMappingDao dao) {this.iDao = dao;}

}