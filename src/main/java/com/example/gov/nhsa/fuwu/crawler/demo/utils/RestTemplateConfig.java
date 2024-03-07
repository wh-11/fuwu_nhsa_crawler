package com.example.gov.nhsa.fuwu.crawler.demo.utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @date: 2024/3/7
 */
@Component
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.setConnectTimeout(Duration.ofSeconds(20));
        builder.setReadTimeout(Duration.ofSeconds(30));
        return builder.build();
    }
}
