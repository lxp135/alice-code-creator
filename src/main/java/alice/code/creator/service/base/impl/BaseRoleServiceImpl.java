package alice.code.creator.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.base.BaseRoleDao;
import alice.code.creator.service.base.BaseRoleService;

/**
 * 角色基本信息表Service实现类
 * @author contact@liuxp.me
 */
@Service
public class BaseRoleServiceImpl extends AbstractService implements BaseRoleService {

	@Autowired
	public void setDao(BaseRoleDao dao) {this.iDao = dao;}
}