package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
* 角色与用户关系表
* @author contact@liuxp.me
*/
@Setter
@Getter
public class BaseRoleUserRel extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	// 角色表主键
	private Long roleId;
	// 用户表主键
	private Long userId;

}