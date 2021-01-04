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
     * @param userId 用户编号
     * @param userName 用户名称
     * @return 数据实体
     */
    GeneratorConfigTemplate insert(GeneratorConfigTemplate generatorConfigTemplate, Long userId,String userName);

    /**
     * 更新数据
     * @param generatorConfigTemplate 数据实体更新对象
     * @param userId 用户编号
     * @param userName 用户名称
     * @return 更新的条数
     */
    int update(GeneratorConfigTemplate generatorConfigTemplate, Long userId,String userName);

    /**
     * 删除数据
     * @param id 主键
     * @param userId 用户编号
     * @param userName 用户名称
     * @return 删除条数
     */
    int delete(Long id, Long userId,String userName);

}