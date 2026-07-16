package com.example.multiagent.integration.tavily.dto;

public record TavilyResult(

        String title,

        String url,

        String content,

        Double score

) {
}