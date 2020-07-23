package com.candoit.dstagram.controller;

import com.candoit.dstagram.model.Post;
import com.candoit.dstagram.repository.PostRepository;
import com.candoit.dstagram.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{userId}/board")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return this.postService.getPosts();
    }

    @GetMapping("{userId}/board")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return this.postService.createPost(post);
    }

    @GetMapping("/{userid}/board/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int boardId, @RequestBody Post post) {
        return this.postService.updatePost(boardId, post);
    }

    @GetMapping("/{userid}/board/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int boardId) {
        this.postService.deletePost(boardId);
    }

}
