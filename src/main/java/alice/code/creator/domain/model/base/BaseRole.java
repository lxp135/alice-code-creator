package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;

/**
* 角色基本信息表
* @author contact@liuxp.me
*/
public class BaseRole extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	// 角色名称
	private String roleName;
	// 角色描述
	private String roleDescription;
	// 状态 0:正常 1:禁用
	private Integer isEnable;
	// 是否选中状态，分配角色弹出页面使用
	private Boolean isCheck;

	/**
	 * 获取角色名称
	 */
	public String getRoleName(){
		return this.roleName;
	}
	/**
	 * 设置角色名称
	 * @param roleName 角色名称
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	/**
	 * 获取角色描述
	 */
	public String getRoleDescription(){
		return this.roleDescription;
	}
	/**
	 * 设置角色描述
	 * @param roleDescription 角色描述
	 */
	public void setRoleDescription(String roleDescription){
		this.roleDescription = roleDescription;
	}
	/**
	 * 获取状态 0:正常 1:禁用
	 */
	public Integer getIsEnable(){
		return this.isEnable;
	}
	/**
	 * 设置状态 0:正常 1:禁用
	 * @param isEnable 状态 0:正常 1:禁用
	 */
	public void setIsEnable(Integer isEnable){
		this.isEnable = isEnable;
	}

	public Boolean getIsCheck() {
		return this.isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}
}