package com.example.multiagent.service;

import com.example.multiagent.agent.CodingAgent;
import com.example.multiagent.agent.ResearchAgent;
import com.example.multiagent.agent.ReviewerAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrchestratorService {

    private static final int MAX_ITERATIONS = 3;

    private final ResearchAgent researchAgent;
    private final CodingAgent codingAgent;
    private final ReviewerAgent reviewerAgent;

    public CodingResult execute(String userRequest) {

        TaskContext context = TaskContext.builder()
                .userRequest(userRequest)
                .build();

        ResearchResult researchResult = researchAgent.research(userRequest);
        context.setResearchResult(researchResult);


        CodingResult codingResult = codingAgent.generate(
                CodingRequest.builder()
                        .userRequest(userRequest)
                        .researchReport(researchResult.report())
                        .build()
        );

        context.setCodingResult(codingResult);

        while (context.getIteration() < MAX_ITERATIONS) {

            ReviewResult review = reviewerAgent.review(
                    ReviewRequest.builder()
                            .userRequest(userRequest)
                            .generatedCode(codingResult.code())
                            .build()
            );

            context.addReview(review);

            if (review.isApproved()) {
                break;
            }

            context.nextIteration();

            codingResult = codingAgent.generate(
                    CodingRequest.builder()
                            .userRequest(userRequest)
                            .researchReport(researchResult.report())
                            .previousCode(codingResult.code())
                            .reviewResult(review)
                            .build()
            );

            context.setCodingResult(codingResult);
        }

        return context.getCodingResult();
    }
}