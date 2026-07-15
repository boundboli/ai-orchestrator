package com.example.multiagent.controller;

import com.example.multiagent.dto.TaskRequest;
import com.example.multiagent.model.AgentResponse;
import com.example.multiagent.service.OrchestratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final OrchestratorService orchestratorService;

    @PostMapping
    public AgentResponse execute(

            @Valid
            @RequestBody
            TaskRequest request

    ) {

        return orchestratorService.execute(
                request.getRequest()
        );
    }

}