package com.zizhuling.boke;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by hebiao on 2018/2/8.
 */


@SpringBootApplication
//启注解事务管理
@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(basePackages = "com.zizhuling.boke.dao")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
