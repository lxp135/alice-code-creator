package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;

/**
* 角色与菜单关系表
* @author contact@liuxp.me
*/
public class BaseRoleMenuRel extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	// 角色表主键
	private Long roleId;
	// 菜单表主键
	private Long menuId;

	/**
	 * 获取角色表主键
	 */
	public Long getRoleId(){
		return this.roleId;
	}
	/**
	 * 设置角色表主键
	 * @param roleId 角色表主键
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	/**
	 * 获取菜单表主键
	 */
	public Long getMenuId(){
		return this.menuId;
	}
	/**
	 * 设置菜单表主键
	 * @param menuId 菜单表主键
	 */
	public void setMenuId(Long menuId){
		this.menuId = menuId;
	}
}