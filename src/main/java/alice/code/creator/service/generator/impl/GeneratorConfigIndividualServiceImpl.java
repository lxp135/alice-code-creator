package alice.code.creator.service.generator.impl;

import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigIndividualDao;
import alice.code.creator.service.generator.GeneratorConfigIndividualService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户个性化配置Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 **/
@Service
public class GeneratorConfigIndividualServiceImpl extends AbstractService implements GeneratorConfigIndividualService {

    @Autowired
    public void setDao(GeneratorConfigIndividualDao dao) {this.iDao = dao;}

}