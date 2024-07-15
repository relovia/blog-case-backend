package com.example.demo.dataAccess.abstracts;

import com.example.demo.entities.concretes.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    List<BlogPost> findAllByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
