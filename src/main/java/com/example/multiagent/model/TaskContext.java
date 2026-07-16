package com.example.multiagent.model;

import com.example.multiagent.model.dto.CodingResult;
import com.example.multiagent.model.dto.ResearchResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskContext {

    private String userRequest;

    private ResearchResult researchResult;

    private CodingResult codingResult;

    private ReviewResult reviewResult;

    @Builder.Default
    private List<ReviewResult> reviewHistory = new ArrayList<>();

    @Builder.Default
    private int iteration = 0;

    public boolean isApproved() {
        return reviewResult != null && reviewResult.isApproved();
    }

    public void addReview(ReviewResult review) {
        reviewHistory.add(review);
        reviewResult = review;
    }

    public void nextIteration() {
        iteration++;
    }
}