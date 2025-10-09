package alice.code.creator.common.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import alice.code.creator.domain.Account;

/**
 * 登录鉴权
 * @author contact@liuxp.me
 */
@Component
public class DbAuthenticationProvider implements AuthenticationProvider {

	// 用户信息与权限读取
	@Autowired
	private DbUserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

	/**
	 * 鉴权
	 * @param authentication 用户
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName(); // 登录账号
		String password = (String) authentication.getCredentials(); // 登录密码
		Account account = userDetailService.loadUserByUsername(username);
		if (account == null) {
			// 用户不存在
			throw new BadCredentialsException(String.format("该用户不存在 %s", username));
		} else if (!account.isEnabled()) {
			// 用户已被禁用
			throw new BadCredentialsException("该用户已被禁用，禁止访问");
		} else {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String passwordEncrypt = bCryptPasswordEncoder.encode(password);
            if (passwordEncoder.matches(password, account.getUserPassword())) {
				// 密码正确
				return new UsernamePasswordAuthenticationToken(account, authentication.getCredentials(), account.getAuthorities());
			}
			// 密码错误
			throw new BadCredentialsException(String.format("密码错误  %s", username));
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
