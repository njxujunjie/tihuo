package com.hzysofti.saasAdmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.hzysofti.saasAdmin.mapper")
public class SaasAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaasAdminApplication.class, args);
    }

}
