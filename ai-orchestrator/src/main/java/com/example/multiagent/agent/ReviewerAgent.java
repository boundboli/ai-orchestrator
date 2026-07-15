package com.example.multiagent.agent;

import com.example.multiagent.model.ReviewResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ReviewAgent {

    @SystemMessage(fromResource = "prompts/review-system.txt")
    ReviewResult review(

            @UserMessage
            String code

    );

}