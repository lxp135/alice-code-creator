package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


/**
* 数据字典表
* @author contact@liuxp.me
*/
@Setter
@Getter
public class BaseDictionary extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	// 分组编码
	private String groupCode;
	// 分组名称
	private String groupName;
	// 字典编码
	private String dictCode;
	// 字典名称
	private String dictName;
	// 启停标志 0：正常、1：禁用
	private Integer isEnable;

}