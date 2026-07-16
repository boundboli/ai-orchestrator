package com.example.multiagent.service.impl;

import com.example.multiagent.agent.CodingAgent;
import com.example.multiagent.agent.ResearchAgent;
import com.example.multiagent.agent.ReviewerAgent;

import com.example.multiagent.model.AgentResponse;
import com.example.multiagent.model.TaskContext;
import com.example.multiagent.model.ReviewResult;

import com.example.multiagent.model.dto.CodingRequest;
import com.example.multiagent.model.dto.CodingResult;
import com.example.multiagent.model.dto.ResearchResult;
import com.example.multiagent.model.dto.ReviewRequest;

import com.example.multiagent.service.CodeStorageService;
import com.example.multiagent.service.OrchestratorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrchestratorServiceImpl implements com.example.multiagent.service.OrchestratorService {


    private static final int MAX_ITERATIONS = 3;


    private final ResearchAgent researchAgent;
    private final CodingAgent codingAgent;
    private final ReviewerAgent reviewerAgent;
    private final CodeStorageService codeStorageService;


    @Override
    public AgentResponse execute(String userRequest) {


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




        codeStorageService.saveProject(codingResult);




        while (context.getIteration() < MAX_ITERATIONS) {


            String generatedCode = codingResult.files()
                    .stream()
                    .map(CodingResult.GeneratedFile::content)
                    .collect(Collectors.joining("\n\n"));



            ReviewResult review = reviewerAgent.review(
                    ReviewRequest.builder()
                            .userRequest(userRequest)
                            .generatedCode(generatedCode)
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
                            .previousCode(generatedCode)
                            .reviewResult(review)
                            .build()
            );


            context.setCodingResult(codingResult);



            codeStorageService.saveProject(codingResult);
        }



        return AgentResponse.builder()
                .result(
                        "Project generated successfully: "
                                + codingResult.projectName()
                )
                .build();
    }
}