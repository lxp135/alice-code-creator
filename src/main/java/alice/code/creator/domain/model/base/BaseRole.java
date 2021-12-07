package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
* 角色基本信息表
* @author contact@liuxp.me
*/
@Setter
@Getter
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

}