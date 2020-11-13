package alice.code.creator.domain.model;


import alice.code.creator.domain.constant.ResultCodeConstant;

import java.io.Serializable;

/**
 * 接口返回值包装类
 * @author contact@liuxp.me
 */
public class Result<T> implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	// 是否成功
	private final boolean success;
	// 返回结果
	private final T content;
	// 错误码
	private final int code;
	// 错误消息
	private final String message;

	/**
	 * 无参构造器
	 */
	public Result(){
		this.success = true;
		this.content = null;
		this.code = ResultCodeConstant.SUCCESS;
		this.message = "执行成功";
	}

	/**
	 * 成功返回构造
	 * @param content 返回结果
	 */
	public Result(T content){
		this.success = true;
		this.content = content;
		this.code = ResultCodeConstant.SUCCESS;
		this.message = "执行成功";
	}

	/**
	 * 失败返回构造
	 * @param code 错误码
	 * @param message 错误信息
	 */
	public Result(int code, String message){
		this.success = false;
		this.content = null;
		this.code = code;
		this.message = message;
	}

	/**
	 * 自定义返回构造
	 * @param success 结果集状态
	 * @param content 返回结果
	 * @param code 错误码，默认为0
	 * @param message 错误信息
	 */
	public Result(boolean success, int code, T content, String message){
		this.success = success;
		this.content = content;
		this.code = code;
		this.message = message;
	}

	/**
	 * 是否执行成功
	 * @return 是否成功
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 取得执行结果
	 * @return 执行结果
	 */
	public T getContent() {
		return content;
	}

	/**
	 * 取得错误码
	 * @return 错误码
	 */
	public int getCode() {
		return code;
	}



	/**
	 * 取得错误信息
	 * @return 错误信息
	 */
	public String getMessage() {
		return message;
	}
	
}
