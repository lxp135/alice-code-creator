package alice.code.creator.common.util;

import alice.code.creator.domain.Account;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AccountUtils {

	public static Account getCurrentUser() {
		return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
