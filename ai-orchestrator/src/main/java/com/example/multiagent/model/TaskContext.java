package com.example.multiagent.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskContext {


    /**
     * Оригинальный запрос пользователя
     */
    private String userRequest;

    private String researchReport;

    private String generatedCode;


    private ReviewResult reviewResult;

    @Builder.Default
    private int iteration = 0;



    public boolean isApproved() {

        return reviewResult != null
                &&
                reviewResult.isApproved();
    }


}