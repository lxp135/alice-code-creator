package alice.code.creator.domain;

import lombok.Getter;
import lombok.Setter;
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
@Setter
@Getter
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

	public boolean isLocked() {
		return locked;
	}

	/**
	 * 获取用户名称
	 */
	public String getUserName(){
		return this.userName;
	}

}