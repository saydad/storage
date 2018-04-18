package self.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * 显示引用hibernate的sessionFactoryBean实例
 * @author lyongy.liu on 下午 6:01.
 */
@Configuration
public class HibernateConfig {

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
