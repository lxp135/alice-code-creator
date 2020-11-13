package alice.code.creator.common.framework.account;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AccountUtils {

	public static Account getCurrentUser() {
		return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
