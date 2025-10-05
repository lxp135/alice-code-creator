package alice.code.creator.common.framework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import alice.code.creator.domain.Account;
import alice.code.creator.domain.model.base.*;
import alice.code.creator.service.base.*;

import jakarta.annotation.Resource;
import java.util.*;

/**
 * 用户信息与权限读取
 * @author contact@liuxp.me
 */
@Component
public class DbUserDetailsService implements UserDetailsService {

	// 公共资源码，通用接口权限使用该编码
	private final static String KEY_PUBLIC_AUTHORITY = "PUBLIC";

	// 用户信息接口
	@Resource
	private BaseUserService baseUserService;

	// 菜单表
	@Resource
	private BaseMenuService baseMenuService;

	// 角色表
	@Resource
	private BaseRoleService baseRoleService;

	// 用户与角色关系表
	@Resource
	private BaseRoleUserRelService baseRoleUserRelService;

	// 角色与菜单关系表
	@Resource
	private BaseRoleMenuRelService baseRoleMenuRelService;

	/**
	 * 取得用户信息
	 * @param userAccount 用户账号
	 * @return 用户信息
	 */
	@Override
	public Account loadUserByUsername(String userAccount){
		// 根据用户账号查询管理平台用户详细信息

		BaseUser baseUserQuery = new BaseUser();
		baseUserQuery.setUserAccount(userAccount);
		BaseUser baseUser = baseUserService.selectOne(baseUserQuery); // 查询用户基本信息

		if(null!= baseUser){

			List<BaseRole> baseRoles = new ArrayList<>(); // 当前用户角色列表
			List<BaseMenu> baseMenus = new ArrayList<>(); // 当前用户当前角色菜单列表

			// 查询用户所属角色列表
			BaseRoleUserRel baseRoleUserRelQuery = new BaseRoleUserRel();
			baseRoleUserRelQuery.setUserId(baseUser.getId());
			List<BaseRoleUserRel> baseRoleUserRels = baseRoleUserRelService.selectList(baseRoleUserRelQuery);
			if(baseRoleUserRels.size()>0){

				Set<Long> menuIds = new HashSet<>();

				for (BaseRoleUserRel baseRoleUserRel : baseRoleUserRels){
					BaseRole baseRole = baseRoleService.selectOne(baseRoleUserRel.getRoleId());
//					baseUser.setRoleId(baseRole.getId()); // 当前登录角色编号
//					baseUser.setRoleName(baseRole.getRoleName()); // 当前登录角色名称
					baseRoles.add(baseRole);

					// 查询当前用户菜单列表
					BaseRoleMenuRel systemBaseRoleMenuRelQuery = new BaseRoleMenuRel();
					systemBaseRoleMenuRelQuery.setRoleId(baseRoleUserRel.getRoleId());
					List<BaseRoleMenuRel> systemBaseRoleMenuRels = baseRoleMenuRelService.selectList(systemBaseRoleMenuRelQuery);

					for(BaseRoleMenuRel baseRoleMenuRel : systemBaseRoleMenuRels){
						menuIds.add(baseRoleMenuRel.getMenuId());
					}

				}

				for(Long menuId:menuIds){
					BaseMenu baseMenu = baseMenuService.selectOne(menuId);
					baseMenus.add(baseMenu);
				}

			}

			baseUser.setBaseRoles(baseRoles); // 保存当前用户角色列表
			baseUser.setBaseMenus(baseMenus); // 保存当前用户当前角色菜单列表

		}

		if(null== baseUser){
			// 用户不存在，直接返回null
			return null;
		}

		Account account = new Account();
		account.setId(baseUser.getId());
		account.setUserAccount(baseUser.getUserAccount());
		account.setUserName(baseUser.getUserName());
		account.setUserPassword(baseUser.getUserPassword());
		account.setUserSex(baseUser.getUserSex());
		account.setUserFace(baseUser.getUserFace());
		account.setUserEmail(baseUser.getUserEmail());
		account.setUserWechat(baseUser.getUserWechat());
		account.setUserPhone(baseUser.getUserPhone());
		account.setIsEnable(baseUser.getIsEnable()); // 账号是否有效
		account.setBaseRoles(baseUser.getBaseRoles()); // 角色集合
		account.setBaseMenus(baseUser.getBaseMenus()); // 菜单集合
		account.setExpiredTime(null); // 过期时间
		account.setLocked(false); // 账号是否锁定

		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority(KEY_PUBLIC_AUTHORITY));		// 添加公共资源码

		Map<Long, BaseMenu> treeMap = new LinkedHashMap<>();
		treeMap.put(0L,new BaseMenu());
		List<BaseMenu> baseMenus = account.getBaseMenus();
		if(null!= baseMenus && baseMenus.size()>0){
			for (BaseMenu baseMenu : baseMenus){
				authorityList.add(new SimpleGrantedAuthority(baseMenu.getMenuKey())); // MENU_KEY作为资源码
				treeMap.put(baseMenu.getId(),baseMenu);
			}
		}

		if (!treeMap.isEmpty()) {
			for (BaseMenu tree : treeMap.values()) {
				BaseMenu parent = treeMap.get(tree.getMenuParentId());
				if (parent != null) {
					parent.addChildMenu(tree);
				}
			}
		}

		account.setBaseMenus(treeMap.get(0L).getChildMenus()); // 设置树状菜单
		account.setAuthorityList(authorityList); // 设置权限码
		return account;
	}

}
