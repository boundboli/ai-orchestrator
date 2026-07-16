package com.example.multiagent.service;

import com.example.multiagent.model.dto.CodingResult;

import java.nio.file.Path;

public interface CodeStorageService {

    Path saveProject(CodingResult codingResult);

}