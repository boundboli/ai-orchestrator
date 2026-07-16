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
            @Qualifier("researchModel") ChatLanguageModel model,
            SearchTool searchTool
    ) {

        return AiServices.builder(ResearchAgent.class)
                .chatLanguageModel(model)
                .tools(searchTool)
                .build();
    }

    @Bean
    public CodingAgent codingAgent(
            @Qualifier("codingModel") ChatLanguageModel model
    ) {

        return AiServices.builder(CodingAgent.class)
                .chatLanguageModel(model)
                .build();
    }

    @Bean
    public ReviewerAgent reviewerAgent(
            @Qualifier("reviewModel") ChatLanguageModel model
    ) {

        return AiServices.builder(ReviewerAgent.class)
                .chatLanguageModel(model)
                .build();
    }
}