package alice.code.creator.domain.model;

import java.io.Serializable;

/**
 * 排序实体类
 * @author contact@liuxp.me
 */
public final class Sort implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	// 属性值
	private final String propertyName;

	// 是否升序
	private final boolean ascending;
	
	/**
	 * 供JSF反射使用的构造函数
	 */
	public Sort(){
		this.propertyName = "id";
		this.ascending = false;
	}

	// 私有构造函数
	private Sort(String propertyName, boolean ascending) {
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	/**
	 * 升序
	 * 
	 * @param propertyName 属性值
	 * @return 排序实例
	 */
	public static Sort asc(String propertyName) {
		return new Sort(propertyName, true);
	}

	/**
	 * 降序
	 * 
	 * @param propertyName 属性值
	 * @return 排序实例
	 */
	public static Sort desc(String propertyName) {
		return new Sort(propertyName, false);
	}

	/**
	 * 获取属性值
	 * 
	 * @return 属性值
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 获取是否升序
	 * 
	 * @return 是否升序
	 */
	public boolean isAscending() {
		return ascending;
	}
}
