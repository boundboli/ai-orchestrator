package com.example.multiagent.config;

import com.example.multiagent.agent.CodingAgent;
import com.example.multiagent.agent.ResearchAgent;
import com.example.multiagent.agent.ReviewAgent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiServiceConfig {

    @Bean
    public ResearchAgent researchAgent(
            @Qualifier("researchModel")
            ChatLanguageModel model
    ) {

        return AiServices.builder(ResearchAgent.class)
                .chatLanguageModel(model)
                .build();
    }

    @Bean
    public CodingAgent codingAgent(
            @Qualifier("codingModel")
            ChatLanguageModel model
    ) {

        return AiServices.builder(CodingAgent.class)
                .chatLanguageModel(model)
                .build();
    }

    @Bean
    public ReviewAgent reviewAgent(
            @Qualifier("reviewModel")
            ChatLanguageModel model
    ) {

        return AiServices.builder(ReviewAgent.class)
                .chatLanguageModel(model)
                .build();
    }
}