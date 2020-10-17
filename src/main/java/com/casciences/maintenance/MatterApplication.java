package com.casciences.maintenance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.casciences.maintenance.dao")
public class MatterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatterApplication.class, args);
    }

}