package com.example.demo.business.dtos.responses.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResponse {
    private int id;
    private int blogPostId;
    private int userId;
    private String content;
    private LocalDateTime commentDate;
}
