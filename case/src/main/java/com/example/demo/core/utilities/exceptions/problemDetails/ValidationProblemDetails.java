package com.example.demo.core.utilities.exceptions.problemDetails;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> errors;

    public ValidationProblemDetails() {
        setTitle("Validation rule violation");
        setDetail("Validation problem");
        setType("https://youtube.com");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }
}
