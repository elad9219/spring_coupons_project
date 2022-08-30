package com.jb.spring_coupons_project.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class MyRestTemplate {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                //set connections timeout for 3 seconds.
                .setConnectTimeout(Duration.ofMillis(3_000))
                //set read timeout for 3 seconds.
                .setReadTimeout(Duration.ofMillis(3_000))
                .build();
    }
}
