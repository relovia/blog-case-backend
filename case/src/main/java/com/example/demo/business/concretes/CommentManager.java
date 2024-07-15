package com.example.demo.business.concretes;

import com.example.demo.business.abstracts.CommentService;
import com.example.demo.business.abstracts.SecurityService;
import com.example.demo.business.dtos.requests.comment.CreateCommentRequest;
import com.example.demo.business.dtos.requests.comment.UpdateCommentRequest;
import com.example.demo.business.dtos.responses.comment.CreateCommentResponse;
import com.example.demo.business.dtos.responses.comment.GetAllCommentResponse;
import com.example.demo.business.dtos.responses.comment.GetCommentByIdResponse;
import com.example.demo.business.dtos.responses.comment.UpdateCommentResponse;
import com.example.demo.business.rules.CommentBusinessRules;
import com.example.demo.core.utilities.exceptions.types.BusinessException;
import com.example.demo.core.utilities.mapping.ModelMapperService;
import com.example.demo.dataAccess.abstracts.BlogPostRepository;
import com.example.demo.dataAccess.abstracts.CommentRepository;
import com.example.demo.dataAccess.abstracts.UserRepository;
import com.example.demo.entities.concretes.BlogPost;
import com.example.demo.entities.concretes.Comment;
import com.example.demo.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private CommentRepository commentRepository;
    private BlogPostRepository blogPostRepository;
    private UserRepository userRepository;
    private ModelMapperService mapperService;
    private CommentBusinessRules commentBusinessRules;
    private SecurityService securityService;

    @Override
    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest) {
        BlogPost blogPost = blogPostRepository.findById(createCommentRequest.getBlogPostId())
                .orElseThrow(() -> new BusinessException("Blog post not found."));
        User user = userRepository.findById(createCommentRequest.getUserId())
                .orElseThrow(() -> new BusinessException("User not found."));

        commentBusinessRules.checkCommentContentIsNotEmpty(createCommentRequest.getContent());
        // commentBusinessRules.checkCommentBlogPostUserId(blogPost.getId(), user.getId());

        Comment comment = mapperService.forRequest().map(createCommentRequest, Comment.class);
        comment.setBlogPost(blogPost);
        comment.setUser(user);
        comment.setCommentDate(LocalDateTime.now());

        commentRepository.save(comment);

        CreateCommentResponse response = mapperService.forResponse().map(comment, CreateCommentResponse.class);
        return response;
    }

    @Override
    public List<GetAllCommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> mapperService.forResponse().map(comment, GetAllCommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetCommentByIdResponse getCommentById(int id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comment not found."));

        GetCommentByIdResponse response = mapperService.forResponse().map(comment, GetCommentByIdResponse.class);
        response.setMessage("Comment successfully listed.");
        return response;
    }

    @Override
    public UpdateCommentResponse updateComment(UpdateCommentRequest request) {
        int commentId = request.getId();
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException("Comment not found."));

        int currentUserId = securityService.getCurrentUserId();
        if (existingComment.getUser().getId() != currentUserId) {
            throw new BusinessException("You are not authorized to update this comment.");
        }

        mapperService.forRequest().map(request, existingComment);
        existingComment.setUpdatedDate(LocalDateTime.now());
        commentRepository.save(existingComment);

        UpdateCommentResponse response = new UpdateCommentResponse();
        response.setMessage("Comment successfully updated.");
        return response;
    }

    @Override
    public void deleteComment(int id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comment not found."));

        int currentUserId = securityService.getCurrentUserId();
        if (existingComment.getUser().getId() != currentUserId) {
            throw new BusinessException("You are not authorized to delete this comment.");
        }
        commentRepository.deleteById(id);
    }
}
