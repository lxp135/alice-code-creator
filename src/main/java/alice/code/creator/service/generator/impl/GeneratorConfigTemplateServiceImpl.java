package alice.code.creator.service.generator.impl;

import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigTemplateDao;
import alice.code.creator.domain.model.generator.GeneratorConfigGroup;
import alice.code.creator.domain.model.generator.GeneratorConfigTemplate;
import alice.code.creator.service.generator.GeneratorConfigGroupService;
import alice.code.creator.service.generator.GeneratorConfigTemplateService;

import cn.hutool.core.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 代码生成器模板Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 **/
@Service
public class GeneratorConfigTemplateServiceImpl extends AbstractService implements GeneratorConfigTemplateService {

    @Autowired
    public void setDao(GeneratorConfigTemplateDao dao) {this.iDao = dao;}

    @Resource
    private GeneratorConfigGroupService generatorConfigGroupService;


    @Override
    @Transactional
    public GeneratorConfigTemplate insert(GeneratorConfigTemplate generatorConfigTemplate, String createUser) {

        Long groupId = generatorConfigTemplate.getGroupId();

        Assert.notNull(groupId,"groupId不能为空");

        GeneratorConfigGroup generatorConfigGroup = generatorConfigGroupService.selectOne(groupId);

        if(null==generatorConfigGroup){
            throw new RuntimeException("模板分组不存在");
        }

        // 计算该分组内模板数量
        generatorConfigGroup.setTemplateAmount(generatorConfigGroup.getTemplateAmount()+1);
        generatorConfigGroupService.update(generatorConfigGroup,createUser);

        // 设置分组名称
        generatorConfigTemplate.setGroupName(generatorConfigGroup.getGroupName());

        // 插入新模板
        return super.insert(generatorConfigTemplate,createUser);
    }

    @Override
    @Transactional
    public int delete(Long id, String deleteUser){
        GeneratorConfigTemplate generatorConfigTemplate = super.selectOne(id);

        if(null==generatorConfigTemplate){
            return 0;
        }

        GeneratorConfigGroup generatorConfigGroup = generatorConfigGroupService.selectOne(generatorConfigTemplate.getGroupId());

        if(null==generatorConfigGroup){
            throw new RuntimeException("模板分组不存在");
        }

        // 计算该分组内模板数量
        generatorConfigGroup.setTemplateAmount(generatorConfigGroup.getTemplateAmount()-1);
        generatorConfigGroupService.update(generatorConfigGroup,deleteUser);

        return super.delete(id, deleteUser);
    }
}