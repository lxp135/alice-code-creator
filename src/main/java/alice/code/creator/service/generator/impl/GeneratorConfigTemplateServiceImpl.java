package alice.code.creator.service.generator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigTemplateDao;
import alice.code.creator.service.generator.GeneratorConfigTemplateService;

/**
 * 代码生成器模板配置Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2018-12-26
 **/
@Service
public class GeneratorConfigTemplateServiceImpl extends AbstractService implements GeneratorConfigTemplateService {

    @Autowired
    public void setDao(GeneratorConfigTemplateDao dao) {this.iDao = dao;}

}