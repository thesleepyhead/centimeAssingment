package com.example.service1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrchestratorConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
