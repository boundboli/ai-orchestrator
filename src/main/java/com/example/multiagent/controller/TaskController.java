package com.example.multiagent.controller;

import com.example.multiagent.model.AgentResponse;
import com.example.multiagent.model.dto.UserRequest;
import com.example.multiagent.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final OrchestratorService orchestratorService;

    @PostMapping
    public AgentResponse execute(@RequestBody UserRequest request) {
        return orchestratorService.execute(request.request());
    }
}