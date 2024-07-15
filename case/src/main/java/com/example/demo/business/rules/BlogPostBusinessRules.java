package com.example.demo.business.rules;

import com.example.demo.dataAccess.abstracts.BlogPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BlogPostBusinessRules {
    private BlogPostRepository blogPostRepository;


}
