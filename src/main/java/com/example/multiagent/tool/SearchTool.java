package com.example.multiagent.tool;

import com.example.multiagent.integration.tavily.TavilyClient;
import com.example.multiagent.integration.tavily.dto.TavilyResponse;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchTool {

    private static final int MAX_CONTENT_LENGTH = 1200;

    private final TavilyClient tavilyClient;

    @Tool("""
            Search the Internet for recent technical information.

            Use this tool whenever you need:
            - framework versions
            - official documentation
            - API references
            - release notes
            - best practices
            - recent changes
            - library documentation

            Return only the most relevant information.
            """)
    public String search(String query) {

        log.info("Searching Tavily for: {}", query);

        TavilyResponse response = tavilyClient.search(query);

        if (response == null || response.results() == null || response.results().isEmpty()) {
            log.warn("No results found for query: {}", query);
            return "No relevant information found.";
        }

        log.info("Found {} search results", response.results().size());

        return formatResponse(response);
    }

    private String formatResponse(TavilyResponse response) {

        StringBuilder builder = new StringBuilder();

        for (var result : response.results()) {

            builder.append("## ")
                    .append(result.title())
                    .append("\n\n");

            builder.append("Source: ")
                    .append(result.url())
                    .append("\n\n");

            builder.append(truncate(result.content()))
                    .append("\n\n");

            builder.append("---\n\n");
        }

        return builder.toString();
    }

    private String truncate(String text) {

        if (text == null) {
            return "";
        }

        if (text.length() <= MAX_CONTENT_LENGTH) {
            return text;
        }

        return text.substring(0, MAX_CONTENT_LENGTH) + "...";
    }
}