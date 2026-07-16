package com.example.multiagent.model.dto;

import lombok.Builder;

@Builder
public record ReviewRequest(

        String userRequest,

        String generatedCode

) {
}