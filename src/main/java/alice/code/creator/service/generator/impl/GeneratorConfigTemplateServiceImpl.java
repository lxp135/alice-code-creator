package alice.code.creator.service.generator.impl;

import alice.code.creator.common.framework.context.BusinessException;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigGroupDao;
import alice.code.creator.dao.generator.GeneratorConfigTemplateDao;
import alice.code.creator.domain.model.generator.GeneratorConfigGroup;
import alice.code.creator.domain.model.generator.GeneratorConfigTemplate;
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
    private GeneratorConfigGroupDao generatorConfigGroupDao;

    @Override
    @Transactional
    public GeneratorConfigTemplate insert(GeneratorConfigTemplate generatorConfigTemplate, Long userId,String userName) {

        Long groupId = generatorConfigTemplate.getGroupId();

        Assert.notNull(groupId,"groupId不能为空");

        GeneratorConfigGroup generatorConfigGroup = generatorConfigGroupDao.selectOne(groupId);

        if(null==generatorConfigGroup){
            throw new BusinessException("模板分组不存在");
        }

        if(!userId.equals(generatorConfigGroup.getOwnerUserId())){
            // 您不能删除其他用户的模板
            throw new BusinessException("您不能在其他用户的分组下创建模板");
        }

        // 计算该分组内模板数量
        generatorConfigGroup.setTemplateAmount(generatorConfigGroup.getTemplateAmount()+1);
        generatorConfigGroup.setUpdateUser(userName);
        generatorConfigGroupDao.update(generatorConfigGroup);

        // 设置分组名称
        generatorConfigTemplate.setGroupName(generatorConfigGroup.getGroupName());

        // 插入新模板
        return super.insert(generatorConfigTemplate,userName);
    }

    @Override
    @Transactional
    public int update(GeneratorConfigTemplate generatorConfigTemplate, Long userId, String userName) {

        Assert.notNull(generatorConfigTemplate.getGroupId(),"groupId不能为空");

        GeneratorConfigGroup generatorConfigGroup = generatorConfigGroupDao.selectOne(generatorConfigTemplate.getGroupId());

        if(!userId.equals(generatorConfigGroup.getOwnerUserId())){
            // 您不能删除其他用户的模板
            throw new BusinessException("您不能在其他用户的分组下修改模板");
        }

        return super.update(generatorConfigTemplate,userName);
    }

    @Override
    @Transactional
    public int delete(Long id, Long userId,String userName){

        GeneratorConfigTemplate generatorConfigTemplate = super.selectOne(id);

        if(null==generatorConfigTemplate){
            return 0;
        }

        GeneratorConfigGroup generatorConfigGroup = generatorConfigGroupDao.selectOne(generatorConfigTemplate.getGroupId());

        if(null==generatorConfigGroup){
            throw new BusinessException("模板分组不存在");
        }

        if(!userId.equals(generatorConfigGroup.getOwnerUserId())){
            // 您不能删除其他用户的模板
            throw new BusinessException("您不能在其他用户的分组下删除模板");
        }

        // 计算该分组内模板数量
        generatorConfigGroup.setTemplateAmount(generatorConfigGroup.getTemplateAmount()-1);
        generatorConfigGroup.setUpdateUser(userName);
        generatorConfigGroupDao.update(generatorConfigGroup);

        return super.delete(id, userName);
    }
}