package com.example.multiagent.service;

import com.example.multiagent.model.AgentResponse;

public interface OrchestratorService {

    AgentResponse execute(String userRequest);

}