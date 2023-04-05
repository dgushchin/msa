package com.example.demo1.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaListeners {

    private Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = "demo", groupId = "myId")
    public void listener(String data){
        logger.info("Directed: " + data);
    }
}
