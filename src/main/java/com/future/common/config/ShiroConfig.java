package com.future.common.config;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.future.common.auth.shiro.ShiroRealm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class ShiroConfig {

    @Autowired
    private FrameworkConfig frameworkConfig;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
        @Qualifier("webSecurityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        // 设置不会被拦截的链接
        Map<String, String> map = new HashMap<>();
        if(frameworkConfig.getShiro() != null) {
            Set<String> excludeUrls = frameworkConfig.getShiro().getExcludeUrls();
            if(CollectionUtils.isNotEmpty(excludeUrls)) {
                for (String url : excludeUrls) {
                    map.put(url, "anon");
                }
            }
        }
        map.put("/api/login", "anon");
        map.put("/api/logout", "anon");
        map.put("/**/*.js.map", "anon");
        map.put("/**/*.css.map", "anon");


        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager webSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
        return manager;
    }

    @Bean("shiroRealm")
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

}
