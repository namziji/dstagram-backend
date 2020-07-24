package com.candoit.dstagram.service;

import com.candoit.dstagram.model.Post;
import com.candoit.dstagram.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    public PostRepository postRepository;

    public List<Post> getPosts() {

        return this.postRepository.findAll();

    }

    public Post createPost(Post post) {

        return this.postRepository.save(post);

    }

    public Post updatePost(int boardId, Post post) {

        return this.postRepository.save(post);
    }

    public void deletePost(int boardId) {
        this.postRepository.deleteById(boardId);
    }
}
