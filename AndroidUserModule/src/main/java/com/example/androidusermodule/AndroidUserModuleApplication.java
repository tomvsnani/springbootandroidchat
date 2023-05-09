package com.example.androidusermodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableFeignClients
@PropertySource(value = "classpath:application.yml")
public class AndroidUserModuleApplication implements CommandLineRunner {

    @Autowired
    KafkaTemplate kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AndroidUserModuleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        kafkaTemplate.send("test","hello");

    }
}
