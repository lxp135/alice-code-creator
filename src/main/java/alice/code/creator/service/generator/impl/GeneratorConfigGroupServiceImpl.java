package alice.code.creator.service.generator.impl;

import alice.code.creator.common.framework.context.BusinessException;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.generator.GeneratorConfigGroupDao;
import alice.code.creator.domain.model.generator.GeneratorConfigGroup;
import alice.code.creator.domain.model.generator.GeneratorConfigTemplate;
import alice.code.creator.service.generator.GeneratorConfigGroupService;

import alice.code.creator.service.generator.GeneratorConfigTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板分组Service层实现类
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 **/
@Service
public class GeneratorConfigGroupServiceImpl extends AbstractService implements GeneratorConfigGroupService {

    @Autowired
    public void setDao(GeneratorConfigGroupDao dao) {this.iDao = dao;}

    @Resource
    private GeneratorConfigTemplateService generatorConfigTemplateService;

    @Override
    public int update(GeneratorConfigGroup generatorConfigGroup, Long userId, String userName) {

        GeneratorConfigGroup oldData = super.selectOne(generatorConfigGroup.getId());

        if(!userId.equals(oldData.getOwnerUserId())){
            // 您不能修改其他用户的分组
            throw new BusinessException("您不能修改其他用户的分组");
        }

        return super.update(generatorConfigGroup,userName);
    }

    @Override
    @Transactional
    public int delete(Long id, Long userId, String userName) {

        GeneratorConfigGroup generatorConfigGroup = super.selectOne(id);

        if(null==generatorConfigGroup){
            throw new BusinessException("模板分组不存在");
        }

        if(!userId.equals(generatorConfigGroup.getOwnerUserId())){
            // 您不能删除其他用户的分组
            throw new BusinessException("您不能删除其他用户的分组");
        }

        // 查询该分组内全部模板
        GeneratorConfigTemplate generatorConfigTemplateQuery = new GeneratorConfigTemplate();
        generatorConfigTemplateQuery.setGroupId(id);
        List<GeneratorConfigTemplate> generatorConfigTemplateList = generatorConfigTemplateService.selectList(generatorConfigTemplateQuery);

        if(null!=generatorConfigTemplateList&&generatorConfigTemplateList.size()>0){

            List<Long> ids = new ArrayList<>();
            for (GeneratorConfigTemplate generatorConfigTemplate : generatorConfigTemplateList) {
                ids.add(generatorConfigTemplate.getId());
            }
            // 删除分组内全部模板
            generatorConfigTemplateService.delete(ids, userName);
        }

        return super.delete(id,userName);
    }
}