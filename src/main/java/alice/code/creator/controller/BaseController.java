package alice.code.creator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alice.code.creator.domain.Account;
import alice.code.creator.common.framework.context.AccountContext;
import alice.code.creator.domain.model.AbstractEntity;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.Sort;

import java.util.Date;

/**
 * Controller抽象基类
 * @author contact@liuxp.me
 */
public abstract class BaseController {

	Logger logger = LoggerFactory.getLogger(getClass());

	protected <T extends AbstractEntity> Page<T> buildPage(T params, int pageNum, int pageSize, String sortName, String sortOrder) {

		// 设置合理的参数
		// 页码最小是1
		if (pageNum < 1) {
			pageNum = 1;
		}
		// 每页条数最小10，最大200
		if (pageSize < 1) {
			pageSize = 10;
		} else if (pageSize > 200) {
			pageSize = 200;
		}
		// 开始页码
		int pageIndex = pageNum - 1;
		// 排序
		Sort sort = null;
		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.desc(sortName);
		} else {
			sort = Sort.asc(sortName);
		}
		// 创建分页对象
		Page<T> page = new Page<T>(params,pageIndex, pageSize, sort);
		return page;
	}

	protected <T extends AbstractEntity> Page<T> buildPage(int pageNum, int pageSize, String sortName, String sortOrder) {

		return buildPage(null,pageNum,pageSize,sortName,sortOrder);
	}

	/**
	 * 取得登录用户信息
	 * @return 登录用户信息
	 */
	protected Account getAccount(){
		Account account = AccountContext.getAccount();
		if(account == null){
			throw new RuntimeException("登录用户不存在，请重新登录");
		}
		return account;
	}

	/**
	 * 插入数据实体中的标准字段
	 * @param entity 数据实体
	 * @param <T> 基础类
	 */
	protected <T extends AbstractEntity> void buildInsert(T entity){
		entity.setCreateUser(getAccount().getUserAccount());
		entity.setCreateTime(new Date());
		entity.setUpdateUser(getAccount().getUserAccount());
		entity.setUpdateTime(new Date());
	}

	/**
	 * 更新数据实体中的标准字段
	 * @param entity 数据实体
	 * @param <T> 基础类
	 */
	protected <T extends AbstractEntity> void buildUpdate(T entity){
		entity.setUpdateUser(getAccount().getUserAccount());
		entity.setUpdateTime(new Date());
	}
}
