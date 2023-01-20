package com.example.demo1.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
@Aspect
public class TimeTrackerAspect {

    private Long startTime;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Before("log()")
    public void beforeLog(){
        startTime = new Date().getTime();
        logger.info("Method started at: " + startTime);
    }

    @Pointcut(value = "within(com.example.demo1.controllers.MainController)")
    public void log(){}

    @After("log()")
    public void afterLog(){
        Long endTime = new Date().getTime();
        logger.info("Method ended at: " + endTime);
        logger.info("Execution time: " + (endTime-startTime));
    }
}
