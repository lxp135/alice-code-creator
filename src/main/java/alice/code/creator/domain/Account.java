package alice.code.creator.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import alice.code.creator.domain.model.AbstractEntity;
import alice.code.creator.domain.model.base.BaseMenu;
import alice.code.creator.domain.model.base.BaseRole;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
* 登录用户
* @author contact@liuxp.me
*/
public class Account extends AbstractEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	// 用户账号
	private String userAccount;
	// 用户名称
	private String userName;
	// 用户密码
	private String userPassword;
	// 性别 0:未填写 1:男 2:女
	private Integer userSex;
	// 用户头像
	private String userFace;
	// 电子邮件
	private String userEmail;
	// 微信号
	private String userWechat;
	// 手机号
	private String userPhone;
	// 用户状态 0:正常 1:禁用
	private Integer isEnable;

	private List<BaseRole> baseRoles;

	private List<BaseMenu> baseMenus;

	private boolean locked = false;
	private Date expiredTime;
	private List<GrantedAuthority> authorityList;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorityList;
	}

	@Override
	public String getPassword() {
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userAccount;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if (expiredTime == null)
			return true;
		Date now = new Date();
		return now.before(expiredTime);
	}

	@Override
	public boolean isEnabled() {
		return isEnable == 1;
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
	 * 获取用户密码
	 */
	public String getUserPassword(){
		return this.userPassword;
	}
	/**
	 * 设置用户密码
	 * @param userPassword 用户密码
	 */
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	/**
	 * 获取性别 0:未填写 1:男 2:女
	 */
	public Integer getUserSex(){
		return this.userSex;
	}
	/**
	 * 设置性别 0:未填写 1:男 2:女
	 * @param userSex 性别 0:未填写 1:男 2:女
	 */
	public void setUserSex(Integer userSex){
		this.userSex = userSex;
	}
	/**
	 * 获取用户头像
	 */
	public String getUserFace(){
		return this.userFace;
	}
	/**
	 * 设置用户头像
	 * @param userFace 用户头像
	 */
	public void setUserFace(String userFace){
		this.userFace = userFace;
	}
	/**
	 * 获取电子邮件
	 */
	public String getUserEmail(){
		return this.userEmail;
	}
	/**
	 * 设置电子邮件
	 * @param userEmail 电子邮件
	 */
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}
	/**
	 * 获取微信号
	 */
	public String getUserWechat(){
		return this.userWechat;
	}
	/**
	 * 设置微信号
	 * @param userWechat 微信号
	 */
	public void setUserWechat(String userWechat){
		this.userWechat = userWechat;
	}
	/**
	 * 获取手机号
	 */
	public String getUserPhone(){
		return this.userPhone;
	}
	/**
	 * 设置手机号
	 * @param userPhone 手机号
	 */
	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}
	/**
	 * 获取用户状态 0:正常 1:禁用
	 */
	public Integer getIsEnable(){
		return this.isEnable;
	}
	/**
	 * 设置用户状态 0:正常 1:禁用
	 * @param isEnable 用户状态 0:正常 1:禁用
	 */
	public void setIsEnable(Integer isEnable){
		this.isEnable = isEnable;
	}

	public List<BaseRole> getBaseRoles() {
		return baseRoles;
	}

	public void setBaseRoles(List<BaseRole> baseRoles) {
		this.baseRoles = baseRoles;
	}

	public List<BaseMenu> getBaseMenus() {
		return baseMenus;
	}

	public void setBaseMenus(List<BaseMenu> baseMenus) {
		this.baseMenus = baseMenus;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public List<GrantedAuthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<GrantedAuthority> authorityList) {
		this.authorityList = authorityList;
	}
}