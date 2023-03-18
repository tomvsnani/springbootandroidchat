package com.example.androidchatmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class AndroidChatModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidChatModuleApplication.class, args);
    }

}
