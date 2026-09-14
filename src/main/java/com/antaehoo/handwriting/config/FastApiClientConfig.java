package com.antaehoo.handwriting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FastApiClientConfig {

    @Value("${fastapi.base-url}")
    private String fastApiBaseUrl;

    @Bean
    public RestClient fastApiRestClient() {
        return RestClient.builder()
                .baseUrl(fastApiBaseUrl)
                .build();
    }
}