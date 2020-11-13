package alice.code.creator.common.dao;


import alice.code.creator.domain.model.AbstractEntity;
import alice.code.creator.domain.model.Sort;

import java.util.List;
import java.util.Map;

/**
 * Dao通用接口
 * @author contact@liuxp.me
 */
public interface IDao {

    /**
     * 插入数据实体
     *
     * @param entity 无主键的数据实体
     * @return 已设置主键的数据实体
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>entity不能是null；
     * <li>entity的createUser属性值不能为空；
     */
    <S extends AbstractEntity> S insert(S entity);

    /**
     * 插入数据实体集合
     *
     * @param entities 无主键的数据实体集合
     * @return 已设置主键的数据实体
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>entities不能是null；
     * <li>entities的元素个数不能为0；
     * <li>entities的元素不能是null；
     * <li>entities的元素的createUser属性值不能为空；
     */
    <S extends AbstractEntity> Iterable<S> insert(Iterable<S> entities);

    /**
     * 按主键更新数据实体
     *
     * @param entity 有主键的数据实体
     * @return 被更新的记录数
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>entity不能是null；
     * <li>entity的id属性值不能为空；
     * <li>entity的updateUser属性值不能为空；
     */
    <S extends AbstractEntity> int update(S entity);

    /**
     * 自定义更新语句
     *
     * @param statementKey sql语句key
     * @param entity 数据实体
     * @return 被更新的记录数
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     */
    <S extends AbstractEntity> int update(String statementKey, S entity);

    /**
     * 按主键查询
     *
     * @param id 主键
     * @return 数据实体，如果没有查询结果，返回null
     */
    <S> S selectOne(Long id);

    /**
     * 按数据实体条件查询（不按主键查询）
     *
     * @param entity 查询数据实体
     * @return 数据实体，如果没有查询结果，返回null
     */
    <T extends AbstractEntity> T selectOne(T entity);

    /**
     * 按数据实体条件查询（不按主键查询）
     *
     * @param statementKey sql语句key
     * @param entity 查询数据实体
     * @return 数据实体，如果没有查询结果，返回null
     */
    <T extends AbstractEntity> T selectOne(String statementKey, T entity);

    /**
     * 查询所有
     *
     * @return 所有数据实体列表
     */
    <T extends AbstractEntity> List<T> selectAll();

    /**
     * 按主键集合查询
     *
     * @param ids 主键集合
     * @return 符合条件的数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList(Iterable<Long> ids);

    /**
     * 按条件查询（不按主键查询）
     *
     * @param entity 查询数据实体
     * @return 符合条件的数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList(T entity);

    /**
     * 查询全部数据
     * @return 数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList();

    /**
     * 查询全部数据
     * @param statementKey sql语句key
     * @return 数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList(String statementKey);

    /**
     * 按条件查询（不按主键查询）
     *
     * @param statementKey sql语句key
     * @param entity 查询数据实体
     * @return 符合条件的数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList(String statementKey, T entity);

    /**
     * 按数据实体、分页、排序查询（不按主键删除）
     *
     * @param entity 查询数据实体
     * @param rowOffset 开始行
     * @param rowLimit 行数
     * @param sorts 排序
     * @return 符合条件的数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList(T entity, Integer rowOffset, Integer rowLimit, Sort... sorts);

    /**
     * 按自定义查询条件和语句进行查询
     * @param statementKey sql语句key
     * @param paramMap 参数
     * @return 列表
     */
    <T> List<T> selectList(String statementKey, Map<String, Object> paramMap);

    /**
     * 按自定义查询条件和语句进行查询
     * @param statementKey sql语句key
     * @param entity 查询数据实体
     * @param rowOffset 开始行
     * @param rowLimit 行数
     * @param sorts 排序
     * @return 符合条件的数据实体列表
     */
    <T extends AbstractEntity> List<T> selectList(String statementKey, T entity, Integer rowOffset, Integer rowLimit, Sort... sorts);

    /**
     * 统计数据实体总数
     *
     * @return 全部数据实体总数
     */
    long count();

    /**
     * 按数据实体条件统计数据实体总数（不按主键统计）
     *
     * @param entity 查询数据实体
     * @return 总数
     */
    <T extends AbstractEntity> long count(T entity);

    /**
     * 按自定义查询条件和语句进行统计
     * @param statementKey sql语句key
     * @param paramMap 参数
     * @return 总数
     */
    long count(String statementKey, Map<String, Object> paramMap);

    /**
     * 按自定义查询条件和语句进行统计
     * @param statementKey sql语句key
     * @param entity 查询数据实体
     * @return 总数
     */
    <T extends AbstractEntity> long count(String statementKey, T entity);

    /**
     * 按主键查询数据是否存在
     *
     * @param id 主键
     * @return 如果存在返回true，否则返回false
     */
    boolean exists(Long id);

    /**
     * 按数据实体条件查询数据是否存在（不按主键查询）
     *
     * @param entity 数据实体
     * @return 如果存在返回true，否则返回false
     */
    <T extends AbstractEntity> boolean exists(T entity);

    /**
     * 按主键删除
     *
     * @param entity 待删除数据实体
     * @return 被删除的记录数
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>entity不能为null
     * <li>entity的id属性不能为null
     * <li>entity的updateUser属性不能为空值
     */
    <S extends AbstractEntity> int delete(S entity);

    /**
     * 按主键集合删除
     *
     * @param entities 待删除数据实体集合
     * @return 被删除的记录数
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>entities不能为null
     * <li>entities的元素数量必须大于0
     * <li>entities的元素的id属性不能为null
     * <li>entities的元素的updateUser属性不能为空值
     */
    <S extends AbstractEntity> int delete(Iterable<S> entities);

    /**
     * 按主键物理删除，在归档时使用
     *
     * @param id 待删除数据实体的主键
     * @return 被删除的记录数
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>id不能为null
     */
    int deletePhysically(Long id);

    /**
     * 按主键集合物理删除，在归档时使用
     *
     * @param ids 待删除数据实体的主键集合
     * @return 被删除的记录数
     * @throws IllegalArgumentException 当不满足以下约束条件时，抛出参数异常
     * <li>ids不能为null
     * <li>ids的元素数量必须大于0
     */
    int deletePhysically(Iterable<Long> ids);

}
