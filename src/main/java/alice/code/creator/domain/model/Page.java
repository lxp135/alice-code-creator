package alice.code.creator.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 分页通用实体类
 * @param <T>
 * @author contact@liuxp.me
 */
public final class Page<T> implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	// 页码，从0开始
	private final int pageIndex;
	// 每页条数
	private final int pageSize;
	// 总页数
	private final long total;
	// 记录总数
	private final long records;
	// 排序列表
	private final List<Sort> sorts = new ArrayList<>();
	// 分页结果集
	private final List<T> content = new ArrayList<>();
	// 查询参数
	private T params;
	
	/**
	 * 构造函数
	 */
	public Page(){
		this.pageIndex = 0;
		this.pageSize = 0;
		this.total = 0;
		this.records = 0;
	}

	/**
	 * 业务代码中使用的构造函数
	 * @param pageIndex 页码，从0开始
	 * @param pageSize 每页条数
	 * @param sorts 排序
	 */
	public Page(int pageIndex, int pageSize, Sort... sorts) {
		this(null,null, 0L, pageIndex, pageSize, sorts);
	}

	/**
	 * 业务代码中使用的构造函数
	 * @param pageIndex 页码，从0开始
	 * @param pageSize 每页条数
	 * @param sorts 排序
	 */
	public Page(T params,int pageIndex, int pageSize, Sort... sorts) {
		this(params,null, 0L, pageIndex, pageSize, sorts);
	}

	/**
	 * 业务代码中使用的构造函数
	 * @param content 分页结果集
	 * @param records 记录总数
	 * @param pageIndex 页码，从0开始
	 * @param pageSize 每页条数
	 * @param sorts 排序
	 */
	public Page(T params, List<T> content, long records, int pageIndex, int pageSize, Sort... sorts) {
		// 查询参数
		this.params = params;
		// 结果集
		if (content != null && content.size() > 0) {
			this.content.addAll(content);
		}
		// 记录总数
		this.total = (int) Math.ceil((double)records / (double)pageSize);;
		this.records = records;
		// 分页
		if (pageIndex < 0) {
			throw new IllegalArgumentException("参数pageIndex不能小于0");
		}
		if (pageSize < 1) {
			throw new IllegalArgumentException("参数pageSize不能小于1");
		}
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		// 排序
		if (sorts != null && sorts.length > 0) {
			this.sorts.addAll(Arrays.asList(sorts));
		}
	}

	/**
	 * 获取当前页码，从0开始
	 * 
	 * @return 当前页码
	 */
	public int getPageIndex() {
		return this.pageIndex;
	}

	/**
	 * 获取当前页码，从1开始
	 * @return 当前页码
	 */
	public int getPageNum(){
		return this.pageIndex + 1;
	}
	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 获取排序列表
	 * 
	 * @return 排序列表
	 */
	public List<Sort> getSorts() {
		return sorts;
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public long getTotal() {
		return this.total;
	}

	/**
	 * 获取分页结果集
	 * 
	 * @return 分页结果集
	 */
	public List<T> getContent() {
		return Collections.unmodifiableList(this.content);
	}

	/**
	 * 获取记录总数
	 *
	 * @return 记录总数
	 */
	public long getRecords() {
		return records;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}
}
