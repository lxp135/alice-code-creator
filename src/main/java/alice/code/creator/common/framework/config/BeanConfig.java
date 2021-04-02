package alice.code.creator.common.framework.config;

import alice.code.creator.service.generator.DataSourceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig implements InitializingBean, ApplicationContextAware{

    private ApplicationContext applicationContext;

    public DataSourceService getSourceService(String beanName){
        return applicationContext.getBean(beanName,DataSourceService.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
