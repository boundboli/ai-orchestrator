package com.example.multiagent.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResult {

    private boolean approved;

    private int score;

    private List<String> feedback;

    private String summary;


}