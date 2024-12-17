package com.zzy.piccenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author T041018
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class PicCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicCenterApplication.class, args);
    }

}
