package com.zzy.piccenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author T041018
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = "com.zzy.piccenter.demos.web.infrastructure.mapper")
@EnableAsync
public class PicCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicCenterApplication.class, args);
    }

}
