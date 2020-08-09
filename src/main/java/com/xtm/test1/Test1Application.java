package com.xtm.test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.xtm.test1.mapper")
//@ComponentScan(basePackages  = "com.xtm")
//@EnableScheduling
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class Test1Application {

    public static void main(String[] args) {
        SpringApplication.run(Test1Application.class, args);
    }

}
