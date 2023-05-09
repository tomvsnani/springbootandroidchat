package com.example.androidchatmodule;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class AndroidChatModuleApplication {

    Logger logger = LoggerFactory.getLogger(AndroidChatModuleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AndroidChatModuleApplication.class, args);
    }


    @KafkaListener(topics = "test",groupId = "grouppp")

    public void listener(String s){
        logger.info("message received -> "+s);
    }

//    @KafkaListener(topics = "test", groupId = "group", topicPartitions = {@TopicPartition(topic = "test", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0"))})
//    public void listener(ConsumerRecord<String, String> record) {
//        logger.debug(record.value());
//
//    }

}
