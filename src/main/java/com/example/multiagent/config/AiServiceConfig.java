package com.example.multiagent.config;

import com.example.multiagent.tool.SearchTool;
import com.example.multiagent.agent.CodingAgent;
import com.example.multiagent.agent.ResearchAgent;
import com.example.multiagent.agent.ReviewerAgent;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiServiceConfig {

    @Bean
    public ResearchAgent researchAgent(
            @Qualifier("researchModel") ChatModel model,
            SearchTool searchTool
    ) {

        return AiServices.builder(ResearchAgent.class)
                .chatModel(model)
                .tools(searchTool)
                .build();
    }

    @Bean
    public CodingAgent codingAgent(
            @Qualifier("codingModel") ChatModel model
    ) {

        return AiServices.builder(CodingAgent.class)
                .chatModel(model)
                .build();
    }

    @Bean
    public ReviewerAgent reviewerAgent(
            @Qualifier("reviewModel") ChatModel model
    ) {

        return AiServices.builder(ReviewerAgent.class)
                .chatModel(model)
                .build();
    }
}