package com.example.demo.core.utilities.exceptions.problemDetails;

import org.springframework.http.HttpStatus;

public class InternalServerErrorProblemDetails extends ProblemDetails {
    public InternalServerErrorProblemDetails() {
        setTitle("Internal server error");
        setType("https://youtube.com");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
