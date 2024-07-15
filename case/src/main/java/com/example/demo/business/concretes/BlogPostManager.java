package com.example.demo.business.concretes;

import com.example.demo.business.abstracts.BlogPostService;
import com.example.demo.business.abstracts.SecurityService;
import com.example.demo.business.dtos.requests.blogPost.CreateBlogPostRequest;
import com.example.demo.business.dtos.requests.blogPost.UpdateBlogPostRequest;
import com.example.demo.business.dtos.responses.blogPost.CreateBlogPostResponse;
import com.example.demo.business.dtos.responses.blogPost.GetAllBlogPostResponse;
import com.example.demo.business.dtos.responses.blogPost.GetBlogPostByIdResponse;
import com.example.demo.business.dtos.responses.blogPost.UpdateBlogPostResponse;
import com.example.demo.business.rules.BlogPostBusinessRules;
import com.example.demo.core.utilities.mapping.ModelMapperService;
import com.example.demo.dataAccess.abstracts.BlogPostRepository;
import com.example.demo.dataAccess.abstracts.UserRepository;
import com.example.demo.entities.concretes.BlogPost;
import com.example.demo.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlogPostManager implements BlogPostService {
    private BlogPostRepository blogPostRepository;
    private UserRepository userRepository;
    private ModelMapperService mapperService;
    private BlogPostBusinessRules blogPostBusinessRules;
    private SecurityService securityService;

    @Override
    public CreateBlogPostResponse createBlogPost(CreateBlogPostRequest createBlogPostRequest) {
        int currentUserId = securityService.getCurrentUserId();

        User author = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("Author not found."));

        if (currentUserId != author.getId() && !securityService.isAdmin()) {
            throw new RuntimeException("You are not authorized to create this post.");
        }

        BlogPost blogPost = mapperService.forRequest().map(createBlogPostRequest, BlogPost.class);
        blogPost.setAuthor(author);
        blogPost.setCreatedDate(LocalDateTime.now());
        blogPost.setPublishedDate(LocalDateTime.now());
        blogPostRepository.save(blogPost);

        CreateBlogPostResponse response = mapperService.forResponse().map(blogPost, CreateBlogPostResponse.class);
        response.setAuthorName(author.getFirstName() + " " + author.getLastName());
        return response;
    }

    @Override
    public List<GetAllBlogPostResponse> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return blogPosts.stream()
                .map(blogPost -> {
                    GetAllBlogPostResponse response = mapperService.forResponse().map(blogPost, GetAllBlogPostResponse.class);
                    response.setAuthorName(blogPost.getAuthor().getFirstName() + " " + blogPost.getAuthor().getLastName());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public GetBlogPostByIdResponse getBlogPostById(int id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog post not found."));

        GetBlogPostByIdResponse response = mapperService.forResponse().map(blogPost, GetBlogPostByIdResponse.class);
        response.setMessage("Blog post successfully listed.");
        response.setAuthorName(blogPost.getAuthor().getFirstName() + " " + blogPost.getAuthor().getLastName());
        return response;
    }

    @Override
    public UpdateBlogPostResponse updateBlogPost(UpdateBlogPostRequest request) {
        int blogPostId = request.getId();
        BlogPost existingBlogPost = blogPostRepository.findById(blogPostId)
                .orElseThrow(() -> new RuntimeException("Blog post not found."));

        int currentUserId = securityService.getCurrentUserId();
        if (existingBlogPost.getAuthor().getId() != currentUserId && !securityService.isAdmin()) {
            throw new RuntimeException("You are not authorized to update this post.");
        }

        mapperService.forRequest().map(request, existingBlogPost);
        existingBlogPost.setUpdatedDate(LocalDateTime.now());
        blogPostRepository.save(existingBlogPost);

        UpdateBlogPostResponse response = new UpdateBlogPostResponse();
        response.setMessage("Blog post successfully updated.");
        return response;
    }

    @Override
    public void deleteBlogPost(int id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog post not found."));

        int currentUserId = securityService.getCurrentUserId();
        if (blogPost.getAuthor().getId() != currentUserId && !securityService.isAdmin()) {
            throw new RuntimeException("You are not authorized to delete this post.");
        }
        blogPostRepository.deleteById(id);
    }
}
