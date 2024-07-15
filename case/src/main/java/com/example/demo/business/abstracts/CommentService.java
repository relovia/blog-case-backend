package com.example.demo.business.abstracts;

import com.example.demo.business.dtos.requests.comment.CreateCommentRequest;
import com.example.demo.business.dtos.requests.comment.UpdateCommentRequest;
import com.example.demo.business.dtos.responses.comment.CreateCommentResponse;
import com.example.demo.business.dtos.responses.comment.GetAllCommentResponse;
import com.example.demo.business.dtos.responses.comment.GetCommentByIdResponse;
import com.example.demo.business.dtos.responses.comment.UpdateCommentResponse;

import java.util.List;

public interface CommentService {
    CreateCommentResponse createComment(CreateCommentRequest createCommentRequest);

    List<GetAllCommentResponse> getAllComments();

    GetCommentByIdResponse getCommentById(int id);

    UpdateCommentResponse updateComment(UpdateCommentRequest request);

    void deleteComment(int id);
}
