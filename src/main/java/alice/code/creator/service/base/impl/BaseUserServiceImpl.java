package alice.code.creator.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.base.BaseRoleUserRelDao;
import alice.code.creator.dao.base.BaseUserDao;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.base.BaseRoleUserRel;
import alice.code.creator.domain.model.base.BaseUser;
import alice.code.creator.service.base.BaseRoleUserRelService;
import alice.code.creator.service.base.BaseUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户基本信息表Service实现类
 * @author contact@liuxp.me
 */
@Service
public class BaseUserServiceImpl extends AbstractService implements BaseUserService {

	@Autowired
	public void setDao(BaseUserDao dao) {this.iDao = dao;}

	@Resource
	private BaseRoleUserRelDao baseRoleUserRelDao;

	@Resource
	private BaseRoleUserRelService baseRoleUserRelService;

	// 默认角色编号
	private static final long DEFAULT_ROLE_ID = 3;

	/**
	 * 删除数据
	 * @param id 主键
	 * @param deleteUser 删除人
	 * @return 删除条数
	 */
	@Override
	@Transactional
	public int delete(Long id, String deleteUser) {

		if (id == null) {
			throw new IllegalArgumentException("参数id不能为null");
		}
		if (deleteUser == null) {
			throw new IllegalArgumentException("参数deleteUser不能为null");
		}

		BaseRoleUserRel baseRoleUserRelQuery = new BaseRoleUserRel();
		baseRoleUserRelQuery.setUserId(id);

		List<BaseRoleUserRel> baseRoleUserRelList = baseRoleUserRelService.selectList(baseRoleUserRelQuery);
		if(null!=baseRoleUserRelList&&baseRoleUserRelList.size()>0){
			for(BaseRoleUserRel baseRoleUserRel : baseRoleUserRelList){
				// 删除用户时，同步删除用户与角色关系
				baseRoleUserRelService.deletePhysically(baseRoleUserRel.getId());
			}
		}

		return super.delete(id,deleteUser);
	}

	/**
	 * 用户注册（含数据库事务）
	 * @param baseUser 用户信息
	 * @return Result<BaseUser>
	 */
	@Override
	@Transactional
	public Result<BaseUser> register(BaseUser baseUser) {

		// 密码加密
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String passwordEncrypt = bCryptPasswordEncoder.encode(baseUser.getUserPassword());
		baseUser.setUserPassword(passwordEncrypt);

		// 默认启用
		baseUser.setIsEnable(1);

		// 插入用户数据
		baseUser = iDao.insert(baseUser);

		// 关联默认角色
		BaseRoleUserRel baseRoleUserRel = new BaseRoleUserRel();
		baseRoleUserRel.setRoleId(DEFAULT_ROLE_ID);
		baseRoleUserRel.setUserId(baseUser.getId());
		baseRoleUserRel.setCreateUser(baseUser.getCreateUser());
		baseRoleUserRelDao.insert(baseRoleUserRel);

		return new Result<>(baseUser);
	}
}