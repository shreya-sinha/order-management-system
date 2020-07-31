package com.example.order_service.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ItemErrorDecoder itemErrorDecoder() {
        return new ItemErrorDecoder();
    }
}
