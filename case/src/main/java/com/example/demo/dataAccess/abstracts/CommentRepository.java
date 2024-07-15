package com.example.demo.dataAccess.abstracts;

import com.example.demo.entities.concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByBlogPostIdAndUserId(int blogPostId, int userId);

}
