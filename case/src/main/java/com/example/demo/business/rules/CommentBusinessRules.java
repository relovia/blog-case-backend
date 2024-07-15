package com.example.demo.business.rules;

import com.example.demo.core.utilities.exceptions.types.BusinessException;
import com.example.demo.dataAccess.abstracts.CommentRepository;
import com.example.demo.entities.concretes.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentBusinessRules {
    private CommentRepository commentRepository;

    public void checkCommentContentIsNotEmpty(String content) {
        if (content == null ||content.trim().isEmpty()) {
            throw new BusinessException("Comment content cannot be empty.");
        }
    }

    public void checkCommentBlogPostUserId(int blogPostId, int userId) {
        if (commentRepository.findByBlogPostIdAndUserId(blogPostId, userId).isPresent()) {
            throw new BusinessException("Comment already exists.");
        }
    }
}
