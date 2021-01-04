package alice.code.creator.service.generator;

import alice.code.creator.common.service.IService;
import alice.code.creator.domain.model.generator.GeneratorConfigTemplate;

/**
 * 代码生成器模板Service层接口类
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 **/
public interface GeneratorConfigTemplateService extends IService{

    /**
     * 插入数据
     * @param generatorConfigTemplate 数据实体
     * @param createUser 创建人
     * @return 数据实体
     */
    GeneratorConfigTemplate insert(GeneratorConfigTemplate generatorConfigTemplate, String createUser);

    /**
     * 删除数据
     * @param id 主键
     * @param deleteUser 删除人
     * @return 删除条数
     */
    int delete(Long id, String deleteUser);

}