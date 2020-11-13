package alice.code.creator.common.framework.account;

/**
 * 应用上下文
 */
public final class AccountContext {

	private final static ThreadLocal<Account> ACCOUNT_HOLDER = new ThreadLocal<Account>();

    /**
     * 设置账户信息
	 * @param account 账户信息
	 */
	public static void setAccount(Account account) {
		ACCOUNT_HOLDER.set(account);
    }

    /**
     * 取得账户信息
	 * @return 账户信息
	 */
	public static Account getAccount(){
		return ACCOUNT_HOLDER.get();
	}
}
