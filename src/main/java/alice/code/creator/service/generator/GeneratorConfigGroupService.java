package alice.code.creator.service.generator;

import alice.code.creator.common.service.IService;
import alice.code.creator.domain.model.generator.GeneratorConfigGroup;

/**
 * 模板分组Service层接口类
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 **/
public interface GeneratorConfigGroupService extends IService{

    /**
     * 更新数据
     * @param generatorConfigGroup 数据实体更新对象
     * @param userId 用户编号
     * @param userName 用户名称
     * @return 更新的条数
     */
    int update(GeneratorConfigGroup generatorConfigGroup, Long userId,String userName);

    /**
     * 复制分组
     * @param id 被选中的分组主键
     * @param userId 用户编号
     * @param userName 用户名称
     * @return 更新的条数
     */
    GeneratorConfigGroup copy(Long id, Long userId,String userName);

    /**
     * 删除数据
     * @param id 主键
     * @param userId 用户编号
     * @param userName 用户名称
     * @return 删除条数
     */
    int delete(Long id, Long userId,String userName);
}