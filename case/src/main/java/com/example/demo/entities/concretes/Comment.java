package com.example.demo.entities.concretes;

import com.example.demo.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
@Builder
public class Comment extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "blog_post_id", referencedColumnName = "id", nullable = false)
    private BlogPost blogPost;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;
}
