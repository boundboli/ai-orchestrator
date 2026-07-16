package com.example.multiagent.service.impl;

import com.example.multiagent.agent.CodingAgent;
import com.example.multiagent.agent.ResearchAgent;
import com.example.multiagent.agent.ReviewAgent;
import com.example.multiagent.model.AgentResponse;
import com.example.multiagent.model.TaskContext;
import com.example.multiagent.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchestratorServiceImpl implements OrchestratorService {

    private static final int MAX_ITERATIONS = 3;

    private final ResearchAgent researchAgent;
    private final CodingAgent codingAgent;
    private final ReviewAgent reviewAgent;

    @Override
    public AgentResponse execute(String userRequest) {

        TaskContext context = TaskContext.builder()
                .userRequest(userRequest)
                .build();

        // Research
        context.setResearchReport(
                researchAgent.research(userRequest)
        );

        // First generation
        context.setGeneratedCode(
                codingAgent.generate(
                        buildCodingPrompt(context)
                )
        );

        while (context.getIteration() < MAX_ITERATIONS) {

            context.setReviewResult(
                    reviewAgent.review(
                            context.getGeneratedCode()
                    )
            );

            if (context.isApproved()) {
                break;
            }

            context.setIteration(
                    context.getIteration() + 1
            );

            context.setGeneratedCode(
                    codingAgent.generate(
                            buildImprovementPrompt(context)
                    )
            );
        }

        return AgentResponse.builder()
                .status(context.isApproved() ? "SUCCESS" : "MAX_ITERATIONS_REACHED")
                .iterations(context.getIteration())
                .result(context.getGeneratedCode())
                .build();
    }

    private String buildCodingPrompt(TaskContext context) {

        return """
                User request:
                                
                %s
                
                Research:
                                
                %s
                """.formatted(
                context.getUserRequest(),
                context.getResearchReport()
        );
    }

    private String buildImprovementPrompt(TaskContext context) {

        return """
                Improve the following code.

                Original request:

                %s

                Existing code:

                %s

                Reviewer feedback:

                %s
                """.formatted(
                context.getUserRequest(),
                context.getGeneratedCode(),
                String.join(
                        "\n",
                        context.getReviewResult().getFeedback()
                )
        );
    }
}