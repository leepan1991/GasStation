package com.volunteer.gasstation.configuration;

import com.volunteer.gasstation.core.interceptor.ApiAuthInterceptor;
import com.volunteer.gasstation.core.interceptor.MgrAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huoyao
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MgrAuthInterceptor mgrAuthInterceptor() {
        return new MgrAuthInterceptor();
    }
    @Bean
    public ApiAuthInterceptor apiAuthInterceptor() {
        return new ApiAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mgrAuthInterceptor())
                .addPathPatterns("/mgr/**")
                .excludePathPatterns(
                        "/mgr/auth/login"
                );
        registry.addInterceptor(apiAuthInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/auth/login",
                        "/api/portal"
                );
    }
}