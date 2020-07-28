package com.candoit.dstagram.post;

import com.candoit.dstagram.post.model.Post;
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
