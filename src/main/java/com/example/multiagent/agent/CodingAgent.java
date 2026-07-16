package com.example.multiagent.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface CodingAgent {

    @SystemMessage(fromResource = "prompts/coder-system.txt")
    CodingResult generate(@UserMessage CodingRequest request);

}