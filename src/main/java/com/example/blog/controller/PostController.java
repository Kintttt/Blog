package com.example.blog.controller;


import com.example.blog.model.PostModel;
import com.example.blog.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost/{posterId}")
    public String createNewPost(@RequestBody PostModel postText, @PathVariable Long posterId){
        if(postText.getPostText().length() == 0){
            return "Please write a post!";
        }

        postService.createPost(posterId, postText.getPostText());
        return postText.getPostText();
    }

    @GetMapping("/allPosts")
    public List<PostModel> showAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/likePost/{userId}/{postId}")
    public String likePost(@PathVariable Long postId, @PathVariable Long userId){
        //postService.likePosts(userId, postId);

        return postService.likePosts(userId, postId);
    }

    @PostMapping("/unLikePost/{userId}/{postId}")
    public String unLikePost(@PathVariable Long postId, @PathVariable Long userId){
        return postService.unLikePosts(userId, postId);
    }

    @GetMapping("userPosts/{userId}")
    public List<PostModel> getAllPostByUserId(@PathVariable Long userId){
        return postService.getAllPostByUserId(userId);
    }

    @GetMapping("friendsPosts/{userId}")
    public List<PostModel> showAllFriendsPosts(@PathVariable Long userId){
        return postService.getFriendsPosts(userId);
    }
}
