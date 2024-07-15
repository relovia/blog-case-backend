package com.example.demo.core.utilities.exceptions.handlers;

import com.example.demo.core.utilities.exceptions.problemDetails.BusinessProblemDetails;
import com.example.demo.core.utilities.exceptions.problemDetails.InternalServerErrorProblemDetails;
import com.example.demo.core.utilities.exceptions.problemDetails.ValidationProblemDetails;
import com.example.demo.core.utilities.exceptions.types.BusinessException;
import com.example.demo.core.utilities.exceptions.types.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public InternalServerErrorProblemDetails handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        InternalServerErrorProblemDetails details = new InternalServerErrorProblemDetails();
        details.setDetail(exception.getMessage());
        return details;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;
    }
}

