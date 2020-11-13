package alice.code.creator.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.base.BaseRoleMenuRelDao;
import alice.code.creator.service.base.BaseRoleMenuRelService;

/**
 * 角色与菜单关系表Service实现类
 * @author contact@liuxp.me
 */
@Service
public class BaseRoleMenuRelServiceImpl extends AbstractService implements BaseRoleMenuRelService {

	@Autowired
	public void setDao(BaseRoleMenuRelDao dao) {this.iDao = dao;}
}