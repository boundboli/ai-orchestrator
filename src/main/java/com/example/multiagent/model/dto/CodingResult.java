package com.example.multiagent.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CodingResult(

        @JsonProperty("projectName")
        String projectName,

        @JsonProperty("description")
        String description,

        @JsonProperty("files")
        List<GeneratedFile> files,

        @JsonProperty("explanation")
        String explanation

) {

    public record GeneratedFile(

            @JsonProperty("path")
            String path,

            @JsonProperty("content")
            String content

    ) {
    }


    public String code() {

        StringBuilder builder = new StringBuilder();

        for (GeneratedFile file : files) {
            builder.append("\n\n===== ")
                    .append(file.path())
                    .append(" =====\n\n")
                    .append(file.content());
        }

        return builder.toString();
    }
}