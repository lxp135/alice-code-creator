package alice.code.creator.common.framework.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import alice.code.creator.common.framework.interceptor.AccountExposingHandlerInterceptor;

import javax.servlet.MultipartConfigElement;
import java.util.Date;

/**
 * 描述：
 * 创建时间：2018/5/23 17:02
 *
 * @author contact@liuxp.me
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 增加字符串转日期的功能
     */
    @Bean
    public Converter<String, Date> addStringToDateConvert() {
        return new StringToDateConverter();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("32MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("128MB");
        return factory.createMultipartConfig();
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccountExposingHandlerInterceptor()).addPathPatterns("/", "/web/**");
    }
}