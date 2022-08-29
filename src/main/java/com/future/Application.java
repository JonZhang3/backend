package com.future;

import com.future.common.jpa.BaseJpaRepositoryFactoryBean;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing // 开启审计功能
@EnableJpaRepositories(basePackages = "com.future", repositoryFactoryBeanClass = BaseJpaRepositoryFactoryBean.class)
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(Application.class)
            .bannerMode(Banner.Mode.OFF)
            .run(args);
    }

}
