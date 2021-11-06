package io.gig.catchreview.admin.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Jake
 * @date : 2021-08-21
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class AdminConfiguration implements ApplicationContextAware, WebMvcConfigurer {

    private ApplicationContext applicationContext;
    private final AdminInterceptor adminInterceptor;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/error", "/login", "/logout");
    }

}
