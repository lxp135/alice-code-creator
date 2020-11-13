package alice.code.creator.common.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 访问权限配置类
 * @author contact@liuxp.me
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        ExpressionBasedPreInvocationAdvice expressionAdvice = new ExpressionBasedPreInvocationAdvice();
        expressionAdvice.setExpressionHandler(getExpressionHandler());
        decisionVoters.add(new AccessDecisionVoter<Object>() {
            public boolean supports(ConfigAttribute attribute) {
                return attribute.getAttribute() != null;
            }

            public boolean supports(Class<?> clazz) {
                return true;
            }

            public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
                if (authentication == null) {
                    return ACCESS_DENIED;
                }
                int result = ACCESS_ABSTAIN;
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (ConfigAttribute attribute : attributes) {
                    if (this.supports(attribute)) {
                        result = ACCESS_DENIED;
                        for (GrantedAuthority authority : authorities) {
                            if (attribute.getAttribute().equalsIgnoreCase(authority.getAuthority())) {
                                return ACCESS_GRANTED;
                            }
                        }
                    }
                }
                return result;
            }
        });
        return new AffirmativeBased(decisionVoters);
    }
}