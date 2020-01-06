package com.fjx.test;/*
 @author Jason
 @DESCRIPTION 
 @create 2020-01-04
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.fjx.test.mapper")
public class BrandApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrandApplication.class,args);
    }
}
