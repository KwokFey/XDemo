package config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dgf
 */

@EnableWebMvc
@Configuration
@ComponentScan
@EnableCaching
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter{
}
