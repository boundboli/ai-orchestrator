package com.example.multiagent.integration.tavily;

import com.example.multiagent.integration.tavily.dto.TavilyRequest;
import com.example.multiagent.integration.tavily.dto.TavilyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class TavilyClient {

    private final RestClient tavilyRestClient;

    @Value("${tavily.api-key}")
    private String apiKey;

    public TavilyResponse search(String query) {

        TavilyRequest request = new TavilyRequest(
                apiKey,
                query,
                5
        );

        return tavilyRestClient.post()
                .uri("/search")
                .body(request)
                .retrieve()
                .body(TavilyResponse.class);
    }
}