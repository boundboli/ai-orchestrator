package com.example.multiagent.integration.tavily;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TavilyConfig {

    @Bean
    public RestClient tavilyRestClient(
            @Value("${tavily.base-url}") String baseUrl
    ) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}