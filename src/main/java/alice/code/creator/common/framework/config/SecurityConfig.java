package alice.code.creator.common.framework.config;

import alice.code.creator.common.framework.security.DbAuthenticationProvider;
import alice.code.creator.common.framework.security.DbUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 配置类
 * @author contact@liuxp.me
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final static String KEY = "WEBAPP";

    @Autowired
    private DbUserDetailsService userDetailService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // 关闭csrf，解决ajax请求403问题
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/login" // 登录
                ,"/login.html" // 静态登录页
                ,"/register" // 注册
                ,"/sendForgotPasswordCaptcha" // 发送找回密码验证码
                ,"/forgotPassword" // 找回密码
                ,"/framework/**" // 框架接口
                ,"/isLogin" // 是否已登录
                ,"/assets/**"
            ).permitAll().anyRequest().authenticated()
            ).formLogin(form -> form
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .loginPage("/login") // 自定义登录
                .failureUrl("/login?error=1")
                .successForwardUrl("/")
                .permitAll()
            ).logout(logout -> logout
                .logoutUrl("/logout") // 注销退出
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
            ).rememberMe(remember -> remember
                .tokenValiditySeconds(86400) // 自动登录
                .key(KEY)
                .userDetailsService(userDetailService)
        );

        return http.build();
    }

    // 配置 AuthenticationManager 和认证提供者
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                      DbAuthenticationProvider dbAuthenticationProvider,
                                                      PasswordEncoder passwordEncoder) {
        try {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class);

            authenticationManagerBuilder
                    .authenticationProvider(dbAuthenticationProvider)
                    .userDetailsService(userDetailService)
                    .passwordEncoder(passwordEncoder);

            return authenticationManagerBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException("AuthenticationManager 配置失败", e);
        }
    }

    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}