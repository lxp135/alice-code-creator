package alice.code.creator.common.framework.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import alice.code.creator.domain.Account;
import alice.code.creator.common.framework.context.AccountContext;
import alice.code.creator.common.util.AccountUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 将当前登录用户信息放置到request中，以便视图中直接使用
 */
public class AccountExposingHandlerInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		try{
			Account account = AccountUtils.getCurrentUser();
			AccountContext.setAccount(account);
		}catch (Exception e){
			logger.error("登录用户取得失败",e);
		}

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){ }

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){ }
}