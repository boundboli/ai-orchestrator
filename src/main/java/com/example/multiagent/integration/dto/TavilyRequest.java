package com.example.multiagent.integration.tavily.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TavilyRequest(

        @JsonProperty("api_key")
        String apiKey,

        String query,

        @JsonProperty("max_results")
        int maxResults

) {
}