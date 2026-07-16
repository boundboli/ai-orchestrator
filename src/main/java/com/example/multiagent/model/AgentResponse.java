package com.example.multiagent.model;

import lombok.Builder;

@Builder
public record AgentResponse(

        String result,

        boolean approved,

        int iterations

) {
}