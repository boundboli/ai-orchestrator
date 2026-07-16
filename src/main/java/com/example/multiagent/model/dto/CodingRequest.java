package com.example.multiagent.model;

import lombok.Builder;

@Builder
public record CodingRequest(

        String userRequest,

        String researchReport,

        String previousCode,

        ReviewResult reviewResult

) {
}