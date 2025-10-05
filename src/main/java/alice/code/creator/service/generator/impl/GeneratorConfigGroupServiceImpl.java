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

import jakarta.annotation.Resource;
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
    public GeneratorConfigGroup copy(Long id, Long userId, String userName) {

        // 查询原分组数据
        GeneratorConfigGroup generatorConfigGroup = super.selectOne(id);

        if(null==generatorConfigGroup){
            throw new BusinessException("模板分组不存在");
        }

        // 查询原分组内全部模板
        GeneratorConfigTemplate generatorConfigTemplateQuery = new GeneratorConfigTemplate();
        generatorConfigTemplateQuery.setGroupId(id);
        List<GeneratorConfigTemplate> generatorConfigTemplateList = generatorConfigTemplateService.selectList(generatorConfigTemplateQuery);

        GeneratorConfigGroup newGroup = new GeneratorConfigGroup();
        newGroup.setGroupName(generatorConfigGroup.getGroupName()+"的拷贝");
        newGroup.setGroupDesc(generatorConfigGroup.getGroupDesc());
        newGroup.setTemplateAmount(generatorConfigGroup.getTemplateAmount());
        newGroup.setDefaultSign(generatorConfigGroup.getDefaultSign());
        newGroup.setDefaultPackage(generatorConfigGroup.getDefaultPackage());
        newGroup.setDefaultTablePrefix(generatorConfigGroup.getDefaultTablePrefix());
        newGroup.setDefaultFieldUnique(generatorConfigGroup.getDefaultFieldUnique());
        newGroup.setDefaultFieldExt(generatorConfigGroup.getDefaultFieldExt());
        newGroup.setDefaultFieldEffective(generatorConfigGroup.getDefaultFieldEffective());
        newGroup.setOwnerUserId(userId);
        newGroup.setOwnerUserName(userName);
        newGroup.setIsPublic(0); // 复制的模板默认为私有

        // 插入新的模板分组，取得主键
        newGroup = super.insert(newGroup,userName);

        if(null!=generatorConfigTemplateList&&generatorConfigTemplateList.size()>0){

            for (GeneratorConfigTemplate generatorConfigTemplate : generatorConfigTemplateList) {
                generatorConfigTemplate.setId(null); // 主键置空
                generatorConfigTemplate.setGroupId(newGroup.getId()); // 设置新的模板分组主键
                generatorConfigTemplate.setGroupName(newGroup.getGroupName()); // 设置新的模板分组名称
                generatorConfigTemplate.setCreateUser(null);
                generatorConfigTemplate.setCreateTime(null);
                generatorConfigTemplate.setUpdateUser(null);
                generatorConfigTemplate.setUpdateTime(null);
            }
            // 插入新的模板
            generatorConfigTemplateService.insert(generatorConfigTemplateList, userName);
        }

        return newGroup;
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