package com.example.multiagent.agent;

import com.example.multiagent.model.dto.ResearchResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ResearchAgent {

    @SystemMessage(fromResource = "prompts/research-system.txt")
    ResearchResult research(@UserMessage String request);

}