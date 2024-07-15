package com.example.demo.business.dtos.requests.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
    @NotNull(message = "Blog post id is required")
    private int blogPostId;

    @NotNull(message = "User id is required")
    private int userId;

    @NotBlank(message = "Content is required")
    private String content;
}
