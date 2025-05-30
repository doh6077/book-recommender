package com.sheridancollege.book_recommendation_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    @Value("${openai.api.key}")
    String openaiApiKey;

    @Bean
    public RestTemplate restTemplate() {
        System.out.println("OpenAI API Key: " + openaiApiKey); // Debug print
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey); 
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}