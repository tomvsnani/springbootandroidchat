package com.example.androidusermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource(value = "classpath:application.yml")
public class AndroidUserModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidUserModuleApplication.class, args);
    }

}
