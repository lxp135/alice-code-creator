package alice.code.creator.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.base.BaseMenuDao;
import alice.code.creator.domain.model.base.BaseRoleMenuRel;
import alice.code.creator.service.base.BaseMenuService;
import alice.code.creator.service.base.BaseRoleMenuRelService;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 菜单基本信息表Service实现类
 * @author contact@liuxp.me
 */
@Service
public class BaseMenuServiceImpl extends AbstractService implements BaseMenuService {

	@Autowired
	public void setDao(BaseMenuDao dao) {this.iDao = dao;}

	@Resource
	private BaseRoleMenuRelService baseRoleMenuRelService;

	@Override
	public int delete(Long id, String deleteUser) {
		if (id == null) {
			throw new IllegalArgumentException("参数id不能为null");
		}
		if (deleteUser == null) {
			throw new IllegalArgumentException("参数deleteUser不能为null");
		}
		int deleteCount = super.delete(id,deleteUser);
		if(deleteCount>0){
			// 删除菜单与角色绑定关系
			BaseRoleMenuRel baseRoleMenuRelQuery = new BaseRoleMenuRel();
			baseRoleMenuRelQuery.setMenuId(id);
			List<BaseRoleMenuRel> baseRoleMenuRelList = baseRoleMenuRelService.selectList(baseRoleMenuRelQuery);
			for(BaseRoleMenuRel baseRoleMenuRel :baseRoleMenuRelList){
				baseRoleMenuRelService.deletePhysically(baseRoleMenuRel.getId());
			}
		}

		return deleteCount;
	}
}