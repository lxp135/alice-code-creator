package alice.code.creator.domain.model.base;
import alice.code.creator.domain.model.AbstractEntity;

/**
* 数据字典表
* @author contact@liuxp.me
*/
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

	/**
	 * 获取分组编码
	 */
	public String getGroupCode(){
		return this.groupCode;
	}
	/**
	 * 设置分组编码
	 * @param groupCode 分组编码
	 */
	public void setGroupCode(String groupCode){
		this.groupCode = groupCode;
	}
	/**
	 * 获取分组名称
	 */
	public String getGroupName(){
		return this.groupName;
	}
	/**
	 * 设置分组名称
	 * @param groupName 分组名称
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	/**
	 * 获取字典编码
	 */
	public String getDictCode(){
		return this.dictCode;
	}
	/**
	 * 设置字典编码
	 * @param dictCode 字典编码
	 */
	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}
	/**
	 * 获取字典名称
	 * @return 字典名称
	 */
	public String getDictName(){
		return this.dictName;
	}
	/**
	 * 设置字典名称
	 * @param dictName 字典名称
	 */
	public void setDictName(String dictName){
		this.dictName = dictName;
	}
	/**
	 * 获取启停标志 0：正常、1：禁用
	 */
	public Integer getIsEnable(){
		return this.isEnable;
	}
	/**
	 * 设置启停标志 0：正常、1：禁用
	 * @param isEnable 启停标志 0：正常、1：禁用
	 */
	public void setIsEnable(Integer isEnable){
		this.isEnable = isEnable;
	}
}