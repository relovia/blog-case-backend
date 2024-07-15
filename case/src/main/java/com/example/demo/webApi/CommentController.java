package com.example.demo.webApi;

import com.example.demo.business.abstracts.CommentService;
import com.example.demo.business.dtos.requests.comment.CreateCommentRequest;
import com.example.demo.business.dtos.requests.comment.UpdateCommentRequest;
import com.example.demo.business.dtos.responses.comment.CreateCommentResponse;
import com.example.demo.business.dtos.responses.comment.GetAllCommentResponse;
import com.example.demo.business.dtos.responses.comment.GetCommentByIdResponse;
import com.example.demo.business.dtos.responses.comment.UpdateCommentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", ""})
public class CommentController {
    private CommentService commentService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponse createComment(@RequestBody @Valid CreateCommentRequest createCommentRequest) {
        return commentService.createComment(createCommentRequest);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCommentByIdResponse getCommentById(@PathVariable int id) {
        return commentService.getCommentById(id);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateCommentResponse updateComment(@RequestBody UpdateCommentRequest request) {
        return commentService.updateComment(request);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
    }
}
