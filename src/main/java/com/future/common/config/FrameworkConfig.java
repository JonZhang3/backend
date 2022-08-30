package com.future.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Set;

@Data
@Configuration
@ConfigurationProperties(prefix = "framework")
public class FrameworkConfig {

    /**
     * 设置是否启用跨域配置
     */
    private boolean cors = false;

    private Shiro shiro;

    private Jwt jwt = new Jwt();

    @Data
    public static class Shiro {

        /**
         * Shiro 权限校验中需要排除的 url
         */
        private Set<String> excludeUrls;

    }

    @Data
    public static class Jwt {

        /**
         * 有效时间
         */
        private Duration duration = Duration.ofMinutes(30);

        /**
         * 密钥
         */
        private String secret = "future";

    }

}
