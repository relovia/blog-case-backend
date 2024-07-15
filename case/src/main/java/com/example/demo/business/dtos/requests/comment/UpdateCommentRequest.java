package com.example.demo.business.dtos.requests.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentRequest {
    @Positive
    private int id;

    @NotBlank(message = "Content is required")
    private String content;
}
