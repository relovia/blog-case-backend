package com.example.demo.business.dtos.responses.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentByIdResponse {
    private int id;
    private int blogPostId;
    private String blogPostTitle;
    private int userId;
    private String username;
    private String content;
    private LocalDateTime commentDate;
    private String message;
}
