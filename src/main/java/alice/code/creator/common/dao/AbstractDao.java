package alice.code.creator.common.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import alice.code.creator.common.util.BeanConvertUtils;
import alice.code.creator.domain.model.AbstractEntity;
import alice.code.creator.domain.model.Sort;

import java.util.*;

/**
 * Dao抽象基类
 * @author contact@liuxp.me
 */
public abstract class AbstractDao implements IDao, IMapper {

    protected SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        // 由于Spring cglib代理限制，不能使用private，final
        return this.sqlSessionTemplate;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    // 日志
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // SQL 插入数据
    private static final String STATEMENT_INSERT = "insert";
    // SQL 批量插入数据
    private static final String STATEMENT_BATCH_INSERT = "batchInsert";
    // SQL 按主键更新
    private static final String STATEMENT_UPDATE_BY_PRIMARY_KEY = "updateByPrimaryKey";
    // SQL 按主键删除
    private static final String STATEMENT_DELETE_BY_PRIMARY_KEY = "deleteByPrimaryKey";
    // SQL 按主键批量删除
    private static final String STATEMENT_BATCH_DELETE_BY_PRIMARY_KEY = "batchDeleteByPrimaryKey";
    // SQL 按主键批量物理删除
    private static final String STATEMENT_BATCH_DELETE_BY_PRIMARY_KEY_PHYSICALLY = "batchDeleteByPrimaryKeyPhysically";
    // SQL 查询所有记录
    private static final String STATEMENT_SELECT_ALL = "selectAll";
    // SQL 按主键查询
    private static final String STATEMENT_SELECT_BY_PRIMARY_KEY = "selectByPrimaryKey";
    // SQL 按主键集合查询
    private static final String STATEMENT_SELECT_BY_PRIMARY_KEY_SET = "selectByPrimaryKeys";
    // SQL 按条件查询
    private static final String STATEMENT_SELECT_BY_CONDITION = "selectByCondition";
    // SQL 按条件统计记录数
    private static final String STATEMENT_COUNT_BY_CONDITION = "countByCondition";

    // 排序参数 Key
    protected static final String KEY_SORT = "_sort";
    // 分页参数 Key
    protected static final String KEY_PAGE = "_page";
    // 数据版本参数 Key
    protected static final String KEY_VERSION = "_version";

    // 批处理操作最大数量
    protected static final int MAX_BATCH = 1000;
    // 查询结果最大数量
    protected static final int MAX_RESULT = 1000;

    /**
     * 获取映射文件命名空间 ，默认使用DAO实例的类名
     * @return 映射文件命名空间
     */
    @Override
    public String getNamespace() {
        Class clazz = this.getClass();

        // 本系统将DAO接口作为命名空间
        Class<?>[] clazzes = clazz.getInterfaces();
        // 如果DAO没有继承接口
        if (clazzes == null || clazzes.length < 1) {
            throw new RuntimeException("DAO实现类需要实现接口，同时该接口名作为Mapper命名空间");
        }
        return clazzes[0].getName();
    }

    /**
     * 获取SQL语句在映射文件中的id
     * @return SQL语句在映射文件中的id
     */
    @Override
    public final String getStatement(String statementKey) {
        return this.getNamespace() + "." + statementKey;
    }

    @Override
    public final <S extends AbstractEntity> S insert(S entity) {
        // 执行时间
        Date exeDate = new Date();
        // 校验参数
        if (entity == null) {
            throw new IllegalArgumentException("参数异常：数据实体是null，无法插入数据库");
        } else if (StringUtils.isEmpty(entity.getCreateUser())) {
            throw new IllegalArgumentException("参数异常：数据实体的createUser属性是空值，无法插入数据库");
        }

        // 设置创建时间
        entity.setCreateTime(exeDate);
        // 设置修改时间
        entity.setUpdateTime(exeDate);
        // 设置修改人
        entity.setUpdateUser(entity.getCreateUser());
        // 设置删除标志
        entity.setIsDelete(AbstractEntity.NOT_DELETED);
        // 数据实体非空校验(跳过)
        // 获取插入语句
        String statement = this.getStatement(STATEMENT_INSERT);
        // 执行插入语句
        int rows = getSqlSessionTemplate().insert(statement, entity);

        // 返回已设置主键的数据实体
        return entity;
    }

    @Override
    public final <S extends AbstractEntity> Iterable<S> insert(Iterable<S> entities) {
        // 执行时间
        Date exeDate = new Date();
        // 校验参数，并将参数转换成List
        List<S> entityList = null;
        if (entities == null) {
            throw new IllegalArgumentException("参数异常：集合是null，无法插入数据库");
        } else {
            entityList = new ArrayList<>();

            for (S entity : entities) {
                if (entity == null) {
                    throw new IllegalArgumentException("参数异常：集合中存在数据实体是null，无法插入数据库");
                } else if (StringUtils.isEmpty(entity.getCreateUser())) {
                    throw new IllegalArgumentException("参数异常：集合中存在数据实体的createUser属性是空值，无法插入数据库");
                } else {
                    // 设置创建时间
                    entity.setCreateTime(exeDate);
                    // 设置修改时间
                    entity.setUpdateTime(exeDate);
                    // 设置修改人
                    entity.setUpdateUser(entity.getCreateUser());
                    // 设置删除标志
                    entity.setIsDelete(AbstractEntity.NOT_DELETED);
                    // 放入列表
                    entityList.add(entity);
                }
            }

            if (entityList.size() == 0) {
                throw new IllegalArgumentException("参数异常：集合中没有数据，无法插入数据库");
            } else if (entityList.size() > MAX_BATCH) {
                throw new IllegalArgumentException("参数异常：插入数据时，集合允许的最大数量为1000，当前数量" + entityList.size() + "，建议分批插入记录");
            }
        }
        // 数据实体非空校验（跳过）
        // 获取批量插入语句
        String statement = this.getStatement(STATEMENT_BATCH_INSERT);
        // 执行批量插入语句
        int rows = getSqlSessionTemplate().insert(statement, entityList);
        // 返回已设置主键的数据实体集合
        return entities;
    }

    @Override
    public final <S extends AbstractEntity> int update(S entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("参数异常：数据实体的id属性是null，无法更新数据库");
        }
        // 获取更新语句
        String statement = this.getStatement(STATEMENT_UPDATE_BY_PRIMARY_KEY);
        // 执行更新语句，返回更新记录数
        return this.update(statement, entity);

    }

    @Override
    public final <S extends AbstractEntity> int update(String statement, S entity) {
        // 执行时间
        Date exeDate = new Date();
        // 校验参数
        if (entity == null) {
            throw new IllegalArgumentException("参数异常：数据实体是null，无法更新数据库");
        }else if (StringUtils.isEmpty(entity.getUpdateUser())) {
            throw new IllegalArgumentException("参数异常：数据实体的updateUser属性是空值，无法更新数据库");
        }
        // 设置修改时间
        entity.setUpdateTime(exeDate);
        // 将查询参数包装成哈希表
        Map<String, Object> parameterMap = null;
        try {
            parameterMap = BeanConvertUtils.bean2Map(entity);
        } catch (Exception e) {
            throw new RuntimeException("参数异常：将更新数据实体转换成MAP时程序出错", e);
        }

        // 执行更新语句，返回更新记录数
        return getSqlSessionTemplate().update(statement, parameterMap);

    }

    @Override
    public final <T> T selectOne(Long id) {

        T result = null;

        // 如果主键不是null
        if (id != null) {

            long nowTime = new Date().getTime();

            // 获取按主键查询语句
            String statement = this.getStatement(STATEMENT_SELECT_BY_PRIMARY_KEY);
            // 执行按主键查询语句
            List<T> resultList = getSqlSessionTemplate().selectList(statement, id);
            // 返回查询结果
            if (resultList != null && resultList.size() > 0) {
                result = resultList.get(0);
            }

            long timeCost = new Date().getTime()-nowTime;

            if(timeCost>500){
                logger.info(statement+"耗时"+timeCost+"毫秒");
            }

        }

        // 如果主键是null，或者查询结果为空
        return result;
    }

    @Override
    public final <T extends AbstractEntity> T selectOne(T entity) {
        // 执行按数据实体，限定查询1条记录的查询语句
        List<T> resultList = this.selectList(entity, 0, 1, new Sort());
        // 返回查询结果
        if (resultList != null && resultList.size() > 0) {
            T result = resultList.get(0);
            return result;
        }
        // 如果查询结果为空
        return null;
    }

    @Override
    public final <T extends AbstractEntity> T selectOne(String statement, T entity) {
        // 执行按数据实体，限定查询1条记录的查询语句
        List<T> resultList = this.selectList(statement,entity, 0, 1, new Sort());
        // 返回查询结果
        if (resultList != null && resultList.size() > 0) {
            T result = resultList.get(0);
            return result;
        }
        // 如果查询结果为空
        return null;
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectAll() {
        long nowTime = new Date().getTime();
        // 获取查询全部的语句
        String statement = this.getStatement(STATEMENT_SELECT_ALL);
        // 执行查询全部的语句
        List<T> resultList = getSqlSessionTemplate().selectList(statement);
        // 判断结果集是否过大
        if (resultList != null && resultList.size() > MAX_RESULT) {
            logger.warn("查询结果集过大：当前数量" + resultList.size() + "，建议慎用selectAll方法，改用分页排序查询[SQL:" + statement + "]");
        }
        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }
        // 返回查询结果
        return resultList;
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList(Iterable<Long> ids) {
        long nowTime = new Date().getTime();
        // 过滤null
        ArrayList<Long> idList = new ArrayList<>();
        if (ids != null) {
            for (Long id : ids) {
                if (id != null) {
                    idList.add(id);
                }
            }
        }
        // 校验查询id的数量
        if (idList.size() == 0) {
            throw new IllegalArgumentException("参数异常：主键集合中没有值，无法按主键集合查询记录");
        } else if (idList.size() > MAX_BATCH) {
            throw new IllegalArgumentException("参数异常：主键集合允许的最大数量为1000，当前数量" + idList.size() + "，建议分批查询记录");
        }
        // 获取按主键集合查询的语句
        String statement = this.getStatement(STATEMENT_SELECT_BY_PRIMARY_KEY_SET);
        // 执行按主键集合查询的语句，返回查询结果
        List<T> result = getSqlSessionTemplate().selectList(statement, idList);
        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }
        return result;
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList(T entity) {
        return this.selectList(entity, null, null);
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList() {
        return this.selectList(STATEMENT_SELECT_BY_CONDITION,null, null, null);
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList(String statementKey) {
        return this.selectList(statementKey,null, null, null);
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList(String statementKey, T entity) {
        return this.selectList(statementKey,entity, null, null);
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList(T entity, Integer rowOffset, Integer rowLimit, Sort... sorts) {
        return this.selectList(STATEMENT_SELECT_BY_CONDITION,entity,rowOffset,rowLimit,sorts);
    }

    @Override
    public final <T extends AbstractEntity> List<T> selectList(String statementKey, T entity, Integer rowOffset, Integer rowLimit, Sort... sorts) {

        long nowTime = new Date().getTime();

        // 将查询参数包装成哈希表
        Map<String, Object> parameterMap = null;
        try {
            parameterMap = BeanConvertUtils.bean2Map(entity);
        } catch (Exception e) {
            throw new RuntimeException("将查询数据实体转换成MAP时程序出错", e);
        }
        // 设置排序参数
        if (sorts != null && sorts.length > 0) {
            parameterMap.put(KEY_SORT, Arrays.asList(sorts));
        }
        // 设置分页参数
        if (rowOffset != null && rowLimit != null) {
            if (rowOffset < 0) {
                throw new IllegalArgumentException("参数异常：参数rowOffset不能小于0");
            }
            if (rowLimit < 1) {
                throw new IllegalArgumentException("参数异常：参数rowLimit不能小于1");
            }
            RowBounds rowBounds = new RowBounds(rowOffset, rowLimit);
            parameterMap.put(KEY_PAGE, rowBounds);
        } else {
            // 当没有设置分页时，硬编码限制返回超过最大值的结果集，用于触发异常
            RowBounds rowBounds = new RowBounds(0, MAX_RESULT + 1);
            parameterMap.put(KEY_PAGE, rowBounds);
        }

        // 获取按条件查询的语句
        String statement = this.getStatement(statementKey);
        // 执行按条件查询的语句
        List<T> resultList = getSqlSessionTemplate().selectList(statement, parameterMap);
        // 判断结果集是否过大
        if (resultList != null && resultList.size() > MAX_RESULT) {
            String param = parameterMap.toString();
            logger.warn("查询结果集过大：当前数量" + resultList.size() + "，建议设置合理的分页排序参数[SQL:" + statement + ";PARAM:" + param + "]");
        }

        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }

        // 返回查询结果
        return resultList;
    }

    @Override
    public final long count() {
        return this.count(null);
    }

    @Override
    public final <T extends AbstractEntity> long count(T entity) {
        long nowTime = new Date().getTime();
        // 将查询参数包装成哈希表
        Map<String, Object> parameterMap = null;
        try {
            parameterMap = BeanConvertUtils.bean2Map(entity);
        } catch (Exception e) {
            throw new RuntimeException("将查询数据实体转换成MAP时程序出错", e);
        }
        // 获取统计语句
        String statement = this.getStatement(STATEMENT_COUNT_BY_CONDITION);
        // 执行统计语句
        Long count = getSqlSessionTemplate().selectOne(statement, parameterMap);
        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }
        // 返回结果
        if (count != null) {
            return count.longValue();
        }
        return 0;
    }

    @Override
    public final <T extends AbstractEntity> long count(String statementKey, T entity) {
        long nowTime = new Date().getTime();
        // 将查询参数包装成哈希表
        Map<String, Object> parameterMap = null;
        try {
            parameterMap = BeanConvertUtils.bean2Map(entity);
        } catch (Exception e) {
            throw new RuntimeException("将查询数据实体转换成MAP时程序出错", e);
        }
        // 获取统计语句
        String statement = this.getStatement(statementKey+"Count");
        // 执行统计语句
        Long count = getSqlSessionTemplate().selectOne(statement, parameterMap);
        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }
        // 返回结果
        if (count != null) {
            return count.longValue();
        }
        return 0;
    }

    @Override
    public final boolean exists(Long id) {
        // 如果主键不是null
        if (id != null) {
            // 执行按主键查询语句
            Object result = this.selectOne(id);
            // 如果查询结果不是null，返回true
            if (result != null) {
                return true;
            }
        }
        // 如果主键是null，或者查询结果为空
        return false;
    }

    @Override
    public final <T extends AbstractEntity> boolean exists(T entity) {
        // 如果数据实体不是null
        if (entity != null) {
            // 执行按数据实体查询语句
            T result = this.selectOne(entity);
            // 如果查询结果不是null，返回true
            if (result != null) {
                return true;
            }
        }
        // 如果数据实体是null，或者查询结果为空
        return false;
    }

    @Override
    public final <S extends AbstractEntity> int delete(S entity) {
        // 执行时间
        Date exeDate = new Date();
        // 校验参数
        if (entity == null) {
            throw new IllegalArgumentException("参数异常：数据实体是null，无法删除记录");
        } else if (entity.getId() == null) {
            throw new IllegalArgumentException("参数异常：数据实体的id属性是null，无法删除记录");
        } else if (StringUtils.isEmpty(entity.getUpdateUser())) {
            throw new IllegalArgumentException("参数异常：数据实体的updateUser属性是空值，无法删除记录");
        }

        // 数据更新时间
        entity.setUpdateTime(exeDate);
        // 获取删除语句
        String statement = this.getStatement(STATEMENT_DELETE_BY_PRIMARY_KEY);
        // 执行删除语句
        return getSqlSessionTemplate().update(statement, entity);

    }

    @Override
    public final <S extends AbstractEntity> int delete(Iterable<S> entities) {
        // 执行时间
        Date exeDate = new Date();
        // 校验参数
        List<Long> idList = new ArrayList<>();
        String updateUser = null;
        String entityClassName = null;

        if (entities == null) {
            throw new IllegalArgumentException("参数异常：集合是null，无法删除记录");
        } else {
            for (S entity : entities) {
                if (entity == null) {
                    throw new IllegalArgumentException("参数异常：数据实体是null，无法删除记录");
                } else if (entity.getId() == null) {
                    throw new IllegalArgumentException("参数异常：数据实体的id属性是null，无法删除记录");
                } else if (StringUtils.isEmpty(entity.getUpdateUser())) {
                    throw new IllegalArgumentException("参数异常：数据实体的updateUser属性是空值，无法删除记录");
                }
                idList.add(entity.getId());
                if (updateUser == null) {
                    updateUser = entity.getUpdateUser();
                }
                if (entityClassName == null) {
                    entityClassName = entity.getClass().getName();
                }
            }
        }

        // 校验删除id的数量
        if (idList.size() == 0) {
            throw new IllegalArgumentException("参数异常：主键集合中没有值，无法按主键集合删除记录");
        } else if (idList.size() > MAX_BATCH) {
            throw new IllegalArgumentException("参数异常：主键集合允许的最大数量为1000，当前数量" + idList.size() + "，建议分批删除记录");
        }

        // 将参数包装成哈希表
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("ids", idList);
        parameterMap.put("updateUser", updateUser);
        parameterMap.put("updateTime", exeDate);
        // 获取批量删除语句
        String statement = this.getStatement(STATEMENT_BATCH_DELETE_BY_PRIMARY_KEY);
        // 执行批量删除语句，返回删除条数
        return getSqlSessionTemplate().update(statement, parameterMap);

    }

    @Override
    public int deletePhysically(Long id) {
        // 参数校验
        if (id == null) {
            throw new IllegalArgumentException("参数异常：id是null，无法物理删除记录");
        }
        // 放入id清单
        List<Long> idList = new ArrayList<>();
        idList.add(id);
        // 执行物理删除
        return this.deletePhysically(idList);
    }

    @Override
    public int deletePhysically(Iterable<Long> ids) {
        // 参数校验
        List<Long> idList = new ArrayList<>();
        if (ids == null) {
            throw new IllegalArgumentException("参数异常：集合是null，无法物理删除记录");
        } else {
            for (Long id : ids) {
                if (id == null) {
                    throw new IllegalArgumentException("参数异常：集合中id是null，无法物理删除记录");
                }
                // 校验通过后，放入清单
                idList.add(id);
            }
        }
        // 校验删除id的数量
        if (idList.size() == 0) {
            throw new IllegalArgumentException("参数异常：主键集合中没有值，无法按主键集合物理删除记录");
        } else if (idList.size() > MAX_BATCH) {
            throw new IllegalArgumentException("参数异常：主键集合允许的最大数量为1000，当前数量" + idList.size() + "，建议分批物理删除记录");
        }
        // 获取批量物理删除语句
        String statement = this.getStatement(STATEMENT_BATCH_DELETE_BY_PRIMARY_KEY_PHYSICALLY);
        // 执行批量物理删除语句，返回删除的条数
        return getSqlSessionTemplate().delete(statement, idList);
    }

    public <S> List<S> selectList(String statementKey, Map<String, Object> paramMap){

        long nowTime = new Date().getTime();

        // 获取语句
        String statement = this.getStatement(statementKey);
        // 执行
        List<S> resultList = getSqlSessionTemplate().selectList(statement, paramMap);
        // 判断结果集是否过大
        if (resultList != null && resultList.size() > MAX_RESULT) {
            String param = paramMap.toString();
            logger.warn("查询结果集过大：当前数量" + resultList.size() + "，建议设置合理的分页排序参数[SQL:" + statement + ";PARAM:" + param + "]");
        }
        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }
        // 返回
        return resultList;

    }

    @Override
    public long count(String statementKey, Map<String, Object> paramMap){
        long nowTime = new Date().getTime();
        // 获取统计语句
        String statement = this.getStatement(statementKey);
        // 执行统计语句
        Long count = getSqlSessionTemplate().selectOne(statement, paramMap);
        long timeCost = new Date().getTime()-nowTime;

        if(timeCost>500){
            logger.info(statement+"耗时"+timeCost+"毫秒");
        }
        // 返回结果
        if (count != null) {
            return count.longValue();
        }
        return 0;
    }
}