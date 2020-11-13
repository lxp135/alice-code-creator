package alice.code.creator.common.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import alice.code.creator.common.framework.account.Account;
import alice.code.creator.common.framework.security.DbAuthenticationProvider;
import alice.code.creator.common.framework.security.DbUserDetailsService;
import alice.code.creator.domain.model.monitor.MonitorLoginLog;
import alice.code.creator.service.monitor.MonitorLoginLogService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 描述：Spring Security 配置类
 * 创建时间：2018/5/22 16:55
 *
 * @author contact@liuxp.me
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String KEY = "WEBAPP";

    @Resource
    private MonitorLoginLogService monitorLoginLogService;

    @Resource
    private DbUserDetailsService userDetailService;

    @Resource
    private DbAuthenticationProvider provider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers("/login" // 登录
                        ,"/register" // 注册
                        ,"/sendRegisterCaptcha" // 发送注册验证码
                        ,"/sendForgotPasswordCaptcha" // 发送找回密码验证码
                        ,"/forgotPassword" // 找回密码
                        ,"/framework/**" // 框架接口
                        ,"/isLogin" // 是否已登录
                        ,"/assets/**").permitAll(); // 开放访问地址：登录、注册、静态资源文件

        httpSecurity.authorizeRequests().anyRequest().authenticated();

        // 自定义登录
        httpSecurity.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=1")
                .loginProcessingUrl("/j_spring_security_check")
                .permitAll();

        // 关闭csrf，解决ajax请求403问题
        httpSecurity.csrf().disable();

        // 自动登录
        httpSecurity.rememberMe().key(KEY);

        // 注销退出
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true);

        // 账号密码拦截器
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setPostOnly(true);
        filter.setFilterProcessesUrl("/j_spring_security_check");
        filter.setUsernameParameter("j_username");
        filter.setPasswordParameter("j_password");
        filter.setAuthenticationManager(authenticationManager());
        filter.setRememberMeServices(new TokenBasedRememberMeServices(KEY, userDetailService));
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=1"));
        filter.setAuthenticationSuccessHandler(new LoginAuthenticationSuccessHandler());
        // 过滤器替换
        httpSecurity.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class);

        // session失效后跳转到登录页面
        httpSecurity.sessionManagement().invalidSessionUrl("/login");
        // 只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
        httpSecurity.sessionManagement().maximumSessions(1).expiredUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 登录成功回调事件
     */
    class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            try {
                // 记录登录日志
                Account account = (Account) authentication.getPrincipal();
                Date now = new Date();
                MonitorLoginLog loginLog = new MonitorLoginLog();
                loginLog.setLoginApp(request.getHeader("User-agent")); // 登录设备信息
                loginLog.setLoginIp(request.getRemoteAddr()); // 登录IP
                loginLog.setLoginTime(now); // 登录时间
                loginLog.setUserId(account.getId()); // 登录用户编号
                loginLog.setUserName(account.getUserName()); // 登录用户名称
                loginLog.setUserAccount(account.getUserAccount()); // 登录用户账号
                monitorLoginLogService.insert(loginLog,account.getUserName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 登录成功默认进入首页
            response.sendRedirect("/");
        }

    }

}