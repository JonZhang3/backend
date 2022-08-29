package com.future.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 在这里添加自定义的应用配置
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
}
