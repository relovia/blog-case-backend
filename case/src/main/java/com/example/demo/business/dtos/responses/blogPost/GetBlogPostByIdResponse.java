package com.example.demo.business.dtos.responses.blogPost;

import com.example.demo.business.dtos.responses.comment.GetAllCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBlogPostByIdResponse {
    private int id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime publishedDate;
    private List<GetAllCommentResponse> comments;
    private String message;
}
