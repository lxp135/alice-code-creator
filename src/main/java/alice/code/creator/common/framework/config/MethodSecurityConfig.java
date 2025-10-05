package alice.code.creator.common.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * 访问权限配置类
 * @author contact@liuxp.me
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
    // 不需要额外配置，Spring Security 6 已经提供了完整的 @Secured 支持
}