package com.future.config;

import com.future.common.config.FrameworkConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;

@Configuration
public class WebAppConfig implements WebMvcConfigurer, WebMvcRegistrations {

    @Resource(type = FrameworkConfig.class)
    private FrameworkConfig frameworkConfig;

    /**
     * 在这里添加过滤器
     */
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {

    }

    /**
     * 在这里添加跨域的相关配置
     */
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        if (frameworkConfig.isCors()) {
            registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*");
        }
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
    }

}
