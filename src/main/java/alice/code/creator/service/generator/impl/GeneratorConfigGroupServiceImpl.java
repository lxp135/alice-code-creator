package alice.code.creator.service.generator.impl;

import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigGroupDao;
import alice.code.creator.service.generator.GeneratorConfigGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模板分组Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 **/
@Service
public class GeneratorConfigGroupServiceImpl extends AbstractService implements GeneratorConfigGroupService {

    @Autowired
    public void setDao(GeneratorConfigGroupDao dao) {this.iDao = dao;}

}