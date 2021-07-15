package com.niici.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

@SpringBootApplication
@Slf4j
public class StudyApplication {
    public static void main(String[] args) {
        /*ConfigurableApplicationContext run = SpringApplication.run(StudyApplication.class, args);
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            log.info("------------------");
            log.info(beanDefinitionName);
        }*/
        SpringApplication.run(StudyApplication.class, args);
    }
}
