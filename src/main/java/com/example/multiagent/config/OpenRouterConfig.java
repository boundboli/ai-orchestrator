package com.example.multiagent.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenRouterConfig {

    @Value("${openrouter.api-key}")
    private String apiKey;

    @Value("${openrouter.base-url}")
    private String baseUrl;

    @Value("${models.research}")
    private String researchModel;

    @Value("${models.coding}")
    private String codingModel;

    @Value("${models.review}")
    private String reviewModel;


    @Bean(name = "researchModel")
    public ChatModel researchModel() {

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(researchModel)
                .temperature(0.2)
                .build();
    }


    @Bean(name = "codingModel")
    public ChatModel codingModel() {

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(codingModel)
                .temperature(0.1)
                .maxTokens(16000)
                .build();
    }


    @Bean(name = "reviewModel")
    public ChatModel reviewModel() {

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(reviewModel)
                .temperature(0.0)
                .build();
    }
}