package com.example.multiagent.service.impl;

import com.example.multiagent.model.dto.CodingResult;
import com.example.multiagent.service.CodeStorageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CodeStorageServiceImpl implements CodeStorageService {


    @Override
    public Path saveProject(CodingResult codingResult) {

        try {

            Path projectDir = Path.of(
                    "generated-projects",
                    codingResult.projectName()
                            .replace(" ", "-")
                            .toLowerCase()
            );


            Files.createDirectories(projectDir);


            for (CodingResult.GeneratedFile file : codingResult.files()) {


                Path filePath = projectDir.resolve(file.path());


                Files.createDirectories(
                        filePath.getParent()
                );


                Files.writeString(
                        filePath,
                        file.content()
                );
            }


            return projectDir;


        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save generated project",
                    e
            );
        }
    }
}