package com.serdyukov.agileengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImgCacheSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImgCacheSearchServiceApplication.class, args);
    }


}
