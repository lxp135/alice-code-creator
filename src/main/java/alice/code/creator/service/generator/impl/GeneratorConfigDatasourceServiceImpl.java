package alice.code.creator.service.generator.impl;

import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigDatasourceDao;
import alice.code.creator.service.generator.GeneratorConfigDatasourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据源配置Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2020-12-31
 **/
@Service
public class GeneratorConfigDatasourceServiceImpl extends AbstractService implements GeneratorConfigDatasourceService {

    @Autowired
    public void setDao(GeneratorConfigDatasourceDao dao) {this.iDao = dao;}

}