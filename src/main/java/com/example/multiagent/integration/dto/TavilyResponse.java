package com.example.multiagent.integration.tavily.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TavilyResponse(

        String query,

        String answer,

        List<TavilyResult> results,

        @JsonProperty("response_time")
        Double responseTime

) {
}