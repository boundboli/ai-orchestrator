package com.example.multiagent.tool;

import com.example.multiagent.integration.tavily.TavilyClient;
import com.example.multiagent.integration.tavily.dto.TavilyResponse;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchTool {

    private final TavilyClient tavilyClient;

    @Tool("""
            Search the Internet for recent technical information.
            
            Use this tool when you need:
            - latest framework versions
            - official documentation
            - API references
            - best practices
            - recent changes
            - library documentation
            
            Return only relevant information.
            """)
    public String search(String query) {

        TavilyResponse response = tavilyClient.search(query);

        return formatResponse(response);
    }

    private String formatResponse(TavilyResponse response) {

        StringBuilder builder = new StringBuilder();

        response.results().forEach(result -> {

            builder.append("Title: ")
                    .append(result.title())
                    .append("\n");

            builder.append("URL: ")
                    .append(result.url())
                    .append("\n");

            builder.append("Content:\n")
                    .append(result.content())
                    .append("\n\n");

        });

        return builder.toString();
    }

}