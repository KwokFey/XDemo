package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * @author dgf
 */

@Configuration
@PropertySource(value = {"classpath:/application-druid.properties",
                        "classpath:/application-redis.properties",
                        "classpath:/application-hibernate.properties"})
@ComponentScan(basePackages = {},excludeFilters ={
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)} )
public class RootConfig {

    @Autowired
    private Environment env;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(env.getProperty("redis.pool.maxIdle")));
        config.setMaxTotal(Integer.valueOf(env.getProperty("redis.pool.maxTotal")));
        return config;
    }

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("driver"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sfb=new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource());
        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql"));
        sfb.setHibernateProperties(properties);
        return sfb;
    }
}
