package com.example.multiagent.model.dto;

import com.example.multiagent.model.ReviewResult;
import lombok.Builder;

@Builder
public record CodingRequest(

        String userRequest,

        String researchReport,

        String previousCode,

        ReviewResult reviewResult

) {
}