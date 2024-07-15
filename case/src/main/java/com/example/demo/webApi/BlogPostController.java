package com.example.demo.webApi;

import com.example.demo.business.abstracts.BlogPostService;
import com.example.demo.business.dtos.requests.blogPost.CreateBlogPostRequest;
import com.example.demo.business.dtos.requests.blogPost.UpdateBlogPostRequest;
import com.example.demo.business.dtos.responses.blogPost.CreateBlogPostResponse;
import com.example.demo.business.dtos.responses.blogPost.GetAllBlogPostResponse;
import com.example.demo.business.dtos.responses.blogPost.GetBlogPostByIdResponse;
import com.example.demo.business.dtos.responses.blogPost.UpdateBlogPostResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogposts")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", ""})
public class BlogPostController {
    private BlogPostService blogPostService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogPostResponse createBlogPost(@RequestBody @Valid CreateBlogPostRequest request) {
        return blogPostService.createBlogPost(request);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBlogPostResponse> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetBlogPostByIdResponse getBlogPostById(@PathVariable int id) {
        return blogPostService.getBlogPostById(id);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateBlogPostResponse updateBlogPost(@RequestBody UpdateBlogPostRequest request) {
        return blogPostService.updateBlogPost(request);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBlogPost(@PathVariable int id) {
        blogPostService.deleteBlogPost(id);
    }
}
