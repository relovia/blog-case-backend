package com.example.demo.business.abstracts;

import com.example.demo.business.dtos.requests.blogPost.CreateBlogPostRequest;
import com.example.demo.business.dtos.requests.blogPost.UpdateBlogPostRequest;
import com.example.demo.business.dtos.responses.blogPost.CreateBlogPostResponse;
import com.example.demo.business.dtos.responses.blogPost.GetAllBlogPostResponse;
import com.example.demo.business.dtos.responses.blogPost.GetBlogPostByIdResponse;
import com.example.demo.business.dtos.responses.blogPost.UpdateBlogPostResponse;

import java.util.List;

public interface BlogPostService {
    CreateBlogPostResponse createBlogPost(CreateBlogPostRequest createBlogPostRequest);

    List<GetAllBlogPostResponse> getAllBlogPosts();

    GetBlogPostByIdResponse getBlogPostById(int id);

    UpdateBlogPostResponse updateBlogPost(UpdateBlogPostRequest request);

    void deleteBlogPost(int id);
}