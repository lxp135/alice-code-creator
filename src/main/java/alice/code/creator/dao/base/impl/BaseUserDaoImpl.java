package alice.code.creator.dao.base.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import alice.code.creator.common.dao.AbstractDao;
import alice.code.creator.dao.base.BaseUserDao;

import javax.annotation.Resource;

/**
 * 用户基本信息表DAO实现类
 * @author contact@liuxp.me
 */
@Repository
public class BaseUserDaoImpl extends AbstractDao implements BaseUserDao {

    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
}