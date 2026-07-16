package com.example.multiagent.model;

import lombok.Builder;

@Builder
public record ReviewRequest(

        String userRequest,

        String generatedCode

) {
}