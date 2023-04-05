//package com.example.demo1.config;
//
//import com.example.demo1.dto.ConsumerChannels;
//import com.example.demo1.dto.GreetingMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.context.annotation.Configuration;
//
//
//
//@Configuration
//public class MessageListenerConfig {
//    private Logger logger = LoggerFactory.getLogger(MessageListenerConfig.class);
//
//    @StreamListener(ConsumerChannels.DIRECTED)
//    public void directed(GreetingMessage message) {
//        logger.debug("Directed: " + message);
//    }
//
//    @StreamListener(ConsumerChannels.BROADCASTS)
//    public void broadcasted(String message) {
//        logger.debug("Directed: " + message);
//    }
//}
