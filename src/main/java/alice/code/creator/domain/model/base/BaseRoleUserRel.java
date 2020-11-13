package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;

/**
* 角色与用户关系表
* @author contact@liuxp.me
*/
public class BaseRoleUserRel extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	// 角色表主键
	private Long roleId;
	// 用户表主键
	private Long userId;

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
	 * 获取用户表主键
	 */
	public Long getUserId(){
		return this.userId;
	}
	/**
	 * 设置用户表主键
	 * @param userId 用户表主键
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
}