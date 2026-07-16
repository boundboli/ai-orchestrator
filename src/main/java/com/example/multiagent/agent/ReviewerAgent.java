package com.example.multiagent.agent;

import com.example.multiagent.model.ReviewResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ReviewerAgent {

    @SystemMessage(fromResource = "prompts/reviewer-system.txt")
    ReviewResult review(@UserMessage ReviewRequest request);

}