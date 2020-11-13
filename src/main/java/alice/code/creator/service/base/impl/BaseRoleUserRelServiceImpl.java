package alice.code.creator.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.base.BaseRoleUserRelDao;
import alice.code.creator.service.base.BaseRoleUserRelService;

/**
 * 角色与用户关系表Service实现类
 * @author contact@liuxp.me
 */
@Service
public class BaseRoleUserRelServiceImpl extends AbstractService implements BaseRoleUserRelService {

	@Autowired
	public void setDao(BaseRoleUserRelDao dao) {this.iDao = dao;}
}