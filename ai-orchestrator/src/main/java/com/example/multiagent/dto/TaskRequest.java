package com.example.multiagent.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank(message = "Request cannot be empty")
    private String request;

}