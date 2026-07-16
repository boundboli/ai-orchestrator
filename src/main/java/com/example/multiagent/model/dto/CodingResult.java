package com.example.multiagent.model;

import lombok.Builder;

@Builder
public record CodingResult(

        String code,

        String explanation

) {
}