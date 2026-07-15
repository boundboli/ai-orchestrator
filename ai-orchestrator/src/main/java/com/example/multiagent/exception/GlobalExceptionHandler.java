package com.example.multiagent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validation(
            MethodArgumentNotValidException e
    ) {

        return Map.of(
                "error",
                e.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage()
        );

    }

}