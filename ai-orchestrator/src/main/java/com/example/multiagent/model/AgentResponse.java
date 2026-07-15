package com.example.multiagent.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class AgentResponse {


    private String status;


    private int iterations;


    private String result;


}