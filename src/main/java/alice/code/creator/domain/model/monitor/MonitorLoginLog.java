package alice.code.creator.domain.model.monitor;
import alice.code.creator.domain.model.AbstractEntity;

import java.util.Date;

/**
* 登录日志
* @author contact@liuxp.me
*/
public class MonitorLoginLog extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	// 用户唯一标识
	private Long userId;
	// 用户账号
	private String userAccount;
	// 用户名称
	private String userName;
	// 登录IP
	private String loginIp;
	// 登录应用
	private String loginApp;
	// 登录时间
	private Date loginTime;

	/**
	 * 获取用户唯一标识
	 */
	public Long getUserId(){
		return this.userId;
	}
	/**
	 * 设置用户唯一标识
	 * @param userId 用户唯一标识
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	/**
	 * 获取用户账号
	 */
	public String getUserAccount(){
		return this.userAccount;
	}
	/**
	 * 设置用户账号
	 * @param userAccount 用户账号
	 */
	public void setUserAccount(String userAccount){
		this.userAccount = userAccount;
	}
	/**
	 * 获取用户名称
	 */
	public String getUserName(){
		return this.userName;
	}
	/**
	 * 设置用户名称
	 * @param userName 用户名称
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	 * 获取登录IP
	 */
	public String getLoginIp(){
		return this.loginIp;
	}
	/**
	 * 设置登录IP
	 * @param loginIp 登录IP
	 */
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	/**
	 * 获取登录应用
	 */
	public String getLoginApp(){
		return this.loginApp;
	}
	/**
	 * 设置登录应用
	 * @param loginApp 登录应用
	 */
	public void setLoginApp(String loginApp){
		this.loginApp = loginApp;
	}
	/**
	 * 获取登录时间
	 */
	public Date getLoginTime(){
		return this.loginTime;
	}
	/**
	 * 设置登录时间
	 * @param loginTime 登录时间
	 */
	public void setLoginTime(Date loginTime){
		this.loginTime = loginTime;
	}
}