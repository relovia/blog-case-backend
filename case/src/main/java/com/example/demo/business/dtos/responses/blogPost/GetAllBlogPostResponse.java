package com.example.demo.business.dtos.responses.blogPost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBlogPostResponse {
    private int id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime publishedDate;
}
