package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class IntelligentLearningSystemManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentLearningSystemManagementApplication.class, args);
    }

}
